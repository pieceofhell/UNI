function getPageId(){
    let url = window.location.href;
    let urlParams = url.split("?");
    let pageId = urlParams[1].split("=")[1];
    return pageId;
}

function loadProds() {
    fetch("https://diwserver.vps.webdock.cloud/products/" + getPageId())
      .then(function (response) {
        return response.json();
      })
      .then(function (response) {
        let tela = document.querySelector("#tela");
        const localle = new Intl.NumberFormat('en-US', {style: 'currency', currency: 'USD'})
        var textoTela = "";
        let product = response;
        textoTela = `<div class="container">
                      <div class="cta">
                        <img src="${product.image}" alt="">
                        <div class="text">
                          <a href="detalhes.html" id="product">${product.title}</a>
                          <p><i class="fa-solid fa-star"></i> ${product.rating.rate} - reviewed by <i class="fa-solid fa-person"></i> ${product.rating.count} people.</p>
                           <br>
                            <p>Brand: ${product.brandName}</p>
                            <p><b>Suggested season for use:</b> ${product.season}</p>
                            <p><b>Release year:</b> ${product.year}</p>
                            <p><b>Recommended usage:</b> ${product.usage}</p>
                            <br>
                           <p>${localle.format(product.price/100)}<br><br>${product.description}</p>
                        </div>
                      </div>
                    </div>`;
        tela.innerHTML = textoTela;
        
      });
  }

  loadProds();

  document.addEventListener("DOMContentLoaded", loadProds);

async function searchProducts(query) {
  let req = fetch(
    "https://diwserver.vps.webdock.cloud/products/search?query=" + query
  );
  let json = (await req).json();
  console.log(await json);
  showProducts(await json);
}

document.getElementById("botao").addEventListener("click", (e) => {
e.preventDefault();
searchProducts(document.getElementById("search").value)
});
    
function showProducts(response) {
    let tela = document.querySelector("#tela");
      const localle = new Intl.NumberFormat('en-US', {style: 'currency', currency: 'USD'})
      var textoTela = "";
      for (let i = 0; i < response.length; i++) {
        let product = response[i];
        textoTela += `<div class="container">
                    <div class="cta">
                      <img src="${product.image}" alt="">
                      <div class="text">
                        <a href="./detalhes.html?id=${product.id}" id="product">${product.title}</a>
                        <p><i class="fa-solid fa-star"></i> ${product.rating.rate} <i class="fa-solid fa-person"></i> ${product.rating.count}</p>
                         <br>
                         <p>${localle.format(product.price/100)}<br><br>${product.description}</p>
                      </div>
                    </div>
                  </div>`;
      }
      tela.innerHTML = textoTela;
    }

  document.addEventListener("DOMContentLoaded", catProds);

function catProds() {
  fetch("https://diwserver.vps.webdock.cloud/products/categories/")
    .then(function (response) {
      return response.json();
    })
    .then(function (response) {
      let categ = 0;
      const localle = new Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "USD",
      });
      var textoCat = "";
      for (let i = 0; i < response.length; i++) {
        let product = response[i];
        textoCat += `<option value="${product}">${product}</option>`;
      }
      categ.innerHTML = textoCat;
      document.getElementById("j").innerHTML = textoCat;
    });
}

function categoria() {
  var select = document.getElementById("j");
  var option = select.value;
  fetch("https://diwserver.vps.webdock.cloud/products/category/" + option)
  .then(function (response) {
    return response.json();
  })
  .then(function (response) {
    let tela = document.querySelector("#tela");
    const localle = new Intl.NumberFormat('en-US', {style: 'currency', currency: 'USD'})
    var textoTela = "";
    for (let i = 0; i < response.products.length; i++) {
      let product = response.products[i];
      textoTela += `<div class="container">
                  <div class="cta">
                    <img src="${product.image}" alt="">
                    <div class="text">
                      <a href="./detalhes.html?id=${product.id}" id="product">${product.title}</a>
                      <p><i class="fa-solid fa-star"></i> ${product.rating.rate} - reviewed by <i class="fa-solid fa-person"></i> ${product.rating.count} people.</p>
                       <br>
                       <p>${localle.format(product.price/100)}<br><br>${product.description}</p>
                    </div>
                  </div>
                </div>`;
    }
    tela.innerHTML = textoTela;
    
  });
}

document.getElementById("catag").addEventListener("click", e=>{
  e.preventDefault()
  categoria();
})
