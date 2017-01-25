package com.speechpro.vk.onepassplugin.ui.deleteperson;

import com.avaya.sce.callflow.extension.DataConnectorProjectEventProcessor;
public class CoreEventProcessor extends DataConnectorProjectEventProcessor {

	public CoreEventProcessor(){
		enableVariableProcessing(FlowItem.PROP_ID_VAR, FlowItem.PROP_ID_VARFIELD);
	}
}
