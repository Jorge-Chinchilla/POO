package edu.unah.poo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.unah.poo.model.Cliente;
import edu.unah.poo.model.Despacho;
import edu.unah.poo.model.Direccion;
import edu.unah.poo.model.Empleado;
import edu.unah.poo.model.ListaPedido;
import edu.unah.poo.model.Pago;
import edu.unah.poo.model.PagoEfectivo;
import edu.unah.poo.model.PagoTarjeta;
import edu.unah.poo.model.Pedido;
import edu.unah.poo.model.Producto;
import edu.unah.poo.model.Proveedor;
import edu.unah.poo.service.ServiceCliente;
import edu.unah.poo.service.ServiceDespacho;
import edu.unah.poo.service.ServiceDireccion;
import edu.unah.poo.service.ServiceEmpleado;
import edu.unah.poo.service.ServiceListaPedido;
import edu.unah.poo.service.ServicePago;
import edu.unah.poo.service.ServicePagoEfectivo;
import edu.unah.poo.service.ServicePagoTarjeta;
import edu.unah.poo.service.ServicePedido;
import edu.unah.poo.service.ServiceProducto;
import edu.unah.poo.service.ServiceProveedor;

@RestController
public class Controlador {

	@Autowired
	ServiceCliente serviceCliente;
	
	@Autowired
	ServiceDireccion serviceDireccion;
	
	@Autowired
	ServicePedido servicePedido;
	
	@Autowired
	ServiceProveedor serviceProveedor;
	
	@Autowired
	ServiceProducto serviceProducto;
	
	@Autowired
	ServiceListaPedido serviceListaPedido;
	
	@Autowired
	ServiceDespacho serviceDespacho;
	
	@Autowired
	ServiceEmpleado serviceEmpleado;
	
	@Autowired
	ServicePagoEfectivo servicePagoEfectivo; 
	
	@Autowired
	ServicePagoTarjeta servicePagoTarjeta;
	
	@Autowired
	ServicePago servicePago;
	
	// /cliente/crearCliente?idCliente=100&nombre=Jorge Alberto Chinchilla Cruz&email=jorgech@gmail.com&telefono=33798888&credito=2095
	
	//====================================================================
	//  Cliente
	//====================================================================
	@RequestMapping (value = "/cliente/crearCliente",method=RequestMethod.GET)
	public Cliente crearCliente(@RequestParam(name = "idCliente") int idCliente,
			                    @RequestParam(name = "nombre") String nombre,
			                    @RequestParam(name = "email") String email,
			                    @RequestParam(name = "telefono") String telefono,
			                    @RequestParam(name = "credito") double credito) {
		 
		Cliente tmpCliente = new Cliente(idCliente,nombre,email,telefono, credito);
		this.serviceCliente.crearCliente(tmpCliente);
		return tmpCliente;
	}
	
	@RequestMapping(value = "/cliente/listarCliente",method=RequestMethod.GET)
	public List<Cliente> listarClientes(){
		return this.serviceCliente.obtenerClientes();
	}
	
	//====================================================================
	//  direccion
	//====================================================================
	@RequestMapping (value = "/direccion/crearDireccion",method=RequestMethod.GET)
	public Direccion crearDireccion(@RequestParam(name = "idDireccion") int idDireccion,
			                    @RequestParam(name = "tipo") String tipo,
			                    @RequestParam(name = "direccion") String direccion,
			                    @RequestParam(name = "idCliente") int idCliente) {
		
		Cliente cliente =  this.serviceCliente.buscarCliente(idCliente);
		Direccion tmpDireccion = new Direccion(idCliente,tipo,direccion, cliente);
		this.serviceDireccion.crearDireccion(tmpDireccion);
		return tmpDireccion;
	}
	
	@RequestMapping(value = "/direccion/listarDirecciones",method=RequestMethod.GET)
	public List<Direccion> listarDirecciones(){
		return this.serviceDireccion.obtenerDirecciones();
	}
	
