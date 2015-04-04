package com.prototipo01.util;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.prototipo01.entity.Filtro;

@Named
@ApplicationScoped
public class Conversor implements Serializable{
	
	public Filtro converter(Filtro filtro){
		filtro.setPesquisa(filtro.getPesquisa() + "tah !");
		return filtro;
	}

}
