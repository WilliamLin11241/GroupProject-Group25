import React, { useState } from "react";
import axios from "axios";
import { TextField, Button, Alert } from "@mui/material";
import { API_ENDPOINT } from "../config";

function AddGrade({ update }) {
  const [formData, setFormData] = useState({ studentId: "", moduleCode: "", score: "" });
  const [error, setError] = useState("");

  const handleSubmit = () => {
    if (!formData.studentId || !formData.moduleCode || !formData.score) {
      setError("All fields are required");
      return;
    }

    setError("");
    axios
      .post(`${API_ENDPOINT}/grades`, formData)
      .then(() => {
        update();
        setFormData({ studentId: "", moduleCode: "", score: "" });
      })
      .catch((err) => {
        setError(err.message);
      });
  };

  return (
    <>
      {error && <Alert severity="error">{error}</Alert>}
      <TextField
        label="Student ID"
        value={formData.studentId}
        onChange={(e) => setFormData({ ...formData, studentId: e.target.value })}
        fullWidth
      />
      <TextField
        label="Module Code"
        value={formData.moduleCode}
        onChange={(e) => setFormData({ ...formData, moduleCode: e.target.value })}
        fullWidth
      />
      <TextField
        label="Score"
        type="number"
        value={formData.score}
        onChange={(e) => setFormData({ ...formData, score: e.target.value })}
        fullWidth
      />
      <Button variant="contained" onClick={handleSubmit}>
        Add Grade
      </Button>
    </>
  );
}

export default AddGrade;
