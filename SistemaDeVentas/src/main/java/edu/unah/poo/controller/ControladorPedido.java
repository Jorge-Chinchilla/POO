package edu.unah.poo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unah.poo.model.Cliente;
import edu.unah.poo.model.Direccion;
import edu.unah.poo.model.ListaPedido;
import edu.unah.poo.model.Pedido;
import edu.unah.poo.model.Producto;
import edu.unah.poo.service.ServiceCliente;
import edu.unah.poo.service.ServiceListaPedido;
import edu.unah.poo.service.ServicePedido;
import edu.unah.poo.service.ServiceProducto;

@Controller
public class ControladorPedido {

	@Autowired
	ServicePedido servicePedido;
	
	@Autowired
	ServiceProducto serviceProducto;
	
	@Autowired
	ServiceCliente serviceCliente;
	
	@Autowired
	ServiceListaPedido serviceListaPedido;
	
	//====================================================================
	//  Plantillas
	//====================================================================
	@RequestMapping(value= "/sistema/registrarPedido", method=RequestMethod.GET)
	public String registrarPedido(@RequestParam(name="idCliente", required=false) String idCliente,
								  Model model) {
		//en caso de que idCliente no reciba ningun parametro, el tipo se declara como String ya que int no puede ser null, despues lo transformamos a int
		if(idCliente!=null) {
			int id = Integer.parseInt(idCliente);
		}
		return "sistema_pedido_registrar";
	}
	
	//====================================================================
	//  Pedido
	//====================================================================	
	@RequestMapping(value="/sistema/generarPedido", method=RequestMethod.POST)
	public String generarPedido(@RequestParam(name="idCliente", required=false) String idCliente,
								@RequestParam(name="idPedido", required=false) String idPedido, Model model) {
		if(idCliente==null || idPedido==null) {
			return "sistema_error";
		}else {
			
			int c = Integer.parseInt(idCliente);
			int p = Integer.parseInt(idPedido);
			if(servicePedido.exist(p)){
				if(!serviceCliente.exist(c)) {
					return "sistema_error";
				}
				Pedido tmpPedido = this.servicePedido.buscarPedido(p);//hay que asignarle  la fecha
				LocalDate fecha = LocalDate.now();
				tmpPedido.setFechaVenta(fecha);
				List<Producto> inventario = this.serviceProducto.obtenerProductos();
				Cliente tmpCliente = this.serviceCliente.buscarCliente(c);
				model.addAttribute("pedido", tmpPedido);
				model.addAttribute("cliente", tmpCliente);
				model.addAttribute("inventario", inventario);
				model.addAttribute("direcciones", tmpCliente.getDirecciones());
				
				List<Producto> productosPedido = new ArrayList<Producto>();
				List<ListaPedido> listaPedido = this.serviceListaPedido.obtenerListaPedidos();
				for(ListaPedido relacion: listaPedido) {
					if(relacion.getIdPedido()==p) {
						Producto producto = this.serviceProducto.buscarProducto(relacion.getIdProducto());
						productosPedido.add(producto);
					}
				}
				model.addAttribute("agregados", productosPedido);
				
				return "sistema_pedido_datos"; // 
				
			}else {
				if(!serviceCliente.exist(c)) {
					return "sistema_error";
				}
				
				Cliente tmpCliente = this.serviceCliente.buscarCliente(c);
				//Vamos a obtener una direccion default del cliente, mas adelante se le puede asignar una en especifico.
				List<Direccion> direcciones = tmpCliente.getDirecciones();
				Pedido tmpPedido = new Pedido(p, LocalDate.now(), tmpCliente, direcciones.get(0));
				this.servicePedido.crearPedido(tmpPedido);
				List<Producto> inventario = this.serviceProducto.obtenerProductos();
				model.addAttribute("pedido", tmpPedido);
				model.addAttribute("cliente", tmpCliente);
				model.addAttribute("inventario", inventario);
				model.addAttribute("direcciones", tmpCliente.getDirecciones());
				
				return "sistema_pedido_datos"; // 
			}
		}
	}
	
	@RequestMapping(value= "/sistema/crearPedido", method=RequestMethod.POST)
	public String crearPedido() {
		return "";
	}

	//====================================================================
	//  producto
	//====================================================================	
	@RequestMapping(value="/sistema/agregarProducto", method=RequestMethod.POST)
	public String agregarProducto(@RequestParam(name="idProducto") int idProducto,
								  @RequestParam(name="cantidad") int cantidad,
								  @RequestParam(name="idPedido") int idPedido, 
								  @RequestParam(name="idCliente") int idCliente,
								  Model model) {
		
		Pedido tmpPedido = this.servicePedido.buscarPedido(idPedido);
		Producto tmpProducto = this.serviceProducto.buscarProducto(idProducto);
		ListaPedido listaPedido =  new ListaPedido(idPedido, idProducto, cantidad, tmpProducto.getPrecio(), tmpPedido, tmpProducto);
		this.serviceListaPedido.crearListaPedido(listaPedido);
		
		Pedido tmpPedido1 = this.servicePedido.buscarPedido(idPedido);
		Cliente tmpCliente1 = this.serviceCliente.buscarCliente(idCliente);
		List<Producto> inventario1 = this.serviceProducto.obtenerProductos();
		
		List<Producto> productosPedido1 = new ArrayList<Producto>();
		List<ListaPedido> listaPedido1 = new ArrayList<ListaPedido>();
		
		for(ListaPedido relacion: listaPedido1) {
			if(relacion.getIdPedido()==idPedido) {
				Producto producto1 = this.serviceProducto.buscarProducto(relacion.getIdProducto());
				productosPedido1.add(producto1);
			}
		}
		
		model.addAttribute("pedido", tmpPedido1);
		model.addAttribute("cliente", tmpCliente1);
		model.addAttribute("inventario", inventario1);
		model.addAttribute("direcciones", tmpCliente1.getDirecciones());
		model.addAttribute("agregados", productosPedido1);
		
		return "sistema_pedido_datos";
		
	}
	
}
