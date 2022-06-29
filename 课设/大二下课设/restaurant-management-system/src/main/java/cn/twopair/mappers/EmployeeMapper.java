package cn.twopair.mappers;

import cn.twopair.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName EmployeeMapper
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/12 下午2:31
 * @Version 1.0
 **/
public interface EmployeeMapper {

    /**
     * @Param:
     * @Description: 根据条件动态返回员工数据
     * @Return: java.util.List<cn.twopair.pojo.Employee>
     * @Author: 李佳骏
     * @Date: 2022/6/12 下午2:51
     */
    List<Employee> getEmployeeData(@Param("id") String id,
                                   @Param("name") String name,
                                   @Param("identityID") String identityID);

    int addOneEmployee(@Param("name") String name,
                       @Param("sex") String sex,
                       @Param("identityID") String identityID,
                       @Param("tel") String tel,
                       @Param("status") String status);

    int mergeOneEmployee(@Param("id") Integer id,
                         @Param("name") String name,
                         @Param("sex") String sex,
                         @Param("identityID") String identityId,
                         @Param("tel") String tel,
                         @Param("status") String status);

    int deleteEmployeeById(@Param("ids") String ids);

    int selectIdByIdentityID(@Param("identityID") String identityID);
}
