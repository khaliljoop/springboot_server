package sn.ssi.ersen.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import sn.ssi.ersen.dto.AbonneeDto;
import sn.ssi.ersen.dto.CentraleDto;
import sn.ssi.ersen.dto.ProjectionAdresse;
import sn.ssi.ersen.dto.ProjectionCentrale;
import sn.ssi.ersen.dto.ProjectionCentraleAbonne;
import sn.ssi.ersen.dto.ProjectionNumeroAbonnementDto;
import sn.ssi.ersen.dto.projectionabonnedto.ProjectionCategorieNom;
import sn.ssi.ersen.dto.projectionabonnedto.ProjectionTypeCategorieNom;
import sn.ssi.ersen.dto.projectioncentraledto.ProjectionCentraleCommune;
import sn.ssi.ersen.dto.projectioncentraledto.ProjectionCentraleDepartement;
import sn.ssi.ersen.dto.projectioncentraledto.ProjectionCentraleOperateur;
import sn.ssi.ersen.dto.projectioncentraledto.ProjectionCentraleProjet;
import sn.ssi.ersen.dto.projectioncentraledto.ProjectionCentraleRegion;
import sn.ssi.ersen.dto.projectioncentraledto.ProjectionCentraleTypeCentrale;
import sn.ssi.ersen.dto.projectioncentraledto.ProjectionCentraleVillage;
import sn.ssi.ersen.dto.utilisateurStat.UtilisateurDto;
import sn.ssi.ersen.entity.ErsenAbonneEntity;
import sn.ssi.ersen.entity.ErsenCategorieEntity;
import sn.ssi.ersen.entity.ErsenCentraleEntity;
import sn.ssi.ersen.entity.ErsenCommuneEntity;
import sn.ssi.ersen.entity.ErsenDepartementEntity;
import sn.ssi.ersen.entity.ErsenProjetEntity;
import sn.ssi.ersen.entity.ErsenRegionEntity;
import sn.ssi.ersen.entity.ErsenTypecategorieEntity;
import sn.ssi.ersen.entity.ErsenTypecentraleEntity;
import sn.ssi.ersen.entity.ErsenVillagequartierEntity;
import sn.ssi.ersen.entity.UtilisateurEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-23T16:12:01+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2 (Oracle Corporation)"
)
@Component
@Qualifier("delegate")
public class RequeteMapperImpl_ implements RequeteMapper {

    @Override
    public AbonneeDto abonneEntityToAbonneDto(ErsenAbonneEntity ersenAbonneEntity) {
        if ( ersenAbonneEntity == null ) {
            return null;
        }

        AbonneeDto abonneeDto = new AbonneeDto();

        abonneeDto.setAdresse( ersenAbonneEntity.getAdresse() );
        abonneeDto.setCni( ersenAbonneEntity.getCni() );
        abonneeDto.setNom( ersenAbonneEntity.getNom() );
        abonneeDto.setNumeroabonnement( ersenAbonneEntity.getNumeroabonnement() );
        abonneeDto.setPrenom( ersenAbonneEntity.getPrenom() );
        abonneeDto.setTelephone( ersenAbonneEntity.getTelephone() );
        abonneeDto.setCentrale( ersenAbonneEntity.getCentrale() );
        abonneeDto.setPuissancesouscrite( ersenAbonneEntity.getPuissancesouscrite() );
        abonneeDto.setSexe( ersenAbonneEntity.getSexe() );
        abonneeDto.setTypecategorie( ersenAbonneEntity.getTypecategorie() );
        abonneeDto.setDate( ersenAbonneEntity.getDate() );
        abonneeDto.setId( ersenAbonneEntity.getId() );

        return abonneeDto;
    }

