package org.example.dziennikbackend.controllers;

import org.example.dziennikbackend.models.DTOs.GroupDTO;
import org.example.dziennikbackend.models.DTOs.StudentInGroupDTO;
import org.example.dziennikbackend.models.Entities.Grade;
import org.example.dziennikbackend.models.Entities.Group;
import org.example.dziennikbackend.models.Entities.Student;
import org.example.dziennikbackend.models.Entities.StudentInGroup;
import org.example.dziennikbackend.services.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private/groups")
public class GroupController {
    private final GroupService groupService;
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable Long id) {
        Group group = groupService.getGroupById(id);
        if (group == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(group);
    }

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody GroupDTO group) {
        Group groupCreated = groupService.createGroup(group);
        if (groupCreated == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(groupCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable Long id, @RequestBody GroupDTO group) {
        Group groupUpdated = groupService.updateGroup(id, group);
        if (groupUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groupUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Group> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<Student>> getStudentsByGroup(@PathVariable Long id) {
        List<Student> students = groupService.getStudentsInGroup(id);
        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @PostMapping("/students")
    public ResponseEntity<StudentInGroup> addStudentToGroup(@RequestBody StudentInGroupDTO studentInGroupDTO) {
        StudentInGroup added = groupService.addStudentToGroup(studentInGroupDTO);
        return ResponseEntity.ok(added);
    }

    @DeleteMapping("/{gId}/students/{sId}")
    public ResponseEntity<Void> deleteStudentFromGroup(@PathVariable Long gId, @PathVariable Long sId) {
        groupService.deleteStudentFromGroup(gId, sId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/grades")
    public ResponseEntity<List<Grade>> getGradesByGroup(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.getGradesInGroup(id));
    }
}
