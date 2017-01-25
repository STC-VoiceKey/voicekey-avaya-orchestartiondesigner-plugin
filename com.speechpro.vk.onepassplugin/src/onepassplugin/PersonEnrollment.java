package onepassplugin;

import org.apache.log4j.Logger;

import util.ApiCreator;

import com.avaya.sce.runtime.IPluggableExecutable;
import com.avaya.sce.runtimecommon.Debugging;
import com.avaya.sce.runtimecommon.IRuntimeSession;
import com.avaya.sce.runtimecommon.IVariableField;
import com.avaya.sce.runtimecommon.VariableName;
import com.speechpro.biometric.platform.onepass.api.OnePassApi;
import com.speechpro.biometric.platform.onepass.api.PersonApi;

public class PersonEnrollment extends Debugging implements IPluggableExecutable {
	
	private static final Logger LOG = Logger.getLogger(PersonEnrollment.class);

	private String destVar;
	private String destField;
	private String personVar;
	private String personField;
	private boolean personIsConstant;
	

	public PersonEnrollment(String destVar, String destField, String personVar, String personField, boolean personIsConstant) {
		this.destVar = 			destVar;
		this.destField = 		destField;
		this.personVar = 		personVar;
		this.personField = 		personField;
		this.personIsConstant = personIsConstant;
		
	}

	public void execute(IRuntimeSession mySession) {
		try {
			/* set fields to null if it they are empty */
			if ((personField != null) && (personField.equals("") == true)) {
				personField = null;
			}
			if ((destField != null) && (destField.equals("") == true)) {
				destField = null;
			}
			IVariableField dest = mySession.getVariableField(new VariableName(destVar, destField));
			String personID = "";
			if (personIsConstant == true){
				personID = personVar;
			} else {
				IVariableField person = mySession.getVariableField(personVar, personField);
				personID = person.getStringValue();
			} 
			dest.setValue(enrollPerson(personID));
		} catch (Exception e) {
			LOG.error(String.format("Error in PersonEnrollment: %s", e.getStackTrace().toString()));
			mySession.throwRTException("CallOnePass error:", e);
		}
	}

    private static boolean enrollPerson(String personID) {
    	boolean personEnrolled = false;
    	try {
    		OnePassApi api = ApiCreator.createApi();
    		PersonApi personApi = api.person(personID);
    		personEnrolled = personApi.createPerson();
		} catch (NullPointerException ex) {
			LOG.error("OnePassApi hasn't been initialized: " + ex.toString());
			ex.printStackTrace();
		}
    	return personEnrolled;
    }
	
	public String getId() {
		return ("onepassplugin");
	}

	public String getTraceMessage() {
		return ("OnePass called");
	}
	
}