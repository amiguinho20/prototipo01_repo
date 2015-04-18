package com.prototipo01.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.prototipo01.dao.PessoaDAO;
import com.prototipo01.entity.Pessoa; 

public class PessoaLazyDataModel extends LazyDataModel<Pessoa>{

	private static final long serialVersionUID = 6547013584171330086L;
	
	private PessoaDAO pessoaDAO;
	
	private List<Pessoa> pessoas;
	private String pesquisa;
	
	public PessoaLazyDataModel(PessoaDAO pessoaDAO, String pesquisa)
	{
		this.pessoas = new ArrayList<>();
		this.pessoaDAO = pessoaDAO;
		this.pesquisa = pesquisa;
	}
	
	@Override
	public Pessoa getRowData(String rowKey) 
	{
		Pessoa pessoaRetorno = null;
		for(Pessoa pessoa : pessoas) 
		{
			if (pessoa.hashCode() == Integer.parseInt(rowKey))
			{
				pessoaRetorno = pessoa;
				break;
	        }
		}
		return pessoaRetorno;
	}
	
	
	@Override
	public Object getRowKey(Pessoa pessoa) 
	{
		//TODO cuidado com isso... apenas teste
		return pessoa.hashCode();
	}
	
	
	@Override
	public List<Pessoa> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) 
	{
		pessoas = pessoaDAO.pesquisarLazy(pesquisa, first, pageSize);
		
		int count = pessoaDAO.contar(pesquisa);
		setRowCount(count);
		
		return pessoas;
	}

}
