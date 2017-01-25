package com.speechpro.vk.onepassplugin.ui.verificationresults;

import java.util.List;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import com.avaya.sce.callflow.ui.extensions.DataConnectorEditPart;

public class EditPart extends DataConnectorEditPart {

	@Override
	protected void addPropertyDescriptors(List<IPropertyDescriptor> list) {
		addVariablePropertyDescriptors(list, 
				new PropDescData(FlowItem.PROP_VERIFICATIONID_VAR, "Verification ID", "Verification session ID."), 
				new PropDescData(FlowItem.PROP_VERIFICATIONID_FIELD, "VerificationID field", "Verification session ID field"),
				null);
		addVariablePropertyDescriptors(list, 
				new PropDescData(FlowItem.PROP_SCORE_VAR, "Verification score", "Verification score"),
				new PropDescData(FlowItem.PROP_SCORE_FIELD, "Verification score field", "Verification score field"),
				null);
	}

	@Override
	public String getText() {
		StringBuilder sb = new StringBuilder("Verification Results < verification session id = \"");
		appendVarOrConstantToLabel(sb, null, FlowItem.PROP_VERIFICATIONID_VAR, FlowItem.PROP_VERIFICATIONID_FIELD);
		sb.append("\" verification score = \"" );
		appendVarOrConstantToLabel(sb, null, FlowItem.PROP_SCORE_VAR, FlowItem.PROP_SCORE_FIELD);
		sb.append("\">");
		return (sb.toString());
	}
	
}
