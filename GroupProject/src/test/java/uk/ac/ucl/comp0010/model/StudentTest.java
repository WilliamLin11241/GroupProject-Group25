package uk.ac.ucl.comp0010.model;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentTest {

    private Student student;

    @BeforeEach
    public void setUp() {
        student = new Student();
        student.setId(1);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john.doe@example.com");
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1, student.getId());
        assertEquals("John", student.getFirstName());
        assertEquals("Doe", student.getLastName());
        assertEquals("john.doe@example.com", student.getEmail());
    }

    @Test
    public void testGradesManagement() {
        // Create mock grades
        Grade grade1 = new Grade(85, student, new Module("COMP0010", "Software Engineering", true));
        Grade grade2 = new Grade(90, student, new Module("COMP0020", "Database Systems", false));

        // Add grades to student
        List<Grade> grades = new ArrayList<>();
        grades.add(grade1);
        grades.add(grade2);
        student.setGrades(grades);

        // Assert grades are properly set
        assertEquals(2, student.getGrades().size());
        assertTrue(student.getGrades().contains(grade1));
        assertTrue(student.getGrades().contains(grade2));
    }

    @Test
    public void testRegistrationManagement() {
        // Create mock registrations
        Registration reg1 = new Registration(student, new Module("COMP0010", "Software Engineering", true));
        Registration reg2 = new Registration(student, new Module("COMP0020", "Database Systems", false));

        // Add registrations to student
        List<Registration> registrations = new ArrayList<>();
        registrations.add(reg1);
        registrations.add(reg2);
        student.setRegistrations(registrations);

        // Assert registrations are properly set
        assertEquals(2, student.getRegistrations().size());
        assertTrue(student.getRegistrations().contains(reg1));
        assertTrue(student.getRegistrations().contains(reg2));
    }

    @Test
    public void testToString() {
        String expected = "Student{id=1, firstName='John', lastName='Doe', email='john.doe@example.com'}";
        assertEquals(expected, student.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Create another student with the same ID
        Student anotherStudent = new Student();
        anotherStudent.setId(1);
        anotherStudent.setFirstName("John");
        anotherStudent.setLastName("Doe");
        anotherStudent.setEmail("john.doe@example.com");

        assertEquals(student, anotherStudent);
        assertEquals(student.hashCode(), anotherStudent.hashCode());

        // Create a student with a different ID
        Student differentStudent = new Student();
        differentStudent.setId(2);
        differentStudent.setFirstName("Jane");
        differentStudent.setLastName("Smith");
        differentStudent.setEmail("jane.smith@example.com");

        assertNotEquals(student, differentStudent);
        assertNotEquals(student.hashCode(), differentStudent.hashCode());
    }
}
