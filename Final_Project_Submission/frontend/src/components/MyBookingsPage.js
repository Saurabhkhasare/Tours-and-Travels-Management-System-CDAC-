import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';

const MyBookingsPage = () => {
  const navigate = useNavigate();
  const [userId, setUserId] = useState(null); 
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [canceledBookingId, setCanceledBookingId] = useState(null); 

  useEffect(() => {
    
    const storedUserId = sessionStorage.getItem('userId');
    setUserId(storedUserId); 
  }, []);

  const fetchBookings = async () => {
    try {
      setLoading(true);
      
      const response = await axios.get(`http://localhost:7070/dashboard/myBookings?userId=${userId}`);
      setBookings(response.data);
      setLoading(false);
    } catch (error) {
      setError('An error occurred while fetching bookings. Please try again.');
      setLoading(false);
    }
  };

  useEffect(() => {
    
    if (userId) {
      fetchBookings();
    }
  }, [userId, canceledBookingId]); 

  const handleCancelBooking = async (bookingId) => {
    try {
      await axios.delete(`http://localhost:7070/dashboard/myBookings/${bookingId}`);
      setCanceledBookingId(bookingId); 
    } catch (error) {
      console.error('Error while canceling booking:', error);
    }
  };

  const handleGenerateTicket = (bookingId) => {
    
    alert('Ticket has been sent to your registered email.');
  };
  

  const handleLogout = () => {
    sessionStorage.clear();
    navigate('/login');
  };

  return (
    <div style={{ backgroundColor: '#f0f0f0', minHeight: '100vh', padding: '20px' }}>
      <div className="container">
        
        <header className="mb-4">
          <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="container-fluid">
              <Link className="navbar-brand" to="/dashboard">Dashboard</Link>
              <div className="collapse navbar-collapse" id="navbarNav">
                <ul className="navbar-nav me-auto">
                  <li className="nav-item">
                    <Link className="nav-link" to="/feedback">Feedback</Link>
                  </li>
                </ul>
              </div>
              <button className="btn btn-outline-danger" onClick={handleLogout}>Log Out</button>
            </div>
          </nav>
        </header>

        <h2>My Bookings</h2>
        {loading && <p>Loading...</p>}
        {error && <p>{error}</p>}
        {bookings.length === 0 && !loading && !error && <p>No bookings found.</p>}
        {bookings.length > 0 && (
          <table className="table">
            <thead>
              <tr>
                <th>From</th>
                <th>To</th>
                <th>Date</th>
                <th>Bus Name</th>
                <th>Departure Time</th>
                <th>Number of Persons</th>
                <th>Total Amount</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {bookings.map(booking => (
                <tr key={booking.id}>
                  <td>{booking.fromSource}</td>
                  <td>{booking.toDestination}</td>
                  <td>{booking.departureDate}</td>
                  <td>{booking.busName}</td>
                  <td>{booking.departureTime}</td>
                  <td>{booking.noOfPersons}</td>
                  <td>{booking.totalAmount}</td>
                  <td>
                    {booking.id !== canceledBookingId && ( 
                      <button className="btn btn-success me-2" onClick={() => handleGenerateTicket(booking.id)}>Generate Ticket</button>
                    )}
                    <button className="btn btn-danger" onClick={() => handleCancelBooking(booking.id)}>Cancel</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
        {canceledBookingId && ( 
          <div className="alert alert-success mt-3">
            Ticket is successfully canceled, the refund will be credited back to your account in the next 3 days.
          </div>
        )}
      </div>
    </div>
  );
};

export default MyBookingsPage;
