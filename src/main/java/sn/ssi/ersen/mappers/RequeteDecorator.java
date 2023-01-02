package sn.ssi.ersen.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.Mapping;
import sn.ssi.ersen.dto.*;
import sn.ssi.ersen.dto.projectionabonnedto.ProjectionCategorieNom;
import sn.ssi.ersen.dto.projectionabonnedto.ProjectionTypeCategorieNom;
import sn.ssi.ersen.dto.projectioncentraledto.*;
import sn.ssi.ersen.dto.projectionrecusdto.ProjectionRecusAbonne;
import sn.ssi.ersen.dto.projectionrecusdto.ProjectionRecusCentrale;
import sn.ssi.ersen.dto.projectionrecusdto.ProjectionRecusNumeroPaiement;
import sn.ssi.ersen.dto.utilisateurStat.UtilisateurDto;
import sn.ssi.ersen.entity.*;

import java.util.List;

public class RequeteDecorator implements RequeteMapper{
    private RequeteMapper requeteMapper;

    @Autowired
    public void setRequeteMapper(RequeteMapper requeteMapper) {
        this.requeteMapper = requeteMapper;
    }

    @Override
    public AbonneeDto abonneEntityToAbonneDto(ErsenAbonneEntity ersenAbonneEntity) {
        return requeteMapper.abonneEntityToAbonneDto(ersenAbonneEntity);
    }

    @Override
    public ErsenAbonneEntity abonneDtoToAbonneEntity(AbonneeDto abonneeDto) {
        return requeteMapper.abonneDtoToAbonneEntity(abonneeDto);
    }

    @Override
    public List<AbonneeDto> abonneEntityToAbonneDTOList(List<ErsenAbonneEntity> ersenAbonneEntities) {
        return requeteMapper.abonneEntityToAbonneDTOList(ersenAbonneEntities);
    }

    @Override
    public ProjectionAdresse abonneEntityToProjection(ErsenAbonneEntity ersenAbonneEntity) {
        return requeteMapper.abonneEntityToProjection(ersenAbonneEntity);
    }

    @Override
    public ErsenAbonneEntity ProjectionToAbonneEntity(ProjectionAdresse projectionAdresse) {
        return requeteMapper.ProjectionToAbonneEntity(projectionAdresse);
    }

    @Override
    public List<ProjectionAdresse> abonneEntityToProjection(List<ErsenAbonneEntity> ersenAbonneEntities) {
        return requeteMapper.abonneEntityToProjection(ersenAbonneEntities);
    }

    @Override
    public ProjectionCentraleAbonne abonneEntityToProjectionCentraleAbonne(ErsenCentraleEntity centraleEntity) {
        return requeteMapper.abonneEntityToProjectionCentraleAbonne(centraleEntity);
    }

    @Override
    public ErsenCentraleEntity ProjectionCentraleAbonneToAbonneEntity(ProjectionCentraleAbonne projectionCentraleAbonne) {
        return requeteMapper.ProjectionCentraleAbonneToAbonneEntity(projectionCentraleAbonne);
    }

    @Override
    public List<ProjectionCentraleAbonne> abonneEntityToProjectioCentraleAbonne(List<ErsenCentraleEntity> ersenAbonneEntities) {
        return requeteMapper.abonneEntityToProjectioCentraleAbonne(ersenAbonneEntities);
    }

    @Override
    public ProjectionNumeroAbonnementDto abonneEntityToProjectionNumeroAbonnement(ErsenAbonneEntity ersenAbonneEntity) {
        return requeteMapper.abonneEntityToProjectionNumeroAbonnement(ersenAbonneEntity);
    }

    @Override
    public ErsenAbonneEntity ProjectionNumeroAbonnementToAbonneEntity(ProjectionNumeroAbonnementDto projectionNumeroAbonnementDto) {
        return requeteMapper.ProjectionNumeroAbonnementToAbonneEntity(projectionNumeroAbonnementDto);
    }

    @Override
    public List<ProjectionNumeroAbonnementDto> abonneEntityToProjectioNumeroAbonnement(List<ErsenAbonneEntity> ersenAbonneEntities) {
        return requeteMapper.abonneEntityToProjectioNumeroAbonnement(ersenAbonneEntities);
    }

    @Override
    public ProjectionCategorieNom categorieEntityToProjectionCategorieDtoNom(ErsenCategorieEntity ersenCategorieEntity) {
        return requeteMapper.categorieEntityToProjectionCategorieDtoNom(ersenCategorieEntity);
    }

    @Override
    public ErsenCategorieEntity projectionCategorieNomToCategorieEntity(ProjectionCategorieNom projectionCategorieNom) {
        return requeteMapper.projectionCategorieNomToCategorieEntity(projectionCategorieNom);
    }