	//====================================================================
	//  pedido
	//====================================================================
	//@RequestParam(name = "fecha") @DateTimeFormat(iso = ISO.DATE) LocalDate fecha
	@RequestMapping(value = "/pedido/crearPedido",method=RequestMethod.GET)
	public Pedido crearPedido(@RequestParam(name = "idPedido") int idPedido,
							  @RequestParam(name = "idCliente") int idCliente,
							  @RequestParam(name = "fecha") @DateTimeFormat(iso = ISO.DATE) LocalDate fecha,
							  @RequestParam(name = "idDireccion") int idDireccion) {
		Cliente cliente = this.serviceCliente.buscarCliente(idCliente);
		Direccion direccion = this.serviceDireccion.buscarDireccion(idDireccion);
		Pedido pedido = new Pedido(idPedido, fecha, cliente, direccion);
		this.servicePedido.crearPedido(pedido);
		return pedido;
	}
	
	@RequestMapping(value = "/pedido/listaPedidos",method=RequestMethod.GET)
	public List<Pedido> listarPedidos(){
		return this.servicePedido.obtenerPedidos();
	}
	
	//====================================================================
	//  proveedor
	//====================================================================
	@RequestMapping(value = "/proveedor/crearProveedor",method=RequestMethod.GET)
	public Proveedor crearProveedor(@RequestParam(name="idProveedor") int idProveedor,
									@RequestParam(name="nombre") String nombre,
									@RequestParam(name="telefono") String telefono,
									@RequestParam(name="email") String email,
									@RequestParam(name="rtn") String rtn) {
		Proveedor proveedor = new Proveedor(idProveedor,nombre, telefono, email,rtn);
		this.serviceProveedor.crearProveedor(proveedor);
		return proveedor;
	}
	
	@RequestMapping(value = "/proveedor/listarProveedores",method=RequestMethod.GET)
	public List<Proveedor> listarProveedores(){
		return this.serviceProveedor.obtenerProveedores();
	}	
	
	//====================================================================
	//  producto
	//====================================================================
	@RequestMapping(value = "/producto/crearProducto",method=RequestMethod.GET)
	public Producto crearProducto(@RequestParam(name="idProducto") int idProducto,
								  @RequestParam(name="nombre") String nombre,
								  @RequestParam(name="unidad") String unidad,
								  @RequestParam(name="precio") double precio,
								  @RequestParam(name="descripcion") String descripcion,
								  @RequestParam(name="cantidad") int cantidad,
								  @RequestParam(name="idProveedor") int idProveedor) {
		Proveedor proveedor = this.serviceProveedor.buscarProveedor(idProveedor);
		Producto producto = new Producto(idProducto, nombre, unidad, precio, descripcion, cantidad, proveedor);
		this.serviceProducto.crearProducto(producto);
		return producto;
	}
	
	@RequestMapping(value = "/producto/listarProductos",method=RequestMethod.GET)
	public List<Producto> listarProductos(){
		return this.serviceProducto.obtenerProductos();
	}
	
	//====================================================================
	//  listaPedido
	//====================================================================
	@RequestMapping(value = "/listaPedido/crearListaPedido",method=RequestMethod.GET)
	public ListaPedido crearListaPedido(@RequestParam(name="idPedido") int idPedido,
								  	 @RequestParam(name="idProducto") int idProduto,
								  	 @RequestParam(name="cantidad") int cantidad) {
		Pedido pedido = this.servicePedido.buscarPedido(idPedido);
		Producto producto = this.serviceProducto.buscarProducto(idProduto);
		ListaPedido listaPedido = new ListaPedido(idPedido, idProduto, cantidad, producto.getPrecio(), pedido, producto);
		this.serviceListaPedido.crearListaPedido(listaPedido);
		return listaPedido;
	}
	
	@RequestMapping(value = "/listaPedido/listarListaPedido",method=RequestMethod.GET)
	public List<ListaPedido> listarListaPedido(){
		return this.serviceListaPedido.obtenerListaPedidos();
	}	
	
	//====================================================================
	//  despacho
	//====================================================================
	@RequestMapping(value = "/despacho/crearDespacho",method=RequestMethod.GET)
	public Despacho crearDespacho(@RequestParam(name="idPedido") int idPedido,
								  	 @RequestParam(name="estado") boolean estado) {
		Pedido pedido = this.servicePedido.buscarPedido(idPedido);
		boolean aprobado = true;
		Despacho despacho = new Despacho(pedido, estado);
		this.serviceDespacho.crearDespacho(despacho);
		return despacho;
	}
	
