package sn.ssi.ersen.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenAbonneRepository;
import sn.ssi.ersen.dao.ErsenCentraleRepository;
import sn.ssi.ersen.dao.ErsenInfoCourbeRepository;
import sn.ssi.ersen.dto.*;
import sn.ssi.ersen.dto.utilisateurStat.ConditionUtilisateurDto;
import sn.ssi.ersen.dto.utilisateurStat.UtilisateurDto;
import sn.ssi.ersen.entity.ErsenAbonneEntity;
import sn.ssi.ersen.entity.ErsenInfoCourbe;
import sn.ssi.ersen.entity.entitieMobile.Centrale;
import sn.ssi.ersen.entity.entitieMobile.CentraleAbonneeForMobile;
import sn.ssi.ersen.entity.forWeb.CentraleAbonne;
import sn.ssi.ersen.entity.forWeb.CourbeEntity;
import sn.ssi.ersen.mappers.RequeteMapper;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static sn.ssi.ersen.controllers.ErsenCentraleController.getCentraleAbonnes;

@RestController
@RequestMapping(value = "/abonne",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
@CrossOrigin("*")
public class ErsenAbonneeController {
    private final ErsenAbonneRepository ersenAbonneRepository;
    private final ErsenCentraleRepository ersenCentraleRepository;
    private final RequeteMapper requeteMapper;
    private final ErsenInfoCourbeRepository ersenInfoCourbeRepository;

    public ErsenAbonneeController(ErsenAbonneRepository ersenAbonneRepository, ErsenCentraleRepository ersenCentraleRepository, ErsenInfoCourbeRepository ersenInfoCourbeRepository, RequeteMapper requeteMapper, ErsenInfoCourbeRepository ersenInfoCourbeRepository1) {
        this.ersenAbonneRepository = ersenAbonneRepository;
        this.ersenCentraleRepository = ersenCentraleRepository;
        this.requeteMapper = requeteMapper;
        this.ersenInfoCourbeRepository = ersenInfoCourbeRepository1;
    }

    @GetMapping(value = "/listAbonne/{idCentrales}")
    public List<ErsenAbonneEntity> getAbonneByUser(@PathVariable List<String> idCentrales){
        List<ErsenAbonneEntity> centrales=new ArrayList<>();
        for(String c : idCentrales)
            centrales.addAll( ersenAbonneRepository.getAbonnesByCentrale(c));
        return centrales;
    }
    @GetMapping("/all")
    public List<ErsenAbonneEntity> getAbonne(){
        return ersenAbonneRepository.findAll();
    }

    @GetMapping("/all/dto")
    public List<AbonneeDto> getAbonneDtos(){
        return requeteMapper.abonneEntityToAbonneDTOList(ersenAbonneRepository.findAll());
    }

    @GetMapping("/all/data/dto")
    public List<AbonneeDto> getAbonneDataDto(){
        return requeteMapper.abonneEntityToAbonneDTOList(ersenAbonneRepository.getDataAbonne());
    }

    @GetMapping(value ="/data/dto/{centrale}")
    List<AbonneeDto> getAbonneByCentraleDto(@PathVariable String centrale){
            return requeteMapper.abonneEntityToAbonneDTOList(ersenAbonneRepository.getErsenAbonneEntitiesByCentrale(centrale));
    }

    /*@GetMapping(value ={
            "/dataAbonne/dto",
            "/dataAbonne/dto/{centrale}"
    } )
    List<AbonneeDto> getAbonneSelect(@PathVariable(required = false) String centrale){
        if (centrale==null){
            return requeteMapper.abonneEntityToAbonneDTOList(ersenAbonneRepository.getDataAbonne());
        }
        else {
            return requeteMapper.abonneEntityToAbonneDTOList(ersenAbonneRepository.getErsenAbonneEntitiesByCentrale(centrale));
        }
    }
*/
    /*
    public List<ErsenAbonneEntity> getDataAbonne() {
        return ersenAbonneRepository.getErsenAbonneEntitiesByCentrale(String centrale)
    }*/


    @GetMapping("/{id}")
    public ErsenAbonneEntity getAbonneeById(@PathVariable String id){
        return ersenAbonneRepository.findById(id).orElse(null);
    }

    @GetMapping("/new/{ids}/{centrales}")
    public List<ErsenAbonneEntity> getAbonneNew(@PathVariable List<String> ids,@PathVariable List<String> centrales){
        List<ErsenAbonneEntity> newAbonnes=ersenAbonneRepository.getNewAbonnes(ids,centrales);
        System.out.println("nv abonnes : "+newAbonnes);
        return newAbonnes;
    }
    public List<CentraleAbonne> getCentraleAbonne(){
        return getCentraleAbonnes(ersenCentraleRepository, ersenAbonneRepository,"");
    }

    @PostMapping("/add")
    @Transactional
    public  Integer addAbonne(@RequestBody ErsenAbonneEntity abonne){
        if (ersenAbonneRepository.getNbAbonneByTel(abonne.getId(),abonne.getTelephone())>0){
            return 0;
        }else if(ersenAbonneRepository.getNbAbonneByNumAbonnement(abonne.getId(),abonne.getNumeroabonnement())>0){
            return -1;
        }else if (ersenAbonneRepository.getNbAbonneByCNI(abonne.getId(), abonne.getCni())>0){
            return -2;
        }
        ersenAbonneRepository.saveAndFlush(abonne);
        return 1;
    }



    @PostMapping("/addList")
    @Transactional
    public  void addListAbonne(@RequestBody List<ErsenAbonneEntity> abonnes){
        List<ErsenAbonneEntity> abonneEntityList=new ArrayList<>();
        try {
            for(ErsenAbonneEntity a : abonnes)
            {
                a.setId(a.getId());
                abonneEntityList.add(a);
            }
            ersenAbonneRepository.saveAll(abonneEntityList);
        }
        catch (Exception e)
        {
            System.out.println("exception save all"+e.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public Integer updateAbonne(@PathVariable String id,@RequestBody ErsenAbonneEntity abonne){
        if (ersenAbonneRepository.getNbAbonneByTel(abonne.getId(), abonne.getTelephone())>0){
            return 0;
        }else if(ersenAbonneRepository.getNbAbonneByNumAbonnement(abonne.getId(),abonne.getNumeroabonnement())>0){
            return -1;
        }else if(ersenAbonneRepository.getNbAbonneByCNI(abonne.getId(), abonne.getCni())>0){
            return -2;
        }
        ersenAbonneRepository.saveAndFlush(abonne);
        return 1;
    }

    @PutMapping("/edit")
    @Transactional
    public void updateabonnes(@RequestBody List<ErsenAbonneEntity> list){
        for (ErsenAbonneEntity a: list) {
            ersenAbonneRepository.saveAndFlush(a);
        }
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public List<CentraleAbonne> deleteAbonne(@PathVariable String id){
        ErsenAbonneEntity abonne = ersenAbonneRepository.getById(id);
        traceAbonneDeleted(abonne);
        ersenAbonneRepository.deleteById(id);
        return getCentraleAbonne();
    }

    public void traceAbonneDeleted(ErsenAbonneEntity abonne){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        //System.out.println(sdf.format(new Date()));
        //System.out.println(sdf.format(abonne.getDate()));
        if(!(sdf.format(new Date()).equals(sdf.format(abonne.getDate()))))
            ersenInfoCourbeRepository.save(new ErsenInfoCourbe(null, abonne.getDate(),new Date(), abonne.getCentrale(), 1));
    }

    @DeleteMapping("/deleteList")
    @Transactional
    public void deleteListAbonne(@RequestBody List<String> abonnes){
        ersenAbonneRepository.deleteAllById(abonnes);
    }

    //######################### FOR WEB ################################################
    @GetMapping(value = "/nbre/{annee}/{region}/{operateur}")
    public CourbeEntity getAbonneesByAnnee(@PathVariable String annee, @PathVariable String region, @PathVariable String operateur) throws ParseException {
        List<String> months = Arrays.asList("01","02","03","04","05","06","07","08","09","10","11","12");
        List<Integer> shsmonthval = new ArrayList<>();
        List<Integer> mcmonthval = new ArrayList<>();
        List<Integer> btmonthval = new ArrayList<>();
        Map<String, List<Integer>> shs = new HashMap<>();
        Map<String, List<Integer>> mc = new HashMap<>();
        Map<String, List<Integer>> bt = new HashMap<>();
        Integer preciousSHS = 0;
        Integer preciousMC = 0;
        Integer preciousBT = 0;

        Integer delMC, delBT, delSHS;
        Integer traceMC,traceSHS,traceBT;
        Integer currMC,currSHS,currBT;

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        Integer y = cal.get(Calendar.YEAR);
        Integer m = cal.get(Calendar.MONTH);
        System.out.println("annee et region");
        if (region.equals("neant") && operateur.equals("neant"))
            for (String month : months) {
                if(!(y==Integer.parseInt(annee) && m+2==Integer.parseInt(month))){
                    traceMC =  ersenAbonneRepository.getNbTraceByAnnee(annee, month, "MC");
                    delMC =  ersenAbonneRepository.getNbDelByAnnee(annee, month, "MC");
                    currMC = ersenAbonneRepository.getNombreAbonneByAnnee(annee,  month, "MC");
                    traceSHS = ersenAbonneRepository.getNbTraceByAnnee(annee,  month, "SHS");
                    delSHS = ersenAbonneRepository.getNbDelByAnnee(annee,month,"SHS");
                    currSHS = ersenAbonneRepository.getNombreAbonneByAnnee(annee,  month, "SHS");
                    traceBT = ersenAbonneRepository.getNbTraceByAnnee(annee,  month, "BT");
                    delBT = ersenAbonneRepository.getNbDelByAnnee(annee,  month, "BT");
                    currBT = ersenAbonneRepository.getNombreAbonneByAnnee(annee,  month, "BT");
                    System.out.println("annee seulementt");
                    preciousSHS = preciousSHS + currSHS  + traceSHS;
                    shsmonthval.add(preciousSHS-delSHS);

                    preciousMC = preciousMC + currMC+traceMC;
                    mcmonthval.add(preciousMC-delMC);

                    preciousBT = preciousBT + currBT + traceBT;
                    btmonthval.add(preciousBT-delBT);
                }else {
                    break;
                }

            }
        else if (!region.equals("neant") && operateur.equals("neant"))
            for (String month : months) {
                if(!(y==Integer.parseInt(annee) && m+2==Integer.parseInt(month))) {
                    traceMC = ersenAbonneRepository.getNbTraceByAnneeAndRegion(annee, region, month, "MC");
                    delMC = ersenAbonneRepository.getNbDelByAnneeAndRegion(annee, region, month, "MC");
                    currMC = ersenAbonneRepository.getNombreAbonneByAnneeAndRegion(annee, region, month, "MC");
                    traceSHS = ersenAbonneRepository.getNbTraceByAnneeAndRegion(annee, region, month, "SHS");
                    delSHS = ersenAbonneRepository.getNbDelByAnneeAndRegion(annee, region, month, "SHS");
                    currSHS = ersenAbonneRepository.getNombreAbonneByAnneeAndRegion(annee, region, month, "SHS");
                    traceBT = ersenAbonneRepository.getNbTraceByAnneeAndRegion(annee, region, month, "BT");
                    delBT = ersenAbonneRepository.getNbDelByAnneeAndRegion(annee, region, month, "BT");
                    currBT = ersenAbonneRepository.getNombreAbonneByAnneeAndRegion(annee, region, month, "BT");
                    System.out.println("annee et region");

                    preciousSHS = preciousSHS + currSHS + traceSHS;
                    shsmonthval.add(preciousSHS - delSHS);

                    preciousMC = preciousMC + currMC + traceMC;
                    mcmonthval.add(preciousMC - delMC);

                    preciousBT = preciousBT + currBT + traceBT;
                    btmonthval.add(preciousBT - delBT);
                }else
                    break;

            }
        else if (region.equals("neant") && !operateur.equals("neant"))
            for (String month : months) {
                if(!(y==Integer.parseInt(annee) && m+2==Integer.parseInt(month))) {
                    System.out.println("annee et operateur");
                    traceMC = ersenAbonneRepository.getNbTraceByAnneeAndOperateur(annee, operateur, month, "MC");
                    delMC = ersenAbonneRepository.getNbDelByAnneeAndOperateur(annee, operateur, month, "MC");
                    currMC = ersenAbonneRepository.getNombreAbonneByAnneeAndOperateur(annee, operateur, month, "MC");
                    traceSHS = ersenAbonneRepository.getNbTraceByAnneeAndOperateur(annee, operateur, month, "SHS");
                    delSHS = ersenAbonneRepository.getNbDelByAnneeAndOperateur(annee, operateur, month, "SHS");
                    currSHS = ersenAbonneRepository.getNombreAbonneByAnneeAndOperateur(annee, operateur, month, "SHS");
                    traceBT = ersenAbonneRepository.getNbTraceByAnneeAndOperateur(annee, operateur, month, "BT");
                    delBT = ersenAbonneRepository.getNbDelByAnneeAndOperateur(annee, operateur, month, "BT");
                    currBT = ersenAbonneRepository.getNombreAbonneByAnneeAndOperateur(annee, operateur, month, "BT");

                    preciousSHS = preciousSHS + currSHS + traceSHS;
                    shsmonthval.add(preciousSHS - delSHS);

                    preciousMC = preciousMC + currMC + traceMC;
                    mcmonthval.add(preciousMC - delMC);

                    preciousBT = preciousBT + currBT + traceBT;
                    btmonthval.add(preciousBT - delBT);
                }else
                    break;
            }
        else
            for (String month : months) {
                if(!(y==Integer.parseInt(annee) && m+2==Integer.parseInt(month))) {
                    traceMC = ersenAbonneRepository.getNbTraceByParams(annee, region, operateur, month, "MC");
                    delMC = ersenAbonneRepository.getNbDelByParams(annee, region, operateur, month, "MC");
                    currMC = ersenAbonneRepository.getNombreAbonneByParams(annee, region, operateur, month, "MC");
                    traceSHS = ersenAbonneRepository.getNbTraceByParams(annee, region, operateur, month, "SHS");
                    delSHS = ersenAbonneRepository.getNbDelByParams(annee, region, operateur, month, "SHS");
                    currSHS = ersenAbonneRepository.getNombreAbonneByParams(annee, region, operateur, month, "SHS");
                    traceBT = ersenAbonneRepository.getNbTraceByParams(annee, region, operateur, month, "BT");
                    delBT = ersenAbonneRepository.getNbDelByParams(annee, region, operateur, month, "BT");
                    currBT = ersenAbonneRepository.getNombreAbonneByParams(annee, region, operateur, month, "BT");

                    preciousSHS = preciousSHS + currSHS + traceSHS;
                    shsmonthval.add(preciousSHS - delSHS);

                    preciousMC = preciousMC + currMC + traceMC;
                    mcmonthval.add(preciousMC - delMC);

                    preciousBT = preciousBT + currBT + traceBT;
                    btmonthval.add(preciousBT - delBT);
                }else
                    break;
            }
        shs.put("shs",shsmonthval);
        mc.put("mt",mcmonthval);
        bt.put("bt",btmonthval);
        return new CourbeEntity(shs,mc,bt);
    }

    @GetMapping(value = "/formobile/{idUser}",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    List<CentraleAbonneeForMobile> getAllAbonneesForMobile(@PathVariable Integer idUser){
        //On récupère d'abord les centrales
        List<CentraleAbonneeForMobile> returnedList= new ArrayList<>();
        List<Centrale> listeCentraleForMobile = ersenCentraleRepository.getCentraleForConducteur(idUser);
        List<CentraleAbonneeForMobile> abonneeForMobiles= ersenAbonneRepository.getAllAbonneesForMobile();
        for(Centrale centrale: listeCentraleForMobile){
            for(CentraleAbonneeForMobile centraleAbonneeForMobile: abonneeForMobiles){
                if(centrale.getId().equals(centraleAbonneeForMobile.getCentrale())){
                    returnedList.add(centraleAbonneeForMobile);
                }
            }
        }
        return returnedList;
    }

    @GetMapping(value = "centrale/{idCentrale}")
    Integer getNombreAbonneeByIdCentrale(@PathVariable("idCentrale") String idCentrale){
        return ersenAbonneRepository.getNombreAbonneeByCentrale(idCentrale);
    }

    //une restriction suivant le nom
   /*@GetMapping(value = "/req/{nom}")
    List<AbonneeDto> getAbonneByNomReque(@PathVariable String nom){
        return requeteMapper.abonneEntityToAbonneDTOList(ersenAbonneRepository.getErsenAbonneByNom(nom));
    }*/

    //une restriction suivant une centrale
    @GetMapping(value = "/req1/{centrale}")
    List<AbonneeDto> getAbonneCentraleReque(@PathVariable String centrale){
        return requeteMapper.abonneEntityToAbonneDTOList(ersenAbonneRepository.getErsenAbonneEntitiesByCentrale(centrale));
    }


    //une restriction suivant l'adresse
    @GetMapping(value ={
            "/reqadresse",
            "/reqadresse/{adresse}"
    } )
    List<AbonneeDto> getAbonneAdresseReque(@PathVariable(required = false) String adresse){
        if (adresse==null){
            return requeteMapper.abonneEntityToAbonneDTOList(ersenAbonneRepository.getDataAbonne());
        }
        else {
            return requeteMapper.abonneEntityToAbonneDTOList(ersenAbonneRepository.getErsenAbonneByAdresse(adresse));
        }
    }

    @GetMapping(value = "/attribue/{id}")
    Field[] getAttribue(@PathVariable Integer id){
        Field[] fields=new Field[0];
        if (id==1)
            fields= AbonneeDto.class.getDeclaredFields();
        if (id==2)
            fields= CentraleDto.class.getDeclaredFields();
        if (id==3)
            fields= UtilisateurDto.class.getDeclaredFields();
        return fields;
    }

    //les conditions
    @GetMapping(value = "/attribue/condition/{id}")
    Field[] getAttribueCondition(@PathVariable Integer id){
        Field[] fields=new Field[0];
        if (id==1)
            fields= ConditionAbonneDto.class.getDeclaredFields();
        if (id==2)
            fields= ConditionCentraleDto.class.getDeclaredFields();
        if (id==3)
            fields= ConditionUtilisateurDto.class.getDeclaredFields();
        return fields;
    }

//reload table
    @GetMapping(value = {"/attribue/tables"})
    List<AbonneeDto> getAttribueTables(){
        List<AbonneeDto> fields= new ArrayList<>();
        fields= getAbonneDataDto();
        return fields;
    }
}
