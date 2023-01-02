package sn.ssi.ersen.entity.entitieMobile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class NewSectAndDeletedOnes {
    List<SectionMaintenance> sectionNewSectionM;
    List<String> deletedSectionM;
}
