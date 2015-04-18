package com.prototipo01.test.auxiliar.batch;

import java.io.PrintWriter;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.prototipo01.batch.eventual.RdoDAO;

public class PessoaOcorrenciaTest {
	
	RdoDAO rdoDAO;
	
	@Before
	public void pre()
	{
		rdoDAO = new RdoDAO();
		rdoDAO.abrirConexao();
	}
	
	@After
	public void pos()
	{
		rdoDAO.fecharConecao();
	}
	
	@Test
	public void testExtrairPessoas()
	{
		
		int count = 0;
		int limit = 1000;
		while (count < 60)
		{
			List<String> pessoas = rdoDAO.pesquisar("", count * limit, limit);
			if (pessoas.isEmpty())
			{
				System.out.println("nao retornou pessoas... acabou.");
				break;
			}
			try{
				PrintWriter writer = new PrintWriter("/Users/Amiguinho/Fences/pula" + count + ".json");
				System.out.println("montagem do arquivo... " + count + " com pessoas: " + pessoas.size());
				for (String pessoa : pessoas)
				{
					writer.println(pessoa);
				}
				writer.close();
			} catch( Exception e ){
				e.printStackTrace();
			}
			count++;
		}
		
		Assert.assertTrue(true);
	    System.out.println("@Test - testExtrairPessoas");
		
	}

}