	@RequestMapping(value = "/despacho/listarDespachos",method=RequestMethod.GET)
	public List<Despacho> listarDespachos(){
		return this.serviceDespacho.obtenerDespachos();
	}	
	
	//====================================================================
	//  empleado
	//====================================================================
	@RequestMapping(value = "/empleado/crearEmpleado",method=RequestMethod.GET)
	public Empleado crearEmpleado(@RequestParam(name="idEmpleado") int idEmpleado,
								  @RequestParam(name="nombre") String nombre,
								  @RequestParam(name="telefono") String telefono,
								  @RequestParam(name="direccion") String direccion) {
		
		Empleado empleado = new Empleado(idEmpleado, nombre, telefono, direccion);
		this.serviceEmpleado.crearEmpleado(empleado);
		return empleado;
	}
	
	@RequestMapping(value = "/empleado/listarEmpleados",method=RequestMethod.GET)
	public List<Empleado> listarEmpleados(){
		return this.serviceEmpleado.obtenerEmpleados();
	}	
	
	//====================================================================
	//  pagoEfectivo
	//====================================================================
	@RequestMapping(value = "/pagoEfectivo/crearPagoEfectivo",method=RequestMethod.GET)
	public PagoEfectivo crearPagoEfectivo(@RequestParam(name="idEfectivo") int idEfectivo,
								  @RequestParam(name="montoPagado") double montoPagado,
								  @RequestParam(name="vuelto") double vuelto) {
		PagoEfectivo pagoEfectivo = new PagoEfectivo(idEfectivo, montoPagado, vuelto);
		this.servicePagoEfectivo.crearPagoEfectivo(pagoEfectivo);
		return pagoEfectivo;
	}
		
	@RequestMapping(value = "/pagoEfectivo/listarPagoEfectivo",method=RequestMethod.GET)
	public List<PagoEfectivo> listarPagoEfectivo(){
		return this.servicePagoEfectivo.obtenerPagoEfectivo();
	}	
		
	//====================================================================
	//  pagoTarjeta
	//====================================================================
	@RequestMapping(value = "/pagoTarjeta/crearPagoTarjeta",method=RequestMethod.GET)
	public PagoTarjeta crearPagoTarjeta(@RequestParam(name="idTarjeta") int idTarjeta,
								  @RequestParam(name="numero") String numero,
								  @RequestParam(name="expiracion") String expiracion,
								  @RequestParam(name="tipo") String tipo,
								  @RequestParam(name="clave") String clave) {	
		PagoTarjeta pagoTarjeta = new PagoTarjeta(idTarjeta, numero, expiracion, tipo, clave);
		this.servicePagoTarjeta.crearPagoTarjeta(pagoTarjeta);
		return pagoTarjeta;	
	}
	
	@RequestMapping(value = "/pagoTarjeta/listarPagoTarjeta",method=RequestMethod.GET)
	public List<PagoTarjeta> listarPagoTarjeta(){
		return this.servicePagoTarjeta.obtenerPagoTarjeta();
	}	
	
	//====================================================================
	//  pago
	//====================================================================
	@RequestMapping(value = "/pago/crearPago",method=RequestMethod.GET)
	public Pago crearPago(@RequestParam(name="idPago") int idPago,
								  @RequestParam(name="valorPedido") double valorPedido,
								  @RequestParam(name="valorEnvio") double valorEnvio,
								  @RequestParam(name="idEfectivo") int idEfectivo,
								  @RequestParam(name="idTarjeta") int idTarjeta) {
		PagoEfectivo pagoEfectivo = this.servicePagoEfectivo.buscarPagoEfectivo(idEfectivo);
		PagoTarjeta pagoTarjeta = this.servicePagoTarjeta.buscarpagoTarjeta(idTarjeta);
		Pago pago = new Pago(idPago, valorPedido, valorEnvio, pagoEfectivo, pagoTarjeta);
		this.servicePago.crearPago(pago);
		return pago;
	}
	
	@RequestMapping(value = "/pago/listarPagos",method=RequestMethod.GET)
	public List<Pago> listarPago(){
		return this.servicePago.obtenerTodosPagos();
	}
	
}
