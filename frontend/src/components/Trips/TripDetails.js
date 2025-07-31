import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import TravelAppService from "../../repository/repo";
import instance from "../../custom-axios/axios";
import AddToTripModal from "../AddToTripModal";

const TripDetails = () => {
    const { id } = useParams();
    const [showModal, setShowModal] = useState(false);
    const navigate = useNavigate();
    const [trip, setTrip] = useState(null);
    const [editMode, setEditMode] = useState(false);
    const [editingItem, setEditingItem] = useState(null); // { type, item }
    const [formData, setFormData] = useState({
        name: "",
        startDate: "",
        endDate: "",
        budget: ""
    });

    const fetchTrip = async () => {
        try {
            const response = await TravelAppService.fetchTripById(id);
            setTrip(response.data);
            setFormData({
                name: response.data.name,
                startDate: response.data.startDate,
                endDate: response.data.endDate,
                budget: response.data.budget
            });
        } catch (err) {
            alert("Failed to load trip");
            navigate("/");
        }
    };

    useEffect(() => {
        fetchTrip();
    }, [id, navigate]);

    const handleDelete = async () => {
        try {
            await TravelAppService.deleteTrip(id);
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
            setTrip({ ...trip, ...formData });
            setEditMode(false);
        } catch {
            alert("Update failed");
        }
    };

    const formatDate = (dateString) => {
        if (!dateString) return '';
        const date = new Date(dateString);
        const isDateOnly = dateString.length <= 10;
        return isDateOnly
            ? date.toLocaleDateString()
            : date.toLocaleString();
    };

const renderCard = (item, type) => {
    const isEditing = editingItem?.id === item.id && editingItem?.type === type;

    const handleEditChange = (e) => {
        const { name, value } = e.target;
        setEditingItem(prev => ({
            ...prev,
            data: {
                ...prev.data,
                [name]: value
            }
        }));
    };

    const saveEdit = async () => {
        const endpointMap = {
            "Transportation": "transportations",
            "Accommodation": "accommodations",
            "Activity": "activities",
        };

        try {
        const payload = {
            ...editingItem.data,
            trip: id 
        };
        await instance.put(`/${endpointMap[editingItem.type]}/${editingItem.id}`, payload);
        setEditingItem(null);
        fetchTrip();
    } catch {
        alert("Update failed");
    }
    };

    const cancelEdit = () => setEditingItem(null);

    if (isEditing) {
        const data = editingItem.data;
        return (
            <div key={item.id} className="card">
                {type === "Accommodation" && (
                    <>
                        <input name="location" value={data.location} onChange={handleEditChange} />
                        <input type="date" name="checkInDate" value={data.checkInDate} onChange={handleEditChange} />
                        <input type="date" name="checkOutDate" value={data.checkOutDate} onChange={handleEditChange} />
                        <input type="number" name="totalCost" value={data.totalCost} onChange={handleEditChange} />
                    </>
                )}
                {type === "Activity" && (
                    <>
                        <input name="name" value={data.name} onChange={handleEditChange} />
                        <input name="description" value={data.description} onChange={handleEditChange} />
                        <input name="location" value={data.location} onChange={handleEditChange} />
                        <input type="datetime-local" name="startTime" value={data.startTime} onChange={handleEditChange} />
                        <input type="datetime-local" name="endTime" value={data.endTime} onChange={handleEditChange} />
                        <input type="number" name="cost" value={data.cost} onChange={handleEditChange} />
                    </>
                )}
                {type === "Transportation" && (
                    <>
                        <select name="type" value={data.type} onChange={handleEditChange}>
                            <option value="CAR">Car</option>
                            <option value="PLANE">Plane</option>
                            <option value="BUS">Bus</option>
                            <option value="TRAIN">Train</option>
                            <option value="BOAT">Boat</option>
                            <option value="TRAM">Tram</option>
                        </select>
                        <input name="startLocation" value={data.startLocation} onChange={handleEditChange} />
                        <input name="destination" value={data.destination} onChange={handleEditChange} />
                        <input type="datetime-local" name="departureTime" value={data.departureTime} onChange={handleEditChange} />
                        <input type="datetime-local" name="arrivalTime" value={data.arrivalTime} onChange={handleEditChange} />
                        <input type="number" name="cost" value={data.cost} onChange={handleEditChange} />
                    </>
                )}
                <button onClick={saveEdit}>Save</button>
                <button onClick={cancelEdit}>Cancel</button>
            </div>
        );
    }

    const deleteEntity = async () => {
        const endpointMap = {
            "Transportation": "transportations",
            "Accommodation": "accommodations",
            "Activity": "activities",
        };

        try {
            await instance.delete(`/${endpointMap[type]}/${item.id}`);
            fetchTrip();
        } catch (err) {
            alert(`Failed to delete ${type.toLowerCase()}.`);
        }
    };

    return (
        <div key={item.id} className="card">
            <p><strong>{type}:</strong> {item.name || item.location || item.description}</p>
            <p><strong>Start:</strong> {formatDate(item.startDate || item.checkInDate || item.date || item.departureTime || item.startTime)}</p>
            <p><strong>End:</strong> {formatDate(item.endDate || item.checkOutDate || item.arrivalTime || item.endTime)}</p>
            <p><strong>Cost:</strong> ${item.cost || item.totalCost || item.price || 0}</p>
            <button onClick={() => setEditingItem({ type, id: item.id, data: { ...item } })}>Edit</button>
            <button onClick={deleteEntity}>Delete</button>
        </div>
    );
};

    if (!trip) return <p>Loading...</p>;

    return (
        <div className="trip-details">
            <h2>Trip Details</h2>
            <button onClick={() => setShowModal(true)}>Add to Trip</button>

            {showModal && (
                <AddToTripModal
                    tripId={id}
                    onClose={() => setShowModal(false)}
                    onSuccess={fetchTrip}
                />
            )}

            {editMode ? (
                <div className="edit-form">
                    <label>Name: <input name="name" value={formData.name} onChange={handleChange} /></label>
                    <label>Start Date: <input name="startDate" type="date" value={formData.startDate} onChange={handleChange} /></label>
                    <label>End Date: <input name="endDate" type="date" value={formData.endDate} onChange={handleChange} /></label>
                    <label>Budget: <input name="budget" type="number" value={formData.budget} onChange={handleChange} /></label>
                    <button onClick={handleUpdate}>Save</button>
                    <button onClick={() => setEditMode(false)}>Cancel</button>
                </div>
            ) : (
                <div className="view-mode">
                    <p><strong>Name:</strong> {trip.name}</p>
                    <p><strong>Dates:</strong> {trip.startDate} – {trip.endDate}</p>
                    <p><strong>Budget:</strong> ${trip.budget}</p>
                    <button onClick={() => setEditMode(true)}>Edit</button>
                    <button onClick={handleDelete}>Delete</button>
                </div>
            )}

            <hr />

            <h3>Transportations</h3>
            <div className="card-group">
                {trip.transportations?.map(t => renderCard(t, "Transportation"))}
            </div>

            <h3>Accommodations</h3>
            <div className="card-group">
                {trip.accommodations?.map(a => renderCard(a, "Accommodation"))}
            </div>

            <h3>Activities</h3>
            <div className="card-group">
                {trip.activities?.map(act => renderCard(act, "Activity"))}
            </div>
        </div>
    );
};

export default TripDetails;
