package sn.ssi.ersen.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import sn.ssi.ersen.dto.*;
import sn.ssi.ersen.dto.projectionabonnedto.ProjectionCategorieNom;
import sn.ssi.ersen.dto.projectionabonnedto.ProjectionTypeCategorieNom;
import sn.ssi.ersen.dto.projectioncentraledto.*;
import sn.ssi.ersen.dto.utilisateurStat.UtilisateurDto;
import sn.ssi.ersen.entity.*;

import java.util.List;

@Mapper
@DecoratedWith(RequeteDecorator.class)
public interface RequeteMapper {

    // AbonnEntity to AbonneDTO
    //ProjectionEntity to ProjectionDTO
    AbonneeDto abonneEntityToAbonneDto(ErsenAbonneEntity ersenAbonneEntity);
    ErsenAbonneEntity abonneDtoToAbonneEntity(AbonneeDto abonneeDto);
    List<AbonneeDto> abonneEntityToAbonneDTOList(List<ErsenAbonneEntity> ersenAbonneEntities );

    ProjectionAdresse abonneEntityToProjection(ErsenAbonneEntity ersenAbonneEntity);
    ErsenAbonneEntity ProjectionToAbonneEntity(ProjectionAdresse projectionAdresse);
    List<ProjectionAdresse> abonneEntityToProjection(List<ErsenAbonneEntity> ersenAbonneEntities );

    ProjectionCentraleAbonne abonneEntityToProjectionCentraleAbonne(ErsenCentraleEntity centraleEntity);
    ErsenCentraleEntity ProjectionCentraleAbonneToAbonneEntity(ProjectionCentraleAbonne projectionCentraleAbonne);
    List<ProjectionCentraleAbonne> abonneEntityToProjectioCentraleAbonne(List<ErsenCentraleEntity> ersenCentraleEntities );

    ProjectionNumeroAbonnementDto abonneEntityToProjectionNumeroAbonnement(ErsenAbonneEntity ersenAbonneEntity);
    ErsenAbonneEntity ProjectionNumeroAbonnementToAbonneEntity(ProjectionNumeroAbonnementDto projectionNumeroAbonnementDto);
    List<ProjectionNumeroAbonnementDto> abonneEntityToProjectioNumeroAbonnement(List<ErsenAbonneEntity> ersenAbonneEntities);

    ProjectionCategorieNom categorieEntityToProjectionCategorieDtoNom(ErsenCategorieEntity ersenCategorieEntity);
    ErsenCategorieEntity projectionCategorieNomToCategorieEntity(ProjectionCategorieNom projectionCategorieNom);
    List<ProjectionCategorieNom> categorieEntityToProjectioCategorieDtoNomList(List<ErsenCategorieEntity> ersenCategorieEntities);

    ProjectionTypeCategorieNom typeCategorieEntityToProjectionTypeCategorieDtoNom(ErsenTypecategorieEntity ersenTypecategorieEntity);
    ErsenCategorieEntity projectionTypeCategorieNomToTypeCategorieEntity(ProjectionTypeCategorieNom projectionTypeCategorieNom);
    List<ProjectionTypeCategorieNom> typeCategorieEntityToProjectioCategorieDtoNomList(List<ErsenTypecategorieEntity> ersenTypecategorieEntities);

    // CentraleEntity to CentraleDTO
    //ProjectionEntity to ProjectionDTO

    CentraleDto centraleEntityToCentraleDto(ErsenCentraleEntity centraleEntity);
    ErsenCentraleEntity centraleDtoToCentraleEntity(CentraleDto centraleDto);
    List<CentraleDto>  centraleEntityToCentraleDtoList(List<ErsenCentraleEntity> ersenCentraleEntities );

    ProjectionCentrale centaleEntityProjectionCentrale(ErsenCentraleEntity centraleEntity);
    ErsenCentraleEntity projectionCentraleToCentrale(ProjectionCentrale projectionCentrale);
    List<ProjectionCentrale>  centraleEntityToProjectionCentraleList(List<ErsenCentraleEntity> ersenCentraleEntities );

