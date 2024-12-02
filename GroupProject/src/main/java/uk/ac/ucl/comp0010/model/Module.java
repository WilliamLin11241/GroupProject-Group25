/**
 * Represents a module in the system.
 * 
 * <p>This class is annotated with JPA annotations to map it to a database table named "module".
 * Each module has a unique code, a name, and a boolean indicating if it is mandatory non-condonable (mnc).
 * A module can have multiple grades associated with it, which are managed with a one-to-many relationship.</p>
 * 
 * <p>Attributes:</p>
 * <ul>
 *   <li>{@code code} - The unique code of the module.</li>
 *   <li>{@code name} - The name of the module.</li>
 *   <li>{@code mnc} - Indicates if the module is mandatory non-condonable.</li>
 *   <li>{@code grades} - A list of grades associated with the module.</li>
 * </ul>
 * 
 * <p>Constructors:</p>
 * <ul>
 *   <li>{@code Module()} - Default constructor.</li>
 *   <li>{@code Module(String code, String name, boolean mnc)} - Constructs a module with the specified code, name, and mnc status.</li>
 * </ul>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@code getCode()} - Returns the code of the module.</li>
 *   <li>{@code setCode(String code)} - Sets the code of the module.</li>
 *   <li>{@code getName()} - Returns the name of the module.</li>
 *   <li>{@code setName(String name)} - Sets the name of the module.</li>
 *   <li>{@code isMnc()} - Returns whether the module is mandatory non-condonable.</li>
 *   <li>{@code setMnc(boolean mnc)} - Sets whether the module is mandatory non-condonable.</li>
 *   <li>{@code getGrades()} - Returns the list of grades associated with the module.</li>
 *   <li>{@code setGrades(List<Grade> grades)} - Sets the list of grades associated with the module.</li>
 *   <li>{@code toString()} - Returns a string representation of the module.</li>
 * </ul>
 */
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
