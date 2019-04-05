import React, { Component } from 'react';
import axios from "axios";

class Connexion extends Component{
  constructor(props){
    super(props);
    this.state={
      password:"",
      cle:"",
      id:""
    }

  }

  send(){
    var formData = new URLSearchParams();
		formData.append("login",this.state.login);
    formData.append("password",this.state.password);

    if(this.state.login === "" || this.state.password === ""){
      alert("Merci de remplir tout les champs");
      return;
    }
    
    axios.get("http://localhost:8080/Twister/Acceuil/login?"+formData).then(r=>{this.traiteReponse(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});
    console.log("http://localhost:8080/Twister/Acceuil/login?"+formData);
  }



  traiteReponse(r){


    //Match !
    if(r.data.status==="OK"){
      this.props.changepage("acceuilperso");
      this.props.setKey(r.data.key);
      this.props.setconnected();
      
    }

    //Section a modifié car pas esthetique !
    //Appel de fonction js => modification dom bootstrap 
    if(r.data.message === "Mot de passe oublie ?")
    {
      alert("Mot de passe oublié ?")
      return;
    }
    if(r.data.message === "Utilisateur inconnu")
    {
      alert("Utilisateur inconnu")
      return;
    }

    /*//Recuperation de la clé
        this.setState({cle: r.data.key});
        this.setState({id: r.data.userID});
        this.props.changepage("acceuilperso");
        this.props.setconnected();
        this.props.getacceuilperso(this.state.cle, this.state.id, this.state.login);
    }*/
   

  }

  

	render(){
	  return (
	  <div className="Login">
	  		 
	  			<div className="modal-content animate">
                    <div className="container">
                      <label htmlFor="username"><b>Login</b></label>
                      <input type="text" placeholder="Login" name="username" onInput={(evt) => {this.setState({login: evt.target.value})}} required/>

                      <label htmlFor="password"><b>Mot de passe</b></label>
                      <input type="password" placeholder="Password" name="password" onInput={(evt) => {this.setState({password: evt.target.value})}} required/>
                        
                      <button className="log" type="submit" onClick={() => this.send()}>Login</button>
                    </div>

					          <div className="container" id="mdp">
				            	<span className="psw"><p onClick={()=> this.props.changepage("inscription")}>Pas encore inscrit ?</p></span>
                    </div>
					
                </div>
		</div>);
	}
}

export default Connexion; 