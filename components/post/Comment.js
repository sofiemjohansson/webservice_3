import React from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux"
import { upvoteComment, downvoteComment, deleteComment } from "../../actions";
import Rating from "../common/Rating";
import { getUser } from "../../user";
import history from "../../history";

const Comment = ({ comment, upvoteComment, downvoteComment, deleteComment, post }) => {

    const deleteEntry = async id => {
        await deleteComment(id);
        history.push(`/posts/${post}`);
    };

    const renderComment = c => {

        const to = {
            pathname: `/comments/reply/${c.id}`,
            search: `?post=${c.post.id}`
        };
        const deleteTo = {
            pathname: `/delete/${c.id}`,
            search: '?type=comment',
            state: {
                text: "Are you sure you want to delete this comment?"
            },
            deleteEntry
        };
        return (
            <div className="comment">
                <div className="content">
                    <div className="avatar" style={{height: '70px', marginTop: '-20px', marginRight: '20px'}}>
                        <Rating id={c.id} upvote={upvoteComment} downvote={downvoteComment} status={c.rating.likes - c.rating.dislikes}/>
                    </div>
                    <Link to={`/users/${c.user.id}`} className="author">{`u/${c.user.username}`}</Link>
                    <div className="metadata">
                        <span className="date">Today at 5:42PM</span>
                    </div>
                    <div className="text">{c.text}</div>
                    <div className="actions">
                        <Link to={to} className="reply" style={{color: 'grey', cursor: 'pointer'}}>Reply</Link>
                        {(getUser() && c.user.id === getUser().id) ? <Link to={deleteTo} className="reply" style={{color: 'grey', cursor: 'pointer'}}>Delete</Link> : null}
                    </div>

                </div>
                {c.children.length > 0 ? renderCommentWithReplies(c.children) : <div className="ui horizontal divider"/>}
            </div>
        );
    };

    const renderCommentWithReplies = replies => {
        const renderedReplies = replies.map(r => renderComment(r));
        return <div className="comments">{renderedReplies}</div>;
    };

    return renderComment(comment);
};

export default connect(null, { upvoteComment, downvoteComment, deleteComment })(Comment);
