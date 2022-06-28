package crm.gui.pantalla.solapa;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.ClienteFacturacionManager;
import crm.client.managers.ClienteManager;
import crm.client.managers.CondIVAManager;
import crm.client.managers.LocalidadManager;
import crm.client.managers.PaisManager;
import crm.client.managers.PartidoManager;
import crm.client.managers.ProvinciaManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.client.validacion.ErrorList;
import crm.gui.Main;
import crm.gui.components.CondicionPagoComboBox;
import crm.gui.components.CustomTextField;
import crm.gui.components.FormaPagoComboBox;
import crm.gui.components.GradientButton;
import crm.gui.pantalla.BuscadorClientes;
import crm.gui.pantalla.BuscadorClientes;
import crm.gui.pantalla.BuscadorClientesEnPptoFact;
import crm.gui.pantalla.PantallaEditarCliente;
import crm.libraries.abm.entities.Cliente;
import crm.libraries.abm.entities.ClienteFacturacion;
import crm.libraries.abm.entities.CondIVA;
import crm.libraries.abm.entities.Presupuesto;

public class FacturacionPanel extends PanelGeneral implements PanelInterface{
	private GradientButton buscarCliente;
    private GradientButton editarCliente;
    private JToggleButton enviarOFAdicionales;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private CondicionPagoComboBox m_cmbCondicionPago;
    private FormaPagoComboBox m_cmbMedioPago;
    private javax.swing.JLabel m_lblCP;
    private javax.swing.JLabel m_lblCalle;
    private javax.swing.JLabel m_lblCondIva;
    private javax.swing.JLabel m_lblCuit;
    private javax.swing.JLabel m_lblDepto;
    private javax.swing.JLabel m_lblLocalidad;
    private javax.swing.JLabel m_lblNro;
    private javax.swing.JLabel m_lblNroCliente;
    private javax.swing.JLabel m_lblPais;
    private javax.swing.JLabel m_lblPartido;
    private javax.swing.JLabel m_lblPiso;
    private javax.swing.JLabel m_lblProvincia;
    private javax.swing.JLabel m_txtCPFact;
    private javax.swing.JLabel m_txtCalleFact;
    private javax.swing.JLabel m_txtClienteRazonSocial;
    private javax.swing.JLabel m_txtCodigoProveedor;
    private javax.swing.JLabel m_txtDeptoFact;
    private javax.swing.JLabel m_txtDiasHoraPago;
    private javax.swing.JLabel m_txtDomicilioPago;
    private javax.swing.JLabel m_txtLocalidadFact;
    private javax.swing.JLabel m_txtNroFact;
    private javax.swing.JLabel m_txtPaisFact;
    private javax.swing.JLabel m_txtPartidoFact;
    private javax.swing.JLabel m_txtPisoFact;
    private javax.swing.JLabel m_txtProvinciaFact;
    private javax.swing.JLabel m_txtResponsable;
    private javax.swing.JLabel m_txtTelResponsable;
    private javax.swing.JFormattedTextField m_txtAdelantoPesos;
    private javax.swing.JFormattedTextField m_txtAdelantoPorcentaje;    
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JCheckBox m_checkAdelantado;
    private javax.swing.JCheckBox m_checkAdelanto;
    private JButton m_btnRecargarCondiciones;    
    private JButton m_btnRecargarFormas;    
    
    private Presupuesto presupuesto;
    private JPanel panel;
    private MainPanelComercial main;
    
    private String codCondIvaElegido;
    //private String codCondPagoElegido;
    //private String codMedioPagoElegido;
    private String codLocalidadElegido;
    
    private Cliente clienteElegido;
    private ClienteFacturacion clienteFactElegido;
    
    public FacturacionPanel(JPanel pan){
		panel = pan;
	}
    
    public void setMainPanel(MainPanelComercial m){
    	main = m;
    }
    
