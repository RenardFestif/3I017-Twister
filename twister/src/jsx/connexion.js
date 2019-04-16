import React, { Component } from 'react';
import axios from "axios";

class Connexion extends Component{
  constructor(props){
    super(props);
    this.state={
      password:"",
      login:"",
    }

  }

  send(){
    var formData = new URLSearchParams();
		formData.append("login",this.state.login);
    formData.append("password",this.state.password);
    console.log("http://localhost:8080/Twister/Acceuil/login?"+formData);
    axios.get("http://localhost:8080/Twister/Acceuil/login?"+formData).then(r=>{this.traiteReponse(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});
  }

  traiteReponse(r){
    console.log(r.data);
    if(r.data.status==="OK"){
    //Si mot de passe faux changement CSS ou bien alert
      this.props.setconnected();
      this.props.setKey(r.data.key);
      this.props.setUser(r.data.userID, this.state.login);
      this.props.changepage("acceuilperso");
    }
    else{
      if(r.data.message === "Mot de passe oublie ?"){
        alert("Il semble que tu te sois trompé de mot de passe. Réessaye !");
      }
      else{
        if(r.data.message === "Utilisateur inconnu"){
          alert("Tu es sur de faire parti de nos twisters ?\n Je ne te trouve pas dans nos bases.")
        }
      }
    }
    
    
  }

  

	render(){
	  return (
    <div>
      <div className="sidenav">
        <div className="login-main-text">
          <h2 className="login-title">Twister<br/> Login Page</h2>
          <p>Connectez-vous et twistez avec vos amis !</p>
        </div>
      </div>

      <div className="main">
        <div className="col-md-6 col-sm-12">
          <div className="login-form">
          
            <div className="form-group">
              <label htmlFor="username"><b>Login</b></label>
              <input type="text" placeholder="Login" name="username" onInput={(evt) => {this.setState({login: evt.target.value})}} required/>
            </div>
            
            <div className="form-group">
              <label htmlFor="password"><b>Mot de passe</b></label>
              <input type="password" placeholder="Password" name="password" onInput={(evt) => {this.setState({password: evt.target.value})}} required/>
            </div>

          <button className="btn btn-success btn-sm button" type="submit" onClick={() => this.send()}>Login</button>
          
          <button className="btn btn-success btn-sm button" type="submit" onClick={() => this.props.changepage("inscription")}>Pas encore inscrit ?</button>
        </div>
      </div>
    </div>
  </div>
  );
	}
}

export default Connexion; 