import React, { Component } from 'react';



class Acceuil extends Component {
    constructor(props){
        super(props);
    }
    
    render(){
        
        return (
            <div className="Acceuil">
				<div id="container" className="">
					<img id="logo" src="../../images/logo.png" alt="logo"/>
					<p id="title">Twister</p>
<<<<<<< HEAD
					<button type="button" className="log" onSubmit={(event)=>this.handleClick("l")} >Inscription</button>
					<button type="submit" className="log" onClick={(event)=>this.handleClick("s")}>Connexion</button>
=======
					<button type="button" className="button" onClick={()=>this.props.changepage("inscription")}>Inscription</button>
					<button type="button" className="button" onClick={()=>this.props.changepage("connexion")}>Connexion</button>
>>>>>>> branch 'master' of https://github.com/RenardFestif/3I017-Twister.git
				</div>
			</div>
            );
    }
}

export default Acceuil;