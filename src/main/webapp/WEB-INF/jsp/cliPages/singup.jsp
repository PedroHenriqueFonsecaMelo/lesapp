<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <link rel="stylesheet" href="/resources/clicss/cliLogin.css" type="text/css">
  <title>LES Login</title>
</head>
<body>
<section>
        <div class="form-box-big">
            <div class="form-value">
                <form action="/cliHome/singup" method="post">
                    <h2>Cadastrar</h2>
                    <table class="tg">
                        <tbody>
                            <tr>
                                <td class="tg-0lax">
                                    <ion-icon name="person-outline"></ion-icon>
                                    <input type="text" required id="nome" name="nome">
                                    <label for="nome">Nome</label>
                                </td>
                                <td class="tg-0lax">
                                    <input type="date" required id="datanasc" name="datanasc">
                                </td>
                            </tr>
                            <tr>
                                <td class="tg-0lax">
                                    <select id="gen" name="gen" required>
                                        <option selected>Genero</option>
                                        <option value="M">Masculino</option>
                                        <option value="F">Feminino</option>
                                        <option value="O">Outro</option>
                                    </select>
                                </td>
                                <td class="tg-0lax">
                                    <ion-icon name="mail-outline"></ion-icon>
                                    <input type="email" required id="email" name="email" placeholder=" ">
                                    <label for="email">Email</label>
                                </td>
                            </tr>
                            <tr>
                                <td class="tg-0lax">
                                    <ion-icon name="lock-closed-outline"></ion-icon>
                                    <input type="password" required id="password" name="senha">
                                    <label for="password">Password</label>
                                </td>
                                <td class="tg-0lax">
                                    <button type="submit">Cadastrar</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </section>
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>