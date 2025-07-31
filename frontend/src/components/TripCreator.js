import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import TravelAppService from "../repository/repo";

const TripCreator = ({ onCreate }) => {
  const navigate = useNavigate();
    const [trip, setTrip] = useState({
        name: "",
        startDate: "",
        endDate: "",
        budget: "",
    });

  const handleCreateTrip = async (tripName) => {
    const token = localStorage.getItem("jwt"); // Or whatever key you used

    if (!token) {
        alert("You must be logged in to create a trip.");
        navigate("/login");
        return;
    }
      try {
          const response = await TravelAppService.createTrip(trip);
          const createdTripId = response.data.id;
          navigate(`/trips/${createdTripId}`);
      } catch (error) {
          alert("Failed to create trip");
      }
  };

  return (
    <section>
      <h1>Create A Trip</h1>
      <p>Organize Your Accommodation, Transportation and Activity!</p>
        <div>
            <input
                type="text"
                value={trip.name}
                onChange={(e) => setTrip({ ...trip, name: e.target.value })}
                placeholder="Trip Name"
            />
            <input
                type="date"
                value={trip.startDate}
                onChange={(e) => setTrip({...trip, startDate: e.target.value})}
            />
            <input
                type="date"
                value={trip.endDate}
                onChange={(e) => setTrip({...trip, endDate: e.target.value})}
            />
            <input
                type="number"
                placeholder="Budget"
                value={trip.budget}
                onChange={(e) => setTrip({...trip, budget: e.target.value})}
            />
            <button
                onClick={() => handleCreateTrip(trip)}
            >
                Create Trip
            </button>
        </div>
    </section>
  );
};

export default TripCreator;
