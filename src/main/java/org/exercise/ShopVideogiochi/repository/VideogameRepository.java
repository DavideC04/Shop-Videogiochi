package org.exercise.ShopVideogiochi.repository;

import org.exercise.ShopVideogiochi.model.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideogameRepository extends JpaRepository<Videogame, Integer> {
}
