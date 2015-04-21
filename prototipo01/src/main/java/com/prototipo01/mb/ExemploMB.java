package com.prototipo01.mb;

import static com.prototipo01.util.Verificador.isValorado;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.prototipo01.dao.PessoaDAO;
import com.prototipo01.dao.RdoDAO;
import com.prototipo01.entity.Filtro;
import com.prototipo01.entity.Pessoa;
import com.prototipo01.util.Conversor;
import com.prototipo01.util.PessoaLazyDataModel;

@Named
@ViewScoped
public class ExemploMB implements Serializable{

	/**
	 *   
	 */
	private static final long serialVersionUID = -928021531814583237L;

	private Integer contagem;
	
	private String centroMapa = "-23.538419906917593, -46.63483794999996";
	
	@Inject
	private Conversor conversor;
	
	@Inject
	private Filtro filtro;
	
	@Inject
	private RdoDAO rdoDAO;
	
	@Inject PessoaDAO pessoaDAO;
	
	private boolean pesquisaAvancadaAcionada;
	
	private LazyDataModel<Pessoa> pessoasResultadoLazy;
	private List<Pessoa> pessoasResultado;
	private List<Pessoa> pessoasSelecionadas;
	
	private MapModel geoModel;
	
	@PostConstruct
	private void init() {	
		//int count = rdoDAO.contarColecao();
		//setContagem(count);
	}
		
	public void converter(){
		conversor.converter(filtro);
	}
	
	public void pesquisar(){
		
		String pesq = getFiltro().getPesquisa();
		if (isValorado(pesq)) 
		{
			//List<Pessoa> pessoas = pessoaDAO.pesquisar(pesq);
 			//setPessoasResultado(pessoas);
 			 
 			setPessoasResultadoLazy(new PessoaLazyDataModel(pessoaDAO, pesq));
 			setContagem(getPessoasResultadoLazy().getRowCount());
 		}
		 
		//pessoasResultado = MockPessoa.mockPessoas();
		 
	}

	public void visualizarLocalidades() throws IOException {
		geoModel = new DefaultMapModel();  		
		for (Pessoa pessoa : pessoasSelecionadas) {
			LatLng coordenada = conversor.converterEnderecoEmLatLong(pessoa.getOcorrencia().getLocalDoFato().getEnderecoCompleto());
			geoModel.addOverlay(new Marker(coordenada, pessoa.getNome() + " - " + pessoa.getOcorrencia().getLocalDoFato().getEnderecoCompleto() ));
		}
		
	}
	
	public void montarPesquisa()
	{
		getFiltro().avancadoParaSimples();
		desacionarPesquisaAvancada();
	}
	
	public void acionarPesquisaAvancada()
	{
		setPesquisaAvancadaAcionada(true);
	}
	
	public void desacionarPesquisaAvancada()
	{
		setPesquisaAvancadaAcionada(false);
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

	public String getCentroMapa() {
		return centroMapa;
	}

	public void setCentroMapa(String centroMapa) {
		this.centroMapa = centroMapa;
	}

	public MapModel getGeoModel() {
		return geoModel;
	}

	public void setGeoModel(MapModel geoModel) {
		this.geoModel = geoModel;
	}

	public LazyDataModel<Pessoa> getPessoasResultadoLazy() {
		return pessoasResultadoLazy;
	}

	public void setPessoasResultadoLazy(LazyDataModel<Pessoa> pessoasResultadoLazy) {
		this.pessoasResultadoLazy = pessoasResultadoLazy;
	}

	public boolean isPesquisaAvancadaAcionada() {
		return pesquisaAvancadaAcionada;
	}

	public void setPesquisaAvancadaAcionada(boolean pesquisaAvancadaAcionada) {
		this.pesquisaAvancadaAcionada = pesquisaAvancadaAcionada;
	}
	
}
