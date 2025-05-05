package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.DTOs.CourseEditionDTO;
import org.example.dziennikbackend.models.Entities.CourseEdition;
import org.example.dziennikbackend.repositories.AppUserRepository;
import org.example.dziennikbackend.repositories.CourseEditionRepository;
import org.example.dziennikbackend.repositories.CourseRepository;
import org.example.dziennikbackend.repositories.SemesterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseEditionService {
    private final CourseEditionRepository courseEditionRepository;
    private final CourseRepository courseRepository;
    private final SemesterRepository semesterRepository;
    private final AppUserRepository appUserRepository;
    public CourseEditionService(CourseEditionRepository courseEditionRepository,
                                CourseRepository courseRepository,
                                SemesterRepository semesterRepository,
                                AppUserRepository appUserRepository) {
        this.courseEditionRepository = courseEditionRepository;
        this.courseRepository = courseRepository;
        this.semesterRepository = semesterRepository;
        this.appUserRepository = appUserRepository;
    }

    @Transactional
    public CourseEdition createCourseEdition(CourseEditionDTO courseEdition) {
        CourseEdition courseEditionEntity = new CourseEdition();
        courseEditionEntity.setCourse(courseRepository.getReferenceById(courseEdition.getCourseId()));
        courseEditionEntity.setSemester(semesterRepository.getReferenceById(courseEdition.getSemesterId()));
        courseEditionEntity.setUser(appUserRepository.getReferenceById(courseEdition.getUserId()));
        return courseEditionRepository.save(courseEditionEntity);
    }

    @Transactional
    public CourseEdition getCourseEditionById(Long id) {
        return courseEditionRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<CourseEdition> getAllCourseEditions() {
        return courseEditionRepository.findAll();
    }

    @Transactional
    public CourseEdition updateCourseEdition(Long id, CourseEditionDTO courseEdition) {
        Optional<CourseEdition> oldEdition = courseEditionRepository.findById(id);
        if (oldEdition.isPresent()) {
            CourseEdition courseEditionToUpdate = oldEdition.get();
            if(courseEdition.getCourseId() != null) {
                courseEditionToUpdate.setCourse(courseRepository.getReferenceById(courseEdition.getCourseId()));
            }
            if (courseEdition.getSemesterId() != null) {
                courseEditionToUpdate.setSemester(semesterRepository.getReferenceById(courseEdition.getSemesterId()));
            }
            if (courseEdition.getUserId() != null) {
                courseEditionToUpdate.setUser(appUserRepository.getReferenceById(courseEdition.getUserId()));
            }
            return courseEditionRepository.save(courseEditionToUpdate);
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteCourseEdition(Long id) {
        courseEditionRepository.deleteById(id);
    }
}
