import React, { Component } from 'react';

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


    render(){
        return (
            <div classNameName="AcceuilPerso">
            <header classNameName="sticky">
                <img id="logo" src ="../images/ancre.png" alt="logo" />
    
                <form id="mess" method="GET" action="">
                    <input id = "pattern" type="text" name="pattern"/>
                </form>
                <div id="hLinks">
                    <a className="headerLink" href="page_perso.html">Mon Profil</a>
                    <a className="headerLink" href="deconnexion.html">Deconnexion</a>
                </div>
            </header>
    
            
    
            <div id="corpus">
    
                <nav>
                    <p>Nombre de messages écrit</p>
                    <p>Nombre d'abonnés</p>
                    <p>Nombre d'abonnements</p>
                    <p>on ajoutera des amis ici</p>
                    <form id="amis" method="GET" action> 
                        <input id="searchFriend" type="text" name="pattern"/>
                        
                    </form>
                </nav>
            
                <article id="messages">
                    
                    <form id="formMess" method="GET" action =""> 
                        <textarea /*ERREUR PAR ICI*/onKeyPress={autoExpand()} className="autoExpand" rows='3' data-min-rows='3' name="message" placeholder="Exprimez-vous !"></textarea> 
                    </form>
                    <p>Liste de messages ici</p>
                    
                </article>
            </div>
    
    
            </div>
        );
    }
}


export default AcceuilPerso;