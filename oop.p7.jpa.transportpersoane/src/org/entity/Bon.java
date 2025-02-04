package org.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Bon {
	private Integer nrBon;

	private Date data=new Date();

	private List<LinieBon> linieBon=new ArrayList<LinieBon>();

	private Calatori calatori;

	private Double totalBon;
	private Double totalTVA;
	
	public Integer getNrBon() {
		return nrBon;
	}
	public void setNrBon(Integer nrBon) {
		this.nrBon = nrBon;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public List<LinieBon> getLinieBon() {
		return linieBon;
	}
	public void setLinieBon(List<LinieBon> linieBon) {
		this.linieBon = linieBon;
	}
	public Calatori getCalatori() {
		return calatori;
	}
	public void setCalatori(Calatori calatori) {
		this.calatori = calatori;
	}
	
	public void setTotalBon(Double totalBon) {
		this.totalBon = totalBon;
	}

	public void setTotalTVA(Double totalTVA) {
		this.totalTVA = totalTVA;
	}

	public Double getTotalBon() {
		if(linieBon.isEmpty()) return null;
		Double totalBon=0.0;
		for(LinieBon lb:linieBon)
			totalBon+=lb.getValoareLinie();
		return totalBon;
	}
	
	Double calculTotal() {
		Double totalBon=.0;
		for(LinieBon lb:linieBon ) totalBon+=lb.getValoareLinie();
		return totalBon;
	}
	public Double getTotalTVA() {
		if(linieBon.isEmpty())
			return null;
		Double totalBon=calculTotal();
		return 0.19/1.09*totalBon; // se aplica tva de 19%
	}
	
	
	
	
	public void adaugaLinie (LinieBon linieBon) {
		LinieBon.add(linieBon);
	}
	public void adauga(Transport transport) {
		LinieBon lb  =new LinieBon();
		lb.setBon(this);
		lb.setTransport(transport);
		this.linieBon.add(lb);
	}
	public Bon(Integer nrBon, Date data, List<LinieBon> linieBon, Calatori calatori, Double totalBon,
			Double totalTVA) {
		super();
		this.nrBon = nrBon;
		this.data = data;
		this.linieBon = linieBon;
		this.calatori = calatori;
		this.totalBon = totalBon;
		this.totalTVA = totalTVA;
	}
	public Bon() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(calatori, data, linieBon, nrBon, totalBon, totalTVA);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bon other = (Bon) obj;
		return Objects.equals(calatori, other.calatori) && Objects.equals(data, other.data)
				&& Objects.equals(linieBon, other.linieBon) && Objects.equals(nrBon, other.nrBon)
				&& Objects.equals(totalBon, other.totalBon) && Objects.equals(totalTVA, other.totalTVA);
	}
	@Override
	public String toString() {
		return "Bon [nrBon=" + nrBon + ", data=" + data + ", linieBon=" + linieBon + ", calatori=" + calatori
				+ ", totalBon=" + totalBon + ", totalTVA=" + totalTVA + "]";
	}
	
	
}


