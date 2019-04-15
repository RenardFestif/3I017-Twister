import React, { Component } from 'react';
import axios from 'axios';


class Amis extends Component {

    _isMounted = false;

    constructor(props){
        super(props);
        

        this.handleOnClick = this.handleOnClick.bind(this);
        this.getAbonnement = this.getAbonnement.bind(this);

    }

    getAbonnement(){
        var formData = new URLSearchParams();
        formData.append("user_key",this.props.userKey);
        console.log("http://localhost:8080/Twister/Profil/listFriend?"+formData);
        axios.get("http://localhost:8080/Twister/Profil/listFriend?"+formData).then(
            r=>{this.traiteReponseFriend(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});
    }


    traiteReponseFriend(r){
        //ListeFriend est une liste contenant les login d'amis
        //on vérifie si on a des amis
        if(this._isMounted){
            console.log(r.data);
            if(r.data.status === "OK" && r.data.amis !== undefined){
                this.props.setListFriend(r.data.amis);
                this.props.setKey(r.data.new_key);
                this.props.send();
            }
        }
    }

    handleOnClick(login_ami){
        this.props.setAmi(login_ami);
        this.props.changepage("pageperso");
        
    }
    
    componentDidMount(){
        this._isMounted = true;
        this.getAbonnement();
    }

    componentWillUnmount(){
        this._isMounted = false;
    }

    render(){

        return (
            <div className="Amis">
                <h1>Liste d'Amis : {this.props.list_friend.length} </h1>
                <div>{this.props.list_friend.map((friend) => 
                    <p key={friend.login} onClick={()=> this.handleOnClick(friend.login)}>
                        {friend.login}
                    </p>
                )}
                </div>
            </div>
            );
    }
} 

export default Amis;