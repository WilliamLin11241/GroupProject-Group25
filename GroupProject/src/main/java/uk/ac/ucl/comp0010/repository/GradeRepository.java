package uk.ac.ucl.comp0010.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import uk.ac.ucl.comp0010.model.Grade;

public interface GradeRepository extends CrudRepository<Grade, Integer> {

    List<Grade> findByStudentId(@Param("studentId") Integer studentId);


    List<Grade> findByModuleCode(@Param("moduleCode") String moduleCode);

    Grade findByStudentIdAndModuleCode(@Param("studentId") Integer studentId, @Param("moduleCode") String moduleCode);


    // Page<Grade> findByStudentId(Integer studentId, Pageable pageable);

    @Query("SELECT AVG(g.score) FROM Grade g WHERE g.module.code = :moduleCode")
    Double findAverageScoreByModuleCode(@Param("moduleCode") String moduleCode);

    @Query("SELECT g FROM Grade g WHERE g.score > :score")
    List<Grade> findGradesAboveScore(@Param("score") int score);
}
