package com.speechpro.vk.onepassplugin.ui.startverification;

import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.avaya.sce.callflow.ui.extensions.DataConnectorEditPart;

public class StartEditPart extends DataConnectorEditPart {

	@Override
	protected void addPropertyDescriptors(List<IPropertyDescriptor> list) {
		addVariablePropertyDescriptors(list, 
				new PropDescData(StartFlowItem.PROP_VERIFICATIONID_VAR, "Verification ID", "Verification session ID."), 
				new PropDescData(StartFlowItem.PROP_VERIFICATIONID_VARFIELD, "VerificationID Field", "Verification session ID field"),
				null);
		addVariablePropertyDescriptors(list, 
				new PropDescData(StartFlowItem.PROP_PASSWORD_VAR, "Password", "Password from OnePass"),
				new PropDescData(StartFlowItem.PROP_PASSWORD_VARFIELD, "Password Field", "Password field"),
				null);
		addVariablePropertyDescriptors(list,
				new PropDescData(StartFlowItem.PROP_ID_VAR, "Person ID", "Person ID value"),
				new PropDescData(StartFlowItem.PROP_ID_VARFIELD, "Person ID field", "Person ID field"),
				new PropDescData(StartFlowItem.PROP_ID_CONST, "Person ID const", "Person ID const"));
	}

	@Override
	public String getText() {
		StringBuilder sb = new StringBuilder("Start Verification < verification session id = \"");
		appendVarOrConstantToLabel(sb, null, StartFlowItem.PROP_VERIFICATIONID_VAR, StartFlowItem.PROP_VERIFICATIONID_VARFIELD);
		sb.append("\" person id = \"");
		appendVarOrConstantToLabel(sb, StartFlowItem.PROP_ID_CONST, StartFlowItem.PROP_ID_VAR, StartFlowItem.PROP_ID_VARFIELD);
		sb.append("\" password = \"" );
		appendVarOrConstantToLabel(sb, null, StartFlowItem.PROP_PASSWORD_VAR, StartFlowItem.PROP_PASSWORD_VARFIELD);
		sb.append("\">");
		return (sb.toString());
	}

}
