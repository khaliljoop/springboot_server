package sn.ssi.ersen.entity.entitieMobile;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Preuve {
   private String id;
   private String idtache;

   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
   private Date datesave;
   private Date dateupdate;
   private Integer isSync;
   private List<Fichier> fichiers;
}
