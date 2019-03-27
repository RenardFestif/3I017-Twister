import React, { Component } from 'react';


function Connexion(props){
	if (!props.cliquer_sur_connexion)
		return null;
	else{
		function Modal(e){
			
			var modall = document.getElementById('connexion');
		
			window.onclick = function(event) {

				if (event.target == modall) {
					modall.style.display = "none";
				}
			};
		}
		return (
			<div id="connexion" className="modal" /*onClick={Modal}*/>
        		<span onClick={props.handleConnexion} className="close" title="Close Modal">&times;</span>
        		<form className="modal-content animate" action="" method="GET">
            		<div className="container">
                		<label for="username"><b>Login</b></label>
                		<input type="text" placeholder="Login" name="username" required/>

                		<label for="password"><b>Mot de passe</b></label>
                		<input type="password" placeholder="Password" name="password" required/>
            
                		<button className="log" type="submit">Login</button>
            		</div>

            		<div className="container" id="mdp">
                		<span className="psw"><a href="#">Mot de passe oublié ?</a></span>
            		</div>
        		</form>
    		</div>)	
	}
}

function Inscription(props){
	if (!props.cliquer_sur_inscription)
		return null;
	else{
		function Modal(e){
			
			var modall = document.getElementById('inscription');
			
			window.onclick = function(event) {

				if (event.target == modall) {
					modall.style.display = "none";	
				}
			};
			
		}
		return(
		<div id="inscription" className="modal" /*onClick={Modal}*/>
				<span onClick={props.handleInscription} className="close" title="Close Modal">&times;</span>
				<form className="modal-content animate" action="" method="GET">
					<div className="container">
						<h1>Inscription</h1>
						<p>Remplis ce formulaire et rejoint le mouv' !</p>
						<hr/>

						<label for="nom"><b>Nom</b></label>
						<input type="text" placeholder="Quel est ton nom ?" name="nom" required/>

						<label for="prenom"><b>Prenom</b></label>
						<input type="text" placeholder="Quel est ton prenom ?" name="prenom" required/>

						<label for="email"><b>Email</b></label>
						<input type="text" placeholder="Renseigne ton Email !" name="email" required/>

						<label for="login"><b>Pseudo</b></label>
						<input type="text" placeholder="Renseigne ton Pseudo" name="login" required/>

						<label for="password"><b>Password</b></label>
						<input type="password" placeholder="Et enfin un mot de passe super sécurisé !" name="password" required/>

						
			

						<div className="clearfix">
							<button type="submit" className="log">Ca part !</button>
						</div>

						
					</div>
				</form>
				
			</div>
			
		);
	}
}

function Acceuil(props){
	if(props.connected){
		return null;
	}
	else{
		return(
		<section>
			<div id="container">

				<img id="logo" src="../images/logo.png" alt="logo" />
				<p id="title">Twister</p>
			
				<div id="Links">
					<a className = "button" onClick={props.handleConnexion}>Connexion</a>
					<a className = "button" onClick={props.handleInscription}>Inscription</a>
				</div>

				
				<Inscription 	cliquer_sur_inscription	= {props.cliquer_sur_inscription}
								handleInscription 		= {props.handleInscription}/>
				<Connexion 		cliquer_sur_connexion	= {props.cliquer_sur_connexion}
								handleConnexion 		= {props.handleConnexion}	/>
				
				

				
			</div>
		</section>)
	} 

}



class MainPage extends Component {
	constructor(props){
		super(props);
		this.state = {cliquer_sur_connexion:false, cliquer_sur_inscription:false, connected:false};
		
		this.handleConnexion = this.handleConnexion.bind(this);
		this.handleInscription = this.handleInscription.bind(this);
		this.handleConnected = this.handleConnected.bind(this);
	}

	handleConnected(){
		this.setState(state => ({
			connexion: !state.connexion
		}));
	}

	handleConnexion(){
		this.setState(state => ({
			cliquer_sur_connexion: !state.cliquer_sur_connexion
		}));
	}

	handleInscription(){
		
		this.setState(state =>({
			cliquer_sur_inscription: !state.cliquer_sur_inscription
		}));

		
	}



	render(){
		
		return(
		<Acceuil 	connected 				= {this.state.connected}
					handleConnexion 		= {this.handleConnexion}
					handleInscription		= {this.handleInscription}
					cliquer_sur_connexion	= {this.state.cliquer_sur_connexion} 	
					cliquer_sur_inscription = {this.state.cliquer_sur_inscription}/>
		);
	}
	



}

export default MainPage;
