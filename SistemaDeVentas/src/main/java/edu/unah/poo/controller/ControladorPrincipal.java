package edu.unah.poo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorPrincipal {

	//====================================================================
	//  pagina principal
	//====================================================================
	@GetMapping("/sistema_de_ventas")
	public String paginaPrincipal() {
		return "pagina_principal";
	}
	
}