    @Override
    public List<ProjectionCategorieNom> categorieEntityToProjectioCategorieDtoNomList(List<ErsenCategorieEntity> ersenCategorieEntities) {
        return requeteMapper.categorieEntityToProjectioCategorieDtoNomList(ersenCategorieEntities);
    }

    @Override
    public ProjectionTypeCategorieNom typeCategorieEntityToProjectionTypeCategorieDtoNom(ErsenTypecategorieEntity ersenTypecategorieEntity) {
        return requeteMapper.typeCategorieEntityToProjectionTypeCategorieDtoNom(ersenTypecategorieEntity);
    }

    @Override
    public ErsenCategorieEntity projectionTypeCategorieNomToTypeCategorieEntity(ProjectionTypeCategorieNom projectionTypeCategorieNom) {
        return requeteMapper.projectionTypeCategorieNomToTypeCategorieEntity(projectionTypeCategorieNom);
    }

    @Override
    public List<ProjectionTypeCategorieNom> typeCategorieEntityToProjectioCategorieDtoNomList(List<ErsenTypecategorieEntity> ersenTypecategorieEntities) {
        return requeteMapper.typeCategorieEntityToProjectioCategorieDtoNomList(ersenTypecategorieEntities);
    }

    @Override
    public CentraleDto centraleEntityToCentraleDto(ErsenCentraleEntity centraleEntity) {
        return requeteMapper.centraleEntityToCentraleDto(centraleEntity);
    }

    @Override
    public ErsenCentraleEntity centraleDtoToCentraleEntity(CentraleDto centraleDto) {
        return requeteMapper.centraleDtoToCentraleEntity(centraleDto);
    }

    @Override
    public List<CentraleDto> centraleEntityToCentraleDtoList(List<ErsenCentraleEntity> ersenCentraleEntities) {
        return requeteMapper.centraleEntityToCentraleDtoList(ersenCentraleEntities);
    }

    @Override
    public ProjectionCentrale centaleEntityProjectionCentrale(ErsenCentraleEntity centraleEntity) {
        return requeteMapper.centaleEntityProjectionCentrale(centraleEntity);
    }

    @Override
    public ErsenCentraleEntity projectionCentraleToCentrale(ProjectionCentrale projectionCentrale) {
        return requeteMapper.projectionCentraleToCentrale(projectionCentrale);
    }

    @Override
    public List<ProjectionCentrale> centraleEntityToProjectionCentraleList(List<ErsenCentraleEntity> ersenCentraleEntities) {
        return requeteMapper.centraleEntityToProjectionCentraleList(ersenCentraleEntities);
    }

    @Override
    public ProjectionCentraleOperateur centraleEntityToProjectionOperateur(ErsenCentraleEntity centraleEntity) {
        return requeteMapper.centraleEntityToProjectionOperateur(centraleEntity);
    }

    @Override
    public ErsenCentraleEntity projectionOperateurToCentraleEntity(ProjectionCentraleOperateur projectionCentraleOperateur) {
        return requeteMapper.projectionOperateurToCentraleEntity(projectionCentraleOperateur);
    }

    @Override
    public List<ProjectionCentraleOperateur> centraleEntityProjectionOperateurList(List<ErsenCentraleEntity> ersenCentraleEntities) {
        return requeteMapper.centraleEntityProjectionOperateurList(ersenCentraleEntities);
    }

    @Override
    public ProjectionCentraleProjet projetEntityToProjectionProjet(ErsenProjetEntity centraleEntity) {
        return projetEntityToProjectionProjet(centraleEntity);
    }

    @Override
    public ErsenProjetEntity projectionOperateurToProjet(ProjectionCentraleProjet projectionCentraleProjet) {
        return projectionOperateurToProjet(projectionCentraleProjet);
    }

    @Override
    public List<ProjectionCentraleProjet> projetProjectionProjetListe(List<ErsenProjetEntity> ersenProjetEntities) {
        return requeteMapper.projetProjectionProjetListe(ersenProjetEntities);
    }


    @Override
    public ProjectionCentraleRegion regionEntityToProjectionCentraleRegion(ErsenProjetEntity centraleEntity) {
        return regionEntityToProjectionCentraleRegion(centraleEntity);
    }

    @Override
    public ErsenRegionEntity projectionCentraleRegionToRegionEntity(ProjectionCentraleRegion projectionCentraleRegion) {
        return projectionCentraleRegionToRegionEntity(projectionCentraleRegion);
    }

    @Override
    public List<ProjectionCentraleRegion> projetToProjectionCentraleRegionList(List<ErsenRegionEntity> ersenRegionEntities) {
        return requeteMapper.projetToProjectionCentraleRegionList(ersenRegionEntities);
    }

