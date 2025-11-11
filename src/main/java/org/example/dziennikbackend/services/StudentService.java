package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.DTOs.StudentDTO;
import org.example.dziennikbackend.models.Entities.Student;
import org.example.dziennikbackend.repositories.StudentRepository;
import org.example.dziennikbackend.utils.DTOMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Wysyła polecenie do bazy danych o stworzenie nowego studenta
     * @param student DTO studenta do utworzenia
     * @return obiekt nowego studenta
     */
    @Transactional
    public StudentDTO createStudent(StudentDTO student) {
        Student newStudent = new Student();
        newStudent = DTOMapper.map(student, Student.class);
        newStudent = studentRepository.save(newStudent);
        return DTOMapper.map(newStudent, StudentDTO.class);
    }

    @Transactional
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return null;
        } else {

        }
    }

    @Transactional
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {

        }
        return studentDTOS;
    }

    @Transactional
    public StudentDTO updateStudent(Long id,StudentDTO student) {
        Student toUpdate = studentRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            if(student.getName() != null) {
                toUpdate.setName(student.getName());
            }
            if(student.getSurname() != null) {
                toUpdate.setSurname(student.getSurname());
            }
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
