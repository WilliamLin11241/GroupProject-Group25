import React from 'react';
import { render, screen } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import '@testing-library/jest-dom';
import Grades from '../grade/Grades';

test('renders the Grades component', () => {
  render(
    <MemoryRouter>
      <Grades />
    </MemoryRouter>
  );


  const linkElement = screen.getByText(/Grades Management/i);
  expect(linkElement).toBeInTheDocument();
});
