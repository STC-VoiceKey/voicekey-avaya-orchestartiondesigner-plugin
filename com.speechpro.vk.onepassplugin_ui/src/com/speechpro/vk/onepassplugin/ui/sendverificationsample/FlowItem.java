package com.speechpro.vk.onepassplugin.ui.sendverificationsample;

import java.util.Set;
import com.avaya.sce.callflow.extension.DataConnectorFlowItem;
import com.avaya.sce.core.PasteOperation;
import com.avaya.sce.core.ResourceReference;

public class FlowItem extends DataConnectorFlowItem {
	
	public static final String PROP_VERIFICATIONID_VAR = "verificationid.varname";
	public static final String PROP_VERIFICATIONID_FIELD = "verificationid.fieldname";
	
	public static final String PROP_VERIFICATIONCOMPLETE_VAR = "verificationcomplete.varname";
	public static final String PROP_VERIFICATIONCOMPLETE_FIELD = "verificationcomplete.fieldname";
	
	public static final String PROP_RECORDING_VAR = "recording.varname";
	public static final String PROP_RECORDING_FIELD = "recording.fieldname";
	
	public static final String PROP_PASSWORD_VAR = "password.varname";
	public static final String PROP_PASSWORD_FIELD = "password.fieldname";
	
	public static final String[] VAR_PROP_IDS = { 	PROP_VERIFICATIONID_VAR, 
													PROP_PASSWORD_VAR, 
													PROP_RECORDING_VAR, 
													PROP_PASSWORD_VAR };

	public static final String[] VARFIELD_PROP_IDS = { 	PROP_VERIFICATIONID_FIELD, 
														PROP_VERIFICATIONCOMPLETE_FIELD, 
														PROP_RECORDING_FIELD,
														PROP_PASSWORD_FIELD};
	
	@Override
	public Set<ResourceReference> getResourceReferences() {
		return (getVariableReferences(VAR_PROP_IDS));
	}
	
	@Override
	public void updatePostPasteReferences(PasteOperation pasteOperation) {
		updatePostPasteVariableReferences(pasteOperation, VAR_PROP_IDS);
	}
	
}

