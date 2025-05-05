package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.DTOs.SemesterDTO;
import org.example.dziennikbackend.models.Entities.Semester;
import org.example.dziennikbackend.repositories.MajorRepository;
import org.example.dziennikbackend.repositories.SemesterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemesterService {
    private final SemesterRepository semesterRepository;
    private final MajorRepository majorRepository;
    public SemesterService(SemesterRepository semesterRepository, MajorRepository majorRepository) {
        this.semesterRepository = semesterRepository;
        this.majorRepository = majorRepository;
    }

    @Transactional
    public Semester createSemester(SemesterDTO semester) {
        if (semesterRepository.existsSemestersByCode(semester.getCode())) {
            return null;
        }
        Semester newSemester = new Semester();
        newSemester.setCode(semester.getCode());
        newSemester.setStart(semester.getStartDate());
        newSemester.setEnd(semester.getEndDate());
        newSemester.setMajor(majorRepository.getReferenceById(semester.getMajorId()));
        return semesterRepository.save(newSemester);
    }

    @Transactional
    public List<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }

    @Transactional
    public Semester getSemesterByCode(String code) {
        Optional<Semester> semester = semesterRepository.findSemestersByCode(code);
        if (semester.isEmpty()){
            return null;
        }
        return semester.get();
    }

    @Transactional
    public void deleteSemesterByCode(String code) {
        Optional<Semester> semester = semesterRepository.findSemestersByCode(code);
        if (semester.isEmpty()){
            return;
        }
        semesterRepository.delete(semester.get());
    }

    @Transactional
    public Semester updateSemester(SemesterDTO semester) {
        Optional<Semester> oldSemester = semesterRepository.findSemestersByCode(semester.getCode());
        if (oldSemester.isPresent()){
            Semester semesterToUpdate = oldSemester.get();
            if(semester.getCode() != null){
                semesterToUpdate.setCode(semester.getCode());
            }
            if (semester.getStartDate() != null){
                semesterToUpdate.setStart(semester.getStartDate());
            }
            if (semester.getEndDate() != null){
                semesterToUpdate.setEnd(semester.getEndDate());
            }
            if (semester.getMajorId() != null){
                semesterToUpdate.setMajor(majorRepository.getReferenceById(semester.getMajorId()));
            }
            return semesterRepository.save(semesterToUpdate);
        }
        else {
            return null;
        }
    }
}
