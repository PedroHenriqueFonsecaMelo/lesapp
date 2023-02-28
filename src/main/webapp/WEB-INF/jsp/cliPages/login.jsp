
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
                <form action="/cliHome/login" method="post">
                    <h2>Login</h2>
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
                    
                    <button type="submit" formmethod="post" >Log in</button>
                    <div class="register">
                        <p>Don't have a account <a href="/cliHome/singup/form" target="_top" >Register</a></p>
                    </div>
                </form>
            </div>
        </div>
    </section>
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>