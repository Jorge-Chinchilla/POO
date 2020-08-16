package edu.unah.poo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.unah.poo.model.Cliente;
import edu.unah.poo.service.ServiceCliente;

@Controller
public class ControladorSistema {
	
	@Autowired
	ServiceCliente serviceCliente;

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
		return "";
	}
	
	
}
