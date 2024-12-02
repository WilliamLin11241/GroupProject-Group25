package uk.ac.ucl.comp0010.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.ucl.comp0010.model.Module;
import uk.ac.ucl.comp0010.model.Registration;
import uk.ac.ucl.comp0010.model.RegistrationId;
import uk.ac.ucl.comp0010.model.Student;
import uk.ac.ucl.comp0010.repository.ModuleRepository;
import uk.ac.ucl.comp0010.repository.RegistrationRepository;
import uk.ac.ucl.comp0010.repository.StudentRepository;


import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final StudentRepository studentRepository;
    private final ModuleRepository moduleRepository;

    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository, StudentRepository studentRepository, ModuleRepository moduleRepository) {
        this.registrationRepository = registrationRepository;
        this.studentRepository = studentRepository;
        this.moduleRepository = moduleRepository;
    }

    // Register a student to a module
    public void registerStudentToModule(Long studentId, String moduleCode) throws Exception {
        try {
            Student student = studentRepository.findById(studentId.intValue())
                    .orElseThrow(() -> new Exception("Student not found"));

            Module module = moduleRepository.findById(moduleCode)
                    .orElseThrow(() -> new Exception("Module not found"));

            RegistrationId registrationId = new RegistrationId(student.getId(), module.getCode());
            Registration registration = new Registration();
            registration.setId(registrationId);
            registration.setStudent(student);
            registration.setModule(module);

            registrationRepository.save(registration);
        } catch (Exception e) {
            throw new Exception("Error occurred during registration: " + e.getMessage());
        }
    }

    // Get all modules registered by a student
    public List<Module> getModulesByStudent(Long studentId) {
        List<Registration> registrations = registrationRepository.findByStudent_Id(studentId.intValue());
        List<Module> modules = new ArrayList<>();
        for (Registration registration : registrations) {
            modules.add(registration.getModule());
        }
        return modules;
    }

    // Get all students registered to a specific module
    public List<Student> getStudentsByModule(String moduleCode) {
        List<Registration> registrations = registrationRepository.findByModule_Code(moduleCode);
        List<Student> students = new ArrayList<>();
        for (Registration registration : registrations) {
            students.add(registration.getStudent());
        }
        return students;
    }

    // Unregister a student from a module
    public void unregisterStudentFromModule(Long studentId, String moduleCode) throws Exception {
        RegistrationId registrationId = new RegistrationId(studentId.intValue(), moduleCode);
        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new Exception("Registration not found for student ID: " + studentId + " and module code: " + moduleCode));
        registrationRepository.delete(registration);
    }
}
