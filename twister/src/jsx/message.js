import React, { Component } from 'react';

class Message extends Component {
    constructor(props){
        super(props);
        this.state = {message: props.content};
        
    }
    
    render(){
        return (
            <li className="Message">
                <pre>@{this.props.auteur} </pre>
                <pre>{this.state.message} </pre>
                <pre>{this.props.date}</pre>

			</li>
            );
    }
}

export default Message;