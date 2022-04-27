var obj;
var img;
function init(){
	document.querySelector('#img').addEventListener('mousemove', localZoom);
    img = document.querySelector('#img');
	obj = document.querySelector('.zoom');
}
function localZoom(){
	obj.style.backgroundImage = "url(../img/destaques-1.jpg)";
    obj.style.backgroundPosition = -(event.x * 2 - 175) + "px " + -(event.y * 2 - 150) + "px";
    console.log(obj.style.backgroundPosition);
    obj.style.backgroundRepeat = "no-repeat";
}
init();