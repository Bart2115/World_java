package Rosliny;
import Projekt.Organizm;
import Projekt.Roslina;
import Projekt.Swiat;

public class Guarana extends Roslina{
	@Override
	public void Kolizja(Organizm Atakujacy) {
		String kom = this.symbol + " umiera w obronie przez " + Atakujacy.getSymbol();
		this.swiat_t.DodajKomentarz(kom);
		this.czyZyje = false;
		this.swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
		Atakujacy.setSila(3);
	}
	@Override
	public Guarana Potomstwo(Swiat swiat_t, int x, int y) {
		return new Guarana(swiat_t, x, y);
	}
	public Guarana(Swiat swiat, int x, int y){
		super(swiat, 0, 0, 0, 'G', x, y, true, 0.2);
	}
}
