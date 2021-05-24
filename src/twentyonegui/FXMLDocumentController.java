package twentyonegui;

import java.awt.Desktop.Action;
import java.io.File;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author aryal
 */
public class FXMLDocumentController implements Initializable {

    String[] picArray = {"2C.png", "2D.png", "2H.png", "2S.png",
        "3C.png", "3D.png", "3H.png", "3S.png",
        "4C.png", "4D.png", "4H.png", "4S.png",
        "5C.png", "5D.png", "5H.png", "5S.png",
        "6C.png", "6D.png", "6H.png", "6S.png",
        "7C.png", "7D.png", "7H.png", "7S.png",
        "8C.png", "8D.png", "8H.png", "8S.png",
        "9C.png", "9D.png", "9H.png", "9S.png",
        "10C.png", "10D.png", "10H.png", "10S.png",
        "JC.png", "JD.png", "JH.png", "JS.png",
        "QC.png", "QD.png", "QH.png", "QS.png",
        "KC.png", "KD.png", "KH.png", "KS.png",
        "AC.png", "AD.png", "AH.png", "AS.png"};

    @FXML
    private Label label;
    @FXML
    private Button hitButton, stayButton, startButton, clearButton, oneButton, elevenButton, nextButton, resetButton;
    @FXML
    private TextArea outputArea, moneyArea;
    @FXML
    private TextField betField;
    @FXML
    private ImageView DealerCard1, DealerCard2, PlayerCard, PlayerCard2;

    public Player you = new Player(0, 50), dealer = new Player(0, 0);

    public Random rand = new Random();

    public static int money;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleHitButtonAction(ActionEvent event) {

        playerturn();

        if (you.getPoints() > 21) {
            outputArea.appendText("You busted");
            moneyArea.setText(null);
            moneyArea.appendText(Integer.toString(you.getMoney()));
            hitButton.setDisable(true);
            stayButton.setDisable(true);
            nextButton.setDisable(false);

        } else if (you.getPoints() == 21) {
            outputArea.appendText("You Win!!!!!!!!!!");
            hitButton.setDisable(true);
            stayButton.setDisable(true);
            you.managemoney(money);
            moneyArea.setText(null);
            moneyArea.appendText(Integer.toString(you.getMoney()));
        }

    }

    @FXML
    private void handleOneButtonAction(ActionEvent event) {

        you.addPoints(1);
        oneButton.setDisable(true);
        elevenButton.setDisable(true);
        hitButton.setDisable(false);
        outputArea.appendText("Your total is: " + Integer.toString(you.getPoints()) + "\n");
    }

    @FXML
    private void handleElevenButtonAction(ActionEvent event) {
        you.addPoints(11);
        oneButton.setDisable(true);
        elevenButton.setDisable(true);
        hitButton.setDisable(false);
        outputArea.appendText("Your total is: " + Integer.toString(you.getPoints()) + "\n");
    }

    @FXML
    private void handleStayButtonAction(ActionEvent event) {
        stayButton.setDisable(true);
        hitButton.setDisable(true);
        nextButton.setDisable(false);

    }

    @FXML
    private void handleResetButtonAction(ActionEvent event) {
        you.setPoints(0);
        dealer.setPoints(0);
        startButton.setDisable(false);
        betField.setDisable(false);
        moneyArea.setText(null);
        moneyArea.appendText(Integer.toString(you.getMoney()));

        File file = new File("/D:/Downloads/Java/Pivs/PNG/red_back.png");

        Image image = new Image(file.toURI().toString());

        DealerCard1.setImage(image);
        DealerCard2.setImage(image);
        PlayerCard.setImage(image);
        PlayerCard2.setImage(image);

        hitButton.setDisable(true);
        stayButton.setDisable(true);
        outputArea.setText(null);

    }

