package Zwierzeta;
import Projekt.Organizm;
import Projekt.Roslina;
import Projekt.Swiat;

public class Wilcze_Jagody extends Roslina{
	@Override
	public void Kolizja(Organizm Atakujacy) {
		String kom = this.symbol + " i " + Atakujacy.getSymbol() + " umieraj¹ w konfrontacji!";
		this.swiat_t.DodajKomentarz(kom);
		this.czyZyje = false;
		this.swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
		Atakujacy.setCzyZyje(false);
		Atakujacy.getSwiat().mapa[Atakujacy.getPolozenieY()][Atakujacy.getPolozenieX()] = null;
	}
	@Override
	public Wilcze_Jagody Potomstwo(Swiat swiat_t, int x, int y) {
		return new Wilcze_Jagody(swiat_t, x, y);
	}
	public Wilcze_Jagody(Swiat swiat, int x, int y) {
		super(swiat, 99, 0, 0, 'J', x, y, true, 0.2);
	}
}
