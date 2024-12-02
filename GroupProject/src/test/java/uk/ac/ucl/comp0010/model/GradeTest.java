package uk.ac.ucl.comp0010.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GradeTest {

    private Grade grade;
    private Student student;
    private Module module;

    @BeforeEach
    public void setUp() {
        // Initialize mock Student and Module
        student = new Student(1, "John", "Doe", "john.doe@example.com");
        module = new Module("COMP0010", "Software Engineering", true);

        // Initialize Grade
        grade = new Grade(85, student, module);
        grade.setId(1); // Set ID explicitly for testing
    }

    @Test
    public void testGettersAndSetters() {
        // Assert initial values
        assertEquals(1, grade.getId());
        assertEquals(85, grade.getScore());
        assertEquals(student, grade.getStudent());
        assertEquals(module, grade.getModule());

        // Change and assert new values
        grade.setScore(90);
        assertEquals(90, grade.getScore());
    }

    @Test
    public void testConstructor() {
        Grade newGrade = new Grade(95, student, module);
        assertEquals(95, newGrade.getScore());
        assertEquals(student, newGrade.getStudent());
        assertEquals(module, newGrade.getModule());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Create another Grade with the same properties
        Grade anotherGrade = new Grade(85, student, module);
        anotherGrade.setId(1);

        // Assert equals and hashCode
        assertEquals(grade, anotherGrade);
        assertEquals(grade.hashCode(), anotherGrade.hashCode());

        // Create a different Grade
        Grade differentGrade = new Grade(90, student, new Module("COMP0020", "Database Systems", false));
        differentGrade.setId(2);

        // Assert not equals and hashCode
        assertNotEquals(grade, differentGrade);
        assertNotEquals(grade.hashCode(), differentGrade.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "Grade{id=1, score=85, student=Student{id=1, firstName='John', lastName='Doe', email='john.doe@example.com'}, module=Module{code='COMP0010', name='Software Engineering', mnc=true}}";
        assertEquals(expected, grade.toString());
    }
}
