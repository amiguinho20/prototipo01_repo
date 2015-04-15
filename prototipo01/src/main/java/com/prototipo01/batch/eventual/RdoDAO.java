package com.prototipo01.batch.eventual;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
	public List<String> pesquisar(final String pesquisa, int skip, int limit)
	{
		List<String> pessoasRetorno = new ArrayList<>();
		
		
	    BasicDBObject search = new BasicDBObject("$search", pesquisa);
	    BasicDBObject text = new BasicDBObject("$text", search);
	    
	    System.out.println("inicio da pesquisa... com skip:" + skip + " e limit: " + limit);
	    MongoCursor<Document> cursor = colecao.find().skip(skip).limit(limit).iterator();
	    
	    try {
	    	int qtd = 0;
	    	System.out.println("inicio da iteracao...");
	        while (cursor.hasNext()) {
	        	Document documento = cursor.next();
	        	
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
	        	
	        	if (pessoasObj != null && pessoasObj.get("PESSOA") != null)
	        	{
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
	        	}
	        	
	        	
	        	for (Pessoa pessoa : pessoas)
	        	{
	        		String json = transformarJson(pessoa);
	        		if (!json.isEmpty())
	        		{
	        			pessoasRetorno.add(json);
	        		}
	        	}
	        	
	            qtd++;
	        }
	        //System.out.println("qtd[" + qtd + "] para a pesquisa [" + pesquisa + "].");
	    } 
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	    } finally {
	        cursor.close();
	    }
	    return pessoasRetorno;
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
	
	public String transformarJson(Pessoa pessoa)
	{
		String retorno = "";
		if (pessoa != null && naoNuloOuVazio(pessoa.getNome()))
		{
			BasicDBObject json = new BasicDBObject();
			montarAtributo(json, "NOME_PESSOA", pessoa.getNome(), true);
			montarAtributo(json, "CPF", pessoa.getCpf());
			montarAtributo(json, "NOMEMAE_PESSOA", pessoa.getNomeDaMae(), true);
			montarAtributo(json, "NUM_BO", pessoa.getOcorrencia().getNumero());
			montarAtributo(json, "ANO_BO", pessoa.getOcorrencia().getAno());
			montarAtributo(json, "ID_DELEGACIA", pessoa.getOcorrencia().getIdDelegacia());
			montarAtributo(json, "NOME_DELEGACIA", pessoa.getOcorrencia().getDelegacia(), true);
			montarAtributo(json, "LOGRADOURO", pessoa.getOcorrencia().getLocalDoFato().getLogradouro(), true);
			montarAtributo(json, "NUMERO_LOGRADOURO", pessoa.getOcorrencia().getLocalDoFato().getNumero());
			montarAtributo(json, "BAIRRO", pessoa.getOcorrencia().getLocalDoFato().getBairro(), true);
			montarAtributo(json, "CIDADE", pessoa.getOcorrencia().getLocalDoFato().getCidade(), true);
			montarAtributo(json, "ID_UF", pessoa.getOcorrencia().getLocalDoFato().getUf());
			retorno = json.toString();
		}
			
		return retorno;
	}
	
	public BasicDBObject montarAtributo(BasicDBObject json, String atributo, String valor)
	{
		return montarAtributo(json, atributo, valor, false);
	}
	
	public BasicDBObject montarAtributo(BasicDBObject json, String atributo, String valor, boolean retirarAcento)
	{
		if (naoNuloOuVazio(valor))
		{
			if (retirarAcento)
			{
				valor = retirarAcentos(valor);
			}
			json.append(atributo, valor);
		}
		return json;
	}
	
	public String retirarAcentos(String arg)
	{
	    String normalizador = Normalizer.normalize(arg, Normalizer.Form.NFD);
	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	    String retorno = pattern.matcher(normalizador).replaceAll("");
	    return retorno;
	}
	
	public boolean naoNuloOuVazio(String arg)
	{
		boolean naoNuloOuVazio = true;
		if (arg == null || 
				arg.trim().isEmpty() || 
				arg.equalsIgnoreCase("null") || 
				arg.equalsIgnoreCase("00000000000") || 
				arg.equalsIgnoreCase("DESCONHECIDO"))
		{
			naoNuloOuVazio = false;
		}
		return naoNuloOuVazio;
	}

	
}
