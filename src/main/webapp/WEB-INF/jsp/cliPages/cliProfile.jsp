<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Account Settings UI Design</title>
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="/resources/clicss/cliSpace/cliSpace.css">

	<link rel="stylesheet" href="/resources/livrocss/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	
	<script src="/resources/clicss/scripts.js" defer></script>
	
	<script src="/resources/clicss/jsTable/table.js" defer type="text/javascript"></script>
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
            </div>
        </nav>
    </section>


	<section class="py-3">
		<div class="container col-12">
			<div class="bg-white shadow rounded-lg d-block d-sm-flex">
				<div class="profile-tab-nav border-right">
					<div class="p-4">
						<div class="img-circle text-center mb-3">
							<img src="/resources/clicss/cliSpace/user2.jpg" alt="Image" class="shadow">
						</div>
						<h4 class="text-center">Kiran Acharya</h4>
					</div>
					<div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
						<a class="nav-link active" id="account-tab" data-toggle="pill" href="#account" role="tab" aria-controls="account" aria-selected="true">
							<i class="fa fa-user text-center mr-1"></i> 
							Conta Info
						</a>
						<a class="nav-link" id="password-tab" data-toggle="pill" href="#password" role="tab" aria-controls="password" aria-selected="false">
							<i class="fa fa-key text-center mr-1"></i> 
							Senha
						</a>
            			<a class="nav-link" id="endereco-tab" data-toggle="pill" href="#endereco" role="tab" aria-controls="endereco" aria-selected="false">
							<i class="fa fa-home text-center mr-1"></i> 
							Endereco
						</a>
            			<a class="nav-link" id="cartoes-tab" data-toggle="pill" href="#cartoes" role="tab" aria-controls="cartoes" aria-selected="false">
							<i class="fa fa-credit-card text-center mr-1"></i> 
							Cartoes
						</a>
						<a class="nav-link" id="ordem-tab" data-toggle="pill" href="#ordem" role="tab" aria-controls="ordem" aria-selected="false">
							<i class="fa fa-credit-card text-center mr-1"></i> 
							Ordem
						</a>
						<a class="nav-link" id="ordemDetails-tab" data-toggle="pill" href="#ordemDetails" role="tab" aria-controls="ordemDetails" aria-selected="false">
							<i class="fa fa-credit-card text-center mr-1"></i> 
							Ordem Details
						</a>
					</div>
				</div>
				<div class="tab-content p-4 p-md-5" id="v-pills-tabContent">
					<div class="tab-pane fade show active" id="account" role="tabpanel" aria-labelledby="account-tab">
						<h3 class="mb-4">Account Settings</h3>
					<form action="/cliHome/update" method="post">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
								  	<label>Cliente Nome</label>
								  	<input type="text" class="form-control" id = "nome" name="nome" value="${Cliente.getNome()}">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
								  	<label>Email</label>
								  	<input type="text" class="form-control" id="email" name="email" value="${Cliente.getEmail()}">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
								  	<label>Phone number</label>
								  	<input type="text" class="form-control" value="+91 9876543215">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
								  	<label>Genero</label>
								  	<input type="text" class="form-control" id="gen" name="gen" value="${Cliente.getGen()}">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
								  	<label>Data Nasc</label>
								  	<input type="date" class="form-control" id="datanasc" name="datanasc" value="${Cliente.getDatanasc()}">
								</div>
							</div>
						</div>
						<div>
							<button class="btn btn-primary" type="submit">Update</button>
							<button class="btn btn-light">Cancel</button>
							<a class="btn btn-light" href="/cliHome/delete" target="_top">Delete</a>
						</div>
					</form>
					</div>
					<div class="tab-pane fade" id="password" role="tabpanel" aria-labelledby="password-tab">
						<h3 class="mb-4">Password Settings</h3>
					<form action="/cliHome/update" method="post">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
								  	<label>Old password</label>
								  	<input type="password" class="form-control">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
								  	<label>New password</label>
								  	<input type="password" class="form-control" id="password" name="senha">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
								  	<label>Confirm new password</label>
								  	<input type="password" class="form-control">
								</div>
							</div>
						</div>
						<div>
							<button class="btn btn-primary" type="submit">Update</button>
							<button class="btn btn-light">Cancel</button>
						</div>
					</form>
						
					</div>
          			<div class="tab-pane fade" id="endereco" role="tabpanel" aria-labelledby="endereco-tab">
						<h3 class="mb-4">Endereco Settings</h3>
						<c:forEach var="endereco" items="${enderecos}">
						<form action="/endereco/update/${endereco.getIdEndereco()}" method="post">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>Cep</label>
										<input type="text" class="form-control" id="cep" name="cep" value="${endereco.getCep()}">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label>Pais</label>
										<input type="text" class="form-control" id="pais" name="pais" data-input value="${endereco.getPais()}">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Estado</label>
										<input type="text" class="form-control" id="estado" name="estado" data-input value="${endereco.getEstado()}">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Cidade</label>
										<input type="text" class="form-control" id="cidade" name="cidade" data-input value="${endereco.getCidade()}">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Bairro</label>
										<input type="text" class="form-control" id="bairro" name="bairro" data-input value="${endereco.getBairro()}">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Rua</label>
										<input type="text" class="form-control" id="rua" name="rua" data-input value="${endereco.getRua()}">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Numero</label>
										<input type="number" class="form-control"  id="numero" name="numero" data-input value="${endereco.getNumero()}">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Complemento</label>
										<input type="text" class="form-control" id="complemento"  name="complemento" data-input value="${endereco.getComplemento()}">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Tipo Residencia</label>
										<input type="text" class="form-control" id="tiporesidencia" name="tiporesidencia" data-input value="${endereco.getTiporesidencia()}">
									</div>
								</div>
							</div>
							<div>
								<button class="btn btn-primary" type="submit">Update</button>
								<button class="btn btn-light">Cancel</button>
								<a class="btn btn-light" href="/endereco/delete/${endereco.getIdEndereco()}" target="_top">Delete</a>
							</div>
						</form>
						</c:forEach>
						<div>
							<a class="btn btn-light" href="/cliHome/add/endereco/form" target="_top">Adicionar</a>
						</div>
					</div>
					<div class="tab-pane fade" id="cartoes" role="tabpanel" aria-labelledby="cartoes-tab">
						<h3 class="mb-4">Cartoes Settings</h3>
						<c:forEach var="cartao" items="${cartoes}">
							<form action="/cartao/update/${cartao.getIdCartao()}" method="post">
								<div class="row">
									<div class="col-md-2">
										<div class="form-group">
											<label>Bandeira Cartao</label>
											<input type="text" class="form-control" id="bandeira" name="bandeira" value="${cartao.getBandeira()}">
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label>Numero Cartao</label>
											<input type="number" class="form-control" id="ncartao" name="ncartao" value="${cartao.getNcartao()}">
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label>Nome Cliente Cartao</label>
											<input type="text" class="form-control" d="nomeCli" name="nomeCli" value="${cartao.getNomecli()}">
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label>Cartao CV</label>
											<input type="number" class="form-control" id="cv" name="cv" value="${cartao.getCv()}">
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label>Cartao Preferencial</label>
											<c:choose>
												<c:when test="${cartao.getPreferencial() == '1'}">
													<input type="checkbox" class="form-control" id="preferencial" name="preferencial" onclick='handleClick(this);' checked value="1">
												</c:when>
												<c:otherwise>
													<input type="checkbox" class="form-control" id="preferencial" name="preferencial" onclick='handleClick(this);' value="0">
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
								<div>
									<button class="btn btn-primary" type="submit">Update</button>
									<button class="btn btn-light">Cancel</button>
									<a class="btn btn-light" href="/cartao/delete/${cartao.getIdCartao()}" target="_top">Delete</a>
								</div>
							</form>
						</c:forEach>
						<div>
							<a class="btn btn-light" href="/cliHome/add/cartao/form" target="_top">Adicionar</a>
						</div>
					</div>
					<div class="tab-pane fade" id="ordem" role="tabpanel" aria-labelledby="ordem-tab">
						<h3 class="mb-4">Pedidos</h3>
						<div class="row">
						<div class="col-md-12">
							<table class="tg" id="TABLE1">
							<tbody>
								<tr>
									<c:forEach var="cliente" items="${ORDEM.get(0)}">
										<td class="tg-0lax">
											${cliente}
										</td>
									</c:forEach>
								</tr>
								<c:forEach var="cliente" items="${ORDEM}"  begin="1">
									<tr>
									<c:forEach var="lista" items="${cliente}">
										<td class="tg-0lax">
											${lista}
										</td>
										<c:choose>
											<c:when test = "${lista == 'EM PROCESSAMENTO'}">
												<td class="tg-0lax">
													<button onclick='changeTable("${cliente.get(0)}")' id="cliDetails${cliente.get(0)}" class="cart-btn">Add to cart</button>
												</td>
											</c:when>
										</c:choose>
									</c:forEach>
									</tr>
								</c:forEach>
							</tbody>
							</table>
							
						</div>
						</div>
					</div>
					<div class="tab-pane fade" id="ordemDetails" role="tabpanel" aria-labelledby="ordemDetails-tab">
						<h3 class="mb-4">Pedido Detalhes Troca</h3>
						<div class="row">
						<div class="col-md-12">
							<input type="hidden" id="json" value='${table}'>
							<div id="TABLE2"></div>
						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>


	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script src="/resources/livrocss/livros.js" defer></script>


	<script>
		function handleClick(cb) {
			if(cb.checked == true){
				cb.value = "1";
			} else {
				cb.value = "0"
			}
		}
	</script>
</body>
</html>