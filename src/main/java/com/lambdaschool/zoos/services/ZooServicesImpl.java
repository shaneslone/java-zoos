package com.lambdaschool.zoos.services;

import com.lambdaschool.zoos.models.Animal;
import com.lambdaschool.zoos.models.Telephone;
import com.lambdaschool.zoos.models.Zoo;
import com.lambdaschool.zoos.models.ZooAnimals;
import com.lambdaschool.zoos.repository.AnimalRepository;
import com.lambdaschool.zoos.repository.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service(value = "zooService")
public class ZooServicesImpl implements ZooServices{
    @Autowired
    private ZooRepository zoorepos;

    @Autowired
    private AnimalRepository animalrepos;

    @Override
    public List<Zoo> findAll() {
        List<Zoo> list = new ArrayList<>();
        zoorepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Zoo findZooById(long id) {
        return zoorepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zoo " + id + " not found!"));
    }

    @Transactional
    @Override
    public Zoo save(Zoo zoo) {
        Zoo newZoo = new Zoo();

        if(zoo.getZooid() != 0){
            zoorepos.findById(zoo.getZooid())
                    .orElseThrow(() -> new EntityNotFoundException("Zoo " + zoo.getZooid() + " not found"));
            newZoo.setZooid(zoo.getZooid());
        }

        newZoo.setZooname(zoo.getZooname());

        List<Telephone> phonenumbers = new ArrayList<>();
        for(Telephone t : zoo.getTelephones())
        {
            Telephone newPhone = new Telephone();
            newPhone.setPhonenumber(t.getPhonenumber());
            newPhone.setPhonetype(t.getPhonetype());
            newPhone.setZoo(newZoo);
            phonenumbers.add(newPhone);
        }
        newZoo.setTelephones(phonenumbers);

        Set<ZooAnimals> animals = new HashSet<>();
        for(ZooAnimals z : zoo.getAnimals()){
            Animal newAnimal = animalrepos.findById(z.getAnimal().getAnimalid())
                        .orElseThrow(() -> new EntityNotFoundException("Animal " + z.getAnimal().getAnimalid() + " not found!"));
            ZooAnimals newZooAnimal = new ZooAnimals(newZoo, newAnimal, z.getIncomingzoo());
            animals.add(newZooAnimal);
        }
        newZoo.setAnimals(animals);

        return zoorepos.save(newZoo);
    }

    @Override
    public void delete(long id) {
        zoorepos.deleteById(id);
    }

    @Override
    public void update(Zoo zoo, long zooid) {
        Zoo updateZoo = zoorepos.findById(zooid)
                .orElseThrow(() -> new EntityNotFoundException("Zoo " + zooid + " not found!"));

        if(zoo.getZooname() != null)updateZoo.setZooname(zoo.getZooname());

        if(zoo.getTelephones().size() > 0){
            for(Telephone t : zoo.getTelephones())
            {
                Telephone newPhone = new Telephone();
                newPhone.setPhonenumber(t.getPhonenumber());
                newPhone.setPhonetype(t.getPhonetype());
                newPhone.setZoo(updateZoo);
                updateZoo.getTelephones().add(newPhone);
            }
        }

        if(zoo.getAnimals().size() > 0){
            for(ZooAnimals z : zoo.getAnimals()){
                Animal newAnimal = animalrepos.findById(z.getAnimal().getAnimalid())
                        .orElseThrow(() -> new EntityNotFoundException("Animal " + z.getAnimal().getAnimalid() + " not found!"));
                ZooAnimals newZooAnimal = new ZooAnimals(updateZoo, newAnimal, z.getIncomingzoo());
                updateZoo.getAnimals().add(newZooAnimal);
            }
        }

        zoorepos.save(updateZoo);
    }
}
