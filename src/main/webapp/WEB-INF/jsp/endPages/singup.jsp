<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <link rel="stylesheet" href="/resources/clicss/endere.css" type="text/css">
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css"/>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
         integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous"/>
  <script src="/resources/clicss/scripts.js" defer></script>
  <title>LES Login</title>
</head>
<body>
  <div id="fade" class="hide">
    <div id="loader" class="spinner-border text-info hide" role="status">
      <span class="visually-hidden">Loading...</span>
    </div>
    <div id="message" class="hide">
      <div class="alert alert-light" role="alert">
        <h4>Mensagem:</h4>
        <p></p>
        <button id="close-message" type="button" class="btn btn-secondary">
          Fechar
        </button>
      </div>
    </div>
  </div>
  <div id="order-form-container" class="container p-6 my-md-4 px-md-0">
    <div id="form-header">
      <h2>Cadastre o seu endereço</h2>
      <p>Preencha os campos para podermos enviar seus produtos</p>
    </div>
    <form id="address-form">
      <div class="row mb-3">
        <div class="form-floating">
          <input type="text" class="form-control shadow-none" id="cep" name="cep" placeholder="Digite o seu CEP" maxlength="8" minlength="8" required/>
          <label for="cep">Digite o seu CEP</label>
        </div>
      </div>
      <div class="row mb-3">
        <div class="col-12 col-sm-6 mb-3 mb-md-0 form-floating">
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
        </div>
        <div class="col-12 col-sm-6 form-floating">
          <input type="text" class="form-control shadow-none" id="city" name="city" placeholder="Cidade" disabled required data-input />
          <label for="city">Cidade</label>
        </div>
      </div>
      <div class="row mb-3">
        <div class="col-12 col-sm-6 mb-3 mb-md-0 form-floating">
          <input type="text" class="form-control shadow-none" id="neighborhood" name="neighborhood" placeholder="Bairro" disabled required data-input />
          <label for="neighborhood">Bairro</label>
        </div>
        <div class="col-12 col-sm-6 form-floating">
          <input type="text" class="form-control shadow-none" id="address" name="address" placeholder="Rua" disabled required data-input />
          <label for="address">Rua</label>
        </div>
      </div>
      <div class="row mb-3">
        <div class="col-12 col-sm-6 mb-3 mb-md-0 form-floating">
          <input type="text" class="form-control shadow-none" id="number" name="number" placeholder="Digite o número da residência" required />
          <label for="number">Digite o número da residência</label>
        </div>
        <div class="col-12 col-sm-6 mb-3 mb-md-0 form-floating">
          <input type="text" class="form-control shadow-none" id="complement" name="complement" placeholder="Digite o complemento" data-input/>
          <label for="complement">Digite o complemento</label>
        </div>
      </div>
      <div class="d-flex justify-content-end">
        <button id="save-btn" type="submit" class="btn btn-primary">
          Cadastrar
        </button>
      </div>
    </form>
  </div>
</body>
</html>