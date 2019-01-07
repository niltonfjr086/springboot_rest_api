package com.apirest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.model.Produto;
import com.apirest.repository.ProdutoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/produto")
@Api("REST Produtos")
public class ProdutoController extends GenericController<Produto, ProdutoRepository> {
	
	@ApiOperation(value = "Return a NULL")
	@GetMapping("/findNothing")
	public Object findNothing() {
		return null;
	}
	
}