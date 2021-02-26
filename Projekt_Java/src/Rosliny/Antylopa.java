package Rosliny;
import java.util.Random;

import Projekt.Organizm;
import Projekt.Swiat;
import Projekt.Zwierze;

public class Antylopa extends Zwierze {
	private void Ucieczka() {
		int szerokosc = this.swiat_t.getSzerokosc();
		int wysokosc = this.swiat_t.getWysokosc();
		int tab[] = {-1,-1,-1,-1};
		while (true) {
			int spr = 0;
			for (int i = 0; i < 4; i++) {
				spr += tab[i];
			}
			if (spr == 4) {
				break;
			}
			Random random = new Random();
			int x = random.nextInt(4);
			if (x == 0) {
				if (this.polozenieY != 0){
					if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX] == null) {
						swiat_t.mapa[this.polozenieY - 1][this.polozenieX] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieY -= 1;
						break;
					}
					else {
						tab[x] = 1;
					}
				}
				else {
					tab[x] = 1;
				}
			}
			else if (x == 1) {
				if (this.polozenieX < szerokosc-1) {
					if (swiat_t.mapa[this.polozenieY][this.polozenieX+1] == null) {
						swiat_t.mapa[this.polozenieY][this.polozenieX+1] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieX += 1;
						break;
					}
					else{
						tab[x] = 1;
					}
				}
				else {
					tab[x] = 1;
				}
			}
			else if (x == 2) {
				if (this.polozenieY != wysokosc-1) {
					if (swiat_t.mapa[this.polozenieY+1][this.polozenieX] == null) {
						swiat_t.mapa[this.polozenieY+1][this.polozenieX] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieY += 1;
						break;
					}
					else {
						tab[x] = 1;
					}
				}
				else {
					tab[x] = 1;
				}
			}
			else if (x == 3) {
				if (this.polozenieX != 0) {
					if (swiat_t.mapa[this.polozenieY][this.polozenieX - 1] == null) {
						swiat_t.mapa[this.polozenieY][this.polozenieX - 1] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieX -= 1;
						break;
					}
					else {
						tab[x] = 1;
					}
				}
				else {
					tab[x] = 1;
				}
			}
		}
	}
	@Override
	public void Akcja() {
		int szerokosc = this.swiat_t.getSzerokosc();
		int wysokosc = this.swiat_t.getWysokosc();
		while (true) {
			Random random = new Random();
			int x = random.nextInt(4);
			//KIERUNKI: GORA 0, PRAWO 1, DOL 2, LEWO 3
			if (x == 0) {
				if (this.polozenieY > 1) {
					if (swiat_t.mapa[this.polozenieY - 2][this.polozenieX] != null) {
						swiat_t.mapa[this.polozenieY - 2][this.polozenieX].Kolizja(this);
						if (!this.czyZyje) {
							break;
						}
					}
					if (swiat_t.mapa[this.polozenieY - 2][this.polozenieX] == null) {
						swiat_t.mapa[this.polozenieY - 2][this.polozenieX] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieY -= 2;
					}
					break;
				}
			}
			else if (x == 1) {
				if (this.polozenieX < szerokosc - 2) {
					if (swiat_t.mapa[this.polozenieY][this.polozenieX + 2] != null) {
						swiat_t.mapa[this.polozenieY][this.polozenieX + 2].Kolizja(this);
						if (!this.czyZyje) {
							break;
						}
					}
					if (swiat_t.mapa[this.polozenieY][this.polozenieX + 2] == null) {
						swiat_t.mapa[this.polozenieY][this.polozenieX + 2] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieX += 2;
					}
					break;
				}
			}
			else if (x == 2) {
				if (this.polozenieY < wysokosc - 2) {
					if (swiat_t.mapa[this.polozenieY + 2][this.polozenieX] != null) {
						swiat_t.mapa[this.polozenieY + 2][this.polozenieX].Kolizja(this);
						if (!this.czyZyje) {
							break;
						}
					}
					if (swiat_t.mapa[this.polozenieY + 2][this.polozenieX] == null) {
						swiat_t.mapa[this.polozenieY + 2][this.polozenieX] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieY += 2;
					}
					break;
				}
			}
			else if (x == 3) {
				if (this.polozenieX > 1) {
					if (swiat_t.mapa[this.polozenieY][this.polozenieX - 2] != null) {
						swiat_t.mapa[this.polozenieY][this.polozenieX - 2].Kolizja(this);
						if (!this.czyZyje) {
							break;
						}
					}
					if (swiat_t.mapa[this.polozenieY][this.polozenieX - 2] == null) {
						swiat_t.mapa[this.polozenieY][this.polozenieX - 2] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieX -= 2;

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
		Random random = new Random();
		int x = random.nextInt(2);
		if (x == 0) {
			Ucieczka();
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
	public Antylopa Potomstwo(Swiat swiat_t, int x, int y) {
		return new Antylopa(swiat_t, x, y);
	}
	public Antylopa(Swiat swiat, int x, int y) {
		super(swiat, 4, 4, 0, 'A', x, y, true);
	}
}
