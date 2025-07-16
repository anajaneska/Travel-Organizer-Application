import './App.css';
import React, {Component} from "react";
import 'bootstrap/dist/css/bootstrap.min.css'
import Accommodations from "../Accommodations/Accommodations";
import TravelAppService from "../../repository/repo";
import {BrowserRouter as Router, Redirect, Route, Routes} from "react-router-dom";
import Header from "../Header/Header";
import Trips from "../Trips/Trips";
import Login from '../Login.js'
import Register from '../Register.js';
import CreateTrip from '../Trips/CreateTrip.js';
import { Navigate } from 'react-router-dom';
import TripsList from '../Trips/TripList.js';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      accommodations: [],
      trips: [],
      isLoggedIn: !!localStorage.getItem("jwt"),
    };
  }

  loadAccommodations = () => {
    TravelAppService.fetchAccommodations()
      .then((data) => {
        this.setState({
          accommodations: data.data,
        });
      });
  };

  loadTrips = () => {
    TravelAppService.fetchTrips()
      .then((data) => {
        this.setState({
          trips: data.data,
        });
      });
  };

  onLogin = () => {
    this.setState({ isLoggedIn: true }, this.loadTrips);
  };

  componentDidMount() {
    this.loadAccommodations();
    if (this.state.isLoggedIn) {
      this.loadTrips();
    }
  }

  render() {
    return (
      <Router>
        <Header />
        <Routes>
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login onLogin={this.onLogin} />} />
          <Route
            path="/accommodations"
            element={<Accommodations accommodations={this.state.accommodations} />}
          />
          <Route
            path="/trips"
            element={
              this.state.isLoggedIn ? (
                <TripsList trips={this.state.trips} />
              ) : (
                <Navigate to="/login" />
              )
            }
          />
          <Route
            path="/create-trip"
            element={
              this.state.isLoggedIn ? (
                <CreateTrip onTripCreated={this.loadTrips} />
              ) : (
                <Navigate to="/login" />
              )
            }
          />
        </Routes>
      </Router>
    );
  }
}

export default App;
