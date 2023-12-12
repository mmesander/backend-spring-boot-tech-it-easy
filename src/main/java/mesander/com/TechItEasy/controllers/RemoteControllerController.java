package mesander.com.TechItEasy.controllers;

import jakarta.validation.Valid;
import mesander.com.TechItEasy.dtos.input.RemoteControllerInputDto;
import mesander.com.TechItEasy.dtos.output.RemoteControllerDto;
import mesander.com.TechItEasy.exceptions.InvalidInputException;
import mesander.com.TechItEasy.services.RemoteControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static mesander.com.TechItEasy.controllers.HelperController.handleBindingResultError;
import java.net.URI;
import java.util.List;

@RestController
public class RemoteControllerController {
    private final RemoteControllerService remoteControllerService;

    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }


    // Crud Requests
    @GetMapping("/remote-controllers")
    public ResponseEntity<List<RemoteControllerDto>> getAllRemoteControllers() {
        List<RemoteControllerDto> dtos = remoteControllerService.getAllRemoteControllers();

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/remote-controllers/brand/{brand}")
    public ResponseEntity<List<RemoteControllerDto>> getAllRemoteControllersByBrand(
            @PathVariable(value = "brand", required = true) String brand
    ) {
        List<RemoteControllerDto> dtos = remoteControllerService.getAllRemoteControllersByBrand(brand);

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/remote-controllers/name/{name}")
    public ResponseEntity<List<RemoteControllerDto>> getAllRemoteControllersByName(
            @PathVariable(value = "name", required = true) String name
    ) {
        List<RemoteControllerDto> dtos = remoteControllerService.getAllRemoteControllersByName(name);

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/remote-controllers/compatible-with/{compatibleWith}")
    public ResponseEntity<List<RemoteControllerDto>> getAllRemoteControllersByCompatibleWith(
            @PathVariable(value = "compatibleWith", required = true) String compatibleWith
    ) {
        List<RemoteControllerDto> dtos = remoteControllerService.getAllRemoteControllersCompatibleWith(compatibleWith);

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/remote-controllers/{id}")
    public ResponseEntity<RemoteControllerDto> getRemoteControllerById(
            @PathVariable(value = "id") Long id
    ) {
        RemoteControllerDto dto = remoteControllerService.getRemoteControllerById(id);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/remote-controllers/{id}")
    public ResponseEntity<Object> deleteRemoteController(@PathVariable("id") Long id) {
        remoteControllerService.deleteRemoteController(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/remote-controllers")
    public ResponseEntity<RemoteControllerDto> createRemoteController(
            @Valid
            @RequestBody RemoteControllerInputDto inputDto,
            BindingResult bindingResult
    ) {
        RemoteControllerDto dto;

        if (bindingResult.hasFieldErrors()) {
            throw new InvalidInputException(handleBindingResultError(bindingResult));
        } else {
            dto = remoteControllerService.createRemoteController(inputDto);
            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/" + dto.getId()).toUriString());
            return ResponseEntity.created(uri).body(dto);
        }
    }

    @PutMapping("/remote-controllers/{id}")
    public ResponseEntity<RemoteControllerDto> updateRemoteController(
            @PathVariable("id") Long id,
            @Valid
            @RequestBody RemoteControllerInputDto inputDto,
            BindingResult bindingResult
    ) {
        RemoteControllerDto dto;

        if (bindingResult.hasFieldErrors()) {
            throw new InvalidInputException(handleBindingResultError(bindingResult));
        } else {
            dto = remoteControllerService.updateRemoteController(id, inputDto);
        }

        return ResponseEntity.ok().body(dto);
    }

    @PatchMapping("/remote-controllers/{id}")
    public ResponseEntity<RemoteControllerDto> patchRemoteController(
        @PathVariable("id") Long id,
        @RequestBody RemoteControllerInputDto inputDto
    ) {
        RemoteControllerDto dto = remoteControllerService.patchRemoteController(id, inputDto);

        return ResponseEntity.ok().body(dto);
    }
}
