import {CREATE_COMMENT, DELETE_COMMENT, REPLY_COMMENT} from "../actions/types";

export default (state = {}, action) => {
    switch (action.type) {
        case CREATE_COMMENT:
            return { ...state, [action.payload.id]: action.payload };
        case REPLY_COMMENT:
            const { id, data } = action.payload;
            state[id].children.concat(data);
            return { ...state, [data.id]: data };
        case DELETE_COMMENT:
            return { ...state, [action.payload.id]: action.payload };
        default:
            return state;
    }
};