    ProjectionCentraleOperateur centraleEntityToProjectionOperateur(ErsenCentraleEntity centraleEntity);
    ErsenCentraleEntity projectionOperateurToCentraleEntity(ProjectionCentraleOperateur projectionCentraleOperateur);
    List<ProjectionCentraleOperateur>  centraleEntityProjectionOperateurList(List<ErsenCentraleEntity> ersenCentraleEntities );

    ProjectionCentraleProjet projetEntityToProjectionProjet(ErsenProjetEntity centraleEntity);
    ErsenProjetEntity projectionOperateurToProjet(ProjectionCentraleProjet projectionCentraleProjet);
    List<ProjectionCentraleProjet> projetProjectionProjetListe(List<ErsenProjetEntity> ersenProjetEntities );

    ProjectionCentraleRegion regionEntityToProjectionCentraleRegion(ErsenProjetEntity centraleEntity);
    ErsenRegionEntity projectionCentraleRegionToRegionEntity(ProjectionCentraleRegion projectionCentraleRegion);
    List<ProjectionCentraleRegion>  projetToProjectionCentraleRegionList(List<ErsenRegionEntity> ersenRegionEntities);

    ProjectionCentraleDepartement departementEntityToProjectionCentraleDepartementDto(ErsenDepartementEntity ersenDepartementEntity);
    ErsenDepartementEntity projectionCentraleDepartementToDepartementEntity(ProjectionCentraleDepartement projectionCentraleDepartement);
    List<ProjectionCentraleDepartement> departementEntityListToProjectionCentraleDepartementList(List<ErsenDepartementEntity> ersenDepartementEntities);

    ProjectionCentraleCommune communeEntityToProjectionCentraleCommune(ErsenCommuneEntity ersenCommuneEntity);
    ErsenCommuneEntity projectionCentraleCommuneToCommuneEntity(ProjectionCentraleCommune projectionCentraleCommune);
    List<ProjectionCentraleCommune> communeEntityListToProjectionCentraleCommunetList(List<ErsenCommuneEntity> ersenCommuneEntities);

    ProjectionCentraleVillage villageToProjectionCentraleRegion(ErsenVillagequartierEntity ersenVillagequartierEntity);
    ErsenVillagequartierEntity projectionCentraleTyeCentraleToVillage(ProjectionCentraleVillage projectionCentraleVillage);
    List<ProjectionCentraleVillage> projetToProjectionCentraleVillageList(List<ErsenVillagequartierEntity> ersenVillagequartierEntities);

    ProjectionCentraleTypeCentrale ersenTypeCentrale(ErsenTypecentraleEntity ersenTypecentraleEntity);
    ErsenTypecentraleEntity projectionToErsenTypeCentrale(ProjectionCentraleTypeCentrale projectionCentraleTypeCentrale);
    List<ProjectionCentraleTypeCentrale> projectionToErsenTypeCentraleList(List<ErsenTypecentraleEntity> ersenTypecentraleEntities);

   /* //concerne la liste des projet dans centrale
    ProjectionCentraleProjet ersenProjetEntityToProjectionCentraleProjet(ErsenProjetEntity ersenProjetEntity);
    ErsenProjetEntity projectionCentraleProjetToProjetEntity(ProjectionCentraleProjet projectionCentraleProjet);
    List<ProjectionCentraleTypeCentrale> ersenProjectEntityToProjectionCentraleProjetList(List<ErsenProjetEntity> ersenProjetEntities);
*/

    // AbonnEntity to AbonneDTO
    //ProjectionEntity to ProjectionDTO

    UtilisateurDto utilisateurEntityToUtilisateurDTO(UtilisateurEntity utilisateurEntity);
    UtilisateurEntity utilisateurDTOToUtilisateurEntity(UtilisateurDto utilisateurDto);
    List<UtilisateurDto>  utilisateurEntityListToUtilisateurDTO(List<UtilisateurEntity> utilisateurEntities );
}
