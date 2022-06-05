package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	@Override
	public Employee saveEmployee(Employee employee) {
		System.out.println(employee);
		return employeeRepository.save(employee);
	}
	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}
	@Override
	public Employee getEmployeeById(int id) {
		Optional <Employee> employee= employeeRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}
		else {
			throw new ResourceNotFoundException("Employee", "Id", id); 
		}
	}
	@Override
	public Employee updateEmployee(Employee employee, int id) {
		// TODO Auto-generated method stub
		Employee existingEmployee=employeeRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Employee", "Id", id)); 
		
		existingEmployee.setName(employee.getName());
		existingEmployee.setSalary(employee.getSalary());
		employeeRepository.save(existingEmployee);
		return existingEmployee;
		
	}
	@Override
	public Employee deleteEmployee(Employee employee, int id) {
		Employee existingEmployee=employeeRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Employee", "Id", id)); 
		employeeRepository.delete(existingEmployee);
		return null;
	}

}
