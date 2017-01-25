package onepassplugin;

public class Version {

	private static final String VERSION_000001 = "00.00.02"; // 02/25/2009 initial version
	/*
	private static final String VERSION_000000 = "00.00.00"; // 12/07/2007 initial version
	 */
	private static final String CURRENT_VERSION = VERSION_000001;

	public static String getVersion() {
		return (CURRENT_VERSION);
	}

	/**
	 * Allow class to be run and print out version.
	 */
	public static void main(String[] args) {
		if (args.length > 0) {
			System.out.print(Version.getVersion());
		} else {
			System.out.println("onepassplugin.jar version " + Version.getVersion());
		}
	}
	
}
