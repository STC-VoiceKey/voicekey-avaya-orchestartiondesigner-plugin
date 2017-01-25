package com.speechpro.vk.onepassplugin.ui.startverification;

import java.util.Set;
import java.util.Map;

import com.avaya.sce.callflow.extension.DataConnectorFlowItem;
import com.avaya.sce.core.PasteOperation;
import com.avaya.sce.core.ResourceReference;

public class StartFlowItem extends DataConnectorFlowItem {

	public static final String PROP_VERIFICATIONID_VAR = "verificationid.varname";
	public static final String PROP_VERIFICATIONID_VARFIELD = "verificationid.varfield";
	public static final String PROP_PASSWORD_VAR = "password.varname";
	public static final String PROP_PASSWORD_VARFIELD = "password.varfield";
	public static final String PROP_ID_VAR = "id.varname";
	public static final String PROP_ID_VARFIELD = "id.varfield";
	public static final String PROP_ID_CONST = "id.const";
	public static final String OP_DESC = "call OnePass";
	
	public static final String[] VAR_PROP_IDS = { PROP_VERIFICATIONID_VAR, PROP_PASSWORD_VAR, PROP_ID_VAR };

	public static final String[] VARFIELD_PROP_IDS = { PROP_VERIFICATIONID_VARFIELD, PROP_ID_VARFIELD, PROP_PASSWORD_VARFIELD };

	/*
	 * Returns the variable referenced by the Hits SMS item so that the variable
	 * can be copied across projects.
	 * 
	 * @see
	 * com.avaya.sce.callflow.internal.flowitem.FlowItem#getResourceReference()
	 */
	@Override
	public Set<ResourceReference> getResourceReferences() {
		return (getVariableReferences(VAR_PROP_IDS));
	}

	/*
	 * Override to update the variable reference if the paste operation resulted
	 * in renaming the referenced variable.
	 * 
	 * @see
	 * com.avaya.sce.callflow.internal.AbstractFlowObject#updatePostPasteReferences
	 * (com.avaya.sce.core.PasteOperation)
	 */
	@Override
	public void updatePostPasteReferences(PasteOperation pasteOperation) {
		updatePostPasteVariableReferences(pasteOperation, VAR_PROP_IDS);
	}

}

