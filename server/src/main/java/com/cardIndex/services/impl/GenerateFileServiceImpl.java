package com.cardIndex.services.impl;

import com.cardIndex.domain.dto.internal.GenerateExcelDtoInternal;
import com.cardIndex.domain.entity.Dates;
import com.cardIndex.domain.entity.DatesFree;
import com.cardIndex.domain.entity.Employee;
import com.cardIndex.repository.DatesFreeRepostiory;
import com.cardIndex.repository.DatesRepository;
import com.cardIndex.repository.EmployeeRepository;
import com.cardIndex.services.GenerateFileService;
import com.cardIndex.utils.ExcelService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class GenerateFileServiceImpl implements GenerateFileService {

    private final ExcelService excelService;
    private final DatesRepository datesRepository;
    private final EmployeeRepository employeeRepository;
    private final DatesFreeRepostiory datesFreeRepostiory;

    public GenerateFileServiceImpl(ExcelService excelService,
                                   DatesRepository datesRepository,
                                   EmployeeRepository employeeRepository,
                                   DatesFreeRepostiory datesFreeRepostiory) {
        this.excelService = excelService;
        this.datesRepository = datesRepository;
        this.employeeRepository = employeeRepository;
        this.datesFreeRepostiory = datesFreeRepostiory;
    }

    @Override
    public ByteArrayInputStream generateExcel(final Long employeeId) throws  NotFoundException {
        List<Dates> datesToExcel = this.datesRepository.findByEmployeeId(employeeId);
        Optional<Employee> employeeOptional = this.employeeRepository.findById(employeeId);
        List<DatesFree> datesFrees = this.datesFreeRepostiory.findAll();
        datesFrees.forEach(date -> {
            Dates dates = new Dates();
            dates.setDate(date.getFreeDate());
            dates.setDateType(date.getDateType());
            datesToExcel.add(dates);
        });

        if(employeeOptional.isPresent()){
            Employee employee = employeeOptional.get();
            GenerateExcelDtoInternal generateExcelDtoInternal = new GenerateExcelDtoInternal();
            generateExcelDtoInternal.setDates(datesToExcel);
            generateExcelDtoInternal.setEmployee(employee);
            generateExcelDtoInternal.setNameToPrint(employee.getName() + " " + employee.getLastName());

            try {
                return this.excelService.generateExcel(generateExcelDtoInternal);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            throw new NotFoundException("Not found");
        }

    }

    @Override
    public void generatePDF(final Long userId) {

    }

}

