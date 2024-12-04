import { useState } from "react";
import React from 'react';

import "./App.css";
import { Container } from "@mui/material";

function App(props) {
  return <Container>{props.children}</Container>;
}

export default App;
