package fatec.ph.les.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        /*
         * Cliente cli = new Cliente();
         * 
         * System.out.println("teste: " + connectBD.CreateTableX(Cliente.class));
         * 
         * Cliente.Inserir("123", "joah", "fatec@");
         * 
         */

        // System.out.println("cli2: " + cli.getClass().getFields().toString());

        /*
         * try {
         * Class.forName("org.h2.Driver");
         * 
         * // Establishing Connection
         * Connection con = DriverManager.getConnection(
         * "jdbc:h2:mem:testdb", "sa", "password");
         * Statement stmt = con.createStatement();
         * String query =
         * "create table TEST (id integer not null primary key,  fullname varchar2(50))"
         * ;
         * stmt.execute(query);
         * 
         * System.out.println("Connected");
         * 
         * con.close();
         * } catch (Exception e) {
         * System.out.println(e);
         * }
         */

        // model.addAttribute("cli", cli.CreateClienteTable());

        return "index";
    }
}
