package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.src.config.Config;
import sample.src.custom.AlertCustom;

import java.util.Random;

public class MainActivityController {
    //   control
    public Spinner spinner;
    public Label lbmyCapital;
    public Label lbcomputerCapital;
    public Button btnDivide;
    public Button btnResult;
    //   ImageView control
    public ImageView imgH1;
    public ImageView imgH2;
    public ImageView imgH3;
    public ImageView imgH4;
    public ImageView imgH5;
    public ImageView imgH6;
    String []imgUrl = Config.HEARTS;
    //    money
    public int myCapital = 100;
    public int computerCapital = 100;
    //    bets
    private int betting = 10;
    private Random random = new Random();
    //    score of play card
    private static int card1, card2, card3, card4, card5, card6;
    private static int cardType = 0; // 0 = HEARTS, 1 = DIAMONDS, 2 = CLUBS, 3 = SPADES;
    //    the point of machine and you
    private static int computerScore;
    private static int myScore;
    //  closing bets
    public void divideClick(ActionEvent actionEvent) {
        //      get number cards
        btnResult.setDisable(false);
        card1 = random.nextInt(13);
        card2 = random.nextInt(13);
        card3 = random.nextInt(13);
        card4 = random.nextInt(13);
        card5 = random.nextInt(13);
        card6 = random.nextInt(13);
        System.out.println(card1 + " ?");
        System.out.println(card2 + " ?");
        System.out.println(card3 + " ?");
        System.out.println(card4 + " ?");
        System.out.println(card5 + " ?");
        System.out.println(card6 + " ?");
        // You have not bet
        if (betting == 0){
            String notBet = "You have not bet";
            AlertCustom.showError(notBet);
        }
        // Not have enough money
        if (betting > myCapital){
            String error = "You do not have enough money to bet " + betting;
            AlertCustom.showError(error);
        } else {
            backCard(imgH1); backCard(imgH2); backCard(imgH3); backCard(imgH4); backCard(imgH5); backCard(imgH6);
        }

    }
    // Open Card
    public void playClick(ActionEvent actionEvent) {
        // You have not bet
        if (betting == 0){
            String notBet = "You have not bet";
            AlertCustom.showError(notBet);
        }
        //  Not enough money
        if (betting > myCapital){
            String error = "You do not have enough money to bet " + betting;
            AlertCustom.showError(error);
        } else {
            play(card1, imgH1);
            play(card2, imgH2);
            play(card3, imgH3);
            play(card4, imgH4);
            play(card5, imgH5);
            play(card6, imgH6);
            scoring();
        }
        //btnResult.setDisable(false);
    }
    // scoring and compare
    private void scoring() {

        myScore = (sumScore(card1) + sumScore(card2) + sumScore(card3)) % 10;
        computerScore = (sumScore(card4) + sumScore(card5) + sumScore(card6)) % 10;
        if (card1 == 0 && card2 == 0 && card3 == 0 && card4 == 0 && card5 == 0 && card6 == 0){
            AlertCustom.showError("You do not close the bet (press the card to close the stake)");
        } else {
            // >
            if (myScore > computerScore) {
                myCapital += betting;
                computerCapital -= betting;
                lbmyCapital.setText("Capital : " + myCapital + "$");
                lbcomputerCapital.setText("Capital : " + computerCapital + "$");
                String title = "Score";
                String myScoreStr = "My score: " + myScore;
                String computerScoreStr = "Computer score: " + computerScore;
                String contentResult = myScoreStr + "\n" + computerScoreStr + "\n* YOU WIN *";
                AlertCustom.showInformationScore(title, contentResult, btnResult);
            }
            // <
            if (myScore < computerScore){
                myCapital -= betting;
                computerCapital += betting;
                lbmyCapital.setText("Capital : " + myCapital + "$");
                lbcomputerCapital.setText("Capital : " + computerCapital + "$");
                String title = "Score";
                String myScoreStr = "My score: " + myScore;
                String computerScoreStr = "Computer score: " + computerScore;
                String contentResult = myScoreStr + "\n" + computerScoreStr + "\n* COMPUTER WIN *";
                AlertCustom.showInformationScore(title, contentResult, btnResult);
            }
            // ==
            if (myScore == computerScore) {
                lbmyCapital.setText("Capital : " + myCapital + "$");
                lbcomputerCapital.setText("Capital : " + computerCapital + "$");
                String title = "Score";
                String myScoreStr = "My score: " + myScore;
                String computerScoreStr = "Computer score: " + computerScore;
                String contentResult = myScoreStr + "\n" + computerScoreStr + "\n* NO ONE WINS *";
                AlertCustom.showInformationScore(title, contentResult, btnResult);
            }
            // You Lose
            if (myCapital <= 0) {
                computerCapital = 100;
                myCapital = 100;
                lbmyCapital.setText("Capital : " + myCapital + "$");
                lbcomputerCapital.setText("Capital : " + computerCapital + "$");
                betting = 0;
                spinner.getValueFactory().setValue(10);
                backCard(imgH1); backCard(imgH2); backCard(imgH3); backCard(imgH4); backCard(imgH5); backCard(imgH6);
                AlertCustom.showInformationScore("Result!!", "COMPUTER WIN" + "\n *FINISH*", btnResult);
            }
            // You Win
            if (computerCapital <= 0) {
                computerCapital = 100;
                myCapital = 100;
                lbmyCapital.setText("Capital : " + myCapital + "$");
                lbcomputerCapital.setText("Capital : " + computerCapital + "$");
                betting = 0;
                spinner.getValueFactory().setValue(10);
                backCard(imgH1); backCard(imgH2); backCard(imgH3); backCard(imgH4); backCard(imgH5); backCard(imgH6);
                AlertCustom.showInformationScore("Result!!", "YOU WIN" + "\n *FINISH*", btnResult);
            }
        }

        spinner.getValueFactory().setValue(10);
    }

