import React, { Component } from 'react';

class Connexion extends Component{
  constructor(props){
    super(props);
}
  
/*Rajouter une methode handleOnClick*/ 

	render(){
	  return (
	  <div className="Login">
	  		 
	  			<form className="modal-content animate" action="" method="GET" title="Connexion">
                    <div className="container">
                      <label htmlFor="username"><b>Login</b></label>
                      <input type="text" placeholder="Login" name="username" required/>

                      <label htmlFor="password"><b>Mot de passe</b></label>
                      <input type="password" placeholder="Password" name="password" required/>
                        
                      <button className="log" type="submit" onClick={() => this.props.changepage("acceuilperso")}>Login</button>
                    </div>

                    <div className="container" id="mdp">
                      <span className="psw"><a href="">Mot de passe oublié ?</a></span>
                    </div>
					<div className="container" id="mdp">
					<span className="psw"><a href="">Pas encore inscrit ?</a></span>
                    </div>
					
                </form>
		</div>);
	}
}

export default Connexion; 