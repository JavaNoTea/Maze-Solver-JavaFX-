package application;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.event.ChangeListener;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Main extends Application {

    Stage window;
    Scene MainScene;
    Group g;
    Map map;
    static TextField TeleField;

    public static void main(String[] args) {
	launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
	window = primaryStage;
	g = new Group();

	// BUTTON SECTION
	// Initialize Buttons
	Button StartButton = new Button("Start");
	Button EndButton = new Button("End");
	Button WallButton = new Button("Wall");
	Button TeleportButton = new Button("Tele");
	Button EraserButton = new Button("Erase");
	Button ResetButton = new Button("Reset");
	Button RunStopButton = new Button("Run");
	// Button Positions
	StartButton.setLayoutX(25);
	StartButton.setLayoutY(15);
	EndButton.setLayoutX(85);
	EndButton.setLayoutY(15);
	WallButton.setLayoutX(145);
	WallButton.setLayoutY(15);
	TeleportButton.setLayoutX(205);
	TeleportButton.setLayoutY(15);
	EraserButton.setLayoutX(265);
	EraserButton.setLayoutY(15);
	ResetButton.setLayoutX(300);
	ResetButton.setLayoutY(755);
	RunStopButton.setLayoutX(700);
	RunStopButton.setLayoutY(755);
	// Button Sizes
	StartButton.setPrefSize(50, 30);
	EndButton.setPrefSize(50, 30);
	WallButton.setPrefSize(50, 30);
	TeleportButton.setPrefSize(50, 30);
	EraserButton.setPrefSize(50, 30);
	ResetButton.setPrefSize(50, 23);
	RunStopButton.setPrefSize(50, 23);
	// Button Colors
	StartButton.setStyle("-fx-font: 10 arial; -fx-background-color: #14b905; ");
	EndButton.setStyle("-fx-font: 10 arial; -fx-background-color: #ff1e14; ");
	WallButton.setStyle("-fx-font: 10 arial; -fx-background-color: #6e6e6e; ");
	TeleportButton.setStyle("-fx-font: 10 arial; -fx-background-color: #f000ec; ");
	EraserButton.setStyle("-fx-font: 10 arial; -fx-background-color: #dbdbdb; ");
	ResetButton.setStyle(
		"-fx-font: 10 arial; -fx-text-fill: #696969; -fx-border-radius: 2px; -fx-border-color:#696969; -fx-background-color: white; ");
	RunStopButton.setStyle(
		"-fx-font: 12 arial; -fx-text-fill: black; -fx-border-radius: 2px; -fx-border-color:#696969; -fx-background-color: #14b905; ");
	// Add to group
	g.getChildren().addAll(StartButton, EndButton, WallButton, TeleportButton, EraserButton, ResetButton, RunStopButton);

	// LABEL SECTION
	// Initialize Labels
	Label WidthLabel = new Label("Grid Width: ");
	Label HeightLabel = new Label("Grid Height: ");
	Label InvalidSizeLabel = new Label("Invalid Size: Please Enter Size Between 1 and 25");
	Label TeleportTypeLabel = new Label("Teleporter Group: ");
	// Label Positions
	WidthLabel.setLayoutX(25);
	WidthLabel.setLayoutY(757);
	HeightLabel.setLayoutX(150);
	HeightLabel.setLayoutY(757);
	InvalidSizeLabel.setLayoutX(450);
	InvalidSizeLabel.setLayoutY(757);
	TeleportTypeLabel.setLayoutX(600);
	TeleportTypeLabel.setLayoutY(17);
	// Label Styles
	WidthLabel.setStyle("-fx-font: 12 arial");
	HeightLabel.setStyle("-fx-font: 12 arial");
	InvalidSizeLabel.setStyle("-fx-font: 12 arial; -fx-text-fill: red;");
	TeleportTypeLabel.setStyle("-fx-font: 12 arial;");
	
	InvalidSizeLabel.setVisible(false);
	// Add Labels to group
	g.getChildren().addAll(WidthLabel, HeightLabel, InvalidSizeLabel, TeleportTypeLabel);

	// TEXT FIELD SECTION
	// Initialize Text Fields
	TextField WidthTextField = new TextField();
	TextField HeightTextField = new TextField();
	TeleField = new TextField();
	// Text Field Positions
	WidthTextField.setLayoutX(100);
	WidthTextField.setLayoutY(755);
	HeightTextField.setLayoutX(230);
	HeightTextField.setLayoutY(755);
	TeleField.setLayoutX(710);
	TeleField.setLayoutY(15);
	// Text Field Sizes
	WidthTextField.setPrefWidth(40);
	HeightTextField.setPrefWidth(40);
	TeleField.setPrefWidth(30);
	// Label Styles
	WidthTextField.setStyle("-fx-font: 12 arial");
	HeightTextField.setStyle("-fx-font: 12 arial");
	TeleField.setStyle("-fx-font: 12 arial");
	//Set Default Text
	WidthTextField.setText("10");
	HeightTextField.setText("10");
	TeleField.setText("A");
	
	// Add Text Fields to group
	g.getChildren().addAll(WidthTextField, HeightTextField, TeleField);

	// SCENE SECTION
	MainScene = new Scene(g, 800, 820);

	// Create Default 10x10 Map
	map = new Map(g, 10, 10);

	

	// EVENT SECTION
	// Reset Event
	ResetButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		if (Integer.parseInt(WidthTextField.getText()) > 25 || Integer.parseInt(WidthTextField.getText()) < 1
			|| Integer.parseInt(HeightTextField.getText()) > 25
			|| Integer.parseInt(HeightTextField.getText()) < 1) {
		    InvalidSizeLabel.setVisible(true);
		    return;
		}
		
		//MOVE THE WHOLE MAP INTO ANOTHER GROUP SO THAT GROUP CAN BE DELETED
		g = map.RemoveContents(g);
		map = new Map(g, Integer.parseInt(WidthTextField.getText()),
			Integer.parseInt(HeightTextField.getText()));
		InvalidSizeLabel.setVisible(false);
		
	    }
	});
	
	WallButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent arg0) {
		map.brush = PointType.WALL;
	    }   
	});
	
	EraserButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent arg0) {
		map.brush = PointType.FREE;
	    }   
	});
	
	StartButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent arg0) {
		map.brush = PointType.START;
	    }   
	});
	
	EndButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent arg0) {
		map.brush = PointType.FINISH;
	    }   
	});
	
	TeleportButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent arg0) {
		map.brush = PointType.TELEPORTER;
	    }   
	});
	RunStopButton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent arg0) {
		if(RunStopButton.getText().compareTo("Run") == 0) {
		    RunStopButton.setText("Stop");
		    RunStopButton.setStyle("-fx-font: 12 arial; -fx-text-fill: black; -fx-border-radius: 2px; -fx-border-color:#696969; -fx-background-color: #ff1e14; ");
		    map.PreprocessMap();
		    System.out.println(map.solve());
		}
		else {
		    RunStopButton.setText("Run");
		    RunStopButton.setStyle("-fx-font: 12 arial; -fx-text-fill: black; -fx-border-radius: 2px; -fx-border-color:#696969; -fx-background-color: #14b905; ");
		}
		
	    }
	});

	// WINDOW SECTION
	window.setScene(MainScene);
	window.setMaxHeight(820);
	window.setMinHeight(820);
	window.setMaxWidth(800);
	window.setMinWidth(800);
	window.setTitle("Maze BFS Made By Christian King");
	window.show();
    }

}

