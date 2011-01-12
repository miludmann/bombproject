package GUI;

public class GUITester {
	public static void main(String args[]) {
		try	{
			TestGUISmall m_instance = new TestGUISmall();
			m_instance.run(args);
		} catch (Throwable t) {
			t.printStackTrace();
			System.err.println("Error: " + t.getMessage());
			System.exit(1);
		}
	}
}
