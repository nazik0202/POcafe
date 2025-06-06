import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class Gui {
    private Menu menu;
    private Order order;

    private JFrame frame;
//    private JPanel panel;

    private ScalableBackground panel; //змінені атрібути
    private GridBagConstraints gdc;

    static boolean secretFich = false;
    private JButton secretFeatureButton;

    private static final AtomicInteger messageOffset = new AtomicInteger(0);
    private static final int MAX_OFFSET = 10;
    private static final int OFFSET_STEP = 30;

    public Gui() {
        this.menu = new Menu();
        this.order = new Order();
        this.frame = new JFrame();

        preStart();

//        gdc.anchor = GridBagConstraints.NORTH;
//        gdc.gridx = 0;
//        gdc.gridy = 0;
//        if(secretFich) {
//            JButton fichbutton = new JButton("Виключити секретну фічу");
//            fichbutton.addActionListener(e -> secretFich());
//            panel.add(fichbutton,gdc);
//        }
//        else{
//            JButton fichbutton = new JButton("Включити секретну фічу");
//            fichbutton.addActionListener(e -> secretFich());
//            panel.add(fichbutton,gdc);
//        }


    }

    public void preStart(){
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

        startButton.setPreferredSize(new Dimension(100, 50));
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

        panel.add(startButton, gdc);

        gdc.gridx = 1;
        gdc.weightx = 0;
        gdc.weighty = 0;
        gdc.anchor = GridBagConstraints.NORTHEAST;
        gdc.insets = new Insets(10, 10, 10, 10);
        exitButton.addActionListener(e -> exit());
        panel.add(exitButton, gdc);

//        frame.add(panel);
//        frame.setVisible(true);

        secretFeatureButton = new JButton(secretFich ? "Виключити секретну фічу" : "Включити секретну фічу");
        secretFeatureButton.addActionListener(e -> secretFich());
        gdc.anchor = GridBagConstraints.NORTH;
        gdc.gridx = 0;
        gdc.gridy = 0;
        panel.add(secretFeatureButton,gdc);
//        if(secretFich) {
//            JButton fichbutton = new JButton("Виключити секретну фічу");
//            fichbutton.addActionListener(e -> secretFich());
//            panel.add(fichbutton,gdc);
//        }
//        else{
//            JButton fichbutton = new JButton("Включити секретну фічу");
//            fichbutton.addActionListener(e -> secretFich());
//            panel.add(fichbutton,gdc);
//        }

//        panel.revalidate();
//        panel.repaint();
        frame.setVisible(true);
    }



    public void exit() {
        final JDialog dialog = new JDialog(frame, "Access Code", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(350, 180);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(frame);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel("Введіть код допуску:");
        //JTextField codeField = new JTextField();
        JPasswordField codeField = new JPasswordField();
        JButton confirmButton = new JButton("Підтвердити");
        confirmButton.setEnabled(false);

        codeField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                //confirmButton.setEnabled(!codeField.getText().trim().isEmpty());
                confirmButton.setEnabled(!(new String(codeField.getPassword()).trim()).isEmpty());
            }
            public void removeUpdate(DocumentEvent e) {
                //confirmButton.setEnabled(!codeField.getText().trim().isEmpty());
                confirmButton.setEnabled(!(new String(codeField.getPassword()).trim()).isEmpty());
            }
            public void changedUpdate(DocumentEvent e) {}
        });

        confirmButton.addActionListener(e -> {
            //String enteredCode = codeField.getText().trim();
            String enteredCode = new String(codeField.getPassword()).trim();
            if ("1212".equals(enteredCode)) { // дефолтний код
                JOptionPane.showMessageDialog(dialog, "Доступ підтверджено");
                dialog.dispose();
                System.exit(0);
            }
            else {
                JOptionPane.showMessageDialog(dialog, "Код неправильний", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        centerPanel.add(label);
        centerPanel.add(Box.createVerticalStrut(5));
        centerPanel.add(codeField);
        centerPanel.add(Box.createVerticalStrut(10));

        JPanel bottomPanel = new JPanel(new BorderLayout());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> dialog.dispose());
        bottomPanel.add(backButton);

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(backButton);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(confirmButton);

        dialog.add(centerPanel, BorderLayout.CENTER);
        dialog.add(bottomPanel, BorderLayout.SOUTH);

        bottomPanel.add(leftPanel, BorderLayout.WEST);
        bottomPanel.add(rightPanel, BorderLayout.EAST);

        dialog.setVisible(true);
    }

//    public void  exit (){
//        JLabel passtext = new JLabel("Ведіть код адміністратора");
//        JTextArea passnum = new JTextArea();
//        JButton passbut = new JButton("Підтвердити");
//        JPanel passpanel = new JPanel();
//        passpanel.add(passtext);
//        passpanel.add(passnum);
//        passpanel.add(passtext);
//        JOptionPane.showMessageDialog(frame, passpanel );
//
//    }

    public void styleb(JButton button) {
        button.setBackground(new Color(0, 0, 0));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 17));
//        button.setPreferredSize(new Dimension(150,100));
//        button.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
    }

    public void stylel(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setBackground(Color.DARK_GRAY);
        if(secretFich) {
            label.setOpaque(true);
        }
        else{
            label.setOpaque(false);
        }
    }

    public void start() {


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
        panel.add(menuLabel, gdc);

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
            panel.add(categoryButton, gdc);

            gdc.gridy += 1;

            if (categoriesCount - gdc.gridy < categoriesCount / 2 && firstColumn) {
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


        if(order.getItems().isEmpty()){
            JButton exitStartScreen = new JButton("Вийти на початковий екран");
            styleb(exitStartScreen);
            exitStartScreen.addActionListener(e -> {
                new Gui();
                this.frame.dispose();
            });
            panel.add(exitStartScreen, gdc);
        }
        else {
            JButton showOrderbutt = new JButton("Показати або оплатити замовлення");
            styleb(showOrderbutt);
            showOrderbutt.addActionListener(e -> showOrder());
            panel.add(showOrderbutt, gdc);
        }


        panel.revalidate();
        panel.repaint();
        frame.setVisible(true);
    }




    private void showOrder() {
        gdc.gridwidth = 1;
        panel.removeAll();
        gdc.gridy = 0;

        for (Item item : order.getItems()) {
            JLabel label = new JLabel(item.getName() + " " + item.getPrice());
            label.setBorder(BorderFactory.createEmptyBorder(5, 100, 5, 0));
            stylel(label);

            JButton del = new JButton("Видалити");
            styleb(del);

            del.addActionListener(e -> {
                order.delete(item);
                showOrder();
            });
            gdc.gridx = 1;
            panel.add(label, gdc);

            gdc.gridx = 2;
            panel.add(del, gdc);

            gdc.gridy += 1;
        }


        gdc.gridx = 1;
        gdc.gridy += 1;

        JLabel totalptice = new JLabel("Сумарна вартість: " + order.getTotalPrice());
        totalptice.setBorder(BorderFactory.createEmptyBorder(5, 100, 5, 0));
        totalptice.setForeground(Color.WHITE);
        totalptice.setFont(new Font("Arial", Font.BOLD, 40));
        panel.add(totalptice, gdc);

        JButton backl = new JButton("Повернутись");
        styleb(backl);
        backl.addActionListener(e -> start());

        gdc.gridx = 2;
        gdc.gridy += 1;

        if(order.getItems().isEmpty()){
            JButton exitStartScren = new JButton("Вийти на початковий екран");
            styleb(exitStartScren);
            exitStartScren.addActionListener(e -> {
                new Gui();
                this.frame.dispose();
            });
            panel.add(exitStartScren, gdc);
        }
        else
        {
            JButton pay = new JButton("Оплатити замовлення");
            styleb(pay);
            pay.addActionListener(e -> paying());
            panel.add(pay, gdc);
        }


        gdc.gridx = 2;
        gdc.gridy += 1;
        gdc.gridx = 0;
        panel.add(backl, gdc);
        panel.revalidate();
        panel.repaint();
    }




    private void paying() {
        int orderNumber = new Random().nextInt(9000) + 1000;

        panel.removeAll();

        JLabel confirmationLabel = new JLabel("Ваше замовлення підтверджене!");
        confirmationLabel.setBorder(BorderFactory.createEmptyBorder(5, 100, 5, 0));
        stylel(confirmationLabel);
        gdc.gridy = 0;
        panel.add(confirmationLabel, gdc);

        JLabel orderNumberLabel = new JLabel("Номер вашого замовлення: " + orderNumber);
        orderNumberLabel.setBorder(BorderFactory.createEmptyBorder(5, 100, 5, 0));
        stylel(orderNumberLabel);
        gdc.gridy += 1;
        panel.add(orderNumberLabel, gdc);

        JButton exiButton = new JButton("Вийти");
        styleb(exiButton);
        exiButton.addActionListener(e -> {
            new Gui();
            this.frame.dispose();
        });

        gdc.gridy += 1;
        panel.add(exiButton, gdc);
        panel.revalidate();
        panel.repaint();
    }



    public void showItems(String categoryName) {
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

        for (Item item : listItem) {
            JButton button = new JButton(item.getName());
            styleb(button);
            button.addActionListener(e -> addToOrder(item));
            panel.add(button, gdc);
        }

        gdc.ipadx = 410;
        gdc.ipady = 75;
        JButton back = new JButton("Повернутися");
        styleb(back);
        back.addActionListener(e -> start());
        panel.add(back, gdc);
        panel.revalidate();
        panel.repaint();
    }



    private void addToOrder(Item item){
        this.order.addOrder(item);
        showAutoClosingMessage(item.getName() + " додано до замовлення.", 15000);
    }



    private void showAutoClosingMessage(String message, int timeoutMillis) {
        JDialog dialog = new JDialog(frame, "Info", false);
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        dialog.getContentPane().add(label);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(300, 100);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int baseY = screenSize.height - dialog.getHeight() - 50;

        // Limit offset to prevent windows from going off-screen
        int currentOffset = Math.min(messageOffset.getAndIncrement(), MAX_OFFSET);

        int x = screenSize.width - dialog.getWidth() - 50;
        int y = Math.max(0, baseY - currentOffset * OFFSET_STEP);

        dialog.setLocation(x, y);

        // Create a timer that closes the dialog and safely decrements the offset
        new Timer(timeoutMillis, e -> {
            if (dialog.isDisplayable()) {
                dialog.dispose();
            }
            messageOffset.updateAndGet(offset -> Math.max(0, offset - 1));
        }).start();

        dialog.setVisible(true);
    }
//    private void secretButton(GridBagConstraints gdc){
//        gdc.anchor = GridBagConstraints.NORTH;
//        gdc.gridx = 0;
//        gdc.gridy = 0;
//        if(secretFich) {
//            JButton fichbutton = new JButton("Виключити секретну фічу");
//            fichbutton.addActionListener(e -> secretFich());
//            panel.add(fichbutton,gdc);
//        }
//        else{
//            JButton fichbutton = new JButton("Включити секретну фічу");
//            fichbutton.addActionListener(e -> secretFich());
//            panel.add(fichbutton,gdc);
//        }
//
//    }
    private void secretFich(){
        secretFich = !secretFich; // Інвертуємо значення boolean
        secretFeatureButton.setText(secretFich ? "Виключити секретну фічу" : "Включити секретну фічу");
        panel.revalidate(); // Повідомляємо менеджер компоновки про зміни
        panel.repaint();
    }
}

//Toolkit.getDefaultToolkit().getScreenSize();
//int x = screenSize.width - dialog.getWidth() - 50;
//int y = screenSize.height - dialog.getHeight() - 50;
//        dialog.setLocation(x, y);