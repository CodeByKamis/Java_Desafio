import java.util.ArrayList; //para chamar a array que é a lista 
import java.util.List; //esse é um tipo de lista que existe 
//diferença: sempre vou precisar o arraylist porq ele é quem faz a lista funcionar, mas o list é um tipo de lista existente, ent nem sempre vou usar ele
import java.util.Scanner; //pedir input
// a classe de produto
class Produto {
    // definindo as variaveis do produto
    String nome;
    String tipo;
    double preco;
    // tipando eles, reconhendo no obj
    public Produto(String nome, String tipo, double preco) {
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
    }
    // quando for chamado, ele vai exibir as info do produto nesse formato
    public void exibirInfo() {
        System.out.println("Produto: " + nome);
        System.out.println("Tipo: " + tipo);
        System.out.println("Preço: R$" + preco);
        System.out.println("______________S2_mercadinhoDoBom_S2________________");
    }
    // método de desconto - precisa dar desconto primeiro para depois comprar
    public void aplicarDesconto(double percentual) {
        preco = preco - (preco * percentual / 100);
    }
}
// classe do cliente
class Cliente {
    // tipando e definidno as informações do cliente
    String email;
    String nomeCliente;
    // obj cliente
    public Cliente(String email, String nomeCliente) {
        this.email = email;
        this.nomeCliente = nomeCliente;
    }
    // quando chamado, vai mostrar as informações do cliente desse jeito
    public void mostrarDados() {
        System.out.println("Cliente: " + nomeCliente);
        System.out.println("Email: " + email);
    }
}
// classe do caixa do mercadinho
class Caixa {
    // tipando e definindo as variaveis do caixa
    Produto produto;
    int quantidade;
    double precoUnitarioFinal;
    // obj caixa chamando as variaveis e as reconhendo - herdou produto aqui com as informações
    public Caixa(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitarioFinal = produto.preco; // mostra o valor com/sem desconto
    }
    // calculo do preço total da compra, multiplicanod o preco unitario pela quantidade de unidades
    public double calcularPreco() {
        return precoUnitarioFinal * quantidade;
    }
    // é como a tela do pc no mercado, nome do produto, quantidade de unidades vezes o preço que é igual ao preço final
    public void mostrarItem() {
        System.out.println("Produto: " + produto.nome + " - R$" + precoUnitarioFinal + " x " + quantidade + " = R$" + calcularPreco());
    }
}
// classe do carrinho de comprar que é uma lista, pois a variavel ela só substitui ou soma, 
// mas é necessário mostrar TODOS os produtos que são pegos em suas devidas quantidades
class Carrinho {
    List<Caixa> itens = new ArrayList<>(); //chamando os produtos de itens (lista)
    // herdou o produto da class produto as informações e quantidade do caixa
    public void adicionarProduto(Produto produto, int quantidade) {
        itens.add(new Caixa(produto, quantidade)); //adicionar no caixa 
    }
    //função para remover produto do carrinho
    public void removerProduto(String produtoNomezin) {
        //loop para percorrer todos os itens da lista que estão no carrinho
        for (int i = 0; i < itens.size(); i++) {
            // verificando se existe na lista um produto com o mesmo nome dentro da lista, se sim, ele vai ser removido se não ele manda a mensagem de erro
            if (itens.get(i).produto.nome.equalsIgnoreCase(produtoNomezin))
            {
                itens.remove(i);
                System.out.println("Produto: " + produtoNomezin + " foi removido do carrinho de compras ;D");
                return; //ele evita que ocorra um loop, pois termina o loop e sai da função de removerproduto
            }
        }
        System.out.println("O produto não foi encontrado no carrinho");
    }
    //função para exibir o total da compra
    public double totalCompra() {
        double total = 0;
        for (Caixa caixa : itens) {
            total += caixa.calcularPreco();
        }
        return total; //retornando o total
    }
    //aqui nós conseguimos chamar a função de cima que calcula o total da compra em mostrarresumo que vai exibir o valor total ao usuário
    public void mostrarResumo() {
        for (Caixa caixa : itens) {
            caixa.mostrarItem();
        }
        System.out.println("Total da compra: R$" + totalCompra());
        System.out.println("--------S2_mercadinhoDoBom_S2----------");
    }

    //fiz as duas ultimas separadas mesmo podendo fazer elas juntas para seguir o principio que aprendemos em QA - single responsability principle - uma responsabilidade para cada um
    //facilita a manutenção de codigo e também melhora a capacidade de expandir o codigo sem ter que refatorar.
}
// iniciando interação com as classes e funções criadas
public class CriarProduto {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //para poder pedir input

