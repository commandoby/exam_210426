package by.techmeskills.figuresfx.drawutils;

import by.techmeskills.figuresfx.exceptions.UnknownFigureTypeException;
import by.techmeskills.figuresfx.exceptions.WrongShapeException;
import by.techmeskills.figuresfx.figures.*;
import javafx.scene.canvas.GraphicsContext;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Drawer<T extends Figure & Drawable> {
    private static final Logger log = Logger.getLogger(Drawer.class);
    private final List<T> figuresToDraw = new ArrayList<>();

    public Drawer(List<T> drawList) {
        figuresToDraw.addAll(drawList);
    }

    //отрисовываем фигуры
    public void draw(GraphicsContext graphicsContext) throws UnknownFigureTypeException {
        for (Figure figure : figuresToDraw) {
            if (figure != null) {
                try {
                    figure.draw(graphicsContext);
                } catch (WrongShapeException e) {
                    log.error(e);
                }
            } else {
                throw new UnknownFigureTypeException("Missing figure.");
            }
        }
    }
}

