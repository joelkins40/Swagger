package com.joel.sprint.web.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joel.sprint.web.app.models.entity.Producto;
import com.joel.sprint.web.app.services.ProductoService;
@RestController
@RequestMapping("/")
public class ProductoController {

	@Autowired
	  ProductoService productoService;

	  @GetMapping("/productos")
	  public ResponseEntity<List<Producto>> getAllproductos(@RequestParam(required = false) String title) {
		  try {
		  List<Producto> productos = productoService.consultarproductos();
		
		  if (productos.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(productos, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	  
	  
	  @GetMapping("/productos/{id}")
	  public ResponseEntity<Producto> getProductoById(@PathVariable("id") long id) {
	  
		  Optional<Producto> productoData = productoService.consultarproductoById(id);
	    if (productoData.isPresent()) {
	      return new ResponseEntity<>(productoData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  @PostMapping("/productos")
	  public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
	    try {
	      productoService.agregarproducto(producto);
	    	return new ResponseEntity<>(producto, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  @PutMapping("/productos/{id}")
	  public ResponseEntity<Producto> updateProducto(@PathVariable("id") long id, @RequestBody Producto producto) {
	   
	    Producto productoData  = productoService.actualizarproducto(producto);
		  if (productoData!=null) {
	      	      return new ResponseEntity<>(productoData, HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  @DeleteMapping("/productos/{id}")
	  public ResponseEntity<HttpStatus> deleteProducto(@PathVariable("id") long id) {
	  
	     if(productoService.eliminarproducto(id)!=null) {
	    	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    		    	 
	     
	     
	    }else {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	}
