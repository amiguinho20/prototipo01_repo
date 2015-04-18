package com.prototipo01.entity;

import java.io.Serializable;

public class Ocorrencia implements Serializable{

	private static final long serialVersionUID = 5356328149344847324L;
	
	private String numero;
	private String ano;
	private String delegacia;
	private String idDelegacia;
	private LocalDoFato localDoFato = new LocalDoFato();
	
	public Ocorrencia() {}
	
	public Ocorrencia(String numero, String ano, String delegacia, String idDelegacia, LocalDoFato localDoFato) {
		this.numero = numero;
		this.ano = ano;
		this.delegacia = delegacia;
		this.idDelegacia = idDelegacia;
		this.localDoFato = localDoFato;
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getDelegacia() {
		return delegacia;
	}
	public void setDelegacia(String delegacia) {
		this.delegacia = delegacia;
	}
	public String getIdDelegacia() {
		return idDelegacia;
	}
	public void setIdDelegacia(String idDelegacia) {
		this.idDelegacia = idDelegacia;
	}
	public LocalDoFato getLocalDoFato() {
		return localDoFato;
	}
	public void setLocalDoFato(LocalDoFato localDoFato) {
		this.localDoFato = localDoFato;
	}
	
	public String getFormatada()
	{
		return getNumero() + "/" + getAno() + "/" + getDelegacia();
	}

	@Override
	public String toString() {
		return "Ocorrencia [numero=" + numero + ", ano=" + ano + ", delegacia="
				+ delegacia + ", idDelegacia=" + idDelegacia + ", localDoFato="
				+ localDoFato + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result
				+ ((idDelegacia == null) ? 0 : idDelegacia.hashCode());
		result = prime * result
				+ ((localDoFato == null) ? 0 : localDoFato.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Ocorrencia other = (Ocorrencia) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (idDelegacia == null) {
			if (other.idDelegacia != null)
				return false;
		} else if (!idDelegacia.equals(other.idDelegacia))
			return false;
		if (localDoFato == null) {
			if (other.localDoFato != null)
				return false;
		} else if (!localDoFato.equals(other.localDoFato))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}
	
	
	
}