        // cadastro de cliente
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email do cliente: ");
        String email = scanner.nextLine();
        Cliente cliente = new Cliente(email, nome);
        cliente.mostrarDados();

        // cadastro do produto (sim eu fiz manual)
        Produto prod1 = new Produto("maça", "Fruta", 1.50);
        Produto prod2 = new Produto("banana", "Fruta", 2.99);
        Produto prod3 = new Produto("água", "Bebida", 1.75);

        Carrinho carrinho = new Carrinho(); //chamando a o carrinho

        int opcao;
        do { //faça isso enquanto a opção for diferente de zero, pois se for zero, o loop para
            System.out.println("_____S2_mercadinhoDoBom_S2_____\n");
            System.out.println("0 - Sair");
            System.out.println("1 - Adicionar produto");
            System.out.println("2 - Remover produto");
            System.out.println("3 - Mostrar preço de carrinho");
            System.out.println("4 - Aplicar desconto em um produto");
            System.out.println("5 - Mostrar preço final");
            System.out.print("Escolha uma opção (apenas números): ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer
            //preferi o switch case para não ficar com muitos if/else e else if
            switch (opcao) {
                case 0: //se for zero a pessoa sai
                    System.out.println("BYE BYE");
                    break;

                case 1: //caso for add produto, fica aqui
                    System.out.println("Escolha o produto (apenas o número por favor):");
                    System.out.println("1 - maça");
                    System.out.println("2 - banana");
                    System.out.println("3 - água");
                    int escolha = scanner.nextInt(); //tipando o tipo do input (numero)
                    scanner.nextLine();
                    Produto escolhido = null; //variavel q armazena o numero escolhido, de inicio ela é nula
                    switch (escolha) { //dependenod do que for escolhido ele seleciona determinado produto
                        case 1: escolhido = prod1; break;
                        case 2: escolhido = prod2; break;
                        case 3: escolhido = prod3; break;
                        default: //se não estiver entre o 3 casos disponíveis, é nada, ou seja, produto inválido.
                            System.out.println("Produto inválido.");
                            continue;//volta para o loop das opções iniciais
                    }
                    System.out.print("Quantidade: "); //pede a quantidade do produto desejado entre os que estão cadastrados
                    int quantidade = scanner.nextInt(); //armazenando
                    carrinho.adicionarProduto(escolhido, quantidade); // adicionado ao carrinho
                    System.out.println("Esse produto foi adicionado com sucesso :)"); //mensagem para o usuario
                    break;//case um finalizado e volta para o loop

                case 2:
                    System.out.print("Digite o nome do produto para remover: "); //digita o nome do produto
                    String remover = scanner.nextLine(); // guarda o input na função
                    carrinho.removerProduto(remover);//chama a função
                    break;//case um finalizado e volta para o loop

                case 3:
                    carrinho.mostrarResumo(); //chama a função de mostrar o total do carrinho
                    break;//case um finalizado e volta para o loop

                case 4:
                    System.out.print("Qual produto deseja aplicar desconto? (maça, banana, água): ");
                    String nomeDesconto = scanner.nextLine();
                    Produto Prodatual = null; // ele inicia nulo
                    //nao foi usado switch aqui devido o equalsignorecase, pois se fosse switch as variaçoes de maiusculo, minusculo e acentuação teria que fazer manualmente
                    if (nomeDesconto.equalsIgnoreCase("maça")) Prodatual = prod1;
                    else if (nomeDesconto.equalsIgnoreCase("banana")) Prodatual = prod2;
                    else if (nomeDesconto.equalsIgnoreCase("água")) Prodatual = prod3;

                    if (Prodatual != null) { //se for diferente de nulo
                        System.out.print("Digite o percentual de desconto (ex: 2 para ter 2%): ");
                        double perc = scanner.nextDouble(); //guarda aqui o numero digitado
                        scanner.nextLine();
                        Prodatual.aplicarDesconto(perc);//chama a funçao de desconto para guardar desconto no produto atual
                        System.out.println("Desconto aplicado com sucesso!"); //exibir para usuario
                    } else { // se for nulo
                        System.out.println("Produto não encontrado.");
                    }
                    break; //fim co case 4

                case 5:
                    carrinho.mostrarResumo(); //chama a função de mostrar o valor total da compra 
                    break;//fim do cade 5

                default:
                    System.out.println("Inválido. tente de novo :)"); //caso a opção escolhida nao exista 
            }
        } while (opcao != 0); //continua enquanto a opção escolhida for diferente de zero

        scanner.close(); //fechar o scanner para ent liberar memoria, pois ele usa espaço do pc - entrada de dados e buffers de memoria, ent é boa pratica fechar
    }
}
