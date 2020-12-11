package com.lambdaschool.zoos.services;

import com.lambdaschool.zoos.models.Animal;
import com.lambdaschool.zoos.repository.AnimalRepository;
import com.lambdaschool.zoos.views.CountZoos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "animalServices")
public class AnimalServicesImpl implements AnimalServices{
    @Autowired
    private AnimalRepository animalrepos;

    @Override
    public List<CountZoos> getZooCountByAnimal() {
        return animalrepos.getAnimalZooCount();
    }

}
