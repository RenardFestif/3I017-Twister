import React, { Component } from 'react';

class Message extends Component {
    constructor(props){
        super(props);
        this.state = {message: props.msg};
        
    }
    
    render(){
        return (
            <li className="Message">
				<div className="truc" >
                    <span>
                        <span className = 'username'>@JP</span>
                        <span>{this.state.message}</span>
                        <span className = "datemess">13/13/13 13:13</span>
                    </span>
                </div>
			</li>
            );
    }
}

export default Message;