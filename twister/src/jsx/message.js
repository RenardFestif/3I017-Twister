import React, { Component } from 'react';
import axios from 'axios';
import trash from '../images/trash.png';

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
                <div className="card">
                <div className="card-header">@{this.props.auteur}</div>
                <div className="card-body">
                    <blockquote className="blockquote">{this.props.content}</blockquote>
                    <footer className="bloquote-footer">{this.props.date}</footer>
                </div>
                <div className="card-footer">
                    <img className="trash" src={trash} alt="logotrash" onClick={()=> this.deleteMessage(this.props.idMess)}/>
                </div>
            </div>
                ); 
        }else{
            return (
                <div className="card">
                    <div className="card-header">@{this.props.auteur}</div>
                    <div className="card-body">
                        <blockquote className="blockquote">{this.props.content}</blockquote>
                        <footer className="bloquote-footer">{this.props.date}</footer>
                    </div>
                </div>
                );
        }
        
    }
}

export default Message;