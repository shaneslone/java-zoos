package com.lambdaschool.zoos.controllers;

import com.lambdaschool.zoos.models.Zoo;
import com.lambdaschool.zoos.services.ZooServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/zoos")
public class ZooController {
    @Autowired
    private ZooServices zooServices;

    @GetMapping(value = "/zoos", produces = "application/json")
    public ResponseEntity<?> listAllZoos(){
        List<Zoo> zoos = zooServices.findAll();
        return new ResponseEntity<>(zoos, HttpStatus.OK);
    }

    @GetMapping(value = "/zoo/{zooid}", produces = "application/json")
    public ResponseEntity<?> findZooById(@PathVariable long zooid){
        Zoo z = zooServices.findZooById(zooid);
        return new ResponseEntity<>(z, HttpStatus.OK);
    }

    @PostMapping(value = "/zoo", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addZoo(@Valid @RequestBody Zoo newZoo){
        newZoo.setZooid(0);
        zooServices.save(newZoo);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newZooURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{zooid}").buildAndExpand(newZoo.getZooid()).toUri();
        responseHeaders.setLocation(newZooURI);

        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/zoo/{zooid}", consumes = "application/json")
    public ResponseEntity<?> updateZooById(@PathVariable long zooid, @Valid @RequestBody Zoo updatedZoo){
        updatedZoo.setZooid(zooid);
        zooServices.save(updatedZoo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/zoo/{zooid}", consumes = "application/json")
    public ResponseEntity<?> patchZooById(@PathVariable long zooid, @RequestBody Zoo updateZoo){
        zooServices.update(updateZoo, zooid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/zoo/{zooid}")
    public ResponseEntity<?> deleteZooById(@PathVariable long zooid){
        zooServices.delete(zooid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
