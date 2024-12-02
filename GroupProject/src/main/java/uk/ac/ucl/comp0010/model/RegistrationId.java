/**
 * Represents a composite primary key for a registration entity.
 * This class is used to embed the composite key in the entity class.
 * It implements Serializable to ensure the key can be serialized.
 * 
 * The composite key consists of:
 * - studentId: The ID of the student.
 * - moduleCode: The code of the module.
 * 
 * This class provides constructors, getters, setters, and overrides
 * the equals() and hashCode() methods to ensure proper comparison
 * and hashing of the composite key.
 * 
 * Annotations:
 * - @Embeddable: Specifies that this class is embeddable in an entity.
 * 
 * Methods:
 * - RegistrationId(): Default constructor.
 * - RegistrationId(Integer studentId, String moduleCode): Parameterized constructor.
 * - getStudentId(): Returns the student ID.
 * - setStudentId(Integer studentId): Sets the student ID.
 * - getModuleCode(): Returns the module code.
 * - setModuleCode(String moduleCode): Sets the module code.
 * - equals(Object o): Compares this object with the specified object for equality.
 * - hashCode(): Returns the hash code value for this object.
 */
package uk.ac.ucl.comp0010.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
public class RegistrationId implements Serializable {

    private Integer studentId;
    private String moduleCode;

    // Constructors
    public RegistrationId() {}

    public RegistrationId(Integer studentId, String moduleCode) {
        this.studentId = studentId;
        this.moduleCode = moduleCode;
    }

    // Getters and Setters

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    // Override equals() and hashCode()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationId that = (RegistrationId) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(moduleCode, that.moduleCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, moduleCode);
    }
}