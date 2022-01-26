package com.joel.sprint.web.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.joel.sprint.web.app.models.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	 	 
	}