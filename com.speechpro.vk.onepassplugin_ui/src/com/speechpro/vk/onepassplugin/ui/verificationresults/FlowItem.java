package com.speechpro.vk.onepassplugin.ui.verificationresults;

import java.util.Set;

import com.avaya.sce.callflow.extension.DataConnectorFlowItem;
import com.avaya.sce.core.PasteOperation;
import com.avaya.sce.core.ResourceReference;

public class FlowItem extends DataConnectorFlowItem {
	
	public static final String PROP_VERIFICATIONID_VAR = "verificationid.varname";
	public static final String PROP_VERIFICATIONID_FIELD = "verificationid.fieldname";
	
	public static final String PROP_SCORE_VAR = "score.varname";
	public static final String PROP_SCORE_FIELD = "score.fieldname";
	
	
	public static final String[] VAR_PROP_IDS = { 	PROP_VERIFICATIONID_VAR, PROP_SCORE_VAR };

	public static final String[] VARFIELD_PROP_IDS = { 	PROP_VERIFICATIONID_FIELD, PROP_SCORE_FIELD};

	@Override
	public Set<ResourceReference> getResourceReferences() {
		return (getVariableReferences(VAR_PROP_IDS));
	}

	@Override
	public void updatePostPasteReferences(PasteOperation pasteOperation) {
		updatePostPasteVariableReferences(pasteOperation, VAR_PROP_IDS);
	}
	
	
}
