package com.speechpro.vk.onepassplugin.ui.enrollment;

import com.avaya.sce.callflow.extension.DataConnectorCodeGenerator;
import com.avaya.sce.callflow.extension.DataConnectorFlowItem;

public class PersonEnrollmentCodeGen extends DataConnectorCodeGenerator {

	public StringBuilder generateConnectorItem(DataConnectorFlowItem item,
			int indentLevel) {
		StringBuilder sb = new StringBuilder();
		PersonEnrollmentFlowItem callOnePassServer = (PersonEnrollmentFlowItem) item;
		String personConstValue = callOnePassServer.getProperty(PersonEnrollmentFlowItem.PROP_ID_CONST);
		String personVarValue = callOnePassServer.getProperty(PersonEnrollmentFlowItem.PROP_ID_VAR);
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
				"actions.add(new onepassplugin.PersonEnrollment(\"");
		sb.append(callOnePassServer.getProperty(PersonEnrollmentFlowItem.PROP_RESULT_VAR));
		sb.append("\", \"");
		sb.append(callOnePassServer.getProperty(PersonEnrollmentFlowItem.PROP_RESULT_VARFIELD));
		sb.append("\", \"");
		sb.append(personVar);
		sb.append("\", \"");
		sb.append(callOnePassServer.getProperty(PersonEnrollmentFlowItem.PROP_ID_VARFIELD));
		sb.append("\" ," + Boolean.toString(personIsConstant));
		sb.append(").setDebugId(" + item.getFlowObjectId() + "));");
		return sb;
	}

}
