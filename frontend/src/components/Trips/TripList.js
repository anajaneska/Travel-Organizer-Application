import React, { useEffect, useState } from "react";
import instance from "../../custom-axios/axios";

export default function TripsList() {
  const [trips, setTrips] = useState([]);

  const fetchTrips = async () => {
    try {
      const response = await instance.get("/trips");
      setTrips(response.data);
    } catch (error) {
      alert("Failed to fetch trips");
    }
  };

  useEffect(() => {
    fetchTrips();
  }, []);

  return (
    <div>
      <h2>Your Trips</h2>
      <ul>
        {trips.map((trip) => (
          <li key={trip.id}>
            {trip.name} - {trip.destination} ({trip.startDate} to {trip.endDate}) - Budget: ${trip.budget}
          </li>
        ))}
      </ul>
    </div>
  );
}
