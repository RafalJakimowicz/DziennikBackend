package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.DTOs.GroupDTO;
import org.example.dziennikbackend.models.DTOs.StudentInGroupDTO;
import org.example.dziennikbackend.models.Entities.Grade;
import org.example.dziennikbackend.models.Entities.Group;
import org.example.dziennikbackend.models.Entities.Student;
import org.example.dziennikbackend.models.Entities.StudentInGroup;
import org.example.dziennikbackend.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final StudentInGroupRepository studentInGroupRepository;
    private final CoursePartRepository coursePartRepository;
    private final AppUserRepository appUserRepository;
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;

    public GroupService(GroupRepository groupRepository,
                        StudentInGroupRepository studentInGroupRepository,
                        CoursePartRepository coursePartRepository,
                        AppUserRepository appUserRepository,
                        StudentRepository studentRepository,
                        GradeRepository gradeRepository) {
        this.groupRepository = groupRepository;
        this.studentInGroupRepository = studentInGroupRepository;
        this.coursePartRepository = coursePartRepository;
        this.appUserRepository = appUserRepository;
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

    @Transactional
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Transactional
    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    @Transactional
    public Group createGroup(GroupDTO group) {
        Group newGroup = new Group();
        newGroup.setUser(appUserRepository.getReferenceById(group.getUserId()));
        newGroup.setCode(group.getCode());
        newGroup.setCoursePart(coursePartRepository.getReferenceById(group.getCoursePartId()));
        return groupRepository.save(newGroup);
    }

    @Transactional
    public Group updateGroup(Long id, GroupDTO group) {
        Optional<Group> groupOptional = groupRepository.findById(id);
        if (groupOptional.isPresent()) {
            Group groupToUpdate = groupOptional.get();
            if (group.getCoursePartId() != null) {
                groupToUpdate.setCoursePart(coursePartRepository.getReferenceById(group.getCoursePartId()));
            } if (group.getCode() != null) {
                groupToUpdate.setCode(group.getCode());
            } if (group.getUserId() != null) {
                groupToUpdate.setUser(appUserRepository.getReferenceById(group.getUserId()));
            }
            return groupRepository.save(groupToUpdate);
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    @Transactional
    public StudentInGroup addStudentToGroup(StudentInGroupDTO studentInGroupDTO) {
        StudentInGroup studentInGroup = new StudentInGroup();
        studentInGroup.setGroup(groupRepository.getReferenceById(studentInGroupDTO.getGroupId()));
        studentInGroup.setStudent(studentRepository.getReferenceById(studentInGroupDTO.getStudentId()));
        return studentInGroupRepository.save(studentInGroup);
    }

    @Transactional
    public List<Student> getStudentsInGroup(Long groupId) {
        List<Student> students = studentRepository.findStudentsInGroup(groupId);
        return students;
    }

    @Transactional
    public void deleteStudentFromGroup(Long sId, Long gId) {
        List<StudentInGroup> sig = studentInGroupRepository.findAllByGroupId(gId);
        for (StudentInGroup studentInGroup : sig) {
            if (studentInGroup.getStudent().getId().equals(sId)) {
                studentInGroupRepository.delete(studentInGroup);
            }
        }
    }

    @Transactional
    public List<Grade> getGradesInGroup(Long groupId) {
        List<Grade> grades = gradeRepository.getGradesByGroupId(groupId);
        return grades;
    }
}
