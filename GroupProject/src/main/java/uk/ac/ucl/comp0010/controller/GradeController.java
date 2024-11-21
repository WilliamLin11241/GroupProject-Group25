package uk.ac.ucl.comp0010.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.ucl.comp0010.model.Grade;
import uk.ac.ucl.comp0010.model.Module;
import uk.ac.ucl.comp0010.model.Student;
import uk.ac.ucl.comp0010.repository.GradeRepository;
import uk.ac.ucl.comp0010.repository.ModuleRepository;
import uk.ac.ucl.comp0010.repository.StudentRepository;

/**
 * A controller that manages grade objects.
 */
@RestController
@RequestMapping("/grades")
public class GradeController {

  private final GradeRepository gradeRepository;
  private final StudentRepository studentRepository;
  private final ModuleRepository moduleRepository;

  public GradeController(GradeRepository gradeRepository, StudentRepository studentRepository,
      ModuleRepository moduleRepository) {
    this.gradeRepository = gradeRepository;
    this.studentRepository = studentRepository;
    this.moduleRepository = moduleRepository;
  }

  /**
   * Handles creating a new grade for a student.
   *
   * @param params student_id, module_code, and score
   * @return saved grade object
   */
  @PostMapping("/addGrade")
  public ResponseEntity<Grade> addGrade(@RequestBody Map<String, String> params) {
    Student student = studentRepository.findById(Integer.parseInt(params.get("student_id")))
        .orElseThrow(() -> new RuntimeException("Student not found"));
    Module module = moduleRepository.findById(params.get("module_code"))
        .orElseThrow(() -> new RuntimeException("Module not found"));

    Grade grade = new Grade();
    grade.setStudent(student);
    grade.setModule(module);
    grade.setScore(Integer.parseInt(params.get("score")));
    grade = gradeRepository.save(grade);

    return ResponseEntity.ok(grade);
  }

  /**
   * Retrieves all grades.
   *
   * @return list of all grade objects
   */
  @GetMapping("/all")
  public ResponseEntity<List<Grade>> getAllGrades() {
    List<Grade> grades = gradeRepository.findAll();
    return ResponseEntity.ok(grades);
  }

  /**
   * Retrieves a specific grade by ID.
   *
   * @param gradeId the ID of the grade to retrieve
   * @return the grade object
   */
  @GetMapping("/{gradeId}")
  public ResponseEntity<Grade> getGradeById(@PathVariable Long gradeId) {
    Grade grade = gradeRepository.findById(gradeId)
        .orElseThrow(() -> new RuntimeException("Grade not found"));
    return ResponseEntity.ok(grade);
  }

  /**
   * Updates an existing grade.
   *
   * @param gradeId the ID of the grade to update
   * @param params new score value
   * @return updated grade object
   */
  @PutMapping("/update/{gradeId}")
  public ResponseEntity<Grade> updateGrade(@PathVariable Long gradeId,
      @RequestBody Map<String, String> params) {
    Grade grade = gradeRepository.findById(gradeId)
        .orElseThrow(() -> new RuntimeException("Grade not found"));

    grade.setScore(Integer.parseInt(params.get("score")));
    grade = gradeRepository.save(grade);

    return ResponseEntity.ok(grade);
  }

  /**
   * Deletes a grade by ID.
   *
   * @param gradeId the ID of the grade to delete
   * @return response message
   */
  @DeleteMapping("/delete/{gradeId}")
  public ResponseEntity<String> deleteGrade(@PathVariable Long gradeId) {
    Grade grade = gradeRepository.findById(gradeId)
        .orElseThrow(() -> new RuntimeException("Grade not found"));

    gradeRepository.delete(grade);
    return ResponseEntity.ok("Grade deleted successfully");
  }
}
