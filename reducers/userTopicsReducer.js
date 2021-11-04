import _ from 'lodash';
import {FETCH_TOPICS, SUBSCRIBE, UNSUBSCRIBE} from "../actions/types";

export default (state = {}, action) => {
    switch (action.type) {
        case FETCH_TOPICS:
            return { ...state, ..._.mapKeys(action.payload, 'id') };
        case SUBSCRIBE:
            return { ..._.mapKeys(action.payload, 'id') };
        case UNSUBSCRIBE:
            return { ..._.mapKeys(action.payload, 'id') };
        default:
            return state;
    }
};
