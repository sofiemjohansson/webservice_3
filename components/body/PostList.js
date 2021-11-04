import React from "react";
import Post from "./Post";

const PostList = ({ posts, topics }) => {

    const renderPosts = () => {
        return posts.map(p => {
            return (
                <div className="segment" key={p.id}>
                    <Post post={p} topics={topics}/>
                </div>
            );
        });
    }

    return (
        <div className="ui grid">
            <div className="four wide column"/>
            <div className="eight wide column">
                <div className="ui vertical segments">{renderPosts()}</div>
            </div>
            <div className="four wide column"/>
        </div>
    );
}

export default PostList;
