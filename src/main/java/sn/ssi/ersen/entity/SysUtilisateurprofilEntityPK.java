package sn.ssi.ersen.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SysUtilisateurprofilEntityPK implements Serializable {
    private String pfCode;
    private long usrId;

    @Column(name = "PF_CODE")
    @Id
    public String getPfCode() {
        return pfCode;
    }

    public void setPfCode(String pfCode) {
        this.pfCode = pfCode;
    }

    @Column(name = "USR_ID")
    @Id
    public long getUsrId() {
        return usrId;
    }

    public void setUsrId(long usrId) {
        this.usrId = usrId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUtilisateurprofilEntityPK that = (SysUtilisateurprofilEntityPK) o;
        return usrId == that.usrId && Objects.equals(pfCode, that.pfCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pfCode, usrId);
    }
}
