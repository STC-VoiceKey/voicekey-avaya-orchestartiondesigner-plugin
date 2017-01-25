package com.speechpro.vk.onepassplugin.ui.personexists;

import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.avaya.sce.callflow.ui.extensions.DataConnectorEditPart;

public class EditPart extends DataConnectorEditPart {

	@Override
	protected void addPropertyDescriptors(List<IPropertyDescriptor> list) {
		addVariablePropertyDescriptors(list, 
				new PropDescData(FlowItem.PROP_RESULT_VAR, "Result Variable", "The variable that receives the result."), 
				new PropDescData(FlowItem.PROP_RESULT_VARFIELD, "Result Variable Field", "The variable field that receives the result."), 
				null);
		addVariablePropertyDescriptors(list, 
				new PropDescData(FlowItem.PROP_ID_VAR, "Id value", "Person ID."),
				new PropDescData(FlowItem.PROP_ID_VARFIELD, "ID value Field", "The person ID variable field."),
				new PropDescData(FlowItem.PROP_ID_CONST, "ID Constant", "Person ID Constant"));
	}
	

	@Override
	public String getText() {
		StringBuilder sb = new StringBuilder("Get Person < result = \"");
		appendVarOrConstantToLabel(sb, null, FlowItem.PROP_RESULT_VAR, FlowItem.PROP_RESULT_VARFIELD);
		sb.append("\" person id = \"" );
		appendVarOrConstantToLabel(sb, FlowItem.PROP_ID_CONST, FlowItem.PROP_ID_VAR, FlowItem.PROP_ID_VARFIELD);
		sb.append("\">");
		return (sb.toString());
	}

}
