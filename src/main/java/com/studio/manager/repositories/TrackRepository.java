package com.studio.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studio.manager.entities.Track;

public interface TrackRepository extends JpaRepository<Track, Long> {
    // Aquí ya tienes gratis métodos como save(), findAll(), deleteById()...
}
