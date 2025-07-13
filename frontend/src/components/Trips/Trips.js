import React from "react";

const trips = (props) => {
    return(
        <div>
            {props.trips.map((term) => {
                return(
                    <div>
                        {term.destination}
                    </div>
                );
            })}
        </div>
    );
}

export default trips;