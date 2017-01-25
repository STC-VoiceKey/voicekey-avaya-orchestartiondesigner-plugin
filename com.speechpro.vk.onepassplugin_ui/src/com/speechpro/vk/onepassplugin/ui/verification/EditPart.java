package com.speechpro.vk.onepassplugin.ui.verification;

import java.util.List;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import com.avaya.sce.callflow.ui.extensions.DataConnectorEditPart;

public class EditPart extends DataConnectorEditPart {

	@Override
	protected void addPropertyDescriptors(List<IPropertyDescriptor> list) {
		addVariablePropertyDescriptors(list, 
				new PropDescData(FlowItem.PROP_VERIFICATIONID_VAR, "Verification ID", "Verification session ID."), 
				new PropDescData(FlowItem.PROP_VERIFICATIONID_FIELD, "VerificationID Field", "Verification session ID field"),
				null);
		addVariablePropertyDescriptors(list, 
				new PropDescData(FlowItem.PROP_PASSWORD_VAR, "Password", "Password from OnePass"),
				new PropDescData(FlowItem.PROP_PASSWORD_FIELD, "Password Field", "Password field"),
				null);
		addVariablePropertyDescriptors(list,
				new PropDescData(FlowItem.PROP_VERIFICATIONCOMPLETE_VAR, "Verification result", "Verif res"),
				new PropDescData(FlowItem.PROP_VERIFICATIONCOMPLETE_FIELD, "Verification result field", "Verif res field"),
				null);
		addVariablePropertyDescriptors(list,
				new PropDescData(FlowItem.PROP_RECORDING_VAR, "Recording", "Rec"),
				new PropDescData(FlowItem.PROP_RECORDING_FIELD, "Recording field", "Rec field"),
				null);

	}

	@Override
	public String getText() {
		//StringBuilder sb = new StringBuilder("Send Verification Sample < verification session id = \"");
		
		StringBuilder sb = new StringBuilder("Send Verification Sample < result = \"");
		appendVarOrConstantToLabel(sb, null, FlowItem.PROP_VERIFICATIONCOMPLETE_VAR, FlowItem.PROP_VERIFICATIONCOMPLETE_FIELD);
		sb.append("\" verification session id = \"");
		appendVarOrConstantToLabel(sb, null, FlowItem.PROP_VERIFICATIONID_VAR, FlowItem.PROP_VERIFICATIONID_FIELD);
		sb.append("\" password = \"" );
		appendVarOrConstantToLabel(sb, null, FlowItem.PROP_PASSWORD_VAR, FlowItem.PROP_PASSWORD_FIELD);
		sb.append("\" recording path = \"");
		appendVarOrConstantToLabel(sb, null, FlowItem.PROP_RECORDING_VAR, FlowItem.PROP_RECORDING_FIELD);
		sb.append("\">");
		return (sb.toString());
	}

	
}
