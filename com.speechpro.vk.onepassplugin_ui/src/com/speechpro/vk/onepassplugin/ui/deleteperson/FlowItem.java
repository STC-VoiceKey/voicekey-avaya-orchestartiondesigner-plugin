package com.speechpro.vk.onepassplugin.ui.deleteperson;

import java.util.Set;
import com.avaya.sce.callflow.extension.DataConnectorFlowItem;
import com.avaya.sce.core.PasteOperation;
import com.avaya.sce.core.ResourceReference;

public class FlowItem extends DataConnectorFlowItem {
	
	public static final String PROP_RES_VAR = "result.varname";
	public static final String PROP_RES_VARFIELD = "result.varfield";
	public static final String PROP_ID_VAR = "id.varname";
	public static final String PROP_ID_VARFIELD = "id.varfield";
	public static final String PROP_ID_CONST = "id.const";
	
	public static final String[] VAR_PROP_IDS = { PROP_ID_VAR};

	public static final String[] VARFIELD_PROP_IDS = {PROP_ID_VARFIELD};
	
	@Override
	public Set<ResourceReference> getResourceReferences() {
		return (getVariableReferences(VAR_PROP_IDS));
	}
	
	@Override
	public void updatePostPasteReferences(PasteOperation pasteOperation) {
		updatePostPasteVariableReferences(pasteOperation, VAR_PROP_IDS);
	}
	
}

