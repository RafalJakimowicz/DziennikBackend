package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.DTOs.MajorDTO;
import org.example.dziennikbackend.models.Entities.Major;
import org.example.dziennikbackend.repositories.MajorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MajorService {
    private final MajorRepository majorRepository;
    public MajorService(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    private MajorDTO changeObjectToDTO(Major major) {
        MajorDTO majorDTO = new MajorDTO();
        majorDTO.setId(major.getId());
        majorDTO.setName(major.getName());
        majorDTO.setShortName(major.getShortName());
        return majorDTO;
    }

    private Major changeDTOToObject(MajorDTO majorDTO) {
        Major major = new Major();
        major.setId(majorDTO.getId());
        major.setName(majorDTO.getName());
        major.setShortName(majorDTO.getShortName());
        return major;
    }

    @Transactional
    public List<MajorDTO> getAllMajors() {
        List<Major> majors = majorRepository.findAll();
        List<MajorDTO> majorDTOS = new ArrayList<>();
        for (Major major : majors) {
            majorDTOS.add(changeObjectToDTO(major));
        }
        return majorDTOS;
    }

    @Transactional
    public MajorDTO getMajorById(Long id) {
        Optional<Major> major = majorRepository.findById(id);
        if (major.isPresent()) {
            return changeObjectToDTO(major.get());
        } else {
            return null;
        }
    }

    @Transactional
    public MajorDTO createMajor(MajorDTO major) {
        if (majorRepository.existsByShortName(major.getShortName()) || major.getShortName() == null) {
            return null;
        }
        return changeObjectToDTO(majorRepository.save(changeDTOToObject(major)));
    }

    @Transactional
    public MajorDTO updateMajor(Long id, MajorDTO major) {
        Optional<Major> majorOptional = majorRepository.findById(id);
        if (majorOptional.isPresent()) {
            if(major.getName().isEmpty() || !major.getName().equals(majorOptional.get().getName())) {
                major.setName(majorOptional.get().getName());
            } if (major.getShortName() == null || !major.getShortName().equals(majorOptional.get().getShortName())) {
                major.setShortName(majorOptional.get().getShortName());
            }
            major.setId(majorOptional.get().getId());
            return changeObjectToDTO(majorRepository.save(changeDTOToObject(major)));
        } else {
            return null;
        }
    }

    public void deleteMajor(Long id) {
        majorRepository.deleteById(id);
    }
}
