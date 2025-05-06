package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.DTOs.StudentDTO;
import org.example.dziennikbackend.models.Entities.Major;
import org.example.dziennikbackend.models.Entities.Student;
import org.example.dziennikbackend.models.Enums.StudentStatus;
import org.example.dziennikbackend.repositories.MajorRepository;
import org.example.dziennikbackend.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final MajorRepository majorRepository;
    public StudentService(StudentRepository studentRepository, MajorRepository majorRepository) {
        this.studentRepository = studentRepository;
        this.majorRepository = majorRepository;
    }

    @Transactional
    public Student createStudent(StudentDTO student) {
        Student newStudent = new Student();
        newStudent.setName(student.getName());
        newStudent.setSurname(student.getSurname());
        newStudent.setAlbumNumber(student.getAlbumNumber());
        newStudent.setStudentStatus(student.getStatus());
        newStudent.setMajor(majorRepository.getReferenceById(student.getMajorId()));
        newStudent.setYear(student.getYear());
        return studentRepository.save(newStudent);
    }

    @Transactional
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional
    public Student updateStudent(Long id,StudentDTO student) {
        Student toUpdate = studentRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            if(student.getName() != null) {
                toUpdate.setName(student.getName());
            }
            if(student.getSurname() != null) {
                toUpdate.setSurname(student.getSurname());
            }
            if(student.getAlbumNumber() != null) {
                toUpdate.setAlbumNumber(student.getAlbumNumber());
            }
            if(student.getYear() != null) {
                toUpdate.setYear(student.getYear());
            }
            if(student.getStatus() != null) {
                toUpdate.setStudentStatus(student.getStatus());
            }
            if(student.getMajorId() != null) {
                toUpdate.setMajor(majorRepository.getReferenceById(student.getMajorId()));
            }
            return studentRepository.save(toUpdate);
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
