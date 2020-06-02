
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class TrazAquiApp{

    private TrazAqui logNegocio;

    private Menu init, difUsers, menuUtil, menuLoja, menuTransportadora, menuEncomenda;

    public static void main(String[] args){
        new TrazAquiApp().run();
    }

    private TrazAquiApp(){
        String[] opcoesInit = {"Log In",
                                "Sign Up"};
        String[] opcoesDifUsers = {"Utilizador Singular",
                                    "Empresa Comercial",
                                    "Empresa Transportadora"};
        String[] opcoesMenuUtil ={"Fazer Encomenda",
                                    "Transportar encomendas",
                                    "Encomendas por entregar",
                                    "Historico de encomendas",
                                    "Procurar Utilizadores"};
        String[] opcoesMenuTransportador ={"Encomendas disponiveis",
                                            "Historico de encomendas",
                                            "Procurar utilizadores"};
        String[] opcoesMenuLoja ={"Encomendas por entregar",
                                    "Encomendas em fila de espera",
                                    "Historico de encomendas",
                                    "Procurar utilizadores"};
        String[] opcoesMenuEncomenda = {"Fazer encomenda a loja",
                                        "Ordenar lojas por mais perto primeiro",
                                        "Ordenar lojas por melhor classificadas",
                                        "Ordenar lojas por ordem alfabética de nome",
                                        "Filtrar lojas disponiveis",
                                        "Filtrar lojas com fila",
                                        "Filtrar lojas sem fila"};
        this.init = new Menu(opcoesInit);
        this.difUsers = new Menu(opcoesDifUsers);
        this.menuUtil = new Menu(opcoesMenuUtil);
        this.menuTransportadora = new Menu(opcoesMenuTransportador);
        this.menuLoja = new Menu(opcoesMenuLoja);
        this.menuEncomenda = new Menu(opcoesMenuEncomenda);
        try {
            this.logNegocio = TrazAqui.carregaEstado("/home/simao/Desktop/Universidade/POO/ProjetoPOO1920/ProjetoPOO1920/estado.obj");
        }
        catch (FileNotFoundException e) {
            System.out.println("Parece que é a primeira utilização, vamos importar informação do ficheiro de logs.");
            try {
                System.out.println("Carregamento de logs bem sucedido");
                this.logNegocio = TrazAqui.importaCSV("TrazAquiApp","/home/simao/Desktop/Universidade/POO/ProjetoPOO1920/ProjetoPOO1920/LogsGerados.csv");
            } catch (IOException ioException) {
                System.out.println("Erro ao importar os Logs");
            }
        }
        catch (IOException e) {
            System.out.println("Ops! Erro de leitura, vamos importar informação do ficheiro de logs.");
            try {
                System.out.println("Carregamento de logs bem sucedido");
                this.logNegocio = TrazAqui.importaCSV("TrazAquiApp","/home/simao/Desktop/Universidade/POO/ProjetoPOO1920/ProjetoPOO1920/LogsGerados.csv");
            } catch (IOException ioException) {
                System.out.println("Erro ao importar os Logs");
            }
        }
        catch (ClassNotFoundException e) {
            System.out.println("Ops! Formato de ficheiro de dados errado, vamos importar informação do ficheiro de logs.");
            try {
                System.out.println("Carregamento de logs bem sucedido");
                this.logNegocio = TrazAqui.importaCSV("TrazAquiApp","/home/simao/Desktop/Universidade/POO/ProjetoPOO1920/ProjetoPOO1920/LogsGerados.csv");
            } catch (IOException ioException) {
                System.out.println("Erro ao importar os Logs");
            }
        }
    }

    private void run(){
        do {
            init.executa();
            switch (init.getOpcao()) {
                case 1: logInData();
                    break;
                case 2: escolheUserType();
                    break;
            }
        } while (init.getOpcao()!=0);
    }

    public void logInData() {
        Scanner scin = new Scanner(System.in);

        System.out.println("Username: ");
        String username = scin.nextLine();
        System.out.println("Password: ");
        String password = scin.nextLine();
        try {
            this.logNegocio.existeUser(username,password);
            menuDeUtil(username);
        }
        catch (UserInexistenteException | IOException | EncomendaInexistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void escolheUserType(){
        do {
            difUsers.executa();
            switch (difUsers.getOpcao()) {
                case 1:
                    signUpDeUtil();
                    break;
                case 2:
                    signUpDeLoja();
                    break;
                case 3:
                    signUpDeEmpresa();
                    break;
            }
        } while (difUsers.getOpcao()!=0);
    }

    public void signUpDeUtil(){
        Scanner scin = new Scanner(System.in);

        System.out.println("Username: ");
        String username = scin.nextLine();
        System.out.println("Nome: ");
        String nome = scin.nextLine();
        System.out.println("Password: ");
        String password = scin.nextLine();
        System.out.println("Idade: ");
        int idade = Integer.parseInt(scin.nextLine());
        System.out.println("Sexo: ");
        String sexo = scin.nextLine();
        try {
            this.logNegocio.existeUsername(username);
            this.logNegocio.adicionaUser(TrazAqui.criaUtilizador(username,nome,password,TrazAqui.criaLocalizacao(0,0),idade,sexo));
            this.logNegocio.guardaEstado("/home/simao/Desktop/Universidade/POO/ProjetoPOO1920/ProjetoPOO1920/estado.obj");
            menuDeUtil(username);
        }
        catch (UserInexistenteException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro objeto não encontrado");
        } catch (IOException e) {
            System.out.println("Erro a guardar no ficheiro objeto");
        } catch (EncomendaInexistenteException e) {
            e.printStackTrace();
        }
    }

    public void signUpDeEmpresa(){
        Scanner scin = new Scanner(System.in);

        System.out.println("Username: ");
        String username = scin.nextLine();
        System.out.println("Nome: ");
        String nome = scin.nextLine();
        System.out.println("Password: ");
        String password = scin.nextLine();
        System.out.println("NIF: ");
        String nif = scin.nextLine();
        System.out.println("Preço por km: ");
        double preco_km = Double.parseDouble(scin.nextLine());
        System.out.println("Preço por kg: ");
        double preco_kg = Double.parseDouble(scin.nextLine());
        System.out.println("Raio de ação: ");
        double raio = Double.parseDouble(scin.nextLine());
        System.out.println("Latitude: ");
        double latitude = Double.parseDouble(scin.nextLine());
        System.out.println("Longitude: ");
        double longitude = Double.parseDouble(scin.nextLine());
        System.out.println("Encontra-se disponivel?[s/n]: ");
        boolean disponivel = s_ou_n(scin.nextLine());
        System.out.println("Faz o transporte de material medicial?[s/n]: ");
        boolean medica = s_ou_n(scin.nextLine());

        try {
            this.logNegocio.existeUsername(username);
            this.logNegocio.adicionaUser(TrazAqui.criaEmpresa(username,nome,password,TrazAqui.criaLocalizacao(latitude,longitude),raio,disponivel,medica,
                                                                new HashMap<>(),new HashMap<>(),preco_km,preco_kg,nif));
            this.logNegocio.guardaEstado("/home/simao/Desktop/Universidade/POO/ProjetoPOO1920/ProjetoPOO1920/estado.obj");
            menuDeUtil(username);
        }
        catch (UserInexistenteException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro objeto não encontrado");
        } catch (IOException e) {
            System.out.println("Erro a guardar no ficheiro objeto");
        } catch (EncomendaInexistenteException e) {
            e.printStackTrace();
        }
    }

    public boolean s_ou_n(String b){
        if(b.equals("s")){
            return true;
        }
        if(b.equals("n")){
            return false;
        }
        return false;
    }

    public void signUpDeLoja(){
        Scanner scin = new Scanner(System.in);

        System.out.println("Username: ");
        String username = scin.nextLine();
        System.out.println("Nome: ");
        String nome = scin.nextLine();
        System.out.println("Password: ");
        String password = scin.nextLine();
        System.out.println("Latitude: ");
        double latitude = Double.parseDouble(scin.nextLine());
        System.out.println("Longitude: ");
        double longitude = Double.parseDouble(scin.nextLine());
        System.out.println("Tem fila de espera?[s/n]: ");
        boolean fila = s_ou_n(scin.nextLine());
        int tam = 0;
        if(fila){
            System.out.println("De que tamanho?: ");
            tam = Integer.parseInt(scin.nextLine());
        }

        try {
            this.logNegocio.existeUsername(username);
            this.logNegocio.adicionaUser(TrazAqui.criaLoja(username,nome,password,TrazAqui.criaLocalizacao(latitude,longitude),new HashMap<>(),new HashMap<>(),fila,tam));
            this.logNegocio.guardaEstado("/home/simao/Desktop/Universidade/POO/ProjetoPOO1920/ProjetoPOO1920/estado.obj");
            menuDeLoja(username);
        }
        catch (UserInexistenteException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro objeto não encontrado");
        } catch (IOException e) {
            System.out.println("Erro a guardar no ficheiro objeto");
        }

    }

    public void menuDeUtil(String username) throws IOException, EncomendaInexistenteException, UserInexistenteException {
        Scanner scin = new Scanner(System.in);
        do {
            menuUtil.executa();
            switch (menuUtil.getOpcao()) {
                case 1:
                    System.out.println("Insira as coordenadas para entrega: ");
                    System.out.println("Latitude: ");
                    double latitude = Double.parseDouble(scin.nextLine());
                    System.out.println("Longitude: ");
                    double longitude = Double.parseDouble(scin.nextLine());
                    fazerEncomenda(latitude,longitude, username);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        } while (menuUtil.getOpcao()!=0);
    }

    public void fazerEncomenda(double latitude, double longitude, String username) throws IOException, UserInexistenteException, EncomendaInexistenteException {
        Scanner scin = new Scanner(System.in);
        System.out.println("\n" + "*** Lojas registadas ***" + "\n");
        for(String ln: this.logNegocio.listOfLojasInfo()){
            System.out.println(ln);
            System.out.println("\n" + "*** ***" + "\n");
        }
        do {
            menuEncomenda.executa();
            switch (menuEncomenda.getOpcao()) {
                case 1:
                    System.out.println("Username da loja: ");
                    String nloja = scin.nextLine();
                    String[][] matrix = new String[50][41];
                    String ex = "s";
                    int ind = 0;
                    while(ex.equals("s")) {
                        System.out.println("Referencia: ");
                        String ref = scin.nextLine();
                        matrix[ind][0] = ref;
                        System.out.println("Descrição do produto: ");
                        String desc = scin.nextLine();
                        matrix[ind][1] = desc;
                        System.out.println("Preço: ");
                        String preco = scin.nextLine();
                        matrix[ind][2] = preco;
                        System.out.println("Quantidade: ");
                        String quant = scin.nextLine();
                        matrix[ind][3] = quant;
                        System.out.println("Deseja juntar outro produto[s/n]");
                        ex = scin.nextLine();
                        ind += 1;
                    }
                    System.out.println("É transporte médico?[s/n]: ");
                    String medica = scin.nextLine();
                    if(medica.equals("s")) {
                        this.logNegocio.adicionaEncomenda(this.logNegocio.criaEncomenda("", username, nloja, "", LocalDateTime.now(), 0.0, matrix, ind, true, false));
                    }
                    else{
                        if(medica.equals("n")) {
                            this.logNegocio.adicionaEncomenda(this.logNegocio.criaEncomenda("", username, nloja, "", LocalDateTime.now(), 0.0, matrix, ind, false, false));
                        }
                        else{
                            System.out.println("A sua escolha é invalida");
                        }
                    }
                    if(this.logNegocio.temFila(nloja)){
                        this.logNegocio.adicionaEncFila(nloja);
                    }
                    else{
                        this.logNegocio.adicionaEncLoja(nloja);
                    }
                    this.logNegocio.adicionaAceites(this.logNegocio.maiorEncomenda());
                    this.logNegocio.guardaEstado("/home/simao/Desktop/Universidade/POO/ProjetoPOO1920/ProjetoPOO1920/estado.obj");
                    System.out.println("A sua encomenda foi realizada, muito obrigado");
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
            }
            System.out.println(this.logNegocio.toString());
        } while (menuEncomenda.getOpcao()!=0);
    }

    public void menuDeLoja(String username){
        do {
            menuUtil.executa();
            switch (menuUtil.getOpcao()) {
                case 1:
                    break;
                case 2:
                    break;
            }
        } while (menuUtil.getOpcao()!=0);
    }

}