package com.example.model;

public class TaxDeductionDTO {
	 private String employeeId;
	    private String firstName;
	    private String lastName;
	    private double yearlySalary;
	    private double taxDeduction;

	    public TaxDeductionDTO(String employeeId, String firstName, String lastName, double yearlySalary, double taxDeduction) {
	        this.employeeId = employeeId;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.yearlySalary = yearlySalary;
	        this.taxDeduction = taxDeduction;
	    }

		public String getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public double getYearlySalary() {
			return yearlySalary;
		}

		public void setYearlySalary(double yearlySalary) {
			this.yearlySalary = yearlySalary;
		}

		public double getTaxDeduction() {
			return taxDeduction;
		}

		public void setTaxDeduction(double taxDeduction) {
			this.taxDeduction = taxDeduction;
		}

	    
}
