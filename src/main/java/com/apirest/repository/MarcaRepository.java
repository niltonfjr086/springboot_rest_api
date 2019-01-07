package com.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}