import React from "react";
import { connect } from "react-redux";
import GuestHeader from "./GuestHeader";
import UserHeader from "./UserHeader";
import { getUser, removeUser } from "../../user";
import { fetchPosts, fetchUserPosts, fetchSubscribedPosts } from "../../actions";
import history from "../../history";
import Menu from "../common/Menu";

class Header extends React.Component {

    options = {
        all: {
            value: 'All',
            callback: this.props.fetchPosts
        },
        myPosts: {
            value: 'My posts',
            callback: this.props.fetchUserPosts
        },
        subscribed: {
            value: 'My topics',
            callback: this.props.fetchSubscribedPosts
        }
    }

    render() {
        const user = getUser();
        const render = (user && user.token) || this.props.isSignedIn;
        return (
            <div className="ui secondary  menu">
                <div className="item">
                    <i className="terminal icon"/>
                </div>
                <div className="item header" onClick={() => history.push("/")} style={{cursor: 'pointer'}}>
                    <h3>Reddit Clone</h3>
                </div>
                <div className="item" style={{width: '200px'}}>
                    {getUser() ? <Menu options={this.options}/> : null}
                </div>
                <div className="right menu">
                    {render ? <UserHeader username={user.username} signOut={() => removeUser()}/> : <GuestHeader/>}
                </div>
            </div>
        );
    }
}

const mapStateToProps = state => {
    return { isSignedIn: state.auth.isSignedIn };
};

export default connect(mapStateToProps, { fetchPosts, fetchUserPosts, fetchSubscribedPosts })(Header);
