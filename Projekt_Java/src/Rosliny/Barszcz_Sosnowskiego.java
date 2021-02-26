package Rosliny;
import Projekt.Organizm;
import Projekt.Roslina;
import Projekt.Swiat;
import Projekt.Zwierze;
import Zwierzeta.Cyber_Owca;
import Zwierzeta.Czlowiek;

public class Barszcz_Sosnowskiego extends Roslina{
	@Override
	public void Kolizja(Organizm Atakujacy) {
		String kom = this.symbol + " i " + Atakujacy.getSymbol() + " umieraj¹ w konfrontacji.";
		this.swiat_t.DodajKomentarz(kom);
		this.czyZyje = false;
		this.swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
		Atakujacy.setCzyZyje(false);
		Atakujacy.getSwiat().mapa[Atakujacy.getPolozenieY()][Atakujacy.getPolozenieX()] = null;
	}
	@Override
	public void Akcja() {
		Rozmnazanie(null, this.prawdopodobienstwoRozsienia);
		int szerokosc = this.swiat_t.getSzerokosc();
		int wysokosc = this.swiat_t.getWysokosc();
		if (this.polozenieY != 0) {
			if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX] != null) {
				if ((swiat_t.mapa[this.polozenieY - 1][this.polozenieX]) instanceof Zwierze){
					if (this.swiat_t.mapa[this.polozenieY - 1][this.polozenieX] instanceof Czlowiek) {
						if(((Czlowiek)(this.swiat_t.mapa[this.polozenieY - 1][this.polozenieX])).getCzyAktywnaZdolnosc()) {
							return;
						}
					}
					else if(this.swiat_t.mapa[this.polozenieY - 1][this.polozenieX] instanceof Cyber_Owca) {
						return;
					}
					swiat_t.mapa[this.polozenieY - 1][this.polozenieX].setCzyZyje(false);
					String kom = swiat_t.mapa[this.polozenieY - 1][this.polozenieX].getSymbol() + " umiera przez " + this.symbol;
					this.swiat_t.DodajKomentarz(kom);
					swiat_t.mapa[this.polozenieY - 1][this.polozenieX] = null;
				}
			}
		}
		if (this.polozenieX < szerokosc - 1) {
			if (swiat_t.mapa[this.polozenieY][this.polozenieX + 1] != null) {
				if ((swiat_t.mapa[this.polozenieY][this.polozenieX+1]) instanceof Zwierze) {
					if (this.swiat_t.mapa[this.polozenieY][this.polozenieX+1] instanceof Czlowiek) {
						if(((Czlowiek)(this.swiat_t.mapa[this.polozenieY][this.polozenieX+1])).getCzyAktywnaZdolnosc()){
							return;
						}
					}
					else if(this.swiat_t.mapa[this.polozenieY][this.polozenieX + 1] instanceof Cyber_Owca) {
						return;
					}
					swiat_t.mapa[this.polozenieY][this.polozenieX + 1].setCzyZyje(false);
					String kom = swiat_t.mapa[this.polozenieY][this.polozenieX + 1].getSymbol() + " umiera przez " + this.symbol;
					this.swiat_t.DodajKomentarz(kom);
					swiat_t.mapa[this.polozenieY][this.polozenieX + 1] = null;
				}
			}
		}
		if (this.polozenieY != wysokosc - 1) {
			if (swiat_t.mapa[this.polozenieY + 1][this.polozenieX] != null) {
				if ((swiat_t.mapa[this.polozenieY +1][this.polozenieX]) instanceof Zwierze) {
					if (this.swiat_t.mapa[this.polozenieY + 1][this.polozenieX] instanceof Czlowiek ) {
						if(((Czlowiek)(this.swiat_t.mapa[this.polozenieY + 1][this.polozenieX])).getCzyAktywnaZdolnosc()) {
							return;
						}
					}
					else if(this.swiat_t.mapa[this.polozenieY + 1][this.polozenieX] instanceof Cyber_Owca) {
						return;
					}
					swiat_t.mapa[this.polozenieY + 1][this.polozenieX].setCzyZyje(false);
					String kom = swiat_t.mapa[this.polozenieY + 1][this.polozenieX].getSymbol() + " umiera przez " + this.symbol;
					this.swiat_t.DodajKomentarz(kom);
					swiat_t.mapa[this.polozenieY + 1][this.polozenieX] = null;
				}
			}
		}
		if (this.polozenieX != 0) {
			if (swiat_t.mapa[this.polozenieY][this.polozenieX - 1] != null) {
				if ((swiat_t.mapa[this.polozenieY][this.polozenieX-1]) instanceof Zwierze) {
					if (this.swiat_t.mapa[this.polozenieY][this.polozenieX-1] instanceof Czlowiek) {
						if(((Czlowiek)(this.swiat_t.mapa[this.polozenieY][this.polozenieX-1])).getCzyAktywnaZdolnosc()) {
							return;
						}
					}
					else if(this.swiat_t.mapa[this.polozenieY][this.polozenieX-1] instanceof Cyber_Owca) {
						return;
					}
					swiat_t.mapa[this.polozenieY][this.polozenieX - 1].setCzyZyje(false);
					String kom = swiat_t.mapa[this.polozenieY][this.polozenieX - 1].getSymbol() + " umiera przez " + this.symbol;
					this.swiat_t.DodajKomentarz(kom);
					swiat_t.mapa[this.polozenieY][this.polozenieX - 1] = null;
				}
			}
		}
	}
	@Override
	public Barszcz_Sosnowskiego Potomstwo(Swiat swiat_t, int x, int y) {
		return new Barszcz_Sosnowskiego(swiat_t, x, y);
	}
	public Barszcz_Sosnowskiego(Swiat swiat, int x, int y) {
		super(swiat, 10, 0, 0, 'B', x, y, true, 0.1);
	}
}
