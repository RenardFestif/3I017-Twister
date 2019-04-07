import React, { Component } from 'react';
import Message from "./message.js";
import PropTypes from 'prop-types';
import axios from 'axios';



class MessageSet extends Component {

    constructor(props){
        super(props);

        this.state={
            listMessages:[],
        }
    }
    
    
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
            console.log(messagesList);
            this.setState({
                listMessages:messagesList,
            })

            //console.log(this.state.listMessages);


            

            

            
        }else{
            //this.props.setLogout();
            //this.props.changepage("connexion");
        }
			
    }

    getMoreInfo(idMess){
        

    }
    
    render(){
        
        this.send();
        return (
            <div className="MessageSet">
                {this.state.listMessages.map(item => <Message date={item[1].date} auteur={item[1].auteur} content={item[1].content}/>)}
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