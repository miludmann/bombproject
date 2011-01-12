package GUI;

public class GUITester {
	public static void main(String args[]) {
		try	{
			GameGUI m_instance = new GameGUI(true);
			m_instance.run();
		} catch (Throwable t) {
			t.printStackTrace();
			System.err.println("Error: " + t.getMessage());
			System.exit(1);
		}
	}
}
