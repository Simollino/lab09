package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    private static final int PROPORTION = 5;

    private SimpleGUI(SimpleController controller) {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextField textField = new JTextField();
        canvas.add(textField,BorderLayout.NORTH);
        final JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        canvas.add(textArea,BorderLayout.CENTER);
        final JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        final JButton print = new JButton("Print");
        final JButton show = new JButton("Show history");
        jp.add(print,BorderLayout.WEST);
        jp.add(show,BorderLayout.EAST);
        canvas.add(jp,BorderLayout.SOUTH);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        print.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setNextString(textField.getText());
                System.out.println(textField.getText());
            }
            
        });
        show.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(controller.getPrintedStrings().toString());
            }
            
        });
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleGUI(new SimpleController()).display();
    }

}
