package org.test;

import java.util.ArrayList;
import java.util.List;

import org.entity.Transport;
import org.entity.Transport_International;
import org.entity.Transport_National;

public class TestTransport{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("TransportPersoaneJPA");
		EntityManager em=emf.createEntityManager();
		
		List <Transport> lstTransportPersistent=em.createQuery("Select t From Transport t",Transport.class).getResultList();
		if(!lstTransportPersistent.isEmpty()) {
			em.getTransaction().begin();
			for(Transport t:lstTransportPersistent) em.remove(t);
			em.getTransaction().commit();
		}
		List<Transport> tipuriTransport=new ArrayList<Transport>();
		//Initializare explicita  a unor tipuri de transport oferite

		Transport_National t1=new Transport_National(1,"Transport national_1",50.0,"interurban","Iasi","Hirlau", 75 , 68  );
		Transport_National t2=new Transport_National(2,"Transport national_2",50.0,"interurban","Iasi","Pascani", 69 , 73  );
		Transport_National t3=new Transport_National (3,"Transport national_3",140.0,"interegional","Iasi","Cluj", 394, 418 );
		Transport_National t4=new Transport_National (4,"Transport national_4",140.0,"interegional","Iasi","Bucuresti", 330, 383 );
		
		tipuriTransport.add(t1);
		tipuriTransport.add(t2);
		tipuriTransport.add(t3);
		tipuriTransport.add(t4);
		
		Transport_International t5= new Transport_International(5,"Transport international_5",2000.0,"intercontinental","Iasi","Tokyo", 2729, 3602);
		Transport_International t6= new Transport_International(6,"Transport international_6",1900.0,"intercontinental", "Iasi","Bejing",6394, 8624);
		Transport_International t7= new Transport_International(7,"Transport international_7",506.0,"de cabotaj","Iasi","Cracovia", 648,859);
		Transport_International t8= new Transport_International(8,"Transport international_8",1037.0,"de cabotaj","Iasi","Hamburg",1141, 1762);
		tipuriTransport.add(t5);
		tipuriTransport.add(t6);
		tipuriTransport.add(t7);
		tipuriTransport.add(t8);
		
	
		
		em.getTransaction().begin();
		tipuriTransport.stream().forEach(t->em.persist(t));
		em.getTransaction().commit();
		//Read after create
		lstTransportPersistent=em.createQuery("Select t From Transport t",Transport.class).getResultList();
		System.out.println("Lista transport persistent/salvat in baza de date");
		for (Transport t:lstTransportPersistent)
			System.out.println("Id: "+t.getIdTransport()+", denumire: "+t.getNume()+", pret: "+t.getPretTransport().toString());
		
	}
}



	
