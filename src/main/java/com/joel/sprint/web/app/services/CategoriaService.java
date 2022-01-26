package com.joel.sprint.web.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joel.sprint.web.app.models.entity.Categoria;
import com.joel.sprint.web.app.repository.CategoriaRepository;


@Service
public class CategoriaService {

	private CategoriaRepository categoriaRepository;

	@Autowired
	public CategoriaService(CategoriaRepository categoriaRepository) {
this.categoriaRepository=categoriaRepository;
	}

	public List<Categoria> consultarCategorias() {
		return categoriaRepository.findAll();
	}

	public Optional<Categoria> consultarCategoriaById(long idCategoria) {
		Optional<Categoria> categoriaData = categoriaRepository.findById(idCategoria);

		return categoriaData;
	}

	public void agregarCategoria(Categoria categoria) {
		categoriaRepository.save(new Categoria(categoria.getIdCategoria(), categoria.getDescripcion()));
	}

	public Categoria actualizarCategoria(Categoria categoria) {
		Optional<Categoria> categoriaData = categoriaRepository.findById(categoria.getIdCategoria());
		if (categoriaData.isPresent()) {
			Categoria _categoria = categoriaData.get();
			_categoria.setDescripcion(_categoria.getDescripcion());
			categoriaRepository.save(categoria);
			return _categoria;
		} else {
			return null;
		}

	}
	

	public Categoria eliminarCategoria(Long id) {
		Optional<Categoria> categoriaData = categoriaRepository.findById(id);
		
		if (categoriaData.isPresent()) {
			categoriaRepository.delete(categoriaData.get());
			return categoriaData.get();
		} else {
			return null;
		}

	}
}
