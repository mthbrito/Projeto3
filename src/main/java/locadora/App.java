package locadora;

import locadora.controller.ClienteController;
import locadora.dao.ClienteDAO;
import locadora.dao.LocacaoDAO;
import locadora.model.Cliente;

import java.io.IOException;

public class App
{
    public static void main( String[] args ) throws IOException {

        ClienteController cc = new ClienteController();
//        System.out.println(new ClienteController().validarNome("Ads2025"));
//        System.out.println(new ClienteController().validarNome("Ads"));
//        System.out.println(new ClienteController().validarCpf("123.255.789-00"));
//        System.out.println(new ClienteController().validarCpf("157.755.4.88-00"));
//        System.out.println(new ClienteController().validarCpf("12345678900"));
//        System.out.println(new ClienteController().validarCpf("123456789-00"));

//        System.out.println(cc.validarEmail("joao@ifpb.com"));
//        System.out.println(cc.validarEmail("joao97@if.com"));

//        new LocacaoDAO().deletar(5000);
//        System.out.println(new ClienteDAO().ler("258.147.369-00"));
//        System.out.println(new ClienteDAO().ler("110.110.789-79"));
//        new ClienteDAO().atualizar(new Cliente("João", "899.456.789-10", "(83)9888-8888", "joao@gmail.com"));

//        new LoginAdmView().acessarAdm(new JTextField("admin@locadora.com"), new JTextField("admin"));
//        System.out.println(new JTextField("admin@locadora.com").getText().equals("admin@locadora.com"));
//
//        List<Usuario> usuariosCadastrados = new ArrayList<>();
//        usuariosCadastrados = new UsuarioDAO().getUsuariosAdm();
//        System.out.println(usuariosCadastrados);
//        new RelatorioGerencial().gerarRelatorioVeiculosLocados();
//        new RelatorioGerencial().gerarFaturamentoMensal();
//        new RelatorioGerencial().gerarRelatorioClientesLocacoes();
//        Gson gson = new Gson();
//        String veiculos = gson.toJson(c);
//        System.out.println(veiculos);
//
//        Gson gson2 = new Gson();
//        Carro v = gson2.fromJson(veiculos, Carro.class);
//        System.out.println(v.getPlaca());


//
//        VeiculoDAO v = new VeiculoDAO();
//        Carro ca = new Carro("abc2000", "uno", 2000, LOCADO);
//        Carro ca1 = new Carro("xyz2001", "corsa", 2005, DISPONIVEL);
////        Caminhao t = new Caminhao("sje2025", "truck", 1997, "disponível");
//        v.salvar(ca);
//        v.salvar(ca1);
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
