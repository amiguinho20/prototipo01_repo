package com.prototipo01.converter;

import javax.enterprise.context.ApplicationScoped;

import org.bson.Document;

import com.prototipo01.entity.LocalDoFato;
import com.prototipo01.entity.Ocorrencia;
import com.prototipo01.entity.Pessoa;

@ApplicationScoped
public class PessoaConverter implements Converter<Pessoa>{

	@Override
	public Document paraDocumento(Pessoa obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pessoa paraObjeto(Document doc) {
		
		Pessoa pessoa = new Pessoa();
		Ocorrencia ocorrencia = pessoa.getOcorrencia();
		LocalDoFato localDoFato = ocorrencia.getLocalDoFato();
		
		pessoa.setNome(doc.getString("NOME_PESSOA"));
		pessoa.setCpf(doc.getString("CPF"));
		pessoa.setNomeDaMae(doc.getString("NOMEMAE_PESSOA"));
		ocorrencia.setNumero(doc.getString("NUM_BO"));
		ocorrencia.setAno(doc.getString("ANO_BO"));
		ocorrencia.setIdDelegacia(doc.getString("ID_DELEGACIA"));
		ocorrencia.setDelegacia(doc.getString("NOME_DELEGACIA"));
		localDoFato.setLogradouro(doc.getString("LOGRADOURO"));
		localDoFato.setNumero(doc.getString("NUMERO_LOGRADOURO"));
		localDoFato.setBairro(doc.getString("BAIRRO"));
		localDoFato.setCidade(doc.getString("CIDADE"));
		localDoFato.setUf(doc.getString("ID_UF"));
		
		return pessoa;
	}

}
