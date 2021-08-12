package com.projetoIntegradorGeneration.projetoIntegradorGeneration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoIntegradorGeneration.projetoIntegradorGeneration.model.InformacoesFamiliaModel;

@Repository
public interface InformacoesFamiliaRepository extends JpaRepository<InformacoesFamiliaModel, Long> {
	public List<InformacoesFamiliaModel> findAllByRendaFamiliar(double rendaFamiliar);

}
