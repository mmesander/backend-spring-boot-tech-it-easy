package mesander.com.TechItEasy.services;

import mesander.com.TechItEasy.dtos.input.CIModuleInputDto;
import mesander.com.TechItEasy.dtos.output.CIModuleDto;
import mesander.com.TechItEasy.exceptions.RecordNotFoundException;
import mesander.com.TechItEasy.models.CIModule;
import mesander.com.TechItEasy.repositories.CIModuleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<CIModule> ciModules = ciModuleRepository.findCIModuleByName(name);
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
    public CIModule transferToCIModule(CIModuleInputDto dto) {
        CIModule ciModule = new CIModule();

        ciModule.setName(dto.getName());
        ciModule.setType(dto.getType());
        ciModule.setPrice(dto.getPrice());

        return ciModule;
    }

    public CIModuleDto transferToDto(CIModule ciModule) {
        CIModuleDto dto = new CIModuleDto();

        dto.setId(ciModule.getId());
        dto.setName(ciModule.getName());
        dto.setType(ciModule.getType());
        dto.setPrice(ciModule.getPrice());

        return dto;
    }
}