//Remove this class and put it all in main
class Map {
    public int sizeX, sizeY;
    Point[][] contents;
    Point Start, End;
    PointType brush;

    Map(Group g, int sizeX, int sizeY) {
	brush = PointType.START;
	
	this.sizeX = sizeX;
	this.sizeY = sizeY;
	
	// Make point array to store the contents on the map
	contents = new Point[sizeX][sizeY];

	// Take in the contents of the map
	for (int y = 0; y < sizeY; y++) {
	    for (int x = 0; x < sizeX; x++) {
		contents[x][y] = new Point('.', x, y, this);
		g.getChildren().add(contents[x][y]);
	    }
	}

	PreprocessMap();
    }

    Group RemoveContents(Group g) {
	for (int y = 0; y < sizeY; y++) {
	    for (int x = 0; x < sizeX; x++) {
		g.getChildren().remove(contents[x][y]);
	    }
	}
	return g;
    }

    void PreprocessMap() {
	// Run the preprocess function for every point on the map
	for (int y = 0; y < sizeY; y++) {
	    for (int x = 0; x < sizeX; x++) {
		contents[x][y].PreprocessPoint(contents, sizeX, sizeY);
	    }
	}
    }

    int solve() {
	// Set our man to the starting coors
	int manX = Start.x, manY = Start.y;

	// Start BFS
	BFS(manX, manY, 0);

	// This is triggered when the step count is still at an impossible number which
	// means it never found the finish
	if (End.shortestpath > 100000)
	    return -1;

	// Return step count
	return End.shortestpath;
    }

