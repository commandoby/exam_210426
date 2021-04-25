package by.techmeskills.figuresfx.figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.Objects;

public class Bean extends Figure {
    private int innerRadius;
    private double beanThickness;
    private double beanLength;

    public Bean(double cx, double cy, double lineWidth, Color color, int innerRadius, double beanThickness, double beanLength) {
        super(FIGURE_TYPE_BEAN, cx, cy, lineWidth, color);
        this.innerRadius = innerRadius;
        this.beanThickness = beanThickness;
        this.beanLength = beanLength;
    }

    public int getInnerRadius() {
        return innerRadius;
    }

    public void setInnerRadius(int innerRadius) {
        this.innerRadius = innerRadius;
    }

    public double getBeanThickness() {
        return beanThickness;
    }

    public void setBeanThickness(int beanThickness) {
        this.beanThickness = beanThickness;
    }

    public double getBeanLength() {
        return beanLength;
    }

    public void setBeanLength(int beanLength) {
        this.beanLength = beanLength;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        double angleRad = beanLength * Math.PI / 360;
        double[] pos = new double[8];
        pos[0] = cx - innerRadius;
        pos[1] = cy + beanThickness / 2;
        pos[2] = cx - innerRadius - beanThickness;
        pos[3] = cy - beanThickness + beanThickness / 2;
        pos[4] = cx - beanThickness / 2 - Math.sin(angleRad) * (innerRadius + beanThickness / 2);
        pos[5] = cy - Math.cos(angleRad) * (innerRadius + beanThickness / 2) + innerRadius;
        pos[6] = cx - beanThickness / 2 + Math.sin(angleRad) * (innerRadius + beanThickness / 2);
        pos[7] = cy - Math.cos(angleRad) * (innerRadius + beanThickness / 2) + innerRadius;

        graphicsContext.setLineWidth(lineWidth);
        graphicsContext.setStroke(color);

        graphicsContext.strokeArc(pos[0], pos[1], innerRadius * 2, innerRadius * 2, 90 - (beanLength / 2), beanLength, ArcType.OPEN);
        graphicsContext.strokeArc(pos[2], pos[3], (innerRadius + beanThickness) * 2, (innerRadius + beanThickness) * 2, 90 - (beanLength / 2), beanLength, ArcType.OPEN);
        graphicsContext.strokeArc(pos[4], pos[5], beanThickness, beanThickness, 90 + (beanLength / 2), 180, ArcType.OPEN);
        graphicsContext.strokeArc(pos[6], pos[7], beanThickness, beanThickness, 270 - (beanLength / 2), 180, ArcType.OPEN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bean bean = (Bean) o;
        return innerRadius == bean.innerRadius && beanThickness == bean.beanThickness && beanLength == bean.beanLength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(innerRadius, beanThickness, beanLength);
    }

    @Override
    public String toString() {
        return "Bean{" +
                "innerRadius=" + innerRadius +
                ", beanThickness=" + beanThickness +
                ", beanLength=" + beanLength +
                ", cx=" + cx +
                ", cy=" + cy +
                ", lineWidth=" + lineWidth +
                ", color=" + color +
                '}';
    }
}
