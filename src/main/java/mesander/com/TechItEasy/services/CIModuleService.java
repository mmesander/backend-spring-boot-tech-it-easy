package mesander.com.TechItEasy.services;

import mesander.com.TechItEasy.dtos.input.CIModuleInputDto;
import mesander.com.TechItEasy.dtos.output.CIModuleDto;
import mesander.com.TechItEasy.models.CIModule;
import mesander.com.TechItEasy.repositories.CIModuleRepository;
import org.springframework.stereotype.Service;

@Service
public class CIModuleService {
    private final CIModuleRepository ciModuleRepository;

    public CIModuleService(CIModuleRepository ciModuleRepository) {
        this.ciModuleRepository = ciModuleRepository;
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
