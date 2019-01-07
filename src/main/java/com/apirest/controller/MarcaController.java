package com.apirest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.model.Marca;
import com.apirest.repository.MarcaRepository;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/marca")
@Api("REST Marcas")
public class MarcaController extends GenericController<Marca, MarcaRepository> {
}