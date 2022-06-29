package cn.twopair.services.impl;

import cn.twopair.dao.DaoUtil;
import cn.twopair.mappers.*;
import cn.twopair.pojo.*;
import cn.twopair.services.Services;
import cn.twopair.utils.CheckUtil;

import org.apache.ibatis.session.SqlSession;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName ServicesImpl
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/11 下午7:22
 * @Version 1.0
 **/
public class ServicesImpl implements Services {

    @Override
    public boolean login(String userName, String inputPassword) {
        try (SqlSession session = DaoUtil.getSqlSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            String password = mapper.getPasswordByUsername(userName);
            session.close();
            return password.equals(inputPassword);
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public Vector<Vector<Object>> getEmployeeData(String id, String name, String identityID) {
        try (SqlSession session = DaoUtil.getSqlSession()) {
            Vector<Vector<Object>> data = new Vector<>();
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            List<Employee> employeeData = mapper.getEmployeeData(id, name, identityID);

            for (Employee employee : employeeData) {
                Vector<Object> row = new Vector<>();
                row.add(employee.getId());
                row.add(employee.getName());
                row.add(employee.getSex());
                row.add(employee.getIdentityId());
                row.add(employee.getTel());
                row.add(employee.getStatus());
                data.add(row);
            }
            session.close();
            return data;

        } catch (Exception exception) {
            return new Vector<>();
        }
    }

    @Override
    public int addOneEmployee(Employee employee) {
        //检查身份证号是否为空
        if (CheckUtil.isEmpty(employee.getIdentityId())) {
            return 0;
        }
        SqlSession session = DaoUtil.getSqlSession();
        try {

            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            List<Employee> employeeData = mapper.getEmployeeData(null, null, employee.getIdentityId());

            if (employeeData.size() != 0) {
                session.close();
                return 0;
            } else {
                int count = mapper.addOneEmployee(employee.getName(), employee.getSex(), employee.getIdentityId(), employee.getTel(), employee.getStatus());
                session.commit();
                session.close();
                return count;
            }

        } catch (Exception exception) {
            session.rollback();
            session.close();
            return 0;
        }
    }

    @Override
    public int mergeOneEmployee(Employee employee) {
        //检查身份证号是否为空
        if (CheckUtil.isEmpty(employee.getIdentityId())) {
            return 0;
        }
        SqlSession session = DaoUtil.getSqlSession();
        try {

            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            List<Employee> employeeData = mapper.getEmployeeData(null, null, employee.getIdentityId());

            //检查修改的身份证号码是否重复 排除自己身份证号
            if (employeeData.size() != 0 &&
                    mapper.selectIdByIdentityID(employee.getIdentityId()) != employee.getId()) {
                session.close();
                return 0;
            }

            int count = mapper.mergeOneEmployee(
                    employee.getId(),
                    employee.getName(),
                    employee.getSex(),
                    employee.getIdentityId(),
                    employee.getTel(),
                    employee.getStatus()
            );
            session.commit();
            session.close();
            return count;

        } catch (Exception exception) {
            session.rollback();
            session.close();
            return 0;
        }
    }

    @Override
    public int deleteEmployeeById(List<Integer> selectedIds) {
        SqlSession session = DaoUtil.getSqlSession();
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            StringBuilder sqlIds = new StringBuilder();
            for (int i = 0; i < selectedIds.size() - 1; i++) {
                sqlIds.append(selectedIds.get(i)).append(",");
            }
            sqlIds.append(selectedIds.get(selectedIds.size() - 1));

            int count = mapper.deleteEmployeeById(String.valueOf(sqlIds));
            session.commit();
            session.close();
            return count;
        } catch (Exception exception) {
            session.rollback();
            session.close();
            return 0;
        }
    }

    @Override
    public Vector<Vector<Object>> getCustomerData(String id, String name, String tel) {
        try (SqlSession session = DaoUtil.getSqlSession()) {
            Vector<Vector<Object>> data = new Vector<>();
            CustomerMapper mapper = session.getMapper(CustomerMapper.class);
            List<Customer> customerData = mapper.getCustomerData(id, name, tel);

            for (Customer customer : customerData) {
                Vector<Object> row = new Vector<>();
                row.add(customer.getId());
                row.add(customer.getName());
                row.add(customer.getSex());
                row.add(customer.getTel());
                row.add(customer.getBase());
                data.add(row);
            }
            session.close();
            return data;

        } catch (Exception exception) {
            return new Vector<>();
        }
    }


    @Override
    public int addOneCustomer(Customer customer) {
        SqlSession session = DaoUtil.getSqlSession();
        try {

            //检查手机号是否为空
            if (CheckUtil.isEmpty(customer.getTel())) {
                session.close();
                return 0;
            }

            CustomerMapper mapper = session.getMapper(CustomerMapper.class);
            List<Customer> customerData = mapper.getCustomerData(null, null, customer.getTel());

            if (customerData.size() != 0) {
                session.close();
                return 0;
            } else {
                int count = mapper.addOneCustomer(customer.getName(), customer.getSex(), customer.getTel(), customer.getBase());
                session.commit();
                session.close();
                return count;
            }

        } catch (Exception exception) {
            session.rollback();
            session.close();
            return 0;
        }
    }

    @Override
    public int mergeOneCustomer(Customer customer) {
        SqlSession session = DaoUtil.getSqlSession();
        try {
            //检查手机号是否为空
            if (CheckUtil.isEmpty(customer.getTel())) {
                session.close();
                return 0;
            }
            CustomerMapper mapper = session.getMapper(CustomerMapper.class);
            List<Customer> customerData = mapper.getCustomerData(null, null, customer.getTel());

            //检查修改的手机号码是否重复 排除自己手机号
            if (customerData.size() != 0 &&
                    mapper.selectIdByTelphone(customer.getTel()) != customer.getId()) {
                session.close();
                return 0;
            }

            int count = mapper.mergeOneCustomer(
                    customer.getId(),
                    customer.getName(),
                    customer.getSex(),
                    customer.getTel(),
                    customer.getBase()
            );
            session.commit();
            session.close();
            return count;

        } catch (Exception exception) {
            session.rollback();
            session.close();
            return 0;
        }
    }

    @Override
    public int deleteCustomerById(List<Integer> selectedIds) {
        SqlSession session = DaoUtil.getSqlSession();
        try {
            CustomerMapper mapper = session.getMapper(CustomerMapper.class);
            StringBuilder sqlIds = new StringBuilder();
            for (int i = 0; i < selectedIds.size() - 1; i++) {
                sqlIds.append(selectedIds.get(i)).append(",");
            }
            sqlIds.append(selectedIds.get(selectedIds.size() - 1));

            int count = mapper.deleteCustomerById(String.valueOf(sqlIds));
            session.commit();
            session.close();
            return count;
        } catch (Exception exception) {
            session.rollback();
            session.close();
            return 0;
        }
    }

    @Override
    public Vector<Vector<Object>> getDeskData(String no, Integer seating, String status) {
        try (SqlSession session = DaoUtil.getSqlSession()) {
            Vector<Vector<Object>> data = new Vector<>();
            DeskMapper mapper = session.getMapper(DeskMapper.class);
            List<Desk> employeeData = mapper.getDeskData(no, seating, status);

            for (Desk desk : employeeData) {
                Vector<Object> row = new Vector<>();
                row.add(desk.getId());
                row.add(desk.getNo());
                row.add(desk.getSeating());
                row.add(desk.getStatus());
                data.add(row);
            }
            session.close();
            return data;

        } catch (Exception exception) {
            return new Vector<>();
        }
    }

    @Override
    public int addOneDesk(Desk desk) {
        SqlSession session = DaoUtil.getSqlSession();
        try {

            //检查合法性
            if (CheckUtil.isEmpty(desk.getNo(), String.valueOf(desk.getSeating())) || desk.getSeating() == 0) {
                session.close();
                return 0;
            }
            DeskMapper mapper = session.getMapper(DeskMapper.class);
            List<Desk> deskData = mapper.getDeskData(desk.getNo(), null, null);
            if (deskData.size() != 0) {
                session.close();
                return 0;
            }

            int count = mapper.addOneDesk(desk.getNo(),
                    desk.getSeating(),
                    desk.getStatus()
            );
            session.commit();
            session.close();
            return count;


        } catch (Exception exception) {
            session.rollback();
            session.close();
            return 0;
        }
    }

    @Override
    public int deleteDeskById(List<Integer> selectedIds) {
        SqlSession session = DaoUtil.getSqlSession();
        try {
            DeskMapper mapper = session.getMapper(DeskMapper.class);
            StringBuilder sqlIds = new StringBuilder();
            for (int i = 0; i < selectedIds.size() - 1; i++) {
                sqlIds.append(selectedIds.get(i)).append(",");
            }
            sqlIds.append(selectedIds.get(selectedIds.size() - 1));

            int count = mapper.deleteDeskById(String.valueOf(sqlIds));
            session.commit();
            session.close();
            return count;
        } catch (Exception exception) {
            session.rollback();
            session.close();
            return 0;
        }
    }

    @Override
    public int mergeOneDesk(Desk desk) {
        SqlSession session = DaoUtil.getSqlSession();
        try {
            //检查餐台编号， 座位数量, 状态是否为空
            if (CheckUtil.isEmpty(desk.getNo(), String.valueOf(desk.getSeating()), desk.getStatus())) {
                session.close();
                return 0;
            }
            DeskMapper mapper = session.getMapper(DeskMapper.class);
            List<Desk> deskData = mapper.getDeskData(null, null, desk.getNo());

            //检查修改的手机号码是否重复 排除自己手机号
            if (deskData.size() != 0 &&
                    mapper.selectIdByNo(desk.getNo()).equals(desk.getId())) {
                session.close();
                return 0;
            }


            int count = mapper.mergeOneDesk(
                    desk.getId(),
                    desk.getNo(),
                    desk.getSeating(),
                    desk.getStatus()
            );
            session.commit();
            session.close();
            return count;

        } catch (Exception exception) {
            session.rollback();
            session.close();
            return 0;
        }
    }

    @Override
    public int mergeOneDeskStatus(Desk desk) {
        SqlSession session = DaoUtil.getSqlSession();
        int count;
        try {
            DeskMapper deskMapper = session.getMapper(DeskMapper.class);
            count = deskMapper.mergeOneDeskStatus(desk);

            session.commit();
            session.close();

        } catch (Exception exception) {
            session.rollback();
            session.close();
            count = 0;
        }
        return count;
    }


    @Override
    public Vector<Vector<Object>> getCategoryData(String id, String name) {
        try (SqlSession session = DaoUtil.getSqlSession()) {
            Vector<Vector<Object>> data = new Vector<>();
            CategoryMapper mapper = session.getMapper(CategoryMapper.class);
            List<Category> employeeData = mapper.getCategoryData(id, name);

            for (Category category : employeeData) {
                Vector<Object> row = new Vector<>();
                row.add(category.getId());
                row.add(category.getName());
                row.add(category.getDescribe());
                data.add(row);
            }
            session.close();
            return data;

        } catch (Exception exception) {
            return new Vector<>();
        }
    }


    @Override
    public int addOneCategory(Category category) {
        SqlSession session = DaoUtil.getSqlSession();
        try {

            //检查合法性(菜品类别名不能为空)
            if (CheckUtil.isEmpty(category.getName())) {
                session.close();
                return 0;
            }
            CategoryMapper mapper = session.getMapper(CategoryMapper.class);
            List<Category> categoryData = mapper.getCategoryData(null, category.getName());
            if (categoryData.size() != 0) {
                session.close();
                return 0;
            }

            int count = mapper.addOneCategory(category.getName(),
                    category.getDescribe()
            );
            session.commit();
            session.close();
            return count;


        } catch (Exception exception) {
            session.rollback();
            session.close();
            return 0;
        }
    }

    @Override
    public int mergeOneCategory(Category category) {
        SqlSession session = DaoUtil.getSqlSession();
        try {
            //检查菜品分类
            if (CheckUtil.isEmpty(category.getName())) {
                session.close();
                return 0;
            }
            CategoryMapper mapper = session.getMapper(CategoryMapper.class);
            List<Category> categoryData = mapper.getCategoryData(null, category.getName());

            //检查修改的名称是否重复 排除自己名称
            if (categoryData.size() != 0 &&
                    !String.valueOf(mapper.selectIdByName(category.getName())).equals(String.valueOf(category.getId()))) {
                session.close();
                return 0;
            }


            int count = mapper.mergeOneCategory(
                    category.getId(),
                    category.getName(),
                    category.getDescribe()
            );
            session.commit();
            session.close();
            return count;

        } catch (Exception exception) {
            session.rollback();
            session.close();
            return 0;
        }
    }

    @Override
    public int deleteCategoryById(List<Integer> selectedIds) {
        SqlSession session = DaoUtil.getSqlSession();
        try {
            CategoryMapper mapper = session.getMapper(CategoryMapper.class);
            StringBuilder sqlIds = new StringBuilder();
            for (int i = 0; i < selectedIds.size() - 1; i++) {
                sqlIds.append(selectedIds.get(i)).append(",");
            }
            sqlIds.append(selectedIds.get(selectedIds.size() - 1));

            int count = mapper.deleteCategoryById(String.valueOf(sqlIds));
            session.commit();
            session.close();

            return count;
        } catch (Exception exception) {
            session.rollback();
            session.close();
            return 0;
        }
    }

    @Override
    public Vector<Vector<Object>> getFoodData(String id, String name, String status) {
        try (SqlSession session = DaoUtil.getSqlSession()) {
            Vector<Vector<Object>> data = new Vector<>();
            FoodMapper mapper = session.getMapper(FoodMapper.class);
            List<Food> foodData = mapper.getFoodData(id, name, status, null);

            for (Food food : foodData) {
                Vector<Object> row = new Vector<>();
                row.add(food.getId());
                row.add(food.getName());
                row.add(food.getCategoryId());
                row.add(food.getPrice());
                row.add(food.getDescribe());
                row.add(food.getStatus());
                row.add(food.getSum());
                data.add(row);
            }
            session.close();
            return data;

        } catch (Exception exception) {
            return new Vector<>();
        }
    }

    @Override
    public int addOneFood(Food food) {
        SqlSession session = DaoUtil.getSqlSession();
        try {

            //检查合法性(菜品名不能为空且销量不能小于0)
            if (CheckUtil.isEmpty(food.getName()) || food.getSum() < 0) {
                session.close();
                return 0;
            }

            FoodMapper foodmapper = session.getMapper(FoodMapper.class);
            CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
            if (categoryMapper.checkIdExist(food.getCategoryId()) <= 0) {
                session.close();
                return 0;
            }
            int count = foodmapper.addOneFood(food);
            if (count > 0) {
                session.commit();
                session.close();
                return count;
            }
            session.close();
            return 0;

        } catch (Exception exception) {
            session.rollback();
            session.close();
            return 0;
        }

    }

    @Override
    public int mergeOneFood(Food food) {
        SqlSession session = DaoUtil.getSqlSession();
        try {
            //检查菜品名称及销量
            if (CheckUtil.isEmpty(food.getName()) || food.getSum() < 0) {
                session.close();
                return 0;
            }

            FoodMapper foodmapper = session.getMapper(FoodMapper.class);
            CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
            if (categoryMapper.checkIdExist(food.getCategoryId()) <= 0) {
                session.close();
                return 0;
            }

            int count = foodmapper.mergeOneFood(food);
            if (count > 0) {
                session.commit();
                session.close();
                return count;
            }
            session.close();
            return 0;

        } catch (Exception exception) {
            session.rollback();
            session.close();
            return 0;
        }
    }

    @Override
    public int deleteFoodById(List<Integer> selectedIds) {
        SqlSession session = DaoUtil.getSqlSession();
        try {
            FoodMapper mapper = session.getMapper(FoodMapper.class);
            StringBuilder sqlIds = new StringBuilder();
            for (int i = 0; i < selectedIds.size() - 1; i++) {
                sqlIds.append(selectedIds.get(i)).append(",");
            }
            sqlIds.append(selectedIds.get(selectedIds.size() - 1));

            int count = mapper.deleteFoodById(String.valueOf(sqlIds));
            session.commit();
            session.close();
            return count;
        } catch (Exception exception) {
            session.rollback();
            session.close();
            return 0;
        }
    }

    @Override
    public String getFoodIconPathById(Integer id) {
        SqlSession session = DaoUtil.getSqlSession();
        FoodMapper mapper = session.getMapper(FoodMapper.class);
        String foodIconPath = mapper.getFoodIconPathById(id);
        session.close();
        return foodIconPath;
    }

    @Override
    public String[] getEmptySeatGreaterThanNumber(Integer number) {
        SqlSession session = DaoUtil.getSqlSession();
        DeskMapper mapper = session.getMapper(DeskMapper.class);
        List<Desk> deskInfo = mapper.getDeskData(null, number, "空闲");


        ArrayList<String> deskIdsList = new ArrayList<>();
        for (Desk desk : deskInfo) {
            deskIdsList.add(String.valueOf(desk.getId()));
        }
        String[] emptySeatGreaterThanNumber = deskIdsList.toArray(new String[deskIdsList.size()]);
        session.close();
        return emptySeatGreaterThanNumber;
    }

    @Override
    public String[] getAllCategoryName() {
        SqlSession session = DaoUtil.getSqlSession();
        CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
        List<Category> categoryData = categoryMapper.getCategoryData("", null);

        ArrayList<String> categoryNamesList = new ArrayList<>();
        for (Category data : categoryData) {
            categoryNamesList.add(data.getName());
        }
        String[] categoryName = categoryNamesList.toArray(new String[categoryNamesList.size()]);
        session.close();
        return categoryName;
    }

    @Override
    public Integer getCategoryIdByName(String categoryName) {
        SqlSession session = DaoUtil.getSqlSession();
        CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
        Integer categoryId = categoryMapper.getCategoryIdByName(categoryName);
        session.close();
        return categoryId;
    }

    @Override
    public Vector<Vector<Object>> getCustomerSelectedSearchFoodData(Food food) {
        SqlSession session = DaoUtil.getSqlSession();
        FoodMapper foodMapper = session.getMapper(FoodMapper.class);
        List<Food> foodData = foodMapper.getFoodData(null, food.getName(), "上架中", String.valueOf(food.getCategoryId()));

        Vector<Vector<Object>> returnData = new Vector<>();
        for (Food item : foodData) {
            Vector<Object> data = new Vector<>();
            data.add(item.getId());
            data.add(item.getName());
            data.add(item.getPrice());
            data.add(item.getDescribe());
            data.add(item.getSum());
            returnData.add(data);
        }
        session.close();
        return returnData;
    }

    @Override
    public Vector<Vector<Object>> getBossRecommendFoodData() {
        SqlSession session = DaoUtil.getSqlSession();
        FoodMapper foodMapper = session.getMapper(FoodMapper.class);

        List<Food> foodData = foodMapper.getBossRecommendFoodData();
        Vector<Vector<Object>> returnData = new Vector<>();
        for (Food item : foodData) {
            Vector<Object> data = new Vector<>();
            data.add(item.getId());
            data.add(item.getName());
            data.add(item.getPrice());
            data.add(item.getDescribe());
            data.add(item.getSum());
            returnData.add(data);
        }
        session.close();
        return returnData;
    }


    @Override
    public Vector<Vector<Object>> selectOrderById(String returnOrderNumber) {
        SqlSession session = DaoUtil.getSqlSession();
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        List<Order> orderData = orderMapper.selectOrderById(returnOrderNumber);

        Vector<Vector<Object>> returnData = new Vector<>();
        for (Order order : orderData) {
            Vector<Object> data = new Vector<>();


            returnData.add(data);
        }
        session.close();
        return returnData;
    }

    @Override
    public String getOrderNumber() {

        //生成当期日期
        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        //生成八位随机数
        Random rand = new Random();//生成随机数
        StringBuilder randomNumber = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            randomNumber.append(rand.nextInt(10));//生成8位数字
        }
        String returnOrderNumber = timeStamp + randomNumber;
        Services services = new ServicesImpl();
        if (services.selectOrderById(returnOrderNumber).size() != 0) {
            return getOrderNumber();
        } else {
            return returnOrderNumber;
        }
    }

