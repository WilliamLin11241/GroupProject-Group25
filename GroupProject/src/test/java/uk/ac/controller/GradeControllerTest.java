package uk.ac.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
// import uk.ac.ucl.comp0010.model.Grade;
// import uk.ac.ucl.comp0010.model.Module;
// import uk.ac.ucl.comp0010.model.Student;
import uk.ac.ucl.comp0010.repository.GradeRepository;
import uk.ac.ucl.comp0010.repository.ModuleRepository;
import uk.ac.ucl.comp0010.repository.StudentRepository;
import uk.ac.ucl.comp0010.controller.GradeController;
import uk.ac.ucl.comp0010.model.Grade;
import uk.ac.ucl.comp0010.model.Module;
import uk.ac.ucl.comp0010.model.Student;








@WebMvcTest(GradeController.class)
class GradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GradeRepository gradeRepository;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private ModuleRepository moduleRepository;

    private Student student;
    private Module module;
    private Grade grade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        student = new Student();
        student.setId(1);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john.doe@example.com");

        module = new Module();
        module.setCode("COMP0010");
        module.setName("Software Engineering");

        grade = new Grade();
        grade.setId(1);
        grade.setStudent(student);
        grade.setModule(module);
        grade.setScore(85);
    }

    @Test
    void testAddGrade() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("student_id", "1");
        params.put("module_code", "COMP0010");
        params.put("score", "85");

        when(studentRepository.findById(anyInt())).thenReturn(Optional.of(student));
        when(moduleRepository.findById(anyString())).thenReturn(Optional.of(module));
        when(gradeRepository.save(any(Grade.class))).thenReturn(grade);

        mockMvc.perform(post("/Grade/addGrade")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(params)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllGrades() throws Exception {
        when(gradeRepository.findAll()).thenReturn(Arrays.asList(grade));

        mockMvc.perform(get("/Grade/allGrades"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetGradeById() throws Exception {
        when(gradeRepository.findById(anyInt())).thenReturn(Optional.of(grade));

        mockMvc.perform(get("/Grade/{gradeId}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateGrade() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("score", "90");

        when(gradeRepository.findById(anyInt())).thenReturn(Optional.of(grade));
        when(gradeRepository.save(any(Grade.class))).thenReturn(grade);

        mockMvc.perform(put("/Grade/update/{gradeId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(params)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteGrade() throws Exception {
        when(gradeRepository.findById(anyInt())).thenReturn(Optional.of(grade));

        mockMvc.perform(delete("/Grade/delete/{gradeId}", 1))
                .andExpect(status().isOk());
    }
}
