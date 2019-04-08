import React, { Component } from 'react';
import axios from 'axios';


class Amis extends Component {
    constructor(props){
        super(props);
        this.state = {
            userId:props.userId,
            listFriend: [],
            listAbonnes: []
        };
    }

    getAbonnement(){
        var formData = new URLSearchParams();
        formData.append("user_key",this.props.userKey);
        console.log("http://localhost:8080/Twister/Profil/listFriend?"+formData);
        axios.get("http://localhost:8080/Twister/Profil/listFriend?"+formData).then(
            r=>{this.traiteReponse(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});
    }

    getAbonnes(){
        var formData = new URLSearchParams();
        formData.append("user_key",this.props.userKey);
        console.log("http://localhost:8080/Twister/Profil/listAbonnes?"+formData);
        axios.get("http://localhost:8080/Twister/Profil/listAbonnes?"+formData).then(
            r=>{this.traiteReponseFriend(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});
    }

    traiteReponseFriend(r){
        //ListeFriend est une liste contenant les login d'amis
        //on vérifie si on a des amis
        if(r.data.status === "OK" && r.data.amis !== undefined){
            this.setState({userKey: r.data.key});
            this.setState({listFriend: r.data.amis});
        }
    }

    traiteReponseAbonnes(r){
        //ListeFriend est une liste contenant les login d'amis
        //on vérifie si on a des amis
        if(r.data.status === "OK" && r.data.amis !== undefined){
            this.setState({userKey: r.data.key});
            this.setState({listAbonnes: r.data.amis});
        }
    }




    render(){

        this.getAbonnement();
        this.getAbonnes();
        return (
            <div className="Amis">
                <p>{this.state.listFriend.length} abonnements</p>
                <p>{this.state.listAbonnes.length} abonnés</p>
            </div>
            );
    }
} 

export default Amis;