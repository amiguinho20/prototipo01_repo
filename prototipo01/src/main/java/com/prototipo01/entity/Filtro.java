package com.prototipo01.entity;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Filtro implements Serializable{
	
	private String pesquisa;
	private String todos;
	private String expressao;
	private String ou;
	private String nao;
	public String getPesquisa() {
		return pesquisa;
	}
	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}
	public String getTodos() {
		return todos;
	}
	public void setTodos(String todos) {
		this.todos = todos;
	}
	public String getExpressao() {
		return expressao;
	}
	public void setExpressao(String expressao) {
		this.expressao = expressao;
	}
	public String getOu() {
		return ou;
	}
	public void setOu(String ou) {
		this.ou = ou;
	}
	public String getNao() {
		return nao;
	}
	public void setNao(String nao) {
		this.nao = nao;
	}
	
	

}
