import React, { Component } from 'react';
import Login from "./jsx/connexion"

class MainPage extends Component {
	constructor(props){
		super(props)
		this.state = { pagecourante :"NavigationPannel", isConnected : false};
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
		  return (
				<div className="MainPage">
	 			 	<NavigationPannel login={this.getConnected} logout={this.setLogout} isConnected={this.state.isConnected}/>
					{this.props.pagecourante === "Acceuil" ? <Signin signin={this.setSignin}/>: <Login/> };
		  	</div>);
	  }
}

class NavigationPannel extends Component {

	render(){
		return (
			<nav className="NavigationPannel">
				  {this.props.isConnected === true ? <Logout logout={this.props.logout}/> : <Login login={this.props.login}/>}
      		</nav>
		);
	}
}

class Logout extends Component {

	send(){
		this.props.logout();
	  }
	  render(){
		return (<button className="Logout" type="button" onClick={(event) => this.send()}>Deconnection</button>);
	  }
}


class Signin extends Component{

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
		if(s === "l"){
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