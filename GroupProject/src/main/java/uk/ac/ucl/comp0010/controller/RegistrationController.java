package uk.ac.ucl.comp0010.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.ucl.comp0010.model.Module;
import uk.ac.ucl.comp0010.model.Student;
import uk.ac.ucl.comp0010.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



import java.util.List;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    // Register a student to a module
    @PostMapping("/register")
    public ResponseEntity<String> registerStudentToModule(@RequestParam Long studentId, @RequestParam String moduleCode) {
        try {
            registrationService.registerStudentToModule(studentId, moduleCode);
            return ResponseEntity.ok("Student successfully registered to module");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }

    // Get all modules registered by a student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Module>> getModulesByStudent(@PathVariable Long studentId) {
        try {
            List<Module> modules = registrationService.getModulesByStudent(studentId);
            return ResponseEntity.ok(modules);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Get all students registered to a specific module
    @GetMapping("/module/{moduleCode}")
    public ResponseEntity<List<Student>> getStudentsByModule(@PathVariable String moduleCode) {
        try {
            List<Student> students = registrationService.getStudentsByModule(moduleCode);
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Unregister a student from a module
    @DeleteMapping("/unregister")
    public ResponseEntity<String> unregisterStudentFromModule(@RequestParam Long studentId, @RequestParam String moduleCode) {
        try {
            registrationService.unregisterStudentFromModule(studentId, moduleCode);
            return ResponseEntity.ok("Student successfully unregistered from module");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unregistration failed: " + e.getMessage());
        }
    }
}