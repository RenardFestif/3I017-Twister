import React, { Component } from 'react';
import axios from "axios";

class Connexion extends Component{
  constructor(props){
    super(props);
    this.handleOnClick = this.handleOnClick.bind(this);
  }
  
  handleOnClick(){
    this.props.changepage("acceuilperso");
    this.props.setconnected();
  }
  send(){
    var formData = new URLSearchParams();
		formData.append("login",this.refs.login);
    formData.append("password",this.refs.password);
    
    axios.get("http://localhost:8080/Twister/Acceuil/login?"+formData).then(r=>{this.traiteReponse(r)}).catch(errorRep => {alert("Erreur : connexion avec le serveur : "+errorRep)});
  }

  

	render(){
	  return (
	  <div className="Login">
	  		 
	  			<form className="modal-content animate" onSubmit={this.send} title="Connexion">
                    <div className="container">
                      <label htmlFor="username"><b>Login</b></label>
                      <input type="text" placeholder="Login" name="username" required/>

                      <label htmlFor="password"><b>Mot de passe</b></label>
                      <input type="password" placeholder="Password" name="password" required/>
                        
                      <button className="log" type="submit" onClick={() => this.handleOnClick()}>Login</button>
                    </div>

					          <div className="container" id="mdp">
				            	<span className="psw"><p onClick={()=> this.props.changepage("inscription")}>Pas encore inscrit ?</p></span>
                    </div>
					
                </form>
		</div>);
	}
}

export default Connexion; 