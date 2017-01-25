package com.speechpro.vk.onepassplugin.ui.enrollment;

import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.avaya.sce.callflow.ui.extensions.DataConnectorEditPart;

public class PersonEnrollmentEditPart extends DataConnectorEditPart {

	@Override
	protected void addPropertyDescriptors(List<IPropertyDescriptor> list) {
		addVariablePropertyDescriptors(list, 
				new PropDescData(PersonEnrollmentFlowItem.PROP_RESULT_VAR, "Result Variable", "The variable that receives the result."), 
				new PropDescData(PersonEnrollmentFlowItem.PROP_RESULT_VARFIELD, "Result Variable Field", "The variable field that receives the result."), null);
		addVariablePropertyDescriptors(list, 
				new PropDescData(PersonEnrollmentFlowItem.PROP_ID_VAR, "Id value", "Person ID."),
				new PropDescData(PersonEnrollmentFlowItem.PROP_ID_VARFIELD, "ID value Field", "The person ID variable field."),
				new PropDescData(PersonEnrollmentFlowItem.PROP_ID_CONST, "ID Constant", "Person ID Constant"));
	}
	

	@Override
	public String getText() {
		StringBuilder sb = new StringBuilder("Enroll < result = \"");
		appendVarOrConstantToLabel(sb, null, PersonEnrollmentFlowItem.PROP_RESULT_VAR, PersonEnrollmentFlowItem.PROP_RESULT_VARFIELD);
		sb.append("\" person id =\"" );
		appendVarOrConstantToLabel(sb, PersonEnrollmentFlowItem.PROP_ID_CONST, PersonEnrollmentFlowItem.PROP_ID_VAR, PersonEnrollmentFlowItem.PROP_ID_VARFIELD);
		sb.append("\">");
		return (sb.toString());
	}

}

