package com.prototipo01.mb;

import java.io.Serializable;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.prototipo01.entity.Filtro;
import com.prototipo01.util.Conversor;

//@Named - @ManagedBean
//@RequestScope

@Model
public class ExemploMB implements Serializable{

	@Inject
	private Conversor conversor;
	
	@Inject
	private Filtro filtro;
	
	
	private String olaMundo = "ola mundo";
	

	public void converter(){
		conversor.converter(filtro);
	}

	public String getOlaMundo() {
		return olaMundo;
	}

	public void setOlaMundo(String olaMundo) {
		this.olaMundo = olaMundo;
	}

	public Filtro getFiltro() {
		return filtro;
	}

	public void setFiltro(Filtro filtro) {
		this.filtro = filtro;
	}
	
}
