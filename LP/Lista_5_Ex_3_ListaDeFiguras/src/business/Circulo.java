package business;
import java.awt.*;

public class Circulo extends Figura {


	public Circulo(int x, int y, int lado, boolean preenchido, Color cor) {
		super(x, y, lado, preenchido, cor);
		// TODO Auto-generated constructor stub
	}

	public void desenha(Graphics g) {
		Color velhaCor = g.getColor();
		g.setColor(getCor());
		if (isPreenchido())
			g.fillOval(getX(), getY(), getLado(), getLado());
		else
			g.drawOval(getX(), getY(), getLado(), getLado());
		g.setColor(velhaCor);
	}

	@Override
	public double getArea() {
		double raio = this.getLado() / 2.0;
		return Math.PI * raio * raio;
	}

	@Override
	public double getPerimetro() {

		return Math.PI * this.getLado();
	}
}