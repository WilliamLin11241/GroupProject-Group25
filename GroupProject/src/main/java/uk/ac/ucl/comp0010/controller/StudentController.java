package uk.ac.ucl.comp0010.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.ucl.comp0010.model.Student;
import uk.ac.ucl.comp0010.repository.StudentRepository;

/**
 * A controller that manages student objects.
 */
@RestController
@RequestMapping("/students")
public class StudentController {

  private final StudentRepository studentRepository;

  public StudentController(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  /**
   * Handles creating a new student.
   *
   * @param params student details
   * @return saved student object
   */
  @PostMapping("/addStudent")
  public ResponseEntity<Student> addStudent(@RequestBody Map<String, String> params) {
    Student student = new Student();
    student.setFirstName(params.get("firstName"));
    student.setLastName(params.get("lastName"));
    student.setUsername(params.get("username"));
    student.setEmail(params.get("email"));
    student = studentRepository.save(student);

    return ResponseEntity.ok(student);
  }

  /**
   * Retrieves all students.
   *
   * @return list of all student objects
   */
  @GetMapping("/all")
  public ResponseEntity<List<Student>> getAllStudents() {
    List<Student> students = studentRepository.findAll();
    return ResponseEntity.ok(students);
  }

  /**
   * Retrieves a specific student by ID.
   *
   * @param studentId the ID of the student to retrieve
   * @return the student object
   */
  @GetMapping("/{studentId}")
  public ResponseEntity<Student> getStudentById(@PathVariable Integer studentId) {
    Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found"));
    return ResponseEntity.ok(student);
  }

  /**
   * Updates an existing student.
   *
   * @param studentId the ID of the student to update
   * @param params new student details
   * @return updated student object
   */
  @PutMapping("/update/{studentId}")
  public ResponseEntity<Student> updateStudent(@PathVariable Integer studentId,
                                               @RequestBody Map<String, String> params) {
    Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found"));

    student.setFirstName(params.get("firstName"));
    student.setLastName(params.get("lastName"));
    student.setUsername(params.get("username"));
    student.setEmail(params.get("email"));
    student = studentRepository.save(student);

    return ResponseEntity.ok(student);
  }

  /**
   * Deletes a student by ID.
   *
   * @param studentId the ID of the student to delete
   * @return response message
   */
  @DeleteMapping("/delete/{studentId}")
  public ResponseEntity<String> deleteStudent(@PathVariable Integer studentId) {
    Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found"));

    studentRepository.delete(student);
    return ResponseEntity.ok("Student deleted successfully");
  }
}