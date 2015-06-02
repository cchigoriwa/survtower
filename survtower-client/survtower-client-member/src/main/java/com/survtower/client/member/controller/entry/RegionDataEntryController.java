/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survtower.client.member.controller.entry;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.common.service.MemberService;
import com.survtower.business.common.service.PeriodService;
import com.survtower.business.common.service.ProgramService;
import com.survtower.business.common.service.SurveillanceService;
import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.domain.RegionSurveillanceAudit;
import com.survtower.business.member.domain.RegionSurveillanceData;
import com.survtower.business.member.service.MemberUserService;
import com.survtower.business.member.service.RegionService;
import com.survtower.business.member.service.RegionSurveillanceAuditService;
import com.survtower.business.member.service.RegionSurveillanceDataService;
import com.survtower.client.member.utility.MessageInfor;
import static com.survtower.client.member.utility.MessageInfor.errorMessages;
import static com.survtower.client.member.utility.MessageInfor.inforMessages;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author tdhlakama
 */
@ManagedBean
@ViewScoped
public class RegionDataEntryController extends MessageInfor implements Serializable {

    public RegionDataEntryController() {
    }

    private Boolean submitted = Boolean.FALSE;
    private Period period;
    private Program program;
    private Region region;
    private Surveillance surveillance;
    private RegionSurveillanceAudit surveillanceAudit;

    @ManagedProperty(value = "#{regionService}")
    RegionService regionService;

    @ManagedProperty(value = "#{regionSurveillanceDataService}")
    RegionSurveillanceDataService regionSurveillanceDataService;

    @ManagedProperty(value = "#{programService}")
    ProgramService programService;

    @ManagedProperty(value = "#{periodService}")
    PeriodService periodService;

    @ManagedProperty(value = "#{surveillanceService}")
    SurveillanceService surveillanceService;

    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    @ManagedProperty(value = "#{regionSurveillanceAuditService}")
    private RegionSurveillanceAuditService surveillanceAuditService;

    public void setSurveillanceAuditService(RegionSurveillanceAuditService surveillanceAuditService) {
        this.surveillanceAuditService = surveillanceAuditService;
    }

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

    public void setRegionSurveillanceDataService(RegionSurveillanceDataService regionSurveillanceDataService) {
        this.regionSurveillanceDataService = regionSurveillanceDataService;
    }

    public RegionSurveillanceAudit getSurveillanceAudit() {
        return surveillanceAudit;
    }

    public void setSurveillanceAudit(RegionSurveillanceAudit surveillanceAudit) {
        this.surveillanceAudit = surveillanceAudit;
    }

