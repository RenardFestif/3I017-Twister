### Definition et Implementation des WebServices
*******************************************************************
*******************************************************************

**Users**

<table>
    <thead>
        <tr>
            <th>Service</th>
            <th align="center">Creation d'utilisateur</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Nom du Web service</td>
            <td align="center">CreateUser</td>
        </tr>
        <tr>
            <td>URL</td>
            <td align="center">Twister/service/create_user</td>
        </tr>
        <tr>
            <td>Description</td>
            <td align="center">Enregistrement d'un nouvel utilisateur</td>
        </tr>
        <tr>
            <td>Parametres</td>
            <td align="center">login/password/nom/prenom/mail</td>
        </tr>
        <tr>
            <td>Format de sortie</td>
            <td align="center">JSON</td>
        </tr>
        <tr>
            <td>Exemple</td>
            <td align="center">{ "status":"KO" , "coderr":"-1" , "message":"manque login" }</td>
        </tr>
        <tr>
            <td>Avancement</td>
            <td align="center"></td>
        </tr>
        <tr>
            <td>Classes Java</td>
            <td align="center">servlet.users.CreateUser / services.users.CreateUser</td>
        </tr>
        <tr>
            <td>Information</td>
            <td align="center"></td>
        </tr>
    </tbody>
</table>



<table>
    <thead>
        <tr>
            <th>Service</th>
            <th align="center">Login</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Nom du Web service</td>
            <td align="center">LogUser</td>
        </tr>
        <tr>
            <td>URL</td>
            <td align="center">Twister/service/log_user</td>
        </tr>
        <tr>
            <td>Description</td>
            <td align="center">Authentification d'un utilisateur sur le site</td>
        </tr>
        <tr>
            <td>Parametres</td>
            <td align="center">login/password</td>
        </tr>
        <tr>
            <td>Format de sortie</td>
            <td align="center">JSON</td>
        </tr>
        <tr>
            <td>Exemple</td>
            <td align="center">{ "status":"OK" , "key":"dIjd87DF8fe7fr" }</td>
        </tr>
        <tr>
            <td>Avancement</td>
            <td align="center"></td>
        </tr>
        <tr>
            <td>Classes Java</td>
            <td align="center">servlet.users.LogUser / services.users.LogUser</td>
        </tr>
        <tr>
            <td>Information</td>
            <td align="center"></td>
        </tr>
    </tbody>
</table>



<table>
    <thead>
        <tr>
            <th>Service</th>
            <th align="center">Logout</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Nom du Web service</td>
            <td align="center">LogOutUser</td>
        </tr>
        <tr>
            <td>URL</td>
            <td align="center">Twister/service/log_out</td>
        </tr>
        <tr>
            <td>Description</td>
            <td align="center">Deconnexion d'un utilisateur sur le site</td>
        </tr>
        <tr>
            <td>Parametres</td>
            <td align="center">clef</td>
        </tr>
        <tr>
            <td>Format de sortie</td>
            <td align="center">JSON</td>
        </tr>
        <tr>
            <td>Exemple</td>
            <td align="center">{ "status":"KO" , "message":"clef utilisateur incorrect" }</td>
        </tr>
        <tr>
            <td>Avancement</td>
            <td align="center"></td>
        </tr>
        <tr>
            <td>Classes Java</td>
            <td align="center">servlet.users.LogOutUser / services.users.LogOutUser</td>
        </tr>
        <tr>
            <td>Information</td>
            <td align="center"></td>
        </tr>
    </tbody>
</table>



***************************************************************************
***************************************************************************
**Message**

<table>
    <thead>
        <tr>
            <th>Service</th>
            <th align="center">Ajout de de message </th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Nom du Web service</td>
            <td align="center">AddMessage</td>
        </tr>
        <tr>
            <td>URL</td>
            <td align="center">Twister/service/add_message</td>
        </tr>
        <tr>
            <td>Description</td>
            <td align="center">Publie un message sur le site</td>
        </tr>
        <tr>
            <td>Parametres</td>
            <td align="center">message/clef</td>
        </tr>
        <tr>
            <td>Format de sortie</td>
            <td align="center">JSON</td>
        </tr>
        <tr>
            <td>Exemple</td>
            <td align="center">{ "status":"OK" , "publication":"Bonjour les amis !", "date":"01/01/2001" }</td>
        </tr>
        <tr>
            <td>Avancement</td>
            <td align="center"></td>
        </tr>
        <tr>
            <td>Classes Java</td>
            <td align="center">servlet.message.AddMessage / services.message.AddMessage</td>
        </tr>
        <tr>
            <td>Information</td>
            <td align="center"></td>
        </tr>
    </tbody>
</table>


<table>
    <thead>
        <tr>
            <th>Service</th>
            <th align="center">Suppression de Message</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Nom du Web service</td>
            <td align="center">DeleteMessage</td>
        </tr>
        <tr>
            <td>URL</td>
            <td align="center">Twister/service/delete_message</td>
        </tr>
        <tr>
            <td>Description</td>
            <td align="center">Supprime un message du site</td>
        </tr>
        <tr>
            <td>Parametres</td>
            <td align="center">clef/idMessage</td>
        </tr>
        <tr>
            <td>Format de sortie</td>
            <td align="center">JSON</td>
        </tr>
        <tr>
            <td>Exemple</td>
            <td align="center">{ "status":"OK" }</td>
        </tr>
        <tr>
            <td>Avancement</td>
            <td align="center"></td>
        </tr>
        <tr>
            <td>Classes Java</td>
            <td align="center">servlet.message.DeleteMessage / services.message.deleteMessage</td>
        </tr>
        <tr>
            <td>Information</td>
            <td align="center"></td>
        </tr>
    </tbody>
