package com.example.assignment2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

import java.util.HashMap;
import java.util.Map;

public class HelloController {
    public Button btnA, btnB, btnC, btnD, btnE, btnF, btnG, btnH, btnI, btnJ, btnK, btnL, btnM, btnN, btnO, btnP, btnQ, btnR, btnS, btnT, btnU, btnV, btnW, btnX, btnY, btnZ;
    public Label lblA, lblB, lblC, lblD, lblE, lblF, lblG, lblH, lblI, lblJ, lblK, lblL, lblM, lblN, lblO, lblP, lblQ, lblR, lblS, lblT, lblU, lblV, lblW, lblX, lblY, lblZ;
    public Label totalPointsValue;
    public TextField word;
    public Button submit;
    public TextArea textarea;
    static int[] values = {9, 2, 2, 4, 12, 2, 3, 2, 8, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
    static Map<Character, Integer> mapchar = new HashMap<>();
    static int[] points = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    static int temp;

    public ScrabbleModel scrabbleModel;

    public HelloController(ScrabbleModel scrabbleModel) {
        this.scrabbleModel = scrabbleModel;
    }

    public void showMessage(String title, String message) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText("Message");
        dialog.setGraphic(null);
        dialog.initStyle(StageStyle.UTILITY);

        Text messageText = new Text(message);
        StackPane stackPane = new StackPane(messageText);
        dialog.getDialogPane().setContent(stackPane);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.showAndWait();
    }

    @FXML
    protected void keysButton(ActionEvent event) {
        Button btn = (Button) event.getSource();
        int a = btn.getText().toLowerCase().charAt(0);
        if (values[a - 97] > 0) {
            word.setText(word.getText() + btn.getText());
        }
    }

    protected boolean checkError(String output) {
        boolean flag = true;
        int[] temp = values.clone();

        for (int i = 0; i < output.length(); i++) {
            int a = output.charAt(i);
            if (--temp[a - 97] < 0) {
                showMessage("Word Point", "Please enter a valid character as " + output.charAt(i) + " is full");
                return false;
            }
        }
        return flag;
    }

    @FXML
    protected void buttonClick() {
        Button[] totalButton = {btnA, btnB, btnC, btnD, btnE, btnF, btnG, btnH, btnI, btnJ, btnK, btnL, btnM, btnN, btnO, btnP, btnQ, btnR, btnS, btnT, btnU, btnV, btnW, btnX, btnY, btnZ};
        Label[] totalLabel = {lblA, lblB, lblC, lblD, lblE, lblF, lblG, lblH, lblI, lblJ, lblK, lblL, lblM, lblN, lblO, lblP, lblQ, lblR, lblS, lblT, lblU, lblV, lblW, lblX, lblY, lblZ};
        String output = word.getText();
        output = output.toLowerCase();

        if (checkError(output)) {
            int totalPoints = Integer.parseInt(totalPointsValue.getText());
            int point = 0;
            for (int i = 0; i < output.length(); i++) {
                int a = output.charAt(i);
                values[a - 97]--;
                if (values[a - 97] == 0) {
                    totalButton[a - 97].setDisable(true);
                    totalLabel[a - 97].setText("" + values[a - 97]);
                    totalPoints += points[a - 97];
                    point += points[a - 97];
                } else {
                    if (values[a - 97] >= 0) {
                        totalLabel[a - 97].setText("" + values[a - 97]);
                        totalPoints += points[a - 97];
                        point += points[a - 97];
                    }
                }
            }
            if (temp == 0) {
                showMessage("Word Point", "" + point);
                totalPointsValue.setText("" + totalPoints);
                textarea.setText(textarea.getText() + " " + output);
            } else {
                showMessage("Word Point", "" + point);
                totalPointsValue.setText("" + totalPoints);
                textarea.setText(textarea.getText() + " , " + output);
            }
            temp++;
            word.setText("");
        }
    }
}
