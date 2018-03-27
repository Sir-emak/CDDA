package pl.seremak.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Objects;

public class CDDA implements Serializable {

    private Integer rowIndex;
    private Integer cddaId;
    private String nationalId;
    private String pslocalId;
    private String psnamespace;
    private String psversionId;
    private String designatedAreaType;
    private String cddaCountryCode;
    private String cddaRegionCode;
    private String designationTypeCode;
    private String iucnCategory;
    private Double siteArea;
    private String majorEcosystemType;
    private Integer marineAreaPercentage;
    private String spatialDataDissemination;
    private String spatialResolutionCode;
    private Date eionetChangeDate;
    private String eionetChangeType;
    private String eionetEditedBy;
    private String eionetInstitute;
    private String remark;
    private Boolean siteEnded;
    private Integer containedBy;

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Integer getCddaId() {
        return cddaId;
    }

    public void setCddaId(Integer cddaId) {
        this.cddaId = cddaId;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPslocalId() {
        return pslocalId;
    }

    public void setPslocalId(String pslocalId) {
        this.pslocalId = pslocalId;
    }

    public String getPsnamespace() {
        return psnamespace;
    }

    public void setPsnamespace(String psnamespace) {
        this.psnamespace = psnamespace;
    }

    public String getPsversionId() {
        return psversionId;
    }

    public void setPsversionId(String psversionId) {
        this.psversionId = psversionId;
    }

    public String getDesignatedAreaType() {
        return designatedAreaType;
    }

    public void setDesignatedAreaType(String designatedAreaType) {
        this.designatedAreaType = designatedAreaType;
    }

    public String getCddaCountryCode() {
        return cddaCountryCode;
    }

    public void setCddaCountryCode(String cddaCountryCode) {
        this.cddaCountryCode = cddaCountryCode;
    }

    public String getCddaRegionCode() {
        return cddaRegionCode;
    }

    public void setCddaRegionCode(String cddaRegionCode) {
        this.cddaRegionCode = cddaRegionCode;
    }

    public String getDesignationTypeCode() {
        return designationTypeCode;
    }

    public void setDesignationTypeCode(String designationTypeCode) {
        this.designationTypeCode = designationTypeCode;
    }

    public String getIucnCategory() {
        return iucnCategory;
    }

    public void setIucnCategory(String iucnCategory) {
        this.iucnCategory = iucnCategory;
    }

    public Double getSiteArea() {
        return siteArea;
    }

    public void setSiteArea(Double siteArea) {
        this.siteArea = siteArea;
    }

    public String getMajorEcosystemType() {
        return majorEcosystemType;
    }

    public void setMajorEcosystemType(String majorEcosystemType) {
        this.majorEcosystemType = majorEcosystemType;
    }

    public Integer getMarineAreaPercentage() {
        return marineAreaPercentage;
    }

    public void setMarineAreaPercentage(Integer marineAreaPercentage) {
        this.marineAreaPercentage = marineAreaPercentage;
    }

    public String getSpatialDataDissemination() {
        return spatialDataDissemination;
    }

    public void setSpatialDataDissemination(String spatialDataDissemination) {
        this.spatialDataDissemination = spatialDataDissemination;
    }

    public String getSpatialResolutionCode() {
        return spatialResolutionCode;
    }

    public void setSpatialResolutionCode(String spatialResolutionCode) {
        this.spatialResolutionCode = spatialResolutionCode;
    }

    public Date getEionetChangeDate() {
        return eionetChangeDate;
    }

    public void setEionetChangeDate(Date eionetChangeDate) {
        this.eionetChangeDate = eionetChangeDate;
    }

    public String getEionetChangeType() {
        return eionetChangeType;
    }

    public void setEionetChangeType(String eionetChangeType) {
        this.eionetChangeType = eionetChangeType;
    }

    public String getEionetEditedBy() {
        return eionetEditedBy;
    }

    public void setEionetEditedBy(String eionetEditedBy) {
        this.eionetEditedBy = eionetEditedBy;
    }

    public String getEionetInstitute() {
        return eionetInstitute;
    }

    public void setEionetInstitute(String eionetInstitute) {
        this.eionetInstitute = eionetInstitute;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getSiteEnded() {
        return siteEnded;
    }

    public void setSiteEnded(Boolean siteEnded) {
        this.siteEnded = siteEnded;
    }

    public Integer getContainedBy() {
        return containedBy;
    }

    public void setContainedBy(Integer containedBy) {
        this.containedBy = containedBy;
    }

    public CDDA() {
    }

    public CDDA(CDDA another) {
        this.rowIndex = another.rowIndex;
        this.cddaId = another.cddaId;
        this.nationalId = another.nationalId;
        this.pslocalId = another.pslocalId;
        this.psnamespace = another.psnamespace;
        this.psversionId = another.psversionId;
        this.designatedAreaType = another.designatedAreaType;
        this.cddaCountryCode = another.cddaCountryCode;
        this.cddaRegionCode = another.cddaRegionCode;
        this.designationTypeCode = another.designationTypeCode;
        this.iucnCategory = another.iucnCategory;
        this.siteArea = another.siteArea;
        this.majorEcosystemType = another.majorEcosystemType;
        this.marineAreaPercentage = another.marineAreaPercentage;
        this.spatialDataDissemination = another.spatialDataDissemination;
        this.spatialResolutionCode = another.spatialResolutionCode;
        this.eionetChangeDate = another.eionetChangeDate;
        this.eionetChangeType = another.eionetChangeType;
        this.eionetEditedBy = another.eionetEditedBy;
        this.eionetInstitute = another.eionetInstitute;
        this.remark = another.remark;
        this.siteEnded = another.siteEnded;
        this.containedBy = another.containedBy;
    }

    @Override
    public String toString() {
        return "CDDA{" +
                "rowIndex=" + rowIndex +
                ", cddaId=" + cddaId +
                ", nationalId='" + nationalId + '\'' +
                ", pslocalId='" + pslocalId + '\'' +
                ", psnamespace='" + psnamespace + '\'' +
                ", psversionId='" + psversionId + '\'' +
                ", designatedAreaType='" + designatedAreaType + '\'' +
                ", cddaCountryCode='" + cddaCountryCode + '\'' +
                ", cddaRegionCode='" + cddaRegionCode + '\'' +
                ", designationTypeCode='" + designationTypeCode + '\'' +
                ", iucnCategory='" + iucnCategory + '\'' +
                ", siteArea=" + siteArea +
                ", majorEcosystemType='" + majorEcosystemType + '\'' +
                ", marineAreaPercentage=" + marineAreaPercentage +
                ", spatialDataDissemination='" + spatialDataDissemination + '\'' +
                ", spatialResolutionCode='" + spatialResolutionCode + '\'' +
                ", eionetChangeDate=" + eionetChangeDate +
                ", eionetChangeType='" + eionetChangeType + '\'' +
                ", eionetEditedBy='" + eionetEditedBy + '\'' +
                ", eionetInstitute='" + eionetInstitute + '\'' +
                ", remark='" + remark + '\'' +
                ", siteEnded=" + siteEnded +
                ", containedBy=" + containedBy +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CDDA cdda = (CDDA) o;
        return
                //Objects.equals(rowIndex, cdda.rowIndex) &&
                //Objects.equals(cddaId, cdda.cddaId) &&
                Objects.equals(nationalId, cdda.nationalId) &&
                Objects.equals(pslocalId, cdda.pslocalId) &&
                Objects.equals(psnamespace, cdda.psnamespace) &&
                //Objects.equals(psversionId, cdda.psversionId) &&
                Objects.equals(designatedAreaType, cdda.designatedAreaType) &&
                Objects.equals(cddaCountryCode, cdda.cddaCountryCode) &&
                Objects.equals(cddaRegionCode, cdda.cddaRegionCode) &&
                Objects.equals(designationTypeCode, cdda.designationTypeCode) &&
                //Objects.equals(iucnCategory, cdda.iucnCategory) &&
                //Objects.equals(siteArea, cdda.siteArea) &&
                Objects.equals(BigDecimal.valueOf(siteArea)
                        .setScale(4, RoundingMode.HALF_UP)
                        .doubleValue(), BigDecimal.valueOf(cdda.siteArea)
                        .setScale(4, RoundingMode.HALF_UP)
                        .doubleValue()) &&
                Objects.equals(majorEcosystemType, cdda.majorEcosystemType) &&
                Objects.equals(marineAreaPercentage, cdda.marineAreaPercentage) &&
                Objects.equals(spatialDataDissemination, cdda.spatialDataDissemination) &&
                Objects.equals(spatialResolutionCode, cdda.spatialResolutionCode);
                //Objects.equals(eionetChangeDate, cdda.eionetChangeDate) &&
                //Objects.equals(eionetChangeType, cdda.eionetChangeType) &&
                //Objects.equals(eionetEditedBy, cdda.eionetEditedBy) &&
                //Objects.equals(eionetInstitute, cdda.eionetInstitute) &&
                //Objects.equals(remark, cdda.remark) &&
                //Objects.equals(siteEnded, cdda.siteEnded) &&
                //Objects.equals(containedBy, cdda.containedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nationalId, pslocalId, psnamespace, designatedAreaType, cddaCountryCode, cddaRegionCode, designationTypeCode, siteArea, majorEcosystemType, marineAreaPercentage, spatialDataDissemination, spatialResolutionCode);
    }
}
