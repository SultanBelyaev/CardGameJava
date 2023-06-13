package com.example.sampleproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GameWindowController {

    @FXML
    private Button addCardButton;

    @FXML
    private GridPane deskAttackCardPane;

    @FXML
    private GridPane deskAnswerCardPane;

    @FXML
    private ScrollPane firstPlayerScroll;

    @FXML
    private ScrollPane secondPlayerScroll;

    @FXML
    private ScrollPane ColodaPane;

    @FXML
    private FlowPane firstPlayerPane;

    @FXML
    private FlowPane secondPlayerPane;

    @FXML
    private FlowPane ColodaFlowPane;

    @FXML
    void addCard(ActionEvent event) throws IOException, InterruptedException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Card.fxml"));

        Pane newPane = (Pane) loader.load();
        CardController cardController = loader.getController();
        List existingCards = new ArrayList();
        int cardNumber = 0;
        while (existingCards.contains(cardNumber)) { // пока найдена дублирующая карта
            cardNumber = (int) (Math.random() * 10 + 1); // генерируем новую карту
        }

        existingCards.add(cardNumber);
        if (firstPlayerPane.getChildren().size() < 6) {
            cardController.setCardParameters(cardController.getNominal(), cardController.getMask(), this, newPane);
            firstPlayerPane.getChildren().add(newPane);
            firstPlayerScroll = new ScrollPane();
            firstPlayerScroll.setContent(firstPlayerPane);

            if (firstPlayerPane.getChildren().size() == 6 && secondPlayerPane.getChildren().size() < 6) {
                while (secondPlayerPane.getChildren().size() < 6) {
                    cardController.setCardParameters(cardController.getNominal(), cardController.getMask(), this, newPane);
                    secondPlayerPane.getChildren().add(newPane);
                    secondPlayerScroll = new ScrollPane();
                    secondPlayerScroll.setContent(secondPlayerPane);
                }
            }
        } else if (secondPlayerPane.getChildren().size() < 6) {
            cardController.setCardParameters(cardController.getNominal(), cardController.getMask(), this, newPane);
            secondPlayerPane.getChildren().add(newPane);
            secondPlayerScroll = new ScrollPane();
            secondPlayerScroll.setContent(secondPlayerPane);
        } else if (firstPlayerPane.getChildren().size() == 6 && secondPlayerPane.getChildren().size() == 6) {
            while (ColodaFlowPane.getChildren().size() < 1) {
                cardController.setCardParameters(cardController.getNominal(), cardController.getMask(), this, newPane);
                ColodaFlowPane.getChildren().add(newPane);
                ColodaPane = new ScrollPane();
                ColodaPane.setContent(ColodaFlowPane);
            }
        }
    }


    public void addCardOnTable(CardController card) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Card.fxml"));

    	Pane newPane = (Pane)loader.load();

    	CardController cardController = loader.getController();
    	cardController.setCardParameters(card.getNominal(), card.getMask(), this, newPane);

    	deskAttackCardPane.add(newPane, deskAttackCardPane.getChildren().size(), 0);
    }
}
