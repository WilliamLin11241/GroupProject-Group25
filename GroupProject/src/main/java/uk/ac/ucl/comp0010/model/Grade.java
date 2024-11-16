package uk.ac.ucl.comp0010.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "grade")
public class Grade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int score;

  @ManyToOne
  private Student student;

  @ManyToOne
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
