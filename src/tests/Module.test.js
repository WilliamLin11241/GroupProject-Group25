import React from 'react';
import axios from 'axios';
import { render, screen } from '@testing-library/react';
import Modules from '../module/Modules';


jest.mock('axios');

describe('Modules Component', () => {
  it('should render modules correctly', async () => {

    axios.get.mockResolvedValueOnce({
      data: [
        { code: 'CS101', name: 'Computer Science 101', mnc: true },
        { code: 'MA101', name: 'Mathematics 101', mnc: false },
      ],
    });

    render(<Modules />);


    await screen.findByText(/Computer Science 101/i);
    await screen.findByText(/Mathematics 101/i);

    expect(screen.getByText(/CS101/i)).toBeInTheDocument();
    expect(screen.getByText(/Computer Science 101/i)).toBeInTheDocument();
  });

  it('should show an error if API request fails', async () => {

    axios.get.mockRejectedValueOnce(new Error('Failed to fetch modules'));

    render(<Modules />);


    await screen.findByText(/Failed to fetch modules/i);
  });
});
