<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <link rel="stylesheet" href="/resources/clicss/cliLogin.css" type="text/css">
  <title>LES Login</title>
</head>
<body>
<section>
        <div class="form-box">
            <div class="form-value">
                <form action="/cliHome/singup" method="post">
                    <h2>Cadastrar</h2>
                    <div class="inputbox">
                        <ion-icon name="person-outline"></ion-icon>
                        <input type="text" required id="nome" name="nome">
                        <label for="nome">Nome</label>
                    </div>
                    <div class="inputbox">
                        <ion-icon name="mail-outline"></ion-icon>
                        <input type="email" required id="email" name="email" placeholder=" ">
                        <label for="email">Email</label>
                    </div>
                    <div class="inputbox">
                        <ion-icon name="lock-closed-outline"></ion-icon>
                        <input type="password" required id="password" name="senha">
                        <label for="password">Password</label>
                    </div>
                    <button type="submit">Cadastrar</button>
                </form>
            </div>
        </div>
    </section>
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>