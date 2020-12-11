package com.lambdaschool.zoos.services;

import com.lambdaschool.zoos.models.Animal;
import com.lambdaschool.zoos.views.CountZoos;

import java.util.List;
import java.util.Optional;

public interface AnimalServices {
    List<CountZoos> getZooCountByAnimal();

}
