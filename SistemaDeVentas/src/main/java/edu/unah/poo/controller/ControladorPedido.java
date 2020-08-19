package edu.unah.poo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unah.poo.model.Cliente;
import edu.unah.poo.model.Direccion;
import edu.unah.poo.model.ListaPedido;
import edu.unah.poo.model.Pedido;
import edu.unah.poo.model.Producto;
import edu.unah.poo.service.ServiceCliente;
import edu.unah.poo.service.ServiceDireccion;
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
	
	@Autowired
	ServiceDireccion serviceDireccion;
	
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
								@RequestParam(name="idPedido", required=false) String idPedido,
								Model model) {
		if(idCliente==null || idPedido==null) {
			return "sistema_error";
		}else {
			
			int c = Integer.parseInt(idCliente);
			int p = Integer.parseInt(idPedido);
			if(servicePedido.exist(p)){
				if(!serviceCliente.exist(c)) {
					return "sistema_error";
				}
				Cliente tmpCliente = this.serviceCliente.buscarCliente(c);
				List<Producto> inventario = this.serviceProducto.obtenerProductos();
				Pedido tmpPedido = this.servicePedido.buscarPedido(p);//hay que asignarle  la fecha
				LocalDate fecha = LocalDate.now();

				tmpPedido.setFechaVenta(fecha);
				model.addAttribute("pedido", tmpPedido);
				model.addAttribute("cliente", tmpCliente);
				model.addAttribute("inventario", inventario);
				model.addAttribute("direcciones", tmpCliente.getDirecciones());

				List<ListaPedido> registro_de_pedido = tmpPedido.getListaPedido();

				//_--------------------------------------------------------------------------------
//				List<Producto> productosPedido = new ArrayList<Producto>();
//				List<ListaPedido> listaPedido = this.serviceListaPedido.obtenerListaPedidos();
//				for(ListaPedido relacion: listaPedido) {
//					if(relacion.getIdPedido()==p) {
//						Producto producto = this.serviceProducto.buscarProducto(relacion.getIdProducto());
//						productosPedido.add(producto);
//					}
//				}
				//---------------------------------------------------------------------
				model.addAttribute("agregados", registro_de_pedido);
				
				return "sistema_pedido_datos"; // 
				
			}else {
				if(!serviceCliente.exist(c)) {
					return "sistema_error";
				}

				List<Producto> inventario = this.serviceProducto.obtenerProductos();
				Cliente tmpCliente = this.serviceCliente.buscarCliente(c);
				List<Direccion> direcciones = tmpCliente.getDirecciones();

				//Vamos a obtener una direccion default del cliente, mas adelante se le puede asignar una en especifico.
				Pedido tmpPedido = new Pedido(p, LocalDate.now(), false, tmpCliente, direcciones.get(0));
				this.servicePedido.crearPedido(tmpPedido);

				model.addAttribute("pedido", tmpPedido);
				model.addAttribute("cliente", tmpCliente);
				model.addAttribute("inventario", inventario);
				model.addAttribute("direcciones", direcciones);
				
				return "sistema_pedido_datos"; // 
			}
		}
	}
	
	@RequestMapping(value= "/sistema/crearPedido", method=RequestMethod.POST)
	public String crearPedido() {
		return "";
	}
	
	@RequestMapping(value = "/sistema/listarPedidos",method=RequestMethod.GET)
	public String listarPedidos(Model model) {
		List<Pedido> pedidos = this.servicePedido.obtenerPedidos();
		model.addAttribute("pedidos", pedidos);
		return "sistema_listar_pedidos";
	}
	
	@GetMapping("/sistema/pedidoDetalles/{id}")
	public String pedidoDetalles(@PathVariable("id") int idPedido, Model model) {
		Pedido pedido = this.servicePedido.buscarPedido(idPedido);
		List<ListaPedido> listaPedido = pedido.getListaPedido();
		Cliente cliente = this.serviceCliente.buscarCliente(pedido.getCliente().getIdCliente());
		model.addAttribute("listaPedido", listaPedido);
		model.addAttribute("pedido", pedido);
		model.addAttribute("cliente", cliente);
		return "sistema_pedido_detalles";
	}
	
	@RequestMapping(value ="/sistema/procesarPedido/{id}", method=RequestMethod.GET)
	public String procesar(@PathVariable("id") int idPedido, Model model) {
		Pedido pedido = this.servicePedido.buscarPedido(idPedido);
		model.addAttribute("pedido", pedido);
		return "sistema_pedido_procesar";
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
		
		Pedido pedido = this.servicePedido.buscarPedido(idPedido); //pedido con el cual se esta trabajando
		Cliente cliente = this.serviceCliente.buscarCliente(idCliente); //cliente al que pertenece el pedido
		Producto producto = this.serviceProducto.buscarProducto(idProducto); //producto que se esta agregando
		List<Producto> inventario1 = this.serviceProducto.obtenerProductos(); //Todos los Productos en la base

		ListaPedido listaPedido =  new ListaPedido(idPedido, idProducto, cantidad, producto.getPrecio(), pedido, producto);
		this.serviceListaPedido.crearListaPedido(listaPedido); //Guardar registro en la base

		List<ListaPedido> registro_de_pedido = pedido.getListaPedido();

		model.addAttribute("pedido", pedido);
		model.addAttribute("cliente", cliente);
		model.addAttribute("inventario", inventario1);
		model.addAttribute("direcciones", cliente.getDirecciones());
		model.addAttribute("agregados", registro_de_pedido);
		
		return "sistema_pedido_datos";
	}
	
	//====================================================================
	//  direccion
	//====================================================================	
	@RequestMapping(value="/sistema/cambiarDireccion", method=RequestMethod.POST)
	public String cambiarDireccion(@RequestParam(value="idDireccion") int idDireccion,
								   @RequestParam(value="idPedido") int idPedido,
								   @RequestParam(value="idCliente") int idCliente,
								   Model model) {
		Pedido pedido = this.servicePedido.buscarPedido(idPedido);
		Direccion direccion = this.serviceDireccion.buscarDireccion(idDireccion);
		pedido.setDireccion(direccion);
		this.servicePedido.crearPedido(pedido);
		
		Cliente cliente = this.serviceCliente.buscarCliente(idCliente);
		List<Producto> inventario = this.serviceProducto.obtenerProductos();


		List<ListaPedido> registro_de_pedido = pedido.getListaPedido();
		
		
		model.addAttribute("pedido", pedido);
		model.addAttribute("cliente", cliente);
		model.addAttribute("inventario", inventario);
		model.addAttribute("direcciones", cliente.getDirecciones());
		model.addAttribute("agregados", registro_de_pedido);
		return "sistema_pedido_datos";
	}
	
}
