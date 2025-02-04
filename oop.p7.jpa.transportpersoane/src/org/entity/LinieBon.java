package org.entity;

import java.util.Objects;

public class LinieBon {
private Integer idLinie;
	
Transport transport;

Bon bon;
private Double TVALinie;
private Double valoareLinie;




public Integer getIdLinie() {
	return idLinie;
}
public void setIdLinie(Integer idLinie) {
	this.idLinie = idLinie;
}
public Transport getTransport() {
	return transport;
}
public void setTransport(Transport transport) {
	this.transport = transport;
}
public Bon getBon() {
	return bon;
}
public void setBon(Bon bon) {
	this.bon = bon;
}

public Double getTVALinie() {
	if(TVALinie==null || TVALinie==0) TVALinie=calcTVALinie();
	return TVALinie;
}
public Double getValoareLinie() {
	if(valoareLinie==null || valoareLinie==0.0) valoareLinie=calcValLinie();
	return valoareLinie;
}
Double calcValLinie() {
	Double val=null;
	if(transport!=null)
		val=transport.getPretTransport();
	return val;
}
Double calcTVALinie() {
	Double valTVA=null;
	if(transport!=null )
		valTVA=0.19/1.19*(transport.getPretTransport());
	return valTVA;
}
public LinieBon(Integer idLinie, Transport transport, Bon bon, Double TVALinie,
		Double valoareLinie) {
	super();
	this.idLinie = idLinie;
	this.transport = transport;
	this.bon = bon;
	this.TVALinie = TVALinie;
	this.valoareLinie = valoareLinie;
}
public LinieBon() {
	super();
}
public static void add(LinieBon linieBon) {
	LinieBon.add(linieBon);
}
@Override
public int hashCode() {
	return Objects.hash(TVALinie, bon, idLinie, transport, valoareLinie);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	LinieBon other = (LinieBon) obj;
	return Objects.equals(TVALinie, other.TVALinie) && Objects.equals(bon, other.bon)
			&& Objects.equals(idLinie, other.idLinie) && Objects.equals(transport, other.transport)
			&& Objects.equals(valoareLinie, other.valoareLinie);
}
@Override
public String toString() {
	return "LinieBon [idLinie=" + idLinie + ", transport=" + transport + ", bon=" + bon + ", TVALinie="
			+ TVALinie + ", valoareLinie=" + valoareLinie + "]";
}


}



	