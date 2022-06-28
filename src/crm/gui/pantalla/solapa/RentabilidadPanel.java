package crm.gui.pantalla.solapa;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;

import crm.client.helper.UserRolesUtil;
import crm.client.managers.ComisionManager;
import crm.client.managers.CostoOperativoManager;
import crm.client.managers.GastosManager;
import crm.client.managers.LugarEventoManager;
import crm.client.managers.PresupuestosManager;
import crm.client.managers.VendedorManager;
import crm.client.util.DateConverter;
import crm.client.util.DateDiff;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.pantalla.Pantalla;
import crm.gui.pantalla.PantallaEmergenteGastoOperativo;
import crm.gui.pantalla.PantallaEmergenteObservSalas;
import crm.gui.tablerenderer.gastos.GastosAsistentesItem;
import crm.gui.tablerenderer.gastos.GastosHoteleriaItem;
import crm.gui.tablerenderer.gastos.GastosOperadoresItem;
import crm.gui.tablerenderer.gastos.GastosRepresentacionItem;
import crm.gui.tablerenderer.gastos.GastosSubContratacionesGeneralItem;
import crm.gui.tablerenderer.gastos.GastosSubContratacionesSalasItem;
import crm.gui.tablerenderer.gastos.GastosVariosItem;
import crm.gui.tablerenderer.gastos.GastosViaticosItem;
import crm.gui.tablerenderer.horarios.HorariosItem;
import crm.gui.tablerenderer.salas.SalaServicioItem;
import crm.gui.tablerenderer.salas.SalaServiciosTableModel;
import crm.libraries.abm.entities.CostoOperativo;
import crm.libraries.abm.entities.EstadoEvento;
import crm.libraries.abm.entities.LugarEvento;
import crm.libraries.abm.entities.PptoCambioEstado;
import crm.libraries.abm.entities.PptoEstadoActual;
import crm.libraries.abm.entities.Ppto_GastoAsistentes;
import crm.libraries.abm.entities.Ppto_GastoHoteleria;
import crm.libraries.abm.entities.Ppto_GastoOperador;
import crm.libraries.abm.entities.Ppto_GastoRepresentacion;
import crm.libraries.abm.entities.Ppto_GastoSC;
import crm.libraries.abm.entities.Ppto_GastoVarios;
import crm.libraries.abm.entities.Ppto_GastoViaticos;
import crm.libraries.abm.entities.Presupuesto;
import crm.services.sei.EstadoEventoManagerSEI;

public class RentabilidadPanel extends PanelGeneral implements PanelInterface{
	private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    //private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel name;
    private javax.swing.JLabel txtComisionesLugarEvento;
    private javax.swing.JLabel txtComisionesTerceros;
    private javax.swing.JLabel txtComisionesTotal;
    private javax.swing.JLabel txtRegalias;
    private javax.swing.JButton btnDetalles;
    private javax.swing.JLabel txtGastoOperativo;
    private javax.swing.JLabel txtGastoOtros;
    private javax.swing.JLabel txtGastoSubContrataciones;
    private javax.swing.JLabel txtGastoTotal;
    private javax.swing.JLabel txtRentabilidadBruta;
    private javax.swing.JLabel txtTotalesPorSala;
    private javax.swing.JLabel txtTotalesPorSalaAfter;
    private javax.swing.JLabel txtTotalesPorSalaBefore;
    private javax.swing.JLabel txtComisionesComercial;
    private javax.swing.JLabel txtPorcentajeRent;
    
    private Presupuesto presupuesto;	
    private Date fechaConfirmacion;
	private double totalesPorSalaBefore;	
	private double totalesPorSalaAfter;	
	private double gastoOperativo;	
	private double gastoAsistentes;	
	private double gastoOperadores;	
	private double gastoSubContrataciones;	
	private double gastoOtros;	
	private double comisionesLugar;	
	private double comisionesReferencia;
	private double comisionesVendedor;
	private double comisionesRegalias;
	
	private LugarEvento lugarElegido;
    private JPanel panel;    
    private MainPanelComercial mainPanel;	
    
    public void setMainPanel(MainPanelComercial mainPanel) {
		this.mainPanel = mainPanel;
	}     
    
    public RentabilidadPanel(JPanel pan){
    	panel = pan;
    }
    
