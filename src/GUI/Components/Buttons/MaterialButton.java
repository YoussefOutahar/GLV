package GUI.Components.Buttons;

import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MaterialButton extends JButton{
    Color color;
    MaterialButton(String text,int width,int height,Color color){
        super(text);
        this.color = color;
        setUI(new BasicButtonUI());
        setPreferredSize(new java.awt.Dimension(width,height));
        setBackground(color);
        setBorder(null);
        addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent arg0) {
                setBackground(color.darker().darker());
            }
            @Override
            public void mouseEntered(MouseEvent arg0) {}
            @Override
            public void mouseExited(MouseEvent arg0) {}
            @Override
            public void mousePressed(MouseEvent arg0) {}
            @Override
            public void mouseReleased(MouseEvent arg0) {
                setBackground(color);
            }});
    }

    public void changeColor(Color color){
        setBackground(color);
    }
}
