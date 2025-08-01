import React, { useEffect, useState } from "react";
import {Link, useNavigate, useParams} from "react-router-dom";
import TravelAppService from "../../repository/repo";
import instance from "../../custom-axios/axios";
import AddToTripModal from "../AddToTripModal";
import { MdOutlineEdit } from "react-icons/md";
import { BiTrash } from "react-icons/bi";

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
        <div key={item.id} className="card d-flex justify-content-between align-top mx-1 my-2 p-3">
            <div>
                <p className={'d-flex'} style={{color: '#555'}}>{type}: <div className='fw-medium mx-1'
                                                                             style={{color: 'rgb(51, 51, 51)'}}> {item.name || item.location || item.description || item.type} </div>
                </p>
                <p className={'d-flex'} style={{color: '#555'}}>Start:<div className='fw-medium mx-1'
                             style={{color: 'rgb(51, 51, 51)'}}> {formatDate(item.startDate || item.checkInDate || item.date || item.departureTime || item.startTime)}
                </div></p>
                <p className={'d-flex'} style={{color: '#555'}}>
                    End:<div className='fw-medium mx-1'
                                     style={{color: 'rgb(51, 51, 51)'}}>{formatDate(item.endDate || item.checkOutDate || item.arrivalTime || item.endTime)}
                    </div></p>
                <p className={'d-flex'} style={{color: '#555'}}>
                    Cost:<div className='fw-medium mx-1'
                              style={{color: 'rgb(51, 51, 51)'}}> ${item.cost || item.totalCost || item.price || 0}</div></p>
            </div>
            <div className='my-3'>
                <MdOutlineEdit size={28} onClick={() => setEditingItem({type, id: item.id, data: {...item}})} className='mx-3'/>
                <BiTrash size={28} onClick={deleteEntity} className='mx-3'/>
            </div>
        </div>
    );
};

    if (!trip) return <p>Loading...</p>;

    return (
        <div className="trip-details container">
            <div className={'d-flex justify-content-between align-top'}>
                <h2 className={'m-0 fw-medium'}>Trip Details</h2>
                <button onClick={() => setShowModal(true)}>Add to Trip</button>
            </div>

            {showModal && (
                <AddToTripModal
                    tripId={id}
                    onClose={() => setShowModal(false)}
                    onSuccess={fetchTrip}
                />
            )}

            {editMode ? (
                <div className="edit-form d-grid" style={{width: 'fit-content'}}>
                    <label className={'d-flex align-items-center'} style={{color: '#555'}}>Name:
                        <input name="name" value={formData.name} onChange={handleChange} className={'px-2 py-1 mx-1'} style={{color: 'rgb(51, 51, 51)'}}/></label>
                    <label className={'d-flex align-items-center'} style={{color: '#555'}}>Start Date:
                        <input name="startDate" type="date" value={formData.startDate} className={'px-2 py-1 mx-1'} style={{color: 'rgb(51, 51, 51)'}}
                                              onChange={handleChange}/></label>
                    <label className={'d-flex align-items-center'} style={{color: '#555'}}>End Date: <input name="endDate" type="date" value={formData.endDate}
                                            onChange={handleChange} className={'px-2 py-1 mx-1'} style={{color: 'rgb(51, 51, 51)'}}/></label>
                    <label className={'d-flex align-items-center'} style={{color: '#555'}}>Budget:
                        <input name="budget" type="number" value={formData.budget} onChange={handleChange} className={'px-2 py-1 mx-1'} style={{color: 'rgb(51, 51, 51)'}}/></label>
                   <button onClick={handleUpdate}>Save</button>
                    <button onClick={() => setEditMode(false)}>Cancel</button>
                </div>
            ) : (
                <div className="view-mode d-flex justify-content-between align-top mx-1">
                    <div>
                        <p className={'d-flex'} style={{color: '#555'}}>Name:
                            <div className='fw-medium mx-1' style={{color: 'rgb(51, 51, 51)'}}>{trip.name}</div>
                        </p>
                        <p className={'d-flex'} style={{color: '#555'}}>Start Date:
                            <div className='fw-medium mx-1' style={{color: 'rgb(51, 51, 51)'}}>{trip.startDate}</div>
                        </p>
                        <p className={'d-flex'} style={{color: '#555'}}>End Date:
                            <div className='fw-medium mx-1' style={{color: 'rgb(51, 51, 51)'}}>{trip.endDate}</div>
                        </p>
                        <p className={'d-flex'} style={{color: '#555'}}>Budget:
                            <div className='fw-medium mx-1' style={{color: 'rgb(51, 51, 51)'}}>${trip.budget}</div>
                        </p>
                    </div>
                    <div className='my-3 mx-3'>
                        <MdOutlineEdit size={28} onClick={() => setEditMode(true)} className='mx-3'/>
                        <BiTrash size={28} onClick={handleDelete} className='mx-3'/>
                    </div>
                </div>
            )}

            <hr/>

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
