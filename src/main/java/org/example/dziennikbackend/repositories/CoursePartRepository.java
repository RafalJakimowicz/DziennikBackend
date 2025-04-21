package org.example.dziennikbackend.repositories;

import org.example.dziennikbackend.models.Entities.CoursePart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursePartRepository extends JpaRepository<CoursePart, Long> {
}
