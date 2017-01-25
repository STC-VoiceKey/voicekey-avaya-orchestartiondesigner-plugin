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

public class PersonExists extends Debugging implements IPluggableExecutable {
	
	private static final Logger LOG = Logger.getLogger(PersonExists.class);

	private String destVar;
	private String destField;
	private String personVar;
	private String personField;
	private boolean personIsConstant;
	

	public PersonExists(String destVar, String destField, String personVar, String personField, boolean personIsConstant) {
		this.destVar = 			destVar;
		this.destField = 		destField;
		this.personVar = 		personVar;
		this.personField = 		personField;
		this.personIsConstant = personIsConstant;
		
	}
	
	public void execute(IRuntimeSession mySession) {
		String personID = "";
		try {
			/* set fields to null if it they are empty */
			if ((personField != null) && (personField.equals("") == true)) {
				personField = null;
			}
			if ((destField != null) && (destField.equals("") == true)) {
				destField = null;
			}
			IVariableField dest = mySession.getVariableField(new VariableName(destVar, destField));
			
			if (personIsConstant == true){
				personID = personVar;
			} else {
				IVariableField person = mySession.getVariableField(new VariableName(personVar, personField));
				personID = person.getStringValue();
			} 
			dest.setValue(personExists(personID));
			
		} catch (Exception e) {
			mySession.throwRTException("Person Exists error:", e);
		}	
		
	}
	
	private static boolean personExists(String personId){
		boolean personExists = false;
		try{
			OnePassApi api = ApiCreator.createApi();
			LOG.info(String.format("Person id = ", personId));
			PersonApi personApi = api.person(personId);
	    
			if (personApi.exists()) {
				boolean fullyEnrolled  = personApi.isFullEnrolled();
				LOG.info(String.format("Person with id %s exists", personId));
				if (fullyEnrolled) {
					LOG.info(String.format("Person with id %s is fully enrolled", personId));
					personExists = true;
				} else {
					LOG.info(String.format("Person with id %s is not fully enrolled", personId));
					personApi.delete();
					personExists = false;
				}
			} else {
				LOG.info(String.format("Person with id %s does not exist", personId));
				personExists = false;
	    	}
		} catch (NullPointerException ex) {
			LOG.error("OnePassApi hasn't been initialized: " + ex.toString());
			ex.printStackTrace();
		}
		return personExists;
	}

	public String getId() {
		return ("onepassplugin");
	}

	public String getTraceMessage() {
		return ("OnePass called");
	}
	
	public static void main (String [] args){
		personExists("35");
	}
	
}