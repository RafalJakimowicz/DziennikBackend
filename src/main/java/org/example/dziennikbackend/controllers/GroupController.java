package org.example.dziennikbackend.controllers;

import org.example.dziennikbackend.models.DTOs.GradeDTO;
import org.example.dziennikbackend.models.DTOs.GroupDTO;
import org.example.dziennikbackend.models.DTOs.StudentDTO;
import org.example.dziennikbackend.models.DTOs.StudentInGroupDTO;
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
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable Long id) {
        GroupDTO group = groupService.getGroupById(id);
        if (group == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(group);
    }

    @GetMapping("my/{uId}")
    public ResponseEntity<List<GroupDTO>> getMyGroups(@PathVariable Long uId) {
        List<GroupDTO> groups = groupService.getGroupsByUserId(uId);
        if (groups == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(groups);
        }
    }

    @PostMapping
    public ResponseEntity<GroupDTO> createGroup(@RequestBody GroupDTO group) {
        GroupDTO groupCreated = groupService.createGroup(group);
        if (groupCreated == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(groupCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDTO> updateGroup(@PathVariable Long id, @RequestBody GroupDTO group) {
        GroupDTO groupUpdated = groupService.updateGroup(id, group);
        if (groupUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groupUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GroupDTO> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentDTO>> getStudentsByGroup(@PathVariable Long id) {
        List<StudentDTO> students = groupService.getStudentsInGroup(id);
        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @PostMapping("/students")
    public ResponseEntity<StudentInGroupDTO> addStudentToGroup(@RequestBody StudentInGroupDTO studentInGroupDTO) {
        StudentInGroupDTO added = groupService.addStudentToGroup(studentInGroupDTO);
        return ResponseEntity.ok(added);
    }

    @DeleteMapping("/{gId}/students/{sId}")
    public ResponseEntity<Void> deleteStudentFromGroup(@PathVariable Long gId, @PathVariable Long sId) {
        groupService.deleteStudentFromGroup(gId, sId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/grades")
    public ResponseEntity<List<GradeDTO>> getGradesByGroup(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.getGradesInGroup(id));
    }
}
