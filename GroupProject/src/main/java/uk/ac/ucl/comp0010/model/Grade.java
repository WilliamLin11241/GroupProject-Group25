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
