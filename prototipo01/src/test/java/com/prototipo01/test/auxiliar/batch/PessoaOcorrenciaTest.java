package com.prototipo01.test.auxiliar.batch;

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
		rdoDAO.pesquisar("marcia");
		
		
		Assert.assertTrue(true);
	    System.out.println("@Test - testExtrairPessoas");
		
	}

}
