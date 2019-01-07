package com.apirest.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.annotations.ApiOperation;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PutMapping;

public abstract class GenericController<T, R extends JpaRepository<T, Long>> {

	@Autowired
	R repository;

	@ApiOperation(value = "Returns a list of all storaged records.")
	@GetMapping("/findAll")
	public List<T> findAll() {
		return repository.findAll();
	}

	@ApiOperation(value = "Returns a record by passing the id in URL.")
	@GetMapping("/findById/{id}")
	public Optional<T> findById(@PathVariable(value = "id") long id) {
		return repository.findById(id);
	}

	@ApiOperation(value = "Inserts a record by passing the record itself in the body but without id. Returns the record itself with id.")
	@PostMapping("/insert")
	public T insert(@RequestBody @Valid T t) {
		return repository.save(t);
	}

	@ApiOperation(value = "Deletes a record by passing the id in URL. If delete on database has succeeded returns a boolean with true value, else returns the error.")
	@GetMapping("/delete/{id}")
	public boolean delete(@PathVariable(value = "id") long id) {
		// repository.delete(t);
		repository.deleteById(id);
		return true;
	}

	@ApiOperation(value = "Updates a register by passing the record itself in the body. Returns the same register after validation and database update.")
	@PostMapping("/update")
	public T update(@RequestBody @Valid T t) {
		return repository.save(t);
	}

}
