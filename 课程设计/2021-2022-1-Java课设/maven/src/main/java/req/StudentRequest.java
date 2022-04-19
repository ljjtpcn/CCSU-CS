package req;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/19 23:34
 */

public class StudentRequest {
    private int pageNow;
    private int PageSize;
    private int start;
    private String user;
    private String id;

    //总输入查询还是总支出查询
    private boolean totalKey;
    private boolean expendKey;

    //查询关键词
    private String searchKey;

    public int getStart() {
        return (pageNow - 1) * PageSize;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
