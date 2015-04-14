package com.prototipo01.batch.eventual;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.prototipo01.entity.LocalDoFato;
import com.prototipo01.entity.Ocorrencia;
import com.prototipo01.entity.Pessoa;

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
	public void abrirConexao()
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
	public void fecharConecao()
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
	    
	    MongoCursor<Document> cursor = colecao.find(text).limit(10).iterator();
	    
	    try {
	    	int qtd = 0;
	        while (cursor.hasNext()) {
	        	Document documento = cursor.next();
	        	
	        	System.out.println("-------------");
	        	
	        	Document bo = (Document) documento.get("BO");
	        	
	        	LocalDoFato localDoFato = new LocalDoFato();
	        	localDoFato.setLogradouro(bo.getString("LOGRADOURO"));
	        	localDoFato.setNumero(bo.getString("NUMERO_LOGRADOURO"));
	        	localDoFato.setBairro(bo.getString("BAIRRO"));
	        	localDoFato.setCidade(bo.getString("CIDADE"));
	        	localDoFato.setUf(bo.getString("ID_UF"));
	        	localDoFato.setUfDescricao(bo.getString("NOME_UF"));
	        	
	        	Ocorrencia ocorrencia = new Ocorrencia();
	        	ocorrencia.setNumero(bo.getString("NUM_BO"));
	        	ocorrencia.setAno(bo.getString("ANO_BO"));
	        	ocorrencia.setDelegacia(bo.getString("NOME_DELEGACIA"));
	        	ocorrencia.setIdDelegacia(bo.getString("ID_DELEGACIA"));
	        	ocorrencia.setLocalDoFato(localDoFato);
	     
	        	List<Pessoa> pessoas = new ArrayList<>();
	        	
	        	Document pessoasObj = (Document) bo.get("PESSOAS");
	        	
	        	if (pessoasObj.get("PESSOA") instanceof ArrayList)
	        	{
	        		List pessoaObjList = (ArrayList) pessoasObj.get("PESSOA");
	        		pessoas = montarPessoas(pessoaObjList, ocorrencia);
	        	}
	        	else
	        	{
	        		Document pessoaObj = (Document) pessoasObj.get("PESSOA");
	        		pessoas = montarPessoas(pessoaObj, ocorrencia);
	        	}
	        	
	        	
	        	System.out.println("size: " + pessoas.size());
	        	System.out.println("pessoas: " + pessoas);
	        	

	        	
	            qtd++;
	        }
	        System.out.println("qtd[" + qtd + "] para a pesquisa [" + pesquisa + "].");
	    } 
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	    } finally {
	        cursor.close();
	    }
	}
	
	public List<Pessoa> montarPessoas(List pessoasObj, Ocorrencia ocorrencia){
		List<Pessoa> pessoas = new ArrayList<>();
		for (Object obj: pessoasObj)
    	{
    		Document pessoaObj = (Document) obj;
    		pessoas.addAll(montarPessoas(pessoaObj, ocorrencia));
    	}
		return pessoas;
	}
	
	public List<Pessoa> montarPessoas(Document pessoaObj, Ocorrencia ocorrencia){
		List<Pessoa> pessoas = new ArrayList<>();
   		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoaObj.getString("CPF"));
		pessoa.setNome(pessoaObj.getString("NOME_PESSOA"));
		pessoa.setNomeDaMae(pessoaObj.getString("NOMEMAE_PESSOA"));
		pessoa.setOcorrencia(ocorrencia);
		pessoas.add(pessoa);
    	return pessoas;
	}
	

	
}
