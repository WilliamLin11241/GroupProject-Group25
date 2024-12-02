package uk.ac.ucl.comp0010.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

/**
 * Represents the registration of a student to a module.
 * This entity uses a composite key defined by {@link RegistrationId}.
 * 
 * <p>Each registration is associated with a {@link Student} and a {@link Module}.
 * The composite key consists of the student ID and the module code.</p>
 * 
 * <p>Annotations:</p>
 * <ul>
 *   <li>{@code @Entity} - Specifies that the class is an entity and is mapped to a database table.</li>
 *   <li>{@code @Table(name = "registration")} - Specifies the name of the database table to be used for mapping.</li>
 *   <li>{@code @EmbeddedId} - Specifies a composite primary key that is an embeddable class.</li>
 *   <li>{@code @ManyToOne} - Specifies a many-to-one relationship with another entity.</li>
 *   <li>{@code @MapsId} - Maps the specified attribute to the primary key of the entity.</li>
 *   <li>{@code @JoinColumn(name = "student_id")} - Specifies the foreign key column for the student relationship.</li>
 *   <li>{@code @JoinColumn(name = "module_code")} - Specifies the foreign key column for the module relationship.</li>
 * </ul>
 * 
 * <p>Constructors:</p>
 * <ul>
 *   <li>{@code Registration()} - Default constructor.</li>
 *   <li>{@code Registration(Student student, Module module)} - Constructs a registration with the specified student and module.</li>
 * </ul>
 * 
 * <p>Getters and Setters:</p>
 * <ul>
 *   <li>{@code getId()} - Returns the composite key of the registration.</li>
 *   <li>{@code setId(RegistrationId id)} - Sets the composite key of the registration.</li>
 *   <li>{@code getStudent()} - Returns the student associated with the registration.</li>
 *   <li>{@code setStudent(Student student)} - Sets the student associated with the registration.</li>
 *   <li>{@code getModule()} - Returns the module associated with the registration.</li>
 *   <li>{@code setModule(Module module)} - Sets the module associated with the registration.</li>
 * </ul>
 */
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
