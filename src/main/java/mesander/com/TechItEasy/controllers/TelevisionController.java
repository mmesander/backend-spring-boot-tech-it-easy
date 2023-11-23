package mesander.com.TechItEasy.controllers;

import mesander.com.TechItEasy.models.Television;
import mesander.com.TechItEasy.exceptions.RecordNotFoundException;
import mesander.com.TechItEasy.repositories.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TelevisionController {
    private final TelevisionRepository televisionRepository;

    public TelevisionController(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    @GetMapping("/televisions")
    public ResponseEntity<List<Television>> getAllTelevisions(@RequestParam(value = "brand", required = false) String brand) {
        List<Television> televisions;
        televisions = televisionRepository.findAll();
        return ResponseEntity.ok().body(televisions);
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Television> getSpecificTelevision(@PathVariable("id") Long id) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        } else {
            Television foundTelevision = television.get();
            return ResponseEntity.ok().body(foundTelevision);
        }

    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Television> deleteSpecificTelevision(@PathVariable("id") Long id) {
        televisionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity<Television> updateTelevision(@PathVariable("id") Long id, @RequestBody Television newTelevision) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        } else {
            Television foundTelevision = television.get();
            foundTelevision.setAmbiLight(newTelevision.getAmbiLight());
            foundTelevision.setAvailableSize(newTelevision.getAvailableSize());
            foundTelevision.setBluetooth(newTelevision.getBluetooth());
            foundTelevision.setBrand(newTelevision.getBrand());
            foundTelevision.setHdr(newTelevision.getHdr());
            foundTelevision.setName(newTelevision.getName());
            foundTelevision.setOriginalStock(newTelevision.getOriginalStock());
            foundTelevision.setPrice(newTelevision.getPrice());
            foundTelevision.setRefreshRate(newTelevision.getRefreshRate());
            foundTelevision.setScreenQuality(newTelevision.getScreenQuality());
            foundTelevision.setScreenType(newTelevision.getScreenType());
            foundTelevision.setSmartTv(newTelevision.getSmartTv());
            foundTelevision.setSold(newTelevision.getSold());
            foundTelevision.setType(newTelevision.getType());
            foundTelevision.setVoiceControl(newTelevision.getVoiceControl());
            foundTelevision.setWifi(newTelevision.getWifi());

            Television updatedTelevision = televisionRepository.save(foundTelevision);
            return ResponseEntity.ok().body(updatedTelevision);
        }
    }

    @PatchMapping("/televisions/{id}")
    public ResponseEntity<Television> updateIncompleteTelevision(@PathVariable("id") Long id, @RequestBody Television newTelevision) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        } else {
            Television foundTelevision = television.get();

            if (newTelevision.getAmbiLight() != null) {
                foundTelevision.setAmbiLight(newTelevision.getAmbiLight());
            }
            if (newTelevision.getAvailableSize() != null) {
                foundTelevision.setAvailableSize(newTelevision.getAvailableSize());
            }
            if (newTelevision.getBluetooth() != null) {
                foundTelevision.setBluetooth(newTelevision.getBluetooth());
            }
            if (newTelevision.getBrand() != null) {
                foundTelevision.setBrand(newTelevision.getBrand());
            }
            if (newTelevision.getHdr() != null) {
                foundTelevision.setHdr(newTelevision.getHdr());
            }
            if (newTelevision.getName() != null) {
                foundTelevision.setName(newTelevision.getName());
            }
            if (newTelevision.getOriginalStock() != null) {
                foundTelevision.setOriginalStock(newTelevision.getOriginalStock());
            }

            if (newTelevision.getPrice() != null) {
                foundTelevision.setPrice(newTelevision.getPrice());
            }

            if (newTelevision.getRefreshRate() != null) {
                foundTelevision.setRefreshRate(newTelevision.getRefreshRate());
            }

            if (newTelevision.getScreenQuality() != null) {
                foundTelevision.setScreenQuality(newTelevision.getScreenQuality());
            }

            if (newTelevision.getScreenType() != null) {
                foundTelevision.setScreenType(newTelevision.getScreenType());
            }

            if (newTelevision.getSmartTv() != null) {
                foundTelevision.setSmartTv(newTelevision.getSmartTv());
            }

            if (newTelevision.getSold() != null) {
                foundTelevision.setSold(newTelevision.getSold());
            }
            if (newTelevision.getType() != null) {
                foundTelevision.setType(newTelevision.getType());
            }
            if (newTelevision.getVoiceControl() != null) {
                foundTelevision.setVoiceControl(newTelevision.getVoiceControl());
            }
            if (newTelevision.getWifi() != null) {
                foundTelevision.setWifi(newTelevision.getWifi());
            }

            Television updatedTelevision = televisionRepository.save(foundTelevision);
            return ResponseEntity.ok().body(updatedTelevision);
        }
    }

}
