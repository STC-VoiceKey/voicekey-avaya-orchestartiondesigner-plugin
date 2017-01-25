package util;

import org.apache.log4j.Logger;

import com.speechpro.biometric.platform.onepass.api.OnePassApi;

public class ApiCreator {
	
	private static final Logger LOG = Logger.getLogger(ApiCreator.class);
	
	public static OnePassApi createApi(){
	    String applicationName 	= System.getProperty("biometric.platform.name");
	    String applicationPort 	= System.getProperty("biometric.platform.port");
	    String applicationRoot 	= System.getProperty("biometric.platform.root");
	    String applicationMode 	= System.getProperty("biometric.platform.mode");
	    
	    LOG.info(String.format("applicationName = %s; applicationPort = %s; applicationRoot = %s; applicationMode = %s",
	    						applicationName, applicationPort, applicationRoot, applicationMode));
	    
	    return new OnePassApi(applicationName, applicationPort, applicationRoot, applicationMode);
	    
	}

}
