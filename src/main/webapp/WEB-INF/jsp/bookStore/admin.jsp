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
</head>


<body>
	<section class="section2">
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
					</div>
				</div>
				<div class="tab-content p-4 p-md-5" id="v-pills-tabContent">
					<div class="tab-pane fade show active" id="account" role="tabpanel" aria-labelledby="account-tab">
						<h3 class="mb-4">Account Settings</h3>
						<div class="row">
							<div class="col-md-12">
								<table>
								<tbody>
									<tr>
									<c:forEach var="cliente" items="${Clientes.get(0)}">
										<td>
											${cliente}
										</td>
									</c:forEach>
									</tr>
									<c:forEach var="cliente" items="${Clientes}"  begin="1">
										<tr>
										<c:forEach var="lista" items="${cliente}">
											<td>
												${lista}
											</td>
										</c:forEach>
										</tr>
									</c:forEach>
								</tbody>
								</table>
							</div>
						</div>
						<div>
							<button class="btn btn-primary" type="submit">Update</button>
							<button class="btn btn-light">Cancel</button>
							<a class="btn btn-light" href="/cliHome/delete" target="_top">Delete</a>
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
										<tr>
										<c:forEach var="lista" items="${cliente}">
											<td class="tg-0lax">
												${lista}
											</td>
										</c:forEach>
											<td class="tg-0lax">
												<a class="btn btn-light" href="/admin/cliPedido/${cliente.get(0)}" target="_top">Aprovar</a>
											</td>
											<td class="tg-0lax">
												<a class="btn btn-light" href="/admin/cliPedido/${cliente.get(0)}/0" target="_top">Recusar</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
								</table>
							</div>
						</div>
						<div>
							<button class="btn btn-primary" type="submit">Update</button>
							<button class="btn btn-light">Cancel</button>
							<a class="btn btn-light" href="/cliHome/delete" target="_top">Delete</a>
						</div>
					</div>
          			
					<div class="tab-pane fade" id="cartoes" role="tabpanel" aria-labelledby="cartoes-tab">
						<h3 class="mb-4">Troca Settings</h3>
						<div class="row">
							<div class="col-md-12">
								<table>
								<tbody>
									<tr>
									<c:forEach var="cliente" items="${Troca.get(0)}">
										<td>
											${cliente}
										</td>
									</c:forEach>
									</tr>
									<c:forEach var="cliente" items="${Troca}"  begin="1">
										<tr>
										<c:forEach var="lista" items="${cliente}">
											<td>
												${lista}
											</td>
										</c:forEach>
											<td>
												<a class="btn btn-light" href="/admin/cliTrocar/${cliente.get(0)}" target="_top">Aprovar</a>
											</td>
											<td>
												<a class="btn btn-light" href="/admin/cliTrocar/${cliente.get(0)}/0" target="_top">Recusar</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
								</table>
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