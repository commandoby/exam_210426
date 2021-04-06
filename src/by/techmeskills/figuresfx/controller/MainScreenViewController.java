package by.techmeskills.figuresfx.controller;

import by.techmeskills.figuresfx.figures.Circle;
import by.techmeskills.figuresfx.figures.Figure;
import by.techmeskills.figuresfx.figures.Rectangle;
import by.techmeskills.figuresfx.figures.Triangle;
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
        Color rgb;
        switch (random.nextInt(3)) {
            case Figure.FIGURE_TYPE_CIRCLE:
                rgb = Color.rgb(random.nextInt(100), 200 + random.nextInt(50), random.nextInt(100));
                figure = new Circle(x, y, 1 + random.nextInt(3), rgb, 30 + random.nextInt(25));
                break;
            case Figure.FIGURE_TYPE_RECTANGLE:
                rgb = Color.rgb(random.nextInt(100), random.nextInt(100), 200 + random.nextInt(50));
                figure = new Rectangle(x, y, 1 + random.nextInt(3), rgb, 30 + random.nextInt(30), 30 + random.nextInt(70));
                break;
            case Figure.FIGURE_TYPE_TRIANGLE:
                rgb = Color.rgb(200 + random.nextInt(50), random.nextInt(100), random.nextInt(100));
                figure = new Triangle(x, y, 1 + random.nextInt(3), rgb, 30 + random.nextInt(40), 30 + random.nextInt(30));
                break;
            default:
                System.out.println("Uncnoun figure type!");
        }
        return figure;
    }

    private void repaint() {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Figure figure : figures) {
            figure.draw(canvas.getGraphicsContext2D());
        }
    }

    @FXML
    private void onMauseClicked(MouseEvent mouseEvent) {
        addFigure(createdFigure(mouseEvent.getX(), mouseEvent.getY()));
        repaint();
    }
}
