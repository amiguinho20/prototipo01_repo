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
		
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		for (int i = 0; i < quantidade; i++) {
			Pessoa pessoa = new Pessoa();
			pessoas.add(pessoa);
			
			pessoa.setNome("nome" + i);
			pessoa.setCpf("123432236-92");
			pessoa.setAnoBO("2015");
			pessoa.setNumeroBO(random.nextInt(99999) +"");
			pessoa.setIdDelegaciaBO(random.nextInt(99999) +"");
			pessoa.setLocalOcorrencia(mockLocal(i));
			
		}
		
		return pessoas;
	}
	
	private static EnderecoOcorrencia mockLocal(int i) {
		
		EnderecoOcorrencia local = new EnderecoOcorrencia();
		
		local.setLogradouro(logradouros[i]);
		local.setNumeroLogradouro(numlocals[i]);
		local.setCidade("São Paulo");
		
		return local;
	}

}
