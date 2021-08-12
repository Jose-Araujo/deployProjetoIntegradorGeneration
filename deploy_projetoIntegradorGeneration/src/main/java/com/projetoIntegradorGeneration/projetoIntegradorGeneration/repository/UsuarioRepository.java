package com.projetoIntegradorGeneration.projetoIntegradorGeneration.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoIntegradorGeneration.projetoIntegradorGeneration.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{
	
	public List<UsuarioModel> findAllByNomeCompletoContainingIgnoreCase (String nomeCompleto);

	public Optional<UsuarioModel> findByUsuario(String usuario);
	
	public UsuarioModel findByNomeCompleto(String nomeCompleto);
}
