package games.highping.server.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import games.highping.server.pojo.Employee;
import games.highping.server.pojo.RespBean;
import games.highping.server.pojo.RespPageBean;
import games.highping.server.pojo.Salary;
import games.highping.server.service.IEmployeeService;
import games.highping.server.service.ISalaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary/sobcfg")
public class SalarySobCfgController {

    @Autowired
    private ISalaryService salaryService;
    @Autowired
    private IEmployeeService employeeService;

    @ApiOperation(value = "获取所有工资账套")
    @GetMapping("/salaries")
    public List<Salary> getAllSalarySobCfgs(){
        return salaryService.list();
    }

    @ApiOperation(value = "获取所有员工套账")
    @GetMapping("/")
    public RespPageBean getAllEmployeeWithSalary(@RequestParam(defaultValue = "1") Integer currentPage,
                                                 @RequestParam(defaultValue = "10") Integer size){
        return employeeService.getAllEmployeeWithSalary(currentPage,size);
    }

    @ApiOperation(value = "更新员工套账")
    @PutMapping("/")
    public RespBean updateEmployeeSalary(Integer eid,Integer sid){
        if (employeeService.update(new UpdateWrapper<Employee>().set("salary_id",sid).eq("id",eid))) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

}
