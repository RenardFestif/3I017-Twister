import React, { Component } from 'react';

class Connexion extends Component{
  constructor(props){
    super(props);
    this.handleOnClick = this.handleOnClick.bind(this);
  }
  
handleOnClick(){
  this.props.changepage("acceuilperso");
  this.props.setconnected();
}


	render(){
	  return (
	  <div className="Login">
	  		 
	  			<form className="modal-content animate" action="" method="GET" title="Connexion">
                    <div className="container">
                      <label htmlFor="username"><b>Login</b></label>
                      <input type="text" placeholder="Login" name="username" required/>

                      <label htmlFor="password"><b>Mot de passe</b></label>
                      <input type="password" placeholder="Password" name="password" required/>
                        
                      <button className="log" type="submit" onClick={() => this.handleOnClick()}>Login</button>
                    </div>

                    <div className="container" id="mdp">
                      <span className="psw"><a href="">Mot de passe oublié ?</a></span>
                    </div>
					          <div className="container" id="mdp">
				            	<span className="psw"><a onClick={()=> this.props.changepage("inscription")}>Pas encore inscrit ?</a></span>
                    </div>
					
                </form>
		</div>);
	}
}

export default Connexion; 