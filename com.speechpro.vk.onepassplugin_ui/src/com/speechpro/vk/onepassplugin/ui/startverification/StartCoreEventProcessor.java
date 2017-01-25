package com.speechpro.vk.onepassplugin.ui.startverification;

import com.avaya.sce.callflow.extension.DataConnectorProjectEventProcessor;

public class StartCoreEventProcessor extends DataConnectorProjectEventProcessor {

	public StartCoreEventProcessor(){
		enableVariableProcessing(StartFlowItem.PROP_PASSWORD_VAR,
				StartFlowItem.PROP_PASSWORD_VARFIELD);
		enableVariableProcessing(StartFlowItem.PROP_VERIFICATIONID_VAR, StartFlowItem.PROP_VERIFICATIONID_VARFIELD);
		enableVariableProcessing(StartFlowItem.PROP_ID_VAR, StartFlowItem.PROP_ID_VARFIELD);
	}
}
