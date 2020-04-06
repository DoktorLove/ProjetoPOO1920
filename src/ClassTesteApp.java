import java.util.ArrayList;
import java.util.HashMap;

public class ClassTesteApp {

    public static void main(String args[]){
        Voluntario pedro = new Voluntario("Pedro","1234",new Localizacao(),
                                        true,false,new HashMap<String,Integer>(),23,"non-binary");
        Empresa sonae = new Empresa("Sonae","123",new Localizacao(),
                                    true,true, new HashMap<String,Integer>(),10.0);
        Utilizador rui = new Utilizador("Rui","12",new Localizacao(2.2,3.3),30,"masculino");

        Loja bolama = new Loja("Bolama","12345",new Localizacao(12.2,13.2),
                                        new ArrayList<Utilizador>());
        pedro.addClassificacao("Joel",3);
        pedro.addClassificacao("Manuel",5);
        sonae.addClassificacao("Pedro",1);
        System.out.println(pedro.toString());
        System.out.println(sonae.toString());
        System.out.println(rui.toString());
        System.out.println(bolama.toString());
        pedro.removeClassificacao("Joel");
        System.out.println(pedro.toString() + "A Classificação é: " + pedro.getClassificacao("Manuel"));
    }
}
