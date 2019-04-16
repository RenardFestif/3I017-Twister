import React, { Component } from 'react';
import axios from "axios";



class Inscription extends Component {
	constructor(props){
		super(props);
		this.state={
			login:"",
			password:"",
			mail:"",
			nom:"",
			prenom:""
		};
		
		
		this.traiteReponse = this.traiteReponse.bind(this);
		this.send = this.send.bind(this);
	}

	send(){
		
		//Tester si les parametres sont bien tous remplit !
		var formData = new URLSearchParams();
		formData.append("login",this.state.login);
		formData.append("password",this.state.password);
		formData.append("mail",this.state.mail);
		formData.append("nom",this.state.nom);
		formData.append("prenom",this.state.prenom);
		axios.get("http://localhost:8080/Twister/Acceuil/signin?"+formData).then(r=>{this.traiteReponse(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});
		
	}

	traiteReponse(r){
		console.log(r.data);
		if(r.data.status==="OK")
			this.props.changepage("connexion");
		else
			this.checkArg(r);
	}

	checkArg(r){
		if(this.state.login === ''){
			alert("Quel est ton super nom de twister ?");
		}
		if(this.state.password === ''){
			alert("Ce n'est pas très sécurisant de ne pas mettre de mot de passe 🤨");
		}
		if(this.state.mail === ''){
			alert("Donnes nous ton mail pour que tu sois toujours au courant des dernières nouveautés");
		}
		if(this.state.nom === ''){
			alert("Tu as oublié de remplir un champs");
		}
		if(this.state.prenom === ''){
			alert("Tu as oublié de remplir un champs");
		}
		if(r.data.message === "Mauvais format de mot de passe"){
			alert("Donne nous un mot de passe plus fort. ");
		}
		if(r.data.message === "Mauvais format de mail"){
			alert("Ce n'est pas vraiment comme ça que je définirai un mail");
		}
	}


  render(){
		return (	
			<div>
				<div className="sidenav">
					<div className="login-main-text">
						<h2 className="login-title">Twister <br/> Signin Page</h2>
						<p>Inscrivez-vous pour rejoindre le mouv'</p>
					</div>
				</div>
				
				<div className="main">
					<div className="col-md-6 col-sm-12">
						<div className="login-form">

							<div className="form-group">
								<label>Nom</label>
								<br/>
								<input type="text" placeholder="Nom" name="nom" onInput={(evt) => {this.setState({nom: evt.target.value})}} required/>
							</div>
								
							<div className="form-group">
								<label>Prenom</label>
								<br/>
								<input type="text" placeholder="Prénom" name="prenom" onInput={(evt) => {this.setState({prenom: evt.target.value})}} required/>
							</div>
								
							<div className="form-group">
								<label>Email</label>
								<br/>
								<input type="text" placeholder="Renseigne ton Email !" name="email"	onInput={(evt) => {this.setState({mail: evt.target.value})}} required/>
							</div>

							<div className="form-group">
								<label>Pseudo</label>
								<br/>
								<input type="text" placeholder="Login" name="login" onInput={(evt) => {this.setState({login: evt.target.value})}} required/>
							</div>

							<div className="form-group">
								<label>Password</label>
								<br/>
								<input type="password" placeholder="Password" name="password" onInput={(evt) => {this.setState({password: evt.target.value})}} required/>
							</div>

					
							<button type="submit" className="btn btn-success btn-sm button" onClick={this.send} >Ca part !</button>
							<button type="submit" className="btn btn-success btn-sm button" onClick={() => this.props.changepage("connexion")} >Déjà inscrit ?</button>
						
						</div>
					</div>
				</div>
			</div>			
    );
  }
}

export default Inscription;