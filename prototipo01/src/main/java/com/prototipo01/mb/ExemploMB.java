package com.prototipo01.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;

import com.prototipo01.dao.RdoDAO;
import com.prototipo01.entity.Filtro;
import com.prototipo01.entity.MockPessoa;
import com.prototipo01.entity.Pessoa;
import com.prototipo01.util.Conversor;

//@Named - @ManagedBean
//@RequestScope

@Model
public class ExemploMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -928021531814583237L;

	private Integer contagem;
	private String pesquisa;
	
	private String dipolLatLong = "-23.538419906917593, -46.63483794999996";
	 
	@Inject
	private Conversor conversor;
	
	@Inject
	private Filtro filtro;
	
	@Inject
	private RdoDAO rdoDAO;
	
	private List<Pessoa> pessoasResultado;
	private List<Pessoa> pessoasSelecionadas;
	
	private MapModel geoModel;
	
	@PostConstruct
	private void init() {
		geoModel = new DefaultMapModel();
		int count = rdoDAO.contarColecao();
		setContagem(count);
		
	}
		
	public void converter(){
		conversor.converter(filtro);
	}
	
	public void pesquisar(){
		//rdoDAO.pesquisar(getPesquisa());
		
		pessoasResultado = MockPessoa.mockPessoas();
		
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

	public List<Pessoa> getPessoasResultado() {
		return pessoasResultado;
	}

	public void setPessoasResultado(List<Pessoa> pessoasResultado) {
		this.pessoasResultado = pessoasResultado;
	}

	public List<Pessoa> getPessoasSelecionadas() {
		return pessoasSelecionadas;
	}

	public void setPessoasSelecionadas(List<Pessoa> pessoasSelecionadas) {
		this.pessoasSelecionadas = pessoasSelecionadas;
	}
	
}
