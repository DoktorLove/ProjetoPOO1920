import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Pair;

import javax.print.DocFlavor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Para criar Botão no menu: {"Button","texto","largura","altura","posiçao linha","posiçao coluna"}
 * Para criar Label no menu: {"Label","texto","posiçao linha","posiçao coluna"}
 * Para criar Caixa de texto: {"TextField","texto","posiçao linha","posiçao coluna"}
 * Para criar Check box: {"CheckBox","texto","posiçao linha","posiçao coluna"}
 * Para criar Botão de return:{"ButtomRet",(mesmos campos que Botão),"diretoria da imagem"}
 * O Botão return, deve ser sempre o ultimo a ser colucado na matriz
 */

public class TrazAquiApp extends Application{

    String[][] opcoesInit  = {
            {"Button","Log In","150","100","7","0"},
            {"Button","Sign In","150","100","7","1"},
            {"ButtonRet","","0","32"}
    };
    String[][] opcoesLogIn  = {
            {"Label","Username","0","0"},
            {"TextField","Username","1","0"},
            {"Label","Password","0","2"},
            {"TextField","Password","1","2"},
            {"Button","Log In","150","100","1","3"},
            {"ButtonRet","","0","29"}
    };
    String[][] opcoesDifUsers  = {
            {"Button","User Singular","150","100","5","0"},
            {"Button","User Comercio","150","100","5","1"},
            {"Button","User Empresa","150","100","5","2"},
            {"ButtonRet","","0","29"}
    };
    String[][] opcoesSignIn  = {
            {"Label","Username","0","0"},
            {"TextField","Username","1","0"},
            {"Label","Email","0","1"},
            {"TextField","Email","1","1"},
            {"Label","Password","0","2"},
            {"TextField","Password","1","2"},
            {"Label","Idade","0","3"},
            {"TextField","Idade","1","3"},
            {"CheckBox","Masculino","0","4"},
            {"CheckBox","Feminino","1","4"},
            {"Button","Sign In","150","100","1","5"},
            {"ButtonRet","","0","20"}
    };

    String[][] opcoesSignInEmpresa  = {
            {"Label","Username","0","0"},
            {"TextField","Username","1","0"},
            {"Label","Email","0","1"},
            {"TextField","Email","1","1"},
            {"Label","Password","0","2"},
            {"TextField","Password","1","2"},
            {"Label","Taxa por km","0","3"},
            {"TextField","km","1","3"},
            {"Label","Taxa por kg","0","4"},
            {"TextField","kg","1","4"},
            {"Button","Sign In","150","100","1","5"},
            {"ButtonRet","","0","20"}
    };

    String[][] opcoesSignInLoja  = {
            {"Label","Username","0","0"},
            {"TextField","Username","1","0"},
            {"Label","Email","0","1"},
            {"TextField","Email","1","1"},
            {"Label","Password","0","2"},
            {"TextField","Password","1","2"},
            {"CheckBox","Fila de espera?","0","3"},
            {"Label","De que tamanho?","0","4"},
            {"TextField","tamanho","1","4"},
            {"Button","Sign In","150","100","1","5"},
            {"ButtonRet","","0","20"}
    };

    String[][] opcoesMenuUtilizadorTop = {
            {"Label","Ordenar: ","0","0"},
            {"Button","Mais populares","150","100","1","5"},
            {"Button","Classificação","150","100","1","5"},
            {"Button","Tempo de entrega","150","100","1","5"},
    };

    String[][] opcoesMenuUtilizadorCenter = {
            {"Label","Ordenar: ","0","0"},
            {"Label","Ordenar: ","0","0"},
            {"Label","Ordenar: ","0","0"},
            {"Label","Ordenar: ","0","0"},
            {"Label","Ordenar: ","0","0"},
    };

    String[][] opcoesMenuUtilizadorBottom = {
            {"Button","Inicio","150","100","1","5"},
            {"Button","Procurar","150","100","1","5"},
            {"Button","Pedidos","150","100","1","5"},
            {"Button","Perfil","150","100","1","5"},
            {"ButtonRet","","0","20"},
    };


    private TrazAqui logNegocio;

