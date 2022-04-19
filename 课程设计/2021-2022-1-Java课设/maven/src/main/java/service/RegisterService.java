package service;

import entity.AdminDO;
import entity.RegisterDO;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/21 17:25
 */
public interface RegisterService {
    /**
     * TODO 请求注册服务接口
     * @param [entity.RegisterDO]
     * @return [int]
     * @date [2021/12/22 10:09]
     */
    public int validateRegister(RegisterDO registerDO);
}
