import com.sun.javafx.collections.MappingChange;

import java.util.*;
import java.util.stream.Collectors;
import java.io.*;
public class TrazAqui implements Serializable{
    /**
     * Gestao do map de comparadores.
     * A classe TrazAqui guarda os diferentes comparadores que as instancias
     * desta classe podem invocar para ordenar as suas instancias de User.
     * Como é suposto que estes comparadores possam ser utilizados por todos os users(todas as
     * instâncias de TrazAqui) faz sentido guardar numa variável de classe
     */
    private static Map<String,Comparator<User>> comparadores = new HashMap<>();

    public static void juntaOrdenacao(String nome, Comparator<User> ordem)
    {
        comparadores.put(nome,ordem);
    }

    public static Comparator<User> getOrdem(String nome)
    {
        return comparadores.get(nome);
    }

    /**
     * Nome do user
     */
    private String nome;
    /**
     * mapeamento do nome do User para User
     */
    private Map<String,User> users;

    private Map<String, Encomenda> encomendas;

    private List<String> aceites;

    /**
     * Construtor Vazio
     */
    public TrazAqui()
    {
        this.nome = "TrazAqui";
        this.users = new HashMap<String,User>();
        this.encomendas = new HashMap<String,Encomenda>();
        this.aceites = new ArrayList<String>();
    }

    /**
     * Construtor por cópia
     */
    public TrazAqui(TrazAqui c)
    {
        this.nome = c.getNome();
        this.users = c.getUsers();
        this.encomendas = c.getEncomendas();
        this.aceites = c.getAceites();
    }

    /**
     * Construtor por parametros
     */
    public TrazAqui(String nome, Map<String,User> users, Map<String,Encomenda> encomendas, List<String> aceites)
    {
        this.nome = nome;
        //this.users = new HashMap<String,User>();
        setUsers(users);
        setEncomendas(encomendas);
        setAceites(aceites);
    }

