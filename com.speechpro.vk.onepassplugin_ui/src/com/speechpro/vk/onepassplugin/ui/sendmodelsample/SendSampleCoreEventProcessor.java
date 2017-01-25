package com.speechpro.vk.onepassplugin.ui.sendmodelsample;

import com.avaya.sce.callflow.extension.DataConnectorProjectEventProcessor;

public class SendSampleCoreEventProcessor extends DataConnectorProjectEventProcessor {
	public SendSampleCoreEventProcessor() {
		enableVariableProcessing(SendSampleFlowItem.PROP_RESULT_VAR,
				SendSampleFlowItem.PROP_RESULT_VARFIELD);
	}
}
