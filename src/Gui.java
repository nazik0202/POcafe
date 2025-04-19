import jdk.jfr.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Gui {
    private Menu menu;
    private Order order;

    private JFrame frame;
//    private JPanel panel;

    private ScalableBackground panel; //змінені атрібути
    private GridBagConstraints gdc;



    public Gui() {
        this.menu = new Menu();
        this.order = new Order();
        this.frame = new JFrame();
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        frame.setSize(1000,100);

        //       this.panel = new JPanel(new GridBagLayout());

        frame.setLayout(new BorderLayout());

        this.panel = new ScalableBackground("src/img/foonn.png"); //потрібнен клас ScalableBackground
        frame.setContentPane(panel);
        panel.setLayout(new GridBagLayout());


        frame.addComponentListener(new ComponentAdapter() { //адаптивність під розмір вікна
            @Override
            public void componentResized(ComponentEvent e) {
                panel.updateBackground(frame.getWidth(), frame.getHeight());
            }
        });
//        JLabel background = new JLabel(new ImageIcon("src/img/kafe.png"));
//        background.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
//        frame.setContentPane(background);
//        panel.add(background);
//
//        ImageIcon icon = new ImageIcon("img/cafe.jpeg");
//        panel.setBackground(icon.getImage());
//        panel.setBackground(Color.DARK_GRAY);



        JButton startButton = new JButton("Старт");
        JButton exitButton = new JButton("Вихід");

        startButton.setPreferredSize(new Dimension(100,50));
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setBorder(BorderFactory.createEmptyBorder());


        startButton.setBackground(new Color(0, 0, 0));
        startButton.setForeground(Color.WHITE);

        exitButton.setBackground(new Color(0, 0, 0));
        exitButton.setForeground(Color.WHITE);

        startButton.addActionListener(e -> start());
        this.gdc = new GridBagConstraints();
        gdc.gridx = 0;
        gdc.gridy = 0;
        gdc.gridwidth = 1;
        gdc.weightx = 1;
        gdc.weighty = 1;
        gdc.gridheight = 1;
        gdc.insets = new Insets(0, 0, 0, 0);
        gdc.anchor = GridBagConstraints.CENTER;
        gdc.ipadx = 0;
        gdc.ipady = 0;

        panel.add(startButton,gdc);
        gdc.gridx = 1;
        gdc.weightx = 0;
        gdc.weighty = 0;
        gdc.anchor = GridBagConstraints.NORTHEAST;
        gdc.insets = new Insets(10, 10, 10, 10);
        exitButton.addActionListener(e ->System.exit(0));
        panel.add(exitButton,gdc);

//        frame.add(panel);
        frame.setVisible(true);
    }

    public void styleb(JButton button){
        button.setBackground(new Color(0, 0, 0));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 17));
