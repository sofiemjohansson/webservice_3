import {
    SIGN_OUT,
    SIGN_IN,
    FETCH_POSTS,
    UPVOTE,
    DOWNVOTE,
    FETCH_TOPICS,
    SUBSCRIBE,
    UNSUBSCRIBE,
    FETCH_USER_POSTS,
    CREATE_POST,
    DELETE_POST,
    FETCH_POST,
    UPVOTE_COMMENT,
    DOWNVOTE_COMMENT,
    CREATE_COMMENT,
    REPLY_COMMENT,
    DELETE_COMMENT, REGISTER
} from "./types";
import reddit from "../api/reddit";
import history from "../history";
import { getUser, addUser, removeUser } from "../user";

const redirect = () => {
    if (!getUser()) {
        history.push('/login');
        return false;
    }
    return true;
}

export const signIn = formValues => {
    const user = getUser();
    if (user && user.token) {
        history.push("/");
        return {
            type: SIGN_IN,
            payload: user
        };
    }
    return async dispatch => {
        const response = await reddit.post("/api/auth/login", formValues);
        addUser(response.data);
        dispatch({ type:SIGN_IN, payload: response.data });
        history.push("/");
    };
};

export const signOut = () => {
    removeUser();
    return {
        type: SIGN_OUT
    };
};

export const fetchOnSignIn = formValues => async dispatch => {
    await dispatch(signIn(formValues));
    dispatch(fetchPosts());
    dispatch(fetchTopics());
}

export const fetchPosts = () => async dispatch => {
    const response = await reddit.get("/api/guest/posts");
    dispatch( {type: FETCH_POSTS, payload: response.data} );
};

export const upvote = id => async dispatch => {
    if (!redirect()) return;
    try {
        const response = await reddit.post(`/api/posts/upvote?post=${id}`, {}, {
            headers: {
                Authorization: `Bearer ${getUser().token}`
            }
        });
        dispatch({ type: UPVOTE, payload: response.data });
    }
    catch (err) {

    }
};

export const downvote = id => async dispatch => {
    if (!redirect()) return;
    try {
        const response = await reddit.post(`/api/posts/downvote?post=${id}`, {}, {
            headers: {
                Authorization: `Bearer ${getUser().token}`
            }
        });
        dispatch({ type: DOWNVOTE, payload: response.data });
    }
    catch (err) {

    }
};

export const fetchTopics = () => async dispatch => {
    const response = await reddit.get("/api/users/topics", {
        headers: {
            Authorization: `Bearer ${getUser().token}`
        }
    });
    dispatch({ type: FETCH_TOPICS, payload: response.data });
};

export const subscribeToTopic = topic => async dispatch => {
    if (!redirect()) return;
    try {
        const response = await reddit.post(`/api/topic/subscribe?topic=${topic}`, {}, {
            headers: {
                Authorization: `Bearer ${getUser().token}`
            }
        });
        dispatch({ type: SUBSCRIBE, payload: response.data });
    }
    catch (err) {}
};

export const unsubscribeToTopic = topic => async dispatch => {
    if (!redirect()) return;
    try {
        const response = await reddit.post(`/api/topic/unsubscribe?topic=${topic}`, {}, {
            headers: {
                Authorization: `Bearer ${getUser().token}`
            }
        });
        dispatch({ type: UNSUBSCRIBE, payload: response.data });
    }
    catch (err) {}
};

export const fetchUserPosts = () => async dispatch => {
    try {
        const response = await reddit.get(`/api/users/posts`, {
            headers: {
                Authorization: `Bearer ${getUser().token}`
            }
        });
        dispatch({ type: FETCH_USER_POSTS, payload: response.data });
    }
    catch (err) {}
};

export const fetchSubscribedPosts = () => async dispatch => {
    if (!redirect()) return;
    try {
        const response = await reddit.get(`/api/users/subscribed`, {
            headers: {
                Authorization: `Bearer ${getUser().token}`
            }
        });
        dispatch({ type: FETCH_USER_POSTS, payload: response.data });
    }
    catch (err) {}
};

export const createPost = formValues => async dispatch => {
    if (!redirect()) return;
    const { topic, title, text } = formValues;
    const response = await reddit.post(`/api/posts/create?topic=${topic}`, { title, text }, {
        headers: {
            Authorization: `Bearer ${getUser().token}`
        }
    });
    dispatch({ type: CREATE_POST, payload: response.data });
    history.push("/");
};

export const deletePost = post => async dispatch => {
    if (!redirect()) return;
    await reddit.delete(`/api/posts/delete?post=${post}`, {
        headers: {
            Authorization: `Bearer ${getUser().token}`
        }
    });
    await dispatch({ type: DELETE_POST, payload: post});
    history.push("/");
};

export const fetchPost = id => async dispatch => {
    const response = await reddit.get(`/api/guest/post/${id}`);
    dispatch({ type: FETCH_POST, payload: response.data });
};

export const upvoteComment = id => async dispatch => {
    if (!redirect()) return;
    try {
        const response = await reddit.post(`/api/comments/upvote?comment=${id}`, {}, {
            headers: {
                Authorization: `Bearer ${getUser().token}`
            }
        });
        dispatch({ type: UPVOTE_COMMENT, payload: response.data });
    }
    catch (err) {}
};

export const downvoteComment = id => async dispatch => {
    if (!redirect()) return;
    try {
        const response = await reddit.post(`/api/comments/downvote?comment=${id}`, {}, {
            headers: {
                Authorization: `Bearer ${getUser().token}`
            }
        });
        dispatch({ type: DOWNVOTE_COMMENT, payload: response.data });
    }
    catch (err) {}
};

export const createComment = formValues => async dispatch => {
    if (!redirect()) return;
    try {
        const response = await reddit.post(`/api/comments/comment`, formValues, {
            headers: {
                Authorization: `Bearer ${getUser().token}`
            }
        });
        dispatch({ type: CREATE_COMMENT, payload: response.data });
    }
    catch (err) {}
};

export const replyComment = (id, formValues) => async dispatch => {
    if (!redirect()) return;
    try {
        const response = await reddit.post(`/api/comments/reply/${id}`, formValues, {
            headers: {
                Authorization: `Bearer ${getUser().token}`
            }
        });
        dispatch({ type: REPLY_COMMENT, payload: { data: response.data, id: id }});
    }
    catch (err) {}
};

export const deleteComment = id => async dispatch => {
    if (!redirect()) return;
    try {
        const response = await reddit.delete(`/api/comments/comment/${id}`, {
            headers: {
                Authorization: `Bearer ${getUser().token}`
            }
        });
        dispatch({ type: DELETE_COMMENT, payload: response.data });
    }
    catch (err) {}
};

export const register = data => async dispatch => {
    try {
        const response = await reddit.post(`/api/auth/register`, data );
        dispatch({ type: REGISTER, payload: response.data });
        history.push('/');
    }
    catch (err) {}
}
