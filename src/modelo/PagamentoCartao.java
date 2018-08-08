package modelo;

public class PagamentoCartao extends Pagamento{
	private String cartao;
	private int quantidadeparcelas;
	
	//gets e sets
	public String getCartao() {
		return cartao;
	}
	public void setCartao(String cartao) {
		this.cartao = cartao;
	}
	public int getQuantidadeparcelas() {
		return quantidadeparcelas;
	}
	public void setQuantidadeparcelas(int quantidadeparcelas) {
		this.quantidadeparcelas = quantidadeparcelas;
	}
	
	// toString
	@Override
	public String toString() {
		return super.toString()+ " tipo: cartão \n cartao: " + cartao + "\n quantidade parcelas: " + quantidadeparcelas;
	}
	
	
	@Override
	public void calcularPagamento(double totalconta) throws Exception{
		// não pode ultrapassar 4 parcelas
		if (quantidadeparcelas > 4) throw new Exception ("limite de parcelas é maior que 4");
		// acrescimo de juros no total
		switch(quantidadeparcelas) {
			case 4:
				totalconta = totalconta*1.20;
			break;
			case 3:
				totalconta = totalconta*1.10;
			break;
		}
		// valor minimo de 100 para cada parcela
		double totalparcela = (totalconta/quantidadeparcelas);
		if (totalparcela < 100 && quantidadeparcelas > 1 ) throw new Exception ("parcela minima é de " + totalparcela);	
		this.setValorpago(totalconta); 
	}
	
}
