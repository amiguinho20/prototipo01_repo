package com.prototipo01.dao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.QueryBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

@Named
@ApplicationScoped
public class RdoDAO {

	private static final String USUARIO = "proto";
	private static final String SENHA = "proto12";
	private static final String HOST = "ds035740.mongolab.com";
	private static final String PORTA = "35740";
	private static final String BANCO = "prototipo";
	private static final String COLECAO = "rdo"; 
	
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
	 * Metodo de teste para contagem de registros na colecao.
	 * @return int
	 */
	public int contarColecao()
	{
		Long countL = colecao.count();
		int countI = countL.intValue();
		return countI;
	}
	
	/**
	 * Pesquisa textual/full text (apenas no NOME_PESSOA).
	 * @param pesquisa
	 */
	public void pesquisar(final String pesquisa)
	{
	    BasicDBObject search = new BasicDBObject("$search", pesquisa);
	    BasicDBObject text = new BasicDBObject("$text", search);
	    
	    MongoCursor<Document> cursor = colecao.find(text).iterator();
	    try {
	    	int qtd = 0;
	        while (cursor.hasNext()) {
	        	Document documento = cursor.next();
	            System.out.println(documento.toJson());
	            qtd++;
	        }
	        System.out.println("qtd[" + qtd + "] para a pesquisa [" + pesquisa + "].");
	    } finally {
	        cursor.close();
	    }
	    
		//final BasicDBObject textSearchCommand = new BasicDBObject();
	    //textSearchCommand.put("text", COLECAO);
	    //textSearchCommand.put("search", pesquisa);
	    //DBObject q = QueryBuilder.start().text(consulta).get();
	    //colecao.find(filter)
	    //colecao.find(textSearchCommand);
	    //banco.runCommand(textSearchCommand);
	    //final CommandResult commandResult = banco.command(textSearchCommand);
	}
}
