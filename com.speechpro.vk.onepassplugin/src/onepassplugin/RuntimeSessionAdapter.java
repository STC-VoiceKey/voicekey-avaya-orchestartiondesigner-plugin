package onepassplugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import com.avaya.sce.runtimecommon.IPerformanceTracker;
import com.avaya.sce.runtimecommon.IReportInfo;
import com.avaya.sce.runtimecommon.IRuntimeSession;
import com.avaya.sce.runtimecommon.ITraceInfo;
import com.avaya.sce.runtimecommon.IVariable;
import com.avaya.sce.runtimecommon.IVariableField;
import com.avaya.sce.runtimecommon.VariableName;

public class RuntimeSessionAdapter implements IRuntimeSession {
	HashMap<String, IVariableField> variables = new HashMap<String, IVariableField>();

	public File createTempFile(String arg0) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCurrentLocationId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getParameter(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public IPerformanceTracker getPerformanceTracker() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getProjectDirectory() {
		// TODO Auto-generated method stub
		return null;
	}

	public IReportInfo getReportOutput() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSessionId() {
		return ("RuntimeSessionAdapter-1");
	}
	
	private ITraceInfo trace = new ITraceInfo() {
		public void writeln(int level, String value) {
			System.out.println("TRACE : " + level + " - " + value);
		}

		public void close() {
		}

	};

	public ITraceInfo getTraceOutput() {
		return (trace);
	}

	public IVariable getVariable(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public IVariableField getVariableField(VariableName name) {
		IVariableField f = variables.get(name.toString());
		if (f == null) {
			f = new VariableAdapter();
			variables.put(name.toString(), f);
		}
		return (f);
	}

	public IVariableField getVariableField(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isTraceEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setupProxy() {
		Properties props = System.getProperties();
		props.put("http" + ".proxyHost", "co.proxy.avaya.com");
		props.put("http" + ".proxyPort", "8000");
		System.setProperties(props);
		
	}

	public void throwRTException(String message) {
		System.out.println("RUNTIME EXCEPTION - " + message);
		
	}

	public void throwRTException(String arg0, Exception arg1) {
		// TODO Auto-generated method stub
		
	}

}
