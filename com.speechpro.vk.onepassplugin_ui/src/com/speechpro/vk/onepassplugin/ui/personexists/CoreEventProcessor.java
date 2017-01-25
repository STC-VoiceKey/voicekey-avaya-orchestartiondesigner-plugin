package com.speechpro.vk.onepassplugin.ui.personexists;

import com.avaya.sce.callflow.extension.DataConnectorProjectEventProcessor;

public class CoreEventProcessor extends DataConnectorProjectEventProcessor {
	public CoreEventProcessor() {
		enableVariableProcessing(FlowItem.PROP_RESULT_VAR,
				FlowItem.PROP_RESULT_VARFIELD);
	}

}
