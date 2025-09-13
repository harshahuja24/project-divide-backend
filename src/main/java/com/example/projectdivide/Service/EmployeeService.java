package com.example.projectdivide.Service;

import com.example.projectdivide.DTO.EmployeeDTO;
import com.example.projectdivide.Entity.Employee;
import com.example.projectdivide.Repository.EmployeeRepository;
import com.example.projectdivide.mapper.EmployeeDTOMapper;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService  {

    @Autowired private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;

    @Autowired
    EmployeeDTOMapper employeeDTOMapper;

    @Autowired
    EmployeeRepository employeeRepository;


    public void createEmployee(EmployeeDTO employeeDTO) {

            Employee employee = employeeDTOMapper.toEntity(employeeDTO);
        System.out.println(employee.getEmail());
        String password = String.valueOf((int)(Math.random() * 100));
            employee.setPassword(password);
            sendSimpleMail(employee);
            employeeRepository.save(employee);
//            return employeeRepository.findByEid(employeeDTO.getEid());

    }

    public List<EmployeeDTO> getAllEmployees() {

            List<Employee> employeeList = employeeRepository.findAll();
            for(Employee emp:employeeList){
                System.out.println("emp"+emp);
            }
            return employeeList.stream()
                    .map((e)->employeeDTOMapper.toDTO(e))
                    .toList();

    }

    public EmployeeDTO getEmployeeById(int id) {

        Optional<Employee> employee = employeeRepository.findByEid(id);

        if(employee.isPresent()){

            EmployeeDTO employeeDTO = employeeDTOMapper.toDTO(employee.get());
            return employeeDTO;

        }

        throw new RuntimeException("Run time error in Employee service ");

    }

    public void setSprintIdForEmployees(Object body){
//       sprintId
//               employeesId []
//
//               update employees where emploeey.eid in emplyessId sprint_id  = sprintId;

        Map<String, Object> bodyMap = (Map<String, Object>) body;

        // Extract and cast sprintId
        Long sprintId = Long.valueOf(bodyMap.get("sprintId").toString());

        // Extract and cast employeeIds
        List<Integer> employeeIdsRaw = (List<Integer>) bodyMap.get("employeeIds");
        List<Long> employeeIds = employeeIdsRaw.stream()
                .map(Long::valueOf)
                .collect(Collectors.toList());

        // Call repository method
        employeeRepository.updateSprintIdForEmployees(sprintId, employeeIds);
    }


    public String sendSimpleMail(Employee employee) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        System.out.println("Inside Simple Mail "+employee.getEmail());
        try{
            mimeMessageHelper.setTo(employee.getEmail());
            mimeMessageHelper.setSubject("Login Creds");
            mimeMessageHelper.setText("Hello "+employee.getEname()+" Welcome On Board, here are your login creds email:"+employee.getEmail()+" and password: ? "+employee.getPassword());

        } catch (Exception e) {
            return "Email Not Sent";
        }
        javaMailSender.send(mimeMessage);
        System.out.println("Mail Sent ");
        return "";
    }




}
