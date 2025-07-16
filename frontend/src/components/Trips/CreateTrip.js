import React, { useState } from "react";
import instance from "../../custom-axios/axios";

export default function CreateTrip({ onTripCreated }) {
  const [trip, setTrip] = useState({
    name: "",
    destination: "",
    startDate: "",
    endDate: "",
    budget: "",
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await instance.post("/trips/create", {
        name: trip.name,
        destination: trip.destination,
        startDate: trip.startDate,
        endDate: trip.endDate,
        budget: parseFloat(trip.budget),
      });
      alert("Trip created!");
      onTripCreated();
    } catch (error) {
      alert("Failed to create trip");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Create Trip</h2>
      <input
        placeholder="Name"
        value={trip.name}
        onChange={(e) => setTrip({ ...trip, name: e.target.value })}
      />
      <input
        placeholder="Destination"
        value={trip.destination}
        onChange={(e) => setTrip({ ...trip, destination: e.target.value })}
      />
      <input
        type="date"
        value={trip.startDate}
        onChange={(e) => setTrip({ ...trip, startDate: e.target.value })}
      />
      <input
        type="date"
        value={trip.endDate}
        onChange={(e) => setTrip({ ...trip, endDate: e.target.value })}
      />
      <input
        type="number"
        placeholder="Budget"
        value={trip.budget}
        onChange={(e) => setTrip({ ...trip, budget: e.target.value })}
      />
      <button type="submit">Create</button>
    </form>
  );
}
