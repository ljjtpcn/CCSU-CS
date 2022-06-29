package cn.twopair.mappers;

import cn.twopair.pojo.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName EmployeeMapper
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/12 下午2:31
 * @Version 1.0
 **/
public interface CustomerMapper {

    /**
     * @Param:
     * @Description: 根据条件动态返回员工数据
     * @Return: java.util.List<cn.twopair.pojo.Employee>
     * @Author: 李佳骏
     * @Date: 2022/6/12 下午2:51
     */
    List<Customer> getCustomerData(@Param("id") String id,
                                   @Param("name") String name,
                                   @Param("tel") String tel);

    int addOneCustomer(@Param("name") String name,
                       @Param("sex") String sex,
                       @Param("tel") String tel,
                       @Param("base") Double base);

    int mergeOneCustomer(@Param("id") Integer id,
                         @Param("name") String name,
                         @Param("sex") String sex,
                         @Param("tel") String tel,
                         @Param("base") Double base);

    int deleteCustomerById(@Param("ids") String ids);

    int selectIdByTelphone(@Param("tel") String tel);
}
