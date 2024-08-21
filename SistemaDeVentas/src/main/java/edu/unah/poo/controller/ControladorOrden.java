package edu.unah.poo.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unah.poo.function.EmailSender;
import edu.unah.poo.model.Cliente;
import edu.unah.poo.model.Direccion;
import edu.unah.poo.model.Empleado;
import edu.unah.poo.model.ListaPedido;
import edu.unah.poo.model.Orden;
import edu.unah.poo.model.Pago;
import edu.unah.poo.model.PagoEfectivo;
import edu.unah.poo.model.PagoTarjeta;
import edu.unah.poo.model.Pedido;
import edu.unah.poo.model.Producto;
import edu.unah.poo.service.ServiceEmpleado;
import edu.unah.poo.service.ServiceListaPedido;
import edu.unah.poo.service.ServiceOrden;
import edu.unah.poo.service.ServicePago;
import edu.unah.poo.service.ServicePagoEfectivo;
import edu.unah.poo.service.ServicePagoTarjeta;
import edu.unah.poo.service.ServicePedido;
import edu.unah.poo.service.ServiceProducto;

@Controller
public class ControladorOrden {
	
	@Autowired
	ServiceOrden serviceOrden;
	
	@Autowired
	ServiceEmpleado serviceEmpleado;
	
	@Autowired
	ServicePagoEfectivo servicePagoEfectivo;
	
	@Autowired
	ServicePagoTarjeta servicePagoTarjeta;
	
	@Autowired
	ServiceListaPedido serviceListaPedido;
	
	@Autowired
	ServicePedido servicePedido;
	
	@Autowired
	ServicePago servicePago;
	
	@Autowired
	ServiceProducto serviceProducto;

	
	//====================================================================
	//  plantillas
	//====================================================================
	@RequestMapping(value ="/sistema/crearOrden", method=RequestMethod.POST)
	public String crearOrden(@RequestParam(name="idPedido") int idPedido,
							 @RequestParam(name="idOrden") int idOrden,
							 @RequestParam(name = "fechaEntrega") @DateTimeFormat(iso = ISO.DATE)  LocalDate fechaEntrega,
							 @RequestParam(name="idEmpleado") int idEmpleado) {
		
		Pedido pedido = this.servicePedido.buscarPedido(idPedido);
		pedido.setEstado(true);
		this.servicePedido.crearPedido(pedido);
		Empleado empleado = this.serviceEmpleado.buscarEmpleado(idEmpleado);
		
		//No se pudo desarrollar la parte de pago por el tiempo justo de entrega
		
		//PagoEfectivo pagoEfectivo = new PagoEfectivo(0,0,0);
		//this.servicePagoEfectivo.crearPagoEfectivo(pagoEfectivo);
		//PagoTarjeta pagoTarjeta = new PagoTarjeta(0,"0","0","0","0");
		//this.servicePagoTarjeta.crearPagoTarjeta(pagoTarjeta);
		
		double valorDeOrden = this.servicePedido.obtenerValor(idPedido);
		double valorDeEnvio = 55;
		
		
		Pago pago = new Pago(1, valorDeOrden, valorDeEnvio);
		this.servicePago.crearPago(pago);
		
		Orden orden = new Orden(idOrden, LocalDate.now(), fechaEntrega, empleado, pago, pedido);
		this.serviceOrden.crearOrden(orden);
		
		//actualizar cantidad de productos en el inventario al crear la orden
		int cantFinal =0;
		List<ListaPedido> productosEnPedido = pedido.getListaPedido();
		for(ListaPedido productoPedido: productosEnPedido) {
			Producto producto = this.serviceProducto.buscarProducto(productoPedido.getIdProducto());
			cantFinal = producto.getCantidad()-productoPedido.getCantidad();
			producto.setCantidad(cantFinal);
			this.serviceProducto.crearProducto(producto);
		}
		
		//Desarrollamos el email para el cliente
		Cliente cliente = pedido.getCliente();
		EmailSender emailSender = new EmailSender(cliente.getEmail(),
                "Su Orden fue procesada",
                "Hola,\n Tu pedido con numero de orden "+orden.getIdOrden()+" ya fue procesado!\n\nEl total del pedido es: "+orden.getPago().getValorPedido() +"lps y "+orden.getPago().getValorEnvio()+"lps de envio. \n\n Gracias por preferirnos");
        try {
            emailSender.sendmail();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
		
		return "sistema_realizado_exitosamente";
	}
	
	@GetMapping("/sistema/ordenesProcesadas")
	public String ordenesListar(Model model) {
		List<Orden> ordenes = this.serviceOrden.obtenerOrdenes();
		model.addAttribute("ordenes", ordenes);
		return "sistema_listar_orden";
	}
	
	@GetMapping("/sistema/borrador")
	public String borrador(Model model) {
		List<Pedido> pedidos = this.servicePedido.obtenerPedidos();
		List<Pedido> pedidosInactivos = new ArrayList<Pedido>();
		for(Pedido p: pedidos) {
			if(p.getEstado()==false) {
				pedidosInactivos.add(p);
			}
		}
		model.addAttribute("pedidosInactivos", pedidosInactivos);
		return "sistema_borrador";
	}
	
	@GetMapping("/sistema/actualizarPedido/{id}")
	public String actualizarPedido(@PathVariable("id") int idPedido, Model model) {
		
		Pedido pedido = this.servicePedido.buscarPedido(idPedido);
		Cliente cliente = pedido.getCliente();
		List<Producto> inventario = this.serviceProducto.obtenerProductos();
		List<ListaPedido> registro_de_pedido = pedido.getListaPedido();
		List<Direccion> direccionesActivas = new ArrayList<Direccion>();
		for(Direccion d: cliente.getDirecciones()) {
			if(d.getActivo()==1) {
				direccionesActivas.add(d);
			}
		}
	
		model.addAttribute("pedido", pedido);
		model.addAttribute("cliente", cliente);
		model.addAttribute("inventario", inventario);
		model.addAttribute("direcciones", direccionesActivas);
		model.addAttribute("agregados", registro_de_pedido);
		return "sistema_pedido_datos";
		
	}
	
}
