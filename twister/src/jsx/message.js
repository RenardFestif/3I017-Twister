import React, { Component } from 'react';
import axios from 'axios';

class Message extends Component {
    
    deleteMessage(messageId){

        var formData = new URLSearchParams();
        
        
        formData.append("userKey",this.props.userKey);
        formData.append("idMessage",this.props.idMess);
        
		
        console.log("http://localhost:8080/Twister/Profil/deletemessage?"+formData)
		axios.get("http://localhost:8080/Twister/Profil/deletemessage?"+formData).then(r=>{this.traiteDelete(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});
    }
    traiteDelete(r){
        console.log(r.data);
        if(r.data.status ==="OK"){
            this.props.setKey(r.data.new_key);
            this.props.refresh();
        }else{
            this.props.logout();
            this.props.changepage("connexion");
        }
    }
    render(){
        if(this.props.delMess !== undefined){
            return (
                <li className="Message">
                    <pre>@{this.props.auteur} </pre>
                    <pre>{this.props.content} </pre>
                    <pre>{this.props.date}</pre>
                    <button onClick={()=> this.deleteMessage(this.props.idMess)}>delete</button>
                </li>
                ); 
        }else{
            return (
                <li className="Message">
                    <pre>@{this.props.auteur} </pre>
                    <pre>{this.props.content} </pre>
                    <pre>{this.props.date}</pre>
    
                </li>
                );
        }
        
    }
}

export default Message;