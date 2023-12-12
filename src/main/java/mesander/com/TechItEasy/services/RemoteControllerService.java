package mesander.com.TechItEasy.services;

import mesander.com.TechItEasy.dtos.input.RemoteControllerInputDto;
import mesander.com.TechItEasy.dtos.output.RemoteControllerDto;
import mesander.com.TechItEasy.models.RemoteController;
import mesander.com.TechItEasy.repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RemoteControllerService {
    private final RemoteControllerRepository remoteControllerRepository;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }

//    public List<RemoteControllerDto> getAllRemoteControllers() {
//        List<RemoteController> remoteControllers = remoteControllerRepository.findAll();
//        List<RemoteControllerDto> remoteControllerDtos = new ArrayList<>();
//
//        for (RemoteController remoteController: remoteControllers) {
//
//        }
//    }

    public RemoteController transferToRemoteController(RemoteControllerInputDto dto){
        RemoteController remoteController = new RemoteController();

        remoteController.setCompatibleWith(dto.getCompatibleWith());
        remoteController.setBatteryType(dto.getBatteryType());
        remoteController.setName(dto.getName());
        remoteController.setBrand(dto.getBrand());
        remoteController.setPrice(dto.getPrice());
        remoteController.setOriginalStock(dto.getOriginalStock());

        return remoteController;
    }

    public RemoteControllerDto transferToDto(RemoteController remoteController){
        RemoteControllerDto dto = new RemoteControllerDto();

        dto.setCompatibleWith(remoteController.getCompatibleWith());
        dto.setBatteryType(remoteController.getBatteryType());
        dto.setName(remoteController.getName());
        dto.setBrand(remoteController.getBrand());
        dto.setPrice(remoteController.getPrice());
        dto.setOriginalStock(remoteController.getOriginalStock());

        return dto;
    }




}
