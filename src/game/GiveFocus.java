package game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.6
 *  This class is GiveFocus
 *  This is a mouse listener that gives the keyboard focus to a specified component.
 */
public class GiveFocus extends MouseAdapter {
    private Component target;

    /**
     * Initialize the listener.
     *
     * @param target the component to be given the focus on mouseEntered().
     */
    public GiveFocus(Component target) {
        this.target = target;
    }

    /**
     * Called when the mouse enters a component.
     *
     * @param e description of the mouse event
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        target.requestFocus();
    }
}
