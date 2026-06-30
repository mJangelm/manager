package com.studio.manager.services;

import java.util.List;

import com.studio.manager.entities.Track;


public interface TrackService {
	
	Track alta(Track track);
	Track modificar(Track track);
	int eliminar(Long codigo);
	Track buscarUno(Long codigo);
	List<Track> buscarTodas();
	List<Track> buscarPorGenero(String genero);
	

}
