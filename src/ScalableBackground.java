import javax.swing.*;
import java.awt.*;

class ScalableBackground extends JLabel {
    private ImageIcon imageIcon;

    public ScalableBackground(String imagePath) {
        imageIcon = new ImageIcon(imagePath);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }

    public void setImage(String imagePath) {
        this.imageIcon = new ImageIcon(imagePath);
        updateBackground(getWidth(), getHeight()); // Оновлення розміру після зміни зображення
    }
    public void updateBackground(int width, int height) {
        if (width > 0 && height > 0) {
            Image img = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(img));
        }
    }
}