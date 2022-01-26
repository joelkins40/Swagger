package com.joel.sprint.web.app.controllers;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joel.sprint.web.app.models.entity.Producto;

@FeignClient("ventas")
public interface VentaController {

	
	@RequestMapping(method = RequestMethod.GET, value = "/productos")
	List<Producto> getProductos(); 
	

}
