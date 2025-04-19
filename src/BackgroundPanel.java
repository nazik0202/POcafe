import javax.swing.*;

class BackgroundPanel extends JPanel {
    private ImageIcon image;
    public BackgroundPanel(String imagePath){
        this.image = new ImageIcon(imagePath);
        JLabel background = new JLabel(image);

    }
}
