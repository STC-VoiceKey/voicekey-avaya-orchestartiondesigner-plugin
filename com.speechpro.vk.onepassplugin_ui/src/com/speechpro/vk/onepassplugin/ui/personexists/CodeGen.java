package com.speechpro.vk.onepassplugin.ui.personexists;

import com.avaya.sce.callflow.extension.DataConnectorCodeGenerator;
import com.avaya.sce.callflow.extension.DataConnectorFlowItem;
import com.speechpro.vk.onepassplugin.ui.personexists.FlowItem;

public class CodeGen extends DataConnectorCodeGenerator {

	public StringBuilder generateConnectorItem(DataConnectorFlowItem item,
			int indentLevel) {
		StringBuilder sb = new StringBuilder();
		FlowItem callOnePassServer = (FlowItem) item;
		String personConstValue = callOnePassServer.getProperty(FlowItem.PROP_ID_CONST);
		String personVarValue = callOnePassServer.getProperty(FlowItem.PROP_ID_VAR);
		String personVar;
		boolean personIsConstant;

		if (personConstValue.equals("") == true){
			personIsConstant = false;
			personVar = personVarValue;
		} else{
			personIsConstant = true;
			personVar = personConstValue;
		}
		
		indent(sb, indentLevel,
				"actions.add(new onepassplugin.PersonExists(\"");
		sb.append(callOnePassServer.getProperty(FlowItem.PROP_RESULT_VAR));
		sb.append("\", \"");
		sb.append(callOnePassServer.getProperty(FlowItem.PROP_RESULT_VARFIELD));
		sb.append("\", \"");
		sb.append(personVar);
		sb.append("\", \"");
		sb.append(callOnePassServer.getProperty(FlowItem.PROP_ID_VARFIELD));
		sb.append("\" ," + Boolean.toString(personIsConstant));
		sb.append(").setDebugId(" + item.getFlowObjectId() + "));");
		System.out.print(sb.toString());
		return sb;
	}

}
