function requisicaoHttp(metodo, endereco, assincrona, callback) {
    var http = new XMLHttpRequest();
    http.open(metodo, endereco, assincrona);
    http.addEventListener("load", function () {
        callback(http.responseText);
    });
    http.send();
}
function requisicaoPost(url, dados, funcao){
    var http = new XMLHttpRequest();
            http.open("POST", url, true);
            http.addEventListener("load", function () {
                funcao(http.responseText);
            });
            http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            http.send(JSON.stringify(dados));
}

