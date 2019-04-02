import React, { Component } from 'react';
import PropTypes from 'prop-types';


class MessageSet extends Component {
    constructor(props){
        super(props);
        this.state = {
            userKey:props.userKey,
            query:props.query,
            userId:props.userId,
            
        };
        //Les messages ne sont pas des etats et doivent être recuperrer dynamiquement   
        //En fonction de la clé
    }

    getMessages(){
        //Tout à null searchMessage(null, null, 0)
        //Recherche de tout les messages
        if(this.state.userKey===undefined 
            && this.state.query===undefined 
            && this.state.userId===undefined ){
            
        }
        //Seul userKey n'est pas a null searchMessage(null,userKey,0)
        //Recherche des messages amis 
        else if(this.state.userKey!==undefined 
            && this.state.query===undefined 
            && this.state.userId===undefined){

        }
        //UserId != null searcheMessage(userId, UserKey, - )
        else{
            //On test si le query === undifined => pas de pattern
            if(this.state.query===undefined){

            }
            //Sinon query
            else{
                
            }
        }  
    }
    
    
    render(){

        this.getMessages()



        return (
            <div className="MessageSet">

            </div>
            );
    }
}

const MESSAGTEST={
    "status":"ok", 
    "Obj035647":"Bonjour tout le monde !", 
    "Obj466981":"Franchement pas cool de bloquer les transports #giletsjaunes"
};

MessageSet.propTypes={
    messages: PropTypes.arrayOf(PropTypes.string)
}

export default MessageSet;