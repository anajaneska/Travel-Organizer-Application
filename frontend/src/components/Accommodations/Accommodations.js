import React from "react";

const accommodations = (props) => {
    return(
        <div>
            {props.accommodations.map((term) => {
                return(
                    <div>
                        {term.location}
                    </div>
                );
            })}
        </div>
    );
}

export default accommodations;