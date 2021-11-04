import React from "react";

const ToggleButton = ({ text, invertedText, callback, invertedCallback, toggle, name }) => {


    const onClicked = () => {
        toggle ? callback(name) : invertedCallback(name);
    }

    return (
        <button onClick={onClicked} className="ui right floated primary button">{toggle ? text : invertedText}</button>
    );
}

export default ToggleButton;
