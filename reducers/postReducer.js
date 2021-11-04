import _ from 'lodash';
import {
    CREATE_POST,
    DELETE_POST,
    DOWNVOTE,
    FETCH_POSTS,
    FETCH_SUBSCRIBED_POSTS,
    FETCH_USER_POSTS,
    UPVOTE
} from "../actions/types";

export default (state = {}, action) => {
    switch (action.type) {
        case FETCH_POSTS:
            return { ...state, ..._.mapKeys(action.payload, 'id') };
        case UPVOTE:
            return { ...state, [action.payload.id]: action.payload };
        case DOWNVOTE:
            return { ...state, [action.payload.id]: action.payload };
        case FETCH_USER_POSTS:
            return { ..._.mapKeys(action.payload, 'id') };
        case FETCH_SUBSCRIBED_POSTS:
            return { ..._.mapKeys(action.payload, 'id') };
        case CREATE_POST:
            return { ...state, [action.payload.id]: action.payload };
        case DELETE_POST:
            return _.omit(state, action.payload);
        default:
            return state;
    }
}
