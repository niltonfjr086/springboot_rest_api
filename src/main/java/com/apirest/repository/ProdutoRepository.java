package com.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}