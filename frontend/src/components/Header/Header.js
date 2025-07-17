import React from "react";
import {Link, useNavigate} from "react-router-dom";
import './Header.css'

const Header = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("jwt");  // remove JWT token on logout
    navigate("/login");              // redirect to login page
  };

  const isLoggedIn = !!localStorage.getItem("jwt");

  return (
    <nav>
      <img src={"/logo.png"} alt={"LOGO"} />
      <div>
        <Link to={"/trips"}>My Trips</Link>
        <Link to={"/profile"}>Profile</Link>
        <Link to={"/accommodations"}>All Accommodations</Link>
        {!isLoggedIn && (
          <>
            <Link to={"/login"}>Login</Link>
            <Link to={"/register"}>Register</Link>
          </>
        )}
        {isLoggedIn && (
          <button onClick={handleLogout} className="logout-button">
            Logout
          </button>
        )}
      </div>
    </nav>
  );
};

export default Header;