    @Override
    public int addOneOrder(Order order) {
        SqlSession session = DaoUtil.getSqlSession();
        int count;
        try {
            OrderMapper orderMapper = session.getMapper(OrderMapper.class);
            count = orderMapper.insertOrder(order);
            session.commit();
            session.close();
        } catch (Exception exception) {
            count = 0;
            session.rollback();
        }
        return count;
    }

    @Override
    public int insertMoreOrderItem(ArrayList<OrderItem> orderItemList) {
        SqlSession session = DaoUtil.getSqlSession();
        OrderItemMapper orderItemMapper = session.getMapper(OrderItemMapper.class);
        int count = 0;

        try {
            for (OrderItem item : orderItemList) {
                count += orderItemMapper.insertOneOrderItem(item);
            }

            if (count < orderItemList.size()) {
                session.rollback();
                return -1; // 保证返回值小于订单项
            }
            session.commit();
            session.close();
            return count;
        } catch (Exception exception) {
            session.rollback();
            session.close();
            return -1;
        }
    }

    @Override
    public Vector<Vector<Object>> getOrderData(Order order) {
        SqlSession session = DaoUtil.getSqlSession();
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);

        List<Order> orderDatas = orderMapper.getOrderData(order);
        Vector<Vector<Object>> returnData = new Vector<>();
        for (Order orderData : orderDatas) {
            Vector<Object> data = new Vector<>();
            data.add(orderData.getOrderNo());
            data.add(orderData.getDeskId());
            data.add(orderData.getCreateTime());
            data.add(orderData.getMoney());
            data.add(orderData.getCustomerId());
            data.add(orderData.getStatus());
            data.add(orderData.getNumber());
            returnData.add(data);
        }
        session.close();
        return returnData;
    }

    @Override
    public Vector<Vector<Object>> getOrderItemData(OrderItem orderItem) {
        SqlSession session = DaoUtil.getSqlSession();
        OrderItemMapper orderItemMapper = session.getMapper(OrderItemMapper.class);
        FoodMapper foodMapper = session.getMapper(FoodMapper.class);
        List<OrderItem> orderItemDatas = orderItemMapper.getOrderItemData(orderItem);
        Vector<Vector<Object>> returnData = new Vector<>();
        for (OrderItem orderItemData : orderItemDatas) {
            Vector<Object> data = new Vector<>();
            data.add(orderItemData.getId());
            data.add(orderItemData.getOrderId());
            String foodName = foodMapper.getFoodNameById(orderItemData.getFoodId());
            data.add(foodName);
            double price = foodMapper.getFoodPriceById(orderItemData.getFoodId());
            data.add(price);
            data.add(orderItemData.getCnt());
            returnData.add(data);
        }
        session.close();
        return returnData;
    }

    @Override
    public double getRateMoney(String cusotomerId) {
        SqlSession session = DaoUtil.getSqlSession();
        CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);

        List<Customer> customerData = customerMapper.getCustomerData(cusotomerId, "", "");
        if (customerData.size() == 0) {
            session.close();
            return 1;
        } else {
            double base = customerData.get(0).getBase();
            session.close();
            return base;
        }
    }

    @Override
    public int mergeOrderStatus(Order order) {
        SqlSession session = DaoUtil.getSqlSession();
        try {
            OrderMapper orderMapper = session.getMapper(OrderMapper.class);
            int count = orderMapper.mergeOrderStatus(order);
            session.commit();
            session.close();
            return count;

        } catch (Exception exception) {
            exception.printStackTrace();
            session.rollback();
            session.close();
            return -1;
        }
    }

    @Override
    public ArrayList<Double> getStatisticsData() {
        SqlSession session = DaoUtil.getSqlSession();
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        HashMap<String, Double> params = new HashMap<>(7);
        params.put("one", (double) 0);
        params.put("two", (double) 0);
        params.put("three", (double) 0);
        params.put("four", (double) 0);
        orderMapper.getStatisticsData(params);
        ArrayList<Double> data = new ArrayList<>();
        data.add(params.get("one"));
        data.add(params.get("two"));
        data.add(params.get("three"));
        data.add(params.get("four"));
        session.close();
        return data;
    }
}
