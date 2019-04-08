import React, { Component } from 'react';
import axios from 'axios';


class Amis extends Component {
    constructor(props){
        super(props);
        this.state = {
            userId:props.userId,
            listFriend: []
        };
    }

    getFriend(){

    }

    send(){
        var formData = new URLSearchParams();
        formData.append("user_key",this.props.userKey);
        console.log("http://localhost:8080/Twister/Profil/listFriend?"+formData);
        axios.get("http://localhost:8080/Twister/Profil/listFriend?"+formData).then(
            r=>{this.traiteReponse(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});
    }

    traiteReponse(r){
        //ListeFriend est une liste contenant les login d'amis
        //on vérifie si on a des amis
        if(r.data.status === "OK" && r.data.login !== undefined){
            this.setState({userKey: r.data.key});
            this.setState({listFriend: this.state.listFriend.push(r.data.login)});
        }
    }


    render(){

        this.send();
        return (
            <div className="Amis">
                {this.state.listFriend.length}
            </div>
            );
    }
} 

export default Amis;