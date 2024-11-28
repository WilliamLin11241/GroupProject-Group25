package uk.ac.ucl.comp0010.model;

import jakarta.persistence.*;

@Entity
@Table(name = "grade")
public class Grade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int score;

  @ManyToOne
  @JoinColumn(name = "student_id", nullable = false)
  private Student student;

  @ManyToOne
  @JoinColumn(name = "module_code", nullable = false)
  private Module module;

  public Grade() {}

  public Grade(int score, Student student, Module module) {
    this.score = score;
    this.student = student;
    this.module = module;
  }

  // Getters and Setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
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
