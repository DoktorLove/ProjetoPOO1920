import java.util.Date;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.Iterator;
import java.io.*;
public class TrazAqui {
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

/**
 * Construtor Vazio
 */
public TrazAqui()
{
    nome = "TrazAqui";
    users = new HashMap<String,User>();
}

/**
 * Construtor por cópia
 */
public TrazAqui(TrazAqui c)
{
    this.nome = c.getNome();
    this.users = c.getUsers();
}

/**
 * Construtor por parametros
 */
public TrazAqui(String nome, Map<String,User> users)
{
    this.nome = nome;
    this.users = new HashMap<String,User>();
    setUsers(users);
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
    this.users = users.entrySet()
        .stream()
        .collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
}

public boolean existeUser(String nome)
{
    return this.users.containsKey(nome);
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

public void adiciona(User h)
{
    this.users.put(h.getNome(), h.clone());
}

public List<User> getUsersAsList()
{
    return users.values()
        .stream()
        .map(User::clone)
        .collect(Collectors.toList());
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
    List<String> usersCSV = TrazAqui.lerCSV(fich);
    
    usersCSV.forEach(s -> hi.adiciona(TrazAqui.csv2User(s)));
    return hi;
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

private static User csv2User(String csv)
{
    User h = null;
    String[] atributos;
}
}
