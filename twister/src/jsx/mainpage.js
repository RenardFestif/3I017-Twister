import React, { Component } from 'react';
import Acceuil from "./acceuil.js";
import Inscription from "./incription.js";
import Connexion from "./connexion.js";
import AcceuilPerso from "./acceuilperso.js"
import Pageperso from "./pageperso.js";




class MainPage extends Component{
    constructor(props){
        super(props);
        this.state = {pagecourrante:"acceuilperso", connected : false, login: "", cle:"", id:""};
        this.changepage = this.changepage.bind(this);
        this.setConnected = this.setConnected.bind(this);
        this.getacceuilperso = this.getacceuilperso.bind(this);
    }


    render(){
        var {connected} = this.state;
        var {pagecourrante} = this.state;

        let page;

        if (connected === true){
            if(pagecourrante === "acceuilperso")
                page = <AcceuilPerso changepage = {this.changepage} setconnected = {this.setConnected} getacceuilperso = {this.getacceuilperso}/> 
            else if(pagecourrante==="pageperso")
                page = <Pageperso changepage = {this.changepage} setconnected = {this.setConnected}/>
        }else{
            if (pagecourrante === "inscription"){
                page = <Inscription changepage = {this.changepage} setconnected = {this.setConnected}/>;
            }
            else if (pagecourrante === "connexion"){
                page = <Connexion changepage = {this.changepage} setconnected = {this.setConnected} getacceuilperso = {this.getacceuilperso}/>;
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

    setConnected(){
        this.setState({connected:!this.state.connected});
    }

    getacceuilperso(k, identifiant, log){
        this.setState({login: log,id:identifiant, cle:k});
    }



}

export default MainPage;