    public void init(){
    	name = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTotalesPorSalaBefore = new javax.swing.JLabel();
        txtTotalesPorSalaAfter = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTotalesPorSala = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtGastoOperativo = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtRegalias = new javax.swing.JLabel();
        btnDetalles = new javax.swing.JButton();
        //jLabel12 = new javax.swing.JLabel();
        //txtGastoOperadores = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtGastoSubContrataciones = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtGastoOtros = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtGastoTotal = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtComisionesLugarEvento = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtComisionesTerceros = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        txtComisionesTotal = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtRentabilidadBruta = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtComisionesComercial = new javax.swing.JLabel();
        txtPorcentajeRent = new javax.swing.JLabel();

        name.setFont(new java.awt.Font("Tahoma", 1, 14));
        name.setText("Facturaci\u00f3n");

        btnDetalles.setText("Detalles");
        btnDetalles.addActionListener(new DetallesActionListener());
        
        jLabel1.setText("Facturaci\u00f3n del evento");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Total facturado");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel7.setText("Gastos");

        jLabel8.setText("Costo operativo");

        jLabel10.setText("Regalias");

        //jLabel12.setText("Gastos de operadores");

        jLabel14.setText("Gastos de subcontrataciones");

        jLabel16.setText("Otros gastos");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel18.setText("Total de gastos");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel20.setText("Comisiones");

        jLabel21.setText("Comisiones al lugar de evento");

        jLabel23.setText("Comisiones a terceros");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel25.setText("Total de comisiones");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel28.setText("Rentabilidad bruta");

        txtRentabilidadBruta.setFont(new java.awt.Font("Tahoma", 1, 14));
        
        jLabel2.setText("Comisiones del comercial");
        
        txtPorcentajeRent.setFont(new java.awt.Font("Tahoma", 1, 16));
    }
    
    public void initLayout(){
    	org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, name)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                    .add(jLabel1)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(txtTotalesPorSalaBefore)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 512, Short.MAX_VALUE)
                                    .add(jLabel5)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(txtTotalesPorSala)
                                    .add(102, 102, 102)))
                            .addContainerGap())
                        .add(layout.createSequentialGroup()
                            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                            .addContainerGap())
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                /*.add(layout.createSequentialGroup()
                                    .add(jLabel12)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(txtGastoOperadores))*/
                                .add(layout.createSequentialGroup()
                                    .add(jLabel14)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(txtGastoSubContrataciones))
                                .add(layout.createSequentialGroup()
                                    .add(jLabel20)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 749, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(layout.createSequentialGroup()
                                    .add(jLabel21)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(txtComisionesLugarEvento)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 664, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(layout.createSequentialGroup()
                                    .add(jLabel23)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(txtComisionesTerceros))
                                .add(layout.createSequentialGroup()
                                    .add(jLabel10)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(txtRegalias))
                                .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                                .add(jLabel7)
                                .add(layout.createSequentialGroup()
                                    .add(jLabel8)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(txtGastoOperativo)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(btnDetalles))
                                /*.add(layout.createSequentialGroup()
                                    .add(jLabel10)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(txtGastoAsistentes))*/
                                .add(layout.createSequentialGroup()
                                    .add(jLabel16)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(txtGastoOtros)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 551, Short.MAX_VALUE)
                                    .add(jLabel18)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(txtGastoTotal)
                                    .add(101, 101, 101)))
                            .addContainerGap())
                        .add(layout.createSequentialGroup()
                            .add(jSeparator3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                            .addContainerGap())
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                            .add(jLabel2)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(txtComisionesComercial)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 497, Short.MAX_VALUE)
                            .add(jLabel25)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(txtComisionesTotal)
                            .add(85, 85, 85))
                        .add(layout.createSequentialGroup()
                            .add(jLabel28)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(txtRentabilidadBruta)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(txtPorcentajeRent)
                            .addContainerGap())))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(name)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel1)
                        .add(txtTotalesPorSalaBefore)
                        .add(jLabel5)
                        .add(txtTotalesPorSala))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel7)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel8)
                        .add(txtGastoOperativo)
                        .add(btnDetalles))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                   /* .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel10)
                        .add(txtGastoAsistentes))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel12)
                        .add(txtGastoOperadores))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)*/
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel14)
                        .add(txtGastoSubContrataciones))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel16)
                        .add(txtGastoOtros)
                        .add(jLabel18)
                        .add(txtGastoTotal))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel20)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel21)
                        .add(txtComisionesLugarEvento))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel23)
                        .add(txtComisionesTerceros))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel10)
                        .add(txtRegalias))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel25)
                        .add(txtComisionesTotal)
                        .add(jLabel2)
                        .add(txtComisionesComercial))
                    .add(15, 15, 15)
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel28)
                        .add(txtRentabilidadBruta)
                        .add(txtPorcentajeRent))
                    .addContainerGap(134, Short.MAX_VALUE))
            );
  
    }
    
    public void setLugarElegido(LugarEvento lugarElegido) {
		this.lugarElegido = lugarElegido;
	}
    
