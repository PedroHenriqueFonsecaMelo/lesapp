<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tutorial</title>
    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">
    <!-- CSS -->
    <link rel="stylesheet" href="/resources/aboutLivrocss/style.css" rel="stylesheet">

    <link rel="stylesheet" href="/resources/livrocss/style.css">
    <link rel="stylesheet" type="text/css" href="/resources/clicss/cliSpace/cliSpace.css">

    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />


    <meta name="robots" content="noindex,follow" />
    <script src="/resources/livrocss/livros.js" defer></script>
  </head>

  <body>
  <section class="section">
        <nav>
            <div class="logo">
                <img src="/resources/indexcss/image/logo.png">
            </div>
            <ul>
                <li><a href="#Home">Home</a></li>
                <li><a href="#About">About</a></li>
                <li><a href="/shop" target="_top">Shopp</a></li>
            </ul>
            <div class="social_icon">
                <i class="fa-solid fa-magnifying-glass"></i>
                <i class="fa-solid fa-heart"></i>
                <i class="fa-solid fa-shopping-cart"></i>
            </div>
        </nav>
    </section>

    <main class="container">

      <!-- Left Column / Headphones Image -->
      <div class="left-column">
        <img data-image="red" class="active" src="/resources/indexcss/image/book_1.jpg" alt="">
      </div>


      <!-- Right Column -->
      <div class="right-column">

        <!-- Product Description -->
        <div class="product-description">
          <span>Livro</span>
          <h1>${livro.getTitulo()}</h1>
          <p>${livro.getSinopse()}</p>
        </div>

        
        <!-- Product Pricing -->
        <label>Quantidade: </label>
        <div class="wrapper">
            <span class="minus" id="minus${livro.getIdlivro()}">-</span>
            <span class="num" id="num${livro.getIdlivro()}">01</span>
            <span class="plus" id="plus${livro.getIdlivro()}">+</span>
        </div>
        <div class="product-price">
          <span>${livro.getPrecificacao()}</span>
          <a href="/cart/cartAdd/${livro.getIdlivro()}/1" id="livrolink" class="cart-btn">Add to cart</a>
        </div>
      </div>
    </main>

    <!-- Scripts -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js" charset="utf-8"></script>
   
  </body>
</html>
