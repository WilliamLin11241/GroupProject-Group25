package uk.ac.ucl.comp0010.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    private String email;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grade> grades = new ArrayList<>();


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registration> registrations = new ArrayList<>();

    public Student() {}

    public Student(int Id, String firstName, String lastName, String email) {
        this.id = Id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Grade> getGrades() {
        return grades;
    }
    
    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
    

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString(){
        return "Student{id=" + id + ", firstName='" + firstName + "', lastName='" + lastName + "', email='" + email + "'}";
    }

    @Override
    public boolean equals(Object object) {
        Student student = (Student) object;
        return this.id == student.id && this.firstName.equals(student.firstName) && this.lastName.equals(student.lastName) && this.email.equals(student.email);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
