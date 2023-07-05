package com.example.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class HelloController {


    @FXML
    private Button buttonTres;

    @FXML
    private Button buttonClean;

    @FXML
    private Button buttonRestar;

    @FXML
    private Button buttonANS;

    @FXML
    private Button buttonDos;

    @FXML
    private Button buttonPorcentaje;

    @FXML
    private Button buttonCuatro;

    @FXML
    private Button buttonNueve;

    @FXML
    private Button buttonCinco;

    @FXML
    private Button buttonSeis;

    @FXML
    private Button buttonOcho;

    @FXML
    private Button buttonSumar;

    @FXML
    private Button buttonUno;

    @FXML
    private Button buttonIgual;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button ButtonCero;

    @FXML
    private Button buttonPunto;

    @FXML
    private Button buttonSiete;

    @FXML
    private Button buttonMultiplicar;

    @FXML
    private Button buttonDivision;

    @FXML
    
    private TextField textFieldNumbers;

    @FXML
    private TextArea textAreaHistory;

    private Boolean operationOn = true;
    private double lastOperation = 0;


    public void cleanScreen(){
        textFieldNumbers.setText("");
        operationOn = true;
    }

    public void deleteValue(){
        if (!(textFieldNumbers.getText().length() == 0)) {
            textFieldNumbers.setText(textFieldNumbers.getText().substring(0, textFieldNumbers.getText().length()-1));
        }
    }

    public void getLastResult(){
        if (!(lastOperation == 0)){
            textFieldNumbers.setText(textFieldNumbers.getText() + lastOperation);
        }

    }


    public void addValue(ActionEvent actionEvent) {
        textFieldNumbers.setText(textFieldNumbers.getText() + ((Button) actionEvent.getSource()).getText());
        operationOn = true;
    }

    public void addOperation(ActionEvent actionEvent) {
        if (operationOn) {
            textFieldNumbers.setText(textFieldNumbers.getText() + ((Button) actionEvent.getSource()).getText());
            operationOn = false;
        }
    }



    public void makeOperation() {
        String operationS = textFieldNumbers.getText();

        Context rhinoContext = Context.enter();
        try {
            Scriptable scope = rhinoContext.initStandardObjects();
            Object result = rhinoContext.evaluateString(scope, operationS.replaceAll("x", "*"), "JavaScript", 1, null);

            textFieldNumbers.setText(result.toString());
            textAreaHistory.setText(operationS + " = " + textFieldNumbers.getText() + "\n" + textAreaHistory.getText());
            lastOperation = Double.parseDouble(textFieldNumbers.getText());
        } catch (Exception e) {
            textFieldNumbers.setText("Error");
        } finally {
            Context.exit();
        }
    }
}