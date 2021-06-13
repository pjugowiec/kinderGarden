package com.unit.report.mapper;

import com.report.domain.entity.EmployeeEntity;
import com.report.domain.model.employee.EmployeeForm;
import com.report.mapper.EmployeeMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.helpers.ReportDataGenerator.createEmployeeEntity;
import static com.helpers.ReportDataGenerator.createEmployeeForm;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeMapperTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    @DisplayName("EmployeeForm to EmployeeEntity - should map all fields")
    void employeeFormToEmployeeEntity_ShouldMapAllFields() {
        final EmployeeForm employeeForm = createEmployeeForm();

        final EmployeeEntity employeeEntity = employeeMapper.employeeFormToEmployeeEntity(employeeForm);

        assertEquals(employeeForm.getName(), employeeEntity.getName());
        assertEquals(employeeForm.getLastname(), employeeEntity.getLastname());
        assertEquals(employeeForm.getPosition(), employeeEntity.getPosition());
        assertEquals(employeeForm.getRegularPost(), employeeEntity.getRegularPost());
        assertEquals(employeeForm.getCountOfVacation(), employeeEntity.getCountOfVacation());
        assertEquals(employeeForm.getCountOfChildrenCare(), employeeEntity.getCountOfChildrenCare());
        assertNull(employeeEntity.getId());
    }

    @Test
    @DisplayName("Update EmployeeEntity by EmployeeForm - should update fields")
    void updateEmployeeEntityByEmployeeForm_ShouldUpdateFields() {
        final EmployeeForm employeeForm = createEmployeeForm();
        EmployeeEntity employeeEntity = createEmployeeEntity();

        employeeEntity = employeeMapper.updateEmployeeEntityByEmployeeForm(employeeEntity, employeeForm);

        assertNotEquals(employeeForm.getId(), employeeEntity.getId());
        assertEquals(employeeForm.getName(), employeeEntity.getName());
        assertEquals(employeeForm.getLastname(), employeeEntity.getLastname());
        assertEquals(employeeForm.getPosition(), employeeEntity.getPosition());
        assertEquals(employeeForm.getRegularPost(), employeeEntity.getRegularPost());
        assertEquals(employeeForm.getCountOfVacation(), employeeEntity.getCountOfVacation());
        assertEquals(employeeForm.getCountOfChildrenCare(), employeeEntity.getCountOfChildrenCare());
    }
}
