import { combineReducers } from "redux";
import { reducer as formReducer } from "redux-form";
import authReducer from "./authReducer";
import postReducer from "./postReducer";
import userTopicsReducer from "./userTopicsReducer";
import selectedPostReducer from "./selectedPostReducer";
import commentReducer from "./commentReducer";

export default combineReducers({
    auth: authReducer,
    posts: postReducer,
    userTopics: userTopicsReducer,
    selectedPost: selectedPostReducer,
    comments: commentReducer,
    form: formReducer
});
