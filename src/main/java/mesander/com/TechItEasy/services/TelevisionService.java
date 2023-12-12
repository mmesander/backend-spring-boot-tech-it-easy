package mesander.com.TechItEasy.services;

import mesander.com.TechItEasy.dtos.output.TelevisionDto;
import mesander.com.TechItEasy.dtos.input.TelevisionInputDto;
import mesander.com.TechItEasy.dtos.output.TelevisionSalesDto;
import mesander.com.TechItEasy.exceptions.RecordNotFoundException;
import mesander.com.TechItEasy.models.RemoteController;
import mesander.com.TechItEasy.models.Television;
import mesander.com.TechItEasy.repositories.RemoteControllerRepository;
import mesander.com.TechItEasy.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {
    private final TelevisionRepository televisionRepository;
    private final RemoteControllerRepository remoteControllerRepository;
    private final RemoteControllerService remoteControllerService;

    public TelevisionService(
            TelevisionRepository televisionRepository,
            RemoteControllerRepository remoteControllerRepository,
            RemoteControllerService remoteControllerService
    ) {
        this.televisionRepository = televisionRepository;
        this.remoteControllerRepository = remoteControllerRepository;
        this.remoteControllerService = remoteControllerService;
    }


    // Crud Methods
    public List<TelevisionDto> getAllTelevisions() {
        List<Television> televisions = televisionRepository.findAll();
        List<TelevisionDto> televisionDtos = new ArrayList<>();

        for (Television television : televisions) {
            TelevisionDto dto = transferToDto(television);
            televisionDtos.add(dto);
        }

        if (televisionDtos.isEmpty()) {
            throw new RecordNotFoundException("No televisions found");
        } else {
            return televisionDtos;
        }
    }

    public List<TelevisionDto> getAllTelevisionsByBrand(String brand) {
        List<Television> televisions = televisionRepository.findTelevisionByBrand(brand);
        List<TelevisionDto> televisionDtos = new ArrayList<>();

        for (Television television : televisions) {
            TelevisionDto dto = transferToDto(television);
            televisionDtos.add(dto);
        }

        if (televisionDtos.isEmpty()) {
            throw new RecordNotFoundException("No televisions found with brand: " + brand);
        } else {
            return televisionDtos;
        }
    }

    public List<TelevisionDto> getAllTelevisionsByName(String name) {
        List<Television> televisions = televisionRepository.findTelevisionByName(name);
        List<TelevisionDto> televisionDtos = new ArrayList<>();

        for (Television television : televisions) {
            TelevisionDto dto = transferToDto(television);
            televisionDtos.add(dto);
        }

        if (televisionDtos.isEmpty()) {
            throw new RecordNotFoundException("No televisions found with name: " + name);
        } else {
            return televisionDtos;
        }
    }

    public List<TelevisionDto> getAllTelevisionsByBrandANdName(String brand, String name) {
        List<Television> televisions = televisionRepository.findTelevisionsByBrandAndName(brand, name);
        List<TelevisionDto> televisionDtos = new ArrayList<>();

        for (Television television : televisions) {
            TelevisionDto dto = transferToDto(television);
            televisionDtos.add(dto);
        }

        if (televisionDtos.isEmpty()) {
            throw new RecordNotFoundException("No televisions found with brand: " + brand + " and name: " + name);
        } else {
            return televisionDtos;
        }
    }

    public TelevisionDto getTelevisionById(Long id) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isPresent()) {
            Television foundTelevision = television.get();
            return transferToDto(foundTelevision);
        } else {
            throw new RecordNotFoundException("No television found with id: " + id);
        }
    }

    public void deleteTelevision(Long id) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isPresent()) {
            televisionRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Television with id: " + id + " is not found.");
        }
    }

    public TelevisionDto createTelevision(TelevisionInputDto input) {
        Television television = transferToTelevision(input);

        televisionRepository.save(television);

        return transferToDto(television);
    }

    public TelevisionDto updateTelevision(Long id, TelevisionInputDto newTelevision) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isPresent()) {
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

            return transferToDto(updatedTelevision);
        } else {
            throw new RecordNotFoundException("Cant find television with id: " + id);
        }
    }

    public TelevisionDto patchTelevision(Long id, TelevisionInputDto newTelevision) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isPresent()) {
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

            Television patchedTelevision = televisionRepository.save(foundTelevision);
            return transferToDto(patchedTelevision);
        } else {
            throw new RecordNotFoundException("Cant find television with id: " + id);
        }
    }


    // Transfer Methods
    public Television transferToTelevision(TelevisionInputDto dto) {
        Television television = new Television();

        television.setAmbiLight(dto.getAmbiLight());
        television.setAvailableSize(dto.getAvailableSize());
        television.setBluetooth(dto.getBluetooth());
        television.setBrand(dto.getBrand());
        television.setHdr(dto.getHdr());
        television.setName(dto.getName());
        television.setOriginalStock(dto.getOriginalStock());
        television.setPrice(dto.getPrice());
        television.setRefreshRate(dto.getRefreshRate());
        television.setScreenQuality(dto.getScreenQuality());
        television.setScreenType(dto.getScreenType());
        television.setSmartTv(dto.getSmartTv());
        television.setSold(dto.getSold());
        television.setType(dto.getType());
        television.setVoiceControl(dto.getVoiceControl());
        television.setWifi(dto.getWifi());

        return television;
    }

    public TelevisionDto transferToDto(Television television) {
        TelevisionDto dto = new TelevisionDto();

        dto.setId(television.getId());
        dto.setAmbiLight(television.getAmbiLight());
        dto.setAvailableSize(television.getAvailableSize());
        dto.setBluetooth(television.getBluetooth());
        dto.setBrand(television.getBrand());
        dto.setHdr(television.getHdr());
        dto.setName(television.getName());
        dto.setOriginalStock(television.getOriginalStock());
        dto.setPrice(television.getPrice());
        dto.setRefreshRate(television.getRefreshRate());
        dto.setScreenQuality(television.getScreenQuality());
        dto.setScreenType(television.getScreenType());
        dto.setSmartTv(television.getSmartTv());
        dto.setSold(television.getSold());
        dto.setType(television.getType());
        dto.setVoiceControl(television.getVoiceControl());
        dto.setWifi(television.getWifi());

        if (television.getRemoteController() != null) {
            dto.setRemoteController(remoteControllerService.transferToDto(television.getRemoteController()));
        }

        return dto;
    }


    // Relations Methods
    public TelevisionDto assignRemoteControllerToTelevision(Long id, Long remoteControllerId) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        Optional<RemoteController> optionalRemoteController = remoteControllerRepository.findById(remoteControllerId);
        TelevisionDto dto;

        if (optionalTelevision.isPresent() && optionalRemoteController.isPresent()) {
            Television television = optionalTelevision.get();
            RemoteController remoteController = optionalRemoteController.get();

            television.setRemoteController(remoteController);
            televisionRepository.save(television);

            dto = transferToDto(television);
            return dto;


        } else {
            if (optionalTelevision.isEmpty() && optionalRemoteController.isEmpty()) {
                throw new RecordNotFoundException("Television with id: " + id + " and remote controller with id: " + remoteControllerId + " are not found");
            } else if (optionalTelevision.isEmpty()) {
                throw new RecordNotFoundException("Television with id: " + id + " is not found");
            } else {
                throw new RecordNotFoundException("Remote controller with id: " + remoteControllerId + " is not found");
            }
        }
    }


    // Bonus
    public List<TelevisionSalesDto> getSalesInfo() {
        List<Television> televisions = televisionRepository.findAll();
        List<TelevisionSalesDto> televisionSalesDtos = new ArrayList<>();

        for (Television television : televisions) {
            TelevisionSalesDto salesDto = transferToSalesDto(television);
            televisionSalesDtos.add(salesDto);
        }

        if (televisionSalesDtos.isEmpty()) {
            throw new RecordNotFoundException("No sales information found");
        } else {
            return televisionSalesDtos;
        }
    }

    public TelevisionSalesDto transferToSalesDto(Television television) {
        TelevisionSalesDto salesDto = new TelevisionSalesDto();

        salesDto.setId(television.getId());
        salesDto.setPrice(television.getPrice());
        salesDto.setOriginalStock(television.getOriginalStock());
        salesDto.setSold(television.getSold());
        salesDto.setEarnings(television.getPrice() * television.getSold());

        return salesDto;
    }
}
