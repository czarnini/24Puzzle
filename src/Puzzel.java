/**
 * Klasa reprezentuj�ca puzzel i dane o nim
 */
public class Puzzel {
	/**
	 * unikalny identyfikator puzzla, wskazuje jego miejsce w tablicy.
	 */
	private int id;
	/**
	 * Konstruktor puzzla przypisuj�cy mu okreslone ID
	 * @param id - ID puzzla
	 */
	Puzzel(int id)
	{
		this.id = id;
	}
	/**
	 * Metoda zwracaj�ca ID puzzla
	 * @return ID puzzla
	 */
	public int getID()
	{
		return id;
	}

}
