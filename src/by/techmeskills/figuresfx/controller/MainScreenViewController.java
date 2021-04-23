package by.techmeskills.figuresfx.controller;

import by.techmeskills.figuresfx.drawutils.Drawer;
import by.techmeskills.figuresfx.exceptions.UnknownFigureTypeException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenViewController implements Initializable {
    Drawer drawer;

    @FXML
    private Canvas canvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Application initialized.");
        drawer = new Drawer();
    }

    //очищаем и перерисовываем окно
    private void repaint() {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawer.draw(canvas.getGraphicsContext2D());
    }

    @FXML //действия на клик мыши
    private void onMouseClicked(MouseEvent mouseEvent) {
        try {
            drawer.addFigure(drawer.createdFigure(mouseEvent.getX(), mouseEvent.getY()));
            repaint();
        } catch (UnknownFigureTypeException e) {
            System.out.println(e.toString());
        }
    }
}
