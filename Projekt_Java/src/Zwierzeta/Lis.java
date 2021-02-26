package Zwierzeta;
import java.util.Random;

import Projekt.Swiat;
import Projekt.Zwierze;

public class Lis extends Zwierze {
	private boolean dobryWech() {
		int szerokosc = this.swiat_t.getSzerokosc();
		int wysokosc = this.swiat_t.getWysokosc();
		int i = 0;
		if (this.polozenieY != 0) {
			if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX] != null) {
				if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX].getSila() > this.sila) {
					i++;
				}
			}
		}
		else {
			i++;
		}
		if (this.polozenieX < szerokosc - 1) {
			if (swiat_t.mapa[this.polozenieY][this.polozenieX + 1] != null) {
				if (swiat_t.mapa[this.polozenieY][this.polozenieX + 1].getSila() > this.sila) {
					i++;
				}
			}
		}
		else {
			i++;
		}
		if (this.polozenieY != wysokosc - 1) {
			if (swiat_t.mapa[this.polozenieY + 1][this.polozenieX] != null) {
				if (swiat_t.mapa[this.polozenieY + 1][this.polozenieX].getSila() > this.sila) {
					i++;
				}
			}
		}
		else {
			i++;
		}
		if (this.polozenieX != 0) {
			if (swiat_t.mapa[this.polozenieY][this.polozenieX - 1] != null) {
				if (swiat_t.mapa[this.polozenieY][this.polozenieX - 1].getSila() > this.sila) {
					i++;
				}
			}
		}
		else {
			i++;
		}
		if (i == 4) {
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public void Akcja() {
		int szerokosc = this.swiat_t.getSzerokosc();
		int wysokosc = this.swiat_t.getWysokosc();
		while (true) {
			Random random = new Random();
			int x = random.nextInt(4);
			if (dobryWech()) {
				break;
			}
			//KIERUNKI: GORA 0, PRAWO 1, DOL 2, LEWO 3
			if (x == 0) {
				if (this.polozenieY != 0) {
					if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX] != null) {
						if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX].getSila() > this.sila) {
							continue;
						}
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
					if (swiat_t.mapa[this.polozenieY][this.polozenieX + 1] != null) {
						if (swiat_t.mapa[this.polozenieY][this.polozenieX + 1].getSila() > this.sila) {
							continue;
						}
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
					if (swiat_t.mapa[this.polozenieY + 1][this.polozenieX] != null) {
						if (swiat_t.mapa[this.polozenieY + 1][this.polozenieX].getSila() > this.sila) {
							continue;
						}
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
					if (swiat_t.mapa[this.polozenieY][this.polozenieX - 1] != null) {
						if (swiat_t.mapa[this.polozenieY][this.polozenieX - 1].getSila() > this.sila) {
							continue;
						}
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
	public Lis Potomstwo(Swiat swiat_t, int x, int y) {
		return new Lis(swiat_t, x, y);
	}
	public Lis(Swiat swiat, int x, int y) {
		super(swiat, 3, 7, 0, 'L', x , y, true);
	}
}
