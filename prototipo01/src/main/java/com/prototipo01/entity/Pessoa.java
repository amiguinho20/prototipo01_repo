package com.prototipo01.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Pessoa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6240504738885913212L;
	private String nome;
	private EnderecoOcorrencia localOcorrencia;
	private String numeroBO;
	private String anoBO;
	private String idDelegaciaBO;
	private String cpf;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public EnderecoOcorrencia getLocalOcorrencia() {
		return localOcorrencia;
	}
	public void setLocalOcorrencia(EnderecoOcorrencia localOcorrencia) {
		this.localOcorrencia = localOcorrencia;
	}
	public String getNumeroBO() {
		return numeroBO;
	}
	public void setNumeroBO(String numeroBO) {
		this.numeroBO = numeroBO;
	}
	public String getAnoBO() {
		return anoBO;
	}
	public void setAnoBO(String anoBO) {
		this.anoBO = anoBO;
	}
	public String getIdDelegaciaBO() {
		return idDelegaciaBO;
	}
	public void setIdDelegaciaBO(String idDelegaciaBO) {
		this.idDelegaciaBO = idDelegaciaBO;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getIdBO() {
		return numeroBO + "/" + anoBO + "/" + idDelegaciaBO;
	}
		
}
