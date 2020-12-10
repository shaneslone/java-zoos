package com.lambdaschool.zoos.services;

import com.lambdaschool.zoos.views.CountZoos;

import java.util.List;

public interface AnimalServices {
    List<CountZoos> getZooCountByAnimal();
}
