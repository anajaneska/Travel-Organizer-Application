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

    const renderTripCard = (trip) => {
    const accommodationLocations = trip.accommodations?.map(acc => acc.location).join(', ') || '';
    const activities = trip.activities || [];

        return (<li key={trip.id} className="trip-card">
            <Link to={`/trips/${trip.id}`} style={{textDecoration: 'none', color: 'inherit'}}>
                <h3 className="trip-name text-capitalize fw-semibold" style={{color: 'rgb(34, 34, 34)', fontSize: '22px'}}>{trip.name}</h3>
                <div className="trip-destination" style={{color: 'rgb(34, 34, 34)'}}>{trip.destination}</div>
                <div className="trip-dates d-flex">
                    Start Date: <div className='fw-medium mx-1' style={{color: '#333'}}> {trip.startDate}</div>
                </div>
                <div className="trip-dates d-flex">
                    End Date: <div className='fw-medium mx-1' style={{color: '#333'}}>{trip.endDate}</div>
                </div>
                {accommodationLocations && (
                    <div className="trip-dates d-flex" style={{ color: '#555' }}>
                        Accommodations: <div className='fw-medium mx-1' style={{color: '#333'}}>{accommodationLocations}</div>
                    </div>
                )}
                {activities.length > 0 && (
                    <div className="trip-activities mt-2 mb-2">
                        {activities.map((activity, index) => (
                            <button
                                key={index}
                                className="btn btn-outline-primary btn-sm me-2 mb-1 px-2"
                                style={{ pointerEvents: 'none', cursor: 'default', border: '#F27705 2px solid', color: '#333' }}
                            >
                                {activity.name}
                            </button>
                        ))}
                    </div>
                )}
                        <div className="trip-budget d-flex justify-content-end fw-medium"
                     style={{color: '#222222', fontSize: '16px'}}> Budget:
                    <div className='fw-medium mx-1'>${trip.budget}</div></div>
            </Link>
        </li>
        )};

    return (
        <div className="trips-container">
            <div className={'d-flex justify-content-between align-top'}>
                <h2 className={'m-0 fw-medium'}>Your Trips</h2>
                <Link to={"/create-trip"} className={'text-decoration-none'}>
                    <button >Create Trip</button>
                </Link>
            </div>

            {upcomingTrips.length > 0 && (
                <div className={'my-5'}>
                    <h3 className={'my-4'}>Upcoming Trips</h3>
                    <ul className="trips-list d-block">
                        {upcomingTrips.map(renderTripCard)}
                    </ul>
                </div>
            )}

            {pastTrips.length > 0 && (
                <div className={'my-5'}>
                    <h3 className={'my-4'}>Past Trips</h3>
                    <ul className="trips-list d-block">
                        {pastTrips.map(renderTripCard)}
                    </ul>
                </div>
            )}
        </div>
    );
}
