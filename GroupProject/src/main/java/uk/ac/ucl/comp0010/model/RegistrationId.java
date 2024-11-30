package uk.ac.ucl.comp0010.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
public class RegistrationId implements Serializable {

    private Integer studentId;
    private String moduleCode;

    // 构造函数
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

    // 重写 equals() 和 hashCode()

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