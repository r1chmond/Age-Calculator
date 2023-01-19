import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;


public class AgeCalcGUI extends Application {

   private TextField dayBox;
   private TextField monthBox;
   private TextField yearBox;

   private ChoiceBox<String> choiceBox;
   private Stage stage;

   private Scene scene_1;

    private final AgeCalc calc;
    private int day;
    private int month;
    private int year;
    private String[] names;
    private final ObservableList<String> sex;

    private final Text errorText;

    /**
     * Constructor for AgeCalcGUI class.
     */
    public AgeCalcGUI()
    {
        sex = FXCollections.observableArrayList("Male" , "Female");
        calc = new AgeCalc();
        errorText = new Text("");
        names = new String[7];
    }

    /**
     * @return The day entered in the text field.
     */
    public int getEnteredDay()
    {
        String input = dayBox.getText();
        try
        {
            return day = Integer.parseInt(input);
        }
        catch (NumberFormatException e)
        {
            return -1;
        }
    }

    /**
     * @return The month entered in the text field.
     */
    public int getEnteredMonth()
    {
        String input = monthBox.getText();
        try
        {
            return month = Integer.parseInt(input);
        }
        catch (NumberFormatException e)
        {
            return -1;
        }
    }

    /**
     * @return The year entered in the text field.
     */
    public int getEnteredYear()
    {
        String input = yearBox.getText();
        try
        {
            return year = Integer.parseInt(input);
        }
        catch (NumberFormatException e)
        {
            return 0;
        }
    }

    /**
     * Retrieve name based on the date of birth provided
     */
    public String getNameByDate()
    {
        if(names[0] == null){
            return "no name. Please Select 'Sex' to generate name.";
        }

        int index = calc.getDateOfBirth().getDayOfWeek().getValue();
        return switch (index) {
            case 1 -> names[0];
            case 2 -> names[1];
            case 3 -> names[2];
            case 4 -> names[3];
            case 5 -> names[4];
            case 6 -> names[5];
            case 7 -> names[6];
            default -> "";
        };

    }
    /**
     * Sets the date of birth
     */
    public void setDOB()
    {
      calc.setDateOfBirth(getEnteredYear(),getEnteredMonth(),getEnteredDay());
    }

    /**
     * About action when it is clicked
     * @param event Create alert box
     */
    public void aboutAction(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setContentText("Created by Richmond");
        alert.showAndWait();

    }

    /**
     * Actions for choice box
     */
    public void choiceBoxAction(Event event)
    {
       int index = choiceBox.getSelectionModel().getSelectedIndex();
        switch (index) {
            case 0 -> names = calc.getMaleDayName();
            case 1 -> names = calc.getFemaleDayName();
        }
    }

    /**
     * Action to be carried out the back button is pressed
     */
    public void backButtonAction(MouseEvent event)
    {
        stage.setScene(scene_1);
        day = 0;
        month = 0;
        year = 0;
    }
    /**
     * Provides description of how to use the programme
     */
public void helpAction(ActionEvent event)
{
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Help");
    alert.setContentText("Insert date of birth in the boxes provided accordingly and click calculate. " + "\n" +
                    "This app has some cultural aspect incorporated. In Ghana, the people have generic names based " +
            "on the day they were born. Eg. Every person born on Friday is called Kofi, etc"
                        );
    alert.showAndWait();
}

/**
 * Action when the calculate button is clicked.
 */
public void calculateButtonAction(MouseEvent mouseEvent)
{
    setDOB();
    if((day <= 31 && day >= 1) && (month <= 12 && month > 0) && (year <= LocalDate.now().getYear() && year >=0)) {
    calc.calcDifference();
    secondScene(stage);
    errorText.setText("");
}
    else{
        errorText.setText("Please enter valid date of birth");
    }
}

