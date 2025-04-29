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

    @Transactional
    public List<CoursePart> findAll() {
        List<CoursePart> courseParts = coursePartRepository.findAll();
        if (courseParts.isEmpty()) {
            return null;
        }
        return courseParts;
    }

    @Transactional
    public CoursePart findById(Long id) {
        Optional<CoursePart> coursePart = coursePartRepository.findById(id);
        if (coursePart.isPresent()) {
            return coursePart.get();
        } else {
            return null;
        }
    }

    @Transactional
    public CoursePart create(CoursePartDTO coursePart) {
        CoursePart newCoursePart = new CoursePart();
        newCoursePart.setEdition(courseEditionRepository.getReferenceById(coursePart.getCourseEditionId()));
        newCoursePart.setDescription(coursePart.getDescription());
        newCoursePart.setEcts(coursePart.getEts());
        newCoursePart.setType(coursePart.getType());
        return coursePartRepository.save(newCoursePart);
    }

    @Transactional
    public CoursePart updateCoursePart(Long id, CoursePartDTO coursePartDTO) {
        Optional<CoursePart> coursePart = coursePartRepository.findById(id);
        if (coursePart.isPresent()) {
            CoursePart toUpdate = coursePart.get();
            if(coursePartDTO.getCourseEditionId() != null) {
                toUpdate.setEdition(courseEditionRepository.getReferenceById(coursePartDTO.getCourseEditionId()));
            } else if (coursePartDTO.getDescription() != null) {
                toUpdate.setDescription(coursePartDTO.getDescription());
            } else if (coursePartDTO.getEts() != null) {
                toUpdate.setEcts(coursePartDTO.getEts());
            } else if (coursePartDTO.getType() != null) {
                toUpdate.setType(coursePartDTO.getType());
            }
            return coursePartRepository.save(toUpdate);
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteCoursePart(Long id) {
        coursePartRepository.deleteById(id);
    }
}
