import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!email || !password) {
      setMessage('Please fill in all fields');
      return;
    }
    try {
      const response = await axios.post('http://localhost:7070/login', {
        email,
        password
      });
      if (response.status === 200 && response.data.role === 'ADMIN') {
        // Redirect to admin dashboard
        navigate('/add-package');
      } else if (response.status === 200 && response.data.role === 'USER') {
        // Redirect to user dashboard
        sessionStorage.setItem('userId', response.data.id);
        navigate('/dashboard');
      } else {
        setMessage('Invalid email or password');
      }
    } catch (error) {
      console.error('Error while logging in:', error);
      setMessage('An error occurred. Please try again.');
    }
  };

  return (
    <div className="d-flex  flex-column align-items-center justify-content-center vh-100 vw-100" style={{ backgroundImage: "url(./images/2.jpg)", backgroundRepeat: "no-repeat",
    backgroundSize: "cover"}}>
     <h2 style={{marginBottom:"1rem" ,color:"#964B00"}}>Tours and Travel Management System.</h2>
      <div className="card p-4" style={{minWidth:'25rem'}}>
        <h2 className="mb-4">Login</h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">Email</label>
            <input type="email" className="form-control" id="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
          </div>
          <div className="mb-3">
            <label htmlFor="password" className="form-label">Password</label>
            <input type="password" className="form-control" id="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
          </div>
          <button type="submit" className="btn btn-primary">Login</button>
          {message && <p className="mt-3">{message}</p>}
        </form>
        <p className="mt-3">Not registered yet? <Link to="/signup">Sign Up here</Link></p>
      </div>
    </div>
  );
}

export default Login;
