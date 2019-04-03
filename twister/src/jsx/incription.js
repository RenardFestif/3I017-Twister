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
		this.handleOnClick = this.handleOnClick.bind(this);
		this.traiteReponse = this.traiteReponse.bind(this);
		this.send = this.send.bind(this);
	}

	send(){
		var formData = new URLSearchParams();
		formData.append("login",this.refs.login);
		formData.append("password",this.refs.password);
		formData.append("mail",this.refs.mail);
		formData.append("nom",this.refs.nom);
		formData.append("prenom",this.refs.prenom);

		axios.get("http://localhost:8080/Twister/Acceuil/signin?"+formData).then(r=>{this.traiteReponse(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});
	}

	traiteReponse(r){
		console.log(r.data.nom);
		if(r.data.status==="OK")
			alert(r.data);
			//this.props.changepage("connexion");
	}

	handleOnClick(){
		//send();
		//Felication Inscription
		
		this.props.changepage("inscription");
	  }

    render(){
		return (
			<div className="Signin">
				<div className="modal-content animate" >
					<div className="container">
						<h1>Inscription</h1>
						<p>Remplis ce formulaire et rejoint le mouv' !</p>

						<label htmlFor="nom"><b>Nom</b></label>
						<input type="text" placeholder="Quel est ton nom ?" name="nom" onInput={(evt) => {this.setState({nom:evt.target.get})}} required/>

						<label htmlFor="prenom"><b>Prenom</b></label>
						<input type="text" placeholder="Quel est ton prenom ?" name="prenom" required/>

						<label htmlFor="email"><b>Email</b></label>
						<input type="text" placeholder="Renseigne ton Email !" name="email" required/>

						<label htmlFor="login"><b>Pseudo</b></label>
						<input type="text" placeholder="Renseigne ton Pseudo" name="login" required/>

						<label htmlFor="password"><b>Password</b></label>
						<input type="password" placeholder="Et enfin un mot de passe super sécurisé !" name="password" required/>

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