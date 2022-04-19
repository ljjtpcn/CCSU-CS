package service;

import req.StudentRequest;
import res.RowDO;
import res.TableDTO;

/**
 * @author ljj
 */
public interface StudentService {
    /**
     * TODO 查询服务接口
     * @param [req.StudentRequest]
     * @return [res.TableDTO]
     * @date [2021/12/22 10:08]
     */
    TableDTO retrieveStudents(StudentRequest request);

    TableDTO totalIncomeStudents(StudentRequest request);

    TableDTO totalExpendStudents(StudentRequest request);

    RowDO getBySelectedId(RowDO request);

}
