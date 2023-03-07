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
</head>


<body>
<div class="col-12 div-header" id="main">Tentativa</div>
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
					</div>
				</div>
				<div class="tab-content p-4 p-md-5" id="v-pills-tabContent">
					<div class="tab-pane fade show active" id="account" role="tabpanel" aria-labelledby="account-tab">
						<h3 class="mb-4">Account Settings</h3>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
								  	<label>Cliente Nome</label>
								  	<input type="text" class="form-control" value="${Cliente.getNome()}">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
								  	<label>Email</label>
								  	<input type="text" class="form-control" value="${Cliente.getEmail()}">
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
								  	<input type="text" class="form-control" value="${Cliente.getGen()}">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
								  	<label>Data Nasc</label>
								  	<input type="date" class="form-control" value="${Cliente.getDatanasc()}">
								</div>
							</div>
						</div>
						<div>
							<button class="btn btn-primary">Update</button>
							<button class="btn btn-light">Cancel</button>
						</div>
					</div>
					<div class="tab-pane fade" id="password" role="tabpanel" aria-labelledby="password-tab">
						<h3 class="mb-4">Password Settings</h3>
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
								  	<input type="password" class="form-control">
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
							<button class="btn btn-primary">Update</button>
							<button class="btn btn-light">Cancel</button>
						</div>
					</div>
          			<div class="tab-pane fade" id="endereco" role="tabpanel" aria-labelledby="endereco-tab">
						<h3 class="mb-4">Endereco Settings</h3>
						<c:forEach var="endereco" items="${enderecos}">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>Cep</label>
										<input type="text" class="form-control" value="${endereco.getCep()}">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label>Pais</label>
										<input type="text" class="form-control" value="${endereco.getPais()}">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Estado</label>
										<input type="text" class="form-control" value="${endereco.getEstado()}">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Cidade</label>
										<input type="text" class="form-control" value="${endereco.getCidade()}">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Bairro</label>
										<input type="text" class="form-control" value="${endereco.getBairro()}">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Rua</label>
										<input type="text" class="form-control" value="${endereco.getRua()}">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Numero</label>
										<input type="number" class="form-control" value="${endereco.getNumero()}">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Complemento</label>
										<input type="text" class="form-control" value="${endereco.getComplemento()}">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Tipo Residencia</label>
										<input type="text" class="form-control" value="${endereco.getTiporesidencia()}">
									</div>
								</div>
								<div class="col-md-3">
									<form action="https://google.com">
											<input type="submit" value="Go to Google" />
									</form>
									<form action="https://google.com">
											<input type="submit" value="Go to Google" />
									</form>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="tab-pane fade" id="cartoes" role="tabpanel" aria-labelledby="cartoes-tab">
						<h3 class="mb-4">Cartoes Settings</h3>
						<c:forEach var="cartao" items="${cartoes}">
								<div class="row">
									<div class="col-md-2">
										<div class="form-group">
											<label>Bandeira Cartao</label>
											<input type="text" class="form-control" value="${cartao.getBandeira()}">
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label>Numero Cartao</label>
											<input type="number" class="form-control" value="${cartao.getNcartao()}">
										</div>
									</div>
								
								
									<div class="col-md-2">
										<div class="form-group">
											<label>Nome Cliente Cartao</label>
											<input type="text" class="form-control" value="${cartao.getNomecli()}">
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label>Cartao CV</label>
											<input type="number" class="form-control" id="CV" name="CV" value="${cartao.getCv()}">
										</div>
									</div>

								
									<div class="col-md-2">
										<div class="form-group">
											<label>Cartao Preferencial</label>
											<c:choose>
												<c:when test="${cartao.getPreferencial() == '1'}">
													<input type="checkbox" class="form-control" id="Preferencial" name="Preferencial" checked>
												</c:when>
												<c:otherwise>
													<input type="checkbox" class="form-control" id="Preferencial" name="Preferencial">
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									<div class="col-md-2">
										<form action="/cartao/edit/${cartao.getIdCartao()}">
											<input type="submit" value="Go to Google" />
										</form>
										<form action="https://google.com">
											<input type="submit" value="Go to Google" />
										</form>
									</div>
								</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</section>


	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>