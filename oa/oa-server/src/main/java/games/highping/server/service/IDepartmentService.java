package games.highping.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import games.highping.server.pojo.Department;
import games.highping.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author noob
 * @since 2023-11-20
 */
public interface IDepartmentService extends IService<Department> {

    // 获取所有部门
    List<Department> getAllDepartments();

    // 添加部门
    RespBean addDep(Department dep);

    // 删除部门
    RespBean deleteDep(Integer id);
}
