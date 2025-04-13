import java.awt.*;

public class Pipe {
    private int posX, posY, width, height;
    private Image image;
    private int velocityX;
    private boolean passed = false;

    public Pipe(int posX, int posY, int width, int height, Image image){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;

        this.velocityX = -3;
        this.passed = false;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getPosY() {
        return posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getHeight() {
        return height;
    }

    public boolean isPassed() {
        return passed;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }
}
