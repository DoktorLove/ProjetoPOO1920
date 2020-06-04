
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class TrazAquiApp{

    private TrazAqui logNegocio;

    private Menu init, difUsers, menuUtil, menuLoja, menuTransportadora, menuEncomenda,
            menuEncomendaPorEntregar, menuHistoricoEncomendas, menuEstatisticas;

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
                                    "Estatisticas"};
        String[] opcoesMenuTransportador ={"Encomendas disponiveis",
                                            "Confirmar entrega de encomenda",
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
        String[] opcoesEncomendasPorEntregar = {"Aceitar transporte de encomenda",
                                                "Ordenar por distancia a percorrer",
                                                "Ordenar por tempo de pedido",
                                                "Filtrar encomenda médica"};
        String[] opcoesMenuHistorico = {"Ordenar encomendas por data de entrega",
                                        "Ordenar encomendas por peso",
                                        "Ordenar encomendas por preço",
                                        "Filtrar encomendas por loja",
                                        "Filtrar encomendas por transportador",
                                        "Filtrar encomendas medicinais",
                                        "Filtrar encomendas não entregues",
                                        "Encomendas feitas hoje",
                                        "Encomendas feitas esta semana",
                                        "Encomendas feitas este mês"};
        String[] opcoesEstatisticas = {"Top 10 utilizadores da App(mais encomendas feitas)",
                                        "Top 10 voluntarios com mais kms",
                                        "Top 10 empresas com mais kms"};
        this.init = new Menu(opcoesInit);
        this.difUsers = new Menu(opcoesDifUsers);
        this.menuUtil = new Menu(opcoesMenuUtil);
        this.menuTransportadora = new Menu(opcoesMenuTransportador);
        this.menuLoja = new Menu(opcoesMenuLoja);
        this.menuEncomenda = new Menu(opcoesMenuEncomenda);
        this.menuEncomendaPorEntregar = new Menu(opcoesEncomendasPorEntregar);
        this.menuHistoricoEncomendas = new Menu(opcoesMenuHistorico);
        this.menuEstatisticas = new Menu(opcoesEstatisticas);
        try {
            this.logNegocio = TrazAqui.carregaEstado("/home/simao/Desktop/Universidade/POO/ProjetoPOO1920/ProjetoPOO1920/estado.obj");
            //System.out.println(this.logNegocio.toString());
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
            if(username.charAt(0) == 'u') {
                menuDeUtil(username);
            }
            if(username.charAt(0) == 'v' || username.charAt(0) == 't') {
                menuDeTransportador(username);
            }
            if(username.charAt(0) == 'l') {
                menuDeLoja(username);
            }
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
                    System.out.println("Quer fazer transporte médico?[true/false]: ");
                    Boolean medico =Boolean.parseBoolean(scin.nextLine());
                    System.out.println("Latitude: ");
                    double latitude1 = Double.parseDouble(scin.nextLine());
                    System.out.println("Longitude: ");
                    double longitude1 = Double.parseDouble(scin.nextLine());
                    System.out.println("Raio de ação: ");
                    double raio = Double.parseDouble(scin.nextLine());
                    String vusername = this.logNegocio.user2Vol(username, medico, raio,latitude1,longitude1);
                    menuDeTransportador(vusername);
                    break;
                case 3:
                    //Encomendas por entregar(ver pedidos de transporte)
                    break;
                case 4:
                    //historico de encomendas
                    break;
                case 5:
                    //estatisticas
                    break;
            }
        } while (menuUtil.getOpcao()!=0);
    }

    public void menuDeTransportador(String username) throws EncomendaInexistenteException {
        Scanner scin = new Scanner(System.in);
        do {
            menuTransportadora.executa();
            switch (menuTransportadora.getOpcao()) {
                case 1:
                    encomendasDisponiveis(username);
                    break;
                case 2:
                    System.out.println("Insira o código da encomenda: ");
                    String cod = scin.nextLine();
                    System.out.println(this.logNegocio.getEncomendaString(cod));
                    System.out.println("Deseja confirmar a entraga?[true/false]: ");
                    boolean b = Boolean.parseBoolean(scin.nextLine());
                    if(b){
                        this.logNegocio.confirmarEntEncomenda(cod,username);
                    }
                    break;
                case 3:
                    //historico de encomendas
                    break;
                case 4:
                    //procurar utilizadores
                    break;
            }
        } while (menuTransportadora.getOpcao()!=0);
    }

    public void encomendasDisponiveis(String username){
        Scanner scin = new Scanner(System.in);
        do {
            StringBuilder encomendas = new StringBuilder("*** Encomendas disponiveis ***\n");
            for(String ln: this.logNegocio.listOfEncomendasInfo(this.logNegocio.listOfEncomendas(username))){
                encomendas.append("\n").append(ln).append("\n").append("*** ***").append("\n");
            }
            System.out.println(encomendas);
            menuEncomendaPorEntregar.executa();
            switch (menuEncomendaPorEntregar.getOpcao()) {
                case 1:
                    System.out.println("Insira o código da encomenda: ");
                    String cod = scin.nextLine();
                    this.logNegocio.realizaEncomenda(cod,username);
                    break;
                case 2:
                    //ordenar por distancia
                    break;
                case 3:
                    // ordenar por tempo do pedido
                    break;
                case 4:
                    //filtrar encomendas medicas
                    break;
            }
        } while (menuEncomendaPorEntregar.getOpcao()!=0);
        System.out.println(this.logNegocio.toString());
    }

    public void fazerEncomenda(double latitude, double longitude, String username) throws IOException, UserInexistenteException, EncomendaInexistenteException {
        Scanner scin = new Scanner(System.in);
        StringBuilder lojas = new StringBuilder("*** Lojas registadas ***\n");
        for(String ln: this.logNegocio.listOfLojasInfo(this.logNegocio.listOfLojas())){
            lojas.append("\n").append(ln).append("\n").append("*** ***").append("\n");
        }
        do {
            System.out.println(lojas);
            menuEncomenda.executa();
            switch (menuEncomenda.getOpcao()) {
                case 1:
                    System.out.println("Username da loja: ");
                    String nloja = scin.nextLine();
                    if(this.logNegocio.contemLoja(nloja)){
                        String[][] matrix = new String[50][4];
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
                            this.logNegocio.adicionaEncomenda(this.logNegocio.criaEncomenda(this.logNegocio.geraCodEncomenda(), username, nloja, "", LocalDateTime.now(), 0.0, matrix, ind, true, false));
                        }
                        else{
                            if(medica.equals("n")) {
                                this.logNegocio.adicionaEncomenda(this.logNegocio.criaEncomenda(this.logNegocio.geraCodEncomenda(), username, nloja, "", LocalDateTime.now(), 0.0, matrix, ind, false, false));
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
                    }
                    else{
                        throw new UserInexistenteException("Esta Loja não existe");
                    }
                    break;
                case 2:
                    lojas = new StringBuilder("\n*** Lojas registadas ***\n");
                    for(String ln: this.logNegocio.listOfLojasInfo(this.logNegocio.ordenaLojasDistancia(latitude,longitude))){
                        lojas.append("\n").append(ln).append("\n").append("*** ***").append("\n");
                    }
                    break;
                case 3:
                    lojas = new StringBuilder("\n*** Lojas registadas ***\n");
                    for(String ln: this.logNegocio.listOfLojasInfo(this.logNegocio.ordenaLojasClass())){
                        lojas.append("\n").append(ln).append("\n").append("*** ***").append("\n");
                    }
                    break;
                case 4:
                    lojas = new StringBuilder("\n*** Lojas registadas ***\n");
                    for(String ln: this.logNegocio.listOfLojasInfo(this.logNegocio.ordenaLojasNome())) {
                        lojas.append("\n").append(ln).append("\n").append("*** ***").append("\n");
                    }
                    break;
                case 5:
                    break;
                case 6:
                    lojas = new StringBuilder("\n*** Lojas registadas ***\n");
                    for(String ln: this.logNegocio.listOfLojasInfo(this.logNegocio.listOfLojasComFila())){
                        lojas.append("\n").append(ln).append("\n").append("*** ***").append("\n");
                    }
                    break;
                case 7:
                    lojas = new StringBuilder("\n*** Lojas registadas ***\n");
                    for(String ln: this.logNegocio.listOfLojasInfo(this.logNegocio.listOfLojasSemFila())){
                        lojas.append("\n").append(ln).append("\n").append("*** ***").append("\n");
                    }
                    break;
            }
            //System.out.println(this.logNegocio.toString());
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