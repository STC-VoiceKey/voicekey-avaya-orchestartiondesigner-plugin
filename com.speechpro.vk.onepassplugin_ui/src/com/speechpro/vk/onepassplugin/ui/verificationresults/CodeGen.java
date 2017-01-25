package com.speechpro.vk.onepassplugin.ui.verificationresults;

import com.avaya.sce.callflow.extension.DataConnectorCodeGenerator;
import com.avaya.sce.callflow.extension.DataConnectorFlowItem;
public class CodeGen extends DataConnectorCodeGenerator {

	@Override
	public StringBuilder generateConnectorItem (DataConnectorFlowItem item,
	int indentLevel) {
		StringBuilder sb = new StringBuilder();	
		FlowItem callOnePassServer = (FlowItem) item;
		indent(sb, indentLevel,
				"actions.add(new onepassplugin.VerificationResults(\"");
		sb.append(callOnePassServer.getProperty(FlowItem.PROP_VERIFICATIONID_VAR));
		sb.append("\", \"");
		sb.append(callOnePassServer.getProperty(FlowItem.PROP_VERIFICATIONID_FIELD));
		sb.append("\", \"");
		sb.append(callOnePassServer.getProperty(FlowItem.PROP_SCORE_VAR));
		sb.append("\", \"");
		sb.append(callOnePassServer.getProperty(FlowItem.PROP_SCORE_FIELD));
		sb.append("\").setDebugId(" + item.getFlowObjectId() + "));");
		return sb;
	}
}
