package com.speechpro.vk.onepassplugin.ui.sendmodelsample;

import com.avaya.sce.callflow.extension.DataConnectorCodeGenerator;
import com.avaya.sce.callflow.extension.DataConnectorFlowItem;

public class SendSampleCodeGen extends DataConnectorCodeGenerator {

	public StringBuilder generateConnectorItem(DataConnectorFlowItem item,
			int indentLevel) {
		StringBuilder sb = new StringBuilder();
		SendSampleFlowItem callOnePassServer = (SendSampleFlowItem) item;
		String personConstValue = callOnePassServer.getProperty(SendSampleFlowItem.PROP_ID_CONST);
		String personVarValue = callOnePassServer.getProperty(SendSampleFlowItem.PROP_ID_VAR);
		String personVar;
		boolean personIsConstant;
		
		if(personConstValue.equals("") == true){
			personIsConstant = false;
			personVar = personVarValue;
		}else{
			personIsConstant = true;
			personVar = personConstValue;
		}
		
		
		indent(sb, indentLevel,
				"actions.add(new onepassplugin.SendSample(\"");
		sb.append(callOnePassServer.getProperty(SendSampleFlowItem.PROP_RESULT_VAR));
		sb.append("\", \"");
		sb.append(callOnePassServer.getProperty(SendSampleFlowItem.PROP_RESULT_VARFIELD));
		sb.append("\", \"");
		sb.append(personVar);
		sb.append("\", \"");
		sb.append(callOnePassServer.getProperty(SendSampleFlowItem.PROP_ID_VARFIELD));
		sb.append("\",\"");
		sb.append(callOnePassServer.getProperty(SendSampleFlowItem.PROP_PATH_VAR));
		sb.append("\",\"");
		sb.append(callOnePassServer.getProperty(SendSampleFlowItem.PROP_PATH_VARFIELD));
		sb.append("\",\"");
		sb.append(callOnePassServer.getProperty(SendSampleFlowItem.PROP_PASSWORD_VAR));
		sb.append("\",\"");
		sb.append(callOnePassServer.getProperty(SendSampleFlowItem.PROP_PASSWORD_VARFIELD));
		sb.append("\" ," + Boolean.toString(personIsConstant));
		sb.append(").setDebugId(" + item.getFlowObjectId() + "));");
		return sb;
	}

}
