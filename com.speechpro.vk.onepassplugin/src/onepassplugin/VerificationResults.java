package onepassplugin;

import org.apache.log4j.Logger;

import com.avaya.sce.runtime.IPluggableExecutable;
import com.avaya.sce.runtimecommon.Debugging;
import com.avaya.sce.runtimecommon.IRuntimeSession;
import com.avaya.sce.runtimecommon.IVariableField;
import com.avaya.sce.runtimecommon.VariableName;
import com.speechpro.biometric.platform.onepass.api.VerificationApi;

public class VerificationResults extends Debugging implements IPluggableExecutable {
	
	private static final Logger LOG = Logger.getLogger(VerificationResults.class);
	
	private String verificationIdVar;
	private String verificationIdField;
	private String scoreVar;
	private String scoreField;
	
	public VerificationResults(	String verificationIdVar,
								String verificationIdField,
								String scoreVar,
								String scoreField){
		this.verificationIdVar = 	verificationIdVar;
		this.verificationIdField = 	verificationIdField;
		this.scoreVar = 			scoreVar;
		this.scoreField = 			scoreField;
		
	}

	public void execute(IRuntimeSession mySession) {
		try{
			String sessionId = "";
			if ((verificationIdField != null) && (verificationIdField.equals("") == true)){
				verificationIdField = null;
				}
		
			if ((scoreField != null) && (scoreField.equals("") == true)){
				scoreField = null;
				}
		
			IVariableField verificationIdVarF = mySession.getVariableField(
				new VariableName(verificationIdVar, verificationIdField));
			IVariableField scoreVarF = mySession.getVariableField(
				new VariableName(scoreVar, scoreField));

			sessionId = verificationIdVarF.getStringValue();
			LOG.info(String.format("Calculating score for Verification sesson %s, score = %s ", 
					sessionId, getScore(sessionId)));
			scoreVarF.setValue(getScore(sessionId));
			
		}catch(NullPointerException ex){
			LOG.error("OnePassApi hasn't been initialized: " + ex.toString());
			ex.printStackTrace();
			}
		}
	
	private static int getScore(String sessionId){
		int score;
		VerificationApi verificationApi = new VerificationApi(sessionId);
		score = ((Double) verificationApi.getVerificationScore()).intValue();
		return score;
	}

	public String getId() {
		return ("onepassplugin");
	}

	public String getTraceMessage() {
		return ("Onepass called");
	}

}
