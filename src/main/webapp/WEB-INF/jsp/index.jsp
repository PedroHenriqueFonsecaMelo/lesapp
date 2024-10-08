
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    
    <section>

        <nav>

            <div class="logo">
                <img src="/resources/indexcss/image/logo.png">
            </div>

            <ul>
                <li><a href="/">Home</a></li>
                <li><a href="#About">About</a></li>
                <li><a href="/shop" target="_top">Shop</a></li>
                <li><a href="/cliHome/login/form" target="_top">Login</a></li>
            </ul>

            <div class="social_icon">
                <form action="/pesquisa" method="post">
                    <input type="text" class="form-control shadow-none" id="pesquisaLivro" name="pesquisaLivro" required  />
                    <i class="fa-solid fa-magnifying-glass"></i>
                </form>
                <i class="fa-solid fa-heart"></i>
                <i class="fa-solid fa-user"></i>
            </div>

        </nav>

        <div class="main">

            <div class="main_tag">
                <h1>WELCOME TO<br><span>BOOK STORE</span></h1>

                <a href="#" class="main_btn">Learn More</a>

            </div>

            <div class="main_img">
                <img src="/resources/indexcss/image/table.png">
            </div>

        </div>

    </section>

    <!--About-->

    <div class="about">

        <div class="about_image">
            <img src="/resources/indexcss/image/about.png">
        </div>
        <div class="about_tag">
            <h1>About Us</h1>
            <p>
                Lorem ipsum, dolor sit amet consectetur adipisicing elit. Beatae cumque atque dolor corporis 
                architecto. Voluptate expedita molestias maxime officia natus consectetur dolor quisquam illo? 
                Quis illum nostrum perspiciatis laboriosam perferendis? Lorem ipsum dolor sit amet consectetur 
                adipisicing elit. Minus ad eius saepe architecto aperiam laboriosam voluptas nobis voluptates 
                id amet eos repellat corrupti harum consectetur, dolorum dolore blanditiis quam quo.
            </p>
            <a href="#" class="about_btn">Learn More</a>
        </div>

    </div>







    
</body>
</html>