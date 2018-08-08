package modelo;

public class PagamentoDinheiro extends Pagamento {
	private int percentualdesconto;

	
	// gets e sets
	public int getPercentualdesconto() {
		return percentualdesconto;
	}

	public void setPercentualdesconto(int percentualdesconto) {
		this.percentualdesconto = percentualdesconto;
	}

	// toString
	@Override
	public String toString() {
		return super.toString()+ "\n tipo: dinheiro\n percentual desconto: " + percentualdesconto;
	}

	@Override
	public void calcularPagamento(double totalconta) throws Exception {
		if (percentualdesconto>5 || percentualdesconto<0) throw new Exception ("percentual de desconto invalido");
		double total = totalconta - ((totalconta * percentualdesconto)/100);
		this.setValorpago(total); 
	}
}