    @Override
    public ErsenAbonneEntity abonneDtoToAbonneEntity(AbonneeDto abonneeDto) {
        if ( abonneeDto == null ) {
            return null;
        }

        ErsenAbonneEntity ersenAbonneEntity = new ErsenAbonneEntity();

        ersenAbonneEntity.setId( abonneeDto.getId() );
        ersenAbonneEntity.setAdresse( abonneeDto.getAdresse() );
        ersenAbonneEntity.setCni( abonneeDto.getCni() );
        ersenAbonneEntity.setNom( abonneeDto.getNom() );
        ersenAbonneEntity.setNumeroabonnement( abonneeDto.getNumeroabonnement() );
        ersenAbonneEntity.setPrenom( abonneeDto.getPrenom() );
        ersenAbonneEntity.setTelephone( abonneeDto.getTelephone() );
        ersenAbonneEntity.setCentrale( abonneeDto.getCentrale() );
        ersenAbonneEntity.setPuissancesouscrite( abonneeDto.getPuissancesouscrite() );
        ersenAbonneEntity.setSexe( abonneeDto.getSexe() );
        ersenAbonneEntity.setTypecategorie( abonneeDto.getTypecategorie() );
        ersenAbonneEntity.setDate( abonneeDto.getDate() );

        return ersenAbonneEntity;
    }

    @Override
    public List<AbonneeDto> abonneEntityToAbonneDTOList(List<ErsenAbonneEntity> ersenAbonneEntities) {
        if ( ersenAbonneEntities == null ) {
            return null;
        }

        List<AbonneeDto> list = new ArrayList<AbonneeDto>( ersenAbonneEntities.size() );
        for ( ErsenAbonneEntity ersenAbonneEntity : ersenAbonneEntities ) {
            list.add( abonneEntityToAbonneDto( ersenAbonneEntity ) );
        }

        return list;
    }

    @Override
    public ProjectionAdresse abonneEntityToProjection(ErsenAbonneEntity ersenAbonneEntity) {
        if ( ersenAbonneEntity == null ) {
            return null;
        }

        ProjectionAdresse projectionAdresse = new ProjectionAdresse();

        projectionAdresse.setAdresse( ersenAbonneEntity.getAdresse() );

        return projectionAdresse;
    }

    @Override
    public ErsenAbonneEntity ProjectionToAbonneEntity(ProjectionAdresse projectionAdresse) {
        if ( projectionAdresse == null ) {
            return null;
        }

        ErsenAbonneEntity ersenAbonneEntity = new ErsenAbonneEntity();

        ersenAbonneEntity.setAdresse( projectionAdresse.getAdresse() );

        return ersenAbonneEntity;
    }

    @Override
    public List<ProjectionAdresse> abonneEntityToProjection(List<ErsenAbonneEntity> ersenAbonneEntities) {
        if ( ersenAbonneEntities == null ) {
            return null;
        }

        List<ProjectionAdresse> list = new ArrayList<ProjectionAdresse>( ersenAbonneEntities.size() );
        for ( ErsenAbonneEntity ersenAbonneEntity : ersenAbonneEntities ) {
            list.add( abonneEntityToProjection( ersenAbonneEntity ) );
        }

        return list;
    }

    @Override
    public ProjectionCentraleAbonne abonneEntityToProjectionCentraleAbonne(ErsenCentraleEntity centraleEntity) {
        if ( centraleEntity == null ) {
            return null;
        }

        ProjectionCentraleAbonne projectionCentraleAbonne = new ProjectionCentraleAbonne();

        projectionCentraleAbonne.setNom( centraleEntity.getNom() );

        return projectionCentraleAbonne;
    }

    @Override
    public ErsenCentraleEntity ProjectionCentraleAbonneToAbonneEntity(ProjectionCentraleAbonne projectionCentraleAbonne) {
        if ( projectionCentraleAbonne == null ) {
            return null;
        }

        ErsenCentraleEntity ersenCentraleEntity = new ErsenCentraleEntity();

        ersenCentraleEntity.setNom( projectionCentraleAbonne.getNom() );

        return ersenCentraleEntity;
    }

    @Override
    public List<ProjectionCentraleAbonne> abonneEntityToProjectioCentraleAbonne(List<ErsenCentraleEntity> ersenCentraleEntities) {
        if ( ersenCentraleEntities == null ) {
            return null;
        }

        List<ProjectionCentraleAbonne> list = new ArrayList<ProjectionCentraleAbonne>( ersenCentraleEntities.size() );
        for ( ErsenCentraleEntity ersenCentraleEntity : ersenCentraleEntities ) {
            list.add( abonneEntityToProjectionCentraleAbonne( ersenCentraleEntity ) );
        }

        return list;
    }

