import React, { useState } from "react";
import './AddToTripModal.css';
import instance from "../custom-axios/axios";

export default function AddToTripModal({ tripId, onClose, onSuccess }) {
    const [type, setType] = useState("");
    const [formData, setFormData] = useState({});

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleSubmit = async () => {
        try {
            const endpointMap = {
                accommodation: `/accommodations/trip/${tripId}`,
                activity: `/activities/trip/${tripId}`,
                transportation: `/transportations/trip/${tripId}`,
            };

            const endpoint = endpointMap[type];
            const payload = { ...formData, tripId };
            console.log("Submitting:", formData);

            await instance.post(endpoint, payload);
            onSuccess();
            onClose();
        } catch (err) {
            alert("Failed to add item to trip.");
        }
    };

    const renderFormFields = () => {
        switch (type) {
            case "accommodation":
                return (
                    <>
                        <input name="location" onChange={handleChange} placeholder="Location" />
                        <input type="date" name="checkInDate" onChange={handleChange} />
                        <input type="date" name="checkOutDate" onChange={handleChange} />
                        <input type="number" name="totalCost"  min={0} onChange={handleChange} placeholder="Total Cost" />
                    </>
                );
            case "activity":
                return (
                    <>
                        <input name="name" onChange={handleChange} placeholder="Name" />
                        <input name="description" onChange={handleChange} placeholder="Description" />
                        <input name="location" onChange={handleChange} placeholder="Location" />
                        <input type="datetime-local" name="startTime" onChange={handleChange} />
                    </>
                );
            case "transportation":
                return (
                    <>
                        <select
                            name="type"
                            value={formData.type || ""}
                            onChange={handleChange}
                        >
                            <option value="">Select Transportation Type</option>
                            <option value="CAR">Car</option>
                            <option value="PLANE">Plane</option>
                            <option value="BUS">Bus</option>
                            <option value="TRAIN">Train</option>
                            <option value="BOAT">Boat</option>
                            <option value="TRAM">Tram</option>
                        </select>
                        <input name="startLocation" onChange={handleChange} placeholder="Start Location"/>
                        <input name="destination" onChange={handleChange} placeholder="Destination"/>
                        <input type="datetime-local" name="departureTime" onChange={handleChange}/>
                        <input type="datetime-local" name="arrivalTime" onChange={handleChange}/>
                        <input type="number" name="cost" step="0.01" min={0} onChange={handleChange} placeholder="Cost"/>
                    </>
                );
            default:
                return null;
        }
    };

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <button className="close-btn" onClick={onClose}>×</button>
                <h3>Add to Trip</h3>

                {/* Type selector for choosing form */}
                <select value={type} onChange={(e) => setType(e.target.value)}>
                    <option value="">Select Type</option>
                    <option value="accommodation">Accommodation</option>
                    <option value="activity">Activity</option>
                    <option value="transportation">Transportation</option>
                </select>

                <div className="form-fields">
                    {renderFormFields()}
                </div>

                {type && <button onClick={handleSubmit}>Submit</button>}
            </div>
        </div>
    );
}
