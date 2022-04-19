package service;

import req.GraphicaRequest;
import res.GraphicalDTO;

/**
 * @description: 返回图形化界面需要的数据
 * @author: 李佳骏
 * @time: 2021/12/24 11:19
 */
public interface GraphicalService {
    public GraphicalDTO retrieveGraphical(GraphicaRequest request);
}
