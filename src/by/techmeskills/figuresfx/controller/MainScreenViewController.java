package by.techmeskills.figuresfx.controller;

import by.techmeskills.figuresfx.figures.Circle;
import by.techmeskills.figuresfx.figures.Figure;
import by.techmeskills.figuresfx.figures.Rectangle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainScreenViewController implements Initializable {
    private Figure[] figures;
    private Random random;

    @FXML
    private Canvas canvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Application initialized.");
        figures = new Figure[1];
        random = new Random(System.currentTimeMillis());
    }

    private void addFigure(Figure figure) {
        if (figures[figures.length - 1] == null) {
            figures[figures.length - 1] = figure;
            return;
        }

        Figure[] tmp = new Figure[figures.length + 1];
        int index = 0;
        for (; index < figures.length; index++) {
            tmp[index] = figures[index];
        }
        tmp[index] = figure;
        figures = tmp;
    }

    private Figure createdFigure(double x, double y) {
        Figure figure = null;
        switch (random.nextInt(3)) {
            case Figure.FIGURE_TYPE_CIRCLE:
                figure = new Circle(x, y, random.nextInt(3), Color.GREEN, random.nextInt(50));
                break;
            case Figure.FIGURE_TYPE_RECTANGLE:
                figure = new Rectangle(x, y, random.nextInt(3), Color.BLUE, random.nextInt(60), random.nextInt(100));
                break;
            case Figure.FIGURE_TYPE_TRIANGLE:
                break;
            default:
                System.out.println("Uncnoun figure type!");
        }
        return figure;
    }

    private void repaint() {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for(Figure figure : figures) {
            figure.draw(canvas.getGraphicsContext2D());
        }
    }

    @FXML
    private void onMauseClicked(MouseEvent mouseEvent) {
        addFigure(createdFigure(mouseEvent.getX(), mouseEvent.getY()));
        repaint();
    }
}
