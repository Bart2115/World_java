package Zwierzeta;
import Projekt.Swiat;
import Projekt.Zwierze;

public class Owca extends Zwierze{
	@Override
	public Owca Potomstwo(Swiat swiat_t, int x, int y) {
		return new Owca(swiat_t, x, y);
	}
	public Owca(Swiat swiat, int x, int y) {
		super(swiat, 4, 4, 0, 'O', x, y, true);
	}
}
