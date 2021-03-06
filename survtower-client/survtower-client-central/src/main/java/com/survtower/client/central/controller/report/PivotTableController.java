package com.survtower.client.central.controller.report;

import com.survtower.business.common.domain.Member;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.common.service.MemberService;
import com.survtower.business.common.service.SurveillanceDataService;
import com.survtower.client.central.utility.MessageInfor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

/**
 *
 * @author Takund Dhlakama
 */
@ManagedBean
@ViewScoped
public class PivotTableController extends MessageInfor implements Serializable {

    @ManagedProperty(value = "#{surveillanceDataService}")
    private SurveillanceDataService surveillanceDataService;

    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;
    private List<SurveillanceData> surveillanceDataList = new ArrayList<SurveillanceData>();
    private List<Period> periods = new ArrayList<Period>();
    private List<Member> members = new ArrayList<Member>();
    private Program program;

    public SurveillanceDataService getSurveillanceDataService() {
        return surveillanceDataService;
    }

    public void setSurveillanceDataService(SurveillanceDataService surveillanceDataService) {
        this.surveillanceDataService = surveillanceDataService;
    }

    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public void createPivotTableIndicatorChart() {
        getSurveillanceDataList().clear();
        for (Period period : getPeriods()) {
            for (Member member : getMembers()) {
                getSurveillanceDataList().addAll(surveillanceDataService.findSurveillanceDataItems(program, period, member));
            }
        }
    }

    public List<SurveillanceData> getSurveillanceDataList() {
        return surveillanceDataList;
    }

    public void setSurveillanceDataList(List<SurveillanceData> surveillanceDataList) {
        this.surveillanceDataList = surveillanceDataList;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public String reset() {
        getSurveillanceDataList().clear();
        getPeriods().clear();
        getMembers().clear();
        return null;
    }

    public void exportToExcel() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        int rowIndex = 0;
        HSSFRow row = sheet.createRow(rowIndex);
        row.createCell(0).setCellValue("Program");
        row.createCell(1).setCellValue("Period");
        row.createCell(2).setCellValue("Member");
        row.createCell(3).setCellValue("Indicator");
        row.createCell(4).setCellValue("Numerator");
        row.createCell(5).setCellValue("Numerator Value");
        row.createCell(6).setCellValue("Denominator");
        row.createCell(7).setCellValue("Denominotor Value");
        row.createCell(8).setCellValue("Indicator Value");
        rowIndex++;
        for (SurveillanceData data : getSurveillanceDataList()) {
            row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(data.getSurveillance().getProgram().getName());
            row.createCell(1).setCellValue(data.getSurveillance().getPeriod().getName());
            row.createCell(2).setCellValue(data.getSurveillance().getMember().getName());
            row.createCell(3).setCellValue(data.getIndicator().getName());
            row.createCell(4).setCellValue(data.getIndicator().getNumeratorDataElement().getName());
            row.createCell(5).setCellValue(data.getNumeratorValue());
            row.createCell(6).setCellValue(data.getIndicator().getDenominatorDataElement().getName());
            row.createCell(7).setCellValue(data.getDenominatorValue());
            row.createCell(8).setCellValue(data.getCalculatedValue() + "" + data.getIndicator().getIndicatorType());
            rowIndex++;
        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.setResponseContentType("application/vnd.ms-excel");
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=" + getProgram().getName() + " Surviellance Data.xls");

        workbook.write(externalContext.getResponseOutputStream());
        facesContext.responseComplete();
    }

    public void createAdoc() throws IOException {
        TextColumnBuilder<Double> numeratorValue = Columns.column("numeratorValue", DataTypes.doubleType());
        TextColumnBuilder<Double> denominatorValue = Columns.column("denominatorValue", DataTypes.doubleType());
        JasperReportBuilder report = DynamicReports.report();//a new report
        report.columns(
                Columns.column("Program", "program", DataTypes.stringType()),
                Columns.column("Period", "period", DataTypes.stringType()),
                Columns.column("Member", "member", DataTypes.stringType()),
                Columns.column("Indicator", "indicator", DataTypes.stringType()),
                numeratorValue,
                Columns.column("Numerator Value", "numeratorValue", DataTypes.doubleType()),
                Columns.column("Denominator", "denominator", DataTypes.stringType()),
                denominatorValue,
                Columns.column("Indicator Value", "indicatorValue", DataTypes.stringType())
        )
                .title(Components.text(getProgram().getName() + " Surveillance Data").setHorizontalAlignment(HorizontalAlignment.CENTER))
                .pageFooter(Components.pageXofY())//show page number on the page footer
                .setDataSource(createDataSource());
        try {

            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.setResponseContentType("application/vnd.pdf");
            externalContext.setResponseHeader("Content-Disposition", "attachment; filename=" + getProgram().getName() + " Surviellance Data.pdf");

            //export the report to a pdf file
            report.toPdf(externalContext.getResponseOutputStream());
            facesContext.responseComplete();

        } catch (DRException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("program", "period", "member", "indicator", "numerator", "numeratorValue", "denominator", "denominatorValue", "indicatorValue");
        for (SurveillanceData data : getSurveillanceDataList()) {
            dataSource.add(data.getSurveillance().getProgram().getName(),
                    data.getSurveillance().getPeriod().getName(),
                    data.getSurveillance().getMember().getName(),
                    data.getIndicator().getName(),
                    data.getIndicator().getNumeratorDataElement().getName(),
                    data.getNumeratorValue(),
                    data.getIndicator().getDenominatorDataElement().getName(),
                    data.getDenominatorValue(),
                    data.getCalculatedValue() + "" + data.getIndicator().getIndicatorType());
        }
        return dataSource;
    }
}
