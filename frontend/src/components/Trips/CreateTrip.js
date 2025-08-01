import React, { useState } from "react";
import instance from "../../custom-axios/axios";
import {useNavigate} from "react-router-dom";

export default function CreateTrip({ onTripCreated }) {
    const [trip, setTrip] = useState({
        name: "",
        startDate: "",
        endDate: "",
        budget: "",
    });
    const navigate = useNavigate();
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await instance.post("/trips", {
                name: trip.name,
                startDate: trip.startDate,
                endDate: trip.endDate,
                budget: parseFloat(trip.budget),
            });
            alert("Trip created!");
            onTripCreated();
            navigate(`/trips`);
        } catch (error) {
            alert("Failed to create trip");
        }
    };

    return (
    <div>
    <h2>Create Trip</h2>
    <form onSubmit={handleSubmit}>

        <input
            placeholder="Name"
            value={trip.name}
            onChange={(e) => setTrip({...trip, name: e.target.value})}
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
        <button type="submit">Create</button>
    </form>
    </div>
)
    ;
}
