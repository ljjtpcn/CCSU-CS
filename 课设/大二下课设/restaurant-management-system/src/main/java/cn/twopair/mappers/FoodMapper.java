package cn.twopair.mappers;

import cn.twopair.pojo.Food;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FoodMapper
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/14 上午10:45
 * @Version 1.0
 **/
public interface FoodMapper {
    List<Food> getFoodData(@Param("id") String id,
                           @Param("name") String name,
                           @Param("status") String status,
                           @Param("categoryId") String categoryId);

    //void addOneFood(HashMap<String, Object> params);
    int addOneFood(@Param("food") Food food);

    int mergeOneFood(@Param("food") Food food);

    int deleteFoodById(@Param("ids") String ids);

    @Select("SELECT filePath FROM food WHERE id = #{id}")
    String getFoodIconPathById(@Param("id") Integer id);

    @Select("SELECT * FROM  food WHERE status = '上架中' ORDER BY sum DESC LIMIT 0, 5")
    List<Food> getBossRecommendFoodData();

    @Select("SELECT name FROM food WHERE id = #{foodId}")
    String getFoodNameById(@Param("foodId") Integer foodId);

    @Select("SELECT price FROM food WHERE id = #{foodId}")
    double getFoodPriceById(@Param("foodId") Integer foodId);
}
