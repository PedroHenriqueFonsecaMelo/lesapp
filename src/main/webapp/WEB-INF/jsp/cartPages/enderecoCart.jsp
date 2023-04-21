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
          <form action="/cart/postEnderecoADD" method="post">
            <div class="inputbox2">
              <input type="text" class="form-control shadow-none" id="cep" name="cep" placeholder="Digite o seu CEP"  maxlength="8" minlength="8" required />
              <label for="cep">Digite o seu CEP</label>
            </div>
            <table class="tg">
              <tbody>
                <tr>
                  <td class="tg-0lax">
                        <select class="form-select shadow-none" id="pais" name="pais" disabled required data-input>
                          <option selected>Pais</option>
                          <option value="BR">Brasil</option>
                        </select>
                  </td>
                  <td class="tg-0lax">
                        <select class="form-select shadow-none" id="estado" name="estado" disabled required data-input>
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
                </tr>
                <tr>
                  <td class="tg-0lax">
                        <input type="text" class="form-control shadow-none" id="cidade" name="cidade" placeholder="Cidade" disabled required data-input />
                        <label for="cidade">Cidade</label>
                  </td>
                  <td class="tg-0lax">
                        <input type="text" class="form-control shadow-none" id="bairro" name="bairro" placeholder="Bairro" disabled required data-input />
                        <label for="bairro">Bairro</label>
                  </td>
                  
                </tr>
                <tr>
                  <td class="tg-0lax">
                        <input type="text" class="form-control shadow-none" id="rua" name="rua" placeholder="Rua" disabled required data-input />
                        <label for="rua">Rua</label>
                  </td>
                  <td class="tg-0lax">
                      <input type="text" class="form-control shadow-none" id="numero" name="numero" placeholder="Digite o número da residência" disabled data-input required />
                      <label for="numero">Digite o número da residência</label>
                  </td>
                </tr>
                <tr>
                  <td class="tg-0lax">
                        <input type="text" class="form-control shadow-none" id="complemento"  name="complemento" placeholder="Digite o complemento" disabled data-input required/>
                        <label for="complemento">Digite o complemento</label>
                  </td>
                  
                  <td class="tg-0lax">
                        <select class="form-select shadow-none" id="tiporesidencia" name="tiporesidencia" disabled required data-input>
                          <option selected>Tipo Residencia</option>
                          <option value="CASA">Casa</option>
                          <option value="APAR">Apartamento</option>
                          <option value="CONDO">Condominio</option>
                        </select>
                  </td>
                </tr>
              </tbody>
            </table>
            <button type="submit" id="save-btn" disabled>Cadastrar</button>
          </form>
        </div>
    </section>
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>