private double round(double value, int decimalDigits) {
		
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(decimalDigits, BigDecimal.ROUND_UP);
	    return bd.doubleValue();
	}
	
	private Object[] ordenarHorarios(Object[] array)throws Exception{
		int i, j;

		Object[] temp;

		for (i = 1; i < array.length; i++){
			
			for (j = 0; j < array.length - i; j++) {
				Object[] p = (Object[]) array;
				Object[] horarioDato = (Object[]) p[j];
				Object[] horarioDato2 = (Object[]) p[j+1];
				
				Date fecha = DateConverter.convertStringToDate((String) horarioDato[0], "yyyy-MM-dd");
				Date fecha2 = DateConverter.convertStringToDate((String) horarioDato2[0], "yyyy-MM-dd");
				
				if (fecha2.before(fecha)) {
					temp = (Object[]) p[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
		return array;

	}

	private StringBuffer detalle =  new StringBuffer();
	
    private double calcularCO(String nroppto){
		try {
			detalle.setLength(0);

			double armado = 0.0;
			double operacion = 0.0;
			double cantPersonasArm = 0.0;
			double cantPersonasOp = 0.0;
			double totalCostoEquipo =0.0;

			double hsExtras=0;
			double hsNormales=0;
			long hsPruebas =0;
			
			long codServicio=0;
			int cantidad=0;
			String fechaDesde="";
			String fechaHasta="";
			double armadoXServ=0d;		
			double operacionXServ=0d;
			double costoUnitXServ=0d;
			double duracionXServ=0d;
			double horasFijas=0d;				
			double costoHsHombreNormal=0d;
			double costoHsHombreExtra=0d;
			double horasPruebasXDefault=0d;
			String fechaPrueba="";	
			long cantHorasNormalesOp=0;
			long cantHorasExtrasOp=0;
			
			GastosManager manager = GastosManager.instance();
			List list = mainPanel.getPanelesSala();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				SalaPanel salaModel = (SalaPanel)it.next();
				List<SalaServicioItem> listservicios = salaModel.getTableModel().getRows();
				Iterator itserv =listservicios.iterator();
				while(itserv.hasNext()){
					SalaServicioItem servicio = (SalaServicioItem)itserv.next();
					codServicio = Long.parseLong(servicio.getServicioCodigo());
					cantidad = servicio.getCantidad();
					fechaDesde = salaModel.getModel().getFechaDeInicio().concat(" "+salaModel.getModel().getHoraDeInicio()+":00");
					fechaHasta = salaModel.getModel().getFechaDeFinalizacion().concat(" "+salaModel.getModel().getHoraDeFinalizacion()+":00");;				
					fechaPrueba = salaModel.getModel().getFechaPrueba().concat(" "+salaModel.getModel().getHoraPrueba()+":00");;
					
					Object[] costos = manager.getCostosXServicio(Long.parseLong(servicio.getServicioCodigo()));
					for(int i=0; i<costos.length; i++){
						Object[] p = (Object[]) costos;
						Object[] costo = (Object[])p[i];
						armadoXServ = (Double)costo[0];
						operacionXServ = (Double)costo[2];
						costoUnitXServ = (Double)costo[3];
						duracionXServ = (Double)costo[4];
						horasFijas = (Double)costo[5]; //     horas de carga de camion, transalado, etc = 5.83
						costoHsHombreNormal = (Double)costo[6]; //    valor de la hora normal trabajada = 54.55
						costoHsHombreExtra = (Double)costo[7]; //    valor de la hora extra trabajada = 109.1
					}
					//ARMADO
					System.out.println("------------------------Servicio cod. "+codServicio+"------------------------------");
					detalle.append("------------------------Servicio cod. "+codServicio+"------------------------------\n");
					System.out.println("Cant.item: "+cantidad);
					detalle.append("Cant.item: "+cantidad+"\n");
					System.out.println("Campo Armado: "+armadoXServ);						
					detalle.append("Campo Armado: "+armadoXServ+"\n");
					double personaNecesarias = cantidad * armadoXServ;
					System.out.println("personas necesarias: "+personaNecesarias);
					detalle.append("personas necesarias: "+personaNecesarias+"\n");
					cantPersonasArm+=personaNecesarias;

					//OPERACION
					System.out.println("Campo operacion: "+operacionXServ);
					detalle.append("Campo operacion: "+operacionXServ+"\n");
					double personasNecesariasOp = cantidad * operacionXServ;
					System.out.println("personas necesarias Op: "+personasNecesariasOp);
					detalle.append("personas necesarias Op: "+personasNecesariasOp+"\n");
					cantPersonasOp+=personasNecesariasOp;						
					
					//COSTO EQUIPO
					
					hsPruebas = DateDiff.calcularHorasTotales(fechaPrueba, fechaDesde);
					if(hsPruebas<=0){
						hsPruebas = ((Double)horasPruebasXDefault).longValue();
					}
					
					long cantHorasTotales = DateDiff.calcularHorasTotales(fechaDesde, fechaHasta);
					System.out.println("Cant. de horas totales de equipo funcionando: "+cantHorasTotales);
					detalle.append("Cant. de horas totales de equipo funcionando: "+cantHorasTotales+"\n");
					double valCURedondeado=0;
					if(duracionXServ != 0 ){

						valCURedondeado= (costoUnitXServ / duracionXServ) * (cantHorasTotales+horasFijas+hsPruebas);
					}
					else{
						valCURedondeado=0;
					}
					System.out.println("Costo unitario: "+round(valCURedondeado,2)+"\n");
					detalle.append("Costo unitario: "+round(valCURedondeado,2)+"\n\n");
					totalCostoEquipo+=round(valCURedondeado,2);
					
				}
				
				List<HorariosItem> listhorarios=salaModel.getModel().getHorarios();  
				Iterator horiterator= listhorarios.iterator();
				while(horiterator.hasNext()){
					HorariosItem item = (HorariosItem)horiterator.next();
					long dif=0;
					if(item.getHoraHasta()!=null){
						if(item.getHoraDesde()!=null)
							dif = DateDiff.calcularHorasTotales(item.getFecha()+" "+ item.getHoraDesde()+":00",item.getFecha()+" "+  item.getHoraHasta()+":00");
						else
							dif = DateDiff.calcularHorasTotales(item.getFecha()+" 00:00",item.getFecha()+" 00:00");
						//dif = DateDiff.calcularHorasTotales(item.getFecha()+" "+ item.getHoraDesde()+":00",item.getFecha()+" "+  item.getHoraHasta()+":00");
					}
					else{
						if(item.getHoraDesde()!=null)
							dif = DateDiff.calcularHorasTotales(item.getFecha()+" "+ item.getHoraDesde()+":00",item.getFecha()+" 00:00");
						else
							dif = DateDiff.calcularHorasTotales(item.getFecha()+" 00:00",item.getFecha()+" 00:00");
					}
					if (dif+hsPruebas <= 9) {
						cantHorasNormalesOp += 9;
					} else{
						cantHorasExtrasOp += dif - 9;
						cantHorasNormalesOp += 9;					
					}
				}
				try {							
					DateDiff dateDiff = DateDiff.calcularFechas(DateConverter.convertStringToDate(fechaDesde, "yyyy-MM-dd"), DateConverter.convertStringToDate(fechaHasta, "yyyy-MM-dd"));
		    		int diasDeSala = dateDiff.getDayOnly()+1;
		    		if(listhorarios.size()<diasDeSala && listhorarios.size()!=0){
		    			int noCargado = listhorarios.size()-diasDeSala;
		    			cantHorasNormalesOp +=noCargado*9;
		    		}
		    		else if (listhorarios.size()==0){
		    			cantHorasNormalesOp +=diasDeSala*9;
		    		}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return -1.0d;
				}
			}
			
			
			
			
			/*horariosSinOrd = PresupuestosManager.instance().buscarHorariosCostos(nroppto);
			horarios = ordenarHorarios(horariosSinOrd);
								
			
			if(presupuestos != null){
				System.out.println("-----------------------------------PPTO "+nroppto+"--------------------------------------------------------------------------");
				for(int i=0; i<presupuestos.length; i++){
					Object[] p = (Object[]) presupuestos;
					Object[] presupuestoDato = (Object[])p[i];
					
					codServicio = (Long)presupuestoDato[0];
					cantidad = (Integer)presupuestoDato[2];
					fechaDesde = (String)presupuestoDato[3];
					fechaHasta = (String)presupuestoDato[4];
					armadoXServ = (Double)presupuestoDato[5];
					operacionXServ = (Double)presupuestoDato[7];
					costoUnitXServ = (Double)presupuestoDato[8];
					duracionXServ = (Double)presupuestoDato[9];
					horasFijas = (Double)presupuestoDato[10]; //     horas de carga de camion, transalado, etc = 5.83
					costoHsHombreNormal = (Double)presupuestoDato[11]; //    valor de la hora normal trabajada = 54.55
					costoHsHombreExtra = (Double)presupuestoDato[12]; //    valor de la hora extra trabajada = 109.1
					horasPruebasXDefault = (Double)presupuestoDato[13]; //  cant de horas de pruebas si no tiene cargada horas de pruebas
					fechaPrueba = (String)presupuestoDato[14];
					
					//ARMADO
					System.out.println("Servicio cod. "+codServicio+"------------------------------");
					
					System.out.println("-----------------------------------------------------------");
					System.out.println("Cant.item: "+cantidad+"\n");
					System.out.println("Campo Armado: "+armadoXServ);						
					//double valRedondeado = round((Integer)presupuestoDato[2] * (Double)presupuestoDato[5],0);
					double personaNecesarias = cantidad * armadoXServ;
					System.out.println("personas necesarias: "+personaNecesarias);
					cantPersonasArm+=personaNecesarias;

					//OPERACION
					System.out.println("Campo operacion: "+operacionXServ);
					double personasNecesariasOp = cantidad * operacionXServ;
					System.out.println("personas necesarias Op: "+personasNecesariasOp);
					cantPersonasOp+=personasNecesariasOp;						
					
					//COSTO EQUIPO
					hsPruebas = DateDiff.calcularHorasTotales(fechaPrueba, fechaDesde);
					if(hsPruebas<=0){
						hsPruebas = ((Double)horasPruebasXDefault).longValue();
					}
					
					long cantHorasTotales = DateDiff.calcularHorasTotales(fechaDesde, fechaHasta);
					System.out.println("Cant. de horas totales de equipo funcionando: "+cantHorasTotales);
					double valCURedondeado=0;
					if(duracionXServ != 0 ){

						valCURedondeado= (costoUnitXServ / duracionXServ) * (cantHorasTotales+horasFijas+hsPruebas);
					}
					else{
						valCURedondeado=0;
					}
					System.out.println("Costo unitario: "+round(valCURedondeado,2));
					totalCostoEquipo+=round(valCURedondeado,2);
					System.out.println("");
				}

				long cantHorasNormalesOp=0;
				long cantHorasExtrasOp=0;
				if (horarios != null) {
					for (int i = 0; i < horarios.length; i++) {
						Object[] p = (Object[]) horarios;
						Object[] horarioDato = (Object[]) p[i];

						long dif = DateDiff.calcularHorasTotales(
								(String) horarioDato[0]
										+" "+ (String) horarioDato[1]+":00",
								(String) horarioDato[0]
										+" "+  (String) horarioDato[2]+":00");
						//si es el primer dia
						if (i == horarios.length-1) {
							cantHorasExtrasOp = dif + hsPruebas -9;
							if(cantHorasExtrasOp<0){
								cantHorasExtrasOp=0;
							}
							cantHorasNormalesOp += 9;
						}
						else{
						if (dif <= 9) {
							cantHorasNormalesOp += 9;
						} else{
							cantHorasExtrasOp += dif - 9;
							cantHorasNormalesOp += 9;
						}
						}
					}
					try {							
						DateDiff dateDiff = DateDiff.calcularFechas(DateConverter.convertStringToDate(fechaDesde, "yyyy-MM-dd"), DateConverter.convertStringToDate(fechaHasta, "yyyy-MM-dd"));
			    		int diasDeSala = dateDiff.getDayOnly()+1;
			    		if(horarios.length<diasDeSala && horarios.length!=0){
			    			int noCargado = horarios.length-diasDeSala;
			    			cantHorasNormalesOp +=noCargado*9;
			    		}
			    		else if (horarios.length==0){
			    			cantHorasNormalesOp +=diasDeSala*9;
			    		}
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						return -1.0d;
					}
					
					
				}
				*/

				System.out.println("---------------------------------------------------------------------------------------");
				detalle.append("---------------------------------------------------------------------------------------\n");
				System.out.println("Cant. de horas de operacion normales totales: "+cantHorasNormalesOp);
				detalle.append("Cant. de horas de operacion normales totales: "+cantHorasNormalesOp+"\n");
				System.out.println("Cant. de horas de operacion extras totales: "+cantHorasExtrasOp);
				detalle.append("Cant. de horas de operacion extras totales: "+cantHorasExtrasOp+"\n");
				armado=round(cantPersonasArm,0) * costoHsHombreNormal * horasFijas;

				hsNormales = cantHorasNormalesOp * costoHsHombreNormal;
				hsExtras = cantHorasExtrasOp * costoHsHombreExtra;						
				
				operacion = round(cantPersonasOp,0) * (hsExtras + hsNormales);
				
				System.out.println("Cant de $ de hs extras: $"+hsExtras);
				detalle.append("Cant de $ de hs extras: $"+hsExtras+"\n");
				System.out.println("Cant de $ de hs normales: $"+hsNormales+"\n");
				detalle.append("Cant de $ de hs normales: $"+hsNormales+"\n\n");
				
				System.out.println("---------------------------------------------------------------------------------------");
				detalle.append("---------------------------------------------------------------------------------------\n");
				System.out.println("-------------------------------------TOTALES-------------------------------------------");
				detalle.append("-------------------------------------TOTALES-------------------------------------------\n");
				
				System.out.println("PERSONAS NECESARIAS ARMADO: "+round(cantPersonasArm,0));
				detalle.append("PERSONAS NECESARIAS ARMADO: "+round(cantPersonasArm,0)+"\n");
				System.out.println("PERSONAS NECESARIAS OP: "+cantPersonasOp);
				detalle.append("PERSONAS NECESARIAS OP: "+cantPersonasOp+"\n");
				System.out.println("TOTAL ARMADO EVENTO: "+armado);
				detalle.append("TOTAL ARMADO EVENTO: "+armado+"\n");
				System.out.println("TOTAL OPERACION EVENTO: "+operacion);
				detalle.append("TOTAL OPERACION EVENTO: "+operacion+"\n");
				System.out.println("TOTAL COSTO EQUIPOS: "+totalCostoEquipo);
				detalle.append("TOTAL COSTO EQUIPOS: "+totalCostoEquipo+"\n");
				
				System.out.println("-------------------------------------------------------------------------------------");
				detalle.append("-----------------------------------------------------------------------------------------\n");
				
				double total = armado + operacion + totalCostoEquipo;
				System.out.println("TOTAL --------> "+total);
				detalle.append("TOTAL --------> "+total+"\n");
				
				return armado + operacion + totalCostoEquipo;
			/*}
			else
				return -1.0d;*/
		}catch (RemoteException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			return -1.0d;
		} catch (Exception exc) {
			// TODO Auto-generated catch block
			System.out.println("numero de presupuesto: "+nroppto);
			exc.printStackTrace();
			return -1.0d;
		}
	}	
    
    public void calculateData() {

		List list = null;
		Iterator it = null;
		// -----GENERALES----------------------------------------------
		list = mainPanel.getSalasCreated();		
		// --------FACTURACION--------------------------------------------------
		totalesPorSalaBefore = mainPanel.getTotalEvento();
		totalesPorSalaAfter = 0.0;			

		double totalesPorSala = totalesPorSalaBefore + totalesPorSalaAfter;

		// -----COSTO
		// OPERATIVO---------------------------------------------------------------
		gastoOperativo = 5675670.0;
		double costoOperativo = 0.0;

		gastoOperativo = calcularCO(presupuesto.getNumeroDePresupuesto().toString());
		// -----ASISTENTES---------------------------------------------------------------
		gastoAsistentes = 0.0;
		
		// -----OPERADORES---------------------------------------------------------------
		gastoOperadores = 0.0;
		/*if(mainPanel.getPanelGastosOperadores() == null){
			Set gastos = presupuesto.getGastosOperador();
			if (gastos != null) {
				Iterator itOperador = gastos.iterator();
				while (itOperador.hasNext()) {
					Ppto_GastoOperador gasto = (Ppto_GastoOperador) itOperador.next();
					gastoOperadores += Double.parseDouble(gasto.getCosto());
				}
			}
		}
		else {
			list = mainPanel.getPanelGastosOperadores().getRows();
			it = list.iterator();
			while (it.hasNext()) {
				GastosOperadoresItem item = (GastosOperadoresItem) it.next();
				gastoOperadores += item.getCosto();
			}
		}*/
		// -----SUBCONTRATACIONES---------------------------------------------------------------
		gastoSubContrataciones = 0.0;
		if(mainPanel.getPanelGastosSubcontGral() == null){
			Set gastos = presupuesto.getGastosSC();
			if (gastos != null) {
				Iterator itSCSalas = gastos.iterator();
				while (itSCSalas.hasNext()) {
					Ppto_GastoSC gasto = (Ppto_GastoSC) itSCSalas.next();
					gastoSubContrataciones += Double.parseDouble(gasto.getCosto());
				}
			}
		}
		else {
		list = mainPanel.getPanelGastosSubcontGral().getRowGenerales();
		it = list.iterator();
		while (it.hasNext()) {
			GastosSubContratacionesSalasItem item = (GastosSubContratacionesSalasItem) it
					.next();
			gastoSubContrataciones += item.getCosto();
		}
		}

		// ------OTROS---------------------------------------------
		gastoOtros = 0.0;
		if(mainPanel.getPanelGastosHoteleria() == null){
			Set gastos = presupuesto.getGastosHoteleria();
			if(gastos != null){
				Iterator itHoteleria = gastos.iterator();
				while (itHoteleria.hasNext()) {
					Ppto_GastoHoteleria gasto = (Ppto_GastoHoteleria) itHoteleria.next();
					gastoOtros += Double.parseDouble(gasto.getCosto());
				}
			}
		}
		else{
			list = mainPanel.getPanelGastosHoteleria().getRows();
			it = list.iterator();
			while (it.hasNext()) {
				GastosHoteleriaItem item = (GastosHoteleriaItem) it.next();
				gastoOtros += item.getCosto();
			}
		}
		if(mainPanel.getPanelGastosVarios() == null){
			Set gastos = presupuesto.getGastosVarios();
			if(gastos != null){
				Iterator itVarios = gastos.iterator();
				while (itVarios.hasNext()) {
					Ppto_GastoVarios gasto = (Ppto_GastoVarios) itVarios.next();
					gastoOtros += Double.parseDouble(gasto.getCosto());
				}
			}
		}
		else{
			list = mainPanel.getPanelGastosVarios().getRows();
			it = list.iterator();
			while (it.hasNext()) {
				GastosVariosItem item = (GastosVariosItem) it.next();
				gastoOtros += item.getCosto();
			}
		}
		if(mainPanel.getPanelGastosViaticos() == null){
			Set gastos = presupuesto.getGastosViaticos();
			if(gastos != null){
				Iterator itViaticos = gastos.iterator();
				while (itViaticos.hasNext()) {
					Ppto_GastoViaticos gasto = (Ppto_GastoViaticos) itViaticos.next();
					gastoOtros += Double.parseDouble(gasto.getCosto());
				}
			}
		}
		else{
			list = mainPanel.getPanelGastosViaticos().getRows();
			it = list.iterator();
			while (it.hasNext()) {
				GastosViaticosItem item = (GastosViaticosItem) it.next();
				gastoOtros += item.getCosto();
			}
		}
		
		double gastoTotal = gastoAsistentes + gastoOperadores
		+ gastoSubContrataciones + gastoOtros+ gastoOperativo;
		
		//------COMISIONES Y RENTABILIDAD----------------------------------
		ComisionManager comisionManager = ComisionManager.instance();
		comisionesLugar= 0.0;
		comisionesReferencia = 0.0;
		comisionesVendedor = 0.0;
		comisionesRegalias = 0.0;
		
		double totalComisiones = 0.0;
		double rentabilidadBruta = 0.0;
		double comisionesVendedor = 0.0;
		double porcentaRentabilidad = 0.0; 
		double porcentajeRegalias = 0.0;
		try{
			String codLugar = null;
			String porcentajeVendedor = "5.0";
			//-------------COMISIONES A LUGAR EVENTO-----------------------------
			if(mainPanel.getPanelLugarEvento() == null){
				codLugar = presupuesto.getLugarDelEvento().getCodigo();
			}
			else if(mainPanel.getPanelLugarEvento().getCodLugar() != null){
				codLugar= mainPanel.getPanelLugarEvento().getCodLugar();
			}
				String codigoLugarComision = LugarEventoManager.instance().getCodigoLugarComisionById(codLugar);
				//String porcentajeLugar = comisionManager.getPorcentajeByCodVendedor(codigoLugarComision);		
				if(codigoLugarComision != null)
					comisionesLugar = totalesPorSala * Double.parseDouble(codigoLugarComision) / 100;
			
			
			//------COMISIONES A TERCEROS---------------------------------	
			if(mainPanel.getPanelGastosRepresentacion() == null){
				Set gastos = presupuesto.getGastosRepresentacion();
				if(gastos != null){
					Iterator itRepresentacion = gastos.iterator();
					while (itRepresentacion.hasNext()) {
						Ppto_GastoRepresentacion gasto = (Ppto_GastoRepresentacion) itRepresentacion.next();
						comisionesReferencia += totalesPorSala * Double.parseDouble(gasto.getCosto())/100;
					}
				}
			}
			else{
				list = mainPanel.getPanelGastosRepresentacion().getRows();
				it = list.iterator();
				while (it.hasNext()) {
					GastosRepresentacionItem item = (GastosRepresentacionItem) it
					.next();
					comisionesReferencia += totalesPorSala * item.getCosto()/ 100;
				}
			}
		
			/*//------COMISIONES VENDEDOR---------------------------------
			comisionesVendedor = totalesPorSala * Double.parseDouble(porcentajeVendedor) / 100;
			//------TOTAL COMISIONES---------------------------------
			totalComisiones = comisionesReferencia + comisionesLugar + comisionesVendedor;
		
			//-----RENTABILIDAD-------------------------------------------
			rentabilidadBruta = totalesPorSala - (gastoTotal + totalComisiones);			

			//------COMISIONES REGALIAS--------------------------------

			porcentajeRegalias = 10.0;
			
			comisionesRegalias = rentabilidadBruta * porcentajeRegalias / 100;*/
			//------COMISIONES VENDEDOR---------------------------------
			comisionesVendedor = (totalesPorSala * Double.parseDouble(porcentajeVendedor) / 100) + 
									(totalesPorSala * 2d / 100) +
									(totalesPorSala * 3.5d / 100);
			//------TOTAL COMISIONES---------------------------------
			
		
			//-----RENTABILIDAD-------------------------------------------
			rentabilidadBruta = totalesPorSala - (gastoSubContrataciones + comisionesReferencia + comisionesLugar);			
			//------COMISIONES REGALIAS--------------------------------

			porcentajeRegalias = 2.2;
			
			comisionesRegalias = totalesPorSala * porcentajeRegalias / 100;
			totalComisiones = comisionesReferencia + comisionesLugar + comisionesVendedor + comisionesRegalias;
			rentabilidadBruta = totalesPorSala - (gastoTotal +totalComisiones);
			
		}
		catch (RemoteException e){
			Util.errMsg(Main.getVentana(), "Error al cargar datos externos", e);
		}	
		
		//---------------------------------------------------------------------
		boolean isCold = false;
		
		if(!isCold){

			txtTotalesPorSalaBefore.setText(getTotalFormateado(totalesPorSalaBefore));
			txtTotalesPorSalaAfter.setText(getTotalFormateado(totalesPorSalaAfter));
			txtTotalesPorSala.setText(getTotalFormateado(totalesPorSala));
		
			txtGastoOperativo.setText(getTotalFormateado(gastoOperativo));
			//txtGastoAsistentes.setText(getTotalFormateado(gastoAsistentes));
			//txtGastoOperadores.setText(getTotalFormateado(gastoOperadores));
			txtGastoSubContrataciones.setText(getTotalFormateado(gastoSubContrataciones));
			txtGastoOtros.setText(getTotalFormateado(gastoOtros));
			txtGastoTotal.setText(getTotalFormateado(gastoTotal));
		
			txtRentabilidadBruta.setText(getTotalFormateado(rentabilidadBruta));
			setColorRentabilidad(txtRentabilidadBruta, rentabilidadBruta);

			txtComisionesLugarEvento.setText(getTotalFormateado(comisionesLugar));
			txtComisionesTerceros.setText(getTotalFormateado(comisionesReferencia));
			txtComisionesComercial.setText(getTotalFormateado(comisionesVendedor));
			txtRegalias.setText(getTotalFormateado(comisionesRegalias));
			txtComisionesTotal.setText(getTotalFormateado(totalComisiones));			
			
			if(rentabilidadBruta != 0)
				porcentaRentabilidad = rentabilidadBruta*100/totalesPorSala;
			else
				porcentaRentabilidad = 0.0;
			txtPorcentajeRent.setText("  ("+getPorcentajeRedondeado(porcentaRentabilidad)+ " %)");
			
		}
		//ProgressDialogUtil.closeProcessDialog();
    		//}
    	//}).start();
	}
    
    private String getPorcentajeRedondeado(double d){
    	java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
    	return df.format(d);
    }
    
    public double getComisionesLugar() {
		return comisionesLugar;
	}

	public void setComisionesLugar(double comisionesLugar) {
		this.comisionesLugar = comisionesLugar;
	}

	public double getComisionesReferencia() {
		return comisionesReferencia;
	}

	public void setComisionesReferencia(double comisionesReferencia) {
		this.comisionesReferencia = comisionesReferencia;
	}

	public double getGastoOperativo() {
		return gastoOperativo;
	}

	public void setGastoOperativo(double gastoOperativo) {
		this.gastoOperativo = gastoOperativo;
	}

	public double getGastoAsistentes() {
		return gastoAsistentes;
	}

	public void setGastoAsistentes(double gastoAsistentes) {
		this.gastoAsistentes = gastoAsistentes;
	}

	public double getGastoOperadores() {
		return gastoOperadores;
	}

	public void setGastoOperadores(double gastoOperadores) {
		this.gastoOperadores = gastoOperadores;
	}

	public double getGastoOtros() {
		return gastoOtros;
	}

	public void setGastoOtros(double gastoOtros) {
		this.gastoOtros = gastoOtros;
	}

	public double getGastoSubContrataciones() {
		return gastoSubContrataciones;
	}

	public void setGastoSubContrataciones(double gastoSubContrataciones) {
		this.gastoSubContrataciones = gastoSubContrataciones;
	}

	public double getTotalesPorSalaAfter() {
		return totalesPorSalaAfter;
	}

	public void setTotalesPorSalaAfter(double totalesPorSalaAfter) {
		this.totalesPorSalaAfter = totalesPorSalaAfter;
	}

	public double getTotalesPorSalaBefore() {
		return totalesPorSalaBefore;
	}

	public void setTotalesPorSalaBefore(double totalesPorSalaBefore) {
		this.totalesPorSalaBefore = totalesPorSalaBefore;
	}

	private void setColorRentabilidad(JLabel textField, double valor){
		if (valor < 0)
			textField.setForeground(Color.RED);
		else if (valor > 0)
			textField.setForeground(new Color(21, 101, 30));
		else textField.setForeground(Color.BLACK); 
	}
	
	public void setPresupuesto(Presupuesto p){
		try {
			presupuesto = p;
			
			//presupuesto.getRentabilidad()
			
			//------------------------------------------------------------------------------
			//-----------------------CALCULO LA FECHA DE CONFIRMACION-----------------------			
			//------------------------------------------------------------------------------
			PptoEstadoActual estadoActual = presupuesto.getEstado();
			if(estadoActual != null && estadoActual.getConfirmado() > 0){
			
				Set cambiosEstado = presupuesto.getCambiosEstado();
				Iterator it = cambiosEstado.iterator();
				while(it.hasNext()){
					PptoCambioEstado cambioEstado = (PptoCambioEstado)it.next();
					EstadoEvento estado = cambioEstado.getEstado();
					if(estado != null && estado.getCodigo().equals(EstadoEventoManagerSEI.CODIGO_ESTADO_CONFIRMADO)){				
						Date fechaConfirmacion = DateConverter.convertStringToDate(cambioEstado.getFecha(), "yyyy-MM-dd HH:mm:ss");
						if(fechaConfirmacion != null){
							if(this.fechaConfirmacion == null || this.fechaConfirmacion.before(fechaConfirmacion)){
								this.fechaConfirmacion = fechaConfirmacion;
							}
						}
					}
				}			
			}
			
			
			
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public String getTotalFormateado(double tot) {
		return getCurrencyFormat().format(tot);
	}

	private NumberFormat getCurrencyFormat() {
		NumberFormat currencyFormat;
		Locale l = new Locale("es","AR");
		currencyFormat = NumberFormat.getCurrencyInstance(l);
		return currencyFormat;
	}
    
	private class DetallesActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			PantallaEmergenteGastoOperativo pantalla = new PantallaEmergenteGastoOperativo(Main.getVentana(), "Detalle de Costo Operativo");

			pantalla.setObservacionesText(detalle.toString());
	        pantalla.setVisible(true);	    
			
		}
		
	}
}
