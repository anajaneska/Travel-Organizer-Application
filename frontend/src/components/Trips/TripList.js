import React, { useEffect, useState } from "react";
import instance from "../../custom-axios/axios";
import './TripList.css'

export default function TripsList() {
  const [trips, setTrips] = useState([]);

  useEffect(() => {
    const fetchTrips = async () => {
      try {
        const response = await instance.get("/trips");
        setTrips(response.data);
      } catch {
        alert("Failed to fetch trips");
      }
    };
    fetchTrips();
  }, []);

  return (
    <div className="trips-container">
      <h2>Your Trips</h2>
      <ul className="trips-list">
        {trips.map(trip => (
          <li key={trip.id} className="trip-card">
            <h3 className="trip-name">{trip.name}</h3>
            <div className="trip-destination">{trip.destination}</div>
            <div className="trip-dates">
              {trip.startDate} – {trip.endDate}
            </div>
            <div className="trip-budget">Budget: ${trip.budget}</div>
          </li>
        ))}
      </ul>
    </div>
  );
}

