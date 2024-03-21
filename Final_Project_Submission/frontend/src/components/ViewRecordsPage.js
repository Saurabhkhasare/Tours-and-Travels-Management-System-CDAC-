import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom'; 

const ViewRecordsPage = () => {
  const navigate = useNavigate(); 
  const [packages, setPackages] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchPackages = async () => {
      try {
        setLoading(true);
        const response = await axios.get('http://localhost:7070/admin/packages');
        setPackages(response.data);
        setLoading(false);
      } catch (error) {
        setError('An error occurred while fetching packages. Please try again.');
        setLoading(false);
      }
    };

    fetchPackages();
  }, []);

  
  const handleDeletePackage = async (packageId) => {
    try {
      
      await axios.delete(`http://localhost:7070/admin/records/${packageId}`);
      
      const updatedPackages = packages.filter(pkg => pkg.id !== packageId);
      setPackages(updatedPackages);
    } catch (error) {
      console.error('Error while deleting package:', error);
    }
  };

  
  const handleLogout = () => {
    
    navigate('/login');
  };

  return (
    <div className=" vh-100" style={{ backgroundColor: '#f0f0f0', padding: '20px', borderRadius: '10px' }}>
      {}
      <header className="mb-4">
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
          <div className="container-fluid">
            <Link className="btn-outline-primary btn " to="/add-package">Add Packages</Link>
            <button className="btn btn-outline-danger " onClick={handleLogout}>Logout</button>
          </div>
        </nav>
      </header>

      <h2 style={{ marginBottom: '20px' }}>View Records</h2>
      {loading && <p>Loading...</p>}
      {error && <p>{error}</p>}
      {packages.length === 0 && !loading && !error && <p>No packages found.</p>}
      {packages.length > 0 && (
        <table className="table">
          <thead>
            <tr>
              <th>From</th>
              <th>To</th>
              <th>Date</th>
              <th>Departure Time</th>
              <th>Bus Name</th>
              <th>Price</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {packages.map(pkg => (
              <tr key={pkg.id}>
                <td>{pkg.fromSource}</td>
                <td>{pkg.toDestination}</td>
                <td>{pkg.departureDate}</td>
                <td>{pkg.departureTime}</td>
                <td>{pkg.busName}</td>
                <td>{pkg.price}</td>
                <td>
                  <Link to={`/edit-package/${pkg.id}`} className="btn btn-primary me-2">Edit</Link>
                  <button className="btn btn-danger" onClick={() => handleDeletePackage(pkg.id)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default ViewRecordsPage;
