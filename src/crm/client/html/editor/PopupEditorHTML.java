package crm.client.html.editor;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import crm.client.managers.IdiomaManager;
import crm.client.managers.ServicioIdiomaManager;
import crm.client.managers.ServicioManager;
import crm.libraries.abm.entities.Idioma;
import crm.libraries.util.MessageUtil;

public class PopupEditorHTML extends JDialog {

	private Frame owner;

	private BasicHTMLEditor editor;

	private JPanel topPanel;

	private JPanel bottomPanel;

	private JButton okButton;

	private JButton cancelButton;

	//private String textReturned;
	
	private List descriptionLines;

	public PopupEditorHTML(Frame owner, String title,List descriptionLines) {
		super(owner);
		this.owner = owner;
		this.setTitle("Descripción detallada de "+title);
		this.setModal(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.getContentPane().setLayout(
				new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		this.createAndAddTopPanel(descriptionLines);
		this.createAndAddBottomPanel();
		this.setSize(800, 600);
		updatePosition();
		// this.setResizable(false);
		this.pack();
	}

	// crea un dialogo con un editor HTML adentro, el cual muestra la
	// descripcion detallada del servicio de codigo 'codServicio' en 
	// el idioma de descripción 'idioma'.
	public PopupEditorHTML(Frame owner, String codServicio, String idioma,List descriptionLines){
		super(owner);
		this.owner = owner;			
		
		this.setTitle("Descripción detallada de "+obtenerTituloServicio(codServicio,idioma));
		this.setModal(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.getContentPane().setLayout(
				new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		this.createAndAddTopPanel(descriptionLines);
		this.createAndAddBottomPanel();
		this.setSize(800, 600);
		updatePosition();
		// this.setResizable(false);
		this.pack();
		
		obtenerDescripcionDetallada(codServicio, idioma);
	}
	
	public void updatePosition(){
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		//Dimension screenSize = new Dimension(1024,740);
		
		this.setLocation((screenSize.width - this.getWidth())/2,
				(screenSize.height - this.getHeight())/2);
		
	}
	
	// obtiene la descripcion detallada del servicio pasado como parametro al 
	// constructor.
	private void obtenerDescripcionDetallada(String codServicio, String idioma) {
		// la descripcion detallada a devolver
		String descripcionDetallada = null;
		
		try {
			// se obtiene la descripcion detallada
			descripcionDetallada = ServicioManager.instance().buscarDescripcion(codServicio,idioma);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		// se asigna el html al editor para que lo muestre al hacerse visible.
		this.editor.setHTMLContent(descripcionDetallada);
	}

	private String obtenerTituloServicio(String codServicio, String idioma) {
		// el titulo del servicio a devolver		
		String tituloServicio = null;
		// entidad idioma que se obtiene mediante la descripcion "idioma"
		Idioma idiomaEnt = null;
			
		try {
			// se obtiene el idioma
			idiomaEnt = IdiomaManager.instance().getIdiomaByDescripcion(idioma);
			
			// si se encontro el idioma...
			if (idiomaEnt != null){
				// obtengo el titulo del servicio pasando el codigo del servicio y
				// el codigo de la entidad idioma recien recibida
				tituloServicio = ServicioIdiomaManager.instance().getDescripcionServicio(codServicio,idiomaEnt.getCodigo());
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
				
		return tituloServicio;	
	}

	public void setHTML(String html){
		this.editor.setHTMLContent(html);
	}
	
	private void createAndAddTopPanel(List descriptionLines) {
		this.topPanel = new JPanel();
		this.topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

		this.editor = new BasicHTMLEditor(descriptionLines);		
		this.editor.setPreferredSize(new Dimension(800,600));
		this.topPanel.setVisible(true);
		this.topPanel.add(this.editor);
		this.getContentPane().add(this.topPanel);
	}

	private void createAndAddBottomPanel() {
		this.bottomPanel = new JPanel();
		this.bottomPanel.setLayout(new BoxLayout(this.bottomPanel,
				BoxLayout.X_AXIS));

		this.okButton = new JButton("Aceptar");
		this.okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnOkActionPerformed(arg0);
			}
		});

		this.cancelButton = new JButton("Cancelar");
		this.cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCancelActionPerformed(arg0);
			}
		});

		this.bottomPanel.add(this.okButton);
		this.bottomPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		this.bottomPanel.add(this.cancelButton);

		this.bottomPanel.setVisible(true);

		this.getContentPane().add(this.bottomPanel);
	}

	protected void btnCancelActionPerformed(ActionEvent arg0) {
		if (MessageUtil.showYesNoMessage(this,
				"¿Está seguro de que desea cancelar?", "Salir del editor") == true) {
			this.setVisible(false);
		}		
	}

	protected void btnOkActionPerformed(ActionEvent arg0) {
		this.descriptionLines = this.editor.getDescriptionLines();
		this.setVisible(false);		
		/*
		String texto = this.editor.getHTMLContent().toString();
		
		if (texto == null || !texto.contains("<li>")){
			MessageUtil.showWarningMessage(this, "La descripción detallada es " +
					"vacía\no no contiene ningún item");			
		}else{
			this.textReturned = texto;
			this.setVisible(false);
		}
		*/
	}
/*
	public String getTextReturned() {
		return textReturned;
	}	
*/
	
	
	public static void main(String[] args) {
		//PopupEditorHTML popup = new PopupEditorHTML(null, "Proyector de TV",html);
		//popup.setVisible(true);

		//System.out.println("Texto: " + popup.getTextReturned());
	}
	
	private static String html = "<html><head></head><body><p style='margin-top: 0'><ul><li></li><li></li></ul></p></body></html>";

	public List getDescriptionLines() {
		return descriptionLines;
	}

	public void setDescriptionLines(List descriptionLines) {
		this.descriptionLines = descriptionLines;
	}

}
