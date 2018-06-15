package modelo;
import java.util.ArrayList;

public class Mesa {
	private int id;
	private boolean ocupada;
	private ArrayList<Conta> contas = new ArrayList<Conta>();
	private Garcom garcom;
	
	// gets e sets
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isOcupada() {
		return ocupada;
	}
	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}
	public Garcom getGarcom() {
		return garcom;
	}
	public void setGarcom(Garcom garcom) {
		this.garcom = garcom;
	}	
	public ArrayList<Conta> getContas() {
		return contas;
	}
	public void setContas(ArrayList<Conta> contas) {
		this.contas = contas;
	}
	public Conta getUltimaConta() {
		if (contas.size()<=0)
			return null;
		return contas.get(contas.size()-1);
	}
	// adicionar, remover e localizar
	public void adicionar(Conta c) {
		contas.add(c);
		c.setMesa(this);
	}
	public void remover(Conta c) {
		contas.remove(c);
		c.setMesa(null);
	}
	public Conta localizar(int num) {
		for (Conta c: contas)
			if(c.getNumero() == num) return c;
		return null;
	}
	
	// construtor e toString
	public Mesa(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		String txt = "\nMesa \n  id: " + id + "\n  ocupada: " + ocupada + "\n  contas: ";
		if(contas.size()>0) {
			for (Conta c: contas) 
				txt += "\n    n: " + c.getNumero()+ " ";
		}
		txt += "\n  garçom: " ;
		if(garcom == null) {
			txt += garcom;
		}else {
			txt += garcom.getApelido();			
		}
		return txt;
				
	}
	
}
