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
import com.speechpro.biometric.platform.onepass.util.SoundSender;

public class SendSample extends Debugging implements IPluggableExecutable {
	
	private static final Logger LOG = Logger.getLogger(SendSample.class);

	private String destVar;
	private String destField;
	private String personVar;
	private String personField;
	private String pathVar;
	private String pathField;
	private boolean personIsConstant;
	private String passwordVar;
	private String passwordField;
	
	public SendSample(	String destVar, String destField, 
						String personVar, String personField, 
						String pathVar, String pathField, 
						String passwordVar, String passwordField,
						boolean personIsConstant) {
		this.destVar = 			destVar;
		this.destField = 		destField;
		this.personVar = 		personVar;
		this.personField = 		personField;
		this.pathField = 		pathField;
		this.pathVar = 			pathVar;
		this.passwordVar = 		passwordVar;
		this.passwordField = 	passwordField;
		this.personIsConstant = personIsConstant;
		
	}

	public void execute(IRuntimeSession mySession){
		try {
			/* set fields to null if it they are empty */
			if ((personField != null) && (personField.equals("") == true)) {
				personField = null;
			}
			
			if ((pathField != null) && (pathField.equals("") == true)){
				pathField = null;
			}
			if ((passwordField != null) && (passwordField.equals("") == true)){
				passwordField = null;
			}
			/*result variable*/
			IVariableField destVarF = mySession.getVariableField(new VariableName(destVar, destField));
			IVariableField passwordVarF = mySession.getVariableField(new VariableName(passwordVar, passwordField));
			String password = passwordVarF.getStringValue();
			String id = "";
			/*if person ID is const, take the const value*/
			if (personIsConstant == true){
				id = personVar;
			} else {
				IVariableField personVarF = mySession.getVariableField(new VariableName(personVar, personField));
				id = personVarF.getStringValue();
			}
			
			IVariableField sourcePathVarF = mySession.getVariableField(new VariableName(pathVar, pathField));
			byte[] fileBytes = SoundSender.readAudioFromAvayaDD(sourcePathVarF.getStringValue());
			String data = SoundSender.convertBytesToBase64String(fileBytes);
			destVarF.setValue(sendSampleforModel(data, id, password));
			
		} catch (Exception e) {
			LOG.info(String.format("Error in Send Model Sample: %s", e.getStackTrace().toString()));
			mySession.throwRTException("SendSample class error:", e);
		}
	}
	
	private boolean sendSampleforModel(String dataBase64, String personId, String password){
		boolean sent = false;
    	try {
    		OnePassApi api = ApiCreator.createApi();
    		PersonApi personApi = api.person(personId);
    		sent = personApi.sendRegistrationVoice(password, dataBase64);	
    	} catch (NullPointerException ex) {
    		LOG.error("OnePassApi hasn't been initialized: " + ex.toString());
    		ex.printStackTrace();
    	}
		return sent;
	}

	public String getId() {
		return ("onepassplugin");
	}

	public String getTraceMessage() {
		return ("Send model sample class");
	}
	
}
