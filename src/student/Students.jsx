import React from "react";
import axios from "axios";
import { Breadcrumbs, Link, Typography, Alert, Grid } from "@mui/material";
import App from "../App";
import { API_ENDPOINT } from "../config";
import AddStudent from "./AddStudent";

function Students() {
  const [students, setStudents] = React.useState([]);
  const [error, setError] = React.useState("");

  React.useEffect(() => {
    fetchStudents();
  }, []);

  const fetchStudents = () => {
    axios
      .get(`${API_ENDPOINT}/students`)
      .then((response) => {
        const studentsData = response.data._embedded.students.map((student) => {

          const id = student._links.self.href.split("/").pop();
          return { ...student, id };
        });
        setStudents(studentsData);
      })
      .catch((error) => {
        console.error("Error fetching students:", error);
        setError("Failed to fetch students");
      });
  };

  return (
    <App>
      <Breadcrumbs sx={{ marginBottom: "30px" }}>
        <Link underline="hover" color="inherit" href="/">
          Home
        </Link>
        <Typography sx={{ color: "text.primary" }}>Students</Typography>
      </Breadcrumbs>
      {error && <Alert color="error">{error}</Alert>}
      {!error && students.length < 1 && (
        <Alert color="warning">No students available</Alert>
      )}
      {students.length > 0 && (
        <>
          <Grid container style={{ padding: "10px 0" }}>
            <Grid item xs={2}>
              Student ID
            </Grid>
            <Grid item xs={4}>
              First Name
            </Grid>
            <Grid item xs={4}>
              Last Name
            </Grid>
            <Grid item xs={2}>
              Email
            </Grid>
          </Grid>
          {students.map((s) => {
            return (
              <Grid container key={s.id} style={{ padding: "10px 0" }}>
                <Grid item xs={2}>
                  {s.id}
                </Grid>
                <Grid item xs={4}>
                  {s.firstName}
                </Grid>
                <Grid item xs={4}>
                  {s.lastName}
                </Grid>
                <Grid item xs={2}>
                  {s.email}
                </Grid>
              </Grid>
  );
})}

        </>
      )}
      <br />
      <br />
      <AddStudent update={fetchStudents} />
    </App>
  );
}

export default Students;
