var modal = document.getElementById('connexion');
var modall = document.getElementById('inscription');

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
    if (event.target == modall) {
        modall.style.display = "none";
    }
  
  
}