package com.example.sampleproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class CardController {
	Pane cardPane;
	
	GameWindowController gameWindow;

    @FXML
    private Text nominal;

    @FXML
    private Text mask;

    @FXML
    private ImageView imgBuffer;

    @FXML
    private Pane imgPane;
    


    public void setCardParameters(String nominal, String mask, GameWindowController gameWindow, Pane cardPane) throws FileNotFoundException {
        String[] nominals = {"6", "7", "8", "9", "10", "Валет", "Дама", "Король", "Туз"};
        String[] masks = {"Пика", "Сердце", "Бубны", "Черви"};


        int nominalIndex = (int) (Math.random() * nominals.length);
        int maskIndex = (int) (Math.random() * masks.length);


        String randomNominal = nominals[nominalIndex];
        String randomMask = masks[maskIndex];


        this.nominal.setText(randomNominal);
        this.mask.setText(randomMask);

    	File img = new File("C:\\Users\\User\\IdeaProjects\\SampleProject\\src\\main\\resources\\com\\example\\sampleproject\\EntrancePicture.jpg");
    	InputStream isImage = (InputStream) new FileInputStream(img);
    	imgBuffer = new ImageView(new Image(isImage));
    	imgBuffer.setFitWidth(100);
    	imgBuffer.setPreserveRatio(true);
        //imgBuffer.setSmooth(true);

    	//cardPane.getChildren().add(imgBuffer);

    	//imgPane = new Pane();
    	imgPane.getChildren().setAll(imgBuffer);

    }

    public String getNominal() {
    	return this.nominal.getText();
    }

    public String getMask() {
    	return this.mask.getText();
    }
    
    @FXML
    void replaceCardToTable(MouseEvent event) throws IOException {
    	gameWindow.addCardOnTable(this);
    	cardPane.setVisible(false);
    	//FlowPane firstPlayerPane = (FlowPane) cardPane.getParent();
    	//firstPlayerPane.getChildren().remove(cardPane);
    }
}
