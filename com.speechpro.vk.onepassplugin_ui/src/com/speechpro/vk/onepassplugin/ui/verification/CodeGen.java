package com.speechpro.vk.onepassplugin.ui.verification;

import com.avaya.sce.callflow.extension.DataConnectorCodeGenerator;
import com.avaya.sce.callflow.extension.DataConnectorFlowItem;

public class CodeGen extends DataConnectorCodeGenerator {

	@Override
	public StringBuilder generateConnectorItem (DataConnectorFlowItem item,
	int indentLevel) {
		StringBuilder sb = new StringBuilder();	
		FlowItem callOnePassServer = (FlowItem) item;
		indent(sb, indentLevel,
				"actions.add(new onepassplugin.SendSampleVerification(\"");
		sb.append(callOnePassServer.getProperty(FlowItem.PROP_VERIFICATIONCOMPLETE_VAR));
		sb.append("\", \"");
		sb.append(callOnePassServer.getProperty(FlowItem.PROP_VERIFICATIONCOMPLETE_FIELD));
		sb.append("\", \"");
		sb.append(callOnePassServer.getProperty(FlowItem.PROP_RECORDING_VAR));
		sb.append("\", \"");
		sb.append(callOnePassServer.getProperty(FlowItem.PROP_RECORDING_FIELD));
		sb.append("\", \"");
		sb.append(callOnePassServer.getProperty(FlowItem.PROP_PASSWORD_VAR));
		sb.append("\", \"");
		sb.append(callOnePassServer.getProperty(FlowItem.PROP_PASSWORD_FIELD));
		sb.append("\", \"");
		sb.append(callOnePassServer.getProperty(FlowItem.PROP_VERIFICATIONID_VAR));
		sb.append("\", \"");
		sb.append(callOnePassServer.getProperty(FlowItem.PROP_VERIFICATIONID_FIELD));
		sb.append("\").setDebugId(" + item.getFlowObjectId() + "));");
		return sb;
	}

}
