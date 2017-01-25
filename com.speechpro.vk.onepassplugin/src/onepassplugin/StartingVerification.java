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
import com.speechpro.biometric.platform.onepass.api.VerificationApi;

public class StartingVerification extends Debugging implements IPluggableExecutable {
	
	private static final Logger LOG = Logger.getLogger(StartingVerification.class);
	
	private String destPassVar;
	private String destPassField;
	private String destSessIdVar;
	private String destSessIdField;
	private String personIdvar;
	private String personIdField;
	private boolean personIdConst;
	
	public StartingVerification(String destPassVar, String destPassField, String destSessIdVar, String destSessIdField, String personIdVar, String personIdField, boolean personIdConst){
		this.destPassVar = 		destPassVar;
		this.destPassField = 	destPassField;
		this.destSessIdVar = 	destSessIdVar;
		this.destSessIdField = 	destSessIdField;
		this.personIdvar = 		personIdVar;
		this.personIdField = 	personIdField;
		this.personIdConst = 	personIdConst;
	}

	@Override
	public void execute(IRuntimeSession mySession) {
		
		try{
			String personID = "";
		
			if ((destPassField != null) && (destPassField.equals("") == true)){
				destPassField = null;
			}
			if ((destSessIdField != null) && (destSessIdField.equals("") == true)){
				destSessIdField = null;
			}
			if ((personIdField != null) && (personIdField.equals("") == true)){
				personIdField = null;
			}
		
			IVariableField destPass = mySession.getVariableField(new VariableName(destPassVar, destPassField));
			IVariableField destSessId = mySession.getVariableField(new VariableName(destSessIdVar, destSessIdField));

			if (personIdConst == true){
				personID = personIdvar;
			} else {
				IVariableField personIdVarF = mySession.getVariableField(new VariableName(personIdvar, personIdField));
				personID = personIdVarF.getStringValue();	
			}
		
			VerificationApi verificationApi = startVerification(personID);
		
			String verificationSessionId = verificationApi.getSessionId();
			String verificationPassword = verificationApi.getVerificationPassword().replaceAll(" ", ", ");
		
			LOG.info(String.format("Verification session id = %s", verificationSessionId));
			LOG.info(String.format("Verification session password = %s", verificationPassword));
		
			destSessId.setValue(verificationSessionId);
			destPass.setValue(verificationPassword);
		
		} catch (Exception ex) {
			LOG.info(String.format("Error in Start Verification Sample: %s", 
					ex.getStackTrace().toString()));
			mySession.throwRTException("SendSampleVerification class error:", ex);
		}
	}
	
	private static VerificationApi startVerification (String personID){
		VerificationApi verificationApi = null;
		try{
			OnePassApi api = ApiCreator.createApi();
			PersonApi personApi = api.person(personID);
			verificationApi = personApi.startVerification();
    	} catch (NullPointerException ex) {
    		LOG.error("OnePassApi hasn't been initialized: " + ex.toString());
    		ex.printStackTrace();
    	}
		return verificationApi;
	}

	@Override
	public String getId() {
		return ("onepassplugin");
	}

	@Override
	public String getTraceMessage() {
		return ("StartingVerificationClass");
	}
	
}
