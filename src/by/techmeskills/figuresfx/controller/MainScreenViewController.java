package by.techmeskills.figuresfx.controller;

import by.techmeskills.figuresfx.drawutils.Drawer;
import by.techmeskills.figuresfx.exceptions.*;
import by.techmeskills.figuresfx.figures.*;
import by.techmeskills.figuresfx.saveload.SaveLoad;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.*;

public class MainScreenViewController implements Initializable {
    private static final Logger log = Logger.getLogger(MainScreenViewController.class);
    private ArrayDeque<Figure> figures;
    private Random random;
    private ArrayList<SaveLoad> saveLoad;
    private int numberLoad;
    private boolean[] figureTypeActive;

    @FXML
    private Canvas canvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("Application initialized.");
        figures = new ArrayDeque<>();
        random = new Random(System.currentTimeMillis());
        saveLoad = new ArrayList<>();
        numberLoad = 0;
        figureTypeActive = new boolean[]{true, true, true, true, true};
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
        CreatedFigureType[] figureType = new CreatedFigureType[5];
        figureType[Figure.FIGURE_TYPE_CIRCLE] = () -> {
            Color rgb = Color.rgb(random.nextInt(100), 200 + random.nextInt(50), random.nextInt(100));
            return new Circle(x, y, 2 + random.nextInt(3), rgb, 30 + random.nextInt(25));
        };
        figureType[Figure.FIGURE_TYPE_RECTANGLE] = () -> {
            Color rgb = Color.rgb(random.nextInt(100), random.nextInt(100), 200 + random.nextInt(50));
            return new Rectangle(x, y, 2 + random.nextInt(3), rgb, 30 + random.nextInt(30), 30 + random.nextInt(70));
        };
        figureType[Figure.FIGURE_TYPE_TRIANGLE] = () -> {
            Color rgb = Color.rgb(200 + random.nextInt(50), random.nextInt(100), random.nextInt(100));
            return new Triangle(x, y, 2 + random.nextInt(3), rgb, 30 + random.nextInt(40), 30 + random.nextInt(30));
        };
        figureType[Figure.FIGURE_TYPE_STAR] = () -> {
            Color rgb = Color.rgb(200 + random.nextInt(50), 150 + random.nextInt(50), random.nextInt(100));
            return new Star(x, y, 2 + random.nextInt(3), rgb, 10 + random.nextInt(30), 15 + random.nextInt(25), 5 + random.nextInt(10));
        };
        figureType[Figure.FIGURE_TYPE_BEAN] = () -> {
            Color rgb = Color.rgb(200 + random.nextInt(50), random.nextInt(100), 200 + random.nextInt(50));
            return new Bean(x, y, 2 + random.nextInt(3), rgb, 10 + random.nextInt(45), 25 + random.nextInt(30), 30 + random.nextInt(45));
        };
        ArrayList<CreatedFigureType> figureTypeList = new ArrayList<>();
        for (int i = 0; i < figureTypeActive.length; i++) {
            if (figureTypeActive[i]) figureTypeList.add(figureType[i]);
        }
        if (!figureTypeList.isEmpty()) {
            return figureTypeList.get(random.nextInt(figureTypeList.size())).figureType();
        } else {
            throw new UnknownFigureTypeException("No figures selected.");
        }
    }

    //очищаем и перерисовываем окно
    private void repaint() {
        try {
            canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            Drawer<Figure> drawer = new Drawer(Arrays.asList(figures.toArray()));
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

    //кнопка очистки
    public void buttonAction() {
        figures = new ArrayDeque<>();
        repaint();
        log.info("Window cleared.");
    }

    //кнопка сохранения
    public void saveAction() {
        try {
            saveLoad.add(new SaveLoad(figures));
            log.info("Saved.");
        } catch (SaveLoadException e) {
            log.error(e);
        }
    }

    //кнопка загрузки
    public void loadAction() {
        try {
            if (!saveLoad.isEmpty()) {
                figures = new ArrayDeque<>();
                figures.addAll(saveLoad.get(numberLoad).load());
                log.info("Loaded # " + numberLoad);
                if (((saveLoad.size() - 1) == numberLoad)) {
                    numberLoad = 0;
                } else {
                    numberLoad++;
                }
                repaint();
            } else {
                throw new SaveLoadException("There is no save.");
            }
        } catch (SaveLoadException e) {
            log.error(e);
        }
    }

    //переключатель круга
    public void circleAction() {
        figureTypeActive[Figure.FIGURE_TYPE_CIRCLE] = !figureTypeActive[Figure.FIGURE_TYPE_CIRCLE];
    }

    //переключатель прямоугольника
    public void rectangleAction() {
        figureTypeActive[Figure.FIGURE_TYPE_RECTANGLE] = !figureTypeActive[Figure.FIGURE_TYPE_RECTANGLE];
    }

    //переключатель треугольника
    public void triangleAction() {
        figureTypeActive[Figure.FIGURE_TYPE_TRIANGLE] = !figureTypeActive[Figure.FIGURE_TYPE_TRIANGLE];
    }

    //переключатель звезды
    public void starAction() {
        figureTypeActive[Figure.FIGURE_TYPE_STAR] = !figureTypeActive[Figure.FIGURE_TYPE_STAR];
    }

    //переключатель боба
    public void beanAction() {
        figureTypeActive[Figure.FIGURE_TYPE_BEAN] = !figureTypeActive[Figure.FIGURE_TYPE_BEAN];
    }
}
