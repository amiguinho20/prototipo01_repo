package com.prototipo01.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.prototipo01.converter.Converter;
import com.prototipo01.entity.Pessoa;

@Named
@ApplicationScoped
public class PessoaDAO {

	private static final String USUARIO = "proto";
	private static final String SENHA = "proto12";
	private static final String HOST = "ds061651.mongolab.com";
	private static final String PORTA = "61651";
	private static final String BANCO = "amiguinho";
	private static final String COLECAO = "pessoa"; 
	
	@Inject
	private Converter<Pessoa> converter;
	
	private MongoClient conexao;
	private MongoDatabase banco;
	private MongoCollection<Document> colecao;
	
	/**
	 * Abrir a conexao com o banco quando o objeto for criado.
	 */
	@PostConstruct
	private void abrirConexao()
	{
		String uriConexao = String.format("mongodb://%s:%s@%s:%s/%s", USUARIO, SENHA, HOST, PORTA, BANCO);
		MongoClientURI uri  = new MongoClientURI(uriConexao); 
	    conexao = new MongoClient(uri);
	    banco = conexao.getDatabase(uri.getDatabase());
	    colecao = banco.getCollection(COLECAO);
	}
	
	/**
	 * Fechar a conexao com o banco quando o objeto for destruido.
	 */
	@PreDestroy
	private void fecharConecao()
	{
		conexao.close();
	}
	
	/**
	 * Pesquisa textual/full text (apenas no NOME_PESSOA).
	 * @param pesquisa
	 */
	public List<Pessoa> pesquisar(final String pesquisa)
	{
		List<Pessoa> pessoas = new ArrayList<>();
		
		BasicDBObject search = new BasicDBObject("$search", pesquisa);
	    BasicDBObject text = new BasicDBObject("$text", search); 
	    
	    MongoCursor<Document> cursor = colecao.find(text).iterator();
	    
	    try {
	        while (cursor.hasNext()) {
	        	Document documento = cursor.next();
	        	Pessoa pessoa = converter.paraObjeto(documento);
	        	pessoas.add(pessoa);
	        }
	    } finally {
	        cursor.close();
	    }
	    return pessoas;
	}
	
	/**
	 * Pesquisa textual/full text (apenas no NOME_PESSOA) com <b>PAGINACAO</b>
	 * @param pesquisa
	 * @param primeiroRegistro
	 * @param registrosPorPagina
	 * @return List<Pessoa> paginado
	 */
	public List<Pessoa> pesquisarLazy(final String pesquisa, final int primeiroRegistro, final int registrosPorPagina)
	{
		List<Pessoa> pessoas = new ArrayList<>();
		
		BasicDBObject search = new BasicDBObject("$search", pesquisa);
	    BasicDBObject text = new BasicDBObject("$text", search); 
	    
	    MongoCursor<Document> cursor = colecao.find(text).skip(primeiroRegistro).limit(registrosPorPagina).iterator();
	    
	    try {
	        while (cursor.hasNext()) {
	        	Document documento = cursor.next();
	        	Pessoa pessoa = converter.paraObjeto(documento);
	        	pessoas.add(pessoa);
	        }
	    } finally {
	        cursor.close();
	    }
	    return pessoas;
	}
	
	/**
	 * Contagem de pesquisa textual/full text (apenas no NOME_PESSOA)
	 * @param pesquisa
	 * @return count
	 */
	public int contar(final String pesquisa)
	{
		BasicDBObject search = new BasicDBObject("$search", pesquisa);
	    BasicDBObject text = new BasicDBObject("$text", search); 
	
	    long countL = colecao.count(text);
	    int countI = (int) countL;
	    
	    return countI;
	}
	
	
}
