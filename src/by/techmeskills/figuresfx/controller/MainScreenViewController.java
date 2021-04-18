package by.techmeskills.figuresfx.controller;

import by.techmeskills.figuresfx.figures.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.*;

public class MainScreenViewController implements Initializable {
    private Queue<Figure> figures;
    private Random random;

    @FXML
    private Canvas canvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Application initialized.");
        figures = new ArrayDeque<>();
        random = new Random(System.currentTimeMillis());
    }

    private void addFigure(Figure figure) {
        if (figures.size() > 30) {
            figures.poll();
        }
        figures.offer(figure);
    }

    private Figure createdFigure(double x, double y) {
        Figure figure = null;
        Color rgb;
        switch (random.nextInt(4)) {
            case Figure.FIGURE_TYPE_CIRCLE:
                rgb = Color.rgb(random.nextInt(100), 200 + random.nextInt(50), random.nextInt(100));
                figure = new Circle(x, y, 2 + random.nextInt(3), rgb, 30 + random.nextInt(25));
                break;
            case Figure.FIGURE_TYPE_RECTANGLE:
                rgb = Color.rgb(random.nextInt(100), random.nextInt(100), 200 + random.nextInt(50));
                figure = new Rectangle(x, y, 2 + random.nextInt(3), rgb, 30 + random.nextInt(30), 30 + random.nextInt(70));
                break;
            case Figure.FIGURE_TYPE_TRIANGLE:
                rgb = Color.rgb(200 + random.nextInt(50), random.nextInt(100), random.nextInt(100));
                figure = new Triangle(x, y, 2 + random.nextInt(3), rgb, 30 + random.nextInt(40), 30 + random.nextInt(30));
                break;
            case Figure.FIGURE_TYPE_STAR:
                rgb = Color.rgb(200 + random.nextInt(50), 150 + random.nextInt(50), random.nextInt(100));
                figure = new Star(x, y, 2 + random.nextInt(3), rgb, 15 + random.nextInt(30), 20 + random.nextInt(25), 5 + random.nextInt(10));
                break;
            default:
                System.out.println("Unknown figure type!");
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
    private void onMouseClicked(MouseEvent mouseEvent) {
        addFigure(createdFigure(mouseEvent.getX(), mouseEvent.getY()));
        repaint();
    }
}
