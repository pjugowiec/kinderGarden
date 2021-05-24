package com.raport.utils;

import com.raport.domain.model.internal.CountOfDates;
import com.raport.domain.model.internal.DatesDtoInternal;
import com.raport.domain.model.internal.GenerateExcelDtoInternal;
import com.raport.domain.entity.Dates;
import com.raport.domain.entity.Employee;
import com.raport.domain.enums.DateType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ExcelService {
    private final static Logger LOGGER = Logger.getLogger(ExcelService.class.getName());

    public ByteArrayInputStream generateExcel(GenerateExcelDtoInternal generateExcelDtoInternal) throws IOException {
        Employee employee = generateExcelDtoInternal.getEmployee();
        CountOfDates countOfDates = new CountOfDates(0,0,0,0,0,0,0,0,0,0,0);

        List<DatesDtoInternal> datesToPrint = new LinkedList<>();

        for(Dates date : generateExcelDtoInternal.getDates()) {
            DatesDtoInternal datesDtoInternal = new DatesDtoInternal();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date.getDate());
            datesDtoInternal.setDateType(date.getDateType());
            datesDtoInternal.setDay(calendar.get(Calendar.DAY_OF_MONTH));
            datesDtoInternal.setMonth(calendar.get(Calendar.MONTH) + 1);
            datesToPrint.add(datesDtoInternal);
        }

        String sheetName = generateExcelDtoInternal.getNameToPrint();

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Sheet sheet = workbook.createSheet(sheetName);
        Map<String, CellStyle> styles = createStyles(workbook);
        sheet.setDefaultColumnWidth((short) 4);
        sheet.setDefaultRowHeightInPoints((float) 10);

        //title row
        Row titleRow = sheet.createRow(1);
        titleRow.setHeightInPoints(40);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("ROCZNA KARTA EWIDENCJI CZASU PRACY za rok " + Calendar.getInstance().get(Calendar.YEAR));
        titleCell.setCellStyle(styles.get("title"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$AQ$2"));

//        words in top-left
        Row sealTopRow = sheet.createRow(2);
        sealTopRow.setHeightInPoints(15);
        Cell sealTopCell = sealTopRow.createCell(0);
        sealTopCell.setCellValue("Pieczęć jednoski organizacyjnej");
        sealTopCell.setCellStyle(styles.get("normal"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:$J$3"));

//        RegularPost panel
        Row regularPostRow = sheet.createRow(3);
        regularPostRow.setHeightInPoints(23);
        Cell regularPostCell = regularPostRow.createCell(0);
        regularPostCell.setCellStyle(styles.get("normal"));
        regularPostCell.setCellValue(employee.getRegularPost());
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$4:$J$4"));

//        Free place
        Row freePlaceTopRow = sheet.createRow(4);
        freePlaceTopRow.setHeightInPoints(30);
        Cell freePlaceTopCell = freePlaceTopRow.createCell(0);
        freePlaceTopCell.setCellStyle(styles.get("normal"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$5:$J$5"));

//        Name and surname (right side)
        Cell nameAndSurname = regularPostRow.createCell(23);
        nameAndSurname.setCellValue("Imie i nazwisko pracownika: " + generateExcelDtoInternal.getNameToPrint());
        nameAndSurname.setCellStyle(styles.get("nameAndSurname"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$W$4:$AQ$4"));

//      Workplace (right side)
        Cell workplaceCell = freePlaceTopRow.createCell(23);
        workplaceCell.setCellValue("Stanowisko pracy: " + employee.getPosition());
        workplaceCell.setCellStyle(styles.get("nameAndSurname"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$W$5:$AQ$5"));

//        Table
        Row firstTableRow = sheet.createRow(5);
        Cell dayOfMonths = firstTableRow.createCell(3);
        dayOfMonths.setCellValue("Dni miesiąca");
        dayOfMonths.setCellStyle(styles.get("dayOfMonths"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$B$6:$AF$6"));

        Cell monthCell = firstTableRow.createCell(0);
        monthCell.setCellValue("m");
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$6:$A$7"));

//        Horizontal
        Row secondTableRow = sheet.createRow(6);
        for(int i = 1; i <= 31; i++) {
            Cell dayToDisplay = secondTableRow.createCell(i);
            dayToDisplay.setCellValue(i);
            dayToDisplay.setCellStyle(styles.get("days"));
        }

        for(int i = 1; i <= 11; i++){
            Cell printStringDateType = secondTableRow.createCell(31 + i);
            printStringDateType.setCellStyle(styles.get("days"));

            switch(i){
                case 1:
                    printStringDateType.setCellValue(DateType.Uw.name());
                    break;
                case 2:
                    printStringDateType.setCellValue(DateType.Um.name());
                    break;
                case 3:
                    printStringDateType.setCellValue(DateType.Ub.name());
                    break;
                case 4:
                    printStringDateType.setCellValue(DateType.Usz.name());
                    break;
                case 5:
                    printStringDateType.setCellValue(DateType.Uo.name());
                    break;
                case 6:
                    printStringDateType.setCellValue(DateType.Ch.name());
                    break;
                case 7:
                    printStringDateType.setCellValue(DateType.NN.name());
                    break;
                case 8:
                    printStringDateType.setCellValue(DateType.Nup.name());
                    break;
                case 9:
                    printStringDateType.setCellValue(DateType.Nun.name());
                    break;
                case 10:
                    printStringDateType.setCellValue(DateType.Del.name());
                    break;
                case 11:
                    printStringDateType.setCellValue(DateType.Inne.name());
                    break;
            }
        }


        for(int i = 1; i <= 12; i++ ) {
            CountOfDates countOfDatesInternal = new CountOfDates();
            Row monthsRow = sheet.createRow(i + 6);
            monthsRow.setHeightInPoints((short) 15);
            Cell monthsCell = monthsRow.createCell(0);
            monthsCell.setCellValue(i);
            monthsCell.setCellStyle(styles.get("days"));
            int finalI = i;
            List<DatesDtoInternal> listToPrint = datesToPrint.stream()
                                                .filter(value -> value.getMonth() == finalI)
                                                .collect(Collectors.toList());

            listToPrint.forEach(value -> {
                     Cell day = monthsRow.createCell(value.getDay());
                     if(value.getDateType().equals(DateType.Saturday) || value.getDateType().equals(DateType.Sunday)){
                         day.setCellValue("X");
                     } else {
                         day.setCellValue(value.getDateType().name());
                     }

                    switch(value.getDateType()){
                        case Uw:
                            day.setCellStyle(styles.get("days"));
                            countOfDatesInternal.setUw(countOfDatesInternal.getUw() + 1);
                            countOfDates.setUw(countOfDates.getUw() + 1);
                            break;
                        case Um:
                            countOfDatesInternal.setUm(countOfDatesInternal.getUm() + 1);
                            day.setCellStyle(styles.get("days"));
                            countOfDates.setUm(countOfDates.getUm() + 1);
                            break;
                        case Ub:
                            countOfDatesInternal.setUb(countOfDatesInternal.getUb() + 1);
                            day.setCellStyle(styles.get("days"));
                            countOfDates.setUb(countOfDates.getUb() + 1);
                            break;
                        case Usz:
                            countOfDatesInternal.setUsz(countOfDatesInternal.getUsz() + 1);
                            day.setCellStyle(styles.get("days"));
                            countOfDates.setUsz(countOfDates.getUsz() + 1);
                            break;
                        case Uo:
                            countOfDatesInternal.setUo(countOfDatesInternal.getUo() + 1);
                            day.setCellStyle(styles.get("days"));
                            countOfDates.setUo(countOfDates.getUo() + 1);
                            break;
                        case Ch:
                            countOfDatesInternal.setCh(countOfDatesInternal.getCh() + 1);
                            day.setCellStyle(styles.get("days"));
                            countOfDates.setCh(countOfDates.getCh() + 1);
                            break;
                        case NN:
                            countOfDatesInternal.setNN(countOfDatesInternal.getNN() + 1);
                            day.setCellStyle(styles.get("days"));
                            countOfDates.setNN(countOfDates.getNN() + 1);
                            break;
                        case Nup:
                            countOfDatesInternal.setNup(countOfDatesInternal.getNup() + 1);
                            day.setCellStyle(styles.get("days"));
                            countOfDates.setNup(countOfDates.getNup() + 1);
                            break;
                        case Nun:
                            countOfDatesInternal.setNun(countOfDatesInternal.getNun() + 1);
                            day.setCellStyle(styles.get("days"));
                            countOfDates.setNun(countOfDates.getNun() + 1);
                            break;
                        case Del:
                            countOfDatesInternal.setDel(countOfDatesInternal.getDel() + 1);
                            day.setCellStyle(styles.get("days"));
                            countOfDates.setDel(countOfDates.getDel() + 1);
                            break;
                        case Inne:
                            countOfDatesInternal.setInne(countOfDatesInternal.getInne() + 1);
                            day.setCellStyle(styles.get("days"));
                            countOfDates.setInne(countOfDates.getInne() + 1);
                            break;
                        case Saturday:
                            day.setCellStyle(styles.get("saturday"));
                            break;
                        case Sunday:
                            day.setCellStyle(styles.get("sunday"));
                            break;
                    }
                     for(int k = 1; k <= 11; k++ ){
                         Cell countMonth = monthsRow.createCell(31 + k);
                         countMonth.setCellStyle(styles.get("days"));
                         switch(k){
                             case 1:
                                 countMonth.setCellValue(countOfDatesInternal.getUw());
                                 break;
                             case 2:
                                 countMonth.setCellValue(countOfDatesInternal.getUm());
                                 break;
                             case 3:
                                 countMonth.setCellValue(countOfDatesInternal.getUb());
                                 break;
                             case 4:
                                 countMonth.setCellValue(countOfDatesInternal.getUsz());
                                 break;
                             case 5:
                                 countMonth.setCellValue(countOfDatesInternal.getUo());
                                 break;
                             case 6:
                                 countMonth.setCellValue(countOfDatesInternal.getCh());
                                 break;
                             case 7:
                                 countMonth.setCellValue(countOfDatesInternal.getNN());
                                 break;
                             case 8:
                                 countMonth.setCellValue(countOfDatesInternal.getNup());
                                 break;
                             case 9:
                                 countMonth.setCellValue(countOfDatesInternal.getNun());
                                 break;
                             case 10:
                                 countMonth.setCellValue(countOfDatesInternal.getDel());
                                 break;
                             case 11:
                                 countMonth.setCellValue(countOfDatesInternal.getInne());
                                 break;
                         }
                     }
            });
        }

        Row countRow = sheet.createRow(19);
        Cell countCell = countRow.createCell(1);
        countCell.setCellValue("Razem: ");
        countCell.setCellStyle(styles.get("count"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$20:$B$20"));

        for(int j = 1; j <= 11; j++ ){
            Cell countPrint = countRow.createCell(31 + j);
            countPrint.setCellStyle(styles.get("days"));
            switch(j){
                case 1:
                    countPrint.setCellValue(countOfDates.getUw());
                    break;
                case 2:
                    countPrint.setCellValue(countOfDates.getUm());
                    break;
                case 3:
                    countPrint.setCellValue(countOfDates.getUb());
                    break;
                case 4:
                    countPrint.setCellValue(countOfDates.getUsz());
                    break;
                case 5:
                    countPrint.setCellValue(countOfDates.getUo());
                    break;
                case 6:
                    countPrint.setCellValue(countOfDates.getCh());
                    break;
                case 7:
                    countPrint.setCellValue(countOfDates.getNN());
                    break;
                case 8:
                    countPrint.setCellValue(countOfDates.getNup());
                    break;
                case 9:
                    countPrint.setCellValue(countOfDates.getNun());
                    break;
                case 10:
                    countPrint.setCellValue(countOfDates.getDel());
                    break;
                case 11:
                    countPrint.setCellValue(countOfDates.getInne());
                    break;
            }
        }

        /*
         * First row
         */
        Row firstDescriptionRow= sheet.createRow(21);
        Cell uwDescription = firstDescriptionRow.createCell(1);
        uwDescription.setCellValue("Uw - urlop wypoczynkowy");
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$22:$E$22"));

        Cell umDescription = firstDescriptionRow.createCell(6);
        umDescription.setCellValue("Um - urlop macierzyński");
        sheet.addMergedRegion(CellRangeAddress.valueOf("$G$22:$K$22"));

        Cell ubDescription = firstDescriptionRow.createCell(12);
        ubDescription.setCellValue("Ub - urlop bezpłatny");
        sheet.addMergedRegion(CellRangeAddress.valueOf("$M$22:$Q$22"));

        Cell uszDescription = firstDescriptionRow.createCell(21);
        uszDescription.setCellValue("Usz - urlop szkoleniowy");
        sheet.addMergedRegion(CellRangeAddress.valueOf("$S$22:$V$22"));

        Cell uoDescription = firstDescriptionRow.createCell(26);
        uoDescription.setCellValue("Uo - urlop okolicznościowy (opieka nad dzieckiem, ślub, pogrzeb itp.)");
        sheet.addMergedRegion(CellRangeAddress.valueOf("$X$22:$AH$22"));

        Cell chDescription = firstDescriptionRow.createCell(35);
        chDescription.setCellValue("Ch/Op - choroba pracownika/opieka nad chorym");
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AJ$22:$AQ$22"));

        /*
        * Second row
        */
        Row secondDescriptionRow = sheet.createRow(22);
        Cell nnDescription = secondDescriptionRow.createCell(1);
        nnDescription.setCellValue("NN - nieobecność nieusprawiedliwiona");
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$23:$G$23"));

        Cell nupDescription = secondDescriptionRow.createCell(8);
        nupDescription.setCellValue("Nup - nieobecność usprawiedliwiona (płatna)");
        sheet.addMergedRegion(CellRangeAddress.valueOf("$I$23:$Q$23"));

        Cell nunDescription = secondDescriptionRow.createCell(21);
        nunDescription.setCellValue("Nun - nieobecność usprawiedliwiona niepłatna");
        sheet.addMergedRegion(CellRangeAddress.valueOf("$S$23:$Z$23"));

        Cell delDescription = secondDescriptionRow.createCell(30);
        delDescription.setCellValue("Del - delegacje, Inne  (szkolenia, urlop wychowawczy i inne wg potrzeb)");
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AB$23:$AQ$23"));

        Row signatureRow = sheet.createRow(24);
        Cell signatureCell = signatureRow.createCell(31);
        signatureCell.setCellValue("Podpis kierownika jednostki");
        signatureCell.setCellStyle(styles.get("count"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AF$25:$AQ$25"));

        Row emptyRow = sheet.createRow(25);
        emptyRow.setHeightInPoints(50);
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AF$26:$AQ$26"));

        workbook.write(out);
        return new ByteArrayInputStream(out.toByteArray());
    }

    /**
     * Create a library of cell styles
     */
    private static Map<String, CellStyle> createStyles(Workbook wb) {

        Map<String, CellStyle> styles = new HashMap<>();

        CellStyle style;

        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short)25);
        titleFont.setBold(true);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(titleFont);
        styles.put("title", style);

        style = wb.createCellStyle();
        Font normalStyle = wb.createFont();
        normalStyle.setFontHeightInPoints((short) 8);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(normalStyle);
        styles.put("normal", style);

        style = wb.createCellStyle();
        Font nameAndSurname = wb.createFont();
        nameAndSurname.setFontHeightInPoints((short) 12);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(nameAndSurname);
        styles.put("nameAndSurname", style);

        style = wb.createCellStyle();
        Font dayOfMonths = wb.createFont();
        dayOfMonths.setFontHeightInPoints((short) 12);
        dayOfMonths.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(dayOfMonths);
        styles.put("dayOfMonths", style);

        style = wb.createCellStyle();
        Font days = wb.createFont();
        days.setFontHeightInPoints((short) 10);
        days.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(days);
        styles.put("days", style);

        style = wb.createCellStyle();
        Font symbol = wb.createFont();
        symbol.setFontHeightInPoints((short) 6);
        symbol.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(symbol);
        styles.put("symbol", style);

        style = wb.createCellStyle();
        Font sunday = wb.createFont();
        sunday.setFontHeightInPoints((short) 10);
        sunday.setBold(true);
        sunday.setColor(IndexedColors.RED.getIndex());
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(sunday);
        styles.put("sunday", style);

        style = wb.createCellStyle();
        Font saturday = wb.createFont();
        saturday.setFontHeightInPoints((short) 10);
        saturday.setBold(true);
        saturday.setColor(IndexedColors.GREEN.getIndex());
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(saturday);
        styles.put("saturday", style);

        style = wb.createCellStyle();
        Font count = wb.createFont();
        count.setFontHeightInPoints((short) 15);
        count.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(count);
        styles.put("count", style);

        return styles;
    }
    }


