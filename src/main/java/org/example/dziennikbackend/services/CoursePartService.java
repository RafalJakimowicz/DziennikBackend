package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.DTOs.CoursePartDTO;
import org.example.dziennikbackend.models.Entities.CoursePart;
import org.example.dziennikbackend.repositories.CourseEditionRepository;
import org.example.dziennikbackend.repositories.CoursePartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursePartService {
    private final CoursePartRepository coursePartRepository;
    private final CourseEditionRepository courseEditionRepository;
    public CoursePartService(CoursePartRepository coursePartRepository, CourseEditionRepository courseEditionRepository) {
        this.coursePartRepository = coursePartRepository;
        this.courseEditionRepository = courseEditionRepository;
    }

    private CoursePartDTO changeObjectToDTO(CoursePart coursePart) {
        CoursePartDTO coursePartDTO = new CoursePartDTO();
        coursePartDTO.setId(coursePart.getId());
        coursePartDTO.setDescription(coursePart.getDescription());
        coursePartDTO.setType(coursePart.getType());
        coursePartDTO.setEcts(coursePart.getEcts());
        coursePartDTO.setCourseEditionId(coursePart.getEdition().getId());
        return coursePartDTO;
    }

    private CoursePart changeDTOToObject(CoursePartDTO coursePartDTO) {
        CoursePart coursePart = new CoursePart();
        coursePart.setId(coursePartDTO.getId());
        coursePart.setDescription(coursePartDTO.getDescription());
        coursePart.setType(coursePartDTO.getType());
        coursePart.setEcts(coursePartDTO.getEcts());
        coursePart.setEdition(courseEditionRepository.getReferenceById(coursePartDTO.getCourseEditionId()));
        return coursePart;
    }

    @Transactional
    public List<CoursePart> findAll() {
        List<CoursePart> courseParts = coursePartRepository.findAll();
        if (courseParts.isEmpty()) {
            return null;
        }
        return courseParts;
    }

    @Transactional
    public CoursePartDTO findById(Long id) {
        Optional<CoursePart> coursePart = coursePartRepository.findById(id);
        if (coursePart.isPresent()) {
            return changeObjectToDTO(coursePart.get());
        } else {
            return null;
        }
    }

    @Transactional
    public CoursePartDTO create(CoursePartDTO coursePart) {
        CoursePart newCoursePart = coursePartRepository.save(changeDTOToObject(coursePart));
        return changeObjectToDTO(newCoursePart);
    }

    @Transactional
    public CoursePartDTO updateCoursePart(Long id, CoursePartDTO coursePartDTO) {
        Optional<CoursePart> coursePart = coursePartRepository.findById(id);
        if (coursePart.isPresent()) {
            CoursePart toUpdate = coursePart.get();
            if(coursePartDTO.getCourseEditionId() != null) {
                toUpdate.setEdition(courseEditionRepository.getReferenceById(coursePartDTO.getCourseEditionId()));
            } if (coursePartDTO.getDescription() != null) {
                toUpdate.setDescription(coursePartDTO.getDescription());
            } if (coursePartDTO.getEcts() != null) {
                toUpdate.setEcts(coursePartDTO.getEcts());
            } if (coursePartDTO.getType() != null) {
                toUpdate.setType(coursePartDTO.getType());
            }
            return changeObjectToDTO(coursePartRepository.save(toUpdate));
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteCoursePart(Long id) {
        coursePartRepository.deleteById(id);
    }
}