    @Override
    public ProjectionCentraleDepartement departementEntityToProjectionCentraleDepartementDto(ErsenDepartementEntity ersenDepartementEntity) {
        return requeteMapper.departementEntityToProjectionCentraleDepartementDto(ersenDepartementEntity);
    }

    @Override
    public ErsenDepartementEntity projectionCentraleDepartementToDepartementEntity(ProjectionCentraleDepartement projectionCentraleDepartement) {
        return requeteMapper.projectionCentraleDepartementToDepartementEntity(projectionCentraleDepartement);
    }

    @Override
    public List<ProjectionCentraleDepartement> departementEntityListToProjectionCentraleDepartementList(List<ErsenDepartementEntity> ersenDepartementEntities) {
        return requeteMapper.departementEntityListToProjectionCentraleDepartementList(ersenDepartementEntities);
    }

    @Override
    public ProjectionCentraleCommune communeEntityToProjectionCentraleCommune(ErsenCommuneEntity ersenCommuneEntity) {
        return requeteMapper.communeEntityToProjectionCentraleCommune(ersenCommuneEntity);
    }

    @Override
    public ErsenCommuneEntity projectionCentraleCommuneToCommuneEntity(ProjectionCentraleCommune projectionCentraleCommune) {
        return requeteMapper.projectionCentraleCommuneToCommuneEntity(projectionCentraleCommune);
    }

    @Override
    public List<ProjectionCentraleCommune> communeEntityListToProjectionCentraleCommunetList(List<ErsenCommuneEntity> ersenCommuneEntities) {
        return requeteMapper.communeEntityListToProjectionCentraleCommunetList(ersenCommuneEntities);
    }

    @Override
    public ProjectionCentraleVillage villageToProjectionCentraleRegion(ErsenVillagequartierEntity ersenVillagequartierEntity) {
        return requeteMapper.villageToProjectionCentraleRegion(ersenVillagequartierEntity);
    }

    @Override
    public ErsenVillagequartierEntity projectionCentraleTyeCentraleToVillage(ProjectionCentraleVillage projectionCentraleVillage) {
        return requeteMapper.projectionCentraleTyeCentraleToVillage(projectionCentraleVillage);
    }

    @Override
    public List<ProjectionCentraleVillage> projetToProjectionCentraleVillageList(List<ErsenVillagequartierEntity> ersenVillagequartierEntities) {
        return requeteMapper.projetToProjectionCentraleVillageList(ersenVillagequartierEntities);
    }

    @Override
    public ProjectionCentraleTypeCentrale ersenTypeCentrale(ErsenTypecentraleEntity ersenTypecentraleEntity) {
        return requeteMapper.ersenTypeCentrale(ersenTypecentraleEntity);
    }
    @Override
    public ErsenTypecentraleEntity projectionToErsenTypeCentrale(ProjectionCentraleTypeCentrale projectionCentraleTypeCentrale) {
        return requeteMapper.projectionToErsenTypeCentrale(projectionCentraleTypeCentrale);
    }
    @Override
    public List<ProjectionCentraleTypeCentrale> projectionToErsenTypeCentraleList(List<ErsenTypecentraleEntity> ersenTypecentraleEntities) {
        return requeteMapper.projectionToErsenTypeCentraleList(ersenTypecentraleEntities);
    }
    @Override
    public UtilisateurDto utilisateurEntityToUtilisateurDTO(UtilisateurEntity utilisateurEntity) {
        return requeteMapper.utilisateurEntityToUtilisateurDTO(utilisateurEntity);
    }
    @Override
    public UtilisateurEntity utilisateurDTOToUtilisateurEntity(UtilisateurDto utilisateurDto) {
        return requeteMapper.utilisateurDTOToUtilisateurEntity(utilisateurDto);
    }

    @Override
    public List<UtilisateurDto> utilisateurEntityListToUtilisateurDTO(List<UtilisateurEntity> utilisateurEntities) {
        return requeteMapper.utilisateurEntityListToUtilisateurDTO(utilisateurEntities);
    }

   /* @Override
    public ProjectionCentraleProjet ersenProjetEntityToProjectionCentraleProjet(ErsenProjetEntity ersenProjetEntity) {
        return requeteMapper.ersenProjetEntityToProjectionCentraleProjet(ersenProjetEntity);
    }

    @Override
    public ErsenProjetEntity projectionCentraleProjetToProjetEntity(ProjectionCentraleProjet projectionCentraleProjet) {
        return requeteMapper.projectionCentraleProjetToProjetEntity(projectionCentraleProjet);
    }

    @Override
    public List<ProjectionCentraleTypeCentrale> ersenProjectEntityToProjectionCentraleProjetList(List<ErsenProjetEntity> ersenProjetEntities) {
        return requeteMapper.ersenProjectEntityToProjectionCentraleProjetList(ersenProjetEntities);
    }*/

}
