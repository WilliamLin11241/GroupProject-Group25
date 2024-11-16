package uk.ac.ucl.comp0010.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "module")
public class Module {

  @Id
  private String code; // Module code (Primary Key)
  private String name; // Name of the module
  private boolean mnc; // Mandatory Non-Condonable flag

  @ManyToOne
  private Student registeredStudent; // Reference to the student who registered for this module

  public Module() {}

  public Module(String code, String name, boolean mnc) {
    this.code = code;
    this.name = name;
    this.mnc = mnc;
  }

  // Getters and Setters
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isMnc() {
    return mnc;
  }

  public void setMnc(boolean mnc) {
    this.mnc = mnc;
  }

  public Student getRegisteredStudent() {
    return registeredStudent;
  }

  public void setRegisteredStudent(Student registeredStudent) {
    this.registeredStudent = registeredStudent;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;

    Module module = (Module) obj;
    return code != null ? code.equals(module.code) : module.code == null;
  }

  @Override
  public int hashCode() {
    return code != null ? code.hashCode() : 0;
  }
}
