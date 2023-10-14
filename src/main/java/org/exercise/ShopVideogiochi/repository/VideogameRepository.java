package org.exercise.ShopVideogiochi.repository;

import org.exercise.ShopVideogiochi.model.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideogameRepository extends JpaRepository<Videogame, Integer> {


    @Query(value = "SELECT * FROM games g WHERE g.title LIKE %:nameSearch% ", nativeQuery = true)
    List<Videogame> findByTitleContaining(@Param("nameSearch") String nameSearch);


}
