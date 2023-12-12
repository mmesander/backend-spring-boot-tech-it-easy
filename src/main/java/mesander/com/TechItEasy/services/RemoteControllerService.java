package mesander.com.TechItEasy.services;

import mesander.com.TechItEasy.dtos.input.RemoteControllerInputDto;
import mesander.com.TechItEasy.dtos.output.RemoteControllerDto;
import mesander.com.TechItEasy.exceptions.RecordNotFoundException;
import mesander.com.TechItEasy.models.RemoteController;
import mesander.com.TechItEasy.repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RemoteControllerService {
    private final RemoteControllerRepository remoteControllerRepository;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }

    public List<RemoteControllerDto> getAllRemoteControllers() {
        List<RemoteController> remoteControllers = remoteControllerRepository.findAll();
        List<RemoteControllerDto> remoteControllerDtos = new ArrayList<>();

        for (RemoteController remoteController: remoteControllers) {
            RemoteControllerDto dto = transferToDto(remoteController);
            remoteControllerDtos.add(dto);
        }

        if (remoteControllerDtos.isEmpty()) {
            throw new RecordNotFoundException("No remote controllers found");
        } else {
            return remoteControllerDtos;
        }
    }

    public List<RemoteControllerDto> getAllRemoteControllersByBrand(String brand) {
        List<RemoteController> remoteControllers = remoteControllerRepository.findRemoteControllerByBrand(brand);
        List<RemoteControllerDto> remoteControllerDtos = new ArrayList<>();

        for (RemoteController remoteController : remoteControllers) {
            RemoteControllerDto dto = transferToDto(remoteController);
            remoteControllerDtos.add(dto);
        }

        if (remoteControllerDtos.isEmpty()) {
            throw new RecordNotFoundException("No remote controllers found with brand: " + brand);
        } else {
            return remoteControllerDtos;
        }
    }

    public List<RemoteControllerDto> getAllRemoteControllersByName(String name) {
        List<RemoteController> remoteControllers = remoteControllerRepository.findRemoteControllerByName(name);
        List<RemoteControllerDto> remoteControllerDtos = new ArrayList<>();

        for (RemoteController remoteController : remoteControllers) {
            RemoteControllerDto dto = transferToDto(remoteController);
            remoteControllerDtos.add(dto);
        }

        if (remoteControllerDtos.isEmpty()) {
            throw new RecordNotFoundException("No remote controllers found with name: " + name);
        } else {
            return remoteControllerDtos;
        }
    }

    public List<RemoteControllerDto> getAllRemoteControllersCompatibleWith(String compatibleWith) {
        List<RemoteController> remoteControllers = remoteControllerRepository.findRemoteControllerByCompatibleWith(compatibleWith);
        List<RemoteControllerDto> remoteControllerDtos = new ArrayList<>();

        for (RemoteController remoteController : remoteControllers) {
            RemoteControllerDto dto = transferToDto(remoteController);
            remoteControllerDtos.add(dto);
        }

        if (remoteControllerDtos.isEmpty()) {
            throw new RecordNotFoundException("No remote controllers are compatible with: " + compatibleWith);
        } else {
            return remoteControllerDtos;
        }
    }

    public RemoteControllerDto getRemoteControllerById(Long id) {
        Optional<RemoteController> remoteController = remoteControllerRepository.findById(id);

        if (remoteController.isPresent()) {
            return transferToDto(remoteController.get());
        } else {
            throw new RecordNotFoundException("No remote controller found with id: " + id);
        }
    }

    public void deleteRemoteController(Long id) {
        Optional<RemoteController> remoteController = remoteControllerRepository.findById(id);

        if (remoteController.isPresent()) {
            remoteControllerRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No remote controller found with id: " + id);
        }
    }

    public RemoteControllerDto createRemoteController(RemoteControllerInputDto input) {
        RemoteController remoteController = transferToRemoteController(input);

        remoteControllerRepository.save(remoteController);

        return transferToDto(remoteController);
    }

    public RemoteControllerDto updateRemoteController(Long id, RemoteControllerInputDto inputDto) {
        Optional<RemoteController> remoteController = remoteControllerRepository.findById(id);

        if (remoteController.isPresent()) {
            RemoteController foundRemoteController = remoteController.get();

            foundRemoteController.setCompatibleWith(inputDto.getCompatibleWith());
            foundRemoteController.setBatteryType(inputDto.getBatteryType());
            foundRemoteController.setName(inputDto.getName());
            foundRemoteController.setBrand(inputDto.getBrand());
            foundRemoteController.setPrice(inputDto.getPrice());
            foundRemoteController.setOriginalStock(inputDto.getOriginalStock());

            RemoteController updatedRemoteContrtoller = remoteControllerRepository.save(foundRemoteController);

            return transferToDto(updatedRemoteContrtoller);
        } else {
            throw new RecordNotFoundException("No remote controller found with id: " + id);
        }
    }

    public RemoteControllerDto patchRemoteController(Long id, RemoteControllerInputDto inputDto) {
        Optional<RemoteController> remoteController = remoteControllerRepository.findById(id);

        if (remoteController.isPresent()) {
            RemoteController foundRemoteController = remoteController.get();

            if (inputDto.getCompatibleWith() != null) {
                foundRemoteController.setCompatibleWith(inputDto.getCompatibleWith());
            }
            if (inputDto.getBatteryType() != null) {
                foundRemoteController.setBatteryType(inputDto.getBatteryType());
            }
            if (inputDto.getName() != null) {
                foundRemoteController.setName(inputDto.getName());
            }
            if (inputDto.getBrand() != null) {
                foundRemoteController.setBrand(inputDto.getBrand());
            }
            if (inputDto.getPrice() != null) {
                foundRemoteController.setPrice(inputDto.getPrice());
            }
            if (inputDto.getOriginalStock() != null) {
                foundRemoteController.setOriginalStock(inputDto.getOriginalStock());
            }

            RemoteController updatedRemoteController = remoteControllerRepository.save(foundRemoteController);

            return transferToDto(updatedRemoteController);
        } else {
            throw new RecordNotFoundException("No remote controller found with id: " + id);
        }
    }


    // Transfer Methods
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

        dto.setId(remoteController.getId());
        dto.setCompatibleWith(remoteController.getCompatibleWith());
        dto.setBatteryType(remoteController.getBatteryType());
        dto.setName(remoteController.getName());
        dto.setBrand(remoteController.getBrand());
        dto.setPrice(remoteController.getPrice());
        dto.setOriginalStock(remoteController.getOriginalStock());

        return dto;
    }




}
