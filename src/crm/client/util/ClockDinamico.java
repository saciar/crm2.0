package crm.client.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClockDinamico extends JLabel implements Runnable {

    Thread clockThread = null;

    public ClockDinamico() {
    	this.setForeground(Color.white);
    	this.setFont(new java.awt.Font("Tahoma", 1, 11));
		this.setPreferredSize(new Dimension(120, 22));
		this.setMaximumSize(new Dimension(120, 22));
		this.setSize(new Dimension(120, 22));
		this.setHorizontalAlignment(JLabel.RIGHT);
        if (clockThread == null) {
            clockThread = new Thread(this, "Clock");
            clockThread.start();
        }
    }
    public void run() {
        // El bucle termina cuando clockThread se pone a null en stop()
        while (Thread.currentThread() == clockThread) {
            repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
            }
        }
    }
    public void paint(Graphics g) {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        Locale l = new Locale("es","AR");
        DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, 
        							DateFormat.DEFAULT, l);
        g.drawString(dateFormatter.format(date), 0, 15);
    }
    public void stop() {
        clockThread = null;
    }
}
