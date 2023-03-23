package fatec.ph.les.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import fatec.ph.les.entidade.Cartao;
import fatec.ph.les.entidade.Categoria;
import fatec.ph.les.entidade.Cliente;
import fatec.ph.les.entidade.Endereco;
import fatec.ph.les.entidade.Livro;
import fatec.ph.les.servicos.connectBD;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        init();
        return "index";
    }

    private void init() {
        connectBD.CreateTableX(Cliente.class);
        connectBD.CreateTableX(Endereco.class);
        connectBD.CreateTableX(Cartao.class);
        connectBD.CreateTableX(Livro.class);
        connectBD.CreateTableX(Categoria.class);

        String st = "insert into LIVRO  ( autor,  ano,  titulo,  editora,  edicao,  isbn,  npaginas,  sinopse,  altura,  largura,  categorias,  peso,  profundidade,  precificacao,  barras ) VALUES ('ph',2023,'jose','WB',1,123,200,' joao e maria', 12.20 , 2.20, 'terror', 23 , 2, 22.2 , 1101 );";
        String sy = "insert into Cliente (senha , nome , datanasc , gen , email) VALUES  ('122' , '12' , '2023-03-06' , 'M' , '3@2' ); ";
        String su = "insert into Endereco (cliuid , pais , cep , estado , cidade , rua , bairro , numero , complemento , tiporesidencia) VALUES  ( 1  , 'BR' , '08780690' , 'SP' , 'Mogi das Cruzes' , 'Rua Professor Manoel Acelino de Mello' , 'Jardim Armênia' ,  21  , '21' , 'CASA' );";
        String si = "insert into Cartao (ncartao , bandeira , nomecli , cli_id , cv , preferencial) VALUES  ( 21  , 'Visa' , '21' ,  1  ,  22  ,  1  ); ";
        String so = "insert into Cartao (ncartao , bandeira , nomecli , cli_id , cv , preferencial) VALUES  ( 212  , 'Visa' , '21' ,  1  ,  22  ,  0  ); ";
        connectBD.EXEquery(
                "create table cupons (cupons_id int primary key AUTO_INCREMENT, cli_id int, desconto NUMERIC (20,2))");
        connectBD.EXEquery("insert into cupons (cli_id, desconto) values  (1, 11.1);");
        connectBD.EXEquery(st);
        connectBD.EXEquery(sy);
        connectBD.EXEquery(su);
        connectBD.EXEquery(si);
        connectBD.EXEquery(so);
    }

    @GetMapping("/shop")
    public String shop(Model model, HttpServletRequest request) {
        ArrayList<Livro> ls = Livro.livroCLIUID(0, 0);

        ArrayList<Livro> top = new ArrayList<>();
        top.addAll(ls.subList(0, (ls.size() / 2)));

        ArrayList<Livro> bottom = new ArrayList<>();
        bottom.addAll(ls.subList((ls.size() / 2), ls.size()));

        model.addAttribute("livros", top);
        model.addAttribute("livros2", bottom);
        return "shopPages/shop";
    }

    @GetMapping("/aboutlivro/{id}")
    public String aboutlivro(@PathVariable(value = "id") int id, Model model, HttpServletRequest request) {

        model.addAttribute("livro", Livro.livroCLIUID(id, 0).get(0));

        return "bookStore/aboutLivro";
    }
}
