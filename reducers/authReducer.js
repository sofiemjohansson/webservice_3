import { SIGN_OUT, SIGN_IN } from "../actions/types";

const DEFAULT_STATE = {
    isSignedIn: null,
    token: null,
    id: null
};

export default (state = DEFAULT_STATE, action) => {
    switch (action.type) {
        case SIGN_IN:
            return { isSignedIn: true, token: action.payload.token, id: action.payload.id };
        case SIGN_OUT:
            return { isSignedIn: false, token: null, id: null };
        default:
            return state;
    }
};
