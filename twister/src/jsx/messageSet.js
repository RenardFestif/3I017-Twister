import React, { Component } from 'react';
import Message from "./message.js";




class MessageSet extends Component {

    
    
    render(){
        
        return (
            <div className="MessageSet">
                {this.props.listMessages.map((item, index) => 
                <Message  key = {index}
                    date={item[2]} 
                    auteur={item[1]} 
                    content={item[3]}
                    idMess={item[0]}
                    userKey={this.props.userKey}
                    setKey={this.props.setKey}
                    send={this.props.send}
                    changepage={this.props.changepage}
                    logout={this.props.logout}
                    login={this.props.login}/>)}
            </div>
            );
    }
}


export default MessageSet;