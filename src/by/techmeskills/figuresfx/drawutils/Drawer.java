package by.techmeskills.figuresfx.drawutils;

import by.techmeskills.figuresfx.exceptions.UnknownFigureTypeException;
import by.techmeskills.figuresfx.figures.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class Drawer<T extends Figure & Drawable> {
    private final Queue<T> figures;
    private final Random random;

    public Drawer() {
        figures = new ArrayDeque<>();
        random = new Random(System.currentTimeMillis());
    }

    //добавляем фигуру в коллекцию
    public void addFigure(T figure) {
        if (figures.size() > 30) {
            figures.poll();
        }
        figures.offer(figure);
    }

    //генерируем фигуру
    public Figure createdFigure(double x, double y) throws UnknownFigureTypeException {
        Figure figure = null;
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
                figure = new Star(x, y, 2 + random.nextInt(3), rgb, 10 + random.nextInt(30), 15 + random.nextInt(25), 5 + random.nextInt(10));
                break;
            case Figure.FIGURE_TYPE_BEAN:
                rgb = Color.rgb(200 + random.nextInt(50), random.nextInt(100), 200 + random.nextInt(50));
                figure = new Bean(x, y, 2 + random.nextInt(3), rgb, 30 + random.nextInt(25), 25 + random.nextInt(30), 30 + random.nextInt(45));
                break;
            default:
                throw new UnknownFigureTypeException("Missing figure at index " + randomFigureType);
        }
        return figure;
    }

    //отрисовываем фигуры
    public void draw(GraphicsContext graphicsContext) {
        for (Figure figure : figures) {
            figure.draw(graphicsContext);
        }
    }
}

