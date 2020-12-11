package com.lambdaschool.zoos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "zooanimals")
@IdClass(ZooAnimalsId.class)
public class ZooAnimals extends Auditable implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "zooid")
    @JsonIgnoreProperties(value = "aniamls")
    private Zoo zoo;

    @Id
    @ManyToOne
    @JoinColumn(name = "animalid")
    @JsonIgnoreProperties(value = "zoos")
    private Animal animal;

    private String incomingzoo;

    public ZooAnimals() {
    }

    public ZooAnimals(Zoo zoo, Animal animal, String incomingzoo) {
        this.zoo = zoo;
        this.animal = animal;
        this.incomingzoo = incomingzoo;
    }

    public Zoo getZoo() {
        return zoo;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof ZooAnimals)) return false;

        ZooAnimals that = (ZooAnimals) o;

        return  ((this.zoo == null) ? 0 : this.zoo.getZooid()) ==
                ((that.getZoo() == null) ? 0 : that.getZoo().getZooid()) &&
                ((this.animal == null) ? 0 : this.animal.getAnimalid()) == ((that.getAnimal() == null) ? 0 : that.getAnimal().getAnimalid());
    }

    public String getIncomingzoo() {
        return incomingzoo;
    }

    public void setIncomingzoo(String incomingzoo) {
        this.incomingzoo = incomingzoo;
    }
}
