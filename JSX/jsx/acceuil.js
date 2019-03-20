import React, { Component } from 'react';


class MainPage extends Component {
	constructor(props){
		super(props);
		this.state = { connected : false , cliquer_sur_connexion:false};
		this.getConnected = this.getConnected.bind(this);
		this.setLogout = this.setLogout.bind(this);
	}

	handleConnexion(){
		this.cliquer_sur_connexion == false ? this.setState(true) : this.setState(false);
	}


	render(){
		return(
		<section>
			<div id="container">

				<img id="logo" src="../images/logo.png" alt="logo" />
				<p id="title">Twister</p>
			
				<div id="Links">
					<a className = "button" onClick={this.handleConnexion}>Connexion</a>
					<a className = "button"  onClick={document.getElementById('inscription').style.display='block'}>Inscription</a>
				</div>

				<div id="connexion" className="modal">
					<span onClick={document.getElementById('connexion').style.display='none'} className="close" title="Close Modal">&times;</span>
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
				</div>

				<div id="inscription" className="modal">
					<span onClick={document.getElementById('inscription').style.display='none'} className="close" title="Close Modal">&times;</span>
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
			</div>
			<script type="text/javascript" src="../js/loginWindow.js"></script>
		</section>
		
		);
	}
	
	getConnected(){
	 this.setState({connected:true});
	 
	}
	 
	setLogout(){
	}



}

export default MainPage;
