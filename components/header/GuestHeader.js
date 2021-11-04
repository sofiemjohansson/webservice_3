import React from "react";
import { Link } from "react-router-dom";

export default () => {
    return (
        <React.Fragment>
            <div className="ui item">
                <Link to="/login" className="ui button">Log in</Link>
            </div>
            <div className="ui item">
                <Link to="/signup"  className="ui primary button">Sign up</Link>
            </div>
        </React.Fragment>
    );
}
