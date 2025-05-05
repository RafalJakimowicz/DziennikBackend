package org.example.dziennikbackend.repositories;

import org.example.dziennikbackend.models.Entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query(value = "SELECT g.* FROM grades AS g JOIN groups AS gr ON g.group_id = gr.id WHERE gr.id = :groupId", nativeQuery = true)
    List<Grade> getGradesByGroupId(@Param("groupId") Long groupId);
}
