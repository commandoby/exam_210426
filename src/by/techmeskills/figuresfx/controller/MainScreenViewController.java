package by.techmeskills.figuresfx.controller;

import by.techmeskills.figuresfx.drawutils.Drawer;
import by.techmeskills.figuresfx.exceptions.*;
import by.techmeskills.figuresfx.figures.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

public class MainScreenViewController implements Initializable {
    private static final Logger log = Logger.getLogger(MainScreenViewController.class);
    private ArrayDeque<Figure> figures;
    private Random random;

    @FXML
    private Canvas canvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("Application initialized.");
        figures = new ArrayDeque<>();
        random = new Random(System.currentTimeMillis());
    }

    //добавляем фигуру в коллекцию
    private void addFigure(Figure figure) {
        if (figures.size() > 30) {
            figures.poll();
            log.debug("The collection is overflowing.");
        }
        figures.offer(figure);
    }

    //генерируем фигуру
    private Figure createdFigure(double x, double y) throws UnknownFigureTypeException {
        Figure figure;
        Color rgb;
        int randomFigureType = random.nextInt(5);
        switch (randomFigureType) {
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
                figure = new Star(x, y, 2 + random.nextInt(3), rgb, 10 + random.nextInt(30), 15 + random.nextInt(25), 4 + random.nextInt(11));
                break;
            case Figure.FIGURE_TYPE_BEAN:
                rgb = Color.rgb(200 + random.nextInt(50), random.nextInt(100), 200 + random.nextInt(50));
                figure = new Bean(x, y, 2 + random.nextInt(3), rgb, 10 + random.nextInt(45), 25 + random.nextInt(30), 30 + random.nextInt(45));
                break;
            default:
                throw new UnknownFigureTypeException("Missing figure at index " + randomFigureType + ".");
        }
        return figure;
    }

    //очищаем и перерисовываем окно
    private void repaint() {
        try {
            canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            Drawer drawer = new Drawer(Arrays.asList(figures.toArray()));
            drawer.draw(canvas.getGraphicsContext2D());
            log.trace("Window repaint.");
        } catch (UnknownFigureTypeException e) {
            log.error(e);
        }
    }

    @FXML //действия на клик мыши
    private void onMouseClicked(MouseEvent mouseEvent) {
        try {
            addFigure(createdFigure(mouseEvent.getX(), mouseEvent.getY()));
            repaint();
        } catch (UnknownFigureTypeException e) {
            log.error(e);
        }
    }
}
