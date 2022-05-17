package cn.twopair.service;


import cn.twopair.entity.BookInfo;
import cn.twopair.req.BookRequest;
import cn.twopair.res.TableDTO;

/**
 * @author ljj
 */
public interface BookService {

    /**
     * @Param: request
     * @Description: 查询接口
     * @Return: cn.twopair.res.TableDTO
     * @Author: 李佳骏
     * @Date: 2022/5/16 下午2:50
     */
    TableDTO selectAll(BookRequest request);
    BookInfo getBySelectedId(Integer id);

}
