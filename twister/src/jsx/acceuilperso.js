import React, { Component } from 'react';
import MessageSet from "./messageSet.js";
import logo from '../images/logo.png';
import Amis from "./amis.js";
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



class AcceuilPerso extends Component {
    constructor(props){
        super(props)
        
        this.getAmis = this.getAmis.bind(this);
        

    }

    deconnexion(){
        var formData = new URLSearchParams();
		formData.append("userKey",this.props.userKey);
        console.log("http://localhost:8080/Twister/Acceuil/logout?"+formData)
        axios.get("http://localhost:8080/Twister/Acceuil/logout?"+formData).then(r=>{this.traiteDeco(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});      
    }

    traiteDeco(r){
        console.log(r.data);
        if (r.data.status === "OK"){
            this.props.setLogout();
            this.props.changepage("acceuil");
        }
    }

    getAmis(cle){
        
    }



    render(){
        
        //Tester les etats pour voir si il fait afficher les message d'acceuil ou bien ceux du profil (Idem pour la side navbar)
        
        return (
            <div className="AcceuilPerso">
            <header className="sticky">
                <img id="logo" src={logo} alt="logo" />
                <div id="hLinks">
                    <button type="button" className="buttontop" onClick={()=> this.props.changepage("acceuilperso")}>Acceuil</button>
                </div>
                
                <form id="mess" method="GET" action="">
                    <input id = "pattern" type="text" name="pattern"/>
                </form>
                <div id="hLinks">
					<button type="button" className="buttontop" onClick={()=> this.deconnexion()}>Déconnexion</button>
                    <button type="button" className="buttontop" onClick={()=> this.props.changepage("pageperso")}>{this.props.login}</button>
                </div>
            </header>
    
            
            

        
            <div id="corpus">
    
                <nav>
                    <p>Nombre de messages écrit</p>
                    <p>Nombre d'abonnés</p>
                    <div>{<Amis getAmis={this.getAmis} userKey={this.props.userKey} setKey={this.props.setKey}/>}</div>
                    <p>on ajoutera des amis ici</p>
                    <form id="amis" method="GET" > 
                        <input id="searchFriend" type="text" name="pattern"/>
                        
                    </form>
                </nav>
            
                <article id="messages">
                    
                    <form id="formMess" method="GET" action =""> 
                        <textarea onKeyPress={autoExpand()} className="autoExpand"  name="message" placeholder="Exprimez-vous !"></textarea> 
                    </form>
                    {<MessageSet userkey={this.props.userKey} setKey={this.props.setKey}/>}
                    
                </article>
            </div>
    
    
            </div>
        );
    }
}


export default AcceuilPerso;