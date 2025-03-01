package locadora.view;

import javax.swing.*;
import java.awt.*;

public class AtendenteView {

    private JFrame frame;

    public AtendenteView(){
        initialize();
    }

    private void initialize(){

        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(25, 25, 112));
        panel.setBounds(0, 0, 450, 300);
        panel.setLayout(null);
        frame.getContentPane().add(panel);
    }

    public JFrame getFrame() {
        return frame;
    }
}
