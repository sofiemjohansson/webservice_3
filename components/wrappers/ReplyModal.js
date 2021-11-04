import React from "react";
import ReactDOM from "react-dom";
import { connect } from 'react-redux';
import { replyComment } from "../../actions";
import history from "../../history";
import CreateComment from "../forms/CreateComment";

const ReplyModal = ({ match, location, replyComment }) => {

    const { id } = match.params;
    const postId = location.search.match(/\d+/)[0];

    const cancel = () => history.push(`/posts/${postId}`)
    const action = async formValues => {
        await replyComment(id, { post: postId, text: formValues.text });
        cancel();
    }

    return ReactDOM.createPortal(
        <div className="ui dimmer modals visible active" onClick={() => cancel()}>
            <div className="ui standard modal visible active" onClick={e => e.stopPropagation()} style={{paddingBottom: '50px'}}>
                <div className="ui item" style={{marginTop: '50px', marginBottom: '50 px'}}>
                    <CreateComment onSubmit={action}/>
                </div>
            </div>
        </div>,
        document.getElementById("modal")
    )
};

export default connect(null, { replyComment })(ReplyModal);
