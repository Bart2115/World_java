package Rosliny;
import Projekt.Roslina;
import Projekt.Swiat;

public class Mlecz extends Roslina {
	@Override
	public void Akcja() {
		for (int i = 0; i < 3; ++i) {
			Rozmnazanie(null, this.prawdopodobienstwoRozsienia);
		}
	}
	@Override
	public Mlecz Potomstwo(Swiat swiat_t, int x, int y) {
		return new Mlecz(swiat_t, x, y);
	}
	public Mlecz(Swiat swiat, int x, int y) {
		super(swiat, 0, 0, 0, 'M', x, y, true, 0.2);
	}
}
