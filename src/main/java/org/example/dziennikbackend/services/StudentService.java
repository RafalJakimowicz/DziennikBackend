package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.Entities.Major;
import org.example.dziennikbackend.models.Entities.Student;
import org.example.dziennikbackend.models.Enums.StudentStatus;
import org.example.dziennikbackend.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudentStatus(Student student, StudentStatus status) {
        int affected_rows = studentRepository.updateStudentStatus(student.getAlbumNumber(), status);
        if (affected_rows == 0) {
            return null;
        }
        return studentRepository.findByAlbumNumber(student.getAlbumNumber()).get();
    }

    @Transactional
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional
    public List<Student> getAllStudentsByMajor(Major major) {
        return studentRepository.findByMajor(major);
    }

    @Transactional
    public Student updateStudentMajor(Major major, Student student) {
        if (!student.getMajor().equals(major)) {
            major.removeStudent(student);
            major.addStudent(student);
        }
        return studentRepository.save(student);
    }
}
