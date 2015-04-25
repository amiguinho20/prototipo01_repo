package com.prototipo01.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Pessoa implements Serializable{
	
	private static final long serialVersionUID = -6240504738885913212L;
	private String id; //-- apenas para o rowKey do primefaces
	private String nome;
	private String cpf;
	private String nomeDaMae;
	private Ocorrencia ocorrencia = new Ocorrencia();
	private List<Ocorrencia> ocorrenciasDeEnvolvimento = new ArrayList<>();

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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", cpf=" + cpf
				+ ", nomeDaMae=" + nomeDaMae + ", ocorrencia=" + ocorrencia
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public List<Ocorrencia> getOcorrenciasDeEnvolvimento() {
		return ocorrenciasDeEnvolvimento;
	}

	public void setOcorrenciasDeEnvolvimento(
			List<Ocorrencia> ocorrenciasDeEnvolvimento) {
		this.ocorrenciasDeEnvolvimento = ocorrenciasDeEnvolvimento;
	}


	
}