</table>

<table>
    <thead>
        <tr>
            <th>Service</th>
            <th align="center">Recherche Message</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Nom du Web service</td>
            <td align="center">SearchMessage</td>
        </tr>
        <tr>
            <td>URL</td>
            <td align="center">Twister/service/search_message</td>
        </tr>
        <tr>
            <td>Description</td>
            <td align="center">Recherche de message sur le site</td>
        </tr>
        <tr>
            <td>Parametres</td>
            <td align="center">message</td>
        </tr>
        <tr>
            <td>Format de sortie</td>
            <td align="center">JSON</td>
        </tr>
        <tr>
            <td>Exemple</td>
            <td align="center">{ "status":"OK" , "idMess":"dIjd87DF8fe7fr", "message":"Hello World!" }</td>
        </tr>
        <tr>
            <td>Avancement</td>
            <td align="center"></td>
        </tr>
        <tr>
            <td>Classes Java</td>
            <td align="center">servlet.message.SearchMessage / services.message.SearchMessage</td>
        </tr>
        <tr>
            <td>Information</td>
            <td align="center"></td>
        </tr>
    </tbody>
</table>


<table>
    <thead>
        <tr>
            <th>Service</th>
            <th align="center">Liste de message</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Nom du Web service</td>
            <td align="center">ListMessage</td>
        </tr>
        <tr>
            <td>URL</td>
            <td align="center">Twister/service/list_message</td>
        </tr>
        <tr>
            <td>Description</td>
            <td align="center">Chargement liste message sur le site</td>
        </tr>
        <tr>
            <td>Parametres</td>
            <td align="center">UserKey</td>
        </tr>
        <tr>
            <td>Format de sortie</td>
            <td align="center">JSON</td>
        </tr>
        <tr>
            <td>Exemple</td>
            <td align="center">{ "status":"OK" , "idmess1":"dIjd87DF8fe7fr", "idmess2":"of4dOD45dDJ8dSS" }</td>
        </tr>
        <tr>
            <td>Avancement</td>
            <td align="center"></td>
        </tr>
        <tr>
            <td>Classes Java</td>
            <td align="center">servlet.message.ListMessage / services.message.ListMessage</td>
        </tr>
        <tr>
            <td>Information</td>
            <td align="center"></td>
        </tr>
    </tbody>
</table>


*****************************************************************************************
*****************************************************************************************
**Relation**

<table>
    <thead>
        <tr>
            <th>Service</th>
            <th align="center">Ajout d'une relation</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Nom du Web service</td>
            <td align="center">AddFriend</td>
        </tr>
        <tr>
            <td>URL</td>
            <td align="center">Twister/service/add_friend</td>
        </tr>
        <tr>
            <td>Description</td>
            <td align="center">Ajout d'un ami sur le site</td>
        </tr>
        <tr>
            <td>Parametres</td>
            <td align="center">idUser/clé</td>
        </tr>
        <tr>
            <td>Format de sortie</td>
            <td align="center">JSON</td>
        </tr>
        <tr>
            <td>Exemple</td>
            <td align="center">{ "status":"OK" }</td>
        </tr>
        <tr>
            <td>Avancement</td>
            <td align="center"></td>
        </tr>
        <tr>
            <td>Classes Java</td>
            <td align="center">servlet.ralation.AddFriend / services.relation.AddFriend</td>
        </tr>
        <tr>
            <td>Information</td>
            <td align="center"></td>
        </tr>
    </tbody>
</table>



<table>
    <thead>
        <tr>
            <th>Service</th>
            <th align="center">Suppression relation</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Nom du Web service</td>
            <td align="center">DeleteFriend</td>
        </tr>
        <tr>
            <td>URL</td>
            <td align="center">Twister/service/delete_friend</td>
        </tr>
        <tr>
            <td>Description</td>
            <td align="center">Suppression d'un "amis" sur le site</td>
        </tr>
        <tr>
            <td>Parametres</td>
            <td align="center">idFriend/clé</td>
        </tr>
        <tr>
            <td>Format de sortie</td>
            <td align="center">JSON</td>
        </tr>
        <tr>
            <td>Exemple</td>
            <td align="center">{ "status":"OK" }</td>
        </tr>
        <tr>
            <td>Avancement</td>
            <td align="center"></td>
        </tr>
        <tr>
            <td>Classes Java</td>
            <td align="center">servlet.relation.DeleteFriend / services.relation.DeleteFriend</td>
        </tr>
        <tr>
            <td>Information</td>
            <td align="center"></td>
        </tr>
    </tbody>
</table>



<table>
    <thead>
        <tr>
            <th>Service</th>
            <th align="center">Liste Amis</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Nom du Web service</td>
            <td align="center">ListFriend</td>
        </tr>
        <tr>
            <td>URL</td>
            <td align="center">Twister/service/list_friend</td>
        </tr>
        <tr>
            <td>Description</td>
            <td align="center">Recherche de la liste d'ami d'un utilisateur sur le site</td>
        </tr>
        <tr>
            <td>Parametres</td>
            <td align="center">userKey</td>
        </tr>
        <tr>
            <td>Format de sortie</td>
            <td align="center">JSON</td>
        </tr>
        <tr>
            <td>Exemple</td>
            <td align="center">{ "status":"OK" , "idami1":"dIjd87DF8fe7fr", "idami2":"fF65Fe4fEFjn" }</td>
        </tr>
        <tr>
            <td>Avancement</td>
            <td align="center"></td>
        </tr>
        <tr>
            <td>Classes Java</td>
            <td align="center">servlet.relation.ListFriend / services.relation.ListFriend</td>
        </tr>
        <tr>
            <td>Information</td>
            <td align="center"></td>
        </tr>
    </tbody>
</table>