    @FXML
    private void handleNextButtonAction(ActionEvent event) {

        dealerturn();

        if (dealer.getPoints() >= 17) {

            if (dealer.getPoints() > 21 && you.getPoints() > 21) {

                outputArea.appendText("Both of you busted");
                moneyArea.setText(null);
                moneyArea.appendText(Integer.toString(you.getMoney()));
                nextButton.setDisable(true);
                resetButton.setDisable(false);

            } else if (dealer.getPoints() > you.getPoints() && dealer.getPoints() < 21) {

                outputArea.appendText("Dealer Wins");
                nextButton.setDisable(true);
                resetButton.setDisable(false);
                you.managemoney(money * -1);
                moneyArea.setText(null);
                moneyArea.appendText(Integer.toString(you.getMoney()));

            } else if (dealer.getPoints() < 21 && you.getPoints() > 21) {
                outputArea.appendText("Dealer Wins");
                nextButton.setDisable(true);
                resetButton.setDisable(false);
                you.managemoney(money * -1);
                moneyArea.setText(null);
                moneyArea.appendText(Integer.toString(you.getMoney()));

            } else if (dealer.getPoints() == you.getPoints()) {
                outputArea.appendText("Both of you Tied");
                moneyArea.setText(null);
                moneyArea.appendText(Integer.toString(you.getMoney()));
                nextButton.setDisable(true);
                resetButton.setDisable(false);

            } else {

                outputArea.appendText("You win");
                nextButton.setDisable(true);
                resetButton.setDisable(false);
                you.managemoney(money);
                moneyArea.setText(null);
                moneyArea.appendText(Integer.toString(you.getMoney()));

            }

        } else if (dealer.getPoints() == 21) {
            outputArea.appendText("The Dealer wins");
            nextButton.setDisable(true);
            resetButton.setDisable(false);
            you.managemoney(money * -1);
            moneyArea.setText(null);
            moneyArea.appendText(Integer.toString(you.getMoney()));

        }
    }

    @FXML
    private void handleStartButtonAction(ActionEvent event) {

        if (!betField.getText().equals("")) {
            startButton.setDisable(true);
 
            moneyArea.setText(null);
            moneyArea.appendText(Integer.toString(you.getMoney()));

            outputArea.setText(null);

            money = Integer.parseInt(betField.getText());

            betField.setDisable(true);

            hitButton.setDisable(false);
            stayButton.setDisable(false);

            int r = rand.nextInt(52);

            String pic = picArray[r];

            File file = new File(pic);

            Image dc1 = new Image(file.toURI().toString());

            int d1 = drng(pic);

            dealer.addPoints(d1);

            DealerCard1.setImage(dc1);

            outputArea.appendText("\nDealer total is: " + Integer.toString(dealer.getPoints()) + "\n");

            initalplayerturn();

            clearButton.setDisable(false);
        } else {
            outputArea.appendText("You must Place a bet first");
        }

    }

    public void initalplayerturn() {

        int r = rand.nextInt(52);

        String pic = picArray[r];

        File file2 = new File(pic);

        Image pc = new Image(file2.toURI().toString());

        int u = prng(pic);

        you.addPoints(u);

        PlayerCard.setImage(pc);

        r = rand.nextInt(52);

        pic = picArray[r];

        file2 = new File(pic);

        pc = new Image(file2.toURI().toString());

        u = prng(pic);

        you.addPoints(u);

        PlayerCard2.setImage(pc);

        outputArea.appendText("Your total is: " + Integer.toString(you.getPoints()) + "\n");

    }

    @FXML
    private void handleClearButtonAction(ActionEvent event) {

        outputArea.setText(null);

        clearButton.setDisable(true);

    }

    public void dealerturn() {
        int r = rand.nextInt(52);

        String pic = picArray[r];

        File file = new File(pic);

        Image dc1 = new Image(file.toURI().toString());

        int d1 = drng(pic);

        dealer.addPoints(d1);

        DealerCard2.setImage(dc1);

        outputArea.appendText("\nDealer total is: " + Integer.toString(dealer.getPoints()) + "\n");

    }

    public int prng(String Card) {

        String x = Card.substring(0, 1);

        switch (x) {

            case "A":

                oneButton.setDisable(false);
                elevenButton.setDisable(false);
                hitButton.setDisable(true);

                outputArea.appendText("Press 1 or 11\n");

                return 0;

            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":

                return 10;
            case "J":

                return 10;
            case "Q":

                return 10;
            case "K":

                return 10;
        }
        return 10;

    }

    public int drng(String Card) {

        String x = Card.substring(0, 1);

        int r = rand.nextInt(2);
        switch (x) {

            case "A":

                if (r == 0) {
                    return 1;
                } else {
                    return 11;
                }

            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":

                return 10;
            case "J":

                return 10;
            case "Q":

                return 10;
            case "K":

                return 10;
        }
        return 10;

    }

    public void playerturn() {

        int r = rand.nextInt(52);

        String pic = picArray[r];

        File file2 = new File(pic);

        Image pc = new Image(file2.toURI().toString());

        int u = prng(pic);

        you.addPoints(u);

        PlayerCard2.setImage(pc);

        outputArea.appendText("Your total is: " + Integer.toString(you.getPoints()) + "\n");

    }

}
