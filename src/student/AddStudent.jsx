import React, { useState } from "react";
import { TextField, Button, Alert } from "@mui/material";
import axios from "axios";
import { API_ENDPOINT } from "../config";

function AddStudent({ update }) {
  const [formData, setFormData] = useState({
    id: "",
    firstName: "",
    lastName: "",
    username: "",
    email: "",
  });
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleSubmit = async () => {
    setError("");
    setSuccess("");
    try {
      await axios.post(`${API_ENDPOINT}/students`, formData);
      setSuccess("Student added successfully!");
      setFormData({ id: "", firstName: "", lastName: "", username: "", email: "" });
      update();
    } catch (err) {
      setError(`Failed to add student: ${err.response?.data || err.message}`);
    }
  };

  return (
    <div style={{ margin: "20px 0" }}>
      <h3>Add New Student</h3>
      {error && <Alert severity="error">{error}</Alert>}
      {success && <Alert severity="success">{success}</Alert>}
      <div style={{ display: "flex", alignItems: "center", gap: "10px", flexWrap: "wrap" }}>
        <TextField
          label="Student ID"
          value={formData.id}
          onChange={(e) => setFormData({ ...formData, id: e.target.value })}
        />
        <TextField
          label="First Name"
          value={formData.firstName}
          onChange={(e) => setFormData({ ...formData, firstName: e.target.value })}
        />
        <TextField
          label="Last Name"
          value={formData.lastName}
          onChange={(e) => setFormData({ ...formData, lastName: e.target.value })}
        />
        <TextField
          label="Email"
          value={formData.email}
          onChange={(e) => setFormData({ ...formData, email: e.target.value })}
        />
        <Button variant="contained" onClick={handleSubmit}>
          Add Student
        </Button>
      </div>
    </div>
  );
}

export default AddStudent;
