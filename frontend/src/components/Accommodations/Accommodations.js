import React, { useEffect, useState } from 'react';
import instance from '../../custom-axios/axios'; // the configured Axios instance with baseURL

export default function Accommodations() {
  const [accommodations, setAccommodations] = useState([]);

  useEffect(() => {
    instance.get('/accommodations')
      .then(res => setAccommodations(res.data))
      .catch(err => {
        console.error("Failed to fetch accommodations", err);
      });
  }, []);

  return (
    <div>
      <h2>Available Accommodations</h2>
      <ul>
        {accommodations.map(acc => (
          <li key={acc.id}>
            {acc.name} – {acc.location}
          </li>
        ))}
      </ul>
    </div>
  );
}
