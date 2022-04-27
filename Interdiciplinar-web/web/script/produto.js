function init() {
    document.querySelector("#salvar").addEventListener("click", cadastraProduto);
}
var URL = "http://localhost:8080/interdiciplinar-web/produtoServlet";
function cadastraProduto() {
    var form = document.querySelector("#form"),
            formData = {};
    
    formData["marca"] = Number(form.marca.value);
    formData["categoria"] = Number(form.categoria.value);
    formData["descricao"] = form.descricao.value;
    formData["precoUnitario"] = Number(form.precoUnitario.value);
    formData["qtde"] = Number(form.qtde.value);
    formData["imagem"] = form.imagem.value;
    
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