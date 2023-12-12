package mesander.com.TechItEasy.services;

import mesander.com.TechItEasy.dtos.input.CIModuleInputDto;
import mesander.com.TechItEasy.dtos.output.CIModuleDto;
import mesander.com.TechItEasy.models.CIModule;

public class CIModuleService {
    // Transfer Methods
    private Long id;

    private String name;
    private String type;
    private Double price;

    public CIModule transferToCIModule(CIModuleInputDto dto) {
        CIModule ciModule = new CIModule();


    }
    public CIModuleDto transferToDto(CIModule ciModule) {

    }
}
