function process(e) {
    
    if(e.which === 13 && !e.shiftKey) { 
        document.forms.namedItem("formMess").submit();
    }
}