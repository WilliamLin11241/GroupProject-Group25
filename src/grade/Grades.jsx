import React, { useState, useEffect } from "react";
import axios from "axios";
import { Paper, Typography, TextField, Button, Alert, Grid } from "@mui/material";
import { useNavigate } from "react-router-dom"; 
import { API_ENDPOINT } from "../config";

function Grades() {
  const [grades, setGrades] = useState(() => {
    const cachedGrades = JSON.parse(sessionStorage.getItem("grades"));
    return cachedGrades || [];
  });
  const [formData, setFormData] = useState({
    studentId: "",
    moduleCode: "",
    score: "",
  });
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const navigate = useNavigate();

  useEffect(() => {
    if (grades.length === 0) {
      fetchGrades();
    }
  }, []);

  const fetchGrades = async () => {
    setError("");
    try {
      const response = await axios.get(`${API_ENDPOINT}/Grade/allGrades`);
      const gradesData = response.data;

      const cachedStudents = JSON.parse(sessionStorage.getItem("students")) || {};
      const cachedModules = JSON.parse(sessionStorage.getItem("modules")) || {};

      const enrichedGrades = await Promise.all(
        gradesData.map(async (grade) => {
          let student = cachedStudents[grade.student_id];
          let module = cachedModules[grade.module_code];

          if (!student) {
            student = await axios
              .get(`${API_ENDPOINT}/students/${grade.student_id}`)
              .then((res) => res.data)
              .catch(() => null);
            if (student) {
              cachedStudents[grade.student_id] = student;
            }
          }

          if (!module) {
            module = await axios
              .get(`${API_ENDPOINT}/Module/${grade.module_code}`)
              .then((res) => res.data)
              .catch(() => null);
            if (module) {
              cachedModules[grade.module_code] = module;
            }
          }

          return {
            ...grade,
            studentName: student ? `${student.firstName} ${student.lastName}` : grade.student_id,
            moduleName: module ? `${module.code} ${module.name}` : grade.module_code,
          };
        })
      );

      sessionStorage.setItem("students", JSON.stringify(cachedStudents));
      sessionStorage.setItem("modules", JSON.stringify(cachedModules));
      sessionStorage.setItem("grades", JSON.stringify(enrichedGrades));

      setGrades(enrichedGrades);
    } catch (err) {
      setError("Failed to fetch grades.");
      console.error("Error fetching grades:", err);
    }
  };

  const handleAddGrade = async () => {
    setError("");
    setSuccess("");

    if (!formData.studentId || !formData.moduleCode || !formData.score) {
      setError("All fields are required.");
      return;
    }

    const isDuplicate = grades.some(
      (grade) =>
        grade.student_id === formData.studentId &&
        grade.module_code === formData.moduleCode
    );
    if (isDuplicate) {
      setError("Grade already exists for this student and module.");
      return;
    }

    const grade = {
      student_id: formData.studentId,
      module_code: formData.moduleCode,
      score: formData.score,
    };

    try {
      await axios.post(`${API_ENDPOINT}/Grade/addGrade`, grade, {
        headers: { "Content-Type": "application/json" },
      });
      setSuccess("Grade added successfully!");

      const cachedStudents = JSON.parse(sessionStorage.getItem("students")) || {};
      const cachedModules = JSON.parse(sessionStorage.getItem("modules")) || {};

      let student = cachedStudents[formData.studentId];
      let module = cachedModules[formData.moduleCode];

      if (!student) {
        student = await axios
          .get(`${API_ENDPOINT}/students/${formData.studentId}`)
          .then((res) => res.data)
          .catch(() => null);
        if (student) {
          cachedStudents[formData.studentId] = student;
          sessionStorage.setItem("students", JSON.stringify(cachedStudents));
        }
      }

      if (!module) {
        module = await axios
          .get(`${API_ENDPOINT}/Module/${formData.moduleCode}`)
          .then((res) => res.data)
          .catch(() => null);
        if (module) {
          cachedModules[formData.moduleCode] = module;
          sessionStorage.setItem("modules", JSON.stringify(cachedModules));
        }
      }

      const newGrade = {
        student_id: formData.studentId,
        module_code: formData.moduleCode,
        studentName: student ? `${student.firstName} ${student.lastName}` : formData.studentId,
        moduleName: module ? `${module.code} ${module.name}` : formData.moduleCode,
        score: formData.score,
      };

      setGrades((prevGrades) => {
        const updatedGrades = [...prevGrades, newGrade];
        sessionStorage.setItem("grades", JSON.stringify(updatedGrades));
        return updatedGrades;
      });

      setFormData({ studentId: "", moduleCode: "", score: "" });
    } catch (err) {
      setError("Failed to add grade. Please check the inputs.");
      console.error("Error adding grade:", err);
    }
  };

  const viewGradesTable = () => {
    navigate("/grades-table");
  };

  return (
    <Paper sx={{ padding: "30px" }}>
      <Button
        variant="outlined"
        onClick={() => navigate("/")}
        sx={{ marginBottom: "20px" }}
      >
        Return to Home
      </Button>

      <Typography variant="h5">Grades Management</Typography>

      <Typography variant="h6" sx={{ marginTop: "20px" }}>
        Add Grade
      </Typography>
      <TextField
        label="Student ID"
        value={formData.studentId}
        onChange={(e) => setFormData({ ...formData, studentId: e.target.value })}
        fullWidth
        margin="normal"
      />
      <TextField
        label="Module Code"
        value={formData.moduleCode}
        onChange={(e) => setFormData({ ...formData, moduleCode: e.target.value })}
        fullWidth
        margin="normal"
      />
      <TextField
        label="Score"
        value={formData.score}
        onChange={(e) => setFormData({ ...formData, score: e.target.value })}
        fullWidth
        margin="normal"
      />
      <Button variant="contained" onClick={handleAddGrade} sx={{ marginTop: "10px" }}>
        Add Grade
      </Button>

      {success && (
        <Alert severity="success" sx={{ marginTop: "10px" }}>
          {success}
        </Alert>
      )}
      {error && (
        <Alert severity="error" sx={{ marginTop: "10px" }}>
          {error}
        </Alert>
      )}

      <Typography variant="h6" sx={{ marginTop: "30px" }}>
        All Grades
      </Typography>
      {grades.length > 0 ? (
        <>
          <Grid container style={{ padding: "10px 0" }}>
            <Grid item xs={4}>
              Student
            </Grid>
            <Grid item xs={4}>
              Module
            </Grid>
            <Grid item xs={4}>
              Score
            </Grid>
          </Grid>
          {grades.map((grade, index) => (
            <Grid container key={index} style={{ padding: "10px 0" }}>
              <Grid item xs={4}>
                {grade.studentName}
              </Grid>
              <Grid item xs={4}>
                {grade.moduleName}
              </Grid>
              <Grid item xs={4}>
                {grade.score || "No score"}
              </Grid>
            </Grid>
          ))}
        </>
      ) : (
        <Typography>No grades found.</Typography>
      )}

      {/* View Grades Table Button */}
      <Button
        variant="contained"
        onClick={() => navigate("/grades/statistic")}
        sx={{ marginTop: "10px", marginLeft: "10px" }}
      >
        View Grades Table
      </Button>


    </Paper>
  );
}

export default Grades;
