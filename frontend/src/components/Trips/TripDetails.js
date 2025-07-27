import React, { useEffect, useState } from "react";
import {useNavigate, useParams} from "react-router-dom";
import TravelAppService from "../../repository/repo";
import {FaTrash} from "react-icons/fa";
import instance from "../../custom-axios/axios";

const TripDetails = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [trip, setTrip] = useState(null);
    const [editMode, setEditMode] = useState(false);
    const [formData, setFormData] = useState({
        name: "",
        destination: "",
        startDate: "",
        endDate: "",
        budget: ""
    });

    useEffect(() => {
        const fetchTrip = async () => {
            try {
                const response = await instance.get(`/trips/${id}`);
                setTrip(response.data);
                setFormData(response.data);
            } catch (err) {
                alert("Failed to load trip");
                navigate("/"); // redirect on failure
            }
        };
        fetchTrip();
    }, [id, navigate]);

    const handleDelete = async () => {
        try {
            await instance.delete(`/trips/${id}`);
            navigate("/trips");
        } catch {
            alert("Delete failed");
        }
    };

    const handleChange = e => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleUpdate = async () => {
        try {
            await instance.put(`/trips/${id}`, formData);
            setTrip(formData);
            setEditMode(false);
        } catch {
            alert("Update failed");
        }
    };

    if (!trip) return <p>Loading...</p>;

    return (
        <div className="trip-details">
            <h2>Trip Details</h2>

            {editMode ? (
                <div className="edit-form">
                    <label>Name: <input name="name" value={formData.name} onChange={handleChange} /></label>
                    <label>Destination: <input name="destination" value={formData.destination} onChange={handleChange} /></label>
                    <label>Start Date: <input name="startDate" type="date" value={formData.startDate} onChange={handleChange} /></label>
                    <label>End Date: <input name="endDate" type="date" value={formData.endDate} onChange={handleChange} /></label>
                    <label>Budget: <input name="budget" type="number" value={formData.budget} onChange={handleChange} /></label>
                    <button onClick={handleUpdate}>Save</button>
                    <button onClick={() => setEditMode(false)}>Cancel</button>
                </div>
            ) : (
                <div className="view-mode">
                    <p><strong>Name:</strong> {trip.name}</p>
                    <p><strong>Destination:</strong> {trip.destination}</p>
                    <p><strong>Dates:</strong> {trip.startDate} – {trip.endDate}</p>
                    <p><strong>Budget:</strong> ${trip.budget}</p>
                    <button onClick={() => setEditMode(true)}>Edit</button>
                    <button onClick={handleDelete}>🗑️ Delete</button>
                </div>
            )}
        </div>
    );
};

export default TripDetails;
