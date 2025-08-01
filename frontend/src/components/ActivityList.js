import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './ActivityList.css'

const ActivityList = () => {
  const [activities, setActivities] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:9091/api/amadeus/activities?lat=48.8566&lon=2.3522")
      .then((res) => {
        console.log("Activity data:", res.data);
        setActivities(res.data.data); // Important: data array
      })
      .catch((err) => {
        console.error("Failed to fetch activities:", err);
      });
  }, []);

  return (
      <div className="container mt-4">
          <h2>Explore More Activities</h2>
          <div className="row justify-content-center">

              {activities.length === 0 ? (
                  <p>Loading activities...</p>
              ) : (
                  activities.map((activity, index) => (
                      <div className="col-md-4 mb-4" key={index}>
                          <div className="card h-100 shadow-sm">
                              {activity.pictures?.[0] && (
                                  <img
                                      src={activity.pictures[0]}
                                      className="card-img-top"
                                      alt={activity.name}
                                      style={{objectFit: 'cover', height: '200px'}}
                                  />
                              )}
                              <div className="card-body">
                                  <h5 className="card-title">{activity.name}</h5>
                              </div>
                          </div>
                      </div>
                  ))
              )}
          </div>
      </div>
  );
};

export default ActivityList;
