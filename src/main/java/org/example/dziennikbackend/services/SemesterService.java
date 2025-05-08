package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.DTOs.SemesterDTO;
import org.example.dziennikbackend.models.Entities.Semester;
import org.example.dziennikbackend.repositories.MajorRepository;
import org.example.dziennikbackend.repositories.SemesterRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SemesterService {
    private final SemesterRepository semesterRepository;
    public SemesterService(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    private SemesterDTO changeObjectToDTO(Semester semester) {
        SemesterDTO semesterDTO = new SemesterDTO();
        semesterDTO.setId(semester.getId());
        semesterDTO.setCode(semester.getCode());
        semesterDTO.setEndDate(semester.getEnd_date());
        semesterDTO.setStartDate(semester.getStart_date());
        return semesterDTO;
    }

    private Semester changeDTOToObject(SemesterDTO semesterDTO) {
        Semester semester = new Semester();
        semester.setId(semesterDTO.getId());
        semester.setCode(semesterDTO.getCode());
        semester.setEnd_date(semesterDTO.getEndDate());
        semester.setStart_date(semesterDTO.getStartDate());
        return semester;
    }

    @Transactional
    public SemesterDTO createSemester(SemesterDTO semester) {
        if (semesterRepository.existsSemestersByCode(semester.getCode())) {
            return null;
        }
        return changeObjectToDTO(semesterRepository.save(changeDTOToObject(semester)));
    }

    @Transactional
    public List<SemesterDTO> getAllSemesters() {
        List<Semester> semesters =  semesterRepository.findAll();
        List<SemesterDTO> semesterDTOs = new ArrayList<>();
        for (Semester semester : semesters) {
            semesterDTOs.add(changeObjectToDTO(semester));
        }
        return semesterDTOs;
    }

    @Transactional
    public SemesterDTO getSemesterById(Long id) {
        Optional<Semester> semester = semesterRepository.findById(id);
        if (semester.isEmpty()){
            return null;
        }
        return changeObjectToDTO(semester.get());
    }

    @Transactional
    public void deleteSemesterById(Long id) {
        semesterRepository.deleteById(id);
    }

    @Transactional
    public SemesterDTO updateSemester(Long id, SemesterDTO semester) {
        Optional<Semester> oldSemester = semesterRepository.findById(id);
        if (oldSemester.isPresent()){
            Semester semesterToUpdate = oldSemester.get();
            if(semester.getCode() != null){
                semesterToUpdate.setCode(semester.getCode());
            }
            if (semester.getStartDate() != null){
                semesterToUpdate.setStart_date(semester.getStartDate());
            }
            if (semester.getEndDate() != null){
                semesterToUpdate.setEnd_date(semester.getEndDate());
            }
            return changeObjectToDTO(semesterRepository.save(semesterToUpdate));
        }
        else {
            return null;
        }
    }
}
