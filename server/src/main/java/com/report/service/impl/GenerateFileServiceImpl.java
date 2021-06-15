package com.report.service.impl;

import java.io.ByteArrayInputStream;

import org.springframework.stereotype.Service;

import com.report.service.GenerateFileService;

import javassist.NotFoundException;

@Service
public class GenerateFileServiceImpl implements GenerateFileService {

//    private final ExcelService excelService;
//    private final DateRepository datesRepository;
//    private final EmployeeRepository employeeRepository;
//    private final DatesFreeRepostiory datesFreeRepostiory;
//
//    public GenerateFileServiceImpl(ExcelService excelService,
//                                   DateRepository datesRepository,
//                                   EmployeeRepository employeeRepository,
//                                   DatesFreeRepostiory datesFreeRepostiory) {
//        this.excelService = excelService;
//        this.datesRepository = datesRepository;
//        this.employeeRepository = employeeRepository;
//        this.datesFreeRepostiory = datesFreeRepostiory;
//    }

    @Override
    public ByteArrayInputStream generateExcel(final Long employeeId) throws  NotFoundException {
//        List<Dates> datesToExcel = this.datesRepository.findByEmployeeId(employeeId);
//        Optional<Employee> employeeOptional = this.employeeRepository.findById(employeeId);
//        List<DatesFree> datesFrees = this.datesFreeRepostiory.findAll();
//        datesFrees.forEach(date -> {
//            Dates dates = new Dates();
//            dates.setDate(date.getFreeDate());
//            dates.setDateType(date.getDateType());
//            datesToExcel.add(dates);
//        });
//
//        if(employeeOptional.isPresent()){
//            Employee employee = employeeOptional.get();
//            GenerateExcelDtoInternal generateExcelDtoInternal = new GenerateExcelDtoInternal();
//            generateExcelDtoInternal.setDates(datesToExcel);
//            generateExcelDtoInternal.setEmployee(employee);
//            generateExcelDtoInternal.setNameToPrint(employee.getName() + " " + employee.getLastName());
//
//            try {
//                return this.excelService.generateExcel(generateExcelDtoInternal);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return null;
//            }
//        } else {
//            throw new NotFoundException("Not found");
//        }
        return null;

    }

    @Override
    public void generatePDF(final Long userId) {

    }

}

