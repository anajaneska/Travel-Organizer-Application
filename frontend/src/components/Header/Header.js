import React from "react";
import {Link} from "react-router-dom";

const header = (props) => {
    return(
        <nav>
            <img src={"/"} alt={"LOGO"}/>
            <div>
                <Link to={"/trips"}>My Trips</Link>
                <Link to={"/profile"}>Profile</Link>
            </div>
        </nav>
    );
}

export default header;