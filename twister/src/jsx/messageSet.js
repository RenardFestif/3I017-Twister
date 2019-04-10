import React, { Component } from 'react';
import Message from "./message.js";




class MessageSet extends Component {
    
    render(){

        //Rafraichissement 5 min
        this.props.shouldI(5);

        return (
            <div className="MessageSet">
                {this.props.listMessages.map(item => <Message date={item[1].date} auteur={item[1].auteur} content={item[1].content}/>)}
            </div>
            );
    }
}


export default MessageSet;