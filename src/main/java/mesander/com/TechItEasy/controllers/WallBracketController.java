package mesander.com.TechItEasy.controllers;

import jakarta.validation.Valid;
import mesander.com.TechItEasy.dtos.input.WallBracketInputDto;
import mesander.com.TechItEasy.dtos.output.WallBracketDto;
import mesander.com.TechItEasy.exceptions.InvalidInputException;
import mesander.com.TechItEasy.services.WallBracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static mesander.com.TechItEasy.controllers.HelperController.handleBindingResultError;

import java.net.URI;
import java.util.List;

@RestController
public class WallBracketController {
    private final WallBracketService wallBracketService;

    public WallBracketController(WallBracketService wallBracketService) {
        this.wallBracketService = wallBracketService;
    }


    // Crud Requests
    @GetMapping("/wall-brackets")
    public ResponseEntity<List<WallBracketDto>> getALlWallBrackets() {
        List<WallBracketDto> dtos = wallBracketService.getAllWallBrackets();

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/wall-brackets/adjustable/{adjustable}")
    public ResponseEntity<List<WallBracketDto>> getAllWallBracketsByAdjustable(
            @PathVariable("adjustable") Boolean adjustable
    ) {
        List<WallBracketDto> dtos = wallBracketService.getAllWallBracketsByAdjustable(adjustable);

        return ResponseEntity.ok().body(dtos);
    }

    @DeleteMapping("/wall-brackets/{id}")
    public ResponseEntity<Object> deleteWallBracket(
            @PathVariable("id") Long id
    ) {
        wallBracketService.deleteWallBracket(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/wall-brackets")
    public ResponseEntity<WallBracketDto> createWallBracket(
            @Valid
            @RequestBody WallBracketInputDto inputDto,
            BindingResult bindingResult
            ) {
        WallBracketDto dto;

        if (bindingResult.hasFieldErrors()) {
            throw new InvalidInputException(handleBindingResultError(bindingResult));
        } else {
            dto = wallBracketService.createWallBracket(inputDto);
            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/" + dto.getId()).toUriString());
            return ResponseEntity.created(uri).body(dto);
        }
    }

    @PutMapping("/wall-brackets/{id}")
    public ResponseEntity<WallBracketDto> updateWallBracket(
            @PathVariable("id") Long id,
            @Valid
            @RequestBody WallBracketInputDto inputDto,
            BindingResult bindingResult
    ) {
        WallBracketDto dto;

        if (bindingResult.hasFieldErrors()) {
            throw new InvalidInputException(handleBindingResultError(bindingResult));
        } else {
            dto = wallBracketService.updateWallBracket(id, inputDto);
        }

        return ResponseEntity.ok().body(dto);
    }

    @PatchMapping("/wall-brackets/{id}")
    public ResponseEntity<WallBracketDto> patchWallBracket(
            @PathVariable("id") Long id,
            @RequestBody WallBracketInputDto inputDto
    ) {
        WallBracketDto dto = wallBracketService.patchWallBracket(id, inputDto);

        return ResponseEntity.ok().body(dto);
    }
}
