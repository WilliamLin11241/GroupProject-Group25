package uk.ac.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
// import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.ac.ucl.comp0010.model.Student;
import uk.ac.ucl.comp0010.repository.StudentRepository;
import uk.ac.ucl.comp0010.controller.StudentController;









@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentController studentController;

    private Student student;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student();
        student.setId(1);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john.doe@example.com");
    }

    @Test
    public void testAddStudent() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("id", "1");
        params.put("firstName", "John");
        params.put("lastName", "Doe");
        params.put("email", "john.doe@example.com");

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/Student/addStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(params)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.firstName").value(student.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(student.getLastName()))
                .andExpect(jsonPath("$.email").value(student.getEmail()));
    }

    @Test
    public void testGetAllStudents() throws Exception {
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student));

        mockMvc.perform(get("/Student/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(student.getId()))
                .andExpect(jsonPath("$[0].firstName").value(student.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(student.getLastName()))
                .andExpect(jsonPath("$[0].email").value(student.getEmail()));
    }

    @Test
    public void testGetStudentById() throws Exception {
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));

        mockMvc.perform(get("/Student/{studentId}", student.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.firstName").value(student.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(student.getLastName()))
                .andExpect(jsonPath("$.email").value(student.getEmail()));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("firstName", "Jane");
        params.put("lastName", "Doe");
        params.put("email", "jane.doe@example.com");

        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(put("/Student/update/{studentId}", student.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(params)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.firstName").value(params.get("firstName")))
                .andExpect(jsonPath("$.lastName").value(params.get("lastName")))
                .andExpect(jsonPath("$.email").value(params.get("email")));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));

        mockMvc.perform(delete("/Student/delete/{studentId}", student.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Student deleted successfully"));

        verify(studentRepository, times(1)).delete(student);
    }
}