package com.lambdaschool.zoos.repository;

import com.lambdaschool.zoos.models.Animal;
import com.lambdaschool.zoos.views.CountZoos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimalRepository extends CrudRepository<Animal, Long> {

    @Query(value = "SELECT a.animaltype, a.animalid, count(z.animalid) AS countzoos " +
            "FROM animals a LEFT JOIN zooanimals z " +
            "ON a.animalid = z.animalid " +
            "GROUP BY a.animaltype", nativeQuery = true)
    List<CountZoos> getAnimalZooCount();
}