    @Override
    public ProjectionNumeroAbonnementDto abonneEntityToProjectionNumeroAbonnement(ErsenAbonneEntity ersenAbonneEntity) {
        if ( ersenAbonneEntity == null ) {
            return null;
        }

        ProjectionNumeroAbonnementDto projectionNumeroAbonnementDto = new ProjectionNumeroAbonnementDto();

        projectionNumeroAbonnementDto.setNumeroabonnement( ersenAbonneEntity.getNumeroabonnement() );

        return projectionNumeroAbonnementDto;
    }

    @Override
    public ErsenAbonneEntity ProjectionNumeroAbonnementToAbonneEntity(ProjectionNumeroAbonnementDto projectionNumeroAbonnementDto) {
        if ( projectionNumeroAbonnementDto == null ) {
            return null;
        }

        ErsenAbonneEntity ersenAbonneEntity = new ErsenAbonneEntity();

        ersenAbonneEntity.setNumeroabonnement( projectionNumeroAbonnementDto.getNumeroabonnement() );

        return ersenAbonneEntity;
    }

    @Override
    public List<ProjectionNumeroAbonnementDto> abonneEntityToProjectioNumeroAbonnement(List<ErsenAbonneEntity> ersenAbonneEntities) {
        if ( ersenAbonneEntities == null ) {
            return null;
        }

        List<ProjectionNumeroAbonnementDto> list = new ArrayList<ProjectionNumeroAbonnementDto>( ersenAbonneEntities.size() );
        for ( ErsenAbonneEntity ersenAbonneEntity : ersenAbonneEntities ) {
            list.add( abonneEntityToProjectionNumeroAbonnement( ersenAbonneEntity ) );
        }

        return list;
    }

    @Override
    public ProjectionCategorieNom categorieEntityToProjectionCategorieDtoNom(ErsenCategorieEntity ersenCategorieEntity) {
        if ( ersenCategorieEntity == null ) {
            return null;
        }

        ProjectionCategorieNom projectionCategorieNom = new ProjectionCategorieNom();

        projectionCategorieNom.setLibelle( ersenCategorieEntity.getLibelle() );

        return projectionCategorieNom;
    }

    @Override
    public ErsenCategorieEntity projectionCategorieNomToCategorieEntity(ProjectionCategorieNom projectionCategorieNom) {
        if ( projectionCategorieNom == null ) {
            return null;
        }

        ErsenCategorieEntity ersenCategorieEntity = new ErsenCategorieEntity();

        ersenCategorieEntity.setLibelle( projectionCategorieNom.getLibelle() );

        return ersenCategorieEntity;
    }

    @Override
    public List<ProjectionCategorieNom> categorieEntityToProjectioCategorieDtoNomList(List<ErsenCategorieEntity> ersenCategorieEntities) {
        if ( ersenCategorieEntities == null ) {
            return null;
        }

        List<ProjectionCategorieNom> list = new ArrayList<ProjectionCategorieNom>( ersenCategorieEntities.size() );
        for ( ErsenCategorieEntity ersenCategorieEntity : ersenCategorieEntities ) {
            list.add( categorieEntityToProjectionCategorieDtoNom( ersenCategorieEntity ) );
        }

        return list;
    }

    @Override
    public ProjectionTypeCategorieNom typeCategorieEntityToProjectionTypeCategorieDtoNom(ErsenTypecategorieEntity ersenTypecategorieEntity) {
        if ( ersenTypecategorieEntity == null ) {
            return null;
        }

        ProjectionTypeCategorieNom projectionTypeCategorieNom = new ProjectionTypeCategorieNom();

        projectionTypeCategorieNom.setLibelle( ersenTypecategorieEntity.getLibelle() );
        projectionTypeCategorieNom.setCategorie( ersenTypecategorieEntity.getCategorie() );

        return projectionTypeCategorieNom;
    }

