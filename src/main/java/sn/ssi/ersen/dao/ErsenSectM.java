package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sn.ssi.ersen.entity.ErsenSectionmaintenanceEntity;
import sn.ssi.ersen.entity.entitieMobile.SectionMaintenance;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource
public interface ErsenSectM extends JpaRepository<ErsenSectionmaintenanceEntity,String> {
    @Query("select distinct new sn.ssi.ersen.entity.entitieMobile.SectionMaintenance(sm.id, sm.libelle, sm.urlimage, csm.isUsed) from  ErsenSectionmaintenanceEntity sm,ErsenConfigCentraleSectionM csm " +
            "where sm.id=csm.sectionMId and csm.isUsed = true and csm.centraleId in :idCentrals")
    List<SectionMaintenance> getSectMs(List<String> idCentrals);

    @Query("select distinct new sn.ssi.ersen.entity.entitieMobile.SectionMaintenance(sm.id, sm.libelle, sm.urlimage, csm.isUsed) from  ErsenSectionmaintenanceEntity sm,ErsenConfigCentraleSectionM csm " +
            "where sm.id=csm.sectionMId and csm.isUsed = true and csm.centraleId in :idCentrals and sm.id not in :oldSectionMIds")
    List<SectionMaintenance> getCentalNewSectM(List<String> idCentrals, List<String> oldSectionMIds);

    @Query("select distinct csm.sectionMId from ErsenConfigCentraleSectionM csm where csm.centraleId in :idCentrals and csm.sectionMId in :oldSectionMIds")
    List<String> getNotDeletedSectionM(List<String> idCentrals,List<String> oldSectionMIds);
}
