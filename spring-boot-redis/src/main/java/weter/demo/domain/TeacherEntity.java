package weter.demo.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 2299
 * @version 1.0 2017/4/12
 */
@Entity
@Table(name = "teacher")
public class TeacherEntity implements Serializable {

  private Long id;
  private String tNo;
  private String tName;
  private String tSex;
  private Date tBirthday;
  private String prof;
  private String depart;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    TeacherEntity that = (TeacherEntity) o;

    if (id != null ? !id.equals(that.id) : that.id != null) {
      return false;
    }
    if (tNo != null ? !tNo.equals(that.tNo) : that.tNo != null) {
      return false;
    }
    if (tName != null ? !tName.equals(that.tName) : that.tName != null) {
      return false;
    }
    if (tSex != null ? !tSex.equals(that.tSex) : that.tSex != null) {
      return false;
    }
    if (tBirthday != null ? !tBirthday.equals(that.tBirthday) : that.tBirthday != null) {
      return false;
    }
    if (prof != null ? !prof.equals(that.prof) : that.prof != null) {
      return false;
    }
    return depart != null ? depart.equals(that.depart) : that.depart == null;
  }

  @Basic
  @Column(name = "T_NO")
  public String gettNo() {
    return tNo;
  }

  public void settNo(String tNo) {
    this.tNo = tNo;
  }

  @Basic
  @Column(name = "T_NAME")
  public String gettName() {
    return tName;
  }

  public void settName(String tName) {
    this.tName = tName;
  }

  @Basic
  @Column(name = "T_SEX")
  public String gettSex() {
    return tSex;
  }

  public void settSex(String tSex) {
    this.tSex = tSex;
  }

  @Basic
  @Column(name = "T_BIRTHDAY")
  public Date gettBirthday() {
    return tBirthday;
  }

  public void settBirthday(Date tBirthday) {
    this.tBirthday = tBirthday;
  }

  @Basic
  @Column(name = "PROF")
  public String getProf() {
    return prof;
  }

  public void setProf(String prof) {
    this.prof = prof;
  }

  @Basic
  @Column(name = "DEPART")
  public String getDepart() {
    return depart;
  }

  public void setDepart(String depart) {
    this.depart = depart;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "TeacherEntity{" +
        "id=" + id +
        ", tNo='" + tNo + '\'' +
        ", tName='" + tName + '\'' +
        ", tSex='" + tSex + '\'' +
        ", tBirthday=" + tBirthday +
        ", prof='" + prof + '\'' +
        ", depart='" + depart + '\'' +
        '}';
  }
}
