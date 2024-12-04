import React, { useState, useEffect } from "react";
import axios from "axios";
import { Paper, Typography, TextField, Button, Alert, Grid } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { API_ENDPOINT } from "../config";

function Registration() {
  const [registrations, setRegistrations] = useState(
    JSON.parse(sessionStorage.getItem("registrations")) || []
  );
  const [formData, setFormData] = useState({ studentId: "", moduleCode: "" });
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [studentModules, setStudentModules] = useState([]);
  const [moduleStudents, setModuleStudents] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    if (!sessionStorage.getItem("registrations")) {
      fetchRegistrations();
    }

    const clearSessionData = () => {
      sessionStorage.removeItem("registrations");
    };


    window.addEventListener("beforeunload", clearSessionData);


    return () => {
      window.removeEventListener("beforeunload", clearSessionData);
    };
  }, []);

  const fetchRegistrations = async () => {
    setError("");
    try {
      const response = await axios.get(`${API_ENDPOINT}/registrations`);
      const registrationsData =
        response.data?._embedded?.registrations.map((reg) => {
          const studentId = reg.student?._links?.self.href.split("/").pop();
          const moduleCode = reg.module?.code;
          return { ...reg, studentId, moduleCode };
        }) || [];
      setRegistrations(registrationsData);
      sessionStorage.setItem("registrations", JSON.stringify(registrationsData));
    } catch (err) {
      setError("Failed to load registrations.");
      console.error("Error fetching registrations:", err);
    }
  };

  const handleRegister = async () => {
    setError("");
    setSuccess("");

    if (!formData.studentId || !formData.moduleCode) {
      setError("Both Student ID and Module Code are required.");
      return;
    }

    const alreadyExists = registrations.some(
      (reg) =>
        reg.studentId === formData.studentId.trim() &&
        reg.moduleCode === formData.moduleCode.trim()
    );

    if (alreadyExists) {
      setError("This registration already exists.");
      return;
    }

    const payload = new URLSearchParams();
    payload.append("studentId", formData.studentId.trim());
    payload.append("moduleCode", formData.moduleCode.trim());

    try {
      await axios.post(
        `${API_ENDPOINT}/registrations/register`,
        payload,
        { headers: { "Content-Type": "application/x-www-form-urlencoded" } }
      );
      setSuccess("Registration successful!");
      const newRegistration = {
        studentId: formData.studentId,
        moduleCode: formData.moduleCode,
      };
      setRegistrations((prev) => {
        const updatedRegistrations = [...prev, newRegistration];
        sessionStorage.setItem(
          "registrations",
          JSON.stringify(updatedRegistrations)
        );
        return updatedRegistrations;
      });
      setFormData({ studentId: "", moduleCode: "" });
    } catch (err) {
      setError("Failed to register. Please check the inputs.");
      console.error("Error during registration:", err.response?.data || err);
    }
  };

  const handleUnregister = async () => {
    setError("");
    setSuccess("");

    if (!formData.studentId || !formData.moduleCode) {
      setError("Both Student ID and Module Code are required.");
      return;
    }

    const payload = new URLSearchParams();
    payload.append("studentId", formData.studentId.trim());
    payload.append("moduleCode", formData.moduleCode.trim());

    try {
      await axios.delete(`${API_ENDPOINT}/registrations/unregister`, {
        data: payload,
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
      });
      setSuccess("Unregistration successful!");
      setRegistrations((prev) => {
        const updatedRegistrations = prev.filter(
          (reg) =>
            !(
              reg.studentId === formData.studentId.trim() &&
              reg.moduleCode === formData.moduleCode.trim()
            )
        );
        sessionStorage.setItem(
          "registrations",
          JSON.stringify(updatedRegistrations)
        );
        return updatedRegistrations;
      });
      setFormData({ studentId: "", moduleCode: "" });
    } catch (err) {
      setError("Failed to unregister. Please check the inputs.");
      console.error("Error during unregistration:", err.response?.data || err);
    }
  };

  const viewModulesByStudent = () => {
    if (!formData.studentId) {
      setError("Student ID is required to view modules.");
      return;
    }
    const modules = registrations
      .filter((reg) => reg.studentId === formData.studentId.trim())
      .map((reg) => reg.moduleCode);
    setStudentModules(modules);
    setError("");
  };

  const viewStudentsByModule = () => {
    if (!formData.moduleCode) {
      setError("Module Code is required to view students.");
      return;
    }
    const students = registrations
      .filter((reg) => reg.moduleCode === formData.moduleCode.trim())
      .map((reg) => reg.studentId);
    setModuleStudents(students);
    setError("");
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

      <Typography variant="h5">Registration Management</Typography>

      <Typography variant="h6" sx={{ marginTop: "20px" }}>
        Register Student to Module
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
        onChange={(e) =>
          setFormData({ ...formData, moduleCode: e.target.value })
        }
        fullWidth
        margin="normal"
      />
      <Button
        variant="contained"
        onClick={handleRegister}
        sx={{ marginTop: "10px", marginRight: "10px" }}
      >
        Register
      </Button>
      <Button
        variant="contained"
        color="error"
        onClick={handleUnregister}
        sx={{ marginTop: "10px", marginRight: "10px" }}
      >
        Unregister
      </Button>
      <Button
        variant="contained"
        onClick={viewModulesByStudent}
        sx={{ marginTop: "10px", marginRight: "10px" }}
      >
        View Modules by Student
      </Button>
      <Button
        variant="contained"
        onClick={viewStudentsByModule}
        sx={{ marginTop: "10px" }}
      >
        View Students by Module
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
        Existing Registrations
      </Typography>
      {registrations.length > 0 ? (
        <>
          <Grid container style={{ padding: "10px 0" }}>
            <Grid item xs={6}>Student ID</Grid>
            <Grid item xs={6}>Module Code</Grid>
          </Grid>
          {registrations.map((reg, index) => (
            <Grid container key={index} style={{ padding: "10px 0" }}>
              <Grid item xs={6}>{reg.studentId}</Grid>
              <Grid item xs={6}>{reg.moduleCode}</Grid>
            </Grid>
          ))}
        </>
      ) : (
        <Typography>No registrations found.</Typography>
      )}

      {studentModules.length > 0 && (
        <>
          <Typography variant="h6" sx={{ marginTop: "30px" }}>
            Modules Registered by Student
          </Typography>
          {studentModules.map((mod, index) => (
            <Typography key={index}>{mod}</Typography>
          ))}
        </>
      )}

      {moduleStudents.length > 0 && (
        <>
          <Typography variant="h6" sx={{ marginTop: "30px" }}>
            Students Registered to Module
          </Typography>
          {moduleStudents.map((stu, index) => (
            <Typography key={index}>{stu}</Typography>
          ))}
        </>
      )}
    </Paper>
  );
}

export default Registration;
