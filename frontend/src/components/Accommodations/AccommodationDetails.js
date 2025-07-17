import { useEffect, useState } from "react";
import { useParams } from "react-router-dom"; // ✅ Import useParams
import instance from '../../custom-axios/axios';

export default function AccommodationDetails() {
  const { id } = useParams(); // ✅ Extract id from route params
  const [accommodation, setAccommodation] = useState(null);
  const [trips, setTrips] = useState([]);
  const [tripId, setTripId] = useState("");
  const [checkIn, setCheckIn] = useState("");
  const [checkOut, setCheckOut] = useState("");
  const [error, setError] = useState("");

  useEffect(() => {
    if (!id) return;
    instance.get(`/accommodations/${id}`).then(res => setAccommodation(res.data));
    instance.get("/trips").then(res => setTrips(res.data));
  }, [id]);

  const book = async () => {
    try {
      await instance.post(`/accommodations/${id}/trip/${tripId}`, {
        checkIn,
        checkOut
      });
      alert("Booked successfully!");
    } catch (e) {
      setError(e.response?.data?.message || "Booking failed");
    }
  };

  if (!accommodation) return <p>Loading...</p>;

  return (
    <div>
      <h2>{accommodation.name}</h2>
      <p>{accommodation.location} - ${accommodation.costPerNight}</p>

      <h3>Book this accommodation</h3>
      <select value={tripId} onChange={e => setTripId(e.target.value)}>
        <option value="">Select trip</option>
        {trips.map(trip => (
          <option key={trip.id} value={trip.id}>
            {trip.name} ({trip.startDate} to {trip.endDate})
          </option>
        ))}
      </select>
      <br />
      <input type="date" value={checkIn} onChange={e => setCheckIn(e.target.value)} />
      <input type="date" value={checkOut} onChange={e => setCheckOut(e.target.value)} />
      <button onClick={book}>Book</button>

      {error && <p style={{ color: "red" }}>{error}</p>}
    </div>
  );
}
