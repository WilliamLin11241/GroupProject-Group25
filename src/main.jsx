import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import "@fontsource/roboto/300.css";
import "@fontsource/roboto/400.css";
import "@fontsource/roboto/500.css";
import "@fontsource/roboto/700.css";
import Home from "./Home";
import Modules from "./module/Modules";
import Students from "./student/Students";
import Grades from "./grade/Grades";
import Registrations from "./registerStudent/Registrations";
import GradeStatistic from "./grade-table/GradeStatistic";

const router = createBrowserRouter([
  { path: "/modules", element: <Modules /> },
  { path: "/students", element: <Students /> },
  { path: "/grades", element: <Grades /> },
  { path: "/registrations", element: <Registrations /> },
  { path: "/grades/statistic", element: <GradeStatistic /> },
  { path: "/", element: <Home /> },
]);

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
);
