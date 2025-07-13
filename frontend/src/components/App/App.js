import './App.css';
import React, {Component} from "react";
import 'bootstrap/dist/css/bootstrap.min.css'
import Accommodations from "../Accommodations/Accommodations";
import TravelAppService from "../../repository/repo";
import {BrowserRouter as Router, Redirect, Route, Routes} from "react-router-dom";
import Header from "../Header/Header";
import Trips from "../Trips/Trips";

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      accommodations : []
    };
    this.state = {
      trips : []
    };
  }

  loadAccommodations = () => {
    TravelAppService.fetchAccommodations()
        .then((data) => {
          this.setState({
            accommodations: data.data
          })
        })
  }

  loadTrips = () => {
    TravelAppService.fetchTrips()
        .then((data) => {
          this.setState({
             trips: data.data
          })
        })
  }


    componentDidMount() {
    this.loadAccommodations();
    this.loadTrips();
  }


  render() {
    return (
        <Router>
            <Header></Header>
            <Routes>
                <Route path={"/accommodations"} exact render={() => <Accommodations accommodations={this.state.accommodations}/>}></Route>
                <Route path={"/trips"} exact render={() => <Trips trips={this.state.trips}/>}></Route>
            </Routes>
        </Router>
    );
  }

}

export default App;
