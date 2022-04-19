package service;

import entity.AddDO;
import entity.DeleteDO;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/22 18:23
 */
public interface AddService {
     boolean validateAdd(AddDO addDO);
}
