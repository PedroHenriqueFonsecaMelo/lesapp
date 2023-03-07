<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Store Website</title>
    <link rel="stylesheet" href="/resources/livrocss/style.css">
    <link rel="stylesheet" type="text/css" href="/resources/clicss/cliSpace/cliSpace.css">

    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
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

    <section class="py-3">
		<div class="container col-12">
			<div class="bg-white shadow rounded-lg d-block d-sm-flex">
				<div class="profile-tab-nav border-right">
                    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
					    <c:forEach var="cartao" items="${cartoes}">
									<div class="col-md-12">
										<div class="form-group">
											<label>Bandeira Cartao</label>
											<label>${cartao.getBandeira()}</label>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label>Numero Cartao</label>
											<label>${cartao.getNcartao()}</label>
										</div>
									</div>
                                    <div class="col-md-12">
										<div class="form-group">
											<label>Remover Cartao</label>
											<a href="/removeCartaoCart/${cartao.getNcartao()}">${cartao.getNcartao()}</a>
										</div>
									</div>
					    </c:forEach>
                        <div class="dropdown">
                            <button class="dropbtn">Dropdown</button>
                            <div class="dropdown-content">
                                <c:forEach var="cartao" items="${cartoes2}">
                                    <a href="/addCartaoCart/${cartao.getNcartao()}">${cartao.getNcartao()}</a>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
				</div>
				<div class="tab-content p-4 p-md-5" id="v-pills-tabContent">
					<div class="arrivals_box">
                        <c:forEach var="livro" items="${livros}">
                            <div class="arrivals_card">
                                <div class="arrivals_image">
                                    <img src="/resources/indexcss/image/arrival_1.jpg">
                                </div>
                                <div class="arrivals_tag">
                                    <p>${livro.getPrecificacao()}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>