    public String getNome()
    {
        return this.nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    private Map<String,User> getUsers()
    {
        return this.users.entrySet()
            .stream()
            .collect(Collectors.toMap(e->e.getKey(), e->e.getValue().clone()));
    }

    private void setUsers(Map<String,User> users)
    {
        this.users = new HashMap<>();
        this.users = users.entrySet()
            .stream()
            .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    private Map<String,Encomenda> getEncomendas()
    {
        return this.encomendas.entrySet()
                .stream()
                .collect(Collectors.toMap(e->e.getKey(), e->e.getValue().clone()));
    }

    private void setEncomendas(Map<String,Encomenda> encomendas)
    {
        this.encomendas = new HashMap<>();
        this.encomendas = encomendas.entrySet()
                .stream()
                .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
    }

    public List<String> getAceites() {
        return new ArrayList<>(this.aceites);
    }

    public void setAceites(List<String> aceites) {
        this.aceites = new ArrayList<>(aceites);
    }

    public boolean existeUser(String nome)
    {
        return this.users.containsKey(nome);
    }

    public boolean corretaPassword(String username, String password){
        return this.users.get(username).getPassword().equals(password);
    }

    public int quantos()
    {
        return this.users.size();
    }

    public User getUser(String nome) throws UserInexistenteException
    {
        User h;
        if(!users.containsKey(nome))
        {
            throw new UserInexistenteException(nome);
        }
        h = users.get(nome).clone();
        return h;
    }

    public static Localizacao criaLocalizacao(double x, double y){
        return new Localizacao(x,y);
    }

    public static Utilizador criaUtilizador(String username, String nome, String password,Localizacao local, int idade, String sexo){
        return new Utilizador(username,nome,password,local,idade,sexo);
    }

    public static Voluntario criaVoluntario(String username, String nome, String password,Localizacao posicao,double raio,
                                     boolean transport, boolean transporte_medico, Map<String,Integer> classificacao, HashMap<String, List<Encomenda>> encomendas, int idade, String sexo){
        return new Voluntario(username,nome,password,posicao,raio,transport,transporte_medico,classificacao,encomendas,idade,sexo);
    }
    public static Empresa criaEmpresa(String username, String nome, String password,Localizacao posicao,double raio,
                                  boolean transport, boolean transporte_medico,
                                  Map<String,Integer> classificacao, HashMap<String, List<Encomenda>> encomendas,
                                  double custo_km, double custo_peso, String nif){
        return new Empresa(username,nome,password,posicao,raio,transport,transporte_medico,classificacao,encomendas,custo_km,custo_peso,nif);
    }

    public static Loja criaLoja(String username, String nome,String password,Localizacao local,HashMap<String,List<Encomenda>> encomendas, HashMap<String,Integer> classificacao,boolean fila){
        if(fila == true){
            return new LojaComFila(username,nome,password,local,encomendas,classificacao,new ArrayList<Utilizador>());
        }
        else{
            return new LojaSemFila(username,nome,password,local,encomendas,classificacao);
        }
    }

    public void adicionaUser(User h)
    {
        this.users.put(h.getUsername(), h.clone());
    }

    public void adicionaEncomenda(Encomenda e){ this.encomendas.put(e.getCodigo(),e.clone());}

    public void adicionaAceites(String s){
        if(!this.aceites.contains(s))
            this.aceites.add(s);
    }

    public List<User> getUsersAsList()
    {
        return users.values()
            .stream()
            .map(User::clone)
            .collect(Collectors.toList());
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.users + "\n")
            .append(this.encomendas + "\n")
            .append(this.aceites + "\n");
        return sb.toString();
    }

    /**
     * Metodo que guarda o estado de uma instancia num ficheiro de texto.
     */
    public void escreveEmFicheiroTxt(String nomeFicheiro) throws FileNotFoundException
    {
        PrintWriter fich = new PrintWriter(nomeFicheiro);
        for(User h: this.users.values())
            fich.println(h.toString());
        fich.flush();
        fich.close();
    }

    /**
     * Metodo que lê um fichero de texto com linhas com imformaçao de User
     */

    public static TrazAqui importaCSV(String nome,String fich) throws FileNotFoundException,IOException
    {
        TrazAqui hi = new TrazAqui();
        hi.setNome(nome);
        String[] linhaPartida;
        List<String> linhas = TrazAqui.lerCSV(fich);
        for(String linha: linhas){
            linhaPartida = linha.split(":",2);
            switch(linhaPartida[0]){
                case "Utilizador":
                    hi.adicionaUser(TrazAqui.csv2Utilizador(linhaPartida[1]));
                    break;
                case "Voluntario":
                    hi.adicionaUser(TrazAqui.csv2Voluntario(linhaPartida[1]));
                    break;
                case "Transportadora":
                    hi.adicionaUser(TrazAqui.csv2Empresa(linhaPartida[1]));
                    break;
                case "Loja":
                    hi.adicionaUser(TrazAqui.csv2Loja(linhaPartida[1]));
                    break;
                case "Encomenda":
                    hi.adicionaEncomenda(TrazAqui.csv2Encomenda(linhaPartida[1]));
                    break;
                case "Aceite":
                    hi.adicionaAceites(linhaPartida[1]);
                    break;
            }
        }

        return hi;
    }

    public void importaCSV(String fich) throws FileNotFoundException,IOException
    {
        this.setNome(nome);
        String[] linhaPartida;
        List<String> linhas = TrazAqui.lerCSV(fich);
        for(String linha: linhas){
            linhaPartida = linha.split(":",2);
            switch(linhaPartida[0]){
                case "Utilizador":
                    this.adicionaUser(TrazAqui.csv2Utilizador(linhaPartida[1]));
                    break;
                case "Voluntario":
                    this.adicionaUser(TrazAqui.csv2Voluntario(linhaPartida[1]));
                    break;
                case "Transportadora":
                    this.adicionaUser(TrazAqui.csv2Empresa(linhaPartida[1]));
                    break;
                case "Loja":
                    this.adicionaUser(TrazAqui.csv2Loja(linhaPartida[1]));
                    break;
                case "Encomenda":
                    this.adicionaEncomenda(TrazAqui.csv2Encomenda(linhaPartida[1]));
                    break;
                case "Aceite":
                    this.adicionaAceites(linhaPartida[1]);
                    break;
            }
        }
    }

    private static List<String> lerCSV(String fich) throws FileNotFoundException,IOException
    {
        List<String> linhas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fich));
        String linha;
        while((linha = br.readLine())!=null)
        {
            linhas.add(linha);
        }
        return linhas;
    }


    private static Utilizador csv2Utilizador(String csv)
    {
        Utilizador u = new Utilizador();
        Random r = new Random();
        Localizacao l = new Localizacao();
        String[] atributos = csv.split(",");
        String username = atributos[0];
        String nome = atributos[1];
        double gpsx = Double.parseDouble(atributos[2]);
        double gpsy = Double.parseDouble(atributos[3]);
        l.setLatitude(gpsx);
        l.setLongitude(gpsy);
        u.setUsername(username);
        u.setNome(nome);
        u.setPosicao(l);
        u.setSexo(u.getRandomSexo());
        u.setIdade(r.nextInt(100));
        return u;
    }

    private static Voluntario csv2Voluntario(String csv)
    {
        Voluntario v = new Voluntario();
        Random r = new Random();
        Localizacao l = new Localizacao();
        String[] atributos = csv.split(",");
        String username = atributos[0];
        String nome = atributos[1];
        double gpsx = Double.parseDouble(atributos[2]);
        double gpsy = Double.parseDouble(atributos[3]);
        double raio = Double.parseDouble(atributos[4]);
        l.setLatitude(gpsx);
        l.setLongitude(gpsy);
        v.setUsername(username);
        v.setNome(nome);
        v.setPosicao(l);
        v.setSexo(v.getRandomSexo());
        v.setIdade(r.nextInt(100));
        v.setTransporteMedico(r.nextBoolean());
        v.setTransporte(r.nextBoolean());
        v.setRaio(raio);
        return v;
    }

    private static Empresa csv2Empresa(String csv)
    {
        Empresa e = new Empresa();
        Random r = new Random();
        Localizacao l = new Localizacao();
        String[] atributos = csv.split(",");
        String username = atributos[0];
        String nome = atributos[1];
        double gpsx = Double.parseDouble(atributos[2]);
        double gpsy = Double.parseDouble(atributos[3]);
        String nif = atributos[4];
        double raio = Double.parseDouble(atributos[5]);
        double custo_km = Double.parseDouble(atributos[6]);
        l.setLatitude(gpsx);
        l.setLongitude(gpsy);
        e.setUsername(username);
        e.setNome(nome);
        e.setPosicao(l);
        e.setNif(nif);
        e.setRaio(raio);
        e.setCusto_Km(custo_km);
        e.setCusto_peso(r.nextInt(5));
        return e;
    }

    private static Loja csv2Loja(String csv)
    {
        Random r = new Random();
        Localizacao l = new Localizacao();
        String[] atributos = csv.split(",");
        String username = atributos[0];
        String nome = atributos[1];
        double gpsx = Double.parseDouble(atributos[2]);
        double gpsy = Double.parseDouble(atributos[3]);
        l.setLatitude(gpsx);
        l.setLongitude(gpsy);
        boolean booleanFila = r.nextBoolean();
        if(booleanFila){
            LojaSemFila ls = new LojaSemFila();
            ls.setUsername(username);
            ls.setNome(nome);
            ls.setPosicao(l);
            return ls;
        }
        else{
            LojaComFila lf = new LojaComFila();
            lf.setUsername(username);
            lf.setNome(nome);
            lf.setPosicao(l);
            return lf;
        }
    }

    private static Encomenda csv2Encomenda(String csv)
    {
        Encomenda e = new Encomenda();
        Random r = new Random();
        String[] atributos = csv.split(",");
        String codigo = atributos[0];
        String usernameU = atributos[1];
        String usernameL = atributos[2];
        double peso = Double.parseDouble(atributos[3]);
        int i = 0;
        for(String s: atributos){
            if(i>3 && i%4==0 && i < atributos.length){
                String[] atributosLE = {"","","",""};
                LinhaEncomenda le = new LinhaEncomenda();
                atributosLE[0] = atributos[i];
                atributosLE[1] = atributos[i+1];
                atributosLE[2] = atributos[i+2];
                atributosLE[3] = atributos[i+3];
                le.setReferencia(atributosLE[0]);
                le.setDescricao(atributosLE[1]);
                le.setQuantidade((int)Double.parseDouble(atributosLE[2]));
                le.setPreco(Double.parseDouble(atributosLE[3]));

                e.adicionaLinha(le);
                i += 4;
            }
            else {
                i += 1;
            }
        }
        e.setUtilizador(usernameU);
        e.setLoja(usernameL);
        e.setMedica(r.nextBoolean());
        e.setPeso(peso);
        e.setCodigo(codigo);
        return e;
    }

    //Método guarda estado
    public void guardaEstado(String nomeFicheiro) throws FileNotFoundException,IOException {
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this); //guarda-se todo o objecto de uma só vez
        oos.flush();
        oos.close();
    }

    //Método carrega estado
    public static TrazAqui carregaEstado(String nomeFicheiro) throws FileNotFoundException,
            IOException,
            ClassNotFoundException {
        FileInputStream fis = new FileInputStream(nomeFicheiro);
        ObjectInputStream ois = new ObjectInputStream(fis);
        TrazAqui h = (TrazAqui) ois.readObject();
        ois.close();
        return h;
    }

}
