package games.highping.server.service.impl;

import games.highping.server.pojo.Salary;
import games.highping.server.mapper.SalaryMapper;
import games.highping.server.service.ISalaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 工资表 服务实现类
 * </p>
 *
 * @author noob
 * @since 2023-11-27
 */
@Service
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements ISalaryService {

}
