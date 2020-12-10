package com.lambdaschool.zoos.services;

import com.lambdaschool.zoos.models.Zoo;

import java.util.List;

public interface ZooServices {
    List<Zoo> findAll();
    Zoo save(Zoo zoo);
}
