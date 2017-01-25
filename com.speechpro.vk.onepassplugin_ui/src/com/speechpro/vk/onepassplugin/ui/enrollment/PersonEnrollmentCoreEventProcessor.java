package com.speechpro.vk.onepassplugin.ui.enrollment;

import com.avaya.sce.callflow.extension.DataConnectorProjectEventProcessor;

public class PersonEnrollmentCoreEventProcessor extends DataConnectorProjectEventProcessor {
	public PersonEnrollmentCoreEventProcessor() {
		enableVariableProcessing(PersonEnrollmentFlowItem.PROP_RESULT_VAR,
				PersonEnrollmentFlowItem.PROP_RESULT_VARFIELD);
	}

}