    @Override
    public ErsenCategorieEntity projectionTypeCategorieNomToTypeCategorieEntity(ProjectionTypeCategorieNom projectionTypeCategorieNom) {
        if ( projectionTypeCategorieNom == null ) {
            return null;
        }

        ErsenCategorieEntity ersenCategorieEntity = new ErsenCategorieEntity();

        ersenCategorieEntity.setLibelle( projectionTypeCategorieNom.getLibelle() );

        return ersenCategorieEntity;
    }

    @Override
    public List<ProjectionTypeCategorieNom> typeCategorieEntityToProjectioCategorieDtoNomList(List<ErsenTypecategorieEntity> ersenTypecategorieEntities) {
        if ( ersenTypecategorieEntities == null ) {
            return null;
        }

        List<ProjectionTypeCategorieNom> list = new ArrayList<ProjectionTypeCategorieNom>( ersenTypecategorieEntities.size() );
        for ( ErsenTypecategorieEntity ersenTypecategorieEntity : ersenTypecategorieEntities ) {
            list.add( typeCategorieEntityToProjectionTypeCategorieDtoNom( ersenTypecategorieEntity ) );
        }

        return list;
    }

    @Override
    public CentraleDto centraleEntityToCentraleDto(ErsenCentraleEntity centraleEntity) {
        if ( centraleEntity == null ) {
            return null;
        }

        CentraleDto centraleDto = new CentraleDto();

        centraleDto.setAdresse( centraleEntity.getAdresse() );
        centraleDto.setCoutinstallation( centraleEntity.getCoutinstallation() );
        centraleDto.setNom( centraleEntity.getNom() );
        centraleDto.setOperateur( centraleEntity.getOperateur() );
        centraleDto.setTypecentrale( centraleEntity.getTypecentrale() );
        centraleDto.setVillage( centraleEntity.getVillage() );
        centraleDto.setCommune( centraleEntity.getCommune() );
        centraleDto.setDepartement( centraleEntity.getDepartement() );
        centraleDto.setRegion( centraleEntity.getRegion() );
        centraleDto.setProjet( centraleEntity.getProjet() );
        centraleDto.setEtat_centrale( centraleEntity.getEtat_centrale() );

        return centraleDto;
    }

    @Override
    public ErsenCentraleEntity centraleDtoToCentraleEntity(CentraleDto centraleDto) {
        if ( centraleDto == null ) {
            return null;
        }

        ErsenCentraleEntity ersenCentraleEntity = new ErsenCentraleEntity();

        ersenCentraleEntity.setAdresse( centraleDto.getAdresse() );
        if ( centraleDto.getCoutinstallation() != null ) {
            ersenCentraleEntity.setCoutinstallation( centraleDto.getCoutinstallation() );
        }
        ersenCentraleEntity.setNom( centraleDto.getNom() );
        ersenCentraleEntity.setOperateur( centraleDto.getOperateur() );
        ersenCentraleEntity.setTypecentrale( centraleDto.getTypecentrale() );
        ersenCentraleEntity.setVillage( centraleDto.getVillage() );
        ersenCentraleEntity.setCommune( centraleDto.getCommune() );
        ersenCentraleEntity.setDepartement( centraleDto.getDepartement() );
        ersenCentraleEntity.setRegion( centraleDto.getRegion() );
        ersenCentraleEntity.setProjet( centraleDto.getProjet() );
        ersenCentraleEntity.setEtat_centrale( centraleDto.getEtat_centrale() );

        return ersenCentraleEntity;
    }

    @Override
    public List<CentraleDto> centraleEntityToCentraleDtoList(List<ErsenCentraleEntity> ersenCentraleEntities) {
        if ( ersenCentraleEntities == null ) {
            return null;
        }

        List<CentraleDto> list = new ArrayList<CentraleDto>( ersenCentraleEntities.size() );
        for ( ErsenCentraleEntity ersenCentraleEntity : ersenCentraleEntities ) {
            list.add( centraleEntityToCentraleDto( ersenCentraleEntity ) );
        }

        return list;
    }

