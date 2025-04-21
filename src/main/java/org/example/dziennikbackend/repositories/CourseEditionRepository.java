package org.example.dziennikbackend.repositories;

import org.example.dziennikbackend.models.Entities.CourseEdition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseEditionRepository extends JpaRepository<CourseEdition, Integer> {
}
