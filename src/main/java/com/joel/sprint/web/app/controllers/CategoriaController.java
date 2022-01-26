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

import com.joel.sprint.web.app.models.entity.Categoria;
import com.joel.sprint.web.app.services.CategoriaService;
@RestController
@RequestMapping("/")
public class CategoriaController {

	@Autowired
	  CategoriaService categoriaService;

	  @GetMapping("/categorias")
	  public ResponseEntity<List<Categoria>> getAllCategorias(@RequestParam(required = false) String title) {
		  try {
		  List<Categoria> categorias = categoriaService.consultarCategorias();
		 
		  if (categorias.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(categorias, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	  
	  
	  @GetMapping("/categorias/{id}")
	  public ResponseEntity<Categoria> getCategoriaById(@PathVariable("id") long id) {
	  
		  Optional<Categoria> categoriaData = categoriaService.consultarCategoriaById(id);
	    if (categoriaData.isPresent()) {
	      return new ResponseEntity<>(categoriaData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  @PostMapping("/categorias")
	  public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
	    try {
	      categoriaService.agregarCategoria(categoria);
	    	return new ResponseEntity<>(categoria, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  @PutMapping("/categorias/{id}")
	  public ResponseEntity<Categoria> updateCategoria(@PathVariable("id") long id, @RequestBody Categoria categoria) {
	   
	    Categoria categoriaData  = categoriaService.actualizarCategoria(categoria);
		  if (categoriaData!=null) {
	      	      return new ResponseEntity<>(categoriaData, HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  @DeleteMapping("/categorias/{id}")
	  public ResponseEntity<HttpStatus> deleteCategoria(@PathVariable("id") long id) {
	  
	     if(categoriaService.eliminarCategoria(id)!=null) {
	    	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    		    	 
	     
	     
	    }else {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	}
