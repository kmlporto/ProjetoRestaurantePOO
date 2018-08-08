package modelo;

public abstract class Pagamento {
	private double valorpago;

	//gets e sets
	public double getValorpago() {
		return valorpago;
	}
	public void setValorpago(double valorpago) {
		this.valorpago = valorpago;
	}
	
	//toString
	@Override
	public String toString() {
		return "\n Pagamento \n valorpago: " + valorpago;
	}
	
	// metodos
	public abstract void calcularPagamento(double totalconta) throws Exception;
	public double calcularGorjeta() {
		return  (this.getValorpago() * 10)/100;
	}
}
