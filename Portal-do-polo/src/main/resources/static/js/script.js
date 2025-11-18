

/* CARROSSEL AUTOMATICO */

const elemSlides = document.querySelector(".slides");
const elemBotaoEsquerdo = document.querySelector(".esquerda");
const elemBotaoDireito = document.querySelector(".direita");
const elemImg = document.querySelectorAll(".slides img");

let slide = 0;
const tamanhoLista = elemImg.length -1;

elemBotaoEsquerdo.addEventListener("click", () => /* () significa arrow function */{
    slide--;
    if(slide < 0) slide = elemImg.length - 1; /* voltar para o ultimo slide */
    atualizarCarrossel();
});

elemBotaoDireito.addEventListener("click", () =>{
    incrementarSlide();
    atualizarCarrossel();
});

const incrementarSlide = () =>{
     slide++; /* passar mais 1 */
    if(slide > elemImg.length - 1) slide = 0; /*voltar para o primeiro slide  */
}

const atualizarCarrossel = () => {
    elemSlides.style.transform = `translateX(-${slide * 100}%)`; /* fazer o calculo para passar para o lado pois para trocar de img é preciso calcular o tamnaho * 2 */
};

setInterval(() =>{
    incrementarSlide();
    atualizarCarrossel();

}, 4000); /* temporizador automatico  */
/* CARROSSEL AUTOMATICO FINAL */

/* EFEITO PARALLAX SCROLL*/
const carrossel = document.querySelector(".overlay-parallax");

window.addEventListener('scroll', function(){
    const scrollPos = window.scrollY;
    const velocidade = 0.5;

    carrossel.style.transform =  `translateY(${scrollPos * velocidade}px)`;
})
/* EFEITO PARALLAX SCROLL FINAL*/

/* PAGINA DE VAGAS */

function mostrarVaga(id) {
  document.querySelectorAll('.detalhe-card').forEach(card => {
    card.style.display = 'none';
  });
  document.getElementById(id).style.display = 'block';
}