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
        this.state = {
            date:new Date(),
            listMessages:[],
            query:'',
        }
        
        this.shouldI    = this.shouldI.bind(this);
        
        this.send();
    }

    onKeyPressHandler(e){
        autoExpand();
        if(e.which === 13 && e.shiftKey) {  
            this.addMessage(e.target.value);
            e.target.value='';
            e.preventDefault();
        }

    }

    addMessage(mess){
        var formData = new URLSearchParams();
        formData.append("user_key",this.props.userKey);
        formData.append("message",mess);
        //console.log("http://localhost:8080/Twister/Profil/ajoutmessage?"+formData)
        axios.get("http://localhost:8080/Twister/Profil/ajoutmessage?"+formData).then(r=>{this.traiteAddMess(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});      
        
        
    }

    traiteAddMess(r){
        console.log(r.data);
        if(r.data.status === "OK"){
            this.props.setKey(r.data.new_key);
            this.send();
            this.setState({date:new Date()});
        }else{
            
            this.props.setLogout();
            this.props.changepage("connexion");
            
        }
    }

    deconnexion(){
        var formData = new URLSearchParams();
		formData.append("userKey",this.props.userKey);
        //console.log("http://localhost:8080/Twister/Acceuil/logout?"+formData)
        axios.get("http://localhost:8080/Twister/Acceuil/logout?"+formData).then(r=>{this.traiteDeco(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});      
    }

    traiteDeco(r){
        //console.log(r.data);
        if (r.data.status === "OK"){
            this.props.setLogout();
        }
        this.props.changepage("acceuil");
    }

    send(){
        var formData = new URLSearchParams();
        
        if(this.props.userKey!==undefined)
            formData.append("userKey",this.props.userKey);
        if(this.props.userId!==undefined)
            formData.append("userId",this.props.userId);
        if(this.state.query!=='')
            formData.append("query",this.state.query);
		
        console.log("http://localhost:8080/Twister/Profil/cherchermessage?"+formData)
		axios.get("http://localhost:8080/Twister/Profil/cherchermessage?"+formData).then(r=>{this.traiteReponse(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});

		
	}

	traiteReponse(r){
        
        
		if(r.data.status==="OK"){
            //Mettre a jour la clé
            
            console.log(r.data);

            //Construction d'un tableau de tableau pour tout les messages 
            /*forme =>
            [
                [idMessage, auteur, date, content]
                [idMessage, auteur, date, content]
                [idMessage, auteur, date, content]
            ]
            */
            var messagesList    = [];
            var messageTmp      = [];
            

            Object.keys(r.data).forEach(function(key){
                if(key !== "status" && key !== "new_key" ){
                    messageTmp.push(key);
                    messageTmp.push(r.data[key]);
                    messagesList.push(messageTmp);
                }
                messageTmp      = [];
            });
            //console.log(messagesList);
            this.setState({
                listMessages:messagesList,
            })

            this.props.setKey(r.data.new_key);
            
        }else{
            this.props.setLogout();
            this.props.changepage("connexion");
        }		
    }

    shouldI(n){
        if(new Date().getMinutes()-this.state.date.getMinutes > n){
            this.setState({
                date:new Date(),
            });
            this.send();
        }
    }

    setPattern(event){
        this.setState({
            query:event.target.value,
        });
        if(event.which === 13 && !event.shiftKey) {  
            this.send();
            event.target.value='';
            this.setState({
                query:'',
            })
            event.preventDefault();
        }
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
                
                <div id="mess">
                    <input id = "pattern" type="text" name="pattern" placeholder="Recherchez les Twists de vos amis !" onInput={(event) => this.setPattern(event)}/>
                </div>
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
                        <textarea onKeyPress={(event) => this.onKeyPressHandler(event)} className="autoExpand"  name="message" placeholder="Exprimez-vous !"></textarea> 
                    </form>
                    
                    {<MessageSet userkey={this.props.userKey} setKey={this.props.setKey} listMessages={this.state.listMessages} shouldI={this.shouldI}/>}
                    
                </article>
            </div>
    
    
            </div>
        );
    }
}


export default AcceuilPerso;