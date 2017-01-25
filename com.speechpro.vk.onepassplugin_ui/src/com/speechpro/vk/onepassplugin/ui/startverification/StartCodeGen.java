package com.speechpro.vk.onepassplugin.ui.startverification;

import com.avaya.sce.callflow.extension.DataConnectorCodeGenerator;
import com.avaya.sce.callflow.extension.DataConnectorFlowItem;

public class StartCodeGen extends DataConnectorCodeGenerator {

	public StringBuilder generateConnectorItem(DataConnectorFlowItem item,
			int indentLevel) {
		StringBuilder sb = new StringBuilder();
		StartFlowItem callOnePassServer = (StartFlowItem) item;
		String personConstValue = callOnePassServer.getProperty(StartFlowItem.PROP_ID_CONST);
		String personVarValue = callOnePassServer.getProperty(StartFlowItem.PROP_ID_VAR);
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
				"actions.add(new onepassplugin.StartingVerification(\"");
		sb.append(callOnePassServer.getProperty(StartFlowItem.PROP_PASSWORD_VAR));
		sb.append("\", \"");
		sb.append(callOnePassServer.getProperty(StartFlowItem.PROP_PASSWORD_VARFIELD));
		sb.append("\", \"");
		sb.append(callOnePassServer.getProperty(StartFlowItem.PROP_VERIFICATIONID_VAR));
		sb.append("\", \"");
		sb.append(callOnePassServer.getProperty(StartFlowItem.PROP_VERIFICATIONID_VARFIELD));
		sb.append("\", \"");
		sb.append(personVar);
		sb.append("\", \"");
		sb.append(callOnePassServer.getProperty(StartFlowItem.PROP_ID_VARFIELD));
		sb.append("\" ," + Boolean.toString(personIsConstant));
		sb.append(").setDebugId(" + item.getFlowObjectId() + "));");
		return sb;
	}

}
