package com.example.backend.repository;

import com.example.backend.entity.FavoriteLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteLocationRepository extends JpaRepository<FavoriteLocation, Long> {

    @Query(value = """
            SELECT * FROM favorite_location
            WHERE city_name = ?1
            AND latitude = ?2
            AND longitude = ?3
            """, nativeQuery = true)
    List<FavoriteLocation> findLocation(String cityName, Double latitude, Double longitude);
}