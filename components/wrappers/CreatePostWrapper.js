import React from "react";
import CreatePost from "../forms/CreatePost";
import { connect } from 'react-redux';
import { createPost } from "../../actions";

class CreatePostWrapper extends React.Component {

    render() {
        return (
            <div className="ui grid">
                <div className="three column row">
                    <div className="column"/>
                    <div className="column">
                        <div className="ui header"><h2>Create a post</h2></div>
                        <CreatePost onSubmit={this.props.createPost}/>
                    </div>
                    <div className="column"/>
                </div>
            </div>
        );
    }
}

export default connect(null, { createPost })(CreatePostWrapper);
