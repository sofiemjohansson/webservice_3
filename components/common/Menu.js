import React, { useState } from "react";

const Menu = ({ options }) => {

    const [selected, setSelected] = useState('All');

    const onOptionChosen = key => {
        options[key].callback()
        setSelected(options[key].value);
    };

    const renderOptions = () => {
        return Object.keys(options).map(key => {
            return (
                <div key={key} className="item" data-value={key} onClick={e => onOptionChosen(key)}>{options[key].value}</div>
            );
        });
    };

    return (
        <div className="ui simple selection dropdown" style={{zIndex: '10'}}>
            <input type="hidden" name="user" />
            <div className="default text">{selected}</div>
            <i className="dropdown icon"/>
            <div className="menu">{renderOptions()}</div>
        </div>
    );
};

export default Menu;
