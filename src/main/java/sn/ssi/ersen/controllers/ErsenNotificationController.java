package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenNotificationRepository;
import sn.ssi.ersen.dao.ErsenTraceTacheRepository;
import sn.ssi.ersen.entity.ErsenNotificationEntity;
import sn.ssi.ersen.entity.ErsenTraceTache;
import sn.ssi.ersen.entity.entitieMobile.TraceTache;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/notification")
public class ErsenNotificationController {
    private final ErsenNotificationRepository ersenNotificationRepository;
    private final ErsenTraceTacheRepository ersenTraceTacheRepository;

    public ErsenNotificationController(ErsenNotificationRepository ersenNotificationRepository, ErsenTraceTacheRepository ersenTraceTacheRepository) {
        this.ersenNotificationRepository = ersenNotificationRepository;
        this.ersenTraceTacheRepository = ersenTraceTacheRepository;
    }

    @GetMapping(value = {"/all/{operateur}", "/all"})
    public List<ErsenNotificationEntity> getNotification(@PathVariable(required = false) Integer operateur){
        if(operateur!=null)
            return ersenNotificationRepository.findNotifByOperateur(operateur);
        else
            return ersenNotificationRepository.findAllNotif();
    }

    @GetMapping(value = {"/nbNotif","/nbNotif/{entreprise}"})
    public Integer getNotificationsNonResolues(@PathVariable(required = false) String entreprise){
        if(entreprise==null)
            return ersenNotificationRepository.getNbNotifNonResolues();
        else
            return ersenNotificationRepository.getNbNotifNonResoluesByEntr(entreprise);
    }

    @GetMapping(value = "/new/{user}")
    public Boolean getNew(@PathVariable Integer user){
        return ersenNotificationRepository.getNew(user);
    }

    @GetMapping("/byid/{id}")
    public ErsenNotificationEntity getNotificationById(@PathVariable String id){
        return ersenNotificationRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  ErsenNotificationEntity addNotification(@RequestBody ErsenNotificationEntity ersenNotificationEntity){
        return ersenNotificationRepository.saveAndFlush(ersenNotificationEntity);
    }

    @GetMapping("/newNotif/false/{user}")
    public Boolean makeNewNotifFalse(@PathVariable Integer user){
        ersenNotificationRepository.makeNewNotifFalse(user);
        return true;
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public ErsenNotificationEntity updateNotification( @PathVariable String id,@RequestBody ErsenNotificationEntity ersenNotificationEntity){
        ersenNotificationEntity.setId(id);
        return ersenNotificationRepository.saveAndFlush(ersenNotificationEntity);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public String deleteNotification(@PathVariable String id){
        ersenNotificationRepository.deleteById(id);
        return "ok";
    }



    /**
     * Cette methode nous permet de ressoudre les notifications qui ne sont pas liees a des tache.
     * - type=s (section) prour Section de maintenance,
     * - type=e (empty) pour notif ni lier a une tache ni a une section de maintenance
     * NB: utile pour le mobile car sans type il nous est impossible d'affichier les notifs selon qu'elles soient liees a une
     * tache, une sectionM ou pas*/
    @GetMapping(value = "/update/{idNotif}/{objet}")
    public void updateNotification(@PathVariable String idNotif,@PathVariable String objet){
        ersenNotificationRepository.updateNotification(idNotif,objet, new Date());
        ersenTraceTacheRepository.save(new ErsenTraceTache(idNotif,objet, new Date()));
    }

    @GetMapping(value = "/updatecommentaire/{idNotif}/{objet}")
    public void updateCommentaire(@PathVariable String idNotif,@PathVariable String objet){
        ersenNotificationRepository.updateCommentaire(idNotif,objet);
    }

    @PostMapping(value = "/closedNotTaskNotifs")
    public List<TraceTache> getNotTaskClosedNotifs(@RequestBody List<String> idTs){
        List<TraceTache> notTaskNotifsTraced = ersenTraceTacheRepository.getNotTaskClosedNotifsIds(idTs);
        ersenTraceTacheRepository.deleteAllById(ersenTraceTacheRepository.getReplicTracesIdsByNotifIDS(idTs));
        return notTaskNotifsTraced;
    }
}
