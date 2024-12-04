
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Paper, Typography, Button } from "@mui/material";
import { BarChart, Bar, XAxis, YAxis, Tooltip, Legend, CartesianGrid, ResponsiveContainer } from "recharts";
import axios from "axios";
import { API_ENDPOINT } from "../config";

function GradeStatistic() {
  const [grades, setGrades] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    
    const fetchGrades = async () => {
      try {
        const response = await axios.get(`${API_ENDPOINT}/Grade/allGrades`);
        setGrades(response.data);
      } catch (error) {
        console.error("Error fetching grades:", error);
      }
    };
    fetchGrades();
  }, []);

  return (
    <Paper sx={{ padding: "30px" }}>
      <Button
        variant="outlined"
        onClick={() => navigate("/grades")}
        sx={{ marginBottom: "20px" }}
      >
        Return to Grades
      </Button>
      <Typography variant="h5">Grade Statistics</Typography>
      {grades.length > 0 ? (
        <ResponsiveContainer width="100%" height={400}>
          <BarChart data={grades}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="module_code" />
            <YAxis />
            <Tooltip />
            <Legend />
            <Bar dataKey="score" fill="#8884d8" />
          </BarChart>
        </ResponsiveContainer>
      ) : (
        <Typography>No grade data available</Typography>
      )}
    </Paper>
  );
}

export default GradeStatistic;
