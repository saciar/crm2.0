package crm.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;

public class GradientButton extends FadeButton{
    private static final Color clrOne = new Color(204,204,204,0);//new Color(163,184,204);
    private static final Color clrTwo = new Color(153,153,153);//new Color(120, 160, 255, 0);
    
    private int trans = 128;
    private long start = 0;
    private long current = 0;
    private int delay = 3000;
    private float pct;
    
    private static GradientButton active;
    private static Timer timer = new Timer(50, new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            if (active == null) {
                return;
            }
            
            active.nextStep();
        }
    });

    public GradientButton(String text, Color base) {
        super(text, base);

        Border b = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 80)),
            BorderFactory.createEmptyBorder(1, 1, 1, 1));
        b = BorderFactory.createCompoundBorder(
            b, new javax.swing.plaf.basic.BasicBorders.MarginBorder());

        setBorder(b);
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
       /* addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                begin();
            }
            
            public void mouseExited(MouseEvent me) {
                end();
            }
        });
        
        addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent fe) {
                begin();
            }
         
            public void focusLost(FocusEvent fe) {
            	end();
            	
            }
        });*/
    }

    private void begin() {
        start = System.currentTimeMillis();
        current = System.currentTimeMillis();
        active = this;
        timer.start();
    }

    private void end() {
        start = 0;
        current = 0;
        timer.stop();
        active = null;
        pct = 0;
        repaint();
    }

    private void nextStep() {
        current = System.currentTimeMillis();
        long diff = current - start;
        long rem = diff % delay;
        
        pct = rem / (float)delay;
        
        repaint();
    }
    
    public void paintComponent(Graphics g) {        
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        int w = getWidth();
        int h = getHeight();
        int hw = w / 2;
        int hh = h / 2;

        double angle = pct * Math.PI * 2;
        int radius = w/2 ;

        int tx = (int)(radius * Math.cos(angle));
        int ty = (int)(radius * Math.sin(angle));

        g2d.translate(hw, hh);

        GradientPaint gp = new GradientPaint(-tx, -ty, clrOne,
                                             tx, ty, clrTwo, false);
        g2d.setPaint(gp);
        g2d.fillRect(-hw + 2, -hh + 2, w - 4, h - 4);

        g2d.dispose();

        if (getModel().isPressed() && getModel().isArmed()) {
            g.translate(1, 1);
        }

        g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        super.paintComponent(g);

        if (getModel().isPressed() && getModel().isArmed()) {
            g.translate(-1, -1);
        }
        Color c1 = Color.white.brighter();
		Color c2 = Color.gray;

		int w1 = getWidth()  -1;
		int h1 = getHeight() -1;
        if (bInside && isEnabled())
		{
			g.setColor(c1);
			g.drawLine(0,0,w1,0);
			g.drawLine(0,0,0,h1);
			
			g.setColor(c2);
			g.drawLine(0,h1,w1,h1);
			g.drawLine(w1,h1,w1,0);
		}
        else if(!bInside){

			g.setColor(c2);
			g.drawLine(0,0,w1,0);
			g.drawLine(0,0,0,h1);
			
			g.setColor(c1);
			g.drawLine(0,h1,w1,h1);
			g.drawLine(w1,h1,w1,0);
        }

    }

}

