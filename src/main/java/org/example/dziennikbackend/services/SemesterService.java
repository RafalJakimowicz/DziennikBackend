package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.Entities.Semester;
import org.example.dziennikbackend.repositories.SemesterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemesterService {
    private final SemesterRepository semesterRepository;
    public SemesterService(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    @Transactional
    public Semester createSemester(Semester semester) {
        if (semesterRepository.existsSemestersByCode(semester.getCode())) {
            return null;
        }
        return semesterRepository.save(semester);
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
    public Semester updateSemester(Semester semester) {
        Optional<Semester> oldSemester = semesterRepository.findSemestersByCode(semester.getCode());
        if (oldSemester.isPresent()){
            if(semester.getCode() == null){
                semester.setCode(oldSemester.get().getCode());
            }
            else if (semester.getStart() == null){
                semester.setStart(oldSemester.get().getStart());
            }
            else if (semester.getEnd() == null){
                semester.setEnd(oldSemester.get().getEnd());
            }
            else if (semester.getMajor() == null){
                semester.setMajor(oldSemester.get().getMajor());
            }
            semester.setId(oldSemester.get().getId());
            return semesterRepository.save(semester);
        }
        else {
            return null;
        }
    }
}
