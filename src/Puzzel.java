/**
 * Klasa reprezentująca puzzel i dane o nim
 */
public class Puzzel {
	/**
	 * unikalny identyfikator puzzla, wskazuje jego miejsce w tablicy.
	 */
	private int id;
	/**
	 * Konstruktor puzzla przypisujący mu okreslone ID
	 * @param id - ID puzzla
	 */
	Puzzel(int id)
	{
		this.id = id;
	}
	/**
	 * Metoda zwracająca ID puzzla
	 * @return ID puzzla
	 */
	public int getID()
	{
		return id;
	}

}
