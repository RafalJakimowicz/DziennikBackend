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

    private GradeDTO changeObjectToDTO(Grade grade) {
        GradeDTO gradeDTO = new GradeDTO();
        gradeDTO.setId(grade.getId());
        gradeDTO.setComment(grade.getComment());
        gradeDTO.setDate(grade.getDate());
        gradeDTO.setScore(grade.getScore());
        gradeDTO.setStudentId(grade.getStudent().getId());
        gradeDTO.setGroupId(grade.getGroup().getId());
        gradeDTO.setUserId(grade.getUser().getId());
        return gradeDTO;
    }

    private Grade changeDTOToObject(GradeDTO gradeDTO) {
        Grade grade = new Grade();
        grade.setId(gradeDTO.getId());
        grade.setComment(gradeDTO.getComment());
        grade.setDate(gradeDTO.getDate());
        grade.setScore(gradeDTO.getScore());
        grade.setUser(appUserRepository.getReferenceById(gradeDTO.getUserId()));
        grade.setGroup(groupRepository.getReferenceById(gradeDTO.getGroupId()));
        grade.setStudent(studentRepository.getReferenceById(gradeDTO.getStudentId()));
        return grade;
    }

    @Transactional
    public GradeDTO createGrade(GradeDTO gradeDTO) {
        return changeObjectToDTO(gradeRepository.save(changeDTOToObject(gradeDTO)));
    }

    @Transactional
    public GradeDTO updateGrade(Long id, GradeDTO gradeDTO) {
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
            return changeObjectToDTO(gradeRepository.save(toUpdate));
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }
}
