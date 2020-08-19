package edu.unah.poo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorPrincipal {

	//====================================================================
	//  pagina principal
	//====================================================================
	@GetMapping("/")
	public String paginaPrincipal() {
		return "pagina_principal";
	}
	
	@GetMapping("/k")
	public String volverPrincipal() {
		return "pagina_principal";
	}
	
}
