package locadora;

import locadora.dao.ClienteDAO;
import locadora.dao.LocacaoDAO;
import locadora.dao.PagamentoDAO;
import locadora.dao.VeiculoDAO;
import locadora.model.*;

import java.io.IOException;

import static locadora.model.StatusVeiculo.DISPONIVEL;
import static locadora.model.StatusVeiculo.LOCADO;

public class App
{
    public static void main( String[] args ) throws IOException {

//        Gson gson = new Gson();
//        String veiculos = gson.toJson(c);
//        System.out.println(veiculos);
//
//        Gson gson2 = new Gson();
//        Carro v = gson2.fromJson(veiculos, Carro.class);
//        System.out.println(v.getPlaca());



        VeiculoDAO v = new VeiculoDAO();
        Carro ca = new Carro("abc2000", "uno", 2000, LOCADO);
        Carro ca1 = new Carro("xyz2001", "corsa", 2005, DISPONIVEL);
//        Caminhao t = new Caminhao("sje2025", "truck", 1997, "disponível");
        v.salvar(ca);
        v.salvar(ca1);
//        v.salvar(t);
//        Moto m = new Moto("lmn7894", "honda", 2010, "locada");
//        v.salvar(m);
//        System.out.println(v);
//        System.out.println(new Gson().toJson(v));
//        System.out.println(v.ler(t));
//        v.deletar(c1);

//        ClienteDAO c = new ClienteDAO();
//        Cliente c1 = new Cliente("Matheus","123456789", "9999-8888", "matheus@ifpb.com");
//        c.salvar(c1);
//        Cliente c2 = new Cliente("Maria", "987654321", "9666-9666", "maria@ifpb.com");
//        c.salvar(c2);
//
//        LocacaoDAO l = new LocacaoDAO();
//        Locacao l1 = new Locacao(101, c1, ca, "02/02/2025", "18/02/2025");
//        Locacao l2 = new Locacao(102, c2, ca1, "01/01/2025", "12/02/2025");
//        l.salvar(l1);
//        l.salvar(l2);
//
//        PagamentoDAO p = new PagamentoDAO();
//        Pagamento p1 = new Pagamento(101, 101, 555.4, "21/02/2025", "credito");
//        p.salvar(p1);

    }
}
