package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.DTOs.StudentDTO;
import org.example.dziennikbackend.models.Entities.Student;
import org.example.dziennikbackend.repositories.MajorRepository;
import org.example.dziennikbackend.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final MajorRepository majorRepository;
    public StudentService(StudentRepository studentRepository, MajorRepository majorRepository) {
        this.studentRepository = studentRepository;
        this.majorRepository = majorRepository;
    }

    private StudentDTO changeObjectToDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setSurname(student.getSurname());
        studentDTO.setStudentStatus(student.getStudentStatus());
        studentDTO.setYear(student.getYear());
        studentDTO.setAlbumNumber(student.getAlbumNumber());
        studentDTO.setMajorId(student.getMajor().getId());
        return studentDTO;
    }

    private Student changeDTOToObject(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setName(studentDTO.getName());
        student.setSurname(studentDTO.getSurname());
        student.setStudentStatus(studentDTO.getStudentStatus());
        student.setYear(studentDTO.getYear());
        student.setAlbumNumber(studentDTO.getAlbumNumber());
        student.setMajor(majorRepository.getReferenceById(studentDTO.getMajorId()));
        return student;
    }

    @Transactional
    public StudentDTO createStudent(StudentDTO student) {
        return changeObjectToDTO(studentRepository.save(changeDTOToObject(student)));
    }

    @Transactional
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return null;
        } else {
            return changeObjectToDTO(student);
        }
    }

    @Transactional
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            studentDTOS.add(changeObjectToDTO(student));
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
            if(student.getAlbumNumber() != null) {
                toUpdate.setAlbumNumber(student.getAlbumNumber());
            }
            if(student.getYear() != null) {
                toUpdate.setYear(student.getYear());
            }
            if(student.getStudentStatus() != null) {
                toUpdate.setStudentStatus(student.getStudentStatus());
            }
            if(student.getMajorId() != null) {
                toUpdate.setMajor(majorRepository.getReferenceById(student.getMajorId()));
            }
            return changeObjectToDTO(studentRepository.save(toUpdate));
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
