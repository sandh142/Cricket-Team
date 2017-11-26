package pkgfinal.project;

import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FinalProject extends Application {

    private ArrayList<String> Players = new ArrayList(100);

    Label playerID = new Label("Player ID");
    Label playerName = new Label("Player Name");
    Label playerCountry = new Label("Player Country");
    Label playerHeight = new Label("Player Height");
    Label playerMatches = new Label("Player Matches");
    Label playerAge = new Label("Player Age");

    TextField plyID = new TextField();
    TextField plyName = new TextField();
    TextField plyCountry = new TextField();
    TextField plyHeight = new TextField();
    TextField plyMatches = new TextField();
    TextField plyAge = new TextField();

    Button first = new Button("First");
    Button last = new Button("Last");
    Button next = new Button("Next");
    Button previous = new Button("Previous");
    Button add = new Button("Add");
    Button update = new Button("Update");
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete");
    int check = 0;
    Button search2 = new Button("Search");
    TextField search1 = new TextField();
    Stage s = new Stage();
    
    @Override
    public void start(Stage primaryStage) {
        VBox search = new VBox();
        search.getChildren().addAll(search1, search2);
        Scene scene2 = new Scene(search, 250, 50);
        s.setTitle("Search a record");
        s.setScene(scene2);
        File pLayers = new File("Player.dat");
        if (pLayers.exists()) {
            try {
                FileReader one = new FileReader("Player.dat");
                BufferedReader two = new BufferedReader(one);
                String line = two.readLine();
                while (line != null) {
                    Players.add(line);
                    line = two.readLine();
                }
            } catch (FileNotFoundException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        int counter=0;
        sTokenizer2();
        last.setOnAction(e -> {
            lastButton();
        });
        first.setOnAction(e -> {
            firstButton();
        });
        next.setOnAction(e -> {
            nextButton();
        });
        previous.setOnAction(e -> {
            previousButton();
        });
        add.setOnAction(e -> {
            addButton();
        });
        update.setOnAction(e -> {
            updateButton();
        });
        cancel.setOnAction(e -> {
            cancelButton();
        });
        delete.setOnAction(e -> {
            deleteButton();
        });
        search2.setOnAction(e -> {
            if (search1.equals(Players)) {
                rSearch(search1);
            } else {
                System.out.println("Record not found.");
            }
        });    
        Image imagee = new Image("image/batandball.jpg");
        BackgroundSize bSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage bImage = new BackgroundImage(imagee, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize);
        Background background = new Background(bImage);
        GridPane grid = new GridPane();
        grid.setBackground(background);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.add(playerID, 3, 8);
        grid.add(plyID, 4, 8);
        grid.add(playerName, 3, 9);
        grid.add(plyName, 4, 9);
        grid.add(playerCountry, 3, 10);
        grid.add(plyCountry, 4, 10);
        grid.add(playerHeight, 3, 11);
        grid.add(plyHeight, 4, 11);
        grid.add(playerMatches, 3, 12);
        grid.add(plyMatches, 4, 12);
        grid.add(playerAge, 3, 13);
        grid.add(plyAge, 4, 13);
        grid.add(first, 1, 14);
        grid.add(last, 5, 14);
        grid.add(next, 7, 8);
        grid.add(previous, 1, 8);
        grid.add(add, 1, 15);
        grid.add(delete, 5, 15);
        grid.add(update, 4, 15);
        grid.add(cancel, 4, 16);

        Scene scene = new Scene(grid, 600, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Final Project");
        primaryStage.show();
        
        s.show();
    }
    int counter = 0;

    public void firstButton() {
        if (Players.isEmpty()) {
            JOptionPane.showMessageDialog(null, "The List Is Empty", "Error Message", JOptionPane.INFORMATION_MESSAGE);
        }
        counter=0;
        sTokenizer2();
    }

    public void lastButton() {
        if (Players.isEmpty()) {
            JOptionPane.showMessageDialog(null, "The List Is Empty", "Error Message", JOptionPane.INFORMATION_MESSAGE);
        }
        counter = Players.size() - 1;
        sTokenizer2();
    }
    
    public void nextButton() {
        counter++;
        try {
            if (counter >= Players.size() && !Players.isEmpty()) {
                JOptionPane.showMessageDialog(null, "This is your last racord,"
                        + " sorry you cannot go further", "Error Message", JOptionPane.INFORMATION_MESSAGE);
                counter--;
                throw new ArrayIndexOutOfBoundsException("");
            }
            if (Players.isEmpty()) {
                JOptionPane.showMessageDialog(null, "The List Is Empty", "Error Message", JOptionPane.INFORMATION_MESSAGE);
            }
            sTokenizer2();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    public void previousButton() {
        try {
            counter--;
            if (counter < 0 && !Players.isEmpty()) {
                counter = 0;
                JOptionPane.showMessageDialog(null, "This is your first record,"
                        + " sorry you cannot go back one record", "Error Message", JOptionPane.INFORMATION_MESSAGE);
                throw new ArrayIndexOutOfBoundsException("Sorry! Less Records");
            }
            if (Players.isEmpty()) {
                JOptionPane.showMessageDialog(null, "The List Is Empty", "Error Message", JOptionPane.INFORMATION_MESSAGE);
            }
            sTokenizer2();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }
    public void addButton() {
        check = 1;
        plyID.setText("");
        plyName.setText("");
        plyCountry.setText("");
        plyHeight.setText("");
        plyMatches.setText("");
        plyAge.setText("");
        plyID.requestFocus();
        plyID.setOnKeyReleased(e -> {
            if (isNumeric(e.getCode())) {
            } else {
                JOptionPane.showMessageDialog(null, "Number is been required",
                        "Error Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        plyMatches.setOnKeyReleased(e -> {
            if (isNumeric(e.getCode())) {
            } else {
                JOptionPane.showMessageDialog(null, "Number is been Required",
                        "Error Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        plyAge.setOnKeyReleased(e -> {
            if (!isNumeric(e.getCode())) {
                JOptionPane.showMessageDialog(null, "Letters are been Required",
                        "Error Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
            }
        });
    }

    public void updateButton() {
        try {
            String textField1 = plyName.getText();
            String textField2 = plyCountry.getText();
            String textField3 = plyID.getText();
            String textField4 = plyHeight.getText();
            String textField5 = plyMatches.getText();
            String textField6 = plyAge.getText();
            String allTextFields = textField1 + "," + textField2 + ","
                    + textField3 + "," + textField4 + "," + textField5 + "," + textField6;
            Players.add(allTextFields);
            FileWriter one = new FileWriter("Player.dat");
            BufferedWriter buff = new BufferedWriter(one);
            for (int sub = 0; sub < Players.size(); sub++) {
                buff.write(Players.get(sub) + '\n');
            }
            buff.close();
            counter = Players.size() - 1;
            sTokenizer2();
            JOptionPane.showMessageDialog(null, "Record has been added Successfully",
                    "Message", JOptionPane.INFORMATION_MESSAGE);
            counter++;
        } catch (IOException e) {
        }
    }

    public void cancelButton() {
        counter = Players.size() - 1;
        sTokenizer2();
    }

    private boolean isNumeric(KeyCode code) {
        switch (code) {
            case DIGIT0:
            case DIGIT1:
            case DIGIT2:
            case DIGIT3:
            case DIGIT4:
            case DIGIT5:
            case DIGIT6:
            case DIGIT7:
            case DIGIT8:
            case DIGIT9:
            case NUMPAD0:
            case BACK_SPACE:
            case ENTER:
            case CAPS:
            case TAB:
            case PERIOD:
            case SPACE:
                return true;
        }
        return false;
    }

    private boolean isAlphabetic(KeyCode code1) {
        switch (code1) {
            case DIGIT0:
            case DIGIT1:
            case DIGIT2:
            case DIGIT3:
            case DIGIT4:
            case DIGIT5:
            case DIGIT6:
            case DIGIT7:
            case DIGIT8:
            case DIGIT9:
            case NUMPAD0:
                return false;
        }
        return true;
    }

    public void deleteButton() {
        try {
            if (Players.isEmpty()) {
                plyName.setText("");
                plyCountry.setText("");
                plyID.setText("");
                plyHeight.setText("");
                plyMatches.setText("");
                plyAge.setText("");
                plyID.requestFocus();
                JOptionPane.showMessageDialog(null, "The List Is Empty", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else if (counter == Players.size() - 1) {
                counter = counter - 1;
                sTokenizer2();
            } else {
                Players.remove(Players.get(counter));
                if (Players.isEmpty()) {
                    plyName.setText("");
                    plyCountry.setText("");
                    plyID.setText("");
                    plyHeight.setText("");
                    plyMatches.setText("");
                    plyAge.setText("");
                    plyID.requestFocus();
                    plyID.setOnKeyReleased(e -> {
                        if (isNumeric(e.getCode())) {
                        } else {
                            JOptionPane.showMessageDialog(null, "Number is been Required",
                                    "Error Message", JOptionPane.INFORMATION_MESSAGE);
                        }
                    });
                    plyMatches.setOnKeyReleased(e -> {
                        if (isNumeric(e.getCode())) {
                        } else {
                            JOptionPane.showMessageDialog(null, "Number is been Required",
                                    "Error Message", JOptionPane.INFORMATION_MESSAGE);
                        }
                    });
                    plyAge.setOnKeyReleased(e -> {
                        if (!isNumeric(e.getCode())) {
                            JOptionPane.showMessageDialog(null, "Letters are been Required",
                                    "Error Message", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                        }
                    });
                }
                FileWriter one = new FileWriter("Player.dat");
                BufferedWriter buff = new BufferedWriter(one);
                for (int sub = 0; sub < Players.size(); sub++) {
                    buff.write(Players.get(sub) + '\n');
                }
                buff.close();
                sTokenizer2();
            }
        } catch (IOException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("This is the last record");
        }
    }

    public void sTokenizer2() {
        String div = Players.get(counter);
        StringTokenizer string1 = new StringTokenizer(div, ":");
        StringTokenizer string2 = new StringTokenizer(string1.nextToken(), ",");
        plyName.setText(string2.nextToken());
        plyCountry.setText(string2.nextToken());
        plyID.setText(string2.nextToken());
        plyHeight.setText(string2.nextToken());
        plyMatches.setText(string2.nextToken());
        plyAge.setText(string2.nextToken());
    }

    public boolean rSearch(TextField search1) {   
        return Players.contains(search1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
