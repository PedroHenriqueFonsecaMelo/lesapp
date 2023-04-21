<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Store Website</title>
    <link rel="stylesheet" type="text/css" href="/resources/livrocss/style.css">
    <link rel="stylesheet" type="text/css" href="/resources/clicss/cliSpace/cliSpace.css">

    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    
    <script src="/resources/livrocss/livros.js" defer></script>
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
                    <input type="text" class="form-control2 shadow-none" id="pesquisaLivro" name="pesquisaLivro" required  />
                    <i class="fa-solid fa-magnifying-glass"></i>
                </form>
                <i class="fa-solid fa-heart"></i>
                <a href="/cart/cartTotal" target="_top"><i class="fa-solid fa-shopping-cart"></i></a>
				<a href="/cliHome/cliProfile" target="_top"> <i class="fa-solid fa-user"></i></a>
            </div>
        </nav>
    </section>

    <section class="py-3">
		<div class="container col-12">
			<div class="bg-white shadow rounded-lg d-block d-sm-flex">
				<div class="profile-tab-nav border-right">
                    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                        <form action="/cart/order" method="post">
                            <select class="form-select shadow-none" id="endereco" name="endereco">
                                <option selected value ="${enderecoPrincipal.getIdEndereco()}">${enderecoPrincipal.getBairro()}  ${enderecoPrincipal.getNumero()}</option>
                                 <c:forEach var="endereco" items="${outrosEnderecos}">
                                    <option value="${endereco.getIdEndereco()}">${endereco.getBairro()}  ${endereco.getNumero()}</option>
                                </c:forEach>
                            </select>
                            
					        <c:forEach var="cartao" items="${cartoes}">
									<div class="col-md-12">
										<div class="form-group">
											<label>Bandeira Cartao</label>
											<label>${cartao.getKey().getBandeira()}</label>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label>Numero Cartao</label>
											<label>${cartao.getKey().getNcartao()}</label>
										</div>
									</div>
                                    <div class="col-md-12">
										<div class="form-group">
											<label>Remover Cartao</label>
											<a href="/cart/removeCartaoCart/${cartao.getKey().getNcartao()}">${cartao.getKey().getNcartao()}</a>
										</div>
									</div>
                                    <div class="col-md-12">
										<div class="form-group">
											<label>Valor no cartao</label>
											<div class="wrapper">
                                                <input type="number" step="0.01" value="${TotalPorCartao}" min="10" id="in${cartao.getKey().getNcartao()}" name="in${cartao.getKey().getNcartao()}">
                                            </div>
										</div>
									</div>
					        </c:forEach>
                            <c:if test="${cupon.size() > 1}">
                                <select class="form-select shadow-none" id="cupon" name="cupon">
                                    <option selected></option>
                                    <c:forEach var="cupon" items="${cupon.get(1)}" begin="2">
                                        <option value="${cupon}">${cupon}  </option>
                                    </c:forEach>
                                </select>
                            </c:if>
                            <button class="dropbtn col-12" type="submit" id="save-btn">Finalizar compra</button>
                        </form>
                        <form action="/cart/getEnderecoADD" method="get">
                             <button class="dropbtn col-12" type="submit">Cadastrar Endereco</button>
                        </form>
                        <div class="dropdown">
                            <button class="dropbtn col-12">Cartoes</button>
                            <div class="dropdown-content">
                                <c:forEach var="cartao" items="${cartoes2}">
                                    <a href="/cart/addCartaoCart/${cartao.getNcartao()}">${cartao.getNcartao()}</a>
                                </c:forEach>
                                <a href="/cart/getCartaoADD">Cadastrar Cartao</a>
                            </div>
                        </div>
                        
                    </div>
				</div>
				<div class="tab-content p-4 p-md-5" id="v-pills-tabContent">
					<div class="arrivals_box">
                        <c:forEach var="livro" items="${livros}">
                            <div class="arrivals_card">
                                <div class="arrivals_image">
                                    <img class="img2"src="/resources/indexcss/image/arrival_1.jpg">
                                </div>
                                <div class="arrivals_tag">
                                    <p>${livro.getKey().getPrecificacao()}</p>
                                    <a href="/cart/removeLivroCart/${livro.getKey().getIdlivro()}">Remover Livro</a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>Total</label>
                            <label id="total">${Total}</label>
                        </div>
                    </div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>