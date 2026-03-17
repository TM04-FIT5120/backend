package com.example.backend.repository;

import com.example.backend.entity.AirQualityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AirQualityRepository extends JpaRepository<AirQualityEntity, Long> {

    @Query("select e from AirQualityEntity e where e.recordTime >= :from")
    List<AirQualityEntity> findAllFromDate(@Param("from") LocalDateTime from);
}

