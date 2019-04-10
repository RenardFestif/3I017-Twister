import React, { Component } from 'react';
import axios from 'axios';


class Amis extends Component {
    constructor(props){
        super(props);
        this.state = {
            listFriend: []
        };
        this.handleOnClick = this.handleOnClick.bind(this);

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
        console.log(r.data);
        if(r.data.status === "OK" && r.data.amis !== undefined){
            this.setState({userKey: r.data.new_key});
            this.setState({listFriend: r.data.amis});
        }
    }

    handleOnClick(login_ami){
        this.setLogin(login_ami);
        this.props.changepage("pageperso");
        
    }

    render(){

        this.getAbonnement();
        return (
            <div className="Amis">
                <h1>Liste d'Amis : {this.state.listFriend.length} </h1>
                <div>{this.state.listFriend.map((friend) => 
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