    private final Menu menuInit = new Menu(opcoesInit);
    private final Menu menuLogIn = new Menu(opcoesLogIn);
    private final Menu menuSignIn = new Menu(opcoesSignIn);
    private final Menu menuDifUsers = new Menu(opcoesDifUsers);
    private final Menu menuSignInLoja = new Menu(opcoesSignInLoja);
    private final Menu menuSignInEmpresa = new Menu(opcoesSignInEmpresa);
    private final Menu menuUtilizadorTop = new Menu(opcoesMenuUtilizadorTop);
    private final Menu menuUtilizadorBottom = new Menu(opcoesMenuUtilizadorBottom);
    private final Menu menuUtilizadorCenter = new Menu(opcoesMenuUtilizadorCenter);

    public static void main(String args[]){
        launch(args);
    }

    /**

     try{
        this.logNegocio = TrazAqui.carregaEstado();
    }
    catch (FileNotFoundException e) {
        System.out.println("Parece que é a primeira utilização...");
        this.logNegocio = new TrazAqui();
    }
    catch (IOException e) {
        System.out.println("Ops! Erro de leitura!");
        this.logNegocio = new TrazAqui();
    }
    catch (ClassNotFoundException e) {
        System.out.println("Ops! Formato de ficheiro de dados errado!");
        this.logNegocio = new TrazAqui();
    }
     */


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("TrazAquiApp");

        run(stage);

