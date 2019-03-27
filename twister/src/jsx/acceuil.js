import React, { Component } from 'react';



class Acceuil extends Component {
    constructor(props){
        super(props);
    }
    
    render(){
        
        return (
            <div className="Acceuil">
				<div id="container" className="">
					<img id="logo" src="../../images/logo.png" alt="logo"/>
					<p id="title">Twister</p>
					<button type="button" className="button" onClick={()=>this.props.changepage("inscription")}>Inscription</button>
					<button type="button" className="button" onClick={()=>this.props.changepage("connexion")}>Connexion</button>
				</div>
			</div>
            );
    }
}

export default Acceuil;