package org.example.dziennikbackend.services;

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

    public List<Major> getAllMajors() {
        return majorRepository.findAll();
    }

    public Major getMajorById(Long id) {
        Optional<Major> major = majorRepository.findById(id);
        return major.orElse(null);
    }

    public Major createMajor(Major major) {
        if (majorRepository.existsById(major.getId()) || major.getShortName() == null) {
            return null;
        }
        return majorRepository.save(major);
    }

    public Major updateMajor(Long id, Major major) {
        Optional<Major> majorOptional = majorRepository.findById(id);
        if (majorOptional.isPresent()) {
            if(major.getName().isEmpty() || !major.getName().equals(majorOptional.get().getName())) {
                major.setName(majorOptional.get().getName());
            } else if (major.getShortName() == null || !major.getShortName().equals(majorOptional.get().getShortName())) {
                major.setShortName(majorOptional.get().getShortName());
            }
            major.setId(id);
            return majorRepository.save(major);
        } else {
            return null;
        }
    }

    public void deleteMajor(Long id) {
        majorRepository.deleteById(id);
    }
}