        stage.show();
    }
    /**
    private void run2(Stage stage) throws Exception{
        ScrollPane sp = new ScrollPane();
        VBox box = new VBox();
        VBox vCaixa = new VBox();
        Scene cena = new Scene(box,300,300);
        stage.setScene(cena);
        box.getChildren().add(sp);
        TrazAqui ta = TrazAqui.importaCSV("TrazAquiApp","/home/simao/Desktop/Universidade/POO/ProjetoPOO1920/ProjetoPOO1920/LogsGerados.csv");
        String s = ta.toString();
        System.out.println(s);
        Label l = new Label(s);
        vCaixa.getChildren().add(l);
        sp.setContent(vCaixa);

    }
     */

    private void run(Stage stage) throws Exception {
        Path path = Files.createTempFile("estado", ".obj");
        if(Files.notExists(path)) {
            //System.out.println("OLa");
            this.logNegocio = TrazAqui.importaCSV("TrazAquiApp", "/home/simao/Desktop/Universidade/POO/ProjetoPOO1920/ProjetoPOO1920/LogsGerados.csv");
        }
        else{
            //System.out.println("Ola");
            try{
                this.logNegocio = TrazAqui.carregaEstado("/home/simao/Desktop/Universidade/POO/ProjetoPOO1920/ProjetoPOO1920/estado.obj");
                this.logNegocio.importaCSV("/home/simao/Desktop/Universidade/POO/ProjetoPOO1920/ProjetoPOO1920/LogsGerados.csv");
                //System.out.println(this.logNegocio.toString());
            }
            catch (FileNotFoundException e) {
                System.out.println("Parece que é a primeira utilização...");
                this.logNegocio = new TrazAqui();
            }
            catch (IOException e) {
                System.out.println("Ops! Erro de leitura!");
                this.logNegocio = new TrazAqui();
            }
            catch (ClassNotFoundException e) {
                System.out.println("Ops! Formato de ficheiro de dados errado!");
                this.logNegocio = new TrazAqui();
            }
        }
        Pair<Scene,List<Control>> p = this.menuInit.executeFormatGrid();
        Scene init = p.getKey();
        stage.setScene(init);
        List<Control> l = p.getValue();

        l.get(0).setOnMousePressed(e->{
            try {
                logIn(stage,init);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        l.get(1).setOnMousePressed(e->{
            try {
                DifUsers(stage,init);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        l.get(l.size()-1).setOnMousePressed(e->{
            try {
                this.logNegocio.guardaEstado("/home/simao/Desktop/Universidade/POO/ProjetoPOO1920/ProjetoPOO1920/estado.obj");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                this.logNegocio.escreveEmFicheiroTxt("/home/simao/Desktop/Universidade/POO/ProjetoPOO1920/ProjetoPOO1920/logsTeste.txt");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            stage.close();
        });

    }

    public void logIn(Stage stage, Scene scene) throws Exception {
        Pair<Scene,List<Control>> p = this.menuLogIn.executeFormatGrid();
        Scene logIn = p.getKey();
        stage.setScene(logIn);
        List<Control> l = p.getValue();

        l.get(l.size()-2).setOnMousePressed(e->{
            if(logInVerifica(l)){
                try {
                    menuUilizador(stage,logIn);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else{
                stage.setScene(logIn);
            }
        });

        l.get(l.size()-1).setOnMousePressed(e->{
            stage.setScene(scene);
        });
        
        
    }

    public void DifUsers(Stage stage,Scene scene) throws Exception {
        Pair<Scene,List<Control>> p = this.menuDifUsers.executeFormatGrid();
        Scene DifUsers = p.getKey();
        stage.setScene(DifUsers);
        List<Control> l = p.getValue();


        l.get(0).setOnMousePressed(e->{
            try {
                signIn(stage,DifUsers);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        l.get(1).setOnMousePressed(e->{
            try {
                signInLoja(stage,DifUsers);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        l.get(2).setOnMousePressed(e->{
            try {
                signInEmpresa(stage,DifUsers);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        l.get(l.size()-1).setOnMousePressed(e->{
            stage.setScene(scene);
        });

    }

    public void signIn(Stage stage, Scene scene) throws Exception {
        Pair<Scene,List<Control>> p = this.menuSignIn.executeFormatGrid();
        Scene signIn = p.getKey();
        stage.setScene(signIn);
        List<Control> l = p.getValue();

        l.get(l.size()-2).setOnMousePressed(e-> {
            try {
                signInUtilizador(l);
                menuUilizador(stage,signIn);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        l.get(l.size()-1).setOnMousePressed(e->{
            stage.setScene(scene);
        });
    }

    public void signInLoja(Stage stage, Scene scene) throws Exception {
        Pair<Scene,List<Control>> p = this.menuSignInLoja.executeFormatGrid();
        Scene signInLoja = p.getKey();
        stage.setScene(signInLoja);
        List<Control> l = p.getValue();

        if((l.get(7).isVisible())){
            l.get(7).setVisible(false);
            l.get(8).setVisible(false);
        }

        l.get(6).setOnMousePressed(e->{
            if((!l.get(7).isVisible())){
                l.get(7).setVisible(true);
                l.get(8).setVisible(true);
            }
            else {
                l.get(7).setVisible(false);
                l.get(8).setVisible(false);
            }
        });

        l.get(l.size()-1).setOnMousePressed(e->{
            stage.setScene(scene);
        });
    }

    public void signInEmpresa(Stage stage, Scene scene) throws Exception {
        Pair<Scene,List<Control>> p = this.menuSignInEmpresa.executeFormatGrid();
        Scene signInEmpresa = p.getKey();
        stage.setScene(signInEmpresa);
        List<Control> l = p.getValue();

        l.get(l.size()-1).setOnMousePressed(e->{
            stage.setScene(scene);
        });
    }

    public void menuUilizador(Stage stage, Scene scene) throws Exception {
        Pair<HBox,List<Control>> p1 = this.menuUtilizadorTop.newHBox();
        Pair<VBox,List<Control>> p2 = this.menuUtilizadorCenter.newVBoxScroll();
        Pair<HBox,List<Control>> p3 = this.menuUtilizadorBottom.newHBox();
        Scene border = Menu.executeFormatBorder(p1.getKey(),p2.getKey(),p3.getKey());
        stage.setScene(border);
        List<Control> l = p3.getValue();
        l.get(l.size()-1).setOnMousePressed(e->{
            try {
                this.logNegocio.guardaEstado("/home/simao/Desktop/Universidade/POO/ProjetoPOO1920/ProjetoPOO1920/estado.obj");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            stage.close();
        });
    }

    private boolean logInVerifica(List<Control> l){
        String username = ((TextField)l.get(1)).getText();
        String password = ((TextField)l.get(3)).getText();
        if(logNegocio.existeUser(username)){
            if((logNegocio.corretaPassword(username, password))){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    private void signInUtilizador(List<Control> l) throws Exception {
        String username = ((TextField)l.get(1)).getText();
        String nome = ((TextField)l.get(3)).getText();
        String password = ((TextField)l.get(5)).getText();
        int idade = Integer.parseInt(((TextField)l.get(7)).getText());
        String sexo = "Apashe";
        if((((CheckBox) l.get(8)).isSelected())){
            sexo = "Masculino";
        }
        if((((CheckBox) l.get(9)).isSelected())){
            sexo = "Feminino";
        }
        logNegocio.adicionaUser(TrazAqui.criaUtilizador(username,nome,password, TrazAqui.criaLocalizacao(0.0,0.0),idade,sexo));
        this.logNegocio.guardaEstado("estado.obj");
    }

    /**
     *
     *
     *         logNegocio = TrazAqui.importaCSV("TrazAquiApp","/home/simao/Desktop/Universidade/POO/ProjetoPOO1920/ProjetoPOO1920/LogsGerados.csv");
     *
     *         ScrollPane sp = new ScrollPane();
     *         VBox box = new VBox();
     *         VBox vCaixa = new VBox();
     *         Scene cena = new Scene(box,300,300);
     *         stage.setScene(cena);
     *         box.getChildren().add(sp);
     *         String s = logNegocio.toString();
     *         System.out.println(s);
     *         Label la = new Label(s);
     *         vCaixa.getChildren().add(la);
     *         sp.setContent(vCaixa);
     *
     * escrever e manipular input de choice box's
     * @Override
     *     public void start(Stage primaryStage) throws Exception {
     *         window = primaryStage;
     *         window.setTitle("TrazAquiApp");
     *
     *         button = new Button("Click me");
     *
     *         ChoiceBox<String> choice = new ChoiceBox<>();
     *
     *         choice.getItems().add("Apple");
     *         choice.getItems().add("Banana");
     *         choice.getItems().add("Bacon");
     *
     *         choice.setValue("Bacon");
     *
     *         VBox layout = new VBox(10);
     *         layout.setPadding(new Insets(20,20,20,20));
     *         layout.getChildren().addAll(choice,button);
     *
     *         button.setOnAction(e->getChoice(choice));
     *
     *         scene = new Scene(layout,300,250);
     *         window.setScene(scene);
     *         window.show();
     *
     *     }
     *
     *     private void getChoice(ChoiceBox<String> choice){
     *         String food = choice.getValue();
     *         System.out.println(food);
     *     }
     */

    /**
     * como criar checkbox's
     * @Override
     *     public void start(Stage primaryStage) throws Exception {
     *         window = primaryStage;
     *         window.setTitle("TrazAquiApp");
     *
     *         CheckBox box1 = new CheckBox("Bacon");
     *         CheckBox box2 = new CheckBox("Tuna");
     *         box1.setSelected(true);
     *
     *         button = new Button("Click me");
     *         button.setOnAction(e-> handleOptions(box1,box2));
     *
     *
     *         VBox layout = new VBox(10);
     *         layout.setPadding(new Insets(20,20,20,20));
     *         layout.getChildren().addAll(box1,box2,button);
     *
     *         scene = new Scene(layout,300,250);
     *         window.setScene(scene);
     *         window.show();
     *
     *     }
     *
     *     private void handleOptions(CheckBox box1, CheckBox box2){
     *         String s = "Users order:\n";
     *
     *         if(box1.isSelected()){
     *             s += "->Bacon\n";
     *         }
     *
     *         if(box2.isSelected()){
     *             s += "->Tuna\n";
     *         }
     *
     *         System.out.println(s);
     *     }
     */


    /**
     *  como interagir com o input do utilizador
     *  @Override
     *     public void start(Stage primaryStage) throws Exception {
     *         window = primaryStage;
     *         window.setTitle("TrazAquiApp");
     *
     *         TextField nameInput = new TextField();
     *         nameInput.setPromptText("name");
     *
     *         button = new Button("Click me");
     *         button.setOnAction(e-> isInt(nameInput, nameInput.getText()));
     *
     *
     *         VBox layout = new VBox(10);
     *         layout.setPadding(new Insets(20,20,20,20));
     *         layout.getChildren().addAll(nameInput,button);
     *
     *         scene = new Scene(layout,300,250);
     *         window.setScene(scene);
     *         window.show();
     *
     *     }
     *
     *     private boolean isInt(TextField input, String s){
     *         try{
     *             int age = Integer.parseInt(input.getText());
     *             System.out.println("User is: "+ age + " years old");
     *             return true;
     *         }catch(NumberFormatException e){
     *             System.out.println("Error: " + s + " is not a number");
     *             return false;
     *         }
     *     }
     */

    /**
     *como criar janelas de texto
     *  public void start(Stage primaryStage) throws Exception {
     *        window = primaryStage;
     *        window.setTitle("TrazAquiApp");
     *
     *         GridPane grid = new GridPane();
     *         grid.setPadding(new Insets(10,10,10,10));
     *         grid.setVgap(8);
     *         grid.setHgap(10);
     *
     *         Label nameLabel1 = new Label("Username:");
     *         GridPane.setConstraints(nameLabel1, 0, 0);
     *
     *         TextField nameInput = new TextField("Bitch");
     *         GridPane.setConstraints(nameInput, 1, 0);
     *
     *         Label passLabel1 = new Label("Password:");
     *         GridPane.setConstraints(passLabel1, 0, 1);
     *
     *         TextField passInput = new TextField();
     *         passInput.setPromptText("password");
     *         GridPane.setConstraints(passInput,1,1);
     *
     *         Button button = new Button("Log in");
     *         GridPane.setConstraints(button,1,2);
     *
     *         grid.getChildren().addAll(nameLabel1, nameInput, passLabel1, passInput, button);
     *
     *         Scene scene = new Scene(grid,300,200);
     *         window.setScene(scene);
     *         window.show();
     *
     *     }
     */

    /**
     * @Override
     *     public void start(Stage primaryStage) throws Exception {
     *         window = primaryStage;
     *         window.setTitle("TrazAquiApp");
     *
     *         HBox topMenu = new HBox();
     *         Button buttonA = new Button("File:");
     *         Button buttonB = new Button("Edit:");
     *         Button buttonC = new Button("View:");
     *         topMenu.getChildren().addAll(buttonA,buttonB,buttonC);
     *
     *         VBox leftMenu = new VBox();
     *         Button buttonD = new Button("D");
     *         Button buttonE = new Button("E");
     *         Button buttonF = new Button("F");
     *         leftMenu.getChildren().addAll(buttonE,buttonF,buttonD);
     *
     *         BorderPane borderPane = new BorderPane();
     *         borderPane.setTop(topMenu);
     *         borderPane.setLeft(leftMenu);
     *
     *         Scene scene = new Scene(borderPane,300,250);
     *         window.setScene(scene);
     *         window.show();
     */

    /**
     * @Override
     *     public void start(Stage primaryStage) throws Exception {
     *         window = primaryStage;
     *
     *         Label label1 = new Label("Welcome");
     *         Button button1 = new Button("Forward");
     *         button1.setOnAction(e-> window.setScene(scene2));
     *
     *         VBox layout1 = new VBox(20);
     *         layout1.getChildren().addAll(label1, button1);
     *         scene1 = new Scene(layout1,200,200);
     *
     *         Label label2 = new Label("Hi");
     *         Button button2 = new Button("Backward");
     *         button2.setOnAction(e -> window.setScene(scene1));
     *
     *         StackPane layout2 = new StackPane();
     *         layout2.getChildren().addAll(label2,button2);
     *         scene2 = new Scene(layout2,600,300);
     *
     *         window.setScene(scene1);
     *         window.setTitle("TrazAquiApp");
     *         window.show();
     *
     *
     *     }
     */

    /**
     * @Override
     *     public void start(Stage stage) throws Exception {
     *         stage.setTitle("TrazAquiApp");
     *
     *         button1 = new Button();
     *
     *         button1.setText("Log in");
     *
     *         button1.setOnAction(e->System.out.println("ola"));
     *
     *         StackPane layout = new StackPane();
     *         layout.getChildren().add(button1);
     *
     *
     *         Scene scene = new Scene(layout, 300, 250);
     *         stage.setScene(scene);
     *         stage.show();
     *     }
     *
     * @Override
     *     public void handle(ActionEvent actionEvent) {
     *         if(actionEvent.getSource()==button1){
     *             System.out.println("Parabéns");
     *         }
     *     }
     */
}
