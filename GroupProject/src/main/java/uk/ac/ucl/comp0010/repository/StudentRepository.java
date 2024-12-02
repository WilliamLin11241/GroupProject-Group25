package uk.ac.ucl.comp0010.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uk.ac.ucl.comp0010.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Page<Student> findByFirstName(String firstName, Pageable pageable);

    Student findByEmail(String email);

    List<Student> findByLastNameIgnoreCase(String lastName);


    @Query("SELECT s FROM Student s JOIN s.registrations r WHERE r.module.code = :moduleCode")
    List<Student> findStudentsByModuleCode(@Param("moduleCode") String moduleCode);

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT :limit", nativeQuery = true)
    List<Student> findLatestStudents(@Param("limit") int limit);
}
