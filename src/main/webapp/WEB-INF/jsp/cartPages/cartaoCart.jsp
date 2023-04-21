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
          <form action="/cart/postCartaoADD" method="post">
            <div class="inputbox2">
              <label>Cadastro Cartao</label>
            </div>
            <table class="tg">
              <tbody>
                <tr>
                  <td class="tg-0lax">
                        <select class="form-select shadow-none" id="bandeira" name="bandeira" required>
                          <option selected>Banderira</option>
                          <option value="Mastercard">Mastercard</option>
                          <option value="Visa">Visa</option>
                          <option value="Elo">Elo</option>
                          <option value="AmericanExpress">American Express</option>
                          <option value="Hipercard">Hipercard</option>
                        </select>
                  </td>
                  <td class="tg-0lax">
                        <input type="text" class="form-control shadow-none" id="nomeCli" name="nomeCli"required  />
                        <label for="nomeCli">Nome Cliente</label>
                  </td>
                </tr>
                <tr>
                  <td class="tg-0lax">
                        <input type="text" class="form-control shadow-none" id="ncartao" name="ncartao" required />
                        <label for="ncartao">Numero Cartao</label>
                  </td>
                  <td class="tg-0lax">
                    <input type="number" class="form-control shadow-none" id="cv" name="cv"required  />
                    <label for="cv">cv</label>
                  </td>
                </tr>
              </tbody>
            </table>
            <button type="submit" id="save-btn">Cadastrar</button>
          </form>
        </div>
    </section>
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>