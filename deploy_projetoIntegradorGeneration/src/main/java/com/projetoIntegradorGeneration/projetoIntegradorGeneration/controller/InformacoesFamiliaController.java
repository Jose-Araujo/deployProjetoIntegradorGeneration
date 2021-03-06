package com.projetoIntegradorGeneration.projetoIntegradorGeneration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoIntegradorGeneration.projetoIntegradorGeneration.model.InformacoesFamiliaModel;
import com.projetoIntegradorGeneration.projetoIntegradorGeneration.repository.InformacoesFamiliaRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/informacoesfamilia")
public class InformacoesFamiliaController {

	@Autowired
	private InformacoesFamiliaRepository repository;

	@GetMapping
	public ResponseEntity<List<InformacoesFamiliaModel>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<InformacoesFamiliaModel> getById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/renda/{renda}")
	public ResponseEntity<List<InformacoesFamiliaModel>> getByRenda(@PathVariable double rendaFamiliar) {
		return ResponseEntity.ok(repository.findAllByRendaFamiliar(rendaFamiliar));
	}

	@PostMapping
	public ResponseEntity<InformacoesFamiliaModel> post(@RequestBody InformacoesFamiliaModel informacoes) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(informacoes));
	}

	@PutMapping
	public ResponseEntity<InformacoesFamiliaModel> put(@RequestBody InformacoesFamiliaModel informacoes) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(informacoes));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}

}
