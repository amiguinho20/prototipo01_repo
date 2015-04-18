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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((nomeDaMae == null) ? 0 : nomeDaMae.hashCode());
		result = prime * result
				+ ((ocorrencia == null) ? 0 : ocorrencia.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nomeDaMae == null) {
			if (other.nomeDaMae != null)
				return false;
		} else if (!nomeDaMae.equals(other.nomeDaMae))
			return false;
		if (ocorrencia == null) {
			if (other.ocorrencia != null)
				return false;
		} else if (!ocorrencia.equals(other.ocorrencia))
			return false;
		return true;
	}
		
	
	
}
