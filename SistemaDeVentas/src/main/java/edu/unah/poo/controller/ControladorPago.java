package edu.unah.poo.controller;


import edu.unah.poo.model.Pago;

import edu.unah.poo.service.ServicePago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Pago")
public class ControladorPago {

    @Autowired
    ServicePago servicePago;

    @RequestMapping(value = "/crearPago1",method= RequestMethod.GET)
    public Pago crearPago1(@RequestParam(name="idPago",required = false) int idPago,
                           @RequestParam(name="metodoPago") int metodoPago,
                           @RequestParam(name="montoPagado") double montoPagado,
                           @RequestParam(name="numeroT", required = false) String numeroT,
                           @RequestParam(name="expiracionT",required = false) String expiracionT,
                           @RequestParam(name="tipoT",required = false) String tipoT,
                           @RequestParam(name="claveT",required = false) String claveT) {



        return null;
    }

}
