package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.DTOs.CourseEditionDTO;
import org.example.dziennikbackend.models.Entities.CourseEdition;
import org.example.dziennikbackend.repositories.AppUserRepository;
import org.example.dziennikbackend.repositories.CourseEditionRepository;
import org.example.dziennikbackend.repositories.CourseRepository;
import org.example.dziennikbackend.repositories.SemesterRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    private CourseEditionDTO changeObjectToDTO(CourseEdition courseEdition) {
        CourseEditionDTO courseEditionDTO = new CourseEditionDTO();
        courseEditionDTO.setId(courseEdition.getId());
        courseEditionDTO.setCourseId(courseEdition.getCourse().getId());
        courseEditionDTO.setSemesterId(courseEdition.getSemester().getId());
        courseEditionDTO.setUserId(courseEdition.getUser().getId());
        return courseEditionDTO;
    }

    private CourseEdition changeDTOtoObject(CourseEditionDTO courseEditionDTO) {
        CourseEdition courseEdition = new CourseEdition();
        courseEdition.setId(courseEditionDTO.getId());
        courseEdition.setUser(appUserRepository.getReferenceById(courseEditionDTO.getUserId()));
        courseEdition.setCourse(courseRepository.getReferenceById(courseEditionDTO.getCourseId()));
        courseEdition.setSemester(semesterRepository.getReferenceById(courseEditionDTO.getSemesterId()));
        return courseEdition;
    }

    @Transactional
    public CourseEditionDTO createCourseEdition(CourseEditionDTO courseEdition) {
        return changeObjectToDTO(courseEditionRepository.save(changeDTOtoObject(courseEdition)));
    }

    @Transactional
    public CourseEditionDTO getCourseEditionById(Long id) {
        CourseEdition courseEdition = courseEditionRepository.findById(id).orElse(null);
        if (courseEdition == null) {
            return null;
        }
        else{
            return changeObjectToDTO(courseEdition);
        }
    }

    @Transactional
    public List<CourseEditionDTO> getAllCourseEditions() {
        List<CourseEdition> courseEditions = courseEditionRepository.findAll();
        List<CourseEditionDTO> courseEditionDTOS = new ArrayList<>();
        for(CourseEdition courseEdition : courseEditions){
            courseEditionDTOS.add(changeObjectToDTO(courseEdition));
        }
        return courseEditionDTOS;
    }

    @Transactional
    public CourseEditionDTO updateCourseEdition(Long id, CourseEditionDTO courseEdition) {
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
            return changeObjectToDTO(courseEditionRepository.save(courseEditionToUpdate));
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteCourseEdition(Long id) {
        courseEditionRepository.deleteById(id);
    }
}
