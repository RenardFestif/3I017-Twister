import React, { Component } from 'react';
import PropTypes from 'prop-types';
import axios from 'axios';



class MessageSet extends Component {
    
    
    send(){
        var formData = new URLSearchParams();
        if(this.props.userkey!==undefined)
            formData.append("userKey",this.props.userkey);
        if(this.props.userId!==undefined)
            formData.append("userId",this.props.userId);
        if(this.props.query!==undefined)
            formData.append("query",this.props.query);
		
    console.log("http://localhost:8080/Twister/Profil/cherchermessage?"+formData)
		axios.get("http://localhost:8080/Twister/Profil/cherchermessage?"+formData).then(r=>{this.traiteReponse(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});

		
	}

	traiteReponse(r){
        
		if(r.data.status==="OK"){
            //Tester si on ne recoit pas oune nouvelle key (timeStamp)
            if(r.data.new_key!==null){
                console.log(r.data.new_key);
                //la clé se met a jour à l'infini
                //probleme java recuperation message
                //this.props.setKey(r.data.new_key);
            }
            console.log(r.data);

            //Construction d'un tableau de tableau pour tout les messages 
            /*forme =>
            [
                [idMessage, auteur, date, content]
                [idMessage, auteur, date, content]
                [idMessage, auteur, date, content]
            ]
            */

            
        }
			
    }
    
    render(){
        
        this.send();
        return (
            <div className="MessageSet">

            </div>
            );
    }
}

/*const MESSAGTEST={
    "status":"ok", 
    "Obj035647":"Bonjour tout le monde !", 
    "Obj466981":"Franchement pas cool de bloquer les transports #giletsjaunes"
};*/

MessageSet.propTypes={
    messages: PropTypes.arrayOf(PropTypes.string)
}

export default MessageSet;