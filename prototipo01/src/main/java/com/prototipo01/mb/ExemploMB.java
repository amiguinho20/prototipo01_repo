package com.prototipo01.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.prototipo01.dao.RdoDAO;
import com.prototipo01.entity.Filtro;
import com.prototipo01.util.Conversor;

//@Named - @ManagedBean
//@RequestScope

@Model
public class ExemploMB implements Serializable{

	private Integer contagem;
	private String pesquisa;
	
	@Inject
	private Conversor conversor;
	
	@Inject
	private Filtro filtro;
	
	@Inject
	private RdoDAO rdoDAO;
	
	private String olaMundo = "ola mundo";
	
	@PostConstruct
	private void init()
	{
		int count = rdoDAO.contarColecao();
		setContagem(count);
	}

	public void converter(){
		conversor.converter(filtro);
	}
	
	public void pesquisar(){
		rdoDAO.pesquisar(getPesquisa());
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

	public Integer getContagem() {
		return contagem;
	}

	public void setContagem(Integer contagem) {
		this.contagem = contagem;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	
}
