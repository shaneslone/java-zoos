package com.lambdaschool.zoos.services;

import com.lambdaschool.zoos.models.Zoo;

import java.util.List;

public interface ZooServices {
    // Returns a list of all Zoos
    List<Zoo> findAll();

    // Returns a zoo with the given primary key
    Zoo findZooById(long id);

    void delete(long id);

    void update(Zoo zoo, long zooid);


    Zoo save(Zoo zoo);
}
