package uk.ac.ucl.comp0010.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "module")
public class Module {

    @Id
    private String code;

    private String name;

    private boolean mnc; // Mandatory Non-Condonable

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grade> grades = new ArrayList<>();

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

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
    
    @Override
    public String toString() {
        return "Module{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", mnc=" + mnc +
                '}';
    }
    
}
