package cn.twopair.mappers;

import cn.twopair.pojo.Desk;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName DeskMapper
 * @Description TODO
 * @Author ljjtpcn
 * @Date 2022/6/13 上午8:12
 * @Version 1.0
 **/
public interface DeskMapper {

    List<Desk> getDeskData(@Param("no") String no,
                           @Param("seating") Integer seating,
                           @Param("status") String status);

    int addOneDesk(@Param("no") String no,
                   @Param("seating") Integer seating,
                   @Param("status") String status);

    int deleteDeskById(@Param("ids") String ids);

    Integer selectIdByNo(@Param("no") String no);

    int mergeOneDesk(@Param("id") Integer id,
                     @Param("no") String no,
                     @Param("seating") Integer seating,
                     @Param("status") String status);

    @Insert("UPDATE desk SET status =  #{desk.status} WHERE id = #{desk.id}")
    int mergeOneDeskStatus(@Param("desk") Desk desk);
}
