package games.highping.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import games.highping.server.pojo.Employee;
import games.highping.server.pojo.RespBean;
import games.highping.server.pojo.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 员工表 服务类
 * </p>
 *
 * @author noob
 * @since 2023-11-20
 */
public interface IEmployeeService extends IService<Employee> {

    // 获取所有员工(分页)
    RespPageBean getAllEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    // 获取最大工号
    RespBean maxWorkID();

    // 添加员工
    RespBean addEmp(Employee employee);

    // 导出员工数据
    List<Employee> exportEmployee(Integer id);

    // 获取所有员工账套
    RespPageBean getAllEmployeeWithSalary(Integer currentPage, Integer size);
}
