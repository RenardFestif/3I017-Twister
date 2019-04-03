import React, { Component } from 'react';
import logo from '../images/logo.png';

class Acceuil extends Component {
    
    render(){
        return (
            <div className="Acceuil">
				<div id="container" className="">
					<img id="logo" src={logo} alt="logo"/>
					<p id="title">Twister</p>
					<button type="button" className="button" onClick={()=> this.props.changepage("inscription")}>Inscription</button>
					<button type="button" className="button" onClick={()=> this.props.changepage("connexion")}>Connexion</button>
				</div>
			</div>
            );
    }
}

export default Acceuil;