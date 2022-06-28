package crm.client.deserializer;

import java.io.Serializable;
import java.lang.reflect.Array;

import javax.xml.namespace.QName;

import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

import crm.client.util.SystemConfig;
import crm.libraries.report.OrdenFacturacion;

public class ArrayOfOrdenFacturacion  implements Serializable {
    private OrdenFacturacion[] evento;

    public ArrayOfOrdenFacturacion() {
    }

    public OrdenFacturacion[] getEvento() {
        return evento;
    }

    public void setEvento(OrdenFacturacion[] evento) {
        this.evento = evento;
    }

    public OrdenFacturacion getEvento(int i) {
        return evento[i];
    }

    public void setEvento(int i, OrdenFacturacion value) {
        this.evento[i] = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArrayOfOrdenFacturacion)) return false;
        ArrayOfOrdenFacturacion other = (ArrayOfOrdenFacturacion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((evento==null && other.getEvento()==null) || 
             (evento!=null &&
              java.util.Arrays.equals(evento, other.getEvento())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getEvento() != null) {
            for (int i=0;
                 i<Array.getLength(getEvento());
                 i++) {
                java.lang.Object obj = Array.get(getEvento(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static TypeDesc typeDesc = new TypeDesc(ArrayOfOrdenFacturacion.class);

    static {
		typeDesc.setXmlType(new QName(SystemConfig.getTypesNameSpace(), "ArrayOfOrdenFacturacion"));
		ElementDesc elemField = new ElementDesc();
		elemField.setFieldName("evento");
		elemField.setXmlName(new QName(SystemConfig.getTypesNameSpace(), "OrdenFacturacion"));
		//elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		typeDesc.addFieldDesc(elemField);
    	
    	/*
        FieldDesc field = new ElementDesc();
        field.setFieldName("evento");
        field.setXmlName(new QName(SystemConfig.getTypesNameSpace(), "Evento"));
        field.setMinOccursIs0(0);
        typeDesc.addFieldDesc(field);*/
    };

    /**
     * Return type metadata object
     */
    public static TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static Serializer getSerializer(String mechType, Class _javaType, QName _xmlType) {
        return new BeanSerializer(_javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static Deserializer getDeserializer(String mechType, Class _javaType, QName _xmlType) {
        return new BeanDeserializer(_javaType, _xmlType, typeDesc);
    }

}
