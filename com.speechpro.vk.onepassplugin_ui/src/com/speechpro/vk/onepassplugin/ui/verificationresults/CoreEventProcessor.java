package com.speechpro.vk.onepassplugin.ui.verificationresults;

import com.avaya.sce.callflow.extension.DataConnectorProjectEventProcessor;
public class CoreEventProcessor extends DataConnectorProjectEventProcessor {
	
	public CoreEventProcessor(){
		enableVariableProcessing(FlowItem.PROP_VERIFICATIONID_VAR, FlowItem.PROP_VERIFICATIONID_FIELD);
		enableVariableProcessing(FlowItem.PROP_SCORE_VAR, FlowItem.PROP_SCORE_FIELD);

	}
	
}
