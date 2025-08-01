import React, { useEffect, useState } from "react";
import Footer from "../components/Footer/Footer";
import TripCreator from "../components/TripCreator/TripCreator";
import ActivityList from "../components/ActivityList";

import HotelList from "../components/HotelList";

const HomePage = () => {
  const [activities, setActivities] = useState([]);


  return (
    <div className="flex flex-col min-h-screen">

      <main>
        <TripCreator />
        <HotelList />
        <ActivityList/>
      </main>

      <Footer />
    </div>
  );
};

export default HomePage;
