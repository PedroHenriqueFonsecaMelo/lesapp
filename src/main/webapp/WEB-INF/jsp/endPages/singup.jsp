<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <link rel="stylesheet" href="/resources/clicss/cliLogin.css" type="text/css">
  <!-- Bootstrap -->
  <script src="/resources/clicss/scripts.js" defer></script>
  <title>LES Login</title>
</head>
<body>
<section>
        <div class="form-box-big">
          <form action="/endereco/singup" method="post">
            <div class="inputbox2">
              <input type="text" class="form-control shadow-none" id="cep" name="cep" placeholder="Digite o seu CEP"  maxlength="8" minlength="8" required />
              <label for="cep">Digite o seu CEP</label>
            </div>
            <table class="tg">
              <tbody>
                <tr>
                  <td class="tg-0lax">
                        <select class="form-select shadow-none" id="region" disabled required data-input>
                          <option selected>Estado</option>
                          <option value="AC">Acre</option>
                          <option value="AL">Alagoas</option>
                          <option value="AP">Amapá</option>
                          <option value="AM">Amazonas</option>
                          <option value="BA">Bahia</option>
                          <option value="CE">Ceará</option>
                          <option value="DF">Distrito Federal</option>
                          <option value="ES">Espírito Santo</option>
                          <option value="GO">Goiás</option>
                          <option value="MA">Maranhão</option>
                          <option value="MT">Mato Grosso</option>
                          <option value="MS">Mato Grosso do Sul</option>
                          <option value="MG">Minas Gerais</option>
                          <option value="PA">Pará</option>
                          <option value="PB">Paraíba</option>
                          <option value="PR">Paraná</option>
                          <option value="PE">Pernambuco</option>
                          <option value="PI">Piauí</option>
                          <option value="RJ">Rio de Janeiro</option>
                          <option value="RN">Rio Grande do Norte</option>
                          <option value="RS">Rio Grande do Sul</option>
                          <option value="RO">Rondônia</option>
                          <option value="RR">Roraima</option>
                          <option value="SC">Santa Catarina</option>
                          <option value="SP">São Paulo</option>
                          <option value="SE">Sergipe</option>
                          <option value="TO">Tocantins</option>
                        </select>
                  </td>
                  <td class="tg-0lax">
                        <input type="text" class="form-control shadow-none" id="city" name="city" placeholder="Cidade" disabled required data-input />
                        <label for="city">Cidade</label>
                  </td>
                </tr>
                <tr>
                  <td class="tg-0lax">
                        <input type="text" class="form-control shadow-none" id="neighborhood" name="neighborhood" placeholder="Bairro" disabled required data-input />
                        <label for="neighborhood">Bairro</label>
                  </td>
                  <td class="tg-0lax">
                        <input type="text" class="form-control shadow-none" id="address" name="address" placeholder="Rua" disabled required data-input />
                        <label for="address">Rua</label>
                  </td>
                </tr>
                <tr>
                  <td class="tg-0lax">
                      <input type="text" class="form-control shadow-none" id="number" name="number" placeholder="Digite o número da residência" disabled data-input required />
                      <label for="number">Digite o número da residência</label>
                  </td>
                  <td class="tg-0lax">
                        <input type="text" class="form-control shadow-none" id="complement"  name="complement" placeholder="Digite o complemento" disabled data-input required/>
                        <label for="complement">Digite o complemento</label>
                  </td>
                </tr>
              </tbody>
            </table>
            <input type="number" name="cliuid" id="cliuid" value="${uid}" hidden>
            <button type="submit" id="save-btn">Cadastrar</button>
          </form>
        </div>
    </section>
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>