CREATE TABLE user
(
    id       int AUTO_INCREMENT COMMENT '序号',
    username varchar(20) NOT NULL UNIQUE COMMENT '用户名',
    password varchar(20) NOT NULL COMMENT '密码',
    PRIMARY KEY (id)
);

CREATE TABLE employee
(
    id         int AUTO_INCREMENT COMMENT '序号',
    name       varchar(20) NOT NULL COMMENT '姓名',
    sex        Enum ('男', '女') COMMENT '性别',
    identityID varchar(18) NOT NULL COMMENT '身份证号',
    tel        varchar(11) COMMENT '电话号码',
    status     Enum ('在职', '离职') COMMENT '状态',
    PRIMARY KEY (id)
);


CREATE TABLE customer
(
    id   int AUTO_INCREMENT COMMENT '序号',
    name varchar(20) NOT NULL COMMENT '姓名',
    sex  varchar(2) COMMENT '性别',
    tel  varchar(11) COMMENT '电话号码',
    base double COMMENT '折扣率',
    PRIMARY KEY (id)
);

CREATE TABLE category
(
    id         int AUTO_INCREMENT COMMENT '序号',
    name       varchar(20) COMMENT '菜分类名',
    `describe` varchar(255) COMMENT '描述',
    PRIMARY KEY (id)
);

CREATE TABLE desk
(
    id      int AUTO_INCREMENT COMMENT '序号',
    no varchar(20) comment '编号',
    seating int NOT NULL COMMENT '座位数',
    status  Enum ('忙碌', '空闲', '预订') COMMENT '状态：忙碌，空闲, 预订',
    PRIMARY KEY (id)
);

CREATE TABLE `order`
(
    orderNo    varchar(20) COMMENT '订单编号（当期日期yyyymmdd+8位随机数）',
    deskId     int      NOT NULL COMMENT '餐台号',
    createTime datetime NOT NULL COMMENT '就餐日期时间',
    money      double   NOT NULL COMMENT '金额',
    customerId int      NOT NULL COMMENT '客户id',
    status     Enum ('已支付', '未支付') COMMENT '状态:已支付，未支付',
    number     int      NOT NULL COMMENT '就餐人数',
    PRIMARY KEY (orderNo)
);

CREATE TABLE orderItem
(
    id      int AUTO_INCREMENT COMMENT '序号',
    orderId varchar(20) NOT NULL COMMENT '订单编号',
    foodId  int         NOT NULL COMMENT '菜品编号',
    cnt     int         NOT NULL COMMENT '菜品数量',
    PRIMARY KEY (id)
);

CREATE TABLE food
(
    id         int AUTO_INCREMENT COMMENT '菜品序号',
    name       varchar(20) NOT NULL COMMENT '菜名',
    categoryId int COMMENT '菜品类别编号',
    price      double      NOT NULL COMMENT '价格',
    `describe` varchar(255) COMMENT '描述',
    status     Enum ('已下架', '上架中') COMMENT '状态：已下架，上架中',
    sum        int         NOT NULL DEFAULT 0 COMMENT '菜品销量',
    filePath   varchar(255) COMMENT '菜品路径',
    PRIMARY KEY (id)
);

CREATE PROCEDURE getSellStatisticsData(OUT one double, OUT two double, OUT three double, OUT four double)
BEGIN
    SELECT IFNULL(SUM(money), 0)
    INTO one
    FROM `order`
    WHERE status = '已支付'
      AND YEAR(NOW()) = YEAR(createTime)
      AND (SELECT QUARTER(createTime) = 1);

    SELECT IFNULL(SUM(money), 0)
    INTO two
    FROM `order`
    WHERE status = '已支付'
      AND YEAR(NOW()) = YEAR(createTime)
      AND (SELECT QUARTER(createTime) = 2);

    SELECT IFNULL(SUM(money), 0)
    INTO three
    FROM `order`
    WHERE status = '已支付'
      AND YEAR(NOW()) = YEAR(createTime)
      AND (SELECT QUARTER(createTime) = 3);

    SELECT IFNULL(SUM(money), 0)
    INTO four
    FROM `order`
    WHERE status = '已支付'
      AND YEAR(NOW()) = YEAR(createTime)
      AND (SELECT QUARTER(createTime) = 4);
END;