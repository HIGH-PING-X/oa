package games.highping.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import games.highping.server.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 员工表 Mapper 接口
 * </p>
 *
 * @author noob
 * @since 2023-11-20
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    IPage<Employee> getAllEmployeeByPage(Page<Employee> page,
                                         @Param("employee") Employee employee,
                                         @Param("beginDateScope") LocalDate[] beginDateScope);

    List<Employee> exportEmployee(Integer id);

    IPage<Employee> getAllEmployeeWithSalary(Page<Employee> page);
}
