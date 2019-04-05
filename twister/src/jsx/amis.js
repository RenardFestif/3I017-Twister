import React, { Component } from 'react';
import axios from 'axios';


class Amis extends Component {
    constructor(props){
        super(props);
        this.state = {
            userKey:props.userKey,
            userId:props.userId,
            listFriend: []
        };
    }

    getFriend(){
    }

    send(){
        var formData = new URLSearchParams();
		formData.append("user_key",this.props.user_key);
        axios.get("http://localhost:8080/Twister/Profil/listFriend?"+formData).then(r=>{this.traiteReponse(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});
    }

    traiteReponse(r){
        console.log(r.data.user_key);
        if(r.data.status === "OK"){
            this.setState({userKey: r.data.key});
            this.setState({listFriend: r.data.Resultat});
            this.props.getAmis(this.state.userKey);
            
        }
    }


    render(){

        this.send();
        return (
            <div className="Amis">
                {this.state.listFriend}
            </div>
            );
    }
} 

export default Amis;