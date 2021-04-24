package by.techmeskills.figuresfx.drawutils;

import by.techmeskills.figuresfx.exceptions.WrongShapeException;
import javafx.scene.canvas.GraphicsContext;

public interface Drawable {
    void draw(GraphicsContext graphicsContext) throws WrongShapeException;
}
