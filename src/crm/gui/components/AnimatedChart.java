package crm.gui.components;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import jcckit.GraphicsPlotCanvas;
import jcckit.data.DataCurve;
import jcckit.data.DataPlot;
import jcckit.data.DataPoint;
import jcckit.util.ConfigParameters;
import jcckit.util.PropertiesBasedConfigData;

public class AnimatedChart extends JPanel {
	private double[] _data = new double[] { 50.5, 34.2, 47.4, 53.1, 69.9, 68.7,
			81.1 };
	private DataPlot _dataPlot;

	public AnimatedChart(){
		super();
		GraphicsPlotCanvas plotCanvas = createPlotCanvas();

		_dataPlot = new DataPlot();
		_dataPlot.addElement(new DataCurve(""));
		plotCanvas.connect(_dataPlot);

		this.setLayout(new BorderLayout());
		this.add(plotCanvas.getGraphicsCanvas(), BorderLayout.CENTER);
		this.add(createControlPanel(), BorderLayout.SOUTH);
	}
	
	private GraphicsPlotCanvas createPlotCanvas() {
		Properties props = new Properties();
		ConfigParameters config = new ConfigParameters(
				new PropertiesBasedConfigData(props));
		props.put("plot/legendVisible", "false");
		props.put("plot/coordinateSystem/xAxis/minimum", "-0.5");
		props.put("plot/coordinateSystem/xAxis/maximum", "6.5");
		props.put("plot/coordinateSystem/xAxis/axisLabel", "");
		props.put("plot/coordinateSystem/xAxis/ticLabelFormat/className",
				"jcckit.plot.TicLabelMap");
		props.put("plot/coordinateSystem/xAxis/ticLabelFormat/map",
				"0=Enero;1=Febrero;2=Marzo;3=Abril;4=Mayo;5=Junio;6=Julio");
		props.put("plot/coordinateSystem/yAxis/axisLabel", "");
		props.put("plot/coordinateSystem/yAxis/maximum", "500");
		//props.put("plot/coordinateSystem/yAxis/ticLabelFormat", "%d%%");
		props.put("plot/curveFactory/definitions", "curve");
		props.put("plot/curveFactory/curve/withLine", "false");
		props.put("plot/curveFactory/curve/symbolFactory/className",
				"jcckit.plot.BarFactory");
		props.put("plot/curveFactory/curve/symbolFactory/attributes/className",
				"jcckit.graphic.ShapeAttributes");
		props.put("plot/curveFactory/curve/symbolFactory/attributes/fillColor",
				"0xfe8000");
		props.put("plot/curveFactory/curve/symbolFactory/attributes/lineColor",
				"0");
		props.put("plot/curveFactory/curve/symbolFactory/size", "0.08");
		props.put("plot/initialHintForNextCurve/className",
				"jcckit.plot.PositionHint");
		props.put("plot/initialHintForNextCurve/position", "0 0.1");

		return new GraphicsPlotCanvas(config);
	}

	private JPanel createControlPanel() {
		JPanel controlPanel = new JPanel();
		JButton startButton = new JButton("animate");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {
						animate();
					}
				}.start();
			}
		});
		controlPanel.add(startButton);

		return controlPanel;
	}

	private void animate() {
		DataCurve curve = new DataCurve("");
		for (int i = 0; i < _data.length; i++) {
			curve.addElement(new DataPoint(i, 0));
		}
		_dataPlot.replaceElementAt(0, curve);

		for (int i = 0; i < _data.length; i++) {
			double x = i;
			double y = 0;
			while (y < _data[i]) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
				}
				y = Math.min(_data[i], y + 5);
				curve.replaceElementAt(i, new DataPoint(x, y));
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Prueba JccKit"); 			
		frame.getContentPane().add(new AnimatedChart());
		frame.setSize(600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
