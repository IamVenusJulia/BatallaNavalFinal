package myProject;

import javax.swing.*;
import java.awt.*;

/**
 * Clase principal
 * @autor Daniel Arias Castrillón - 2222205
 * @autor Venus Juliana Paipilla 2177134-3744
 */
public class Header extends JLabel {
    public Header(String title, Color colorBackground){
        this.setText(title);
        this.setBackground(colorBackground);
        this.setForeground(Color.WHITE);
        this.setFont(new Font(Font.MONOSPACED,Font.BOLD,30));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setOpaque(true);
    }
}
