package com.studio.manager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studio.manager.entities.Track;
import com.studio.manager.repositories.TrackRepository;

@Service
public class TrackServiceImpl implements TrackService{
	
@Autowired
private TrackRepository trepo;

@Override
public Track alta(Track track) {
	try {
        // Forzamos que el ID sea null para que JPA entienda que es una INSERCIÓN nueva
        // y no un intento de actualizar uno existente.
        track.setIdTrack(null); 
        return trepo.save(track);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

@Override
public Track modificar(Track track) {
	try {
		if (trepo.existsById(track.getIdTrack()))
			return trepo.save(track);
		else 
			return null;
		
		
	} catch (Exception e) {
		e.printStackTrace();
	
	return null;
	}
}




@Override
public List<Track> buscarTodas() {
	// TODO Auto-generated method stub
	return trepo.findAll();
}

@Override
public int eliminar(Long codigo) {
	try {
		if (trepo.existsById(codigo)) {
			trepo.deleteById(codigo);
			return 1;
		}
		else
			return 0;
		
	} catch (Exception e) {
		e.printStackTrace();
		return -1;
	}
}

@Override
public Track buscarUno(Long codigo) {
	// TODO Auto-generated method stub
	return trepo.findById(codigo).orElse(null);
}

@Override
public List<Track> buscarPorGenero(String genero) {
	try {
        return trepo.findByGeneroIgnoreCase(genero);
    } catch (Exception e) {
        e.printStackTrace();
        return List.of(); // Devolvemos una lista vacía en caso de error para evitar nulls
    }
}



}
