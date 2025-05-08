package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.DTOs.CourseDTO;
import org.example.dziennikbackend.models.Entities.Course;
import org.example.dziennikbackend.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    private CourseDTO changeObjectToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setCode(course.getCode());
        courseDTO.setEcts(course.getEcts());
        return courseDTO;
    }
    
    private Course changeDTOToObject(CourseDTO courseDTO) {
        Course course = new Course();
        course.setId(courseDTO.getId());
        course.setName(courseDTO.getName());
        course.setCode(courseDTO.getCode());
        course.setEcts(courseDTO.getEcts());
        return course;
    }
    
    @Transactional
    public List<Course> findAll() {
        return courseRepository.findAll();
    }
    
    @Transactional
    public CourseDTO findById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            return changeObjectToDTO(course.get());
        } else {
            return null;
        }
    }

    @Transactional
    public CourseDTO createCourse(CourseDTO course){
        if(course.getName().isBlank() || course.getCode().isBlank() || course.getEcts() == null){
            return null;
        }
        return changeObjectToDTO(courseRepository.save(changeDTOToObject(course)));
    }

    @Transactional
    public CourseDTO updateCourse(Long id, CourseDTO course){
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isPresent()) {
            if(!course.getName().isBlank()){
                courseOptional.get().setName(course.getName());
            }
            if (!course.getCode().isBlank()){
                courseOptional.get().setCode(course.getCode());
            }
            if (course.getEcts() != null){
                courseOptional.get().setEcts(course.getEcts());
            }
            return changeObjectToDTO(courseRepository.save(courseOptional.get()));
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteCourseById(Long id){
        courseRepository.deleteById(id);
    }
}
