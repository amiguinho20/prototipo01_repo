package com.prototipo01.entity;

import java.io.Serializable;

public class LocalDoFato implements Serializable{
	
	private static final long serialVersionUID = 7828721027064829582L;

	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;
	private String uf;
	private String ufDescricao;
	
	public LocalDoFato() {}
	
	public LocalDoFato(String logradouro, String numero, String bairro,
			String cidade, String uf, String ufDescricao) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.ufDescricao = ufDescricao;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getUfDescricao() {
		return ufDescricao;
	}
	public void setUfDescricao(String ufDescricao) {
		this.ufDescricao = ufDescricao;
	}
	
	public String getEnderecoCompleto() {
		return concatenarCamposValidos(logradouro, numero, bairro, cidade, uf);
	}
	
	public String concatenarCamposValidos(String... campos) {
		String resultado = "";
		for (String campo : campos) {
			if(campo != null && !campo.trim().isEmpty()) {
				if (!resultado.isEmpty())
				{
					resultado += ", ";
				} 
				resultado += campo; 					
			}
		}
		return resultado;
	}

	@Override
	public String toString() {
		return "LocalDoFato [logradouro=" + logradouro + ", numero=" + numero
				+ ", bairro=" + bairro + ", cidade=" + cidade + ", uf=" + uf
				+ ", ufDescricao=" + ufDescricao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result
				+ ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
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
		LocalDoFato other = (LocalDoFato) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
	}
	
	
	
	
}
