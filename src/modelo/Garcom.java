package modelo;
import java.util.ArrayList;

public class Garcom {
	private String apelido;
	private ArrayList<Mesa> mesas = new ArrayList<Mesa>();
	
	//gets e sets
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public ArrayList<Mesa> getMesas() {
		return mesas;
	}
	public void setMesas(ArrayList<Mesa> mesas) {
		this.mesas = mesas;
	}
	
	//adicionar, remover e localizar
	public void adicionar(Mesa m) {
		mesas.add(m);
		m.setGarcom(this);
	}
	public void remover(Mesa m) {
		mesas.remove(m);
		m.setGarcom(null);
	}
	public Mesa localizar(int id) {
		for (Mesa m: mesas)
			if(m.getId()== id) return m;
		return null;
	}
	
	// construtor e toString
	public Garcom(String apelido) {
		super();
		this.apelido = apelido;
	}
	@Override
	public String toString() {
		String txt = "\nGarcom \n apelido: " + apelido + "\n mesas: ";
		for (Mesa m: mesas)
			txt += "\n   id: " + m.getId() + ", ocupada: " + m.isOcupada()+ ", garçom: " + m.getGarcom().getApelido();
		return txt;
	}
	
	
}
