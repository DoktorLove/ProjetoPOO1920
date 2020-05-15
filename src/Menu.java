import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu{
    // variáveis de instância
    private String[][] opcoes;

    /**
     * Constructor for objects of class Menu
     */
    public Menu(String[][] opcoes) {
        this.opcoes = opcoes;
    }


    //lista de janelas de texto e menu com janelas de texto.
    public List<Control> format() throws FileNotFoundException {
        List<Control> lst = new ArrayList<>();
        for (String[] lista: this.opcoes) {
            if(lista[0].equals("Button")) {
                Button b = new Button(lista[1]);
                b.setMaxSize(Integer.parseInt(lista[2]), Integer.parseInt(lista[3]));
                GridPane.setConstraints(b, Integer.parseInt(lista[4]),
                        Integer.parseInt(lista[5]));
                lst.add(b);
            }
            else if(lista[0].equals("Label")){
                Label l = new Label(lista[1]);
                GridPane.setConstraints(l, Integer.parseInt(lista[2]),
                        Integer.parseInt(lista[3]));
                lst.add(l);
            }
            else if(lista[0].equals("TextField")){
                TextField t = new TextField();
                t.setPromptText(lista[1]);
                GridPane.setConstraints(t, Integer.parseInt(lista[2]),
                        Integer.parseInt(lista[3]));
                lst.add(t);
            }
            else if(lista[0].equals("CheckBox")){
                CheckBox c = new CheckBox(lista[1]);
                GridPane.setConstraints(c, Integer.parseInt(lista[2]),
                        Integer.parseInt(lista[3]));
                lst.add(c);
            }
            else if(lista[0].equals("ButtonRet")){
                FileInputStream input = new FileInputStream("/home/simao/Desktop/Universidade/POO/ProjetoPOO1920/ProjetoPOO1920/Imagens/returnImage.png");
                Image image = new Image(input);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(20);
                imageView.setFitHeight(20);

                Button b = new Button(lista[1], imageView);
                b.setGraphic(imageView);
                GridPane.setConstraints(b, Integer.parseInt(lista[2]),
                        Integer.parseInt(lista[3]));
                lst.add(b);
            }
        }
        return lst;
    }

    public Pair<Scene,List<Control>> executeFormatGrid() throws Exception {
        List<Control> lst = format();
        GridPane layoutInic = new GridPane();
        layoutInic.setPadding(new Insets(50,10,10,35));
        layoutInic.setVgap(8);
        layoutInic.setHgap(10);
        layoutInic.getChildren().addAll(lst);
        Scene scene = new Scene(layoutInic,350,400);
        Pair<Scene,List<Control>> par = new Pair<>(scene,lst);
        return par;
    }
    /**
    public Pair<Scene,List<Control>> executeFormatScroll() throws Exception {
        VBox layoutInic = new VBox();
        Scene scene = new Scene(layoutInic,350,400);


        layoutInic.getChildren().add(sp);
        Pair<Scene,List<Control>> p = this.executeFormatGrid();
        List<Control> lista = p.getValue();
        sp.setContent(lista.get(0));

        Pair<Scene,List<Control>> par = new Pair<>(scene,new ArrayList<>());
        return par;
    }
     */

}