    /**
     * Calculate button action
     * @param stage The main stage of the application
     */
    public void secondScene(Stage stage)
{   VBox childRoot = new VBox();
    createMenubar(childRoot);

    Button backButton = new Button(" < ");
    backButton.setAlignment(Pos.TOP_LEFT);
    backButton.setOnMousePressed(this::backButtonAction);

    Text textYears = new Text("You are " + calc.getYears()+ " years, " +
                                calc.getMonth()+ " months and " +
                                    calc.getDays() + " days old");
    textYears.setId("secondSceneText");

    Text textDay = new Text("You were born on " + calc.weekDayOfBirth());
    textDay.setId("textDay");

    Text textName = new Text("Your name is " + getNameByDate());

    textName.setId("textName");

    VBox forText = new VBox(textYears, textDay, textName);
    forText.setAlignment(Pos.BOTTOM_CENTER);
    VBox.setMargin(textDay, new Insets(30, 0,0,0));



    BorderPane contentPane = new BorderPane(forText, backButton, null,null, null);
    BorderPane.setMargin(forText, new Insets(60,0,0,0));


    childRoot.getChildren().add(contentPane);
    Scene resultScene = new Scene(childRoot, 710, 500);
    resultScene.getStylesheets().add("StyleSheet.css");
    stage.setScene(resultScene);
}
    /**
     * Action to close the program.
     */
    public void closeAction(ActionEvent event)
    {
        System.exit(0);
    }

    /**
     * Main launch method
     * @param stage The main stage of application
     */
    @Override
    public void start(Stage stage) {

    this.stage = stage;
        VBox root = new VBox();

// -- menu bar --
   createMenubar(root);

       // -- text fields --
       dayBox = new TextField();
        Label dayLabel = new Label("Day");

       monthBox = new TextField();
       Label monthLabel = new Label("Month");

       yearBox = new TextField();
        Label yearLabel = new Label("Year");

        HBox fillArea = new HBox(dayLabel, dayBox, monthLabel, monthBox, yearLabel,yearBox);
        fillArea.setAlignment(Pos.CENTER);
        fillArea.prefHeight(200);

        HBox choice = new HBox();
        choice.setAlignment(Pos.CENTER);
        choiceBox = new ChoiceBox<>(sex);
        choiceBox.setOnAction(this::choiceBoxAction);

        Label choiceLabel = new Label("Sex");
        choice.getChildren().addAll(choiceLabel,choiceBox);

        VBox centreStruct = new VBox();
        VBox.setMargin(choice, new Insets(15, 0,0,0));
        centreStruct.getChildren().addAll(fillArea, choice);

        Text text = new Text("Enter your date of birth");
        text.setId("firstSceneText");

        HBox forText = new HBox(text);
        forText.setAlignment(Pos.BOTTOM_CENTER);
        HBox.setMargin(text, new Insets(50,0,0,0));
        VBox top = new VBox(forText);

        // -- 'calculate' button --
        Button calculateButton = new Button("Calculate");
        calculateButton.setOnMousePressed(this::calculateButtonAction);

        VBox newStruct = new VBox();
        HBox bottom = new HBox(calculateButton);
        bottom.setAlignment(Pos.CENTER);
        newStruct.setAlignment(Pos.CENTER);


        newStruct.getChildren().addAll(bottom, errorText);
        VBox.setMargin(errorText, new Insets(20, 0, 0,0));



        BorderPane contentPane = new BorderPane(centreStruct, top, null, newStruct, null);
        BorderPane.setMargin(centreStruct, new Insets(40,0,30,0));

        root.getChildren().add(contentPane);
        scene_1 = new Scene(root, 710, 500);

        scene_1.getStylesheets().add("StyleSheet.css");

        stage.setScene(scene_1);
        stage.setTitle("Age Calculator");
        stage.show();
    }


    /**
     * Creates the menu bar.
     * @param pane the root pane
     */
    public void createMenubar(Pane pane)
    {
        // -- menu items --
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        Menu helpMenu = new Menu("Help");

        MenuItem close= new MenuItem("Close");
        MenuItem help = new MenuItem("Help");
        MenuItem about = new MenuItem("About");

        fileMenu.getItems().add(close);
        close.setOnAction(this::closeAction);

        helpMenu.getItems().addAll(help,about);
        about.setOnAction(this::aboutAction);
        help.setOnAction(this::helpAction);

        menuBar.getMenus().addAll(fileMenu,helpMenu);

        pane.getChildren().add(menuBar);

    }

    /**
     *
     */
    public void notAMethod(){
        System.out.println("Not a method");
    }
    public static void main(String[] args)
    {
        launch();
    }
}
