package org.example.dziennikbackend.repositories;

import org.example.dziennikbackend.models.Entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM groups WHERE user_id = :uId")
    List<Group> findByUserId(@Param("uId") Long userId);
}
