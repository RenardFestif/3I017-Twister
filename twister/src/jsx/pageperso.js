import React, { Component } from 'react';
import MessageSet from "./messageSet.js";
import Amis from "./amis.js";
import logo from '../images/logo.png';

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
        this.handleOnClick = this.handleOnClick.bind(this);
    }

    handleOnClick(){
        this.props.changepage("Acceuil");
        this.props.setconnected();
    }

    
    
    render(){ 
        
        return(
        <div classNameName="AcceuilPerso">
            <header classNameName="sticky">
                <img id="logo" src={logo} alt="logo" />
                <div id="hLinks">
                    <button type="button" className="buttontop" onClick={()=> this.props.changepage("pageperso")}>Login</button>
                    <button type="button" className="buttontop" onClick={()=> this.props.changepage("acceuilperso")}>Acceuil</button>
                </div>
                
                <form id="mess" method="GET" action="">
                    <input id = "pattern" type="text" name="pattern"/>
                </form>
                <div id="hLinks">
					<button type="button" className="buttontop" onClick={()=> this.handleOnClick()}>Déconnexion</button>
                </div>
            </header>
    
            
    
            <div id="corpus">
    
                <nav>
                    <p>Nombre de messages écrit</p>
                    <p>Nombre d'abonnés</p>
                    <div>{<Amis userKey={this.props.userKey} setKey={this.props.setKey} setAmi={this.setAmi}/>}</div>
                    <p>on ajoutera des amis ici</p>
                    <form id="amis" method="GET" action> 
                        <input id="searchFriend" type="text" name="pattern" />
                        
                    </form>
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
}

export default Pageperso;