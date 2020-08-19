package edu.unah.poo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unah.poo.model.Empleado;
import edu.unah.poo.model.Producto;
import edu.unah.poo.model.Proveedor;
import edu.unah.poo.service.ServiceEmpleado;
import edu.unah.poo.service.ServiceProducto;
import edu.unah.poo.service.ServiceProveedor;

@Controller
public class ControladorAdministracion {
	
	@Autowired
	ServiceEmpleado serviceEmpleado;
	
	@Autowired
	ServiceProveedor serviceProveedor;
	
	@Autowired
	ServiceProducto serviceProducto;

	@Autowired
	PasswordEncoder passwordEncoder;

	//====================================================================
	//  Plantillas
	//====================================================================
	@RequestMapping(value ="/administracion/principal", method=RequestMethod.GET)
	public String administracionPrincipal() {
		return "administracion_principal";
	}
	
	@RequestMapping(value ="/administracion/registrar", method=RequestMethod.GET)
	public String registrar() {
		return "administracion_registrar_empleado";
	}
	
	@RequestMapping(value ="/administracion/empleadosListar", method=RequestMethod.GET)
	public String listarEmpleados(Model model) {
		List<Empleado> empleados = this.serviceEmpleado.obtenerEmpleados();
		model.addAttribute("empleados", empleados);
		return "administracion_listar_empleados";
	}
	
	@RequestMapping(value ="/administracion/empleadosEliminar", method=RequestMethod.GET)
	public String empleadoEliminar(@RequestParam(name="idEmpleado") int idEmpleado, Model model) {
		if(serviceEmpleado.exist(idEmpleado)) {
			Empleado tmpEmpleado = this.serviceEmpleado.buscarEmpleado(idEmpleado);
			if(tmpEmpleado.getActivo()!=0) {
				model.addAttribute("empleado", tmpEmpleado);
				return "administracion_empleado_eliminar";
			}
		}
		return "administracion_error";		
	}	
	
	@RequestMapping(value ="/administracion/registrarProveedor", method=RequestMethod.GET)
	public String registrarProveedor() {
		return "administracion_registrar_proveedor";
	}
	
	@RequestMapping(value ="/administracion/listarProveedores", method=RequestMethod.GET)
	public String listarProveedores(Model model) {
		List<Proveedor> proveedores = this.serviceProveedor.obtenerProveedores();
		model.addAttribute("proveedores", proveedores);
		return "administracion_listar_proveedores";
	}
	
	@RequestMapping(value ="/administracion/productoAñadir", method=RequestMethod.GET)
	public String registrarProducto() {
		return "administracion_registrar_producto";
	}
	
	@RequestMapping(value ="/administracion/inventario", method=RequestMethod.GET)
	public String inventario(Model model) {
		List<Producto> productos = this.serviceProducto.obtenerProductos();
		model.addAttribute("productos", productos);
		return "administracion_inventario";
	}
	
	//====================================================================
	//  empleado
	//====================================================================
	@RequestMapping(value = "/administracion/crearEmpleado", method=RequestMethod.POST)
	public String crearEmpleado(@RequestParam(name="idEmpleado") int idEmpleado,
								@RequestParam(name="nombre") String nombre,
								@RequestParam(name="telefono") String telefono,
								@RequestParam(name="direccion") String direccion,
								@RequestParam(name="usuario") String usuario,
								@RequestParam(name="contrasenia") String contrasenia) {
		Empleado empleado = new Empleado(idEmpleado, nombre, telefono, direccion, passwordEncoder.encode(usuario), contrasenia, "mecanico", 1);
		this.serviceEmpleado.crearEmpleado(empleado);
		return "administracion_realizado_exitosamente";
	}
	
	@RequestMapping (value = "/administracio/eliminarEmpleado",method=RequestMethod.POST)
	public String eliminarEmpleado(@RequestParam(name="idEmpleado") int idEmpleado) {
		this.serviceEmpleado.eliminarEmpleado(idEmpleado);
		return "administracion_realizado_exitosamente";
	}
	
	//====================================================================
	//  proveedor
	//====================================================================
	@RequestMapping(value = "/administracion/crearProveedor", method=RequestMethod.POST)
	public String crearProveedor(@RequestParam(name="idProveedor") int idProveedor,
								@RequestParam(name="nombre") String nombre,
								@RequestParam(name="telefono") String telefono,
								@RequestParam(name="email") String email,
								@RequestParam(name="rtn") String rtn) {
		Proveedor proveedor = new Proveedor(idProveedor, nombre, telefono, email, rtn, 1);
		this.serviceProveedor.crearProveedor(proveedor);
		return "administracion_realizado_exitosamente";
	}
	
	//====================================================================
	//  producto
	//====================================================================
	@RequestMapping(value = "/administracion/crearProducto", method=RequestMethod.POST)
	public String crearProducto(@RequestParam(name="idProducto") int idProducto,
								@RequestParam(name="nombre") String nombre,
								@RequestParam(name="unidad") String unidad,
								@RequestParam(name="precio") double precio,
								@RequestParam(name="descripcion") String descripcion,
								@RequestParam(name="cantidad") int cantidad,
								@RequestParam(name="idProveedor") int idProveedor) {
		Proveedor proveedor = this.serviceProveedor.buscarProveedor(idProveedor);
		Producto producto = new Producto(idProducto, nombre, unidad, precio, descripcion, cantidad, proveedor);
		this.serviceProducto.crearProducto(producto);
		return "administracion_realizado_exitosamente";
	}

	@GetMapping(value = "/encriptar")
	public String encriptarContraseña(){
		List<Empleado> empleados = this.serviceEmpleado.obtenerEmpleados();
		String contrasenia;
		for (Empleado emp:empleados) {
			contrasenia = passwordEncoder.encode(emp.getContrasenia());
			emp.setContraseña(contrasenia);
			this.serviceEmpleado.crearEmpleado(emp);
		}
		return "encriptar";
	}
}
