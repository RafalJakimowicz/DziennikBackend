package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
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

    @Transactional
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Transactional
    public Course findByCode(String code) {
        Optional<Course> course = courseRepository.findByCode(code);
        if (course.isPresent()) {
            return course.get();
        } else {
            return null;
        }
    }

    @Transactional
    public Course createCourse(Course course){
        if(course.getName().isBlank() || course.getCode().isBlank() || course.getEts() == null){
            return null;
        }
        return courseRepository.save(course);
    }

    @Transactional
    public Course updateCourse(String code, Course course){
        Optional<Course> courseOptional = courseRepository.findByCode(code);
        if (courseOptional.isPresent()) {
            if(!course.getName().isBlank()){
                courseOptional.get().setName(course.getName());
            }
            else if (!course.getCode().isBlank()){
                courseOptional.get().setCode(course.getCode());
            }
            else if (course.getEts() != null){
                courseOptional.get().setEts(course.getEts());
            }
            return courseRepository.save(courseOptional.get());
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteCourseByCode(String code){
        courseRepository.deleteByCode(code);
    }
}
