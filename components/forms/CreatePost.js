import React from 'react';
import { Field, reduxForm } from "redux-form";
import { connect } from 'react-redux';
import { fetchTopics } from "../../actions";
import history from "../../history";
import { Select } from 'antd';
import 'antd/dist/antd.css';

const { Option } = Select;

class CreatePost extends React.Component {

    componentDidMount() {
        this.props.fetchTopics();
    }

    renderInput = ({ label, type, input, meta }) => {
        const className = `field ${meta.error && meta.touched ? 'error' : ''}`;
        return (
            <div className={className}>
                <label>{label}</label>
                <input {...input} type={type}/>
            </div>
        );
    }

    renderTextArea = ({ label, input, meta }) => {
        const className = `field ${meta.error && meta.touched ? 'error' : ''}`;
        return (
            <div className={className}>
                <label>{label}</label>
                <textarea {...input} rows="10"/>
            </div>
        );
    }

    renderTopics = topics => {
        return topics.map(t => {
            return (
                <Option key={t.name} value={t.name}>{`r/${t.name}`}</Option>
            );
        });
    }

    renderDropdown = ({ input, topics }) => {
        return (
            <React.Fragment>
                <Select
                    showSearch
                    style={{ width: '100%' }}
                    placeholder="Select a topic"
                    optionFilterProp="children"
                    onChange={input.onChange}
                    filterOption={(input, option) =>
                        option.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
                    }
                >
                    {this.renderTopics(topics)}
                </Select>
                <div className="ui horizontal divider"/>
            </React.Fragment>
        );
    }

    onSubmit = formValues => this.props.onSubmit(formValues);

    render() {
        return (
            <form className="ui form error" onSubmit={this.props.handleSubmit(this.onSubmit)}>
                <Field name="topic" component={this.renderDropdown} topics={this.props.topics}/>
                <Field label="Enter post title" name="title" type="text" component={this.renderInput}/>
                <Field label="Enter text" name="text" component={this.renderTextArea}/>
                <div className="ui right floated buttons">
                    <button className="ui red button" style={{marginRight: '10px'}} onClick={() => history.push("/")}>Cancel</button>
                    <button type={"submit"} className="ui button primary">Create</button>
                </div>
            </form>
        );
    }
}

const form = reduxForm({
    form: 'createPostForm'
})(CreatePost);

const mapStateToProps = state => {
    return { topics: Object.values(state.userTopics) };
};

export default connect(mapStateToProps, { fetchTopics })(form);

