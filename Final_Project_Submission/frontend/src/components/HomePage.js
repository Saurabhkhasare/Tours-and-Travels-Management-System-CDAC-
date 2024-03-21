import React from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

const HomePage = () => {
  return (

    <div className="d-flex flex-column justify-content-between align-items-center vh-100 vw-100" style={{ backgroundImage: "url(./images/bg_home.jpg)", backgroundRepeat: "no-repeat",
    backgroundSize: "cover"}}>
      <h2 style={{marginTop:"2rem", color:"#964B00"}}>Welcome to Tours and Travel Management System.</h2>
      <div className="mt-3">
        <Link to="/login" className="btn btn-primary me-3" style={{fontSize:"1.5rem", fontWeight:"600"}}>Login</Link>
        <Link to="/signup" className="btn btn-success" style={{fontSize:"1.5rem", fontWeight:"600"}}>Sign Up</Link>
      </div>
      <div>

      </div>
    </div>
  );
}

export default HomePage;
