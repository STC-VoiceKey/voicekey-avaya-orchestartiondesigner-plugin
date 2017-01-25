package onepassplugin;


import org.apache.log4j.Logger;

import util.ApiCreator;

import com.avaya.sce.runtime.IPluggableExecutable;
import com.avaya.sce.runtimecommon.Debugging;
import com.avaya.sce.runtimecommon.IRuntimeSession;
import com.avaya.sce.runtimecommon.IVariableField;
import com.avaya.sce.runtimecommon.VariableName;
import com.speechpro.biometric.platform.onepass.api.PersonApi;
import com.speechpro.biometric.platform.onepass.api.OnePassApi;

public class DeletePerson extends Debugging implements IPluggableExecutable{
	
	private static final Logger LOG = Logger.getLogger(DeletePerson.class);

	private String resultVar;
	private String resultField;
	private String personVar;
	private String personField;
	private boolean personIsConstant;
	

	public DeletePerson(String resultVar, String resultField, String personVar, String personField, boolean personIsConstant) {
		this.resultVar = 		resultVar;
		this.resultField = 		resultField;
		this.personVar = 		personVar;
		this.personField = 		personField;
		this.personIsConstant = personIsConstant;
}
	
	public void execute(IRuntimeSession mySession) {
		try {
			
			if ((resultField != null) && (resultField.equals("") == true)) {
				resultField = null;
			}
			
			/* set fields to null if it they are empty */
			if ((personField != null) && (personField.equals("") == true)) {
				personField = null;
			}
			String personId = "";
			if (personIsConstant == true) {
				personId = personVar;
			} else {
				IVariableField person = mySession.getVariableField(new VariableName(personVar, personField));
				personId = person.getStringValue();
			}
			
			IVariableField result = mySession.getVariableField(new VariableName(resultVar, resultField));
			
			System.out.print("I'm going to delete person: " + personId);
			LOG.info(String.format("Person with id %s will be deleted", personId));
			result.setValue(delete(personId));
			
		} catch (Exception e) {
			mySession.throwRTException("Person Exists error:", e);
		}	
	}
	
    private static boolean delete (String personId)  {
    	boolean deleted = false;
    	try {
    		OnePassApi api = ApiCreator.createApi();
    		PersonApi personApi = api.person(personId);
    		deleted = personApi.delete();
    		LOG.info(String.format("Person with id %s deleted = %s",personId, deleted));
    	} catch (NullPointerException ex) {
    		LOG.error("OnePassApi hasn't been initialized: " + ex.toString());
    		ex.printStackTrace();
    	}
    return deleted;
    }

	public String getId() {

		return ("onepassplugin");
	}

	public String getTraceMessage() {
		return ("Onepass called");
	}
	
	

}

