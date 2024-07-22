package org.plant.dao;

import org.plant.entity.CompendiumPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompendiumDao extends JpaRepository<CompendiumPage, Integer> {
}
