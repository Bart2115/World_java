package Zwierzeta;
import Projekt.Swiat;
import Projekt.Zwierze;

public class Wilk extends Zwierze {
	@Override
	public Wilk Potomstwo(Swiat swiat_t, int x, int y) {
		return new Wilk(swiat_t, x, y);
	}
	public Wilk(Swiat swiat, int x, int y) {
		super(swiat, 9, 5, 0, 'W',x, y, true);
	}
}
