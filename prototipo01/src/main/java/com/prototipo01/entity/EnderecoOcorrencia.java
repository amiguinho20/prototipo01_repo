package com.prototipo01.entity;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class EnderecoOcorrencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4847237569217463768L;
	
	private String logradouro;
	private String numeroLogradouro;
	private String uf;
	private String nomeUF;
	private String cidade;
	private String bairro;
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumeroLogradouro() {
		return numeroLogradouro;
	}
	public void setNumeroLogradouro(String numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getNomeUF() {
		return nomeUF;
	}
	public void setNomeUF(String nomeUF) {
		this.nomeUF = nomeUF;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getEnderecoCompleto() {
		return logradouro + ", " + numeroLogradouro + ", "
				+ bairro + ", " + cidade + ", " + nomeUF;
	}

	
}
