import React from "react";
import { Field, reduxForm } from "redux-form";
import { getUser } from "../../user";
import history from "../../history";

class CreateComment extends React.Component {

    onSubmit = formValues => {
        if (!getUser()) history.push("/login");
        this.props.onSubmit({ post: this.props.id, text: formValues.text });
        this.props.reset();
    }

    renderInput = ({ input, meta }) => {
        const className = `field ${meta.error && meta.touched ? 'error' : ''}`;
        return (
            <div className={className}>
                <textarea {...input} rows="8" placeholder="What are your thoughts?"/>
            </div>
        );
    };

    render() {
        return (
            <form className="ui form error" onSubmit={this.props.handleSubmit(this.onSubmit)} style={{width: '90%', margin: 'auto'}}>
                <Field name="text" component={this.renderInput}/>
                <div className="ui right floated buttons">
                    <button type={"submit"} className="ui button primary">Create</button>
                </div>
            </form>
        );
    }

}

export default reduxForm({
    form: 'createCommentForm'
})(CreateComment);
