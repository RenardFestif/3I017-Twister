import React, { Component } from 'react';
import logo from '../images/logo.png';

class Acceuil extends Component {
    
    render(){
        return (
            <div className="container-fluid">
				<div className="row haut">
					<img className="logoAc" src={logo} alt="logo"/>
					<h2 className="display-2 titre">Twister</h2>
				</div>
				<div className="row bas">
					<button type="button" className="btn btn-success btn-sm button rounded-pill btnAc" onClick={()=> this.props.changepage("inscription")}>Inscription</button>
					<button type="button" className="btn btn-success btn-sm button rounded-pill btnAc" onClick={()=> this.props.changepage("connexion")}>Connexion</button>
				</div>
			</div>
            );
    }
}

export default Acceuil;