import React, { Component } from 'react';


class Connexion extends Component{
    constructor(props){
        super(props);

    }

    render(){
    <div id="connexion" className="modal">
        <span onClick={this.handleConnexion} className="close" title="Close Modal">&times;</span>
        <form className="modal-content animate" action="" method="GET">
            <div className="container">
                <label for="username"><b>Login</b></label>
                <input type="text" placeholder="Login" name="username" required/>

                <label for="password"><b>Mot de passe</b></label>
                <input type="password" placeholder="Password" name="password" required/>
            
                <button className="log" type="submit">Login</button>
            </div>

            <div className="container" id="mdp">
                <span className="psw"><a href="#">Mot de passe oublié ?</a></span>
            </div>
        </form>
    </div>
    }

}