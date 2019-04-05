import React, { Component } from 'react';
import Acceuil from "./acceuil.js";
import Inscription from "./incription.js";
import Connexion from "./connexion.js";
import AcceuilPerso from "./acceuilperso.js"
import Pageperso from "./pageperso.js";




class MainPage extends Component{
    constructor(props){
        super(props);
<<<<<<< HEAD

        this.state = {pagecourrante:"acceuil", connected:false, key:"", id:""};
        this.changepage = this.changepage.bind(this);
        this.setConnected = this.setConnected.bind(this);
        this.setKey = this.setKey.bind(this);

        this.getacceuilperso = this.getacceuilperso.bind(this);

=======
        this.state = {pagecourrante:"acceuil", connected : false};
        this.changepage = this.changepage.bind(this);
        this.setConnected = this.setConnected.bind(this);
>>>>>>> parent of bc0ee0a... Passage de key a MessageSet
    }


    render(){
        var {connected} = this.state;
        var {pagecourrante} = this.state;

        let page;

        if (connected === true){
            if(pagecourrante === "acceuilperso")
<<<<<<< HEAD

                page = <AcceuilPerso changepage = {this.changepage} setconnected = {this.setConnected} userKey={this.state.key} getacceuilperso = {this.getacceuilperso}/> 

=======
                page = <AcceuilPerso changepage = {this.changepage} setconnected = {this.setConnected}/> 
>>>>>>> parent of bc0ee0a... Passage de key a MessageSet
            else if(pagecourrante==="pageperso")
                page = <Pageperso changepage = {this.changepage} setconnected = {this.setConnected}/>
        }else{
            if (pagecourrante === "inscription"){
                page = <Inscription changepage = {this.changepage} setconnected = {this.setConnected}/>;
            }
            else if (pagecourrante === "connexion"){
<<<<<<< HEAD

                page = <Connexion changepage = {this.changepage} setconnected = {this.setConnected} setKey = {this.setKey} getacceuilperso = {this.getacceuilperso}/>;

=======
                page = <Connexion changepage = {this.changepage} setconnected = {this.setConnected}/>;
>>>>>>> parent of bc0ee0a... Passage de key a MessageSet
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