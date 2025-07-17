import React from 'react';
import { Link } from 'react-router-dom';
import './Accommodations.css';

const Accommodations = ({ accommodations }) => {
  return (
    <div className="accommodations-container">
      <h2>Available Accommodations</h2>
      <ul className="accommodation-list">
        {accommodations.map(acc => (
          <li key={acc.id} className="accommodation-item">
            <Link to={`/accommodations/${acc.id}`}>
              <strong>{acc.name}</strong> - {acc.location} - ${acc.price}
            </Link>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Accommodations;




