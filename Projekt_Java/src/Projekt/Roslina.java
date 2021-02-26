package Projekt;
import java.util.Random;

public abstract class Roslina extends Organizm {
	protected double prawdopodobienstwoRozsienia;
	@Override
	public void Akcja() {
		Rozmnazanie(null, this.prawdopodobienstwoRozsienia);
	}
	@Override
	public void Kolizja(Organizm Atakujacy) {
		String kom = this.symbol + " umiera w obronie przez " + Atakujacy.getSymbol();
		this.swiat_t.DodajKomentarz(kom);
		this.czyZyje = false;
		this.swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
	}
	@Override
	public void Rozmnazanie(Organizm drugiRodzic, double prawdopodobienstwo) {
		int szerokosc = this.swiat_t.getSzerokosc();
		int wysokosc = this.swiat_t.getWysokosc();
		if (this.wiek <= 20) {
			return;
		}
		prawdopodobienstwo *= 100;
		Random random = new Random();
		int x = random.nextInt(100);
		if (prawdopodobienstwo < x) {
			return;
		}
		if (this.czyWolneMiejsce()) {
			String kom = "Wyrós³ nowy "+this.symbol;
			this.swiat_t.DodajKomentarz(kom);
			Organizm organism;
			while (true) {
				x = random.nextInt(4);
				//KIERUNKI: GORA 0, PRAWO 1, DOL 2, LEWO 3
				if (x == 0) {
					if (this.polozenieY != 0) {
						if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX] == null) {
							organism = this.Potomstwo(this.swiat_t, this.polozenieX, this.polozenieY - 1);
							organism.getSwiat().DodajDoOrganizmow(organism);
							swiat_t.mapa[this.polozenieY - 1][this.polozenieX] = organism;
						}
						break;
					}
				}
				else if (x == 1) {
					if (this.polozenieX < szerokosc - 1) {
						if (swiat_t.mapa[this.polozenieY][this.polozenieX + 1] == null) {
							organism = this.Potomstwo(this.swiat_t, this.polozenieX + 1, this.polozenieY);
							organism.getSwiat().DodajDoOrganizmow(organism);
							swiat_t.mapa[this.polozenieY][this.polozenieX + 1] = organism;
						}
						break;
					}
				}
				else if (x == 2) {
					if (this.polozenieY != wysokosc - 1) {
						if (swiat_t.mapa[this.polozenieY + 1][this.polozenieX] == null) {
							organism = this.Potomstwo(this.swiat_t, this.polozenieX, this.polozenieY + 1);
							organism.getSwiat().DodajDoOrganizmow(organism);
							swiat_t.mapa[this.polozenieY + 1][this.polozenieX] = organism;
						}
						break;
					}
				}
				else if (x == 3) {
					if (this.polozenieX != 0) {
						if (swiat_t.mapa[this.polozenieY][this.polozenieX - 1] == null) {
							organism = this.Potomstwo(this.swiat_t, this.polozenieX - 1, this.polozenieY);
							organism.getSwiat().DodajDoOrganizmow(organism);
							swiat_t.mapa[this.polozenieY][this.polozenieX - 1] = organism;
						}
						break;
					}
				}
			}
		}
	}
	public Roslina(Swiat swiat, int sila, int inicjatywa, int wiek, char symbol, int x, int y, boolean czyZyje, double prawdopodobienstwoRozsienia) {
		super(swiat,sila,inicjatywa,wiek,symbol,x,y,czyZyje);
		this.prawdopodobienstwoRozsienia = prawdopodobienstwoRozsienia;
	}
}
