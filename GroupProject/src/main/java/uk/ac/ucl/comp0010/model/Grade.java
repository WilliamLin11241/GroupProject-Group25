/**
 * Represents a Grade entity in the system.
 * This entity is mapped to the "grade" table in the database.
 * It contains information about the score, the student who received the grade,
 * and the module for which the grade was given.
 * 
 * Annotations:
 * - @Entity: Specifies that the class is an entity and is mapped to a database table.
 * - @Table(name = "grade"): Specifies the name of the database table to be used for mapping.
 * - @Id: Specifies the primary key of an entity.
 * - @GeneratedValue(strategy = GenerationType.IDENTITY): Provides the specification of generation strategies for the values of primary keys.
 * - @ManyToOne: Specifies a many-to-one relationship between entities.
 * - @JoinColumn: Specifies the foreign key column.
 * - @JsonIdentityInfo and @JsonIdentityReference: Used for handling circular references during JSON serialization.
 * 
 * Fields:
 * - id: The unique identifier for the grade.
 * - score: The score received by the student.
 * - student: The student who received the grade.
 * - module: The module for which the grade was given.
 * 
 * Constructors:
 * - Grade(): Default constructor.
 * - Grade(int score, Student student, Module module): Parameterized constructor to initialize the grade with a score, student, and module.
 * 
 * Methods:
 * - getId(): Returns the id of the grade.
 * - setId(int id): Sets the id of the grade.
 * - getScore(): Returns the score of the grade.
 * - setScore(int score): Sets the score of the grade.
 * - getStudent(): Returns the student associated with the grade.
 * - setStudent(Student student): Sets the student associated with the grade.
 * - getModule(): Returns the module associated with the grade.
 * - setModule(Module module): Sets the module associated with the grade.
 * - toString(): Returns a string representation of the grade.
 * - equals(Object o): Checks if this grade is equal to another object.
 * - hashCode(): Returns the hash code value for the grade.
 */
package uk.ac.ucl.comp0010.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    @JoinColumn(name = "student_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "fullName")
    @JsonIdentityReference(alwaysAsId = true)
    private Student student;


    @ManyToOne
    @JoinColumn(name = "module_code", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "code")
    @JsonIdentityReference(alwaysAsId = true)
    private Module module;

    // @Temporal(TemporalType.TIMESTAMP)
    // private Date createdAt = new Date();

    public Grade() {
    }

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

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", score=" + score +
                ", student=" + student +
                ", module=" + module +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grade grade = (Grade) o;

        if (id != grade.id) return false;
        if (score != grade.score) return false;
        if (!student.equals(grade.student)) return false;
        return module.equals(grade.module);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + score;
        result = 31 * result + student.hashCode();
        result = 31 * result + module.hashCode();
        return result;
    }

    // public Date getCreatedAt() {
    //     return createdAt;
    // }

    // public void setCreatedAt(Date createdAt) {
    //     this.createdAt = createdAt;
    // }
}
