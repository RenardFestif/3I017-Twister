import React, { Component } from 'react';
import Acceuil from "./acceuil.js";
import Inscription from "./incription.js";
import Connexion from "./connexion.js";
import AcceuilPerso from "./acceuilperso.js"
import Pageperso from "./pageperso.js";
import axios from 'axios';


class MainPage extends Component{
    constructor(props){
        super(props);

        this.state = {pagecourrante:"acceuil", connected:false, key:"", id:"", login:"", ami:"", list_friend:[]};

        this.changepage = this.changepage.bind(this);
        this.setConnected = this.setConnected.bind(this);
        this.setKey = this.setKey.bind(this);
        this.setUser = this.setUser.bind(this);
        this.setLogout = this.setLogout.bind(this);
        this.setAmi = this.setAmi.bind(this);
        this.setListFriend = this.setListFriend.bind(this);
        this.deconnexion = this.deconnexion.bind(this);
        this.chercheAmi = this.chercheAmi.bind(this);
    }


    render(){
        var {connected} = this.state;
        var {pagecourrante} = this.state;
        
        
        let page;

        if (connected === true){
            if(pagecourrante === "acceuilperso")

                page = <AcceuilPerso changepage = {this.changepage} setLogout = {this.setLogout} userKey={this.state.key} setKey = {this.setKey} setAmi={this.setAmi} userId={this.state.id} login={this.state.login} ami={this.state.ami} list_friend={this.state.list_friend} deconnexion={this.deconnexion} setListFriend={this.setListFriend} chercheAmi={this.chercheAmi}/> 

            else if(pagecourrante==="pageperso")
                page = <Pageperso changepage = {this.changepage} setLogout = {this.setLogout} userKey={this.state.key} setKey = {this.setKey} setAmi = {this.setAmi} userId = {this.state.id} login={this.state.login} ami={this.state.ami} list_friend={this.state.list_friend} deconnexion={this.deconnexion} setListFriend={this.setListFriend} chercheAmi={this.chercheAmi}/>;

        }else{
            if (pagecourrante === "inscription"){
                page = <Inscription changepage = {this.changepage} setconnected = {this.setConnected}/>;
            }
            else if (pagecourrante === "connexion"){

                page = <Connexion changepage = {this.changepage} setconnected = {this.setConnected} setKey = {this.setKey} setUser= {this.setUser}/>;

            }
            else{
                page = <Acceuil changepage = {this.changepage}/> 
            }

        }
        return( 
            <div>{page}</div>
        );
    }

    changepage(nomPage){
        this.setState({pagecourrante:nomPage});
    }

    setKey(newkey){
        this.setState({key:newkey});
    }

    setConnected(){
        this.setState({connected:!this.state.connected});
    }

    setUser(identifiant, log){
        this.setState({login: log,id:identifiant, });
    }

    setAmi(ami){
        this.setState({ami: ami});
    }

    setListFriend(list_ami){
        this.setState({list_friend:list_ami});
    }

    setLogout(){
        this.setState({login: "",id:"",key:"", ami:""});
        this.setConnected();
    }

    deconnexion(){
        var formData = new URLSearchParams();
        formData.append("userKey",this.state.key);
        console.log("http://localhost:8080/Twister/Acceuil/logout?"+formData);
        axios.get("http://localhost:8080/Twister/Acceuil/logout?"+formData).then(r=>{this.traiteDeco(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});      
    }

    traiteDeco(r){
        console.log(r.data);
        if (r.data.status === "OK"){
            this.setLogout();
        }
        this.changepage("acceuil");
    }

    chercheAmi(){
        console.log(this.state.ami);
        var formData = new URLSearchParams();
        formData.append("pseudo", this.state.ami);
        formData.append("user_key", this.state.key);
        console.log("http://localhost:8080/Twister/Profil/searchFriend?"+formData);
        axios.get("http://localhost:8080/Twister/Profil/searchFriend?"+formData).then(r=>{this.traiteChercheAmi(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});
    }

    traiteChercheAmi(r){
        console.log(r.data);
        if(r.data.status === "OK"){
            this.setKey(r.data.new_key);
            this.changepage("pageperso");
        }
    }



}

export default MainPage;