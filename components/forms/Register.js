import React from 'react';
import { Field, reduxForm } from "redux-form";

class Register extends React.Component {

    renderInput = ({ label, type, input, meta }) => {
        const className = `field ${meta.error && meta.touched ? 'error' : ''}`;
        return (
            <div className={className}>
                <label>{label}</label>
                <input {...input} type={type}/>
            </div>
        );
    }

    onSubmit = formValues => this.props.onSubmit(formValues);

    render() {
        return (
            <form className="ui form error" onSubmit={this.props.handleSubmit(this.onSubmit)}>
                <Field label="Enter username" type="text" name="username" component={this.renderInput}/>
                <Field label="Enter password" type="password" name="password" component={this.renderInput}/>
                <Field label="Enter email" type="text" name="email" component={this.renderInput}/>
                <button className="ui button primary">Sign up</button>
            </form>
        );
    }
}

export default reduxForm({
    form: 'registerForm'
})(Register);