    /**
     * Sum score
     * @param card
     * @return
     */
    private int sumScore(int card) {
        if(card <= 9) {
            return card + 1;
        } else {
            return 10;
        }
    }

    /**
     * open play card
     * @param number
     * @param img
     */
    private void play(int number, ImageView img) {
        switch (number){
            case 0 :
                imgUrl = typeCard();
                img.setImage(new Image(imgUrl[0]));
                break;
            case 1 :
                imgUrl = typeCard();
                img.setImage(new Image(imgUrl[1]));
                break;
            case 2 :
                imgUrl = typeCard();
                img.setImage(new Image(imgUrl[2]));
                break;
            case 3 :
                imgUrl = typeCard();
                img.setImage(new Image(imgUrl[3]));
                break;
            case 4 :
                imgUrl = typeCard();
                img.setImage(new Image(imgUrl[4]));
                break;
            case 5 :
                imgUrl = typeCard();
                img.setImage(new Image(imgUrl[5]));
                break;
            case 6 :
                imgUrl = typeCard();
                img.setImage(new Image(imgUrl[6]));
                break;
            case 7 :
                imgUrl = typeCard();
                img.setImage(new Image(imgUrl[7]));
                break;
            case 8 :
                imgUrl = typeCard();
                img.setImage(new Image(imgUrl[8]));
                break;
            case 9 :
                imgUrl = typeCard();
                img.setImage(new Image(imgUrl[9]));
                break;
            case 10 :
                imgUrl = typeCard();
                img.setImage(new Image(imgUrl[10]));
                break;
            case 11 :
                imgUrl = typeCard();
                img.setImage(new Image(imgUrl[11]));
                break;
            case 12 :
                imgUrl = typeCard();
                img.setImage(new Image(imgUrl[12]));
                    break;
            default:
                imgUrl = typeCard();
                img.setImage(new Image(imgUrl[9]));
                    break;
        }
    }

    /**
     * get number bets
     * @param mouseEvent
     */
    public void spinnerClick(MouseEvent mouseEvent) {
        betting = (int) spinner.getValue();
    }

    /**
     * Rule button events
     * @param actionEvent
     */
    public void inforClick(ActionEvent actionEvent) {
        String title = "Rule";
        String content = "At the beginning of each player's 100 bets," +
                " the player bets the number of bets before the cards are dealt." +
                " (If not, the bet amount will be 10)." +
                " If you win," +
                " you will be added to the amount you have bet if you lose the amount you have bet on your game capital ending when your capital or machine is zero.";
        AlertCustom.showInformation(title, content);
    }

    /**
     * Back Card
     */
    public void backCard(ImageView imageView) {
            imageView.setImage(new Image(Config.BEHIND_CARDS));
    }

    /**
     * TYPE CARD
     */
    public String[] typeCard() {
        cardType = 0;
        cardType = random.nextInt(4);
        System.out.println(cardType + " ??");
        switch (cardType){
            case 0:
                return Config.HEARTS;
            case 1:
                return Config.DIAMONDS;
            case 2:
                return Config.CLUBS;
            case 3:
                return Config.SPADES;
            default:
                return Config.HEARTS;
        }
    }
}
