import React from "react";
import { Link } from 'react-router-dom';
import { connect } from "react-redux";
import { upvote, downvote, subscribeToTopic, unsubscribeToTopic, deletePost } from "../../actions";
import { getUser } from "../../user";
import Rating from "../common/Rating";
import ToggleButton from "../common/ToggleButton";

const Post = ({ post, upvote, downvote, subscribeToTopic, unsubscribeToTopic, deletePost,topics }) => {

    const toggle = () => {
        if (!getUser()) return true;
        let show = true;
        topics.forEach(t => {
            if (t.id === post.topic.id) show = false;
        });
        return show;
    };

    const deleteEntry = id => {
        deletePost(id);
    }

    const renderDeleteButton = () => {
        if (!getUser() || post.user.id !== getUser().id) return null;
        const to = {
            pathname: `/delete/${post.id}`,
            search: '?type=post',
            state: {
                text: "Are you sure you want to delete this post?"
            },
            deleteEntry
        };
        return (
            <Link to={to} className="ui red button">Delete</Link>
        );
    };

    return (
            <div className="ui icon message" style={{backgroundColor: 'white'}}>
                <Rating id={post.id} upvote={upvote} downvote={downvote} status={post.rating.likes - post.rating.dislikes}/>
                <div className="content" style={{paddingLeft: '10px', paddingRight: '10px'}}>
                    <span>
                        <p>
                            <span  style={{marginRight: '5px'}}>{`r/${post.topic.name}`}</span>
                            Posted by <span>{`u/${post.user.username}`}</span>
                        </p>
                    </span>
                    <Link to={`/posts/${post.id}`} className="header" style={{marginBottom: '10px', marginTop: '10px'}}>{post.title}</Link>
                    <div className="meta">
                        <Link to={`/posts/${post.id}`} className="ui basic button">
                            <i className="comment icon"/>
                            {`${post.comments.length} comments`}
                        </Link>
                        {renderDeleteButton()}
                    </div>
                </div>
                <ToggleButton text="Join" invertedText="Leave" callback={subscribeToTopic} invertedCallback={unsubscribeToTopic} toggle={toggle()} name={post.topic.name}/>
            </div>
    );
};

export default connect(null, { upvote, downvote, subscribeToTopic, unsubscribeToTopic, deletePost })(Post);
