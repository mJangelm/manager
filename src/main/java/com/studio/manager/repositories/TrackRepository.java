package com.studio.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studio.manager.entities.Track;
import java.util.List;

public interface TrackRepository extends JpaRepository<Track, Long> {
	List<Track> findByGeneroIgnoreCase(String genero);
}
