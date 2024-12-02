/**
 * Represents a student entity with details such as id, first name, last name, and email.
 * This class is annotated as an entity for persistence in a database.
 * It also maintains relationships with grades and registrations.
 * 
 * Annotations:
 * - @Entity: Specifies that the class is an entity.
 * - @Table: Specifies the table name in the database.
 * - @Id: Specifies the primary key of the entity.
 * - @Column: Specifies the column name in the database.
 * - @OneToMany: Specifies a one-to-many relationship with another entity.
 * 
 * Fields:
 * - id: The unique identifier for the student.
 * - firstName: The first name of the student.
 * - lastName: The last name of the student.
 * - email: The email address of the student.
 * - grades: A list of grades associated with the student.
 * - registrations: A list of registrations associated with the student.
 * 
 * Constructors:
 * - Student(): Default constructor.
 * - Student(int id, String firstName, String lastName, String email): Parameterized constructor to initialize the student.
 * 
 * Methods:
 * - getId(): Returns the student's id.
 * - setId(int id): Sets the student's id.
 * - getFirstName(): Returns the student's first name.
 * - setFirstName(String firstName): Sets the student's first name.
 * - getLastName(): Returns the student's last name.
 * - setLastName(String lastName): Sets the student's last name.
 * - getEmail(): Returns the student's email.
 * - setEmail(String email): Sets the student's email.
 * - getGrades(): Returns the list of grades.
 * - setGrades(List<Grade> grades): Sets the list of grades.
 * - getRegistrations(): Returns the list of registrations.
 * - setRegistrations(List<Registration> registrations): Sets the list of registrations.
 * - getFullName(): Returns the full name of the student.
 * - toString(): Returns a string representation of the student.
 * - equals(Object object): Checks if two student objects are equal.
 * - hashCode(): Returns the hash code for the student.
 */
package uk.ac.ucl.comp0010.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonIgnore

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
