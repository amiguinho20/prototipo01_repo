package com.prototipo01.entity;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
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
	
	public void avancadoParaSimples(){
		pesquisa = "";
		pesquisa = concatenar(pesquisa, todos);
		pesquisa = concatenar(pesquisa, expressao);
		pesquisa = concatenar(pesquisa, ou);
		pesquisa = concatenar(pesquisa, nao);
		todos = "";
		expressao = "";
		ou = "";
		nao = "";
	}
	
	/**
	 * Concatena arg2 em arg1.
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	private String concatenar(String arg1, String arg2)
	{
		if (arg2 != null && !arg2.trim().isEmpty())
		{
			arg1 += arg2.trim() + " ";
		}
		return arg1;
	}
	
	public void simplesParaAvancada(){
		
	}

	@Override
	public String toString() {
		return "Filtro [pesquisa=" + pesquisa + ", todos=" + todos
				+ ", expressao=" + expressao + ", ou=" + ou + ", nao=" + nao
				+ "]";
	}

	
	
}