    @Override
    public ProjectionCentrale centaleEntityProjectionCentrale(ErsenCentraleEntity centraleEntity) {
        if ( centraleEntity == null ) {
            return null;
        }

        ProjectionCentrale projectionCentrale = new ProjectionCentrale();

        projectionCentrale.setAdresse( centraleEntity.getAdresse() );
        projectionCentrale.setNom( centraleEntity.getNom() );
        projectionCentrale.setOperateur( centraleEntity.getOperateur() );
        projectionCentrale.setTypecentrale( centraleEntity.getTypecentrale() );
        projectionCentrale.setVillage( centraleEntity.getVillage() );
        projectionCentrale.setRegion( centraleEntity.getRegion() );
        projectionCentrale.setProjet( centraleEntity.getProjet() );

        return projectionCentrale;
    }

    @Override
    public ErsenCentraleEntity projectionCentraleToCentrale(ProjectionCentrale projectionCentrale) {
        if ( projectionCentrale == null ) {
            return null;
        }

        ErsenCentraleEntity ersenCentraleEntity = new ErsenCentraleEntity();

        ersenCentraleEntity.setAdresse( projectionCentrale.getAdresse() );
        ersenCentraleEntity.setNom( projectionCentrale.getNom() );
        ersenCentraleEntity.setOperateur( projectionCentrale.getOperateur() );
        ersenCentraleEntity.setTypecentrale( projectionCentrale.getTypecentrale() );
        ersenCentraleEntity.setVillage( projectionCentrale.getVillage() );
        ersenCentraleEntity.setRegion( projectionCentrale.getRegion() );
        ersenCentraleEntity.setProjet( projectionCentrale.getProjet() );

        return ersenCentraleEntity;
    }

    @Override
    public List<ProjectionCentrale> centraleEntityToProjectionCentraleList(List<ErsenCentraleEntity> ersenCentraleEntities) {
        if ( ersenCentraleEntities == null ) {
            return null;
        }

        List<ProjectionCentrale> list = new ArrayList<ProjectionCentrale>( ersenCentraleEntities.size() );
        for ( ErsenCentraleEntity ersenCentraleEntity : ersenCentraleEntities ) {
            list.add( centaleEntityProjectionCentrale( ersenCentraleEntity ) );
        }

        return list;
    }

    @Override
    public ProjectionCentraleOperateur centraleEntityToProjectionOperateur(ErsenCentraleEntity centraleEntity) {
        if ( centraleEntity == null ) {
            return null;
        }

        ProjectionCentraleOperateur projectionCentraleOperateur = new ProjectionCentraleOperateur();

        projectionCentraleOperateur.setOperateur( centraleEntity.getOperateur() );

        return projectionCentraleOperateur;
    }

    @Override
    public ErsenCentraleEntity projectionOperateurToCentraleEntity(ProjectionCentraleOperateur projectionCentraleOperateur) {
        if ( projectionCentraleOperateur == null ) {
            return null;
        }

        ErsenCentraleEntity ersenCentraleEntity = new ErsenCentraleEntity();

        ersenCentraleEntity.setOperateur( projectionCentraleOperateur.getOperateur() );

        return ersenCentraleEntity;
    }

    @Override
    public List<ProjectionCentraleOperateur> centraleEntityProjectionOperateurList(List<ErsenCentraleEntity> ersenCentraleEntities) {
        if ( ersenCentraleEntities == null ) {
            return null;
        }

        List<ProjectionCentraleOperateur> list = new ArrayList<ProjectionCentraleOperateur>( ersenCentraleEntities.size() );
        for ( ErsenCentraleEntity ersenCentraleEntity : ersenCentraleEntities ) {
            list.add( centraleEntityToProjectionOperateur( ersenCentraleEntity ) );
        }

        return list;
    }

    @Override
    public ProjectionCentraleProjet projetEntityToProjectionProjet(ErsenProjetEntity centraleEntity) {
        if ( centraleEntity == null ) {
            return null;
        }

        ProjectionCentraleProjet projectionCentraleProjet = new ProjectionCentraleProjet();

        projectionCentraleProjet.setNom( centraleEntity.getNom() );

        return projectionCentraleProjet;
    }

