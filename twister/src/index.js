import React from 'react';
import ReactDOM from 'react-dom';

import './css/bootstrap.css';
import './css/acceuil_bootstrap.css';
import './css/inscription_bootstrap.css';
import './css/connexion_bootstrap.css';
import './css/acceuil_perso_bootstrap.css';

import './images/logo.png';
import MainPage from "./jsx/mainpage";
import * as serviceWorker from './serviceWorker';

ReactDOM.render(<MainPage/>, document.getElementById('root'));
serviceWorker.unregister();
