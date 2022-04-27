var URL = "http://localhost:8080/interdiciplinar-web/marcaServlet";
function init() {
    document.querySelector("#salvar").addEventListener("click", cadastraMarca);
    requisicaoHttp("GET", URL, true, montaTabela);
}
function montaTabela(data){
    var dados = JSON.parse(data),
            tr,
            td;
    for(var a = 0; a < dados.length; a++){
        
    }
}
function cadastraMarca() {
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