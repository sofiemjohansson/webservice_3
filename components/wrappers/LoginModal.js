import React from "react";
import ReactDOM from 'react-dom';
import { Link } from "react-router-dom";
import { connect } from 'react-redux';
import { fetchOnSignIn } from '../../actions/index'
import Login from "../forms/Login";
import history from "../../history";

const LoginModal = props => {

    const onSubmit = formValues => props.fetchOnSignIn(formValues);
    const onBackgroundClicked = e => props.onBackgroundClicked();

    return ReactDOM.createPortal(
        <div className="ui dimmer modals visible active" onClick={onBackgroundClicked}>
            <div className="ui standard modal visible active" onClick={e => e.stopPropagation()}>
                <div className="ui placeholder segment">
                    <div className="ui two column stackable center aligned grid" style={{backgroundColor: 'white'}}>
                        <div className="ui vertical divider">LOGIN</div>
                        <div className="middle aligned row">
                            <div className="column">
                                <div className="ui icon header">
                                    <i className="user large icon"/>
                                </div>
                            </div>
                            <div className="column">
                                <div className="item">
                                    <div className="ui right floated icon button" style={{backgroundColor: 'white'}} onClick={e => history.push("/")}>
                                        <i className="close icon"/>
                                    </div>
                                    <div className="ui horizontal divider"/>
                                    <div className="header">
                                        <h4>By continuing, you agree to our User agreement and Privacy policy.</h4>
                                    </div>
                                </div>
                                <div className="ui horizontal divider"/>
                                <div className="ui horizontal divider"/>
                                <div className="item">
                                    <Login onSubmit={onSubmit}/>
                                </div>
                                <div className="ui horizontal divider"/>
                                <div className="ui horizontal divider"/>
                                <div className="ui horizontal divider"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>,
        document.getElementById("modal")
    );
};

export default connect(null, { fetchOnSignIn })(LoginModal);
