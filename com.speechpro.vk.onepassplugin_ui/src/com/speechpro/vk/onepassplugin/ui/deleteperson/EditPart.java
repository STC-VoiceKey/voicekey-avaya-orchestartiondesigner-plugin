package com.speechpro.vk.onepassplugin.ui.deleteperson;

import java.util.List;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import com.avaya.sce.callflow.ui.extensions.DataConnectorEditPart;

public class EditPart extends DataConnectorEditPart {

	@Override
	protected void addPropertyDescriptors(List<IPropertyDescriptor> list) {
		addVariablePropertyDescriptors(list, 
				new PropDescData(FlowItem.PROP_ID_VAR, "Person ID value", "ID value"),
				new PropDescData(FlowItem.PROP_ID_VARFIELD, "Person ID value field", "ID value field"),
				new PropDescData(FlowItem.PROP_ID_CONST, "ID Constant", "Person ID Constant"));
		addVariablePropertyDescriptors(list,
				new PropDescData(FlowItem.PROP_RES_VAR, "Delete var", "Delete person var"),
				new PropDescData(FlowItem.PROP_RES_VARFIELD, "Delete field", "Delete person field"),
				null);
	}

	@Override
	public String getText() {
		StringBuilder sb = new StringBuilder("Delete Person < result=\"");
		appendVarOrConstantToLabel(sb, null, FlowItem.PROP_RES_VAR, FlowItem.PROP_RES_VARFIELD);
		sb.append("\" person id = \"" );
		appendVarOrConstantToLabel(sb, FlowItem.PROP_ID_CONST, FlowItem.PROP_ID_VAR, FlowItem.PROP_ID_VARFIELD);
		sb.append("\">");
		return (sb.toString());
	}
}