    public Boolean getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Boolean submitted) {
        this.submitted = submitted;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }

    public void setSurveillanceService(SurveillanceService surveillanceService) {
        this.surveillanceService = surveillanceService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Surveillance getSurveillance() {
        return surveillance;
    }

    public void saveInitalDataValues() {
        for (RegionSurveillanceData data : getSurveillanceDataList()) {
            regionSurveillanceDataService.save(data);
        }
        if (getSurveillanceAudit().getId() == null) {
            getSurveillanceAudit().setPeriod(period);
            getSurveillanceAudit().setProgram(program);
            getSurveillanceAudit().setRegion(region);
            getSurveillanceAudit().setUploadedBy(getCurrentUser());
            surveillanceAuditService.save(surveillanceAudit);
        }
    }

    public void setSurveillance(Surveillance surveillance) {
        this.surveillance = surveillance;
    }

    public String submitSurviellanceForm() {
        saveInitalDataValues();
        submitted = Boolean.TRUE;
        return null;
    }

    public String saveSurviellanceForm() {
        if (getCurrentUser() == null) {
            errorMessages("User needs to login to continue");
            return null;
        }
        try {
            for (RegionSurveillanceData data : getSurveillanceDataList()) {
                if (!data.getValid()) {
                    errorMessages("Data Incomplete");
                    submitted = Boolean.TRUE;
                    return null;
                }
            }

            if (!getCurrentUser().getRoles().contains(MemberUser.ROLE_COUNTRY_DISEASE_MANAGER)) {
                if (getSurveillanceAudit().getSubmissionDone()) {//Check for Final Submission
                    errorMessages("Data Upload Has Already been Approved,Changes Permitted");
                    return null;
                }
            }

            submitted = Boolean.TRUE;
            for (RegionSurveillanceData data : getSurveillanceDataList()) {
                if (data.getValid()) {
                    regionSurveillanceDataService.save(data);
                }
            }

            getSurveillanceAudit().setUploadedBy(getCurrentUser());
            getSurveillanceAudit().setUploadedOn(new Date());
            surveillanceAuditService.save(surveillanceAudit);

            inforMessages("Surviellance Data Saved Successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            errorMessages("Surveillance Data Not Processed Succefully");
        }
        return null;
    }

    public String editSurviellanceForm() {
        submitted = Boolean.FALSE;
        return null;
    }

    public String reset() {
        surveillance = null;
        program = null;
        period = null;
        region = null;
        return null;
    }
    private List<RegionSurveillanceData> surveillanceDataList = new ArrayList<RegionSurveillanceData>();

    public RegionService getRegionService() {
        return regionService;
    }

    public void setRegionService(RegionService regionService) {
        this.regionService = regionService;
    }

    public List<RegionSurveillanceData> getSurveillanceDataList() {
        return surveillanceDataList;
    }

    public void setSurveillanceDataList(List<RegionSurveillanceData> surveillanceDataList) {
        this.surveillanceDataList = surveillanceDataList;
    }

    @PostConstruct
    public void loadData() {
        String programId = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("programId");
        String periodId = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("periodId");
        String regionId = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("regionId");
        program = programService.findByUuid(programId);
        period = periodService.findByUuid(periodId);
        region = regionService.findByUuid(regionId);

        surveillance = surveillanceService.get(program, period, memberService.getCurrentMember());

        if (surveillance == null) {
            surveillance = surveillanceService.createSurveillanceData(program, period, memberService.getCurrentMember());
        }

        surveillanceAudit = surveillanceAuditService.get(program, period, region);

        if (surveillanceAudit == null) {
            surveillanceAudit = new RegionSurveillanceAudit();
            for (SurveillanceData surveillanceData : getSurveillance().getSurveillanceDataSet()) {
                RegionSurveillanceData data = null;
                data = regionSurveillanceDataService.find(surveillanceData, region);
                if (data == null) {
                    data = new RegionSurveillanceData();
                    data.setSurveillanceData(surveillanceData);
                    data.setCreateDate(new Date());
                    data.setRegion(region);
                }
                surveillanceDataList.add(data);
            }
        } else {
            surveillanceDataList.addAll(regionSurveillanceDataService.findAll(surveillance, region));
        }

    }

    public MemberUser getCurrentUser() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext externalContext = fc.getExternalContext();
        if (externalContext.getUserPrincipal() == null) {
            return null;
        } else {
            String user = externalContext.getUserPrincipal().getName();
            MemberUser memberUser = memberUserService.findByUserName(user);
            if (memberUser != null) {
                return memberUser;
            } else {
                return null;
            }
        }
    }

    public String downloadSurviellanceForm() {

        saveInitalDataValues();

        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        response.setContentType("application/vnd.ms-excel");

        response.setContentType("application/force-download");

        String downloadFile = getRegion().getName() + " " + getPeriod().getName() + ".xls";

        downloadFile = downloadFile.replace(" ", "_");

        response.addHeader("content-disposition", "inline;  filename=" + downloadFile + "");

        try {
            Workbook workbook = writeFile(new HSSFWorkbook());
            workbook.write(response.getOutputStream());
            inforMessages("Data Entry Form Created");
        } catch (Exception ex) {
            ex.printStackTrace();
            errorMessages("Error Creating Data Entry Form");
        }
        facesContext.responseComplete();
        return null;
    }

    public Workbook writeFile(Workbook workbook) {
        workbook = new HSSFWorkbook();

        Sheet sheet = workbook.createSheet(getRegion().getName() + " " + getPeriod().getName() + " " + getProgram().getName());

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short) 13);
        style.setFont(font);
        style.setWrapText(true);

        int rowIndex = 0;
        sheet.createRow(rowIndex);
        sheet.getRow(rowIndex).setZeroHeight(true);
        sheet.setColumnHidden(rowIndex, true);
        sheet.getRow(rowIndex).createCell(1).setCellValue(getRegion().getUuid());
        sheet.getRow(rowIndex).createCell(2).setCellValue(getProgram().getUuid());
        sheet.getRow(rowIndex).createCell(3).setCellValue(getPeriod().getUuid());
        rowIndex++;

        sheet.createRow(rowIndex);
        sheet.getRow(rowIndex).createCell(1).setCellValue("Regional Surveillance Data Entry Form");
        sheet.getRow(rowIndex).createCell(2).setCellValue("");
        sheet.getRow(rowIndex).getCell(1).setCellStyle(style);
        sheet.getRow(rowIndex).setHeight((short) 400);
        sheet.addMergedRegion(new CellRangeAddress(
                rowIndex, //first row (0-based)
                rowIndex, //last row  (0-based)
                1, //first column (0-based)
                2 //last column  (0-based)
        ));

        rowIndex++;

        sheet.createRow(rowIndex);
        sheet.getRow(rowIndex).createCell(1).setCellValue(getRegion().getName());
        sheet.getRow(rowIndex).createCell(2).setCellValue(getProgram().getName());
        sheet.getRow(rowIndex).createCell(3).setCellValue(getPeriod().getName());
        sheet.getRow(rowIndex).getCell(1).setCellStyle(style);
        sheet.getRow(rowIndex).setHeight((short) 400);

        for (int i = 1; i < 4; i++) {
            sheet.getRow(rowIndex).getCell(i).setCellStyle(style);
        }

        rowIndex++;

        sheet.createRow(rowIndex);
        sheet.getRow(rowIndex).createCell(1).setCellValue("Indicator Name");
        sheet.getRow(rowIndex).createCell(2).setCellValue("Numerator Value");
        sheet.getRow(rowIndex).createCell(3).setCellValue("Denominator Value");
        sheet.getRow(rowIndex).setHeight((short) 400);
        for (int i = 1; i < 4; i++) {
            sheet.getRow(rowIndex).getCell(i).setCellStyle(style);
        }
        rowIndex++;

        for (RegionSurveillanceData data : surveillanceDataList) {
            sheet.createRow(rowIndex);
            sheet.getRow(rowIndex).createCell(0).setCellValue(data.getUuid());
            sheet.getRow(rowIndex).createCell(1).setCellValue(data.getSurveillanceData().getIndicator().getName());
            font.setFontHeightInPoints((short) 12);
            sheet.getRow(rowIndex).getCell(1).setCellStyle(style);

            if (data.getNumeratorValue() != null) {
                sheet.getRow(rowIndex).createCell(2).setCellValue(data.getNumeratorValue());
            } else {
                sheet.getRow(rowIndex).createCell(2).setCellType(Cell.CELL_TYPE_NUMERIC);
            }
            if (data.getDenominatorValue() != null) {
                sheet.getRow(rowIndex).createCell(3).setCellValue(data.getDenominatorValue());
            } else {
                sheet.getRow(rowIndex).createCell(3).setCellType(Cell.CELL_TYPE_NUMERIC);
            }

            sheet.getRow(rowIndex).setHeight((short) 400);
            rowIndex++;
        }

        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);

        return workbook;

    }

    public String handleFileUpload(FileUploadEvent event) {

        Workbook workbook = null;

        try {

            if (event.getFile() == null) {
                errorMessages("File Cannot found");
            }

            workbook = new HSSFWorkbook(event.getFile().getInputstream());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            errorMessages("Error reading file");
            return null;
        }

        Sheet sheet = workbook.getSheetAt(0);
        String regionId, programId, periodId = null;
        try {
            regionId = sheet.getRow(0).getCell(1).getStringCellValue();
            programId = sheet.getRow(0).getCell(2).getStringCellValue();
            periodId = sheet.getRow(0).getCell(3).getStringCellValue();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            errorMessages("Could Not Locate Key Identifiers for Excel Upload, File Cannot be used");
            return null;
        }

        program = programService.findByUuid(programId);
        period = periodService.findByUuid(periodId);
        region = regionService.findByUuid(regionId);

        if (program == null) {
            errorMessages("Program Surviellance not found, File Cannot be used");
            return null;
        }

        if (period == null) {
            errorMessages("Period Surviellance not found, File Cannot be used");
            return null;
        }

        if (region == null) {
            errorMessages("Region not found, File Cannot be used");
            return null;
        }

        surveillance = surveillanceService.get(program, period, memberService.getCurrentMember());

        if (surveillance == null) {
            errorMessages("Surviellance Reporting not Found, File Cannot be used");
            return null;
        }

        int errors = 0;
        int itemsSaved = 0;
        int lastrow = sheet.getLastRowNum() + 1;
        for (int rowIndex = 4; rowIndex < lastrow; rowIndex++) {
            RegionSurveillanceData data = regionSurveillanceDataService.findByUuid(sheet.getRow(rowIndex).getCell(0).getStringCellValue());
            if (data != null && data.getRegion().getId().equals(region.getId())) {
                try {
                    data.setNumeratorValue(sheet.getRow(rowIndex).getCell(2).getNumericCellValue());
                    data.setNumeratorValue(sheet.getRow(rowIndex).getCell(3).getNumericCellValue());
                    regionSurveillanceDataService.save(data);
                    itemsSaved++;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                    errors++;
                }
            }
        }
        inforMessages(itemsSaved + " Entries Uploaded. " + errors + " Entries Failed to Upload.");
        surveillanceDataList.clear();
        surveillanceDataList.addAll(regionSurveillanceDataService.findAll(surveillance, region));
        return null;

    }

}
