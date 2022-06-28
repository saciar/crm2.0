package crm.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;

/**
 * @author Shannon Hickey
 */
public class FadeButton extends JButton{
    protected boolean bInside = true;
    private static final BasicStroke stroke = new BasicStroke(1.0f);
    private static final int TIME = 500;
    private static final HashSet list = new HashSet();

    private Color clrBase = Color.RED;
    private Color clrOther = null;
    private float[] colComps = new float[3];
    
    private Font font;
    
    private long start, end, current;
    private int dir;
    private float ALPHA_MAX = 0.2f;
    private float trans;
    
    private Color colorFont = Color.BLACK;
    
    private static final Timer timer = new Timer(50, new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            FadeButton[] fields = new FadeButton[list.size()];
            fields = (FadeButton[])list.toArray(fields);
            
            for (int i = 0; i < fields.length; i++) {
                fields[i].nextStep();
            }
        }
    });

    public FadeButton(String text, Color base) {
        super(text);
        font = getFont();        
        if (base != null) {
            this.clrBase = base;
        }
        init();
    }

    private void init() {
        setOpaque(false);

        addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent fe) {            	
            	setForeground(Color.GRAY);
                fadeIn();
            }
         
            public void focusLost(FocusEvent fe) {  
            	setForeground(colorFont);
            	bInside = true;
            	fadeOut();
            	
            }
        });
        
        addMouseListener(new MouseAdapter() {
        	public void mouseEntered(MouseEvent e)
    		{
    			//bInside = false;        		
        		setForeground(Color.GRAY);
    			repaint();
    		}

    		public void mouseExited(MouseEvent e)
    		{
    			setForeground(colorFont);
    			bInside = true;
    			repaint();
    		}
    		public void mousePressed(MouseEvent e){
    			bInside = false;
        		repaint();
    		}
        });
       
    }

    public void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        float pct = trans / ALPHA_MAX;
        int w = getWidth();
        int h = getHeight();

        g2d.setStroke(stroke);
        g2d.setColor(new Color(163,184,204));
        //g2d.drawRect(2, 2, w - 4, h - 4);
        
        if ((clrOther != null) && (pct > 0.0f)) {
            GradientPaint gp = new GradientPaint(0, 0, clrOther,
                                                 w / 2, 0, clrBase, true);
            g2d.setPaint(gp);
            int cw = (int)(w * pct);
            g2d.setClip(new Rectangle((int)((w - cw) / 2), 0, cw, h));
            //g2d.drawRect(2, 2, w - 4, h - 4);
        }
        
        g2d.dispose();
    }

    public void setOtherColor(Color col) {
        clrOther = col;
    }

    private void nextStep() {
        current = System.currentTimeMillis();

        if (current >= end) {
            start = end = current = 0;
            trans = (dir == 1) ? ALPHA_MAX : 0.0f;
            removeField(this);
            repaint();
            return;
        }

        float newTrans = (float)(current - start) / (float)(end - start);

        if (dir == -1) {
            newTrans = 1 - newTrans;
        }

        trans = newTrans * ALPHA_MAX;
        repaint();
    }

    private static void addField(FadeButton field) {
        list.add(field);
        timer.start();
    }

    private static void removeField(FadeButton field) {
        list.remove(field);
    }

    private void restart(int dir) {
        long now = System.currentTimeMillis();
        long left = end - current;
        
        start = now - left;
        end = now + TIME - left;
        current = now;
        
        this.dir = dir;
        addField(this);
    }

    private void fadeIn() {
        restart(1);
    }

    private void fadeOut() {
        restart(-1);
    }

	/**
	 * @return Returns the colorFont.
	 */
	public Color getColorFont() {
		return colorFont;
	}

	/**
	 * @param colorFont The colorFont to set.
	 */
	public void setColorFont(Color colorFont) {
		this.colorFont = colorFont;
	}

}



