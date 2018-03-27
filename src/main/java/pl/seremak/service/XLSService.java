package pl.seremak.service;

import org.apache.poi.hssf.usermodel.*;
import pl.seremak.domain.CDDA;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@ManagedBean(name = "xlsService")
@ViewScoped
public class XLSService implements Serializable {

    private HSSFWorkbook xls;
    private HSSFSheet designatedAreaSheet;
    private HSSFSheet linkedDatasetSheet;
    private HSSFCellStyle dateStyle;

    public HSSFWorkbook getXls() {
        return xls;
    }

    public void setXls(HSSFWorkbook xls) {
        this.xls = xls;
        this.designatedAreaSheet = this.xls.getSheetAt(0);
        this.linkedDatasetSheet = this.xls.getSheetAt(1);

        HSSFRow row = designatedAreaSheet.getRow(1);
        HSSFCell cell = row.getCell(15);
        this.dateStyle = cell.getCellStyle();

    }

    public void setDataset(Integer id, String filename) {
        HSSFRow row = linkedDatasetSheet.getRow(1);
        if (row == null)
            row = linkedDatasetSheet.createRow(1);
        updateCell(row, 0, id);
        updateCell(row, 1, filename);
    }

    public List<CDDA> findAllCDDA() {
        List<CDDA> cddaList = new ArrayList<>();

        Iterator rows = designatedAreaSheet.rowIterator();
        rows.next(); //ominiecie naglowka
        int rIdx = 1; //index wiersza

        while (rows.hasNext()) {
            int i = 0;
            HSSFRow row = (HSSFRow) rows.next();
            CDDA cdda = new CDDA();
            cdda.setRowIndex(rIdx++);
            cdda.setCddaId(getIntegerValueFromCell(row.getCell(i++)));
            cdda.setNationalId(getStringValueFromCell(row.getCell(i++)));
            cdda.setPslocalId(getStringValueFromCell(row.getCell(i++)));
            cdda.setPsnamespace(getStringValueFromCell(row.getCell(i++)));
            cdda.setPsversionId(getStringValueFromCell(row.getCell(i++)));
            cdda.setDesignatedAreaType(getStringValueFromCell(row.getCell(i++)));
            cdda.setCddaCountryCode(getStringValueFromCell(row.getCell(i++)));
            cdda.setCddaRegionCode(getStringValueFromCell(row.getCell(i++)));
            cdda.setDesignationTypeCode(getStringValueFromCell(row.getCell(i++)));
            cdda.setIucnCategory(getStringValueFromCell(row.getCell(i++)));
            cdda.setSiteArea(getDoubleValueFromCell(row.getCell(i++)));
            cdda.setMajorEcosystemType(getStringValueFromCell(row.getCell(i++)));
            cdda.setMarineAreaPercentage(getIntegerValueFromCell(row.getCell(i++)));
            cdda.setSpatialDataDissemination(getStringValueFromCell(row.getCell(i++)));
            cdda.setSpatialResolutionCode(getStringValueFromCell(row.getCell(i++)));
            cdda.setEionetChangeDate(row.getCell(i++).getDateCellValue());
            cdda.setEionetChangeType(getStringValueFromCell(row.getCell(i++)));
            cdda.setEionetEditedBy(getStringValueFromCell(row.getCell(i++)));
            cdda.setEionetInstitute(getStringValueFromCell(row.getCell(i++)));
            cdda.setRemark(getStringValueFromCell(row.getCell(i++)));
            cdda.setSiteEnded(Boolean.valueOf(getStringValueFromCell(row.getCell(i++))));
            cdda.setContainedBy(getIntegerValueFromCell(row.getCell(i++)));

            cddaList.add(cdda);
        }

        return cddaList;
    }

    public void updateCDDA(CDDA cdda) {
        if (cdda == null)
            return;
        HSSFRow row = designatedAreaSheet.getRow(cdda.getRowIndex());
        if (row == null)
            row = designatedAreaSheet.createRow(cdda.getRowIndex());
        int i = 0;
        updateCell(row, i++, cdda.getCddaId());
        updateCell(row, i++, cdda.getNationalId());
        updateCell(row, i++, cdda.getPslocalId());
        updateCell(row, i++, cdda.getPsnamespace());
        updateCell(row, i++, cdda.getPsversionId());
        updateCell(row, i++, cdda.getDesignatedAreaType());
        updateCell(row, i++, cdda.getCddaCountryCode());
        updateCell(row, i++, cdda.getCddaRegionCode());
        updateCell(row, i++, cdda.getDesignationTypeCode());
        updateCell(row, i++, cdda.getIucnCategory());
        updateCell(row, i++, String.format("%.4f", cdda.getSiteArea()).replace(",","."));
        updateCell(row, i++, cdda.getMajorEcosystemType());
        updateCell(row, i++, cdda.getMarineAreaPercentage());
        updateCell(row, i++, cdda.getSpatialDataDissemination());
        updateCell(row, i++, cdda.getSpatialResolutionCode());
        updateCell(row, i++, cdda.getEionetChangeDate());
        updateCell(row, i++, cdda.getEionetChangeType());
        updateCell(row, i++, cdda.getEionetEditedBy());
        updateCell(row, i++, cdda.getEionetInstitute());
        updateCell(row, i++, cdda.getRemark());
        updateCell(row, i++, cdda.getSiteEnded());
        updateCell(row, i++, cdda.getContainedBy());
    }

    public void addCDDA(CDDA cdda) {
        int rows = designatedAreaSheet.getPhysicalNumberOfRows();
        cdda.setRowIndex(rows);
        updateCDDA(cdda);
    }

    private HSSFCell updateCell(HSSFRow row, int col, Object val) {
        HSSFCell cell = row.getCell(col);
        if(cell == null)
            cell = row.createCell(col);
        if (val != null) {
            if (val instanceof Double)
                cell.setCellValue((Double) val);
            if (val instanceof String)
                cell.setCellValue((String) val);
            if (val instanceof Integer)
                cell.setCellValue((Integer) val);
            if (val instanceof Boolean) {
                Boolean b = (Boolean) val;
                cell.setCellValue((Boolean.TRUE.equals(b))? "true" : "false" );
            }
            if (val instanceof Date) {
                cell.setCellValue((Date) val);
                cell.setCellStyle(dateStyle);
            }
        }

        return cell;
    }

    private String getStringValueFromCell(HSSFCell c) {
        String val = null;
        try {
            switch (c.getCellTypeEnum()) {
                case STRING:
                    val = c.getStringCellValue();
                    break;
                case NUMERIC:
                    val = ""+c.getNumericCellValue();
                    break;
            }
        } catch (Exception e) {}
        return val;
    }

    private Double getDoubleValueFromCell(HSSFCell c) {
        Double val = null;
        try {
            switch (c.getCellTypeEnum()) {
                case STRING:
                    val = Double.parseDouble(c.getStringCellValue());
                    break;
                case NUMERIC:
                    val = c.getNumericCellValue();
                    break;
            }
        } catch (Exception e) {}

        return val;
    }

    private Integer getIntegerValueFromCell(HSSFCell c) {
        if (getDoubleValueFromCell(c) != null)
            return getDoubleValueFromCell(c).intValue();
        return null;
    }

}
