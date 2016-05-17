/** 
 * Klasa kontrolera mi�dzy widokiem gry a modelem gry. Zajmuje si� ��czeniem widoku z gr� (aby stanowi�y 2 niezalezne modu�y)
 * oraz przekazywaniem polece� mi�dzy widokiem a gr�*/
public class GVControler
{
	View view;	/** Klasa widoku*/
	GameControl gameControl; /** Klasa modelu gry*/
	/**
	 * Konstruktor klasy, ��cz�cy ze sob� powi�zane klasy
	 * @param gameControl - model gry
	 */
	GVControler (GameControl gameControl)
	{
		view = new View();
		this.gameControl = gameControl;
		view.linkController(this);
		gameControl.linkController(this);
	}
	/**
	 * metoda przesy�aj�ca do widoku polecenie przerywowania pozycji kafelka
	 * @param i - docelowa pozycja X kafelka
	 * @param j - docelowa pozycja Y kafelka
	 * @param ID - ID przesuwanego kafelka
	 */
	void redraw (int i, int j, int ID)
	{
		view.redraw(i,j,ID);
	}
	/**
	 * metoda przesy�aj�ca do modelu polecenie z guzika, przerysowanie planszy
	 */
	public void randomize()
	{
		gameControl.randomize();
	}
	/**
	 * metoda przesy�aj�ca do modelu polecenie z guzika, rozwi�zanie puzzli
	 * @throws InterruptedException - wyj�tek dla metody sleep
	 */
	public void solve() throws InterruptedException
	{
		gameControl.solve();
	}
	/**
	 * metoda przesy�aj�ca do modelu polecenie z guzika, zatrzymanie rozwi�zania
	 */
	public void pause() {
		gameControl.pause();
	}
	/**
	 * metoda przesy�aj�ca do modelu polecenie z guzika, wznowienie rozwi�zania
	 */
	public void unPause() {
		gameControl.unPause();
	}
	/**
	 * metoda przesy�aj�ca do modelu polecenie zmiany szybko�ci wykonywania kolejnych krok�w
	 * @param i - szybko�� wykonywania kolejnych krok�w przekazana przez widok, w ms
	 */
	public void setClockTick(int i)
	{
		gameControl.setClockTick(i);
	}
	/**
	 * metoda przesy�aj�ca do widoku polecenie zwi�kszenia licznika krok�w
	 */
	public void updateSteps()
	{
		view.updateSteps();
	}
	/**
	 * metoda przesy�aj�ca do widoku informacj� �e program zako�czy� rozwi�zywanie �amig��wki
	 */
	public void done()
	{
		view.done();		
	}
}