package com.prototipo01.converter;

import org.bson.Document;

/**
 * @param <T> Tipo de Objeto para conversao.
 */
public interface Converter<T> {

	Document paraDocumento(T obj);
	T paraObjeto(Document doc);

}
