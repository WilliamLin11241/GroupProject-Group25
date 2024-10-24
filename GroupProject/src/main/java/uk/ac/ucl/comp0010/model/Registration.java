package uk.ac.ucl.comp0010.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "registration")
public class Registration {

  @Id
  @ManyToOne
  private Student student;

  @Id
  @ManyToOne
  private Module module;

  public Registration() {}

  public Registration(Student student, Module module) {
    this.student = student;
    this.module = module;
  }

  // Getters and Setters
  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Module getModule() {
    return module;
  }

  public void setModule(Module module) {
    this.module = module;
  }
}
