package uk.ac.ucl.comp0010.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.ucl.comp0010.model.Grade;
import uk.ac.ucl.comp0010.model.Module;
import uk.ac.ucl.comp0010.model.Student;
import uk.ac.ucl.comp0010.repository.GradeRepository;
import uk.ac.ucl.comp0010.repository.ModuleRepository;
import uk.ac.ucl.comp0010.repository.StudentRepository;

@RestController
@RequestMapping("/grades")
public class GradeController {

  @Autowired
  private GradeRepository gradeRepository;

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private ModuleRepository moduleRepository;

  @PostMapping("/addGrade")
  public ResponseEntity<Grade> addGrade(@RequestBody Map<String, String> params) {
    int studentId = Integer.parseInt(params.get("student_id"));
    String moduleCode = params.get("module_code");
    int score = Integer.parseInt(params.get("score"));

    Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new IllegalArgumentException("Invalid student ID"));
    Module module = moduleRepository.findById(moduleCode)
        .orElseThrow(() -> new IllegalArgumentException("Invalid module code"));

    Grade grade = new Grade();
    grade.setStudent(student);
    grade.setModule(module);
    grade.setScore(score);

    Grade savedGrade = gradeRepository.save(grade);
    return ResponseEntity.ok(savedGrade);
  }
}
