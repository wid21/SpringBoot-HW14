package com.example.springboothw14.EmployeeController;

import com.example.springboothw14.ApiResponse.ApiResponse;
import com.example.springboothw14.Model.Employee;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    ArrayList<Employee> employees=new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Employee>getEmployees(){
        return employees;

    }

    @PostMapping("/add")
    public ResponseEntity addEmployee(@Valid @RequestBody Employee employee, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        employees.add(employee);
        return ResponseEntity.status(200).body(new ApiResponse("employee added"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateEmployee(@PathVariable int index , @RequestBody@Valid Employee employee,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));}
         employees.set(index,employee);
        return ResponseEntity.status(200).body(new ApiResponse("employee added"));

    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEmployee(@PathVariable int index ){
        employees.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("employee deleted"));
    }

    @PutMapping("/apply/{index}")
    public ResponseEntity apply(@PathVariable int index )  {
        if (employees.get(index).isOnLeave()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Employee is already on leave"));
        }
        if (employees.get(index).getAnnualLeave() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Employee has no more annual leave days"));
        }


        employees.get(index).setOnLeave(true);
        employees.get(index).setAnnualLeave(employees.get(index).getAnnualLeave() - 1);
        return ResponseEntity.status(200).body(new ApiResponse( " you have " + employees.get(index).getAnnualLeave()+" anual leave days to apply"));
    }
}
