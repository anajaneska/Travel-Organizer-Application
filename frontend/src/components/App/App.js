import './App.css';
import React, {Component} from "react";
import 'bootstrap/dist/css/bootstrap.min.css'
import TravelAppService from "../../repository/repo";
import {BrowserRouter as Router, Redirect, Route, Routes} from "react-router-dom";
import Header from "../Header/Header";
import Login from '../Login.js'
import Register from '../Register.js';
import CreateTrip from '../Trips/CreateTrip.js';
import { Navigate } from 'react-router-dom';
import TripsList from '../Trips/TripList.js';
import HomePage from '../../pages/HomePage.js';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      accommodations: [],
      trips: [],
      isLoggedIn: !!localStorage.getItem("jwt"),
    };
  }

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
    if (this.state.isLoggedIn) {
      this.loadTrips();
    }
  }

  render() {
    return (
      <Router>
        <Header />
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login onLogin={this.onLogin} />} />
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
