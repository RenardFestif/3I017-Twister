import React, { Component } from 'react';
import axios from 'axios';


class Amis extends Component {

    _isMounted = false;

    constructor(props){
        super(props);
        

        this.handleOnClick = this.handleOnClick.bind(this);

    }


    handleOnClick(login_ami){
        this.props.setAmi(login_ami);
        this.props.changepage("pageperso");
        
    }
    
    componentDidMount(){
        this._isMounted = true;
        this.props.getAbonnement(this._isMounted);
    }

    componentWillUnmount(){
        this._isMounted = false;
    }

    render(){

        return (
            <div className="Amis">
                <h1>Liste d'Amis : {this.props.list_friend.length} </h1>
                <div>{this.props.list_friend.map((friend) => 
                    <p key={friend[0].login} onClick={()=> this.handleOnClick(friend[0].login)}>
                        {friend[0].login}
                    </p>
                )}
                </div>
            </div>
            );
    }
} 

export default Amis;