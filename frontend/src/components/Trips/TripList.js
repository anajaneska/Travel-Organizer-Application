import React, { useEffect, useState } from "react";
import instance from "../../custom-axios/axios";
import './TripList.css'
import {Link} from "react-router-dom";

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
      <Link to={"/create-trip"}>
        <button>Create Trip</button>
      </Link>

      <ul className="trips-list">
        {trips.map(trip => (
          <li key={trip.id} className="trip-card">
            <Link to={`/trips/${trip.id}`} className="trip-link">
            <h3 className="trip-name">{trip.name}</h3>
            <div className="trip-destination">{trip.destination}</div>
            <div className="trip-dates">
              {trip.startDate} – {trip.endDate}
            </div>
            <div className="trip-budget">Budget: ${trip.budget}</div>
            </Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

