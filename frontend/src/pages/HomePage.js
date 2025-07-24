import React, { useEffect, useState } from "react";
import Footer from "../components/Footer";
import TripCreator from "../components/TripCreator";
import ActivityCard from "../components/ActivityCard";

import HotelList from "../components/HotelList";

const HomePage = () => {
  const [accommodations, setAccommodations] = useState([]);
  const [activities, setActivities] = useState([]);

  useEffect(() => {
    // Replace with your actual endpoints
    // instance.get("https://api.example.com/random-accommodations").then((res) => {
    //   setAccommodations(res.data);
    // });

    // instance.get("https://api.example.com/random-activities").then((res) => {
    //   setActivities(res.data);
    // });
  }, []);

  return (
    <div className="flex flex-col min-h-screen">

      <main className="flex-1 container mx-auto p-4">
        <TripCreator />

        {/* <section className="mt-8">
          <h2 className="text-2xl font-semibold mb-4">Recommended Accommodations</h2>
          <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
            {accommodations.map((acc) => (
              <AccommodationCard key={acc.id} accommodation={acc} />
            ))}
          </div>
        </section> */}
        <HotelList />

        <section className="mt-8">
          <h2 className="text-2xl font-semibold mb-4">Popular Activities</h2>
          <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
            {activities.map((act) => (
              <ActivityCard key={act.id} activity={act} />
            ))}
          </div>
        </section>
      </main>

      <Footer />
    </div>
  );
};

export default HomePage;
