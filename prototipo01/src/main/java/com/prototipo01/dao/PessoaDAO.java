package com.prototipo01.dao;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.prototipo01.converter.Converter;
import com.prototipo01.entity.Ocorrencia;
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
	 * Consulta pelo id (identificador unico), o "_id"
	 * @param id
	 */
	public Pessoa consultar(final String id)
	{
	    Document documento = colecao.find(eq("_id", new ObjectId(id))).first();
	    Pessoa pessoa = converter.paraObjeto(documento);
	    return pessoa;
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
	    
	    //ExplainVerbosity ex;
	    //FindModel fm;
	    
	    //banco.
	    
	    
	    //colecao.explain(new FindModel(new FindOptions().criteria(new Document("x", 1))),
        //        ExplainVerbosity.ALL_PLANS_EXECUTIONS);
	    
	    //colecao.find().
	    
	    BasicDBObject doc = new BasicDBObject("$explain", text);
	    doc.append("verbosity", "allPlansExecution");
	    
	    FindIterable<Document> fi = colecao.find(doc);
	    Document primeiro = fi.first();
	    
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
	        	
	        	//-- associa ocorrencias de envolvimento
	        	List<Ocorrencia> envolvimentos = pesquisarPorNome(pessoa);
	        	pessoa.setOcorrenciasDeEnvolvimento(envolvimentos);
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
	
	/**
	 * Pesquisa por nome e EXCLUI o registro utilizado no filtro da lista de resultado.
	 * O resultado sao as demais ocorrencias que esta pessoa esta envolvida.
	 * @param pessoaArg
	 * @return
	 */
	public List<Ocorrencia> pesquisarPorNome(Pessoa pessoaArg)
	{
		List<Pessoa> pessoas = new ArrayList<>();
		List<Ocorrencia> ocorrencias = new ArrayList<>();
		
		BasicDBObject pesquisa = new BasicDBObject("NOME_PESSOA", pessoaArg.getNome());
	    
	    MongoCursor<Document> cursor = colecao.find(pesquisa).iterator();
	    
	    try {
	        while (cursor.hasNext()) {
	        	Document documento = cursor.next();
	        	Pessoa pessoa = converter.paraObjeto(documento);
	        	pessoas.add(pessoa);
	        }
	        pessoas.remove(pessoaArg);
	    } finally {
	        cursor.close();
	    }
	    
	    for (Pessoa pessoa : pessoas)
	    {
	    	ocorrencias.add(pessoa.getOcorrencia());
	    }
	    
	    return ocorrencias;	
	}

	
}
