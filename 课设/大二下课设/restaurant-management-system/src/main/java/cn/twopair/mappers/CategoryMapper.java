package cn.twopair.mappers;

import cn.twopair.pojo.Category;
import lombok.Setter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName CategoryMapper
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/13 下午9:24
 * @Version 1.0
 **/
public interface CategoryMapper {

    List<Category> getCategoryData(@Param("id") String id, @Param("name") String name);

    int addOneCategory(@Param("name") String name, @Param("describe") String describe);

    Integer selectIdByName(@Param("name") String name);

    int mergeOneCategory(@Param("id") Integer id, @Param("name") String name, @Param("describe") String describe);

    int deleteCategoryById(@Param("ids") String ids);

    @Select("select count(*) from category where id = #{id}")
    int checkIdExist(@Param("id") Integer id);

    @Select("SELECT id FROM category WHERE name = #{categoryName}")
    Integer getCategoryIdByName(String categoryName);
}
