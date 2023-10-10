package com.example.employeeservice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeerepo.EmployeeRepository;
import com.example.model.Employee;
@Service
public class EmployeeService {
	 
	  private final EmployeeRepository employeeRepository;

	    @Autowired
	    public EmployeeService(EmployeeRepository employeeRepository) {
	        this.employeeRepository = employeeRepository;
	    }

	    @Transactional
	    public Employee saveEmployee(Employee employee) {
	        // Validate data (add your validation logic here)
	        if (!isValidEmployee(employee)) {
	            throw new IllegalArgumentException("Invalid employee data");
	        }

	        // Calculate total salary considering DOJ
	        BigDecimal totalSalary = calculateTotalSalary(employee);

	        // Set the yearly salary for the employee
	        employee.setSalary(totalSalary);

	        // Save the employee
	        return employeeRepository.save(employee);
	    }

	    public List<Employee> getAllEmployees() {
	        return employeeRepository.findAll();
	    }

	    private boolean isValidEmployee(Employee employee) {
	        // Implement your data validation logic here
	        // For example, check if fields are not null and meet your criteria
	        return employee != null &&
	               employee.getEmployeeId() != null &&
	               employee.getFirstName() != null &&
	               employee.getLastName() != null &&
	               employee.getEmail() != null &&
	               employee.getPhoneNumbers() != null &&
	               !employee.getPhoneNumbers().isEmpty() &&
	               employee.getDoj() != null &&
	               employee.getSalary() != null &&
	               employee.getSalary().compareTo(BigDecimal.ZERO) >= 0;
	    }

	    private BigDecimal calculateTotalSalary(Employee employee) {
	        LocalDate currentDate = LocalDate.now();
	        LocalDate doj = employee.getDoj();

	        // Calculate the number of months worked in the current financial year
	        int monthsWorked = 12 - doj.getMonthValue() + 1;

	        // If the employee joined in the current month, consider only the current month's salary
	        if (doj.getYear() == currentDate.getYear() && doj.getMonth() == currentDate.getMonth()) {
	            monthsWorked = 1;
	        }

	        // Calculate prorated salary for the months worked
	        BigDecimal proratedSalary = employee.getSalary()
	                .multiply(BigDecimal.valueOf(monthsWorked))
	                .divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);

	        return proratedSalary.multiply(BigDecimal.valueOf(employee.getPhoneNumbers().size()));
	    }

}
