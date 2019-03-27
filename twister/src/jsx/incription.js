import React, { Component } from 'react';

class Inscription extends Component {
    constructor(props){
        super(props);
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
							<button type="submit" className="log" onClick={() => this.props.changepage("acceuilperso") }>Ca part !</button>
						</div>
					</div>
				</form>
			</div>			
        );
    }
}

export default Inscription;