package service;

import entity.ModifyIncomeDO;
import entity.RegisterDO;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/22 10:05
 */
public interface ModifyService {
    
    /**
     * TODO 请求修改服务接口
     * @param [entity.ModifyIncomeDO]
     * @return [boolean]
     * @date [2021/12/22 10:06]
     */
    public boolean validateModifyIncome(ModifyIncomeDO modifyIncomeDO);
}
