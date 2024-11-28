package uk.ac.ucl.comp0010.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.ac.ucl.comp0010.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}