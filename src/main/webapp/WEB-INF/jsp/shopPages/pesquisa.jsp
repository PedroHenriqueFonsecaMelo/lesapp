<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Store Website</title>
    <link rel="stylesheet" href="/resources/indexcss/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    
    <section class="section2">
        <nav>
            <div class="logo">
                <img src="/resources/indexcss/image/logo.png">
            </div>
            <ul>
                <li><a href="/">Home</a></li>
                <li><a href="#About">About</a></li>
                <li><a href="/shop" target="_top">Shop</a></li>
            </ul>
            <div class="social_icon">
                <form action="/pesquisa" method="post">
                    <input type="text" class="form-control shadow-none" id="pesquisaLivro" name="pesquisaLivro" required  />
                    <i class="fa-solid fa-magnifying-glass"></i>
                </form>
                <i class="fa-solid fa-heart"></i>
                <a href="/cart/cartTotal" target="_top"><i class="fa-solid fa-shopping-cart"></i></a>
            </div>
        </nav>
    </section>

    <!--Arrivals-->
    <div class="arrivals">
        <h1>New Arrivals</h1>

        <div class="arrivals_box">
            <c:forEach var="livro" items="${livros}">
                <div class="arrivals_card">
                    <div class="arrivals_image">
                        <img src="/resources/indexcss/image/arrival_1.jpg">
                    </div>
                    <div class="arrivals_tag">
                        <p>${livro.getTitulo()}</p>
                        <div class="arrivals_icon">
                            <i class="fa-solid fa-star"></i>
                            <i class="fa-solid fa-star"></i>
                            <i class="fa-solid fa-star"></i>
                            <i class="fa-solid fa-star"></i>
                            <i class="fa-solid fa-star-half-stroke"></i>
                        </div>
                        <a href="aboutlivro/${livro.getIdlivro()}" class="arrivals_btn">Learn More</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

</body>
</html>