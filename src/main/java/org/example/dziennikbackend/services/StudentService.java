package org.example.dziennikbackend.services;

import org.example.dziennikbackend.models.Entities.Student;
import org.example.dziennikbackend.models.Enums.StudentStatus;
import org.example.dziennikbackend.repositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudentStatus(Student student, StudentStatus status) {
        int affected_rows = studentRepository.updateStudentStatus(student.getAlbum_number(), status);
        if (affected_rows == 0) {
            return null;
        }
        return studentRepository.findByAlbum_number(student.getAlbum_number()).get();
    }
}
