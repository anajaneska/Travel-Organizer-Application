import React from 'react';

export default function Accommodations({ accommodations }) {
  return (
    <div>
      <h2>Available Accommodations</h2>
      <ul>
        {accommodations.map(acc => (
          <li key={acc.id}>
            <strong>{acc.name}</strong> - {acc.location} - ${acc.price}
          </li>
        ))}
      </ul>
    </div>
  );
}


