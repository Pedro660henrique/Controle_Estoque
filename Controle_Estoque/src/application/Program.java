package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.impl.ClienteDaoJDBC;
import model.dao.impl.EntradaDaoJDBC;
import model.dao.impl.FornecedorDaoJDBC;
import model.dao.impl.LoginDaoJDBC;
import model.dao.impl.ProdutoDaoJDBC;
import model.dao.impl.SaidaDaoJDBC;
import model.dao.impl.UsuarioDaoJDBC;
import model.entities.Cliente;
import model.entities.Entrada;
import model.entities.Fornecedor;
import model.entities.Produto;
import model.entities.Saida;
import model.entities.Usuario;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Bem-vindo ao sistema!");
            System.out.println("1. Criar novo usuário");
            System.out.println("2. Fazer login");
            System.out.println("3. Cadastrar Produto");
            System.out.println("4. Cadastrar Fornecedor");
            System.out.println("5. Cadastrar Cliente");
            System.out.println("6. Registrar Entrada de Produto");
            System.out.println("7. Registrar Saída de Produto");
            System.out.println("8. Buscar Produto");
            System.out.println("9. Buscar Cliente");
            System.out.println("10. Buscar Fornecedor");
            System.out.println("0. Sair");

            System.out.print("Escolha uma opção (0-10): ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Insira as informções do novo Usuario: ");
                    System.out.print("Digite seu nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Digite sua data de nascimento (dd/MM/yyyy): ");
                    String dataNascimentoStr = sc.nextLine();
                    LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    System.out.print("Digite sua senha: ");
                    String senha = sc.nextLine();

                    Usuario novoUsuario = new Usuario(null, nome, dataNascimento, senha);
                    UsuarioDaoJDBC usuarioDao = (UsuarioDaoJDBC) DaoFactory.createUsuarioDao();
                    usuarioDao.insert(novoUsuario);

                    System.out.println("Usuário criado com sucesso! Agora você pode fazer login.");
                    break;

                case 2:
                    System.out.print("Digite seu ID de usuário: ");
                    Integer idUsuario = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Digite sua senha: ");
                    senha = sc.nextLine();

                    boolean autenticado = LoginDaoJDBC.autenticar(idUsuario, senha);

                    if (autenticado) {
                        System.out.println("Acesso permitido! Bem-vindo ao sistema.");
                    } else {
                        System.out.println("Acesso negado. Credenciais inválidas.");
                    }
                    break;

                case 3:
                    System.out.print("Digite o nome do produto: ");
                    String nomeProduto = sc.nextLine();

                    System.out.print("Digite a marca do produto: ");
                    String marcaProduto = sc.nextLine();

                    System.out.print("Digite o lote do produto: ");
                    int lote = sc.nextInt();

                    System.out.print("Digite o preço do produto: ");
                    double preco = sc.nextDouble();

                    System.out.print("Digite a quantidade do produto: ");
                    int quantidade = sc.nextInt();

                    Produto novoProduto = new Produto(null, nomeProduto, marcaProduto, preco, lote, quantidade, null);
                    ProdutoDaoJDBC produtoDao = (ProdutoDaoJDBC) DaoFactory.createProdutoDao();
                    produtoDao.insert(novoProduto);

                    System.out.println("Produto cadastrado com sucesso!");
                    break;

                case 4:
                    sc.nextLine(); 
                    System.out.print("Digite o nome do fornecedor: ");
                    String nomeFornecedor = sc.nextLine();

                    System.out.print("Digite o endereço do fornecedor: ");
                    String enderecoFornecedor = sc.nextLine();

                    System.out.print("Digite a cidade do fornecedor: ");
                    String cidade = sc.nextLine();

                    System.out.print("Digite o bairro do fornecedor: ");
                    String bairro = sc.nextLine();

                    System.out.print("Digite o CEP do fornecedor: ");
                    String cep = sc.nextLine();

                    System.out.print("Digite o UF do fornecedor: ");
                    String uf = sc.nextLine();

                    System.out.print("Digite o telefone do fornecedor: ");
                    String telefone = sc.nextLine();

                    System.out.print("Digite o CNPJ do fornecedor: ");
                    String cnpj = sc.nextLine();

                    Fornecedor novoFornecedor = new Fornecedor(null, nomeFornecedor, enderecoFornecedor, cidade, bairro, cep, uf, telefone, cnpj);
                    FornecedorDaoJDBC fornecedorDao = (FornecedorDaoJDBC) DaoFactory.createFornecedorDao();
                    fornecedorDao.insert(novoFornecedor);

                    System.out.println("Fornecedor cadastrado com sucesso!");
                    break;

                case 5:
                    sc.nextLine();
                    System.out.print("Digite o nome do cliente: ");
                    String nomeCliente = sc.nextLine();

                    System.out.print("Digite o telefone do cliente: ");
                    String telefoneCliente = sc.nextLine();

                    System.out.print("Digite o endereço do cliente: ");
                    String enderecoCliente = sc.nextLine();

                    System.out.print("Digite a cidade do cliente: ");
                    String cidadeCliente = sc.nextLine();

                    System.out.print("Digite o bairro do cliente: ");
                    String bairroCliente = sc.nextLine();

                    System.out.print("Digite o CEP do cliente: ");
                    String cepCliente = sc.nextLine();

                    System.out.print("Digite o UF do cliente: ");
                    String ufCliente = sc.nextLine();

                    Cliente novoCliente = new Cliente(null, nomeCliente, telefoneCliente, enderecoCliente, cidadeCliente, bairroCliente, cepCliente, ufCliente);
                    ClienteDaoJDBC clienteDao = (ClienteDaoJDBC) DaoFactory.createClienteDao();
                    clienteDao.insert(novoCliente);

                    System.out.println("Cliente cadastrado com sucesso!");
                    break;

                case 6:
                    System.out.print("Digite o ID do produto: ");
                    int idProduto = sc.nextInt();

                    ProdutoDaoJDBC produtoDaoEntrada = (ProdutoDaoJDBC) DaoFactory.createProdutoDao();
                    Produto produtoEntrada = produtoDaoEntrada.findById(idProduto);
                    if (produtoEntrada == null) {
                        System.out.println("Produto não encontrado.");
                        break;
                    }

                    System.out.print("Digite o ID do fornecedor: ");
                    int idFornecedorEntrada = sc.nextInt();

                    FornecedorDaoJDBC fornecedorDaoEntrada = (FornecedorDaoJDBC) DaoFactory.createFornecedorDao();
                    Fornecedor fornecedorEntrada = fornecedorDaoEntrada.findById(idFornecedorEntrada);
                    if (fornecedorEntrada == null) {
                        System.out.println("Fornecedor não encontrado.");
                        break;
                    }

                    System.out.print("Digite o ID de usuário responsável pela entrada: ");
                    int idUsuarioEntrada = sc.nextInt();

                    UsuarioDaoJDBC usuarioDaoEntrada = (UsuarioDaoJDBC) DaoFactory.createUsuarioDao();
                    Usuario usuarioEntrada = usuarioDaoEntrada.findById(idUsuarioEntrada);
                    if (usuarioEntrada == null) {
                        System.out.println("Usuário não encontrado.");
                        break;
                    }

                    System.out.print("Digite a data de entrada (dd/MM/yyyy): ");
                    sc.nextLine();
                    String dataEntradaStr = sc.nextLine();
                    LocalDate dataEntrada = LocalDate.parse(dataEntradaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    Entrada novaEntrada = new Entrada(null, dataEntrada, usuarioEntrada, produtoEntrada, fornecedorEntrada);
                    EntradaDaoJDBC entradaDao = (EntradaDaoJDBC) DaoFactory.createEntradaDao();
                    entradaDao.insert(novaEntrada);

                    System.out.println("Entrada registrada com sucesso!");
                    break;

                case 7:
                    System.out.print("Digite o ID do produto: ");
                    int idProdutoSaida = sc.nextInt();

                    ProdutoDaoJDBC produtoDaoSaida = (ProdutoDaoJDBC) DaoFactory.createProdutoDao();
                    Produto produtoSaida = produtoDaoSaida.findById(idProdutoSaida);
                    if (produtoSaida == null) {
                        System.out.println("Produto não encontrado.");
                        break;
                    }

                    System.out.print("Digite o ID do cliente: ");
                    int idClienteSaida = sc.nextInt();

                    ClienteDaoJDBC clienteDaoSaida = (ClienteDaoJDBC) DaoFactory.createClienteDao();
                    Cliente clienteSaida = clienteDaoSaida.findById(idClienteSaida);
                    if (clienteSaida == null) {
                        System.out.println("Cliente não encontrado.");
                        break;
                    }

                    System.out.print("Digite o ID de usuário responsável pela saída: ");
                    int idUsuarioSaida = sc.nextInt();

                    UsuarioDaoJDBC usuarioDaoSaida = (UsuarioDaoJDBC) DaoFactory.createUsuarioDao();
                    Usuario usuarioSaida = usuarioDaoSaida.findById(idUsuarioSaida);
                    if (usuarioSaida == null) {
                        System.out.println("Usuário não encontrado.");
                        break;
                    }

                    System.out.print("Digite o preço da saída: ");
                    double precoSaida = sc.nextDouble();

                    System.out.print("Digite a data de saída (dd/MM/yyyy): ");
                    sc.nextLine();
                    String dataSaidaStr = sc.nextLine();
                    LocalDate dataSaida = LocalDate.parse(dataSaidaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    Saida novaSaida = new Saida(null, dataSaida, precoSaida, usuarioSaida, produtoSaida, clienteSaida);
                    SaidaDaoJDBC saidaDao = (SaidaDaoJDBC) DaoFactory.createSaidaDao();
                    saidaDao.insert(novaSaida);

                    System.out.println("Saída registrada com sucesso!");
                    break;

                case 8:
                    ProdutoDaoJDBC produtoDaoBusca = (ProdutoDaoJDBC) DaoFactory.createProdutoDao();
                    System.out.print("Digite o ID do produto para buscar: ");
                    int idProdutoBusca = sc.nextInt();
                    Produto produtoBusca = produtoDaoBusca.findById(idProdutoBusca);
                    if (produtoBusca != null) {
                        System.out.println(produtoBusca);
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;

                case 9:
                    ClienteDaoJDBC clienteDaoBusca = (ClienteDaoJDBC) DaoFactory.createClienteDao();
                    System.out.print("Digite o nome do cliente para buscar: ");
                    sc.nextLine();
                    String nomeClienteBusca = sc.nextLine();
                    List<Cliente> clientesBusca = clienteDaoBusca.findByName(nomeClienteBusca);
                    if (!clientesBusca.isEmpty()) {
                        System.out.println(clientesBusca.get(0));
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;

                case 10:
                    FornecedorDaoJDBC fornecedorDaoBusca = (FornecedorDaoJDBC) DaoFactory.createFornecedorDao();
                    System.out.print("Digite o nome do fornecedor para buscar: ");
                    sc.nextLine();
                    String nomeFornecedorBusca = sc.nextLine();
                    List<Fornecedor> fornecedoresBusca = fornecedorDaoBusca.findByName(nomeFornecedorBusca);
                    if (!fornecedoresBusca.isEmpty()) {
                        System.out.println(fornecedoresBusca.get(0));
                    } else {
                        System.out.println("Fornecedor não encontrado.");
                    }
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    continuar = false;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            if (continuar) {
                System.out.println("\nDeseja escolher outra opção? (s/n)");
                String resposta = sc.nextLine();
                if (resposta.equalsIgnoreCase("n")) {
                    continuar = false;
                }
            }
        }

        sc.close();
    }
}