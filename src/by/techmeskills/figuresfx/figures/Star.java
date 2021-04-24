package by.techmeskills.figuresfx.figures;

import by.techmeskills.figuresfx.drawutils.Drawable;
import by.techmeskills.figuresfx.exceptions.WrongShapeException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Objects;

public class Star extends Figure implements Drawable {
    private int starRadius;
    private int rayLength;
    private int rayNumber;

    public Star(double cx, double cy, double lineWidth, Color color, int starRadius, int rayLength, int rayNumber) {
        super(FIGURE_TYPE_STAR, cx, cy, lineWidth, color);
        this.starRadius = starRadius;
        this.rayLength = rayLength;
        this.rayNumber = rayNumber;
    }

    public int getStarRadius() {
        return starRadius;
    }

    public void setStarRadius(int starRadius) {
        this.starRadius = starRadius;
    }

    public int getRayLength() {
        return rayLength;
    }

    public void setRayLength(int rayLength) {
        this.rayLength = rayLength;
    }

    public int getRayNumber() {
        return rayNumber;
    }

    public void setRayNumber(int rayNumber) {
        this.rayNumber = rayNumber;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) throws WrongShapeException {
        if (rayNumber < 5 || rayNumber > 15) {
            rayNumber = 5;
            throw new WrongShapeException("Invalid number of star rays.");
        }
        //double angleStep = Math.toRadians(180/rayNumber); //работает не корректно
        double angleStep = Math.PI / rayNumber;
        ArrayList<Double> angleList = new ArrayList<>();
        for (int i = 0; i < rayNumber * 2 + 1; i++) {
            angleList.add(angleStep * i);
        }

        graphicsContext.setLineWidth(lineWidth);
        graphicsContext.setStroke(color);

        for (int i = 0; i < rayNumber; i++) {
            graphicsContext.strokeLine(cx - (starRadius + rayLength) * Math.sin(angleList.get(i * 2)), cy - (starRadius + rayLength) * Math.cos(angleList.get(i * 2)), cx - starRadius * Math.sin(angleList.get(i * 2 + 1)), cy - starRadius * Math.cos(angleList.get(i * 2 + 1)));
            graphicsContext.strokeLine(cx - starRadius * Math.sin(angleList.get(i * 2 + 1)), cy - starRadius * Math.cos(angleList.get(i * 2 + 1)), cx - (starRadius + rayLength) * Math.sin(angleList.get(i * 2 + 2)), cy - (starRadius + rayLength) * Math.cos(angleList.get(i * 2 + 2)));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Star star = (Star) o;
        return starRadius == star.starRadius && rayLength == star.rayLength && rayNumber == star.rayNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(starRadius, rayLength, rayNumber);
    }

    @Override
    public String toString() {
        return "Star{" +
                "cx=" + cx +
                ", cy=" + cy +
                ", lineWidth=" + lineWidth +
                ", color=" + color +
                ", starRadius=" + starRadius +
                ", rayLength=" + rayLength +
                ", rayNumber=" + rayNumber +
                '}';
    }
}
