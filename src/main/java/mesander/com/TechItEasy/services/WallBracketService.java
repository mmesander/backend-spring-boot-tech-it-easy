package mesander.com.TechItEasy.services;

import mesander.com.TechItEasy.dtos.input.WallBracketInputDto;
import mesander.com.TechItEasy.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

@Service
public class WallBracketService {
    private final WallBracketRepository wallBracketRepository;

    public WallBracketService(WallBracketRepository wallBracketRepository) {
        this.wallBracketRepository = wallBracketRepository;
    }
    private Long id;
    private String size;
    private Boolean adjustable;
    private String name;
    private Double price;

    // Transfer Methods
    public WallBracket transferToWallBracket(WallBracketInputDto inputDto)
}