    @Override
    public ErsenProjetEntity projectionOperateurToProjet(ProjectionCentraleProjet projectionCentraleProjet) {
        if ( projectionCentraleProjet == null ) {
            return null;
        }

        ErsenProjetEntity ersenProjetEntity = new ErsenProjetEntity();

        ersenProjetEntity.setNom( projectionCentraleProjet.getNom() );

        return ersenProjetEntity;
    }

    @Override
    public List<ProjectionCentraleProjet> projetProjectionProjetListe(List<ErsenProjetEntity> ersenProjetEntities) {
        if ( ersenProjetEntities == null ) {
            return null;
        }

        List<ProjectionCentraleProjet> list = new ArrayList<ProjectionCentraleProjet>( ersenProjetEntities.size() );
        for ( ErsenProjetEntity ersenProjetEntity : ersenProjetEntities ) {
            list.add( projetEntityToProjectionProjet( ersenProjetEntity ) );
        }

        return list;
    }

    @Override
    public ProjectionCentraleRegion regionEntityToProjectionCentraleRegion(ErsenProjetEntity centraleEntity) {
        if ( centraleEntity == null ) {
            return null;
        }

        ProjectionCentraleRegion projectionCentraleRegion = new ProjectionCentraleRegion();

        projectionCentraleRegion.setId( centraleEntity.getId() );

        return projectionCentraleRegion;
    }

    @Override
    public ErsenRegionEntity projectionCentraleRegionToRegionEntity(ProjectionCentraleRegion projectionCentraleRegion) {
        if ( projectionCentraleRegion == null ) {
            return null;
        }

        ErsenRegionEntity ersenRegionEntity = new ErsenRegionEntity();

        ersenRegionEntity.setId( projectionCentraleRegion.getId() );
        ersenRegionEntity.setLibelle( projectionCentraleRegion.getLibelle() );

        return ersenRegionEntity;
    }

    @Override
    public List<ProjectionCentraleRegion> projetToProjectionCentraleRegionList(List<ErsenRegionEntity> ersenRegionEntities) {
        if ( ersenRegionEntities == null ) {
            return null;
        }

        List<ProjectionCentraleRegion> list = new ArrayList<ProjectionCentraleRegion>( ersenRegionEntities.size() );
        for ( ErsenRegionEntity ersenRegionEntity : ersenRegionEntities ) {
            list.add( ersenRegionEntityToProjectionCentraleRegion( ersenRegionEntity ) );
        }

        return list;
    }

    @Override
    public ProjectionCentraleDepartement departementEntityToProjectionCentraleDepartementDto(ErsenDepartementEntity ersenDepartementEntity) {
        if ( ersenDepartementEntity == null ) {
            return null;
        }

        ProjectionCentraleDepartement projectionCentraleDepartement = new ProjectionCentraleDepartement();

        projectionCentraleDepartement.setId( ersenDepartementEntity.getId() );
        projectionCentraleDepartement.setLibelle( ersenDepartementEntity.getLibelle() );
        projectionCentraleDepartement.setRegion( ersenDepartementEntity.getRegion() );

        return projectionCentraleDepartement;
    }

    @Override
    public ErsenDepartementEntity projectionCentraleDepartementToDepartementEntity(ProjectionCentraleDepartement projectionCentraleDepartement) {
        if ( projectionCentraleDepartement == null ) {
            return null;
        }

        ErsenDepartementEntity ersenDepartementEntity = new ErsenDepartementEntity();

        ersenDepartementEntity.setId( projectionCentraleDepartement.getId() );
        ersenDepartementEntity.setLibelle( projectionCentraleDepartement.getLibelle() );
        ersenDepartementEntity.setRegion( projectionCentraleDepartement.getRegion() );

        return ersenDepartementEntity;
    }

    @Override
    public List<ProjectionCentraleDepartement> departementEntityListToProjectionCentraleDepartementList(List<ErsenDepartementEntity> ersenDepartementEntities) {
        if ( ersenDepartementEntities == null ) {
            return null;
        }

        List<ProjectionCentraleDepartement> list = new ArrayList<ProjectionCentraleDepartement>( ersenDepartementEntities.size() );
        for ( ErsenDepartementEntity ersenDepartementEntity : ersenDepartementEntities ) {
            list.add( departementEntityToProjectionCentraleDepartementDto( ersenDepartementEntity ) );
        }

        return list;
    }

