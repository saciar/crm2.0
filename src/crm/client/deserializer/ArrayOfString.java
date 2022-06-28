package crm.client.deserializer;

import javax.xml.namespace.QName;

import org.apache.axis.description.ElementDesc;

import crm.client.util.SystemConfig;

public class ArrayOfString implements java.io.Serializable {
	private java.lang.String[] string;

	public ArrayOfString() {
	}

	public java.lang.String[] getString() {
		return string;
	}

	public void setString(java.lang.String[] string) {
		this.string = string;
	}

	public java.lang.String getString(int i) {
		return string[i];
	}

	public void setString(int i, java.lang.String value) {
		this.string[i] = value;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof ArrayOfString))
			return false;
		ArrayOfString other = (ArrayOfString) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && ((string == null && other.getString() == null) || (string != null && java.util.Arrays.equals(string, other.getString())));
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
		if (getString() != null) {
			for (int i = 0; i < java.lang.reflect.Array.getLength(getString()); i++) {
				java.lang.Object obj = java.lang.reflect.Array.get(getString(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(ArrayOfString.class);

	static {
		typeDesc.setXmlType(new QName(SystemConfig.getTypesNameSpace(), "ArrayOfString"));
		ElementDesc elemField = new ElementDesc();
		elemField.setFieldName("string");
		elemField.setXmlName(new QName(SystemConfig.getTypesNameSpace(), "string"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		typeDesc.addFieldDesc(elemField);
	}

	/**
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc() {
		return typeDesc;
	}

	/**
	 * Get Custom Serializer
	 */
	public static org.apache.axis.encoding.Serializer getSerializer(java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer
	 */
	public static org.apache.axis.encoding.Deserializer getDeserializer(java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

}