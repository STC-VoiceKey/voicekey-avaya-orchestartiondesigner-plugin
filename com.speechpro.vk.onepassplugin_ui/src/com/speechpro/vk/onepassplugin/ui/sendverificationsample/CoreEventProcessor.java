package com.speechpro.vk.onepassplugin.ui.sendverificationsample;

import com.avaya.sce.callflow.extension.DataConnectorProjectEventProcessor;
public class CoreEventProcessor extends DataConnectorProjectEventProcessor {

	public CoreEventProcessor(){
		enableVariableProcessing(FlowItem.PROP_PASSWORD_VAR, FlowItem.PROP_PASSWORD_FIELD);
		enableVariableProcessing(FlowItem.PROP_VERIFICATIONID_VAR, FlowItem.PROP_VERIFICATIONID_FIELD);
		enableVariableProcessing(FlowItem.PROP_RECORDING_VAR, FlowItem.PROP_RECORDING_FIELD);
		enableVariableProcessing(FlowItem.PROP_VERIFICATIONCOMPLETE_VAR, FlowItem.PROP_VERIFICATIONCOMPLETE_FIELD);
	}
}
