package cn.twopair.services;

import cn.twopair.pojo.*;

import java.util.*;

/**
 * @ClassName Services
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/11 下午7:20
 * @Version 1.0
 **/


public interface Services {

    /**
     * @Param: username
     * @Param: inputpassword
     * @Description: 登录校验接口
     * @Return: boolean
     * @Author: 李佳骏
     * @Date: 2022/6/22 上午10:43
     */
    boolean login(String userName, String inputPassword);

    /**
     * @Param: id
     * @Param: name
     * @Param: identityID
     * @Description: 根据传入的数据条件查询员工数据
     * @Return: java.util.Vector<java.util.Vector<java.lang.Object>>
     * @Author: 李佳骏
     * @Date: 2022/6/22 上午10:45
     */
    Vector<Vector<Object>> getEmployeeData(String id, String name, String identityID);

    /**
     * @Param: employee 新增员工实体信息
     * @Description: 新增员工接口
     * @Return: int
     * @Author: 李佳骏
     * @Date: 2022/6/22 上午10:46
     */
    int addOneEmployee(Employee employee);

    /**
     * @Param: employee 修改员工实体信息
     * @Description: 修改员工接口
     * @Return: int
     * @Author: 李佳骏
     * @Date: 2022/6/22 上午10:47
     */
    int mergeOneEmployee(Employee employee);

    /**
     * @Param: selectedIds 删除员工id列表
     * @Description: 删除员工接口
     * @Return: int
     * @Author: 李佳骏
     * @Date: 2022/6/22 上午10:47
     */
    int deleteEmployeeById(List<Integer> selectedIds);

    Vector<Vector<Object>> getCustomerData(String id, String name, String tel);

    int addOneCustomer(Customer customer);

    int mergeOneCustomer(Customer customer);

    int deleteCustomerById(List<Integer> selectedIds);

    Vector<Vector<Object>> getDeskData(String no, Integer seating, String status);

    int addOneDesk(Desk desk);

    int deleteDeskById(List<Integer> selectedIds);

    int mergeOneDesk(Desk desk);

    int mergeOneDeskStatus(Desk desk);


    Vector<Vector<Object>> getCategoryData(String id, String name);

    int addOneCategory(Category category);

    int mergeOneCategory(Category category);

    int deleteCategoryById(List<Integer> selectedIds);

    Vector<Vector<Object>> getFoodData(String id, String name, String status);

    int addOneFood(Food food);

    int mergeOneFood(Food food);

    int deleteFoodById(List<Integer> selectedIds);

    String getFoodIconPathById(Integer id);

    String[] getEmptySeatGreaterThanNumber(Integer number);

    String[] getAllCategoryName();

    Integer getCategoryIdByName(String categoryName);

    Vector<Vector<Object>> getCustomerSelectedSearchFoodData(Food food);

    Vector<Vector<Object>> getBossRecommendFoodData();

    String getOrderNumber();

    Vector<Vector<Object>> selectOrderById(String returnOrderNumber);

    int addOneOrder(Order order);

    int insertMoreOrderItem(ArrayList<OrderItem> orderItemList);

    Vector<Vector<Object>> getOrderData(Order order);

    Vector<Vector<Object>> getOrderItemData(OrderItem orderItem);

    double getRateMoney(String cusotomerId);

    int mergeOrderStatus(Order order);

    ArrayList<Double> getStatisticsData();

}
