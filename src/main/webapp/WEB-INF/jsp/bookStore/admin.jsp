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
					<div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
						<a class="nav-link active" id="account-tab" data-toggle="pill" href="#account" role="tab" aria-controls="account" aria-selected="true">
							<i class="fa fa-user text-center mr-1"></i> 
							Contas Info
						</a>
						<a class="nav-link" id="password-tab" data-toggle="pill" href="#password" role="tab" aria-controls="password" aria-selected="false">
							<i class="fa fa-key text-center mr-1"></i> 
							Ordem Info
						</a>
            			
            			<a class="nav-link" id="cartoes-tab" data-toggle="pill" href="#cartoes" role="tab" aria-controls="cartoes" aria-selected="false">
							<i class="fa fa-credit-card text-center mr-1"></i> 
							Transações
						</a>
						<a class="nav-link" id="livros-tab" data-toggle="pill" href="#livros" role="tab" aria-controls="livros" aria-selected="false">
							<i class="fa fa-book text-center mr-1"></i> 
							Adicionar Livros
						</a>
						<a class="nav-link" id="relatorio-tab" data-toggle="pill" href="#relatorio" role="tab" aria-controls="relatorio" aria-selected="false">
							<i class="fa fa-book text-center mr-1"></i> 
							Relatorio
						</a>
						<a class="nav-link" id="LivrosCadastrados-tab" data-toggle="pill" href="#LivrosCadastrados" role="tab" aria-controls="LivrosCadastrados" aria-selected="false">
							<i class="fa fa-book text-center mr-1"></i> 
							Livros Cadastrados
						</a>
					</div>
				</div>
				<div class="tab-content p-4 p-md-5" id="v-pills-tabContent">
					<div class="tab-pane fade show active" id="account" role="tabpanel" aria-labelledby="account-tab">
						<h3 class="mb-4">Account Settings</h3>
						<div class="row">
						<div class="col-md-12">
							<form class="formAdmin" action="/admin/pesquisa" method="post" >
								<h5>Pesquisa Cliente</h5>
								<table class="tg">
								<tbody>
									<tr>
										<td class="tg-0lax">
											<select class="form-select shadow-none" id="pesquisaCli" name="pesquisaCli" required>
											<c:forEach var="cliente" items="${Clientes.get(0)}">
											<option value="${cliente}">${cliente}</option>
											</c:forEach>
											</select>
										</td>
										<td class="tg-0lax">
											<input type="text" class="form-control shadow-none" id="pesquisaCliValue" name="pesquisaCliValue"required  />
										</td>
										<td class="tg-0lax">
											<button class="btn btn-primary" type="submit">Pesquisar</button>
										</td>
									</tr>
								</tbody>
								</table>
								
							</form>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<table class="tg">
								<tbody>
									<tr>
									<c:forEach var="cliente" items="${Clientes.get(0)}">
										<td class="tg-0lax">
											${cliente}
										</td>
									</c:forEach>
									</tr>
									<c:forEach var="cliente" items="${Clientes}"  begin="1">
										<tr>
										<c:forEach var="lista" items="${cliente}">
											<td class="tg-0lax">
												${lista}
											</td>
										</c:forEach>
										</tr>
									</c:forEach>
								</tbody>
								</table>
							</div>
						</div>
						
					</div>
					<div class="tab-pane fade" id="password" role="tabpanel" aria-labelledby="password-tab">
						<h3 class="mb-4">Ordem Settings</h3>
						<div class="row">
							<div class="col-md-12">
								<table class="tg">
								<tbody>
									<tr>
									<c:forEach var="cliente" items="${Ordem.get(0)}">
										<td class="tg-0lax">
											${cliente}
										</td>
									</c:forEach>
									</tr>
									<c:forEach var="cliente" items="${Ordem}"  begin="1">
										<c:if test="${cliente != null}">
											<tr>
												<c:forEach var="lista" items="${cliente}">
													<td class="tg-0lax">
														${lista}
													</td>
												</c:forEach>
												
												<form action="/admin/pedidostatus" method="post">
												<td class="tg-0lax">
													<select class="form-select shadow-none" id="pedidoStatus" name="pedidoStatus" required>
														<option selected value="Aprovar">Aprovar</option>
														<option value="Recusar">Recusar</option>
														<option value="Em Processamento">Em Processamento</option>
													</select>
												</td>
												<input type="hidden" id="index" name="index" value="${cliente.get(0)}">
												
												<td class="tg-0lax">
													<button type='submit' id='save-btn'>Alterar Status</button>
												</td>
											</form>
												
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
								</table>
							</div>
						</div>
					</div>
          			
					<div class="tab-pane fade" id="cartoes" role="tabpanel" aria-labelledby="cartoes-tab">
						<h3 class="mb-4">Troca Settings</h3>
						<div class="row">
							<div class="col-md-12">
								<table class="tg">
								<tbody>
									<tr>
									<c:forEach var="cliente" items="${Troca.get(0)}">
										<td class="tg-0lax">
											${cliente}
										</td>
									</c:forEach>
									</tr>
									<c:forEach var="cliente" items="${Troca}"  begin="1">
										<c:if test="${cliente != null}">
										<tr>
										<c:forEach var="lista" items="${cliente}">
											<td class="tg-0lax">
												${lista}
											</td>
										</c:forEach>
											<form action="/admin/trocastatus" method="post">
												<td class="tg-0lax">
													<select class="form-select shadow-none" id="trocaStatus" name="trocaStatus" required>
														<option selected value="Aprovar Troca">Aprovar</option>
														<option value="Recusar Troca">Recusar</option>
														<option value="Troca Em Processamento">Em Processamento</option>
													</select>
												</td>
												<input type="hidden" id="index" name="index" value="${cliente.get(0)}">
												<td class="tg-0lax">
													<button type='submit' id='save-btn'>Alterar Status</button>
												</td>
											</form>
										</tr>
										</c:if>
									</c:forEach>
								</tbody>
								</table>
							</div>
						</div>
					</div>

					<div class="tab-pane fade" id="livros" role="tabpanel" aria-labelledby="livros-tab">
						<h3 class="mb-4">Adicionar Livro</h3>
						<form action="/admin/livro" method="post">
							<div class="row">
								<c:forEach var="livro" items="${livros}">
								<div class="col-md-4">
									<div class="form-group">
										<label style="display: block;">${livro.getKey()}</label>
										<input ${livro.getValue()} id="${livro.getKey()}" name="${livro.getKey()}">
									</div>
								</div>
								</c:forEach>
							</div>
							<div>
								<button class="btn btn-primary" type="submit">Update</button>
								<button class="btn btn-light">Cancel</button>
							</div>
						</form>
					</div>

					<div class="tab-pane fade" id="relatorio" role="tabpanel" aria-labelledby="relatorio-tab">
						<h3 class="mb-4">Grafico de Vendas</h3>
						<input type="hidden" id="json" value='${grafico}'>
						<input type="hidden" id="json2" value='${graficoDetalhes}'>
						<div id="piechart" style="width: 500px; height: 400px;">
						</div>
						<select class="form-select shadow-none" id="AnosGraph" name="AnosGraph">
							<option value="null"></option>
							${datas}
						</select>
					</div>

					<div class="tab-pane fade" id="LivrosCadastrados" role="tabpanel" aria-labelledby="LivrosCadastrados-tab">
						<h3 class="mb-4">Livros Cadastrados</h3>
						<table class="tg2" id="TABLE2">
								${LivrosCadastrados}
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>


	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	
	<script src="/resources/graficos/draw.js"></script>

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