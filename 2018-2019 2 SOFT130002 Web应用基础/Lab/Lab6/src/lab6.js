/*jshint esversion: 6 */

window.onload = function () {
    let imgFeatured = document.getElementById("featured");
    let imgs = document.getElementById("thumbnails").getElementsByTagName("img");
    for(let i = 0; i < imgs.length; i++){
        imgs[i].onclick = function () {
            let imgFeatured = document.getElementById("featured").getElementsByTagName("img")[0];
            let titleFeatured = document.getElementById("featured").getElementsByTagName("figcaption")[0];
            imgFeatured.src = this.src.replace("small", "medium");
            imgFeatured.title = this.title;
            titleFeatured.innerHTML = this.title;
        };
    }
    imgFeatured.onmouseenter = function () {
        caption(true);
    };
    imgFeatured.onmouseleave = function () {
        caption(false);
    };
};

let id = 0;
function caption(isShow) {
    clearTimeout(0);
    let titleFeatured = document.getElementById("featured").getElementsByTagName("figcaption")[0];
    if(titleFeatured.style.opacity === (isShow ? "0.8": "0" ))
        return;
    let val = isShow ? parseFloat(titleFeatured.style.opacity?titleFeatured.style.opacity:"0") : parseFloat(titleFeatured.style.opacity);
    (function () {
        titleFeatured.style.opacity = val;
        if (isShow)  {
            val += 0.004;
            if(val <= 0.8){
                id = setTimeout(arguments.callee, 5);
            }
        }
        else {
            val -= 0.004;
            if(val >= 0){
                id = setTimeout(arguments.callee, 5);
            }
        }
    })();
}
