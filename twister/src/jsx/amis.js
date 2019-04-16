import React, { Component } from 'react';


class Amis extends Component {

    _isMounted = false;

    constructor(props){
        super(props);
        

        this.handleOnClick = this.handleOnClick.bind(this);

    }


    handleOnClick(login_ami){
        this.props.setAmi(login_ami);
        this.props.changepage("pageperso");
        this.props.send();
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
                <h5 className="display-4">Mes amis</h5>
                <ul className="list-unstyled" >{this.props.list_friend.map((friend) => 
                    <li className="h4 text-muted" key={friend[0].login} onClick={()=> this.handleOnClick(friend[0].login)}>
                        {friend[0].login}
                    </li>
                )}
                </ul>
            </div>
            );
    }
} 

export default Amis;