import React, { Component } from 'react';

class MainPage extends Component {
	constructor(props){
		super(props)
		this.state = {pagecourante :"Acceuil", isConnected : false};
		this.getConnected = this.getConnected.bind(this);
		this.setLogout = this.setLogout.bind(this);
		this.setLogin = this.setLogin.bind(this);
		this.setSignin = this.setSignin.bind(this);
	}

	getConnected(){
		this.setState({isConnected: true, pagecourante: "NavigationPannel"});
	  }
	  
	  setLogout(){
		this.setState({isConnected: false, pagecourante: "Acceuil" });
	  }

	  setLogin(){
		this.setState({isConnected: false, pagecourante : "Login"});
	  }
	  setSignin(){
		this.setState({isConnected: false, pagecourante: "Signin" })
	  }

	  render(){
		  return (<div className="MainPage">
	 			 	<NavigationPannel signin={this.setSignin} login={this.setLogin} logout={this.setLogout} isConnected={this.state.isConnected}/>
					
		  			</div>);
	  }
}

class NavigationPannel extends Component {
	constructor(props){
		super(props);
	}

	render(){
		return (
			<nav className="NavigationPannel">
				  {this.props.isConnected === true ? <Logout logout={this.props.logout}/> : <Acceuil signin={this.props.signin} login={this.props.login}/>  }
      		</nav>
		);
	}
}

class Logout extends Component {
	constructor(props){
		super(props);
	}
	send(){
		this.props.logout();
	  }
	  render(){
		return (<button className="Logout" type="button" onClick={(event) => this.send()}>Deconnection</button>);
	  }
}

class Login extends Component{
	constructor(props){
	  super(props);
	}
	
	send(){
	  this.props.login();
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
                        
                      <button className="log" type="submit" onClick={(event)=> this.send()}>Login</button>
                    </div>

                    <div className="container" id="mdp">
                      <span className="psw"><a href="#">Mot de passe oublié ?</a></span>
                    </div>
					<div className="container" id="mdp">
					<span className="psw"><a href="#">Pas encore inscrit ?</a></span>
                    </div>
					
                </form>
		</div>);
	}
}

class Signin extends Component{
	constructor(props){
		super(props);
	}

	send(){
		this.props.signin();
	}

	render(){
		return (
			<div className="Signin">
				<form className="modal-content animate" action="" method="GET">
					<div className="container">
						<h1>Inscription</h1>
						<p>Remplis ce formulaire et rejoint le mouv' !</p>

						<label htmlFor="nom"><b>Nom</b></label>
						<input type="text" placeholder="Quel est ton nom ?" name="nom" required/>

						<label htmlFor="prenom"><b>Prenom</b></label>
						<input type="text" placeholder="Quel est ton prenom ?" name="prenom" required/>

						<label htmlFor="email"><b>Email</b></label>
						<input type="text" placeholder="Renseigne ton Email !" name="email" required/>

						<label htmlFor="login"><b>Pseudo</b></label>
						<input type="text" placeholder="Renseigne ton Pseudo" name="login" required/>

						<label htmlFor="password"><b>Password</b></label>
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

class Acceuil extends Component{
	constructor(props){
		super(props);
		this.state={signin:false, login:false};
		this.handleClick = this.handleClick.bind(this);
	}

	handleClick(s){
		if(s == "l"){
			this.setState({login :true});
			this.props.login();
			return <Login/>;
		}
		else{
			this.setState({signin :true});
			this.props.signin();
			return <Login/>;
		}
	}

	render(){
		return (
			<div className="Acceuil">
				<div id="container" className="">
					<img id="logo" src="../images/logo.png" alt="logo"/>
					<p id="title">Twister</p>
					<button type="button" className="log" onClick={(event)=>this.handleClick("l")} >Inscription</button>
					<button type="submit" className="log" onClick={(event)=>this.handleClick("s")}>Connexion</button>
				</div>
			</div>
		);

	}
}

export default MainPage;