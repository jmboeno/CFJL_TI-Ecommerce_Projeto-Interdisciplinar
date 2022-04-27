function init() {
    document.querySelector("#salvar").addEventListener("click", cadastraCategoria);
}
var URL = "http://localhost:8080/interdiciplinar-web/categoriaServlet";
function cadastraCategoria() {
    var form = document.querySelector("#form"),
            formData = {};
    formData["descricao"] = form.descricao.value;
    
    var http = new XMLHttpRequest();
    http.open("POST", URL, true);
    http.addEventListener("load", function () {
        parseJson(http.responseText);
    });
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send(JSON.stringify(formData));
}
function parseJson(jsonData) {
    var obj = JSON.parse(jsonData),
        inputs;
        inputs = document.getElementsByTagName("input");
        for(var i = 0; i < inputs.length; i++){
            inputs[i].value = "";
        }
        alert(obj.mensagem);
}
init();