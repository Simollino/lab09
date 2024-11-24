package it.unibo.mvc;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final String TITLE = "My second Java graphical interface";
    private final JFrame frame = new JFrame(TITLE);
    private static final int PROPORTION = 5;

    private SimpleGUIWithFileChooser(Controller controller) {

        String initialPath = controller.getCurrentFilePath();
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextArea text = new JTextArea();
        canvas.add(text,BorderLayout.CENTER);
        final JButton save = new JButton("Save");
        canvas.add(save,BorderLayout.SOUTH);
        final JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp,BoxLayout.LINE_AXIS));
        final JTextField textField = new JTextField(initialPath);
        textField.setEditable(false);
        jp.add(textField);
        final JButton browse = new JButton("Browse...");
        jp.add(browse);
        canvas.add(jp,BorderLayout.NORTH);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent event) {
                try {
                    controller.save(text.getText());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "An error occurred", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });

        browse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser("Choose where to save");
                if(fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    controller.setCurrentFile(fileChooser.getSelectedFile());
                }
                else if(fileChooser.showSaveDialog(frame) == JFileChooser.CANCEL_OPTION) {
                }
                else {
                    JOptionPane.showMessageDialog(frame, "An error occurred while saving the file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                if(controller.getCurrentFilePath() != initialPath) {
                    textField.setText(controller.getCurrentFilePath());
                }
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
        new SimpleGUIWithFileChooser(new Controller()).display();
    }

}
