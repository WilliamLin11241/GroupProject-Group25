package uk.ac.ucl.comp0010.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import uk.ac.ucl.comp0010.exception.NoGradeAvailableException;
import uk.ac.ucl.comp0010.exception.NoRegistrationException;

@Entity
@Table(name = "student")
public class Student {

  @Id
  private int id;
  private String firstName;
  private String lastName;
  private String username;
  private String email;

  // Additional fields for grades and registered modules
  private List<Grade> grades = new ArrayList<>();
  private List<Module> registeredModules = new ArrayList<>();

  public Student() {}

  public Student(int id, String firstName, String lastName, String username, String email) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
  }

  // Compute average grade
  public float computeAverage() {
    if (grades.isEmpty()) {
      throw new NoGradeAvailableException("No grades available for student");
    }
    float total = 0;
    for (Grade grade : grades) {
      total += grade.getScore();
    }
    return total / grades.size();
  }

  // Add grade to the student's list of grades
  public void addGrade(Grade grade) {
    grades.add(grade);
  }

  // Get grade for a specific module
  public Grade getGrade(Module module) {
    return grades.stream().filter(g -> g.getModule().equals(module)).findFirst()
        .orElseThrow(() -> new NoGradeAvailableException("No grade available for module"));
  }

  // Register the student for a module
  public void registerModule(Module module) {
    if (!registeredModules.contains(module)) {
      registeredModules.add(module);
    } else {
      throw new NoRegistrationException("Already registered for this module");
    }
  }

  // Getters and Setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