//        button.setPreferredSize(new Dimension(150,100));
//        button.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
    }
    public void stylel(JLabel label){
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 20));
    }
    public void start(){



        panel.removeAll();
//        GridLayout layout = new GridLayout(0,1);

        panel.setImage("src/img/foonn.png"); //зміна фона
        panel.updateBackground(frame.getWidth(), frame.getHeight());

        gdc.gridx = 2; //місце по горизонталі
        gdc.gridy = 0; //місце по вертикалі
        gdc.gridwidth = 2; //скільки займає місця по горизонталі
        gdc.gridheight = 1; //скільки займає місця по вертикалі
        gdc.weightx = 1; //"вага" по горизонталі
        gdc.weighty = 1; //"вага" по вертикалі
        gdc.insets = new Insets(10, 10, 10, 10); //відступи
        gdc.anchor = GridBagConstraints.NORTH; //розміщення
        gdc.ipadx = 50; //розмір по горизонталі
        gdc.ipady = 50; //розмір по вертикалі

        JLabel menuLabel = new JLabel("Виберіть категорію:", SwingConstants.CENTER);
        stylel(menuLabel);
//        menuLabel.setLocation(menuLabel.getX(),0);
        panel.add(menuLabel,gdc);

        gdc.gridx = 1;
        gdc.gridy = 1;
        gdc.gridwidth = 2;
        gdc.ipadx = 100;
        gdc.ipady = 45;

        int categoriesCount = menu.getMenuCategories().size();
        boolean firstColumn = true;

        for (String category : menu.getMenuCategories().keySet()) {

            JButton categoryButton = new JButton(category);
            categoryButton.addActionListener(e -> showItems(category));
            styleb(categoryButton);
            panel.add(categoryButton,gdc);
            gdc.gridy +=1;

            if(categoriesCount-gdc.gridy<categoriesCount/2 && firstColumn){
                gdc.gridx = 3;
                gdc.gridy = 1;
                firstColumn = false;
            }
        }

        gdc.gridwidth = 0;
        gdc.gridx = 2;
        gdc.gridy = -1;
        gdc.ipadx = 220;
        gdc.ipady = 70;
        gdc.anchor = GridBagConstraints.SOUTH;

        JButton showOrderbutt = new JButton("Показати або оплатити замовлення");
        styleb(showOrderbutt);
        showOrderbutt.addActionListener(e->showOrder());
        panel.add(showOrderbutt,gdc);
        panel.revalidate();
        panel.repaint();
        frame.setVisible(true);
    }



    private void showOrder() {
        panel.removeAll();
        gdc.gridy = 0;
        for (Item item: order.getItems()){
            JLabel label = new JLabel(item.getName() + " " + item.getPrice());
            label.setBorder(BorderFactory.createEmptyBorder(5,100,5,0));
            stylel(label);
            JButton del = new JButton("Видалити");
            styleb(del);

            del.addActionListener(e->{
                order.delete(item);
                showOrder();
            });
            gdc.gridx = 0;
            panel.add(label,gdc);
            gdc.gridx = 2;
            panel.add(del,gdc);
            gdc.gridy += 1;
        }
        JLabel totalptice = new JLabel("Сумарна вартість: " + order.getTotalPrice());
        totalptice.setBorder(BorderFactory.createEmptyBorder(5,100,5,0));
        stylel(totalptice);
        gdc.gridx = 1;
        gdc.gridy += 1;
        panel.add(totalptice,gdc);
        JButton backl = new JButton("Повернутись");
        styleb(backl);
        backl.addActionListener(e->start());
        JButton pay = new JButton("Оплатити замовлення");
        styleb(pay);
        pay.addActionListener(e->paying());
        gdc.gridx = 2;
        gdc.gridy += 1;
        panel.add(pay,gdc);
        gdc.gridx = 0;
        panel.add(backl,gdc);
        panel.revalidate();
        panel.repaint();
    }

    private void paying(){
        int orderNumber = new Random().nextInt(9000) + 1000;

        panel.removeAll();

        JLabel confirmationLabel = new JLabel("Ваше замовлення підтверджене!");
        confirmationLabel.setBorder(BorderFactory.createEmptyBorder(5,100,5,0));
        stylel(confirmationLabel);
        gdc.gridy = 0;
        panel.add(confirmationLabel,gdc);

        JLabel orderNumberLabel = new JLabel("Номер вашого замовлення: " + orderNumber);
        orderNumberLabel.setBorder(BorderFactory.createEmptyBorder(5,100,5,0));
        stylel(orderNumberLabel);
        gdc.gridy += 1;
        panel.add(orderNumberLabel,gdc);

        JButton exiButton = new JButton("Вийти");
        styleb(exiButton);
        exiButton.addActionListener(e -> {
            new Gui();
            this.frame.dispose();
        });
        gdc.gridy += 1;
        panel.add(exiButton,gdc);
        panel.revalidate();
        panel.repaint();
    }

    public void showItems(String categoryName){
        panel.removeAll();
//        gdc.gridx = 0; //місце по горизонталі
//        gdc.gridy = 0; //місце по вертикалі
//        gdc.gridwidth = 1; //скільки займає місця по горизонталі
//        gdc.gridheight = 1; //скільки займає місця по вертикалі
//        gdc.weightx = 1; //"вага" по горизонталі
//        gdc.weighty = 1; //"вага" по вертикалі
//        gdc.insets = new Insets(10, 10, 10, 10); //відступи
//        gdc.anchor = GridBagConstraints.CENTER; //розміщення
        gdc.ipadx = 120; //розмір по горизонталі
        gdc.ipady = 60; //розмір по вертикалі

        List<Item> listItem = menu.getMenuCategories().get(categoryName);
        for(Item item: listItem){
            JButton button = new JButton(item.getName());
            styleb(button);
            button.addActionListener(e->addToOrder(item));
            panel.add(button,gdc);
        }
        gdc.ipadx = 410;
        gdc.ipady = 75;
        JButton back = new JButton("Повернутися");
        styleb(back);
        back.addActionListener(e->start());
        panel.add(back,gdc);
        panel.revalidate();
        panel.repaint();
    }
    private void addToOrder(Item item){
        this.order.addOrder(item);
        JOptionPane.showMessageDialog(frame, item.getName() + " Додано до замовлення.");
    }
}