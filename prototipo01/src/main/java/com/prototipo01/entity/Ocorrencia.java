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

	@Override
	public String toString() {
		return "Ocorrencia [numero=" + numero + ", ano=" + ano + ", delegacia="
				+ delegacia + ", idDelegacia=" + idDelegacia + ", localDoFato="
				+ localDoFato + "]";
	}
	
	
	
}
