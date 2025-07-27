import React, { useEffect, useState } from "react";
import instance from "../../custom-axios/axios";
import './TripList.css';
import { Link } from "react-router-dom";

export default function TripsList() {
    const [upcomingTrips, setUpcomingTrips] = useState([]);
    const [pastTrips, setPastTrips] = useState([]);

    useEffect(() => {
        const fetchTrips = async () => {
            try {
                const response = await instance.get("/trips");
                const now = new Date();

                const upcoming = [];
                const past = [];

                response.data.forEach(trip => {
                    const endDate = new Date(trip.endDate);
                    if (endDate >= now) {
                        upcoming.push(trip);
                    } else {
                        past.push(trip);
                    }
                });

                upcoming.sort((a, b) => new Date(a.startDate) - new Date(b.startDate));
                past.sort((a, b) => new Date(b.startDate) - new Date(a.startDate));

                setUpcomingTrips(upcoming);
                setPastTrips(past);
            } catch {
                alert("Failed to fetch trips");
            }
        };

        fetchTrips();
    }, []);

    const renderTripCard = (trip) => (
        <li key={trip.id} className="trip-card">
            <Link to={`/trips/${trip.id}`} style={{ textDecoration: 'none', color: 'inherit' }}>
                <h3 className="trip-name">{trip.name}</h3>
                <div className="trip-destination">{trip.destination}</div>
                <div className="trip-dates">
                    {trip.startDate} – {trip.endDate}
                </div>
                <div className="trip-budget">Budget: ${trip.budget}</div>
            </Link>
        </li>
    );

    return (
        <div className="trips-container">
            <h2>Your Trips</h2>
            <Link to={"/create-trip"}>
                <button>Create Trip</button>
            </Link>

            {upcomingTrips.length > 0 && (
                <>
                    <h3>Upcoming Trips</h3>
                    <ul className="trips-list">
                        {upcomingTrips.map(renderTripCard)}
                    </ul>
                </>
            )}

            {pastTrips.length > 0 && (
                <>
                    <h3>Past Trips</h3>
                    <ul className="trips-list">
                        {pastTrips.map(renderTripCard)}
                    </ul>
                </>
            )}
        </div>
    );
}
