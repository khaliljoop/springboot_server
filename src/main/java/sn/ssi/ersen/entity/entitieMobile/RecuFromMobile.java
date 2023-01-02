package sn.ssi.ersen.entity.entitieMobile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sn.ssi.ersen.entity.ErsenDetailRecusEntity;
import sn.ssi.ersen.entity.ErsenRecusEntity;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecuFromMobile {
 ErsenRecusEntity recu;
 List<ErsenDetailRecusEntity> detailrecus;


}
