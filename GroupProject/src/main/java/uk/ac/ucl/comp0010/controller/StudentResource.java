// package uk.ac.ucl.comp0010.controller;

// import java.net.URI;
// import java.util.List;
// import java.util.Optional;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
// import uk.ac.ucl.comp0010.exception.StudentNotFoundException;
// import uk.ac.ucl.comp0010.model.Student;
// import uk.ac.ucl.comp0010.repository.StudentRepository;

// @RestController
// public class StudentResource {

//   @Autowired
//   private StudentRepository studentRepository;

//   // Retrieve all students
//   @GetMapping("/students")
//   public List<Student> retrieveAllStudents() {
//     return studentRepository.findAll();
//   }

//   // Retrieve a student by ID
//   @GetMapping("/students/{id}")
//   public Student retrieveStudent(@PathVariable int id) {
//     Optional<Student> student = studentRepository.findById(id);

//     if (student.isEmpty()) {
//       throw new StudentNotFoundException("id-" + id);
//     }

//     return student.get();
//   }

//   // Delete a student by ID
//   @DeleteMapping("/students/{id}")
//   public void deleteStudent(@PathVariable int id) {
//     studentRepository.deleteById(id);
//   }

//   // Create a new student
//   @PostMapping("/students")
//   public ResponseEntity<Object> createStudent(@RequestBody Student student) {
//     Student savedStudent = studentRepository.save(student);

//     URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//         .buildAndExpand(savedStudent.getId()).toUri();

//     return ResponseEntity.created(location).build();
//   }

//   // Update an existing student by ID
//   @PutMapping("/students/{id}")
//   public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable int id) {

//     Optional<Student> studentOptional = studentRepository.findById(id);

//     if (studentOptional.isEmpty()) {
//       return ResponseEntity.notFound().build();
//     }

//     student.setId(id);
//     studentRepository.save(student);

//     return ResponseEntity.noContent().build();
//   }
// }
