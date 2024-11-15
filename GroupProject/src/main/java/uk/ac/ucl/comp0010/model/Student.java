package uk.ac.ucl.comp0010.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String firstName;
  private String lastName;
  private String username;
  private String email;

  @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Grade> grades = new ArrayList<>();

  @OneToMany(mappedBy = "registeredStudent", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Module> registeredModules = new ArrayList<>();

  public Student() {}

  public Student(int id, String firstName, String lastName, String username, String email) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
  }

  // Methods to manage grades and modules
  public void registerModule(Module module) {
    if (!registeredModules.contains(module)) {
      registeredModules.add(module);
      module.setRegisteredStudent(this); // Maintain bidirectional relationship
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

  public List<Module> getRegisteredModules() {
    return registeredModules;
  }

  public void setRegisteredModules(List<Module> registeredModules) {
    this.registeredModules = registeredModules;
  }
}
