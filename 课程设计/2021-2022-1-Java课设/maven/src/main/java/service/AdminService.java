package service;

import entity.AdminDO;

public interface AdminService {
    /**
     * TODO 登录校验接口
     * @param [entity.AdminDO]
     * @return [boolean]
     * @date [2021/12/22 10:09]
     */
    public boolean validateAdmin(AdminDO adminDO);
}
