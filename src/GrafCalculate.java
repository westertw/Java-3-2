import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrafCalculate extends JFrame implements ActionListener {


    static JFrame frame;
    static JTextField result;
    static String a = "", b = "", operation = "";

    public static void main(String[] args) {

        GrafCalculate listen = new GrafCalculate();

        frame = new JFrame("Calculator"); // название

        result = new JTextField(16); // колво строк
        result.setEditable(false); // неизменяемое поле

        ArrayList<JButton> buttons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            JButton jTmpButton = new JButton(Integer.toString(i));
            jTmpButton.addActionListener(listen);
            buttons.add(jTmpButton);
        }

        List<String> operations = Arrays.asList("+", "-", "/", "*", ".", "C", "=");

        JPanel button = new JPanel();
        buttons.forEach(button::add);
        operations.forEach(it -> {
            JButton jTmpButton = new JButton(it);
            jTmpButton.addActionListener(listen);
            button.add(jTmpButton);
        });

        GridLayout numsAndoprs = new GridLayout(4, 4); //расположение объектов
        button.setLayout(numsAndoprs);


        JPanel mainPanel = new JPanel();
        mainPanel.add(result);
        mainPanel.add(button);


        frame.add(mainPanel);
        frame.setSize(360, 180); // размер окна
        frame.setVisible(true); // видимость окна?

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.charAt(0) >= '0' && s.charAt(0) <= '9') {
            if (operation.equals("")) {
                a = a + s;
            } else
                b = b + s;
            result.setText(a + operation + b);
        } else if (s.charAt(0) == 'c') {
            a = operation = b = "";
            result.setText(a + operation + b);
        } else if (s.charAt(0) == '=') {
            int rslt = switch (operation) {
                case "+" -> Integer.parseInt(a) + Integer.parseInt(b);
                case "-" -> Integer.parseInt(a) - Integer.parseInt(b);
                case "/" -> Integer.parseInt(a) / Integer.parseInt(b);
                default -> Integer.parseInt(a) * Integer.parseInt(b);
            };
            a = String.valueOf(rslt);
            result.setText(a);
            operation = b = "";
        } else {
            if (operation.equals(""))
                operation = s;
            result.setText(a + operation + b);
        }

    }
}