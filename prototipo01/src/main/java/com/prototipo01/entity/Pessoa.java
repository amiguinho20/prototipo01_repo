package com.prototipo01.entity;

import java.io.Serializable;


public class Pessoa implements Serializable{
	
	private static final long serialVersionUID = -6240504738885913212L;
	private String nome;
	private String cpf;
	private String nomeDaMae;
	private Ocorrencia ocorrencia = new Ocorrencia();

	public Pessoa(){}
	
	public Pessoa(String nome, String cpf, String nomeDaMae, Ocorrencia ocorrencia) {
		this.nome = nome;
		this.cpf = cpf;
		this.nomeDaMae = nomeDaMae;
		this.ocorrencia = ocorrencia;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNomeDaMae() {
		return nomeDaMae;
	}
	public void setNomeDaMae(String nomeDaMae) {
		this.nomeDaMae = nomeDaMae;
	}
	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}
	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", cpf=" + cpf + ", nomeDaMae="
				+ nomeDaMae + ", ocorrencia=" + ocorrencia + "]";
	}
			
}
