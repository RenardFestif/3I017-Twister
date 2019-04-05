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
		this.checkArg();

		var formData = new URLSearchParams();
		formData.append("login",this.state.login);
		formData.append("password",this.state.password);
		formData.append("mail",this.state.mail);
		formData.append("nom",this.state.nom);
		formData.append("prenom",this.state.prenom);

		//console.log("http://localhost:8080/Twister/Acceuil/signin?"+formData)
		


		axios.get("http://localhost:8080/Twister/Acceuil/signin?"+formData).then(r=>{this.traiteReponse(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});
		
	}

	traiteReponse(r){
		if(r.data.status==="OK")
			this.props.changepage("connexion");
	}

	checkArg(){
		if(this.state.login === ''){
			
		}
		if(this.state.password === ''){
			
		}
		if(this.state.mail === ''){
			
		}
		if(this.state.nom === ''){
			
		}
		if(this.state.prenom === ''){
			
		}
	}


  render(){
		return (	
			<div className="Signin">
				<div className="modal-content animate" >
					<div className="container">
						<h1>Inscription</h1>
						<p>Remplis ce formulaire et rejoint le mouv' !</p>

						<label htmlFor="nom"><b>Nom</b></label>
						<input type="text" placeholder="Quel est ton nom ?" name="nom"  																	onInput={(evt) => {this.setState({nom: evt.target.value})}} required/>

						<label htmlFor="prenom"><b>Prenom</b></label>
						<input type="text" placeholder="Quel est ton prenom ?" name="prenom"  														onInput={(evt) => {this.setState({prenom: evt.target.value})}} required/>

						<label htmlFor="email"><b>Email</b></label>
						<input type="text" placeholder="Renseigne ton Email !" name="email" 	 														onInput={(evt) => {this.setState({mail: evt.target.value})}} required/>

						<label htmlFor="login"><b>Pseudo</b></label>
						<input type="text" placeholder="Renseigne ton Pseudo" name="login" 	 															onInput={(evt) => {this.setState({login: evt.target.value})}} required/>

						<label htmlFor="password"><b>Password</b></label>
						<input type="password" placeholder="Et enfin un mot de passe super sécurisé !" name="password"   	onInput={(evt) => {this.setState({password: evt.target.value})}} required/>

						<div className="clearfix">
							<button onClick={this.send} className="log"  >Ca part !</button>
						</div>
					</div>
				</div>
			</div>			
        );
    }
}

export default Inscription;