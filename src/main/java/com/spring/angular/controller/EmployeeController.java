//package com.spring.angular.controller;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.spring.angular.exception.ResourceNotFoundException;
//import com.spring.angular.models.Employee;
//import com.spring.angular.repository.EmployerRepositoy;
//
//@RestController
//@RequestMapping("/api/v1/")
//public class EmployeeController {
//
//	@Autowired
//	private EmployerRepositoy employerRepositoy;
//	
////	@PostMapping("/save")
////	public ResponseEntity<?> saveEmployee(@RequestParam String firstName,
////			@RequestParam String lastName, @RequestParam String emailId
////			){
////		Employee employee = new Employee(firstName,lastName,emailId);
////		
////		employerRepositoy.save(employee);
////		
////		return ResponseEntity.ok(Map.of("message","enregistrer avec succes"));
////		
////	}
//	
//	@GetMapping("/test")
//	public String test() {
//		return "ttest";
//	}
//	
//	@CrossOrigin(origins = "*")
//	@PostMapping("/save")
//	public Employee employee(@RequestBody Employee employee
//			){
//		return employerRepositoy.save(employee);
//		
//		
//		
//	}
//	
//	//recherche d'un employe par son identifiant
//	@CrossOrigin(origins = "*")
//	@GetMapping("/findEmployee/{id}")
//	public Employee employe(@PathVariable Long id) {
//		return employerRepositoy.findById(id).get();
//	}
//	
//	//liste de tout les employees
//	@CrossOrigin(origins = "*")
//	@GetMapping("/employees")
//	public List<Employee> getAllEmployees(){
//		return employerRepositoy.findAll();
//	}
//	//get employee by id rest api
//	@CrossOrigin(origins = "*")
//	@GetMapping("/employee/{id}")
//	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
//		Employee employee = employerRepositoy.findById(
//				id).orElseThrow(
//						() -> new ResourceNotFoundException("Employee n'existe pas avec ce identifiant:" + id)
//						);
//		return ResponseEntity.ok(employee);
//	}
//	
//	//update employee rest api
//	@CrossOrigin(origins = "*")
//	@PutMapping("/updateEmployee/{id}")
//	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
//		Employee existinEmployee = employerRepositoy.findById(
//				id).orElseThrow(
//						() -> new ResourceNotFoundException("Employee n'existe pas avec ce identifiant:" + id)
//						);
//		existinEmployee.setFirstName(employee.getFirstName());
//		existinEmployee.setLastName(employee.getLastName());
//		existinEmployee.setEmailId(employee.getEmailId());
//		
//		Employee updatEmployee = employerRepositoy.save(existinEmployee);
//		return ResponseEntity.ok(updatEmployee);
//	}
//	
//	//delete employee
//	@CrossOrigin(origins = "*")
//	@DeleteMapping("/deleteEmployee/{id}")
//	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
//		Employee existinEmployee = employerRepositoy.findById(
//				id).orElseThrow(
//						() -> new ResourceNotFoundException("Employee n'existe pas avec ce identifiant:" + id)
//						);
//		
//		employerRepositoy.delete(existinEmployee);
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("deleted", Boolean.TRUE);
//		return ResponseEntity.ok(response);
//	}
//}
