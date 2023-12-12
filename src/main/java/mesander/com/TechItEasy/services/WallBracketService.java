package mesander.com.TechItEasy.services;

import mesander.com.TechItEasy.dtos.input.WallBracketInputDto;
import mesander.com.TechItEasy.dtos.output.WallBracketDto;
import mesander.com.TechItEasy.exceptions.RecordNotFoundException;
import mesander.com.TechItEasy.models.WallBracket;
import mesander.com.TechItEasy.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WallBracketService {
    private final WallBracketRepository wallBracketRepository;

    public WallBracketService(WallBracketRepository wallBracketRepository) {
        this.wallBracketRepository = wallBracketRepository;
    }


    // CRUD Methods
    public List<WallBracketDto> getAllWallBrackets() {
        List<WallBracket> wallBrackets = wallBracketRepository.findAll();
        List<WallBracketDto> wallBracketDtos = new ArrayList<>();

        for (WallBracket wallBracket : wallBrackets) {
            WallBracketDto dto = transferToDto(wallBracket);
            wallBracketDtos.add(dto);
        }

        if (wallBrackets.isEmpty()) {
            throw new RecordNotFoundException("No wall brackets found");
        } else {
            return wallBracketDtos;
        }
    }

    public List<WallBracketDto> getAllWallBracketsByAdjustable(Boolean adjustable) {
        List<WallBracket> wallBrackets = wallBracketRepository.findWallBracketsByAdjustable(adjustable);
        List<WallBracketDto> wallBracketDtos = new ArrayList<>();

        for (WallBracket wallBracket : wallBrackets) {
            WallBracketDto dto = transferToDto(wallBracket);
            wallBracketDtos.add(dto);
        }

        if (wallBrackets.isEmpty()) {
            if (!adjustable) {
                throw new RecordNotFoundException("No wall brackets found that are not adjustable");
            } else {
                throw new RecordNotFoundException("No wall brackets found that are adjustable");
            }
        } else {
            return wallBracketDtos;
        }
    }

    public WallBracketDto getWallBracketById(Long id) {
        Optional<WallBracket> wallBracket = wallBracketRepository.findById(id);

        if (wallBracket.isPresent()) {
            return transferToDto(wallBracket.get());
        } else {
            throw new RecordNotFoundException("No wall bracket found with id: " + id);
        }
    }

    public void deleteWallBracket(Long id) {
        Optional<WallBracket> wallBracket = wallBracketRepository.findById(id);

        if (wallBracket.isPresent()) {
            wallBracketRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No wall bracket found with id: " + id);
        }
    }

    public WallBracketDto createWallBracket(WallBracketInputDto inputDto) {
        WallBracket wallBracket = transferToWallBracket(inputDto);

        wallBracketRepository.save(wallBracket);

        return transferToDto(wallBracket);
    }

    public WallBracketDto updateWallBracket(Long id, WallBracketInputDto inputDto) {
        Optional<WallBracket> wallBracket = wallBracketRepository.findById(id);

        if (wallBracket.isPresent()) {
            WallBracket foundWallBracket = wallBracket.get();

            foundWallBracket.setSize(inputDto.getSize());
            foundWallBracket.setAdjustable(inputDto.getAdjustable());
            foundWallBracket.setName(inputDto.getName());
            foundWallBracket.setPrice(inputDto.getPrice());

            WallBracket updatedWallBracket = wallBracketRepository.save(foundWallBracket);

            return transferToDto(updatedWallBracket);
        } else {
            throw new RecordNotFoundException("No wall bracket found with id: " + id);
        }
    }

    public WallBracketDto patchWallBracket(Long id, WallBracketInputDto inputDto) {
        Optional<WallBracket> wallBracket = wallBracketRepository.findById(id);

        if (wallBracket.isPresent()) {
            WallBracket foundWallBracket = wallBracket.get();

            if (inputDto.getSize() != null) {
                foundWallBracket.setSize(inputDto.getSize());
            }
            if (inputDto.getAdjustable() != null) {
                foundWallBracket.setAdjustable(inputDto.getAdjustable());
            }
            if (inputDto.getName() != null) {
                foundWallBracket.setName(inputDto.getName());

            }
            if (inputDto.getPrice() != null) {
                foundWallBracket.setPrice(inputDto.getPrice());
            }


            WallBracket patchedWallBracket = wallBracketRepository.save(foundWallBracket);

            return transferToDto(patchedWallBracket);
        } else {
            throw new RecordNotFoundException("No wall bracket found with id: " + id);
        }
    }


    // Transfer Methods
    public WallBracket transferToWallBracket(WallBracketInputDto inputDto) {
        WallBracket wallBracket = new WallBracket();

        wallBracket.setSize(inputDto.getSize());
        wallBracket.setAdjustable(inputDto.getAdjustable());
        wallBracket.setName(inputDto.getName());
        wallBracket.setPrice(inputDto.getPrice());

        return wallBracket;
    }

    public WallBracketDto transferToDto(WallBracket wallBracket) {
        WallBracketDto dto = new WallBracketDto();

        dto.setId(wallBracket.getId());
        dto.setSize(wallBracket.getSize());
        dto.setAdjustable(wallBracket.getAdjustable());
        dto.setName(wallBracket.getName());
        dto.setPrice(wallBracket.getPrice());

        return dto;
    }
}
