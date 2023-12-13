package mesander.com.TechItEasy.controllers;

import jakarta.validation.Valid;
import mesander.com.TechItEasy.dtos.input.CIModuleInputDto;
import mesander.com.TechItEasy.dtos.output.CIModuleDto;
import mesander.com.TechItEasy.exceptions.InvalidInputException;
import mesander.com.TechItEasy.services.CIModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static mesander.com.TechItEasy.controllers.HelperController.handleBindingResultError;
import java.net.URI;
import java.util.List;

@RestController
public class CIModuleController {
    private final CIModuleService ciModuleService;

    public CIModuleController(CIModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }


    // Crud Requests
    @GetMapping("/ci-modules")
    public ResponseEntity<List<CIModuleDto>> getAllCIModules() {
        List<CIModuleDto> dtos = ciModuleService.getAllCIModules();

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/ci-modules/name/{name}")
    public ResponseEntity<List<CIModuleDto>> getAllCIModulesByName(
            @PathVariable("name") String name
    ) {
        List<CIModuleDto> dtos = ciModuleService.getAllCIModulesByName(name);

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/ci-modules/{id}")
    public ResponseEntity<CIModuleDto> getCIModuleById(
            @PathVariable("id") Long id
    ) {
        CIModuleDto dto = ciModuleService.getCIModuleById(id);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/ci-modules/{id}")
    public ResponseEntity<Object> deleteCIModule(
            @PathVariable("id") Long id
    ) {
        ciModuleService.deleteCIModule(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/ci-modules")
    public ResponseEntity<CIModuleDto> createCIModule(
            @Valid
            @RequestBody CIModuleInputDto inputDto,
            BindingResult bindingResult
    ) {
        CIModuleDto dto;

        if (bindingResult.hasFieldErrors()) {
            throw new InvalidInputException(handleBindingResultError(bindingResult));
        } else {
            dto = ciModuleService.createCIModule(inputDto);
            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/" + dto.getId()).toUriString());
            return ResponseEntity.created(uri).body(dto);
        }
    }

    @PutMapping("/ci-modules/{id}")
    public ResponseEntity<CIModuleDto> updateCIModule(
        @PathVariable("id") Long id,
        @Valid
        @RequestBody CIModuleInputDto inputDto,
        BindingResult bindingResult
    ) {
        CIModuleDto dto;

        if (bindingResult.hasFieldErrors()) {
            throw new InvalidInputException(handleBindingResultError(bindingResult));
        } else {
            dto = ciModuleService.updateCIModule(id, inputDto);
        }

        return ResponseEntity.ok().body(dto);
    }

    @PatchMapping("/ci-modules/{id}")
    public ResponseEntity<CIModuleDto> patchCIModule(
        @PathVariable("id") Long id,
        @RequestBody CIModuleInputDto inputDto
    ) {
        CIModuleDto dto = ciModuleService.patchCIModule(id, inputDto);

        return ResponseEntity.ok().body(dto);
    }
}
