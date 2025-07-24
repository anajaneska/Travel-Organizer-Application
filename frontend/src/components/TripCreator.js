import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

const TripCreator = ({ onCreate }) => {
  const [tripName, setTripName] = useState('');

  const handleCreateTrip = () => {
    if (tripName.trim() !== '') {
      onCreate(tripName);
      setTripName('');
    }
  };

  return (
    <section>
      <h1>Create A Trip</h1>
      <p>Organize Your Accommodation, Transportation and Activity!</p>
      <div>
        <input
          type="text"
          value={tripName}
          onChange={(e) => setTripName(e.target.value)}
          placeholder="Trip Name"
        />
        <button
          onClick={handleCreateTrip}
        >
          Create Trip
        </button>
      </div>
    </section>
  );
};

export default TripCreator;
