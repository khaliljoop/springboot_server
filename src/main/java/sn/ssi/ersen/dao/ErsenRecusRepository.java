package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenDetailRecusEntity;
import sn.ssi.ersen.entity.ErsenRecusEntity;

import java.util.Collection;
import java.util.List;

@Repository
public interface ErsenRecusRepository extends JpaRepository<ErsenRecusEntity,String> {
    @RestResource


    /*@Query(value = "select new sn.ssi.ersen.entity.ErsenRecusEntity(r.id,r.montantTotal,r.abonne,c.id,r.annee,r.idReceveur,r.isSync,r.date)\n" +
            "FROM ErsenAbonneEntity a, ErsenRecusEntity r, ErsenCentraleEntity c\n" +
            "where a.id=r.abonne and r.centrale=c.id")
    List<ErsenRecusEntity> getAllRecu();*/

    /*@Query(value = "select new sn.ssi.ersen.entity.ErsenRecusEntity(r.id,r.montantTotal,r.abonne,a.centrale,r.annee,r.idReceveur,r.isSync,r._date)\n" +
            "FROM  ErsenRecusEntity r, ErsenAbonneEntity a\n" +
            "where  r.abonne=a.id and a.centrale=:centrale")
    List<ErsenRecusEntity> getErsenRecusEntitiesByCentrale(String centrale);*/

    @Query(value = "select new sn.ssi.ersen.entity.ErsenRecusEntity(r.id,r.montantTotal,r.abonne,r.centrale,r.annee,r.idReceveur,r.isSync,r._date)\n" +
            "FROM  ErsenRecusEntity r where  r.abonne in (SELECT a.id from ErsenAbonneEntity a where a.centrale in :centrales)")
    List<ErsenRecusEntity> getErsenRecusEntitiesByAbonne(List<String> centrales);

    @Query(value = "select dr from ErsenDetailRecusEntity  dr where dr.idRecu=:idRecu")
    List<ErsenDetailRecusEntity> getErsenDetailRecusByRecu(String idRecu);


    @Query(value = "select new sn.ssi.ersen.entity.ErsenRecusEntity(r.id,r.montantTotal,r.abonne,a.centrale,r.annee,r.idReceveur,r.isSync,r._date)\n" +
            "FROM  ErsenRecusEntity r, ErsenAbonneEntity a\n" +
            "where  r.abonne=a.id and a.centrale=:centrale")
    List<ErsenRecusEntity> getErsenRecusEntitiesByCentrale(String centrale);
    @Query(value = "select r from ErsenRecusEntity r where r.isSync=1 AND r.centrale in :centrales")
    List<ErsenRecusEntity> getErsenRecusEntitiesByIsSync(List<String>centrales);
    @Query(value = "select r from ErsenRecusEntity r where r.id not in :recuIds")
    List<ErsenRecusEntity> getErsenNewRecus(List<String>recuIds);
}
