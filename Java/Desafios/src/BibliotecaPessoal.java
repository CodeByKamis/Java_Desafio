import java.util.ArrayList; //para chamar a array que é a lista 
import java.util.List; //esse é um tipo de lista que existe 
//diferença: sempre vou precisar o arraylist porq ele é quem faz a lista funcionar, mas o list é um tipo de lista existente, ent nem sempre vou usar ele
import java.util.Scanner; //pedir input
//classe se livro
class Livro {
    //variaveis do livro
    private String titulo;
    private String autor;
    private String isbn;
    private boolean emprestado;
    //reconhecendo as variaveis
    public Livro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.emprestado = false;
    }
    
    public String info_titulo() {
        return titulo; //retorna apens o nome do livro
    }

    public String info_Autor() {
        return autor; //retorna apens o autor do livro
    }

    public String info_Isbn() {
        return isbn; //retorna apens o codigo do livro
    }

    public boolean esta_Emprestado() {
        return emprestado; //se tiver imprestado é true, se nao, é false
    }

    public void emprestar() {
        this.emprestado = true;
    }

    public void devolver() {
        this.emprestado = false;
    }

    public void exibirInfo() {
        String status = emprestado ? "Emprestado" : "Disponível"; //se o status for igual a false, empresta - se for true, está emprestado
        System.out.println("Título: " + titulo + " | Autor: " + autor + " | ISBN: " + isbn + " | Status: " + status);
    } //exibir uma mensagem padrao do livro em questao
}

//classe biblioteca
class Biblioteca {
    List<Livro> livros;

    public Biblioteca() {//lista de livros da biblioteca
        this.livros = new ArrayList<>();
    }
    //funcao de cadastro
    public void cadastrarLivro(String titulo, String autor, String isbn) {
        Livro novoLivro = new Livro(titulo, autor, isbn);
        livros.add(novoLivro);
        System.out.println("Livro cadastrado com sucesso!");
    }
    //funcao de emprestar
    public void emprestarLivro(String isbn) {
        for (Livro livro : livros) {
            if (livro.info_Isbn().equals(isbn)) {
                if (livro.esta_Emprestado()) {
                    System.out.println("O livro já está emprestado."); //se tiver mostra isso
                } else {
                    livro.emprestar();
                    System.out.println("Livro emprestado com sucesso!"); //se nao, mostra isso
                }
                return;
            }
        }
        System.out.println("Livro com ISBN " + isbn + " não encontrado."); //caos o livro nao exista na biblioteca
    }
    //devolucao
    public void devolverLivro(String isbn) {
        for (Livro livro : livros) {
            if (livro.info_Isbn().equals(isbn)) {
                if (!livro.esta_Emprestado()) {//! serve para inverter o valor do boolean, por causa do true/false que coloquei la em cima, se for true, está emprestado, se for false, está disponivel, ent é para inverter, se for false ent true, está livre
                    System.out.println("O " +livro+" livro não está emprestado."); //se o livro nao estiver emprestado
                } else { //se estiver true la, ent aqui é um false, ele foi emprestado e agora vai ser devolvido
                    livro.devolver();
                    System.out.println("Querido usuário, o Livro "+ livro +" foi devolvido S2"); //ele devolve e depois consta como false, ou seja, nao emprestado, está disponivel
                }
                return;//finalizando a funcao
            }
        }
        System.out.println("Livro com ISBN " + isbn + " não encontrado."); //tratativa de erro
    }

    public void listarLivros(boolean mostrarEmprestados) {
        for (Livro livro : livros) { //mostra o livro com bse no seu status
            if (livro.esta_Emprestado() == mostrarEmprestados) {
                livro.exibirInfo(); //exibir aquelas informações padroes do livro
            }
        }
    }
    //procurando o livro
    public void buscarLivro(String termo) {
        termo = termo.toLowerCase();
        boolean encontrado = false;
        for (Livro livro : livros) {
            if (livro.info_titulo().toLowerCase().contains(termo) || livro.info_Autor().toLowerCase().contains(termo)) {
                livro.exibirInfo();
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("infelizmente n consegui achar nenhum livro desse tipo usuário");
        }
    }
}
// agora vamos iniciar a parte com a interação com o usuário
public class BibliotecaPessoal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //psra poder dar input
        Biblioteca biblioteca = new Biblioteca(); //chamando a classe
        int opcao; // tipando a opcao para receber apenas numeors

        do {
            System.out.println("_______________S2_BEM-VINDO(A)_A_KAMIS_LABRARY_S2_________________");
            System.out.println("0. Sair");
            System.out.println("1. Cadastrar um novo livro");
            System.out.println("2. Pegar um livro emprestado");
            System.out.println("3. Devolver um Livro");
            System.out.println("4. Ver todos os livros que estão disponíveis");
            System.out.println("5. Ver todos os livros que estão indisponiveis");
            System.out.println("6. Fazer busca por titulo ou por autor");
            System.out.print("Escolha uma opção (0 - 6): ");
            opcao = scanner.nextInt(); //vai receber o numero
            scanner.nextLine();

            switch (opcao) { //switch é melhor para nao ficar cheio de if, else if, else
                case 0: //se for zero o while para
                    System.out.println("BYE BYE KAMIS LABRARY S2");
                    break; //fim do case
                case 1:
                    //adicionando as info
                    System.out.print("Título do livro: "); //adiciona o tiutlo
                    String titulo = scanner.nextLine();
                    System.out.print("Autor do livrp: ");
                    String autor = scanner.nextLine();
                    System.out.print("ISBN do livro: ");
                    String isbn = scanner.nextLine();
                    biblioteca.cadastrarLivro(titulo, autor, isbn); //chamando a funcao cadastrarlivro e guardando nela as informacoes
                    break; //fim do case
                case 2:
                    System.out.print("Digite o isbn do livro que você quer pegar:");
                    String isbnEmprestar = scanner.nextLine();//guardando na variavel
                    biblioteca.emprestarLivro(isbnEmprestar); //chamando a funcao para emprestar o livro usando a isbn como base
                    break; //fim do case
                case 3:
                    System.out.print("Digite o isbn do livro que você quer devolver:"); //devolução de livro, vai procurar o livro por codigo
                    String isbnDevolver = scanner.nextLine(); //guarda na variavel
                    biblioteca.devolverLivro(isbnDevolver); //chama a funcao para devolver
                    break; //fim do case
                case 4:
                    System.out.println("Veja os livros disponíveis:");
                    biblioteca.listarLivros(false); //se o emprestado é false, o livro ta disponivel
                    break; //fim do case
                case 5:
                    System.out.println("Veja os livros indisponíveis:");
                    biblioteca.listarLivros(true); //se o emprestado é true, é porque está realmente emprestado
                    break; //fim do case
                case 6:
                    System.out.print("Escreva o nome do titulo ou o autor do livro que deseja:");
                    String termo = scanner.nextLine();
                    biblioteca.buscarLivro(termo);
                    break; //fim do case
                default:
                    System.out.println("Opção inválida."); //caso o usuário coloque outro numero diferente de 0 - 6
                    break; //fim do case
            }

        } while (opcao != 0); //so para o loop quando o opcao for igual a zero, caso contrario continua

        scanner.close(); //fecha por boa pratica, pois isso coonsome memoria do pc, então usamos apenas essa memoria enquanto o sistema estiver em uso, terminou, parou ce usar a memoria do pc
    }
}