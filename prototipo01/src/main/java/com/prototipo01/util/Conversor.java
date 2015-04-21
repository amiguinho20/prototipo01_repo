package com.prototipo01.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.primefaces.model.map.LatLng;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.prototipo01.entity.Filtro;

@Named
@ApplicationScoped
public class Conversor implements Serializable{
	
	private static final String IDIOMA = "pt";
	final Geocoder geocoder = new Geocoder();
	
	public Filtro converter(Filtro filtro){
		filtro.setPesquisa(filtro.getPesquisa() + "tah !");
		
		return filtro;
	}
	
	public LatLng converterEnderecoEmLatLong(String endereco) throws IOException {
		
			GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(endereco).setLanguage(IDIOMA).getGeocoderRequest();
			GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
			List<GeocoderResult> results = geocoderResponse.getResults(); 
			float latitude = results.get(0).getGeometry().getLocation().getLat().floatValue();
		    float longitude = results.get(0).getGeometry().getLocation().getLng().floatValue();
		
			LatLng coordenada = new LatLng(latitude, longitude);
	        	
		return coordenada;
	}

}
