import React from "react";
import axios from "axios";
import { Breadcrumbs, Link, Typography, Alert, Grid, TextField, Button, Select, MenuItem } from "@mui/material";
import App from "../App.jsx";
import { API_ENDPOINT } from "../config";

function Modules() {
  const [modules, setModules] = React.useState([]);
  const [formData, setFormData] = React.useState({ moduleCode: "", moduleName: "", isMNC: false });
  const [error, setError] = React.useState("");
  const [success, setSuccess] = React.useState("");

  React.useEffect(() => {
    updateModules();
  }, []);


  function updateModules() {
    axios
      .get(`${API_ENDPOINT}/Module/all`)
      .then((response) => {
        setModules(response.data);
      })
      .catch((err) => {
        setError(err.message);
      });
  }


  function handleSubmit() {
    setError("");
    setSuccess("");
    axios
      .post(`${API_ENDPOINT}/Module/addModule`, {
        module_code: formData.moduleCode,
        name: formData.moduleName,
        mnc: formData.isMNC,
      })
      .then(() => {
        setSuccess("Module added successfully!");
        updateModules();
        setFormData({ moduleCode: "", moduleName: "", isMNC: false });
      })
      .catch((err) => {
        setError(err.message);
      });
  }

  return (
    <App>
      <Breadcrumbs sx={{ marginBottom: "30px" }}>
        <Link underline="hover" color="inherit" href="/">
          Home
        </Link>
        <Typography sx={{ color: "text.primary" }}>Modules</Typography>
      </Breadcrumbs>

      {/* Display error or success messages */}
      {error && <Alert color="error">{error}</Alert>}
      {success && <Alert color="success">{success}</Alert>}
      {!error && modules.length < 1 && (
        <Alert color="warning">No modules</Alert>
      )}

      {/* Display the list of modules */}
      {modules.length > 0 && (
        <>
          <Grid container style={{ padding: "10px 0" }}>
            <Grid item xs={2}>
              Module Code
            </Grid>
            <Grid item xs={8}>
              Module Name
            </Grid>
            <Grid item xs={2}>
              Is MNC
            </Grid>
          </Grid>
          {modules.map((m) => (
            <Grid container key={m.code} style={{ padding: "10px 0" }}>
              <Grid item xs={2}>
                {m.code}
              </Grid>
              <Grid item xs={8}>
                {m.name}
              </Grid>
              <Grid item xs={2}>
                {m.mnc ? "Yes" : "No"}
              </Grid>
            </Grid>
          ))}
        </>
      )}

      {/* Form to add a new module */}
      <Typography variant="h6" sx={{ marginTop: "30px" }}>
        Add New Module
      </Typography>
      <Grid container spacing={2}>
        <Grid item xs={12} sm={4}>
          <TextField
            label="Module Code"
            value={formData.moduleCode}
            onChange={(e) => setFormData({ ...formData, moduleCode: e.target.value })}
            fullWidth
          />
        </Grid>
        <Grid item xs={12} sm={4}>
          <TextField
            label="Module Name"
            value={formData.moduleName}
            onChange={(e) => setFormData({ ...formData, moduleName: e.target.value })}
            fullWidth
          />
        </Grid>
        <Grid item xs={12} sm={4}>
          <Select
            value={formData.isMNC}
            onChange={(e) => setFormData({ ...formData, isMNC: e.target.value })}
            fullWidth
            displayEmpty
          >
            <MenuItem value={true}>Yes</MenuItem>
            <MenuItem value={false}>No</MenuItem>
          </Select>
        </Grid>
      </Grid>
      <Button
        variant="contained"
        onClick={handleSubmit}
        sx={{ marginTop: "10px" }}
      >
        Submit
      </Button>
    </App>
  );
}

export default Modules;
