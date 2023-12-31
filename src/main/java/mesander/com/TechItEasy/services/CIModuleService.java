package mesander.com.TechItEasy.services;

import mesander.com.TechItEasy.dtos.input.CIModuleInputDto;
import mesander.com.TechItEasy.dtos.output.CIModuleDto;
import mesander.com.TechItEasy.dtos.output.TelevisionDto;
import mesander.com.TechItEasy.exceptions.RecordNotFoundException;
import mesander.com.TechItEasy.models.CIModule;
import mesander.com.TechItEasy.models.Television;
import mesander.com.TechItEasy.repositories.CIModuleRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CIModuleService {
    private final CIModuleRepository ciModuleRepository;

    public CIModuleService(CIModuleRepository ciModuleRepository) {
        this.ciModuleRepository = ciModuleRepository;
    }


    // CRUD Methods
    public List<CIModuleDto> getAllCIModules() {
        List<CIModule> ciModules = ciModuleRepository.findAll();
        List<CIModuleDto> ciModuleDtos = new ArrayList<>();

        for (CIModule ciModule : ciModules) {
            CIModuleDto dto = transferToDto(ciModule);
            ciModuleDtos.add(dto);
        }

        if (ciModuleDtos.isEmpty()) {
            throw new RecordNotFoundException("No CI-modules found");
        } else {
            return ciModuleDtos;
        }
    }

    public List<CIModuleDto> getAllCIModulesByName(String name) {
        List<CIModule> ciModules = ciModuleRepository.findCIModuleByNameContainsIgnoreCase(name);
        List<CIModuleDto> ciModuleDtos = new ArrayList<>();

        for (CIModule ciModule : ciModules) {
            CIModuleDto dto = transferToDto(ciModule);
            ciModuleDtos.add(dto);
        }

        if (ciModuleDtos.isEmpty()) {
            throw new RecordNotFoundException("No CI-modules found with name: " + name);
        } else {
            return ciModuleDtos;
        }
    }

    public CIModuleDto getCIModuleById(Long id) {
        Optional<CIModule> ciModule = ciModuleRepository.findById(id);

        if (ciModule.isPresent()) {
            return transferToDto(ciModule.get());
        } else {
            throw new RecordNotFoundException("No CI-module found with ID: " + id);
        }
    }

    public void deleteCIModule(Long id) {
        Optional<CIModule> ciModule = ciModuleRepository.findById(id);

        if (ciModule.isPresent()) {
            ciModuleRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No CI-module found with ID: " + id);
        }
    }

    public CIModuleDto createCIModule(CIModuleInputDto inputDto) {
        CIModule ciModule = transferToCIModule(inputDto);

        ciModuleRepository.save(ciModule);

        return transferToDto(ciModule);
    }

    public CIModuleDto updateCIModule(Long id, CIModuleInputDto inputDto) {
        Optional<CIModule> ciModule = ciModuleRepository.findById(id);

        if (ciModule.isPresent()) {
            CIModule foundCIModule = ciModule.get();

            foundCIModule.setName(inputDto.getName());
            foundCIModule.setType(inputDto.getType());
            foundCIModule.setPrice(inputDto.getPrice());

            CIModule updatedCIModule = ciModuleRepository.save(foundCIModule);

            return transferToDto(updatedCIModule);
        } else {
            throw new RecordNotFoundException("No CI-module found with ID: " + id);
        }
    }

    public CIModuleDto patchCIModule(Long id, CIModuleInputDto inputDto) {
        Optional<CIModule> ciModule = ciModuleRepository.findById(id);

        if (ciModule.isPresent()) {
            CIModule foundCIModule = ciModule.get();

            if (inputDto.getName() != null) {
                foundCIModule.setName(inputDto.getName());
            }
            if (inputDto.getType() != null) {
                foundCIModule.setType(inputDto.getType());
            }
            if (inputDto.getPrice() != null) {
                foundCIModule.setPrice(inputDto.getPrice());
            }


            CIModule patchedCIModule = ciModuleRepository.save(foundCIModule);

            return transferToDto(patchedCIModule);
        } else {
            throw new RecordNotFoundException("No CI-module found with ID: " + id);
        }
    }


    // Transfer Methods
    public CIModule transferToCIModule(CIModuleInputDto inputDto) {
        CIModule ciModule = new CIModule();

        ciModule.setName(inputDto.getName());
        ciModule.setType(inputDto.getType());
        ciModule.setPrice(inputDto.getPrice());

        return ciModule;
    }

    public CIModuleDto transferToDto(CIModule ciModule) {
        CIModuleDto dto = new CIModuleDto();

        dto.setId(ciModule.getId());
        dto.setName(ciModule.getName());
        dto.setType(ciModule.getType());
        dto.setPrice(ciModule.getPrice());

        if (ciModule.getTelevisions() != null) {
            Set<Television> televisions = ciModule.getTelevisions();
            Set<TelevisionDto> televisionDtos = new HashSet<>();

            for (Television television : televisions) {
                TelevisionDto tdto = new TelevisionDto();

                tdto.setId(television.getId());
                tdto.setAmbiLight(television.getAmbiLight());
                tdto.setAvailableSize(television.getAvailableSize());
                tdto.setBluetooth(television.getBluetooth());
                tdto.setBrand(television.getBrand());
                tdto.setHdr(television.getHdr());
                tdto.setName(television.getName());
                tdto.setOriginalStock(television.getOriginalStock());
                tdto.setPrice(television.getPrice());
                tdto.setRefreshRate(television.getRefreshRate());
                tdto.setScreenQuality(television.getScreenQuality());
                tdto.setScreenType(television.getScreenType());
                tdto.setSmartTv(television.getSmartTv());
                tdto.setSold(television.getSold());
                tdto.setType(television.getType());
                tdto.setVoiceControl(television.getVoiceControl());
                tdto.setWifi(television.getWifi());

                televisionDtos.add(tdto);
            }

            dto.setTelevision(televisionDtos);
        }

        return dto;
    }
}