    public void init(){
    	jLabel1 = new javax.swing.JLabel();
    	m_lblNroCliente = new javax.swing.JLabel();
    	jLabel3 = new javax.swing.JLabel();
    	m_txtClienteRazonSocial = new javax.swing.JLabel();
    	buscarCliente = new GradientButton("", Color.blue);
    	jLabel5 = new javax.swing.JLabel();
    	m_lblCuit = new javax.swing.JLabel();
    	jLabel7 = new javax.swing.JLabel();
    	m_lblCondIva = new javax.swing.JLabel();
    	jSeparator1 = new javax.swing.JSeparator();
    	jLabel9 = new javax.swing.JLabel();
    	jLabel10 = new javax.swing.JLabel();
    	m_lblCalle = new javax.swing.JLabel();
    	jLabel12 = new javax.swing.JLabel();
    	m_lblNro = new javax.swing.JLabel();
    	jLabel14 = new javax.swing.JLabel();
    	m_lblPiso = new javax.swing.JLabel();
    	jLabel16 = new javax.swing.JLabel();
    	m_lblDepto = new javax.swing.JLabel();
    	jLabel18 = new javax.swing.JLabel();
    	m_lblCP = new javax.swing.JLabel();
    	jLabel20 = new javax.swing.JLabel();
    	m_lblLocalidad = new javax.swing.JLabel();
    	jLabel22 = new javax.swing.JLabel();
    	m_lblPartido = new javax.swing.JLabel();
    	jLabel24 = new javax.swing.JLabel();
    	m_lblProvincia = new javax.swing.JLabel();
    	jLabel26 = new javax.swing.JLabel();
    	m_lblPais = new javax.swing.JLabel();
    	jSeparator2 = new javax.swing.JSeparator();
    	jLabel28 = new javax.swing.JLabel();
    	jLabel29 = new javax.swing.JLabel();
    	m_cmbCondicionPago = new CondicionPagoComboBox();
    	jLabel30 = new javax.swing.JLabel();
    	m_cmbMedioPago = new FormaPagoComboBox();
    	jLabel31 = new javax.swing.JLabel();
    	m_txtResponsable = new javax.swing.JLabel();
    	jLabel33 = new javax.swing.JLabel();
    	m_txtTelResponsable = new javax.swing.JLabel();
    	jLabel35 = new javax.swing.JLabel();
    	m_txtDiasHoraPago = new javax.swing.JLabel();
    	jLabel37 = new javax.swing.JLabel();
    	m_txtDomicilioPago = new javax.swing.JLabel();
    	jLabel39 = new javax.swing.JLabel();
    	m_txtCodigoProveedor = new javax.swing.JLabel();
    	editarCliente = new GradientButton("", Color.blue);
    	jLabel2 = new javax.swing.JLabel();
    	jLabel4 = new javax.swing.JLabel();
    	m_txtCalleFact = new javax.swing.JLabel();
    	jLabel8 = new javax.swing.JLabel();
    	m_txtNroFact = new javax.swing.JLabel();
    	jLabel13 = new javax.swing.JLabel();
    	m_txtPisoFact = new javax.swing.JLabel();
    	jLabel17 = new javax.swing.JLabel();
    	m_txtDeptoFact = new javax.swing.JLabel();
    	jLabel21 = new javax.swing.JLabel();
    	m_txtCPFact = new javax.swing.JLabel();
    	jLabel25 = new javax.swing.JLabel();
    	m_txtLocalidadFact = new javax.swing.JLabel();
    	jLabel32 = new javax.swing.JLabel();
    	m_txtPartidoFact = new javax.swing.JLabel();
    	jLabel36 = new javax.swing.JLabel();
    	m_txtProvinciaFact = new javax.swing.JLabel();
    	jLabel40 = new javax.swing.JLabel();
    	m_txtPaisFact = new javax.swing.JLabel();
    	jSeparator3 = new javax.swing.JSeparator();
    	jLabel42 = new javax.swing.JLabel();
    	jLabel6 = new javax.swing.JLabel();
        m_txtAdelantoPorcentaje = CustomTextField.getVaricionIntInstance();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        m_txtAdelantoPesos = CustomTextField.getDecimalInstance(10,2);
        m_checkAdelanto = new javax.swing.JCheckBox();
        m_checkAdelantado = new javax.swing.JCheckBox();
        enviarOFAdicionales = new JToggleButton();
        jLabel19 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        
        m_btnRecargarCondiciones = new JButton();
        m_btnRecargarFormas = new JButton(); 
        
        
    	jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("C\u00f3digo de cliente");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Raz\u00f3n social");

        buscarCliente.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        buscarCliente.setText("Buscar cliente");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("CUIT");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel7.setText("Condici\u00f3n de IVA");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13));
        jLabel9.setText("Domicilio legal");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("Calle");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel12.setText("Nro");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel14.setText("Piso");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel16.setText("Depto");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel18.setText("C\u00f3digo postal");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel20.setText("Localidad");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel22.setText("Partido");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel24.setText("Provincia");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel26.setText("Pais");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 13));
        jLabel28.setText("Informaci\u00f3n de pago");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel29.setText("Condici\u00f3n de pago");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel30.setText("Forma de pago");
        
        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel31.setText("Nombre del contacto de pago");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel33.setText("Tel\u00e9fono");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel35.setText("Fecha y hora de pago a proveedores");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel37.setText("Domicilio de pago");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel39.setText("C\u00f3digo de proveedor CRN");

        editarCliente.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        editarCliente.setText("Editar datos de facturaci\u00f3n");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13));
        jLabel2.setText("Domicilio de entrega de factura");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Calle");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setText("Nro");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel13.setText("Piso");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel17.setText("Depto");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel21.setText("C\u00f3digo postal");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel25.setText("Localidad");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel32.setText("Partido");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel36.setText("Provincia");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel40.setText("Pais");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 13));
        jLabel42.setText("Cliente a facturar");
    	
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel6.setText("Adelanto de pago");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setText("%");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel15.setText("en pesos $");
        
        m_txtAdelantoPorcentaje.setText("0");
        m_txtAdelantoPesos.setText("0");
        
    	m_cmbCondicionPago.loadItems();
    	m_cmbMedioPago.loadItems();

    	m_checkAdelanto.setText("OF de adelanto");
        m_checkAdelanto.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        m_checkAdelanto.setMargin(new java.awt.Insets(0, 0, 0, 0));
        m_checkAdelanto.setBackground(new Color(211,210,206));
        m_checkAdelanto.setEnabled(true);

        m_checkAdelantado.setText("Adelantado");
        m_checkAdelantado.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        m_checkAdelantado.setEnabled(false);
        m_checkAdelantado.setMargin(new java.awt.Insets(0, 0, 0, 0));
        m_checkAdelantado.setBackground(new Color(211,210,206));
        
        enviarOFAdicionales.setIcon(new javax.swing.ImageIcon(getUrlImagen("email_go.png")));
        enviarOFAdicionales.setText("Enviar OF de adicionales");
        enviarOFAdicionales.setEnabled(false);
        
        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel19.setText("Facturacion de adelantos y adicionales");
        
        m_btnRecargarCondiciones.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        m_btnRecargarFormas.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_refresh.png")));
        
    	createListeners();
    }
    
    public void initLayout(){
    	 org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
         panel.setLayout(layout);
         layout.setHorizontalGroup(
                 layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                 .add(layout.createSequentialGroup()
                     .addContainerGap()
                     .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                         .add(layout.createSequentialGroup()
                             .add(jSeparator4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 911, Short.MAX_VALUE)
                             .addContainerGap())
                         .add(layout.createSequentialGroup()
                             .add(jLabel42)
                             .add(410, 410, 410))
                         .add(layout.createSequentialGroup()
                             .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                 .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 911, Short.MAX_VALUE)
                                 .add(layout.createSequentialGroup()
                                     .add(jLabel1)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_lblNroCliente)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(jLabel3)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_txtClienteRazonSocial)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 608, Short.MAX_VALUE)
                                     .add(buscarCliente))
                                 .add(layout.createSequentialGroup()
                                     .add(jLabel5)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_lblCuit)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(jLabel7)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_lblCondIva)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                                 .add(jLabel2)
                                 .add(layout.createSequentialGroup()
                                     .add(jLabel4)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_txtCalleFact)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(jLabel8)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_txtNroFact)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(jLabel13)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_txtPisoFact)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(jLabel17)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_txtDeptoFact)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(jLabel21)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_txtCPFact))
                                 .add(layout.createSequentialGroup()
                                     .add(jLabel25)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_txtLocalidadFact)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(jLabel32)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_txtPartidoFact)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(jLabel36)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_txtProvinciaFact)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(jLabel40)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_txtPaisFact))
                                 .add(jSeparator3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 911, Short.MAX_VALUE)
                                 .add(jLabel9)
                                 .add(layout.createSequentialGroup()
                                     .add(jLabel10)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_lblCalle)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(jLabel12)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_lblNro)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(jLabel14)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_lblPiso)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(jLabel16)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_lblDepto)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(jLabel18)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_lblCP))
                                 .add(layout.createSequentialGroup()
                                     .add(jLabel20)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_lblLocalidad)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(jLabel22)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_lblPartido)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(jLabel24)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_lblProvincia)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(jLabel26)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_lblPais))
                                 .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 911, Short.MAX_VALUE)
                                 .add(jLabel28)
                                 .add(layout.createSequentialGroup()
                                     .add(jLabel29)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_cmbCondicionPago, 0, 378, Short.MAX_VALUE)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_btnRecargarCondiciones)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(jLabel30)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_cmbMedioPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 335, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_btnRecargarFormas))
                                 .add(layout.createSequentialGroup()
                                     .add(jLabel31)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_txtResponsable)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(jLabel33)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_txtTelResponsable))
                                 /*.add(layout.createSequentialGroup()
                                     .add(jLabel35)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_txtDiasHoraPago))
                                 .add(layout.createSequentialGroup()
                                     .add(jLabel37)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_txtDomicilioPago)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(jLabel39)
                                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                     .add(m_txtCodigoProveedor))*/)
                             .addContainerGap())
                         .add(layout.createSequentialGroup()
                             .add(m_checkAdelanto)
                             .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                             .add(m_checkAdelantado)
                             .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 196, Short.MAX_VALUE)
                             .add(editarCliente)
                             .add(372, 372, 372))
                         .add(layout.createSequentialGroup()
                             .add(jLabel6)
                             .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                             .add(m_txtAdelantoPorcentaje, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                             .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                             .add(jLabel11)
                             .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                             .add(jLabel15)
                             .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                             .add(m_txtAdelantoPesos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                             .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 321, Short.MAX_VALUE)
                             .add(enviarOFAdicionales)
                             .add(160, 160, 160))
                         .add(layout.createSequentialGroup()
                             .add(jLabel19)
                             .addContainerGap(703, Short.MAX_VALUE))))
             );
             layout.setVerticalGroup(
                 layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                 .add(layout.createSequentialGroup()
                     .addContainerGap()
                     .add(jLabel42)
                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                     .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                         .add(jLabel1)
                         .add(buscarCliente)
                         .add(m_lblNroCliente)
                         .add(jLabel3)
                         .add(m_txtClienteRazonSocial))
                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                     .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                         .add(jLabel5)
                         .add(m_lblCuit)
                         .add(jLabel7)
                         .add(m_lblCondIva))
                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                     .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                     .add(jLabel2)
                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                     .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                         .add(jLabel4)
                         .add(m_txtCalleFact)
                         .add(jLabel8)
                         .add(m_txtNroFact)
                         .add(jLabel13)
                         .add(m_txtPisoFact)
                         .add(jLabel17)
                         .add(m_txtDeptoFact)
                         .add(jLabel21)
                         .add(m_txtCPFact))
                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                     .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                         .add(jLabel25)
                         .add(m_txtLocalidadFact)
                         .add(jLabel32)
                         .add(m_txtPartidoFact)
                         .add(jLabel36)
                         .add(m_txtProvinciaFact)
                         .add(jLabel40)
                         .add(m_txtPaisFact))
                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                     .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                     .add(jLabel9)
                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                     .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                         .add(jLabel10)
                         .add(m_lblCalle)
                         .add(jLabel12)
                         .add(m_lblNro)
                         .add(jLabel14)
                         .add(m_lblPiso)
                         .add(jLabel16)
                         .add(m_lblDepto)
                         .add(jLabel18)
                         .add(m_lblCP))
                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                     .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                         .add(jLabel20)
                         .add(m_lblLocalidad)
                         .add(jLabel22)
                         .add(m_lblPartido)
                         .add(jLabel24)
                         .add(m_lblProvincia)
                         .add(jLabel26)
                         .add(m_lblPais))
                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                     .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                     .add(jLabel28)
                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                     .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                         .add(jLabel29)
                         .add(m_cmbMedioPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                         .add(m_btnRecargarCondiciones)
                         .add(jLabel30)
                         .add(m_cmbCondicionPago, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                         .add(m_btnRecargarFormas))
                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                     .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                         .add(jLabel31)
                         .add(m_txtResponsable)
                         .add(jLabel33)
                         .add(m_txtTelResponsable))
                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                     /*.add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                         .add(jLabel35)
                         .add(m_txtDiasHoraPago))
                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                     .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                         .add(jLabel37)
                         .add(m_txtDomicilioPago)
                         .add(jLabel39)
                         .add(m_txtCodigoProveedor))*/
                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                     .add(jSeparator4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                     .add(jLabel19)
                     .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                     .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                         .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                             .add(editarCliente)
                             .addContainerGap())
                         .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                             .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                 .add(jLabel6)
                                 .add(m_txtAdelantoPorcentaje, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                 .add(jLabel11)
                                 .add(jLabel15)
                                 .add(m_txtAdelantoPesos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                 .add(enviarOFAdicionales))
                             .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                             .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                 .add(m_checkAdelanto)
                                 .add(m_checkAdelantado))
                             .add(19, 19, 19))))
             );
             if(main.getPanelRentabilidad() != null){
            	 main.getPanelRentabilidad().calculateData();
            	 if(main.getPanelRentabilidad().getTotalesPorSalaAfter()>0d){
            		 enviarOFAdicionales.setEnabled(true);
            	 }
            	 if(presupuesto.getEstadoActual() != null){			
            		 if(presupuesto.getEstadoActual().getAdicionales() == 1){
            			 enviarOFAdicionales.setEnabled(false);
            		 }
            	 }
             }
    }
    
    private void createListeners(){
    	buscarCliente.addActionListener(new BuscarClienteAccion());
		editarCliente.addActionListener(new EditarClienteAction());
		m_txtAdelantoPorcentaje.addKeyListener(new PorcentajeKeyListener());
		m_txtAdelantoPesos.addKeyListener(new ValorKeyListener());	
		m_checkAdelanto.addActionListener(new CheckAdelantoListener());
		m_cmbMedioPago.addActionListener(new MedioPagoListener());
		m_cmbCondicionPago.addActionListener(new CondicionListener());
		enviarOFAdicionales.addActionListener(new AdicionalesActionListener());
		m_btnRecargarCondiciones.addActionListener(new RefreshCondicionesActionListener());
		m_btnRecargarFormas.addActionListener(new RefreshFormasActionListener());
    }
    
    public void setPresupuesto(Presupuesto p) {
    	this.presupuesto = p;	
    	if (!presupuesto.isNew()){
    		resetFields();
    		try {
    			clienteElegido = ClienteManager.instance().getClienteById(presupuesto.getFacturacion().getCodCliente());
    			clienteFactElegido = ClienteFacturacionManager.instance().getClienteFacturacionById(presupuesto.getFacturacion().getCodCliente());
    		} catch (RemoteException e) {
    			e.printStackTrace();
    		};
    	}
    	else{
    		setDatosFacturacion(null);			
    	}
		if(presupuesto.getAdelanto() != null){
			m_txtAdelantoPesos.setText(presupuesto.getAdelanto().getValor());
			m_txtAdelantoPorcentaje.setText(presupuesto.getAdelanto().getPorcentaje());			
		}
		if(presupuesto.getEstadoActual() != null){
			if(presupuesto.getEstadoActual().getAdelanto() == 1){
				m_checkAdelanto.setSelected(true);
				m_checkAdelanto.setEnabled(false);
				main.setBtnReenviarOFAdelantoEnabled(true);
			}
			else if(presupuesto.getAdelanto() != null){
				m_checkAdelanto.setSelected(false);
				m_checkAdelanto.setEnabled(true);
				main.setBtnReenviarOFAdelantoEnabled(false);
			}
		}
		if(presupuesto.getEstadoActual() != null){
			if(presupuesto.getEstadoActual().getAdelantado() == 1){
				m_checkAdelantado.setSelected(true);

			}
		}
		if(presupuesto.getEstadoActual() != null){			
			if(presupuesto.getEstadoActual().getAdicionales() == 1){
				enviarOFAdicionales.setEnabled(false);
			}
		}
	}
    
    public void changeStatesAdelanto(){
    	m_checkAdelanto.setSelected(false);
		m_checkAdelanto.setEnabled(true);
    }
    
    public void setPorcentajeAdelantoEnabled(boolean b){
    	m_txtAdelantoPorcentaje.setEnabled(b);
    }
    
    public void setValorAdelantoEnabled(boolean b){
    	m_txtAdelantoPesos.setEnabled(b);
    }
    
    public void setCheckAdelantoEnabled(boolean b){
    	m_checkAdelanto.setEnabled(b);
    }
    
    public void setCheckAdelantadoEnabled(boolean b){
    	m_checkAdelantado.setEnabled(b);
    }
    
    public void setClienteElegido(Cliente cliente) {
    	if (cliente != null) {
			setDatosFacturacion(cliente);				
		}
    }
    
    public void setDatosFacturacion(Cliente cliente){
		
		try{
			if(cliente != null){		
				//clienteFacElegido = cliente;
				m_lblNroCliente.setText(cliente.getCodigo());
				m_txtClienteRazonSocial.setText(cliente.getEmpresa());
				m_lblCuit.setText(cliente.getCuit());		
			
				CondIVA iva = CondIVAManager.instance().getCondIVAById(cliente.getIva());
				if(iva != null){
					codCondIvaElegido = cliente.getIva();
					m_lblCondIva.setText(iva.getDescripcion());
				}
				else
					m_lblCondIva.setText("");
				m_txtResponsable.setText(cliente.getPagoContacto());
				m_txtTelResponsable.setText(cliente.getPagoTelefono());		
				
				m_txtCalleFact.setText(cliente.getCalle());
				m_txtNroFact.setText(cliente.getNumero());
				m_txtPisoFact.setText(cliente.getPiso());
				m_txtDeptoFact.setText(cliente.getDepartamento());
				String s;
				if (cliente.getLocalidad() != null) {
					s = LocalidadManager.instance().getNombreLocalidadById(
							cliente.getLocalidad());
					codLocalidadElegido = cliente.getLocalidad();
					m_txtLocalidadFact.setText(s);
				}
				if (cliente.getPartido() != null) {
					s = PartidoManager.instance().getNombrePartidoById(
							cliente.getPartido());
					m_txtPartidoFact.setText(s);
				}

				if (cliente.getProvincia() != null) {
					s = ProvinciaManager.instance().getNombreProvinciaById(
							cliente.getProvincia());
					m_txtProvinciaFact.setText(s);
				}

				if (cliente.getPais() != null) {
					s = PaisManager.instance().getNombrePaisById(
							cliente.getPais());
					m_txtPaisFact.setText(s);
				}
				
				
				
				ClienteFacturacion clienteFac = ClienteFacturacionManager.instance().getClienteFacturacionById(cliente.getCodigo());			
				if (clienteFac != null){
					m_lblCalle.setText(clienteFac.getCalle());
					m_lblNro.setText(clienteFac.getNumero());
					m_lblPiso.setText(clienteFac.getPiso());
					m_lblDepto.setText(clienteFac.getDepto());
					m_lblCP.setText(clienteFac.getCodigoPostal());

					if (clienteFac.getLocalidad() != null) {
						s = LocalidadManager.instance().getNombreLocalidadById(
								clienteFac.getLocalidad());
						m_lblLocalidad.setText(s);
					}

					if (clienteFac.getPartido() != null) {
						s = PartidoManager.instance().getNombrePartidoById(
								clienteFac.getPartido());
						m_lblPartido.setText(s);
					}

					if (clienteFac.getProvincia() != null) {
						s = ProvinciaManager.instance().getNombreProvinciaById(
								clienteFac.getProvincia());
						m_lblProvincia.setText(s);
					}

					if (clienteFac.getPais() != null) {
						s = PaisManager.instance().getNombrePaisById(
								clienteFac.getPais());
						m_lblPais.setText(s);
					}
					
					m_txtDiasHoraPago.setText(clienteFac.getDiaHoraPago());
					m_txtDomicilioPago.setText(clienteFac.getDomicilioPago());
					m_txtCodigoProveedor.setText(clienteFac.getCodProveedor());
					
				}
				else vaciarDatosFacturacion();
			}
			else{
				m_lblNroCliente.setText(null);
				m_txtClienteRazonSocial.setText(null);
				m_lblCuit.setText(null);	
				m_lblCondIva.setText(null);
				m_txtResponsable.setText(null);
				m_txtTelResponsable.setText(null);
				vaciarDatosFacturacion();
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void vaciarDatosFacturacion(){
		m_lblCalle.setText(null);
		m_lblNro.setText(null);
		m_lblPiso.setText(null);
		m_lblDepto.setText(null);
		m_lblCP.setText(null);
		m_lblLocalidad.setText(null);
		m_lblProvincia.setText(null);
		m_lblPais.setText(null);
		m_lblPartido.setText(null);
		m_txtDiasHoraPago.setText(null);
		m_txtDomicilioPago.setText(null);
		m_txtCodigoProveedor.setText(null);
	}
	
	public void resetFields(){
		String codigoCliente = presupuesto.getFacturacion().getCodCliente();
		m_lblNroCliente.setText(codigoCliente);
		this.getClientePorCodigo(codigoCliente);
		if (presupuesto.getPago() != null){
			m_cmbMedioPago.setForeign(presupuesto.getPago().getCodMedioPago());
			m_cmbCondicionPago.setForeign(presupuesto.getPago().getCodCondicionPago());
		}
	}
	
	private Cliente getClientePorCodigo(String cod){
		Cliente c = null;
		try{
			c = ClienteManager.instance().getClienteById(cod);
			this.setDatosFacturacion(c);
		}
		catch(Exception e){
			Util.errMsg(Main.getVentana(), "Error al cargar datos externos", e);
		}
		return c;
	}
	
	public String getCalle(){
		return m_txtCalleFact.getText();
	}
	public String getNumero(){
		return m_txtNroFact.getText();
	}
	public String getPiso(){
		return m_txtPisoFact.getText();
	}
	public String getDepto(){
		return m_txtDeptoFact.getText();
	}
	public String getLocalidad(){
		return m_txtLocalidadFact.getText();
	}
	
	public String getResponsable(){
		return m_txtResponsable.getText();
	}
	
	public String getTelResponsable(){
		return m_txtTelResponsable.getText();
	}
	
	public String getDiasYHoraPago(){
		return m_txtDiasHoraPago.getText();
	}
	
	public String getDomicilioPago(){
		return m_txtDomicilioPago.getText();
	}
	
	public String getProveedor(){
		return m_txtCodigoProveedor.getText();
	}
	
	public String getMedioPago(){
		return m_cmbMedioPago.searchForeign();
	}
	
	public String getCondicionPago(){
		return m_cmbCondicionPago.searchForeign();
	}
	
	public String getCondIva(){
		return m_lblCondIva.getText();
	}
	
	public String getCuit(){
		return m_lblCuit.getText();
	}
	
	public String getNroCliente(){
		if (m_lblNroCliente.getText().length() >0)
			return m_lblNroCliente.getText();
		else return null;
	}
	
	public String getAdelantoValor(){
		return m_txtAdelantoPesos.getText();
	}
	
	public String getAdelantoPorcentaje(){
		return m_txtAdelantoPorcentaje.getText();
	}
	
	public String getRazonSocial(){
		return m_txtClienteRazonSocial.getText();
	}
	
	
	
	private void setAllLabelBlack(){
    	jLabel29.setForeground(Color.BLACK);
    	jLabel30.setForeground(Color.BLACK);
    	editarCliente.setForeground(Color.BLACK);
    	editarCliente.setColorFont(Color.BLACK);
    }
	
	public ErrorList validateRequiredFields() {
		setAllLabelBlack();
		if (main.isConfirmado()){
			ErrorList errors = new ErrorList();		

			/*if (StringUtils.isBlank(getResponsable()) ||
					StringUtils.isBlank(getTelResponsable()) ||
					StringUtils.isBlank(getDiasYHoraPago()) ||
					StringUtils.isBlank(getDomicilioPago()) ||
					StringUtils.isBlank(getCondIva())){
				editarCliente.setForeground(Color.RED);
				editarCliente.setColorFont(Color.RED);
				errors.addError("Complete los datos de facturación");
			}*/
				
			if (StringUtils.isBlank(getResponsable())){
				editarCliente.setForeground(Color.RED);
				editarCliente.setColorFont(Color.RED);
				errors.addError("Ingrese el nombre del contacto de pago");
			}
			if (StringUtils.isBlank(getTelResponsable())){
				editarCliente.setForeground(Color.RED);
				editarCliente.setColorFont(Color.RED);
				errors.addError("Ingrese el telefono del contacto de pago");
			}
			if (StringUtils.isBlank(getDiasYHoraPago())){
				editarCliente.setForeground(Color.RED);
				editarCliente.setColorFont(Color.RED);
				errors.addError("Ingrese el día y el horario de pago");
			}		
			if (StringUtils.isBlank(getDomicilioPago())){
				editarCliente.setForeground(Color.RED);
				editarCliente.setColorFont(Color.RED);
				errors.addError("Ingrese el domicilio de pago");
			}
			if (StringUtils.isBlank(getCondIva())){
				editarCliente.setForeground(Color.RED);
				editarCliente.setColorFont(Color.RED);
				errors.addError("Ingrese la condición de IVA del cliente");
			}
			if (StringUtils.isBlank(m_lblCalle.getText())){
				editarCliente.setForeground(Color.RED);
				editarCliente.setColorFont(Color.RED);
				errors.addError("Ingrese la calle del domicilio legal");
			}
			if (StringUtils.isBlank(m_lblNro.getText())){
				editarCliente.setForeground(Color.RED);
				editarCliente.setColorFont(Color.RED);
				errors.addError("Ingrese el nro de la calle del domicilio legal");
			}
			if (StringUtils.isBlank(m_lblLocalidad.getText())){
				editarCliente.setForeground(Color.RED);
				editarCliente.setColorFont(Color.RED);
				errors.addError("Ingrese la localidad del domicilio legal");
			}
			if (StringUtils.isBlank(getMedioPago())){
				jLabel30.setForeground(Color.RED);
				errors.addError("Seleccione un medio de pago.");
			}
			if (StringUtils.isBlank(getCondicionPago())){
				jLabel29.setForeground(Color.RED);
				errors.addError("Seleccione una condición de pago.");
			}
			
			return errors;
		}
		else return null;
		
	}
	
	public boolean isAdelanto(){
		return m_checkAdelanto.isEnabled();
	}
	
	public int getAdelanto() {
        return m_checkAdelanto.isSelected()?1:0;
    }
    public void setAdelanto(int i) {
        m_checkAdelanto.setSelected(i==1);
    }
    
    public int getAdelantado() {
        return m_checkAdelantado.isSelected()?1:0;
    }
    public void setAdelantado(int i) {
        m_checkAdelantado.setSelected(i==1);
    }
	
    public void setFormaDePagoValue(String value){
		if(!StringUtils.isBlank(value)){
			m_cmbMedioPago.setForeign(value);
		}
	}
	
	public void setCondicionDePagoValue(String value){
		if(!StringUtils.isBlank(value)){
			m_cmbCondicionPago.setForeign(value);
		}
	}
	
    //***********************************ACCIONES**********************************************
    private class BuscarClienteAccion implements ActionListener{

		public void actionPerformed(ActionEvent evt) {

			BuscadorClientesEnPptoFact buscador = new BuscadorClientesEnPptoFact(Main.getVentana(), FacturacionPanel.this);
			buscador.init();
			buscador.initComponent();
			buscador.setVisible(true);
			
		}
		
	}
	
	private class EditarClienteAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			final String codCliente = m_lblNroCliente.getText();
			if (!StringUtils.isBlank(codCliente)){
				/*ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_PPTO_TYPE);
				ProgressDialogUtil.launchProcessDialog(Main.getVentana());
				new Thread(new Runnable() {				
					public void run() {
						//Cliente clienteAnterior = getClientePorCodigo(codCliente);
						PantallaEditarCliente pantallaEditar = new PantallaEditarCliente(Main.getVentana());
						pantallaEditar.initComponents();
						pantallaEditar.setEditMode(codCliente);
						pantallaEditar.setVisible(true);
						getClientePorCodigo(codCliente);
						//setClienteModificado(clienteAnterior, clienteEditado);
						ProgressDialogUtil.closeProcessDialog();
					}				
				}).start();*/
				
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						Main.getVentana().getGlassPane().start();
						Thread performer = new Thread(new Runnable(){
							public void run(){
								PantallaEditarCliente pantallaEditar = new PantallaEditarCliente(Main.getVentana());
								pantallaEditar.initComponents();
								pantallaEditar.setEditMode(codCliente);
								pantallaEditar.setVisible(true);
								/*Cliente clienteEditado = */getClientePorCodigo(codCliente);
								Main.getVentana().getGlassPane().stop();
							}
						},"Editar cliente Facturacion");
						performer.start();
					}
				});

			}
			else Util.errMsg(Main.getVentana(),"Primero debe seleccionar un cliente",null);
		}
	}
	
	private class ValorKeyListener implements KeyListener{

		public void keyTyped(KeyEvent arg0) {
			
		}

		public void keyPressed(KeyEvent arg0) {
			
		}

		public void keyReleased(KeyEvent arg0) {
			//if(!StringUtils.isBlank(m_txtAdelantoPesos.getText())){
			if(!m_txtAdelantoPesos.getText().equals("0.00")){
				double valor = Double.parseDouble(m_txtAdelantoPesos.getText());
				m_txtAdelantoPorcentaje.setText(String.valueOf(valor*100/main.getTotalEvento()));
				if(main.getConfirmado() == 1 && !m_txtAdelantoPesos.getText().equals("0.00")){
					m_checkAdelanto.setEnabled(true);
				}
				else m_checkAdelanto.setEnabled(false);
				if(main.getOrdenDeServicio() == 1 && !m_txtAdelantoPesos.getText().equals("0.00")){
					main.setOrdenDeFacturacion(0);
					main.getM_checkFact().setEnabled(false);
					//main.setBtnReenviarOFAdelantoEnabled(true);
				}	
			}
			else{
				//m_txtAdelantoPesos.setText("0.00");
				m_txtAdelantoPorcentaje.setText("0");
				m_checkAdelanto.setEnabled(false);
				if(main.getOrdenDeServicio() == 1 && m_txtAdelantoPesos.getText().equals("0.00")){
					main.getM_checkFact().setEnabled(true);
					//main.setBtnReenviarOFAdelantoEnabled(false);
				}
			}		
		}
		
	}
	
	private class PorcentajeKeyListener implements KeyListener{

		public void keyTyped(KeyEvent arg0) {
						
		}

		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void keyReleased(KeyEvent arg0) {
			//if(!StringUtils.isBlank(m_txtAdelantoPorcentaje.getText())){
			if(!m_txtAdelantoPorcentaje.getText().equals("0") && !StringUtils.isBlank(m_txtAdelantoPorcentaje.getText())){			
				double porc = Double.parseDouble(m_txtAdelantoPorcentaje.getText());
				m_txtAdelantoPesos.setText(String.valueOf(main.getTotalEvento()*porc/100));	
				if(main.getConfirmado() == 1 && !m_txtAdelantoPorcentaje.getText().equals("0")){
					m_checkAdelanto.setEnabled(true);
				}
				else m_checkAdelanto.setEnabled(false);
				if(main.getOrdenDeServicio() == 1 && !m_txtAdelantoPorcentaje.getText().equals("0")){
					main.setOrdenDeFacturacion(0);
					main.getM_checkFact().setEnabled(false);
					//main.setBtnReenviarOFAdelantoEnabled(true);
				}				
			}
			else{
				m_txtAdelantoPesos.setText("0");
				m_txtAdelantoPorcentaje.setText("0");	
				m_checkAdelanto.setEnabled(false);
				if(main.getOrdenDeServicio() == 1 && m_txtAdelantoPorcentaje.getText().equals("0")){
					main.getM_checkFact().setEnabled(true);
					//main.setBtnReenviarOFAdelantoEnabled(false);
				}
			}
		}
		
	}
	
	private class CheckAdelantoListener implements ActionListener{

		public void actionPerformed(ActionEvent evt) {
			if (((JCheckBox)evt.getSource()).isSelected()){      	
				main.setBtnReenviarOFAdelantoEnabled(true);
	        }
	        else{
	        	main.setBtnReenviarOFAdelantoEnabled(false);		
	        }
			
		}
		
	}
	
	private class MedioPagoListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(main.getPanelReportes() != null)
				main.getPanelReportes().setFormaDePagoValue(m_cmbMedioPago.searchForeign());			
		}
		
	}
	
	private class CondicionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(main.getPanelReportes() != null)
				main.getPanelReportes().setCondicionDePagoValue(m_cmbCondicionPago.searchForeign());		
		}
		
		
	}
	
	public boolean haveAdicionales=false;
	
	private class AdicionalesActionListener implements ActionListener{
		
		
		
		public void actionPerformed(ActionEvent arg0) {
			main.getPanelRentabilidad().calculateData();
			if(main.getPanelRentabilidad().getTotalesPorSalaAfter()>0d){
				if(enviarOFAdicionales.isSelected() && enviarOFAdicionales.isEnabled()){
					//Util.errMsg(Main.getVentana(),"Se va a enviar la OF de adicionales",null);
					haveAdicionales=true;
				}
				else haveAdicionales=false;
					
			}
			else{
				Util.errMsg(Main.getVentana(),"Este presupuesto no tiene Adicionales",null);
			}
				
			
		}
		
	}

	/**
	 * @return Returns the clienteFacElegido.
	 */
	public Cliente getClienteElegido() {
		return clienteElegido;
	}
	
	/**
	 * @return Returns the clienteFacElegido.
	 */
	public ClienteFacturacion getClienteFactElegido() {
		return clienteFactElegido;
	}

	/**
	 * @return Returns the codCondIvaElegido.
	 */
	public String getCodCondIvaElegido() {
		return codCondIvaElegido;
	}

	/**
	 * @return Returns the codLocalidadElegido.
	 */
	public String getCodLocalidadElegido() {
		return codLocalidadElegido;
	}

	public boolean isHaveAdicionales() {
		return haveAdicionales;
	}

	public void setHaveAdicionales(boolean haveAdicionales) {
		this.haveAdicionales = haveAdicionales;
	}
	
	private class RefreshCondicionesActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			m_cmbCondicionPago.recargar();		
		}
		
	}
	private class RefreshFormasActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			m_cmbMedioPago.recargar();		
		}
		
	}

}
