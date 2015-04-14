package com.prototipo01.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockPessoa {
	
	private static Random random = new Random();
	private static int quantidade = 5;
	
	private static String[] logradouros = {"Rua Brigadeiro Tobias", "Rua Cásper Libero", "Rua Santa Ifigênia", "Rua Paula Souza", "Av. Sen. Queirós" };
	private static String[] numlocals = {"527", "478", "338", "221", "396"};
	
	public static List<Pessoa> mockPessoas() {
		
		List<Pessoa> pessoas = new ArrayList<>();
		for (int i = 0; i < quantidade; i++) {
			Pessoa pessoa = new Pessoa();
			pessoas.add(pessoa);
			pessoa.setNome("nome" + i);
			pessoa.setCpf("123432236-92");
			pessoa.setOcorrencia(mockOcorrencia(i));
		}
		
		return pessoas;
	}
	
	private static Ocorrencia mockOcorrencia(int i)
	{
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setNumero(random.nextInt(99999) + "");
		ocorrencia.setAno("2015");
		ocorrencia.setIdDelegacia(random.nextInt(99999) +"");
		ocorrencia.setLocalDoFato(mockLocal(i));
		return ocorrencia;
	}
	
	private static LocalDoFato mockLocal(int i) 
	{
		LocalDoFato local = new LocalDoFato();
		local.setLogradouro(logradouros[i]);
		local.setNumero(numlocals[i]);
		local.setCidade("São Paulo");
		return local;
	}

}
