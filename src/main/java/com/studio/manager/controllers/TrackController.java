package com.studio.manager.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.studio.manager.entities.Track;
import com.studio.manager.repositories.TrackRepository;
import com.studio.manager.services.TrackService;





@CrossOrigin (origins = "http://localhost:5173")
@RestController
public class TrackController {

    @Autowired
    private TrackService trackService;

    @GetMapping("/")
    public List<Track> getTracks() {
        return trackService.buscarTodas();
    }
    @PostMapping("/alta")
    public ResponseEntity<?> guardar(@RequestBody Track track) {
        Track guardado = trackService.alta(track);
// Usamos el ? porque o bien nos devuelve un Track, o bien nos devuelve
 //un error, osease, un String.
        
        if (guardado != null) {
            // Todo OK. Devolvemos el objeto y un código 201 (Created)
            return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
        } else {
            // El servicio devolvió null porque el ID ya existía
            // Pista: El código 409 es "Conflict"
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: El track ya existe");
        }
    }
    
	@PutMapping("/{codigo}") 
	public ResponseEntity<?> modificar(@PathVariable Long Codigo, @RequestBody Track track) {
	
		track.setIdTrack(Codigo);
		Track modificado = trackService.modificar(track);
	
	if (modificado != null) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(modificado);
	}
	else {

        return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: El track no existe ");
	}
	}
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        int resultado = trackService.eliminar(id);
        
        if (resultado == 1) {
            // 200 OK - Todo fue bien
            return ResponseEntity.ok("Track eliminado correctamente");
        } else if (resultado == 0) {
            // 404 Not Found - No encontramos qué borrar
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el track con ID: " + id);
        } else {
            // 500 Internal Server Error - Algo explotó en el servidor
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al intentar eliminar");
        }
    }
    @GetMapping("/uno/{codigo}")
    public ResponseEntity<?> trackUno(@PathVariable Long codigo) {
        Track resultado = trackService.buscarUno(codigo);
        
        if (resultado != null) {
            // 200 OK es el estándar para lecturas
            return ResponseEntity.ok(resultado); 
        } else {
            // 404 porque el servidor NO falló, solo no encontró el dato
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: No se encontró el track");
        }
    }
    
    @GetMapping("/genero/{genero}")
    public ResponseEntity<?> buscarPorGenero(@PathVariable String genero) {
        List<Track> resultado = trackService.buscarPorGenero(genero);
        
        if (!resultado.isEmpty()) {
            return ResponseEntity.ok(resultado);
        } else {
            // Si no hay tracks de ese género, devolvemos un 204 (No Content) o una lista vacía con 200.
            // En REST es muy común devolver 200 con la lista vacía [] para que el Front no rompa.
            return ResponseEntity.ok(resultado); 
        }
    }

    
    
    
}