    @Override
    public ProjectionCentraleCommune communeEntityToProjectionCentraleCommune(ErsenCommuneEntity ersenCommuneEntity) {
        if ( ersenCommuneEntity == null ) {
            return null;
        }

        ProjectionCentraleCommune projectionCentraleCommune = new ProjectionCentraleCommune();

        projectionCentraleCommune.setId( ersenCommuneEntity.getId() );
        projectionCentraleCommune.setLibelle( ersenCommuneEntity.getLibelle() );
        projectionCentraleCommune.setDepartement( ersenCommuneEntity.getDepartement() );

        return projectionCentraleCommune;
    }

    @Override
    public ErsenCommuneEntity projectionCentraleCommuneToCommuneEntity(ProjectionCentraleCommune projectionCentraleCommune) {
        if ( projectionCentraleCommune == null ) {
            return null;
        }

        ErsenCommuneEntity ersenCommuneEntity = new ErsenCommuneEntity();

        ersenCommuneEntity.setId( projectionCentraleCommune.getId() );
        ersenCommuneEntity.setLibelle( projectionCentraleCommune.getLibelle() );
        ersenCommuneEntity.setDepartement( projectionCentraleCommune.getDepartement() );

        return ersenCommuneEntity;
    }

    @Override
    public List<ProjectionCentraleCommune> communeEntityListToProjectionCentraleCommunetList(List<ErsenCommuneEntity> ersenCommuneEntities) {
        if ( ersenCommuneEntities == null ) {
            return null;
        }

        List<ProjectionCentraleCommune> list = new ArrayList<ProjectionCentraleCommune>( ersenCommuneEntities.size() );
        for ( ErsenCommuneEntity ersenCommuneEntity : ersenCommuneEntities ) {
            list.add( communeEntityToProjectionCentraleCommune( ersenCommuneEntity ) );
        }

        return list;
    }

    @Override
    public ProjectionCentraleVillage villageToProjectionCentraleRegion(ErsenVillagequartierEntity ersenVillagequartierEntity) {
        if ( ersenVillagequartierEntity == null ) {
            return null;
        }

        ProjectionCentraleVillage projectionCentraleVillage = new ProjectionCentraleVillage();

        projectionCentraleVillage.setLibelle( ersenVillagequartierEntity.getLibelle() );

        return projectionCentraleVillage;
    }

    @Override
    public ErsenVillagequartierEntity projectionCentraleTyeCentraleToVillage(ProjectionCentraleVillage projectionCentraleVillage) {
        if ( projectionCentraleVillage == null ) {
            return null;
        }

        ErsenVillagequartierEntity ersenVillagequartierEntity = new ErsenVillagequartierEntity();

        ersenVillagequartierEntity.setLibelle( projectionCentraleVillage.getLibelle() );

        return ersenVillagequartierEntity;
    }

    @Override
    public List<ProjectionCentraleVillage> projetToProjectionCentraleVillageList(List<ErsenVillagequartierEntity> ersenVillagequartierEntities) {
        if ( ersenVillagequartierEntities == null ) {
            return null;
        }

        List<ProjectionCentraleVillage> list = new ArrayList<ProjectionCentraleVillage>( ersenVillagequartierEntities.size() );
        for ( ErsenVillagequartierEntity ersenVillagequartierEntity : ersenVillagequartierEntities ) {
            list.add( villageToProjectionCentraleRegion( ersenVillagequartierEntity ) );
        }

        return list;
    }

    @Override
    public ProjectionCentraleTypeCentrale ersenTypeCentrale(ErsenTypecentraleEntity ersenTypecentraleEntity) {
        if ( ersenTypecentraleEntity == null ) {
            return null;
        }

        ProjectionCentraleTypeCentrale projectionCentraleTypeCentrale = new ProjectionCentraleTypeCentrale();

        projectionCentraleTypeCentrale.setId( ersenTypecentraleEntity.getId() );
        projectionCentraleTypeCentrale.setLibelle( ersenTypecentraleEntity.getLibelle() );

        return projectionCentraleTypeCentrale;
    }

