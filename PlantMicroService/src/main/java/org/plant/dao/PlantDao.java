package org.plant.dao;

import org.plant.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlantDao extends JpaRepository<Plant, Integer> {

    @Query("FROM Plant WHERE plantName = :plantName")
    Optional<Plant> findByName(@Param("plantName") String plantName);
}