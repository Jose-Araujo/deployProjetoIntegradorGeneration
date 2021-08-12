package com.projetoIntegradorGeneration.projetoIntegradorGeneration.controller;

import java.util.List;
import java.util.Optional;

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

import com.projetoIntegradorGeneration.projetoIntegradorGeneration.model.UsuarioLogin;
import com.projetoIntegradorGeneration.projetoIntegradorGeneration.model.UsuarioModel;
import com.projetoIntegradorGeneration.projetoIntegradorGeneration.repository.UsuarioRepository;
import com.projetoIntegradorGeneration.projetoIntegradorGeneration.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<UsuarioModel>> getAll() {
		return ResponseEntity.ok(repository.findAll());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioModel> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
		
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List <UsuarioModel>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeCompletoContainingIgnoreCase(nome));
		
	}
	
	@PostMapping
	public ResponseEntity<UsuarioModel> post (@RequestBody UsuarioModel usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
		
	}
	
	@PutMapping
	public ResponseEntity<UsuarioModel> put(@RequestBody UsuarioModel usuario){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuario));	
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UsuarioLogin> Autentication(@RequestBody Optional<UsuarioLogin> user) {
		return usuarioService.loginUsuario(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioModel> cadastrarUsuario(@RequestBody UsuarioModel usuario) {

		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrarUsuario(usuario));
	}

	@PutMapping("/alterar")
	public ResponseEntity<UsuarioModel> updateUsuario(@RequestBody UsuarioModel usuario) {

		Optional<UsuarioModel> updateUsuario = usuarioService.atualizarUsuario(usuario);
		try {
			return ResponseEntity.ok(updateUsuario.get());
		} 
		catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	
}
