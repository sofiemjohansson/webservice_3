import React from 'react';
import ReactDOM from 'react-dom';
import history from "../../history";

const DeleteModal = props => {

    const { match, location } = props;
    const { id } = match.params;
    const { deleteEntry, state } = location;
    const { text } = state;
    const cancel = () => history.push("/");
    const action = () => deleteEntry(id);

    return ReactDOM.createPortal(
        <div className="ui dimmer modals visible active" onClick={() => cancel()}>
            <div className="ui standard modal visible active" onClick={e => e.stopPropagation()}>
                <div className="ui placeholder segment">
                    <div className="ui icon header">
                        <i className="terminal icon"/>
                        {text}
                    </div>
                    <div className="ui buttons">
                        <div className="ui button" onClick={() => cancel()}>Cancel</div>
                        <div className="ui red button" onClick={() => action()}>Delete</div>
                    </div>
                </div>
            </div>
        </div>,
        document.getElementById("modal")
    )
};

export default DeleteModal;
