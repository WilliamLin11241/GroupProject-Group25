package uk.ac.ucl.comp0010.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.ac.ucl.comp0010.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