    void BFS(int manX, int manY, int steps) {
	// if wall return
	if (contents[manX][manY].type == PointType.WALL)
	    return;

	// if the steps is greater than the previous shortest path
	// then this new path must be longer, so return
	if (steps > contents[manX][manY].shortestpath)
	    return;

	// This is the new shortest path so record it
	contents[manX][manY].shortestpath = steps;

	// Recurse in all possible directions including teleports
	for (int i = 0; i < contents[manX][manY].places2Go.size(); i++) {
	    BFS(contents[manX][manY].places2Go.get(i).x, contents[manX][manY].places2Go.get(i).y, steps + 1);
	}

	return;
    }
}

class Point extends Button {
    PointType type;
    char teleportTo;
    ArrayList<Point> places2Go;
    boolean used = false;
    int x, y, shortestpath = Integer.MAX_VALUE;
    int dxy[] = { 1, -1 };

    Point(char data, int x, int y, Map map) {
	Intialize(data, x, y, map);
    }
    
    void Intialize(char data, int x, int y, Map map) {
	int rows = map.sizeX;
	int cols = map.sizeY;
	this.x = x;
	this.y = y;
	places2Go = new ArrayList<Point>();

	this.setLayoutX((x * (760 / cols)) + 25);
	this.setLayoutY((y * (690 / rows)) + 60);
	int width = (760 / cols) - 5;
	int height = (690 / rows) - 5;

	this.setPrefSize(width, height);

	// Assign point type
	switch (data) {
	case '!':
	    type = PointType.WALL;
	    this.setStyle("-fx-background-color: #6e6e6e;");
	    break;
	case '.':
	    type = PointType.FREE;
	    this.setStyle("-fx-background-color: #dbdbdb;");
	    break;
	case '*':
	    type = PointType.START;
	    this.setStyle("-fx-background-color: #14b905;");
	    
	    if(map.Start != null)
	    map.Start.Intialize('.', map.Start.x, map.Start.y, map);
	    map.Start = this;
	    break;
	case '$':
	    type = PointType.FINISH;
	    this.setStyle("-fx-background-color: #ff1e14;");
	    
	    if(map.End != null)
	    map.End.Intialize('.', map.End.x, map.End.y, map);
	    map.End = this;
	    break;
	default:
	    // If its a teleporter store what letter teleporter it is
	    type = PointType.TELEPORTER;
	    this.setStyle("-fx-background-color: #f000ec;");
	    teleportTo = data;
	    this.setText(Character.toString(data));
	    break;
	}
	this.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		switch(map.brush) {
		case WALL:
		    Intialize('!', x, y, map);
		    break;
		case FREE:
		    Intialize('.', x, y, map);
		    break;
		case START:
		    Intialize('*', x, y, map);
		    break;
		case FINISH:
		    Intialize('$', x, y, map);
		    break;
		case TELEPORTER:
		    Intialize(Main.TeleField.getText().charAt(0), x, y, map);
		    break;
		}
		
	    }
	});
    }

    void PreprocessPoint(Point[][] contents, int sizeX, int sizeY) {
	// Store the two possible x movements
	for (int i = 0; i < 2; i++) {
	    if (x + dxy[i] >= 0 && x + dxy[i] < sizeX) {
		places2Go.add(contents[x + dxy[i]][y]);
	    }
	}
	// Store the two possible y movements
	for (int i = 0; i < 2; i++) {
	    if (y + dxy[i] >= 0 && y + dxy[i] < sizeY) {
		places2Go.add(contents[x][y + dxy[i]]);
	    }
	}

	// If its a teleporter then store all the possible teleportation locations
	if (type == PointType.TELEPORTER) {
	    for (int y = 0; y < sizeY; y++) {
		for (int x = 0; x < sizeX; x++) {
		    if (contents[x][y].teleportTo == this.teleportTo) {
			places2Go.add(contents[x][y]);
		    }
		}
	    }
	}
    }
}

//Types of points that any given point on the map can be
enum PointType {
    WALL, FREE, TELEPORTER, START, FINISH
}
