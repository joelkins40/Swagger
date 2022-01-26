package com.joel.sprint.web.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joel.sprint.web.app.models.entity.Producto;
import com.joel.sprint.web.app.repository.ProductoRepository;

@Service
public class ProductoService {

	private ProductoRepository productoRepository;
	
	@Autowired
	public ProductoService(ProductoRepository productoRepository) {
this.productoRepository=productoRepository;
	}

	public List<Producto> consultarproductos() {
		return productoRepository.findAll();
	}

	public Optional<Producto> consultarproductoById(long idproducto) {
		Optional<Producto> productoData = productoRepository.findById(idproducto);

		return productoData;
	}

	public void agregarproducto(Producto producto) {
		productoRepository.save(new Producto(producto.getId(),producto.getNombre(),producto.getCategoria()));
	}

	public Producto actualizarproducto(Producto producto) {
		Optional<Producto> productoData = productoRepository.findById(producto.getId());
		if (productoData.isPresent()) {
			Producto _producto = productoData.get();
		      _producto.setNombre(producto.getNombre());
		      _producto.setCategoria(producto.getCategoria());;
			productoRepository.save(producto);
			return _producto;
		} else {
			return null;
		}

	}
	

	public Producto eliminarproducto(Long id) {
		Optional<Producto> productoData = productoRepository.findById(id);
		
		if (productoData.isPresent()) {
			productoRepository.delete(productoData.get());
			return productoData.get();
		} else {
			return null;
		}

	}
}