    @Override
    public ErsenTypecentraleEntity projectionToErsenTypeCentrale(ProjectionCentraleTypeCentrale projectionCentraleTypeCentrale) {
        if ( projectionCentraleTypeCentrale == null ) {
            return null;
        }

        ErsenTypecentraleEntity ersenTypecentraleEntity = new ErsenTypecentraleEntity();

        ersenTypecentraleEntity.setId( projectionCentraleTypeCentrale.getId() );
        ersenTypecentraleEntity.setLibelle( projectionCentraleTypeCentrale.getLibelle() );

        return ersenTypecentraleEntity;
    }

    @Override
    public List<ProjectionCentraleTypeCentrale> projectionToErsenTypeCentraleList(List<ErsenTypecentraleEntity> ersenTypecentraleEntities) {
        if ( ersenTypecentraleEntities == null ) {
            return null;
        }

        List<ProjectionCentraleTypeCentrale> list = new ArrayList<ProjectionCentraleTypeCentrale>( ersenTypecentraleEntities.size() );
        for ( ErsenTypecentraleEntity ersenTypecentraleEntity : ersenTypecentraleEntities ) {
            list.add( ersenTypeCentrale( ersenTypecentraleEntity ) );
        }

        return list;
    }

    @Override
    public UtilisateurDto utilisateurEntityToUtilisateurDTO(UtilisateurEntity utilisateurEntity) {
        if ( utilisateurEntity == null ) {
            return null;
        }

        UtilisateurDto utilisateurDto = new UtilisateurDto();

        utilisateurDto.setPp( utilisateurEntity.getPp() );
        utilisateurDto.setUsrPrenom( utilisateurEntity.getUsrPrenom() );
        utilisateurDto.setUsrNom( utilisateurEntity.getUsrNom() );
        utilisateurDto.setTel( utilisateurEntity.getTel() );
        utilisateurDto.setUsrMail( utilisateurEntity.getUsrMail() );
        utilisateurDto.setTypeutilisateur( utilisateurEntity.getTypeutilisateur() );
        utilisateurDto.setEntreprise( utilisateurEntity.getEntreprise() );

        return utilisateurDto;
    }

    @Override
    public UtilisateurEntity utilisateurDTOToUtilisateurEntity(UtilisateurDto utilisateurDto) {
        if ( utilisateurDto == null ) {
            return null;
        }

        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();

        utilisateurEntity.setUsrMail( utilisateurDto.getUsrMail() );
        utilisateurEntity.setUsrNom( utilisateurDto.getUsrNom() );
        utilisateurEntity.setUsrPrenom( utilisateurDto.getUsrPrenom() );
        utilisateurEntity.setTel( utilisateurDto.getTel() );
        utilisateurEntity.setTypeutilisateur( utilisateurDto.getTypeutilisateur() );
        utilisateurEntity.setPp( utilisateurDto.getPp() );
        utilisateurEntity.setEntreprise( utilisateurDto.getEntreprise() );

        return utilisateurEntity;
    }

    @Override
    public List<UtilisateurDto> utilisateurEntityListToUtilisateurDTO(List<UtilisateurEntity> utilisateurEntities) {
        if ( utilisateurEntities == null ) {
            return null;
        }

        List<UtilisateurDto> list = new ArrayList<UtilisateurDto>( utilisateurEntities.size() );
        for ( UtilisateurEntity utilisateurEntity : utilisateurEntities ) {
            list.add( utilisateurEntityToUtilisateurDTO( utilisateurEntity ) );
        }

        return list;
    }

    protected ProjectionCentraleRegion ersenRegionEntityToProjectionCentraleRegion(ErsenRegionEntity ersenRegionEntity) {
        if ( ersenRegionEntity == null ) {
            return null;
        }

        ProjectionCentraleRegion projectionCentraleRegion = new ProjectionCentraleRegion();

        projectionCentraleRegion.setId( ersenRegionEntity.getId() );
        projectionCentraleRegion.setLibelle( ersenRegionEntity.getLibelle() );

        return projectionCentraleRegion;
    }
}
