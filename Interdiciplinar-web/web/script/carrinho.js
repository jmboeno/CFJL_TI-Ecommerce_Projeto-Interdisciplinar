function init() {
    var URL = "http://localhost:8080/interdiciplinar-web/carrinhoItensServlet",
            dados = {};
    dados["ccliente"] = Number(localStorage.getItem("cod"));

    requisicaoPost(URL, dados, montaGrid);

}
function montaGrid(dados) {
    var dvCatalogo = document.querySelector('#itens'),
            i, qtde, dvProduto, dvProdutoNome, imgProduto, dvProdutoPreco, dvProdutoQtd, dvExcluir,
            produtos = JSON.parse(JSON.parse(dados).produtos);


    dvProduto = document.createElement('DIV');
    dvProduto.className = 'dv-head-carrinho';

    imgProduto = document.createElement('DIV');
    imgProduto.className = 'dv-head-img-carrinho';
    imgProduto.textContent = "Imagem";
    dvProduto.appendChild(imgProduto);

    dvProdutoNome = document.createElement('DIV');
    dvProdutoNome.className = 'dv-head-descricao-carrinho';
    dvProdutoNome.textContent = "Descrição";
    dvProduto.appendChild(dvProdutoNome);

    dvProdutoPreco = document.createElement('DIV');
    dvProdutoPreco.className = 'dv-head-preco-carrinho';
    dvProdutoPreco.textContent = "Preço";
    dvProduto.appendChild(dvProdutoPreco);

    dvProdutoQtd = document.createElement('DIV');
    dvProdutoQtd.className = 'dv-head-qtd-carrinho';
    dvProdutoQtd.textContent = "Quantidade";
    dvProduto.appendChild(dvProdutoQtd);

    dvCatalogo.appendChild(dvProduto);

    document.querySelector(".qtd-cart").innerText = produtos.length;
    localStorage.setItem("qtdeCarrinho", produtos.length);

    for (var i = 0, qtde = produtos.length; i < qtde; i++) {

        dvProduto = document.createElement('DIV');
        dvProduto.className = 'dv-carrinho-produto';
        dvProduto.id = produtos[i].codigo;

        imgProduto = document.createElement('IMG');
        imgProduto.className = 'img-carrinho-produto';
        imgProduto.src = produtos[i].imagem;
        dvProduto.appendChild(imgProduto);

        dvExcluir = document.createElement('DIV');
        dvExcluir.className = 'excluir';
        dvExcluir.textContent = 'X - Excluir';
        dvExcluir.addEventListener('click', excluir);

        dvProdutoNome = document.createElement('DIV');
        dvProdutoNome.className = 'dv-carrinho-produto-nome';
        dvProdutoNome.textContent = produtos[i].nome;
        dvProdutoNome.appendChild(dvExcluir);
        dvProduto.appendChild(dvProdutoNome);

        dvProdutoPreco = document.createElement('DIV');
        dvProdutoPreco.className = 'dv-carrinho-produto-preco';
        dvProdutoPreco.textContent = 'R$ ' + produtos[i].valor.toFixed(2).replace('.', ',');
        dvProduto.appendChild(dvProdutoPreco);

        dvProdutoQtd = document.createElement('INPUT');
        dvProdutoQtd.className = 'dv-carrinho-produto-qtd';
        dvProdutoQtd.value = "";
        dvProduto.appendChild(dvProdutoQtd);

        dvProdutoQtd = document.createElement('DIV');
        dvProdutoQtd.className = 'dv-carrinho-produto-qtd-disponivel';
        dvProdutoQtd.textContent = produtos[i].qtde + " Disponivel";
        dvProduto.appendChild(dvProdutoQtd);

        dvProdutoQtd = document.createElement('BUTTON');
        dvProdutoQtd.className = 'dv-carrinho-produto-btn-comprar';
        dvProdutoQtd.textContent = "Comprar";
        dvProdutoQtd.addEventListener('click', efetuaCompra);
        dvProduto.appendChild(dvProdutoQtd);


        dvCatalogo.appendChild(dvProduto);
    }
    if (localStorage.getItem("usuario") !== null && localStorage.getItem("usuario") !== '') {
        document.querySelector(".barra-topo").style.display = "none";
        document.querySelector(".barra-usuario").style.display = "inline-block";
        document.querySelector("#sair").addEventListener("click", sair);
        document.querySelector("#usuario").innerText = "Olá, " + localStorage.getItem("usuario") + "!";
    }
}
function efetuaCompra(e) {
    var URL = "http://localhost:8080/interdiciplinar-web/compraServlet",
            dados = {};

    if (document.querySelector('.dv-carrinho-produto-qtd').value) {
        if (Number(document.querySelector('.dv-carrinho-produto-qtd').value)
                <= Number(document.querySelector('.dv-carrinho-produto-qtd-disponivel').innerText.split(' ')[0])) {
            dados["ccliente"] = Number(localStorage.getItem("cod"));
            dados["cproduto"] = Number(e.target.parentNode.id);
            dados["qtde"] = Number(document.querySelector('.dv-carrinho-produto-qtd').value);
            requisicaoPost(URL, dados, resultCompra);
        } else {
            alert('Saldo indisponivel!');
        }
    } else {
        alert('Informe a quantidade que deseja comprar!');
    }
}
function resultCompra(data) {
    var resposta = JSON.parse(data);
    alert(resposta.mensagem);
    window.location.href = "principalCarrinho.html";
}
function sair() {
    document.querySelector(".barra-topo").style.display = "inline-block";
    document.querySelector(".barra-usuario").style.display = "none";
    limpaUsuarioLocalStorage();
    window.location.href = "../index.html";
}
function excluir(e){
    var URL = "http://localhost:8080/interdiciplinar-web/excluirItemCarrinhoServlet",
            dados = {};
    dados["ccliente"] = Number(localStorage.getItem("cod"));
    dados["cproduto"] = Number(e.target.parentNode.parentNode.id);
    requisicaoPost(URL, dados, resultCompra);
}
init();

