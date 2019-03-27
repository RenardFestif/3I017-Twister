import React from 'react';
import ReactDOM from 'react-dom';
import './css/acceuil.css';
import './css/login.css';
import './css/connexion.css';
import './css/perso.css';
import MainPage from "./jsx/mainpage";
import * as serviceWorker from './serviceWorker';

ReactDOM.render(<MainPage/>, document.getElementById('root'));
serviceWorker.unregister();
