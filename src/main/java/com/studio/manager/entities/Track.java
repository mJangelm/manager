package com.studio.manager.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Tracks") // Debe coincidir con tu tabla de MySQL
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_track") // Mapea el nombre exacto de tu columna
    private Long idTrack;

    private String title;
    
    // Lo ponemos como String porque así lo definiste en el CREATE TABLE (aunque sea un número)
    private String bpm; 
}
