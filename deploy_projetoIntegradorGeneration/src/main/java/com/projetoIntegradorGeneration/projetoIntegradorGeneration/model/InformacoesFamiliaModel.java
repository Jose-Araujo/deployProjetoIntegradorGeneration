package com.projetoIntegradorGeneration.projetoIntegradorGeneration.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_informacoes_familia")
public class InformacoesFamiliaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Min(1)
	private double rendaFamiliar;

	@Min(1)
	private int quantidadeMembrosFamilia;

	private boolean cadastroProgramasSociais;

	private boolean pessoasComDeficiencia;

	@NotNull
	private String descricaoSituacao;
	
	@OneToMany(mappedBy = "familia", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("familia")
	private List<PostagemModel> postagem;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getRendaFamiliar() {
		return rendaFamiliar;
	}

	public void setRendaFamiliar(double rendaFamiliar) {
		this.rendaFamiliar = rendaFamiliar;
	}

	public int getQuantidadeMembrosFamilia() {
		return quantidadeMembrosFamilia;
	}

	public void setQuantidadeMembrosFamilia(int quantidadeMembrosFamilia) {
		this.quantidadeMembrosFamilia = quantidadeMembrosFamilia;
	}

	public boolean isCadastroProgramasSociais() {
		return cadastroProgramasSociais;
	}

	public void setCadastroProgramasSociais(boolean cadastroProgramasSociais) {
		this.cadastroProgramasSociais = cadastroProgramasSociais;
	}

	public boolean isPessoasComDeficiencia() {
		return pessoasComDeficiencia;
	}

	public void setPessoasComDeficiencia(boolean pessoasComDeficiencia) {
		this.pessoasComDeficiencia = pessoasComDeficiencia;
	}

	public String getDescricao() {
		return descricaoSituacao;
	}

	public void setDescricao(String descricao) {
		this.descricaoSituacao = descricao;
	}

	public String getDescricaoSituacao() {
		return descricaoSituacao;
	}

	public void setDescricaoSituacao(String descricaoSituacao) {
		this.descricaoSituacao = descricaoSituacao;
	}

	public List<PostagemModel> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<PostagemModel> postagem) {
		this.postagem = postagem;
	}
}
