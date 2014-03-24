package com.stratio.streaming.utils;

import java.util.List;

import org.wso2.siddhi.query.api.definition.Attribute;
import org.wso2.siddhi.query.api.definition.Attribute.Type;
import org.wso2.siddhi.query.api.definition.StreamDefinition;
import org.wso2.siddhi.query.api.exception.AttributeNotExistException;
import org.wso2.siddhi.query.compiler.exception.SiddhiPraserException;

import com.stratio.streaming.messages.ColumnNameTypeValue;

public class SiddhiUtils {
	
	private static final String SIDDHI_TYPE_STRING 		= "STRING";
	private static final String SIDDHI_TYPE_BOOLEAN 	= "BOOLEAN";
	private static final String SIDDHI_TYPE_DOUBLE 		= "DOUBLE";
	private static final String SIDDHI_TYPE_INT 		= "INTEGER";
	private static final String SIDDHI_TYPE_LONG 		= "LONG";
	private static final String SIDDHI_TYPE_FLOAT 		= "FLOAT";
	
	
	private SiddhiUtils() {
		
	}
	

	
	public static Type decodeSiddhiType(String originalType) throws SiddhiPraserException {
		
		switch (originalType.toUpperCase()) {
			case SIDDHI_TYPE_STRING:
				return Attribute.Type.STRING;
			case SIDDHI_TYPE_BOOLEAN:
				return Attribute.Type.BOOL;
			case SIDDHI_TYPE_DOUBLE:
				return Attribute.Type.DOUBLE;
			case SIDDHI_TYPE_INT:
				return Attribute.Type.INT;
			case SIDDHI_TYPE_LONG:
				return Attribute.Type.LONG;
			case SIDDHI_TYPE_FLOAT:
				return Attribute.Type.FLOAT;
			default:
				throw new SiddhiPraserException("Unsupported Column type");
		}
		
	}
	
	public static Object[] getOrderedValues(StreamDefinition streamMetaData, List<ColumnNameTypeValue> columns) throws AttributeNotExistException {
		
		Object[] orderedValues = new Object[streamMetaData.getAttributeList().size()];
		
		for (ColumnNameTypeValue column : columns) {
			
//			if attribute does not exist, a AttributeNotExistException exception wil be thrown
			orderedValues[streamMetaData.getAttributePosition(column.getColumn())] = column.getValue();
		}

		return orderedValues;
		
	}

}
