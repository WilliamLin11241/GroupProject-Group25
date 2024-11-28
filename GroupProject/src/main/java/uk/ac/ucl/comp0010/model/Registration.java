package uk.ac.ucl.comp0010.model;

import jakarta.persistence.*;

@Entity
@Table(name = "registration")
public class Registration {

  @EmbeddedId
  private RegistrationId id;

  @ManyToOne
  @MapsId("studentId")
  @JoinColumn(name = "student_id")
  private Student student;

  @ManyToOne
  @MapsId("moduleCode")
  @JoinColumn(name = "module_code")
  private Module module;

  // 构造函数
  public Registration() {}

  public Registration(Student student, Module module) {
    this.student = student;
    this.module = module;
    this.id = new RegistrationId(student.getId(), module.getCode());
  }

  // Getters and Setters

  public RegistrationId getId() {
    return id;
  }

  public void setId(RegistrationId id) {
    this.id = id;
  }

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
