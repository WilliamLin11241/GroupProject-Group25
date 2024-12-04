import React, { useState } from "react";
import axios from "axios";
import { Paper, TextField, Button, Typography, Alert } from "@mui/material";
import { API_ENDPOINT } from "../config";

function AddRegistration() {
  const [registration, setRegistration] = useState({ studentId: "", moduleCode: "" });
  const [error, setError] = useState("");

  const handleSubmit = () => {
    axios
      .post(`${API_ENDPOINT}/registrations/register`, registration)
      .then(() => {
        alert("Registration added successfully!");
      })
      .catch((err) => {
        setError(err.message);
      });
  };

  return (
    <Paper sx={{ padding: "30px" }}>
      <Typography variant="h5">Add Registration</Typography>
      <TextField
        label="Student ID"
        value={registration.studentId}
        onChange={(e) => setRegistration({ ...registration, studentId: e.target.value })}
        fullWidth
        margin="normal"
      />
      <TextField
        label="Module Code"
        value={registration.moduleCode}
        onChange={(e) => setRegistration({ ...registration, moduleCode: e.target.value })}
        fullWidth
        margin="normal"
      />
      <Button variant="contained" onClick={handleSubmit}>
        Submit
      </Button>
      {error && <Alert severity="error">{error}</Alert>}
    </Paper>
  );
}

export default AddRegistration;
