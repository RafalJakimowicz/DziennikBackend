package org.example.dziennikbackend.services;

import jakarta.transaction.Transactional;
import org.example.dziennikbackend.models.DTOs.GradeDTO;
import org.example.dziennikbackend.models.DTOs.GroupDTO;
import org.example.dziennikbackend.models.DTOs.StudentDTO;
import org.example.dziennikbackend.models.DTOs.StudentInGroupDTO;
import org.example.dziennikbackend.models.Entities.Grade;
import org.example.dziennikbackend.models.Entities.Group;
import org.example.dziennikbackend.models.Entities.Student;
import org.example.dziennikbackend.models.Entities.StudentInGroup;
import org.example.dziennikbackend.repositories.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    private GroupDTO changeObjectToDTO(Group group) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(group.getId());
        groupDTO.setCode(group.getCode());
        groupDTO.setUserId(group.getUser().getId());
        groupDTO.setCoursePartId(group.getCoursePart().getId());
        return groupDTO;
    }

    private Group changeDTOToObject(GroupDTO groupDTO) {
        Group group = new Group();
        group.setId(groupDTO.getId());
        group.setCode(groupDTO.getCode());
        group.setCoursePart(coursePartRepository.getReferenceById(groupDTO.getCoursePartId()));
        group.setUser(appUserRepository.getReferenceById(groupDTO.getUserId()));
        return group;
    }

    @Transactional
    public List<GroupDTO> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        List<GroupDTO> groupDTOS = new ArrayList<>();
        for(Group group : groups){
            groupDTOS.add(changeObjectToDTO(group));
        }
        return groupDTOS;
    }

    @Transactional
    public GroupDTO getGroupById(Long id) {
        Group group = groupRepository.findById(id).orElse(null);
        if(group == null){
            return null;
        } else {
            return changeObjectToDTO(group);
        }
    }

    @Transactional
    public GroupDTO createGroup(GroupDTO group) {
        return changeObjectToDTO(groupRepository.save(changeDTOToObject(group)));
    }

    @Transactional
    public GroupDTO updateGroup(Long id, GroupDTO group) {
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
            return changeObjectToDTO(groupRepository.save(groupToUpdate));
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    private StudentInGroupDTO changeObjectToDTO(StudentInGroup group) {
        StudentInGroupDTO studentInGroupDTO = new StudentInGroupDTO();
        studentInGroupDTO.setId(group.getId());
        studentInGroupDTO.setStudentId(group.getStudent().getId());
        studentInGroupDTO.setStudentId(group.getStudent().getId());
        return studentInGroupDTO;
    }

    @Transactional
    public StudentInGroupDTO addStudentToGroup(StudentInGroupDTO studentInGroupDTO) {
        StudentInGroup studentInGroup = new StudentInGroup();
        studentInGroup.setGroup(groupRepository.getReferenceById(studentInGroupDTO.getGroupId()));
        studentInGroup.setStudent(studentRepository.getReferenceById(studentInGroupDTO.getStudentId()));
        return changeObjectToDTO(studentInGroupRepository.save(studentInGroup));
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

    @Transactional
    public List<StudentDTO> getStudentsInGroup(Long groupId) {
        List<Student> students = studentRepository.findStudentsInGroup(groupId);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for(Student student : students){
            studentDTOS.add(changeObjectToDTO(student));
        }
        return studentDTOS;
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

    private GradeDTO changeObjectToDTO(Grade grade) {
        GradeDTO gradeDTO = new GradeDTO();
        gradeDTO.setId(grade.getId());
        gradeDTO.setComment(grade.getComment());
        gradeDTO.setDate(grade.getDate());
        gradeDTO.setScore(grade.getScore());
        gradeDTO.setStudentId(grade.getStudent().getId());
        gradeDTO.setGroupId(grade.getGroup().getId());
        gradeDTO.setUserId(grade.getUser().getId());
        return gradeDTO;
    }

    @Transactional
    public List<GradeDTO> getGradesInGroup(Long groupId) {
        List<Grade> grades = gradeRepository.getGradesByGroupId(groupId);
        List<GradeDTO> gradeDTOS = new ArrayList<>();
        for(Grade grade : grades){
            gradeDTOS.add(changeObjectToDTO(grade));
        }
        return gradeDTOS;
    }
}
