package com.employees.crud.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employees.crud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
