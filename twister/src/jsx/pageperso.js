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
            query:'',
            delMess:true
        }
        this.handleOnClick = this.handleOnClick.bind(this);
        this.send = this.send.bind(this);
        this.getAbonnement = this.getAbonnement.bind(this);
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
        {if(friend[0].login === ami){
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
            axios.get("http://localhost:8080/Twister/Profil/ajoutrelation?"+formData).then(r=>{this.traiteAjoutRelation(r); }).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});      
        }
        else{
            axios.get("http://localhost:8080/Twister/Profil/deleterelation?"+formData).then(r=>{this.traiteSupprRelation(r);}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});      
        }
        
    }

    traiteAjoutRelation(r){
        if(r.data.status === "OK"){
            this.props.setKey(r.data.new_key);
            this.props.changepage("pageperso");
            this.getAbonnement();
        }
    }

    traiteSupprRelation(r){
        console.log(r.data);
        if(r.data.status === "OK"){
            this.props.setKey(r.data.new_key);
            this.props.changepage("pageperso");
            this.getAbonnement();
        }
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
                this.props.setListFriend(r.data.amis);
                this.props.setKey(r.data.new_key);
                //this.send();
            }
    }

    send(){
    
        var formData = new URLSearchParams();
        console.log(this.props.userId); 
        if(this.props.userKey!==undefined)
            formData.append("userKey",this.props.userKey);
        if(this.props.userId!==undefined && this.props.ami=== "")
            formData.append("userId",this.props.userId);
        else
            formData.append("userId",this.props.ami);
        
        if(this.state.query!=='')
            formData.append("query",this.state.query);
		
        console.log("http://localhost:8080/Twister/Profil/cherchermessage?"+formData)
		axios.get("http://localhost:8080/Twister/Profil/cherchermessage?"+formData).then(r=>{this.traiteReponse(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});
		
	}

	traiteReponse(r){
        console.log(r.data);
		if(r.data.status==="OK"){
            //Mettre a jour la clé
            if(r.data.Resultat!==undefined){
                alert("Pas de Resultat pour la recherche");
            }

            this.props.setKey(r.data.new_key);
            
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
            
            Object.keys(r.data.messages).forEach(function(key){
                
                messageTmp.push(r.data.messages[key].messID);
                messageTmp.push(r.data.messages[key].auteur);
                messageTmp.push(r.data.messages[key].date);
                messageTmp.push(r.data.messages[key].content);
                messagesList.push(messageTmp);
                messageTmp = [];
            });
            this.setState({
                listMessages:messagesList,
            });             
        }   
    }
    
    render(){ 
        if(this.props.ami=== ""){
            return (
                <div className="AcceuilPerso">
                    <header className="sticky">
                        <img id="logo" src={logo} alt="logo" />
                        <div id="hLinks">
                            
                        </div>
                
                            <div id="mess">
                                <input id = "pattern" type="text" name="pattern"/>
                            </div>
                        <div id="hLinks">
					        <button type="button" className="buttontop" onClick={()=> this.props.deconnexion()}>Déconnexion</button>
                            <button type="button" className="buttontop" onClick={()=> this.retouracceuil()}>Acceuil</button>
                        </div>
                    </header>
    
                    <div id="corpus">
                        <nav>
                            <p>{this.state.listMessages.length} posts</p>
                            <div>{<Amis userKey={this.props.userKey} 
                                        changepage={this.props.changepage} 
                                        setAmi={this.props.setAmi} 
                                        setKey={this.props.setKey} 
                                        deconnexion={this.props.deconnexion} 
                                        list_friend={this.props.list_friend} 
                                        setListFriend={this.props.setListFriend}
                                        send={this.send}
                                        getAbonnement={this.getAbonnement}
                                        />}
                            </div>
                            <input type="text" placeholder="Cherches tes amis !" name="username" onInput={(evt) => {this.props.setAmi(evt.target.value)}} required/>
                            <button className="" type="submit" onClick={() => this.props.chercheAmi()}>Go</button>
                        </nav>

                        <article id="messages">
                            <div id="formMess" method="GET" action =""> 
                                <textarea onKeyPress={autoExpand()} className="autoExpand" rows='3' data-min-rows='3' name="message" placeholder="Exprimez-vous !"></textarea> 
                            </div>
                            {<MessageSet userkey={this.props.userKey} 
                                        setKey={this.props.setKey} 
                                        listMessages={this.state.listMessages}
                                        delMess={this.state.delMess}
                                        setKey={this.props.setKey}
                                        userKey={this.props.userKey}
                                        refresh={this.send}
                                        changepage={this.props.changepage}
                                        logout={this.props.logout} />}
                        </article>
                    </div>
                </div>
            );
        }
        else{
            /* affichage de la page de son ami */
        return( 
        <div className="AcceuilPerso">
            <header className="sticky">
                <img id="logo" src={logo} alt="logo" />
                <div id="hLinks">
                    <button type="button" className="buttontop" onClick={()=> this.retourpageperso()}>{this.props.login}</button>
                    <button type="button" className="buttontop" onClick={()=> this.retouracceuil()}>Acceuil</button>
                </div>
            
                <div id="mess" method="GET" action="">
                    <input id = "pattern" type="text" name="pattern"/>
                </div>
                <div id="hLinks">
                    <button type="button" className="buttontop" onClick={()=> this.props.deconnexion()}>Déconnexion</button>
                </div>
            </header>

        

            <div id="corpus">
                <nav>
                    <p>{this.state.listMessages.length} posts</p>
                    <div>{<Amis userKey={this.props.userKey} 
                                setKey={this.props.setKey} 
                                setAmi={this.props.setAmi} 
                                changepage={this.props.changepage} 
                                deconnexion={this.props.deconnexion} 
                                list_friend={this.props.list_friend} 
                                setListFriend={this.props.setListFriend}
                                send={this.send} 
                                getAbonnement={this.getAbonnement}/>}
                    </div>
                    <input type="text" placeholder="Cherches tes amis !" name="username" onInput={(evt) => {this.props.setAmi(evt.target.value)}} required/>
                    <button className="" type="submit" onClick={() => this.props.chercheAmi()}>Go</button>
                </nav>
        
                <article id="messages">
                    <h1>{this.props.ami}</h1>
                    <button type="button" onClick={() => this.send_ajout_remove(this.props.ami)} >{this.add_remove_affichage(this.props.ami)}</button>
                    {<MessageSet userkey={this.props.userKey} setKey={this.props.setKey} listMessages={this.state.listMessages}/>}
                
                </article>
            </div>


        </div>
        );
        }
    }
}

export default Pageperso;