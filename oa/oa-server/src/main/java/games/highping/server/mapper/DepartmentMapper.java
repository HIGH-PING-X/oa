package games.highping.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import games.highping.server.pojo.Department;

import java.util.List;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author noob
 * @since 2023-11-20
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> getAllDepartments(Integer parentId);

    void addDep(Department dep);

    void deleteDep(Department dep);
}
