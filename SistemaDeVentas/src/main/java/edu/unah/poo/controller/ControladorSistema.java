package edu.unah.poo.controller;

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
import edu.unah.poo.model.Pedido;
import edu.unah.poo.service.ServiceCliente;
import edu.unah.poo.service.ServiceDireccion;

@Controller
public class ControladorSistema {
	
	@Autowired
	ServiceCliente serviceCliente;
	
	@Autowired
	ServiceDireccion serviceDireccion;

	//====================================================================
	//  Plantillas
	//====================================================================
	@RequestMapping(value ="/sistema/principal",method=RequestMethod.GET)
	public String sistemaPrincipal() {
		return "sistema_principal";
	}

	@RequestMapping(value ="/sistema/registrar",method=RequestMethod.GET)
	public String registrarCliente() {
		return "sistema_registrar_cliente";
	}
	
	
	@RequestMapping(value ="/sistema/listarClientes",method=RequestMethod.GET)
	public String listarClientes(Model model) {
		List<Cliente> clientes = this.serviceCliente.obtenerClientes();
		model.addAttribute("clientes", clientes);
		return "sistema_listar_clientes";
	}
	
	@GetMapping("/sistema/clienteDetalles/{id}")
	public String detallesMecanico(@PathVariable("id") int idCliente, Model model) {
		Cliente cliente = this.serviceCliente.buscarCliente(idCliente);
		List<Direccion> direcciones= cliente.getDirecciones();
		List<Pedido> pedidos = cliente.getPedidos();
		model.addAttribute("cliente", cliente);
		model.addAttribute("direcciones", direcciones);
		model.addAttribute("pedidos", pedidos);
		return "sistema_cliente_detalles";
	}
	
	@RequestMapping(value ="/sistema/añadirDireccion",method=RequestMethod.GET)
	public String añadirDireccion() {
		return "sistema_añadir_direccion";
	}
	
	@RequestMapping(value = "/sistema/clienteActualizar",method=RequestMethod.GET)
	public String clienteActualizar(@RequestParam(name="idCliente") int idCliente, Model model) {
		if(serviceCliente.exist(idCliente)) {
			Cliente tmpcliente = this.serviceCliente.buscarCliente(idCliente);
			model.addAttribute("cliente", tmpcliente);
			return "sistema_cliente_actualizar";
		}else {
			return "sistema_error";
		}
	}
	
	@RequestMapping(value = "/sistema/clienteEliminar",method=RequestMethod.GET)
	public String clienteEliminar(@RequestParam(name="idCliente") int idCliente, Model model) {
		if(serviceCliente.exist(idCliente)) {
			Cliente tmpCliente = this.serviceCliente.buscarCliente(idCliente);
			if(tmpCliente.getActivo()!=0) {
				model.addAttribute("cliente", tmpCliente);
				return "sistema_cliente_eliminar";
			}
		}
		return "sistema_error";
	}
	
	@RequestMapping(value ="/sistema/listarDirecciones",method=RequestMethod.GET)
	public String listarDirecciones(Model model) {
		List<Direccion> direcciones = this.serviceDireccion.obtenerDirecciones();
		model.addAttribute("direcciones", direcciones);
		return "sistema_listar_direcciones";
	}
	
	@RequestMapping(value = "/sistema/direccionEliminar",method=RequestMethod.GET)
	public String direccionEliminar(@RequestParam(name="idDireccion") int idDireccion, Model model) {
		if(serviceDireccion.exist(idDireccion)) {
			Direccion tmpDireccion = this.serviceDireccion.buscarDireccion(idDireccion);
			if(tmpDireccion.getActivo()!=0) {
				model.addAttribute("direccion", tmpDireccion);
				return "sistema_direccion_eliminar";	
			}
		}
		return "sistema_error";
	}	
	
	//====================================================================
	//  Cliente
	//====================================================================
	@RequestMapping (value = "/cliente/crearCliente",method=RequestMethod.POST)
	public String crearCliente(@RequestParam(name = "idCliente") int idCliente,
			                    @RequestParam(name = "nombre") String nombre,
			                    @RequestParam(name = "email") String email,
			                    @RequestParam(name = "telefono") String telefono,
			                    @RequestParam(name = "credito") double credito,
			                    @RequestParam(name = "idDireccion") int idDireccion,
			                    @RequestParam(name = "tipo") String tipo,
			                    @RequestParam(name = "direccion") String direccion) {
		
		this.serviceCliente.crearCliente(idCliente, nombre, email, telefono, credito, idDireccion, tipo, direccion);
		return "sistema_realizado_exitosamente";
	}
	
	@RequestMapping (value = "/cliente/actualizarCliente",method=RequestMethod.POST)
	public String actualizarCliente(@RequestParam(name = "idCliente") int idCliente,
									@RequestParam(name = "nombre") String nombre,
									@RequestParam(name = "email") String email,
									@RequestParam(name = "telefono") String telefono,
									@RequestParam(name = "credito") double credito) {
		Cliente cliente = new Cliente(idCliente, nombre, email, telefono, credito, 1);
		this.serviceCliente.crearSoloCliente(cliente);
		return "sistema_realizado_exitosamente";
	}
	
	@RequestMapping (value = "/cliente/eliminarCliente",method=RequestMethod.POST)
	public String eliminarCliente(@RequestParam(name="idCliente") int idCliente) {
		this.serviceCliente.eliminarCliente(idCliente);
		return "sistema_realizado_exitosamente";
	}
	
	//====================================================================
	//  direccion
	//====================================================================
	@RequestMapping (value = "/direccion/crearDireccion",method=RequestMethod.POST)
	public String crearDireccion(@RequestParam(name = "idCliente") int idCliente,
								 @RequestParam(name = "idDireccion") int idDireccion,
								 @RequestParam(name = "tipo") String tipo,
								 @RequestParam(name = "direccion") String direccion) {
		Cliente cliente = this.serviceCliente.buscarCliente(idCliente);
		this.serviceDireccion.crearDireccion(idDireccion, tipo, direccion, cliente);
		return "sistema_realizado_exitosamente";
	}
	
	@RequestMapping (value = "/direccion/eliminarDireccion",method=RequestMethod.POST)
	public String eliminarDireccion(@RequestParam(name="idDireccion") int idDireccion) {
		this.serviceDireccion.eliminarDireccion(idDireccion);
		return "sistema_realizado_exitosamente";
	}
	
}
