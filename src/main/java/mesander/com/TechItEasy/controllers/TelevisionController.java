package mesander.com.TechItEasy.controllers;

import jakarta.validation.Valid;
import mesander.com.TechItEasy.dtos.TelevisionDto;
import mesander.com.TechItEasy.dtos.TelevisionInputDto;
import mesander.com.TechItEasy.dtos.TelevisionSalesDto;
import mesander.com.TechItEasy.services.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class TelevisionController {
    private final TelevisionService televisionService;

    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    // CRUD Requests
    @GetMapping("/televisions")
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions(
            @RequestParam(value = "brand", required = false) Optional<String> brand,
            @RequestParam(value = "name", required = false) Optional<String> name
    ) {
        List<TelevisionDto> dtos;

        if (brand.isPresent() && name.isPresent()) {
            dtos = televisionService.getAllTelevisionsByBrandANdName(brand.get(), name.get());
        } else if (brand.isPresent()) {
            dtos = televisionService.getAllTelevisionsByBrand(brand.get());
        } else if (name.isPresent()) {
            dtos = televisionService.getAllTelevisionsByName(name.get());
        } else {
            dtos = televisionService.getAllTelevisions();
        }

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<TelevisionDto> getSpecificTelevision(@PathVariable("id") Long id) {
        TelevisionDto television = televisionService.getTelevisionById(id);

        return ResponseEntity.ok().body(television);
    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable("id") Long id) {
        televisionService.deleteTelevision(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/televisions")
    public ResponseEntity<Object> createTelevision(@Valid @RequestBody TelevisionInputDto televisionInputDto, BindingResult bindingResult) {
        TelevisionDto dto;
        StringBuilder sb = new StringBuilder();

        if (bindingResult.hasFieldErrors()) {
            for (FieldError error: bindingResult.getFieldErrors()) {
                sb.append(error.getField());
                sb.append(" : ");
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        } else {
            dto = televisionService.createTelevision(televisionInputDto);
        }

        return ResponseEntity.created(null).body(dto);
    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable("id") Long id, @Valid @RequestBody TelevisionInputDto newTelevision, BindingResult bindingResult) {
        TelevisionDto dto;
        StringBuilder sb = new StringBuilder();

        if (bindingResult.hasFieldErrors()) {
            for (FieldError error: bindingResult.getFieldErrors()) {
                sb.append(error.getField());
                sb.append(" : ");
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        } else {
            dto = televisionService.updateTelevision(id, newTelevision);
        }

        return ResponseEntity.ok().body(dto);
    }

    @PatchMapping("/televisions/{id}")
    public ResponseEntity<TelevisionDto> patchTelevision(@PathVariable("id") Long id, @RequestBody TelevisionInputDto newTelevision) {
        TelevisionDto dto = televisionService.patchTelevision(id, newTelevision);

        return ResponseEntity.ok().body(dto);
    }

    // Bonus
    @GetMapping("/televisions/sales-info")
    public ResponseEntity<List<TelevisionSalesDto>> getSalesInfo() {
        List<TelevisionSalesDto> salesDtos;

        salesDtos = televisionService.getSalesInfo();

        return ResponseEntity.ok().body(salesDtos);
    }
}
