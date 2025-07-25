import React, { useEffect, useState } from "react";
import axios from "axios";

const HotelList = () => {
  const [hotels, setHotels] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:9091/api/amadeus/hotels")
      .then((res) => {
        console.log("Hotel data:", res.data);
        setHotels(res.data.data); // Important: data array
      })
      .catch((err) => {
        console.error("Failed to fetch hotels:", err);
      });
  }, []);

  return (
    <div className="container mt-4">
      <div className="row">
        <h2>Top Accommodations for You</h2>
        {hotels.length === 0 ? (
          <p>Loading hotels...</p>
        ) : (
          hotels.map((hotel, index) => (
            <div className="col-md-4 mb-4" key={index}>
              <div className="card h-100 shadow-sm">
                <div className="card-body">
                  <h5 className="card-title">{hotel.name?.trim()}</h5>
                  <p className="card-text">
                    {hotel.address?.lines?.join(", ")}<br />
                    {hotel.address?.cityName}, {hotel.address?.countryCode}
                  </p>
                  {hotel.distance && (
                    <p className="text-muted">
                      Distance: {hotel.distance.value} {hotel.distance.unit}
                    </p>
                  )}
                </div>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default HotelList;

