package mesander.com.TechItEasy.controllers;

import mesander.com.TechItEasy.dtos.output.RemoteControllerDto;
import mesander.com.TechItEasy.services.RemoteControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
