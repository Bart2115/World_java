package Zwierzeta;
import java.util.Random;

import Projekt.Organizm;
import Projekt.Swiat;
import Projekt.Zwierze;

public class Zolw extends Zwierze{
	@Override
	public void Akcja() {
		int szerokosc = this.swiat_t.getSzerokosc();
		int wysokosc = this.swiat_t.getWysokosc();
		while (true) {
			Random random = new Random();
			int x = random.nextInt(4);
			if (x == 0) {
				x = random.nextInt(4);
			}
			else {
				//std::cout << "Bezczynnosc " << this.symbol << "\n";
				break;
			}
			//KIERUNKI: GORA 0, PRAWO 1, DOL 2, LEWO 3
			if (x == 0) {
				if (this.polozenieY != 0) {
					//std::cout << "Ruch w gore " << this.symbol << "\n";
					if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX] != null) {
						swiat_t.mapa[this.polozenieY - 1][this.polozenieX].Kolizja(this);
						if (!this.czyZyje) {
							break;
						}
					}
					if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX] == null) {
						swiat_t.mapa[this.polozenieY - 1][this.polozenieX] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieY -= 1;

					}
					break;
				}
			}
			else if (x == 1) {
				if (this.polozenieX < szerokosc - 1) {
					//std::cout << "Ruch w prawo " << this.symbol << "\n";
					if (swiat_t.mapa[this.polozenieY][this.polozenieX + 1] != null) {
						swiat_t.mapa[this.polozenieY][this.polozenieX + 1].Kolizja(this);
						if (!this.czyZyje) {
							break;
						}
					}
					if (swiat_t.mapa[this.polozenieY][this.polozenieX + 1] == null) {
						swiat_t.mapa[this.polozenieY][this.polozenieX + 1] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieX += 1;

					}

					break;
				}
			}
			else if (x == 2) {
				if (this.polozenieY != wysokosc - 1) {
					//std::cout << "Ruch w dol " << this.symbol << "\n";
					if (swiat_t.mapa[this.polozenieY + 1][this.polozenieX] != null) {
						swiat_t.mapa[this.polozenieY + 1][this.polozenieX].Kolizja(this);
						if (!this.czyZyje) {
							break;
						}
					}
					if (swiat_t.mapa[this.polozenieY + 1][this.polozenieX] == null) {
						swiat_t.mapa[this.polozenieY + 1][this.polozenieX] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieY += 1;

					}
					break;
				}
			}
			else if (x == 3) {
				if (this.polozenieX != 0) {
					//std::cout << "Ruch w lewo " << this.symbol << "\n";
					if (swiat_t.mapa[this.polozenieY][this.polozenieX - 1] != null) {
						swiat_t.mapa[this.polozenieY][this.polozenieX - 1].Kolizja(this);
						if (!this.czyZyje) {
							break;
						}
					}
					if (swiat_t.mapa[this.polozenieY][this.polozenieX - 1] == null) {
						swiat_t.mapa[this.polozenieY][this.polozenieX - 1] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieX -= 1;

					}
					break;
				}
			}
		}
	}
	@Override
	public void Kolizja(Organizm Atakujacy) {
		if (Atakujacy.getSymbol() == this.symbol) {
			this.Rozmnazanie(Atakujacy, 1);
			return;
		}
		if(Atakujacy.getSila() < 5){
			return;
		}
		if (this.sila > Atakujacy.getSila()) {
			String kom = Atakujacy.getSymbol() + " umiera w ataku na " + this.symbol;
			this.swiat_t.DodajKomentarz(kom);
			Atakujacy.setCzyZyje(false);
			Atakujacy.getSwiat().mapa[Atakujacy.getPolozenieY()][Atakujacy.getPolozenieX()] = null;
		}
		else if (this.sila <= Atakujacy.getSila()) {
			String kom = this.symbol + " umiera w obronie przez " + Atakujacy.getSymbol();
			this.swiat_t.DodajKomentarz(kom);
			this.czyZyje = false;
			this.swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
		}
	}
	@Override
	public Zolw Potomstwo(Swiat swiat_t, int x, int y) {
		return new Zolw(swiat_t, x, y);
	}
	public Zolw(Swiat swiat, int x, int y){
		super(swiat, 2, 1, 0, 'Z', x, y, true);
	}
}
