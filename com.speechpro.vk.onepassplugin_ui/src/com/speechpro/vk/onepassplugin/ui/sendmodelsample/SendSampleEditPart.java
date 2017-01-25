package com.speechpro.vk.onepassplugin.ui.sendmodelsample;

import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.avaya.sce.callflow.ui.extensions.DataConnectorEditPart;

public class SendSampleEditPart extends DataConnectorEditPart {

	@Override
	protected void addPropertyDescriptors(List<IPropertyDescriptor> list) {
		addVariablePropertyDescriptors(list, 
				new PropDescData(SendSampleFlowItem.PROP_RESULT_VAR, "Result Variable", "The variable that receives the result."), 
				new PropDescData(SendSampleFlowItem.PROP_RESULT_VARFIELD, "Result Variable field", "The variable field that receives the result."), null);
		addVariablePropertyDescriptors(list, 
				new PropDescData(SendSampleFlowItem.PROP_ID_VAR, "Id value", "Person ID."),
				new PropDescData(SendSampleFlowItem.PROP_ID_VARFIELD, "ID value field", "The person ID variable field."),
				new PropDescData(SendSampleFlowItem.PROP_ID_CONST, "ID value const", "The person ID const"));
		addVariablePropertyDescriptors(list,
				new PropDescData(SendSampleFlowItem.PROP_PATH_VAR, "Path value", "Path value"),
				new PropDescData(SendSampleFlowItem.PROP_PATH_VARFIELD, "Path value field", "Path value variable field"),
				null);
		addVariablePropertyDescriptors(list,
				new PropDescData(SendSampleFlowItem.PROP_PASSWORD_VAR, "Password", ""),
				new PropDescData(SendSampleFlowItem.PROP_PASSWORD_VARFIELD, "Password field", ""),
				null);
	}
	

	@Override
	public String getText() {
		StringBuilder sb = new StringBuilder("Send Model Sample < result = \"");
		appendVarOrConstantToLabel(sb, null, SendSampleFlowItem.PROP_RESULT_VAR, SendSampleFlowItem.PROP_RESULT_VARFIELD);
		sb.append("\" person id = \"" );
		appendVarOrConstantToLabel(sb, null, SendSampleFlowItem.PROP_ID_VAR, SendSampleFlowItem.PROP_ID_VARFIELD);
		sb.append("\" recording path = \"");
		appendVarOrConstantToLabel(sb, null, SendSampleFlowItem.PROP_PATH_VAR, SendSampleFlowItem.PROP_PATH_VARFIELD);
		sb.append("\" password = \"");
		appendVarOrConstantToLabel(sb, null, SendSampleFlowItem.PROP_PASSWORD_VAR, SendSampleFlowItem.PROP_PASSWORD_VARFIELD);
		sb.append("\">");
		return (sb.toString());
	}

}
