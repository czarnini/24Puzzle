/**
 * Klasa reprezentuj¹ca puzzel i dane o nim
 */
public class Puzzel {
	/**
	 * unikalny identyfikator puzzla, wskazuje jego miejsce w tablicy.
	 */
	private int id;
	/**
	 * Konstruktor puzzla przypisuj¹cy mu okreslone ID
	 * @param id - ID puzzla
	 */
	Puzzel(int id)
	{
		this.id = id;
	}
	/**
	 * Metoda zwracaj¹ca ID puzzla
	 * @return ID puzzla
	 */
	public int getID()
	{
		return id;
	}

}
