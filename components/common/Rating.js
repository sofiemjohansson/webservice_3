import React  from "react";
import { getUser } from "../../user";
import history from "../../history";

const Rating = props => {

    const { id, upvote, downvote, status} = props;

    const onUpvote = () => {
        checkCredentials();
        upvote(id);
    };

    const onDownvote = () => {
        checkCredentials();
        downvote(id);
    };

    const checkCredentials = () => {
        const user = getUser();
        if (!user || !user.token) history.push("/login");
    }
    return (
        <div className="ui vertical icon buttons">
            <button onClick={onUpvote} className={`ui icon button`} style={{backgroundColor: 'white'}}>
                <i className={`chevron up icon`}/>
            </button>
            <button disabled={true} className="ui button" style={{backgroundColor: 'white'}}>{status}</button>
            <button onClick={onDownvote} className={`ui icon button`} style={{backgroundColor: 'white'}}>
                <i className={`chevron down icon`}/>
            </button>
        </div>
    );
}


export default Rating;
