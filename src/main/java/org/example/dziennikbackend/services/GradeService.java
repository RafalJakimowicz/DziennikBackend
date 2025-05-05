package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.DTOs.GradeDTO;
import org.example.dziennikbackend.models.Entities.Grade;
import org.example.dziennikbackend.repositories.AppUserRepository;
import org.example.dziennikbackend.repositories.GradeRepository;
import org.example.dziennikbackend.repositories.GroupRepository;
import org.example.dziennikbackend.repositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class GradeService {
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final AppUserRepository appUserRepository;
    public GradeService(GradeRepository gradeRepository, StudentRepository studentRepository, GroupRepository groupRepository, AppUserRepository appUserRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.appUserRepository = appUserRepository;
    }

    @Transactional
    public Grade createGrade(GradeDTO gradeDTO) {
        Grade grade = new Grade();
        grade.setDate(gradeDTO.getDate());
        grade.setStudent(studentRepository.getReferenceById(gradeDTO.getStudentId()));
        grade.setGroup(groupRepository.getReferenceById(gradeDTO.getGroupId()));
        grade.setComment(gradeDTO.getComment());
        grade.setUser(appUserRepository.getReferenceById(gradeDTO.getUserId()));
        grade.setScore(gradeDTO.getScore());
        return gradeRepository.save(grade);
    }

    @Transactional
    public Grade updateGrade(Long id, GradeDTO gradeDTO) {
        Grade toUpdate = gradeRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            if (gradeDTO.getComment() != null) {
                toUpdate.setComment(gradeDTO.getComment());
            }
            if (gradeDTO.getScore() != null) {
                toUpdate.setScore(gradeDTO.getScore());
            }
            if (gradeDTO.getUserId() != null) {
                toUpdate.setUser(appUserRepository.getReferenceById(gradeDTO.getUserId()));
            }
            if (gradeDTO.getGroupId() != null) {
                toUpdate.setGroup(groupRepository.getReferenceById(gradeDTO.getGroupId()));
            }
            if (gradeDTO.getStudentId() != null) {
                toUpdate.setStudent(studentRepository.getReferenceById(gradeDTO.getStudentId()));
            }
            if (gradeDTO.getDate() != null) {
                toUpdate.setDate(gradeDTO.getDate());
            }
            return gradeRepository.save(toUpdate);
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }
}
