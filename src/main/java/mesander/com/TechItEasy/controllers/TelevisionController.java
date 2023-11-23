package mesander.com.TechItEasy.controllers;

import mesander.com.TechItEasy.models.Television;
import mesander.com.TechItEasy.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TelevisionController {
    private ArrayList<Television> televisions = new ArrayList<>();

    @GetMapping("/televisions")
    public ResponseEntity<ArrayList<Television>> getAllTelevisions() {
        return new ResponseEntity<>(this.televisions, HttpStatus.OK);
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Television> getTelevision(@PathVariable int id) {
        if (id >= 0 && id < this.televisions.size()) {
            return new ResponseEntity<>(this.televisions.get(id), HttpStatus.OK);
        } else {
            throw new RecordNotFoundException("ID cannot be found");
        }
    }

    @PostMapping("/televisions")
    public ResponseEntity<Television> addTelevision(@RequestBody Television television) {
        this.televisions.add(television);
        return new ResponseEntity<>(television, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Television> updateTelevision(@PathVariable int id, @RequestBody Television television) {
        if (id >= 0 && id < this.televisions.size()) {
            this.televisions.set(id, television);
            return new ResponseEntity<>(television, HttpStatus.OK);
        } else {
            throw new RecordNotFoundException("ID cannot be found");
        }
    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Television> deleteTelevision(@PathVariable int id) {
        if (id >= 0 && id < this.televisions.size()) {
            this.televisions.remove(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new RecordNotFoundException("ID cannot be found");
        }
    }
}
