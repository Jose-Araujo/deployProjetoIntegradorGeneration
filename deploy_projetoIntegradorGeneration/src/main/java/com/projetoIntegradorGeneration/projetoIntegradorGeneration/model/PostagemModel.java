package com.projetoIntegradorGeneration.projetoIntegradorGeneration.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_postagem")
public class PostagemModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	
	@NotBlank
	@Size(max = 50)
	private String titulo; 
	
	
	@NotBlank
	@Size(max = 500)
	private String conteudo;
	
    @Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());

    @ManyToOne
    @JsonIgnoreProperties("postagem")
    private InformacoesFamiliaModel familia;
    
    @ManyToOne
    @JsonIgnoreProperties("postagem")
    private UsuarioModel usuario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public InformacoesFamiliaModel getFamilia() {
		return familia;
	}

	public void setFamilia(InformacoesFamiliaModel familia) {
		this.familia = familia;
	}

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

}
