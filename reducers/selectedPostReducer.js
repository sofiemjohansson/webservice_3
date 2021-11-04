import {
    CREATE_COMMENT, DELETE_COMMENT,
    DOWNVOTE,
    DOWNVOTE_COMMENT,
    FETCH_POST,
    REPLY_COMMENT,
    UPVOTE,
    UPVOTE_COMMENT
} from "../actions/types";

export default (state = {}, action) => {
    switch (action.type) {
        case FETCH_POST:
            return action.payload;
        case UPVOTE_COMMENT:
            const commentsAfterUpvote = state.comments.map(c => (c.id === action.payload.id ? action.payload : c));
            return { ...state, comments: commentsAfterUpvote };
        case DOWNVOTE_COMMENT:
            const comments = state.comments.map(c => (c.id === action.payload.id ? action.payload : c));
            return { ...state, comments };
        case UPVOTE:
            return action.payload;
        case DOWNVOTE:
            return action.payload;
        case CREATE_COMMENT:
            const addedComments = state.comments.concat(action.payload);
            return { ...state, comments: addedComments};
        case REPLY_COMMENT:
            const { id, data } = action.payload;
            const repliedComments = state.comments.map(c => {
                if (c.id === id) c.children.concat(data);
                return c;
            });
            return { ...state, comments: repliedComments };
        case DELETE_COMMENT:
            const deletedComments = state.comments.map(c => (c.id === action.payload.id) ? action.payload : c);
            return { ...state, comments: deletedComments};
        default:
            return state;
    }
};
