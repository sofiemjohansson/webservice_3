import React from "react";
import { connect } from 'react-redux';

class PostFilter extends React.Component {

    render() {
        return (
            <div className="ui two item menu">
                <div className="item">
                    <button className="ui labeled icon button">
                        <i className="hotjar large icon"/>
                        Hot
                    </button>
                </div>
                <div className="item">
                    <button className="ui labeled icon button">
                        <i className="chart line large icon"/>
                        Top
                    </button>
                </div>
            </div>
        );
    }
}

const mapStateToProps = state => {
    return { posts: Object.values(state.posts) };
};

export default connect(mapStateToProps)(PostFilter);
