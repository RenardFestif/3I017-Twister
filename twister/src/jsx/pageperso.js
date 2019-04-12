import React, { Component } from 'react';
import MessageSet from "./messageSet.js";
import Amis from "./amis.js";
import logo from '../images/logo.png';
import axios from 'axios';

function autoExpand(){
    document.addEventListener('input', function (event){
        if(event.target.tagName.toLowerCase() === 'textarea')
          autoExpand(event.target);
      },false);
      
      var autoExpand = function(field){
        
        field.style.height = '0px';
        var computed = window.getComputedStyle(field);
        
        var height = parseInt(computed.getPropertyValue('border-top-width'), 10)
                       + parseInt(computed.getPropertyValue('padding-top'), 10)
                       + field.scrollHeight
                       + parseInt(computed.getPropertyValue('padding-bottom'), 10)
                       + parseInt(computed.getPropertyValue('border-bottom-width'), 10);
        
        field.style.height = height + 'px';
      }
      
}
class Pageperso extends Component {
    constructor(props){
        super(props);
        this.state = {
            date:new Date(),
            listMessages:[],
            query:''
        }
        this.handleOnClick = this.handleOnClick.bind(this);
    }

    handleOnClick(){
        this.props.changepage("Acceuil");
        this.props.setconnected();
    }

    retouracceuil(){
        this.props.changepage("acceuilperso");
        this.props.setAmi("");
    }

    retourpageperso(){
        this.props.changepage("pageperso");
        this.props.setAmi("");
    }

    add_remove_affichage(ami){
        
        var boolean = false;
        this.props.list_friend.map( (friend) =>
        {
            if(friend.login === ami){
                boolean = true;
            }
        })
        if(boolean){
            return "Supprimer";
        }
        else
            return "Ajouter";
    }

    send_ajout_remove(ami){
        var formData = new URLSearchParams();
        formData.append("pseudo",ami);
        formData.append("user_key",this.props.userKey);
        if(this.add_remove_affichage(ami) === "Ajouter"){
            axios.get("http://localhost:8080/Twister/Profil/ajoutrelation?"+formData).then(r=>{this.traiteAjoutRelation(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});      
        }
        else{
            axios.get("http://localhost:8080/Twister/Profil/deleterelation?"+formData).then(r=>{this.traiteSupprRelation(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});      
        }
        
    }

    traiteAjoutRelation(r){
        console.log(r.data);
        if(r.data.status === "OK"){
            this.props.setKey(r.data.new_key);
            this.props.changepage("pageperso");
            
        }
    }

    traiteSupprRelation(r){
        console.log(r.data);
        if(r.data.status === "OK"){
            this.props.setKey(r.data.new_key);
            this.props.changepage("pageperso");
        }
    }
    
    render(){ 
        if(this.props.ami=== ""){
            /*affichage de sa page*/
            console.log(this.props.login);
            return (
                <div className="AcceuilPerso">
            <header className="sticky">
                <img id="logo" src={logo} alt="logo" />
                <div id="hLinks">
                    <button type="button" className="buttontop" onClick={()=> this.retourpageperso()}>{this.props.login}</button>
                    <button type="button" className="buttontop" onClick={()=> this.retouracceuil()}>Acceuil</button>
                </div>
                
                <form id="mess" method="GET" action="">
                    <input id = "pattern" type="text" name="pattern"/>
                </form>
                <div id="hLinks">
					<button type="button" className="buttontop" onClick={()=> this.props.deconnexion()}>Déconnexion</button>
                </div>
            </header>
    
            <div id="corpus">
    
                <nav>
                    <p>Nombre de messages écrit</p>
                    <div>{<Amis userKey={this.props.userKey} changepage={this.props.changepage} setAmi={this.props.setAmi} setKey={this.props.setKey} deconnexion={this.props.deconnexion} list_friend={this.props.list_friend} setListFriend={this.props.setListFriend}/>}</div>
                </nav>
            
                <article id="messages">
                    
                    <form id="formMess" method="GET" action =""> 
                        <textarea onKeyPress={autoExpand()} className="autoExpand" rows='3' data-min-rows='3' name="message" placeholder="Exprimez-vous !"></textarea> 
                    </form>
                    {<MessageSet userkey={this.props.userKey} setKey={this.props.setKey} listMessages={this.state.listMessages} /*shouldI={this.shouldI}*//>}
                    
                </article>
            </div>
    
    
            </div>
            );
        }
        else{
            /* affichage de la page de son ami */
        
        return( <div className="AcceuilPerso">
        <header className="sticky">
            <img id="logo" src={logo} alt="logo" />
            <div id="hLinks">
                <button type="button" className="buttontop" onClick={()=> this.retourpageperso()}>{this.props.login}</button>
                <button type="button" className="buttontop" onClick={()=> this.retouracceuil()}>Acceuil</button>
            </div>
            
            <form id="mess" method="GET" action="">
                <input id = "pattern" type="text" name="pattern"/>
            </form>
            <div id="hLinks">
                <button type="button" className="buttontop" onClick={()=> this.props.deconnexion()}>Déconnexion</button>
            </div>
        </header>

        

        <div id="corpus">
            <nav>
                <p>Nombre de messages écrit</p>
                <div>{<Amis userKey={this.props.userKey} setKey={this.props.setKey} setAmi={this.props.setAmi} changepage={this.props.changepage} deconnexion={this.props.deconnexion} list_friend={this.props.list_friend} setListFriend={this.props.setListFriend} />}</div>
            </nav>
        
            <article id="messages">
                <h1>{this.props.ami}</h1>
                <button type="button" onClick={() => this.send_ajout_remove(this.props.ami)} >{this.add_remove_affichage(this.props.ami)}</button>
                {<MessageSet userkey={this.props.userKey} setKey={this.props.setKey} listMessages={this.state.listMessages} /*shouldI={this.shouldI}*//>}
                
            </article>
        </div>


        </div>
        );
        }
    }
}

export default Pageperso;