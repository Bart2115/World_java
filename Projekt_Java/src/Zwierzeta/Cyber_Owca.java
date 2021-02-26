package Zwierzeta;
import java.util.Random;

import Projekt.Organizm;
import Projekt.Swiat;
import Projekt.Zwierze;
import Rosliny.Barszcz_Sosnowskiego;

import static java.lang.Math.abs;
public class Cyber_Owca extends Zwierze{
	private boolean EksterminacjaBarszczu() {
		int x_celu = -1;
		int y_celu = -1;
		int droga = 999;
		for(int i=0;i<20;i++) {
			for(int j=0; j<20; j++) {
				if(this.swiat_t.mapa[i][j] instanceof Barszcz_Sosnowskiego) {
					int droga2 = abs(this.swiat_t.mapa[i][j].getPolozenieX()-this.polozenieX)+abs(this.swiat_t.mapa[i][j].getPolozenieY()-this.polozenieY);
					if(droga > droga2 ) {
						x_celu = j;
						y_celu = i;
						droga = droga2;
					}
				}
			}
		}
		if(x_celu < 0 && y_celu < 0) {
			return false;
		}
		
		if(this.polozenieY != y_celu) {
			if(y_celu < this.polozenieY) {
				if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX] != null) {
					this.Kolizja(swiat_t.mapa[this.polozenieY - 1][this.polozenieX]);
					if (!this.czyZyje) {
						return true;
					}
				}
				if (swiat_t.mapa[this.polozenieY - 1][this.polozenieX] == null) {
					swiat_t.mapa[this.polozenieY - 1][this.polozenieX] = this;
					swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
					this.polozenieY -= 1;
					
				}
			}
			else {
				if (swiat_t.mapa[this.polozenieY + 1][this.polozenieX] != null) {
					this.Kolizja(swiat_t.mapa[this.polozenieY+1][this.polozenieX]);
					if (!this.czyZyje) {
						return true;
					}
				}
				if (swiat_t.mapa[this.polozenieY+1][this.polozenieX] == null) {
					swiat_t.mapa[this.polozenieY+1][this.polozenieX] = this;
					swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
					this.polozenieY += 1;
					
				}
			}
		}
		else if(this.polozenieX != x_celu) {
			if(x_celu < this.polozenieX) {
				if(swiat_t.mapa[this.polozenieY][this.polozenieX - 1] != null) {
					this.Kolizja(swiat_t.mapa[this.polozenieY][this.polozenieX-1]);
					if (!this.czyZyje) {
						return true;
					}
				}
				if (swiat_t.mapa[this.polozenieY][this.polozenieX - 1] == null) {
					swiat_t.mapa[this.polozenieY][this.polozenieX - 1] = this;
					swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
					this.polozenieX -= 1;
				}
			}
			else {
				if(swiat_t.mapa[this.polozenieY][this.polozenieX + 1] != null){
					this.Kolizja(swiat_t.mapa[this.polozenieY][this.polozenieX+1]);
					if (!this.czyZyje) {
						return true;
					}
				}
				if (swiat_t.mapa[this.polozenieY][this.polozenieX+1] == null) {
					swiat_t.mapa[this.polozenieY][this.polozenieX+1] = this;
					swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
					this.polozenieX += 1;
				}
			}
		}
		return true;
	}
	@Override
	public void Kolizja(Organizm organizm) {
		if(organizm instanceof Barszcz_Sosnowskiego) {
			organizm.setCzyZyje(false);
			organizm.getSwiat().mapa[organizm.getPolozenieY()][organizm.getPolozenieX()] = null;
			String kom = "B zosta³ unicestwiony przez C";
			this.swiat_t.DodajKomentarz(kom);
			return;
		}
		if (organizm.getSymbol() == this.symbol) {
			this.Rozmnazanie(organizm, 1);
			return;	
		}
		if (this.sila > organizm.getSila()) {
			String kom = organizm.getSymbol() + " umiera w ataku na " + this.symbol;
			this.swiat_t.DodajKomentarz(kom);
			organizm.setCzyZyje(false);
			organizm.getSwiat().mapa[organizm.getPolozenieY()][organizm.getPolozenieX()] = null;
		}
		else if (this.sila <= organizm.getSila()) {
			String kom = this.symbol + " umiera w obronie przez " + organizm.getSymbol();
			this.swiat_t.DodajKomentarz(kom);
			this.czyZyje = false;
			this.swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
		}
	}
	@Override
	public Cyber_Owca Potomstwo(Swiat swiat_t, int x, int y) {
		return new Cyber_Owca(swiat_t, x, y);
	}
	public Cyber_Owca(Swiat swiat, int x, int y) {
		super(swiat, 11, 4, 0, 'C', x, y, true);
	}
	@Override
	public void Akcja() {
		if(this.EksterminacjaBarszczu()) {
			return;
		}
		int szerokosc = this.swiat_t.getSzerokosc();
		int wysokosc = this.swiat_t.getWysokosc();
		while (true) {
			Random random = new Random();
			int x = random.nextInt(4);
			//KIERUNKI: GORA 0, PRAWO 1, DOL 2, LEWO 3
			if (x == 0) {
				if(this.polozenieY != 0){
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
				if (this.polozenieX < szerokosc-1) {
					if(swiat_t.mapa[this.polozenieY][this.polozenieX + 1] != null){
						swiat_t.mapa[this.polozenieY][this.polozenieX+1].Kolizja(this);
						if (!this.czyZyje) {
							break;
						}
					}
					if (swiat_t.mapa[this.polozenieY][this.polozenieX+1] == null) {
						swiat_t.mapa[this.polozenieY][this.polozenieX+1] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieX += 1;
					}
					break;
				}
			}
			else if (x == 2) {
				if (this.polozenieY != wysokosc-1) {
					if (swiat_t.mapa[this.polozenieY + 1][this.polozenieX] != null) {
						swiat_t.mapa[this.polozenieY+1][this.polozenieX].Kolizja(this);
						if (!this.czyZyje) {
							break;
						}
					}
					if (swiat_t.mapa[this.polozenieY+1][this.polozenieX] == null) {
						swiat_t.mapa[this.polozenieY+1][this.polozenieX] = this;
						swiat_t.mapa[this.polozenieY][this.polozenieX] = null;
						this.polozenieY += 1;
						
					}
					break;
				}
			}
			else if (x == 3) {
				if (this.polozenieX != 0) {
					if(swiat_t.mapa[this.polozenieY][this.polozenieX - 1] != null) {
						swiat_t.mapa[this.polozenieY][this.polozenieX-1].Kolizja(this);
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
}
