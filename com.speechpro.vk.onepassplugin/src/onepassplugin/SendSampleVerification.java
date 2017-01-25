package onepassplugin;

import org.apache.log4j.Logger;

import com.avaya.sce.runtime.IPluggableExecutable;
import com.avaya.sce.runtimecommon.Debugging;
import com.avaya.sce.runtimecommon.IRuntimeSession;
import com.avaya.sce.runtimecommon.IVariableField;
import com.avaya.sce.runtimecommon.VariableName;
import com.speechpro.biometric.platform.onepass.api.VerificationApi;
import com.speechpro.biometric.platform.onepass.util.SoundSender;

public class SendSampleVerification extends Debugging implements IPluggableExecutable{
	
	private static final Logger LOG = Logger.getLogger(SendSampleVerification.class);
	
	private String verificationCompleteVar;
	private String verificationCompleteField;
	private String recordingVar;
	private String recordingField;
	private String passwordVar;
	private String passwordField;
	private String verificationIdVar;
	private String verificationIdField;
	
	public SendSampleVerification(	String verificationCompleteVar,
									String verificationCompleteField,
									String recordingVar,
									String recordingField,
									String passwordVar,
									String passwordField,
									String verificationIdVar,
									String verificationIdField){
		
		this.verificationIdVar = 			verificationIdVar;
		this.verificationIdField = 			verificationIdField;
		this.recordingVar = 				recordingVar;
		this.recordingField = 				recordingField;
		this.passwordVar = 					passwordVar;
		this.passwordField = 				passwordField;
		this.verificationCompleteVar = 		verificationCompleteVar;
		this.verificationCompleteField = 	verificationCompleteField;
	}

	public void execute(IRuntimeSession mySession) {
		try {
			if ((verificationIdField != null) && (verificationIdField.equals("") == true)) {
				verificationIdField = null;
			}
			if ((recordingField != null) && (recordingField.equals("") == true)){
				recordingField = null;
			}
			if ((passwordField != null) && (passwordField.equals("") == true)){
				passwordField = null;
			}
			if ((verificationCompleteField != null) && (verificationCompleteField.equals("") == true)){
				verificationCompleteField = null;
			}
			IVariableField verificationIdVarF = mySession.getVariableField(
					new VariableName(verificationIdVar, verificationIdField));
			IVariableField verificationCompleteVarF = mySession.getVariableField(
					new VariableName(verificationCompleteVar, verificationCompleteField));
			IVariableField recordingVarF = mySession.getVariableField(
					new VariableName(recordingVar, recordingField));
			IVariableField passwordVarF = mySession.getVariableField(
					new VariableName(passwordVar, passwordField));
		
			String password = passwordVarF.getStringValue();
			password = password.replace(",", "");
			LOG.info(String.format("Password in verification = %s", password));
		
			byte[] fileBytes = SoundSender.readAudioFromAvayaDD(recordingVarF.getStringValue());
			String data = SoundSender.convertBytesToBase64String(fileBytes);
		
			String verificationSessionId = verificationIdVarF.getStringValue();
			LOG.info(String.format("Verification session id = %s", verificationSessionId));
		
			verificationCompleteVarF.setValue(verify(data, verificationSessionId, password));
		
		} catch (Exception ex) {
			LOG.info(String.format("Error in Send Verification Sample: %s", ex.getStackTrace().toString()));
			mySession.throwRTException("SendSampleVerification class error:", ex);
		}
		
	}
	
	private boolean verify(String data, String verificationSessionId, String password){
		boolean sent = false;
		VerificationApi verificationApi = new VerificationApi(password, verificationSessionId);
		sent = verificationApi.sendVerificationVoice(data);
		return sent;
	}

	@Override
	public String getId() {
		return ("onepassplugin");
	}

	@Override
	public String getTraceMessage() {
		return ("SendSampleVerificationClass");
	}

}
