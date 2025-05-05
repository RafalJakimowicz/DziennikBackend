package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.Entities.Major;
import org.example.dziennikbackend.repositories.MajorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MajorService {
    private final MajorRepository majorRepository;
    public MajorService(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    @Transactional
    public List<Major> getAllMajors() {
        return majorRepository.findAll();
    }

    @Transactional
    public Major getMajorByShortName(String shortName) {
        Optional<Major> major = majorRepository.findByShortName(shortName);
        return major.orElse(null);
    }

    @Transactional
    public Major createMajor(Major major) {
        if (majorRepository.existsByShortName(major.getShortName()) || major.getShortName() == null) {
            return null;
        }
        return majorRepository.save(major);
    }

    @Transactional
    public Major updateMajor(String shortName, Major major) {
        Optional<Major> majorOptional = majorRepository.findByShortName(shortName);
        if (majorOptional.isPresent()) {
            if(major.getName().isEmpty() || !major.getName().equals(majorOptional.get().getName())) {
                major.setName(majorOptional.get().getName());
            } if (major.getShortName() == null || !major.getShortName().equals(majorOptional.get().getShortName())) {
                major.setShortName(majorOptional.get().getShortName());
            }
            major.setId(majorOptional.get().getId());
            return majorRepository.save(major);
        } else {
            return null;
        }
    }

    public void deleteMajor(String shortName) {
        majorRepository.deleteByShortName(shortName);
    }
}
