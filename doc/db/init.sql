-- 角色表
CREATE TABLE sys_user
(
    userId      BIGINT PRIMARY KEY COMMENT '用户ID',
    account     VARCHAR(10) COMMENT '账号',
    nickname    VARCHAR(8) COMMENT '昵称',
    userType    VARCHAR(255) COMMENT '用户类型',
    email       VARCHAR(255) COMMENT '邮箱',
    phonenumber VARCHAR(255) COMMENT '手机号',
    sex         CHAR COMMENT '性别',
    avatar      VARCHAR(255) COMMENT '头像',
    password    VARCHAR(255) COMMENT '密码',
    status      VARCHAR(255) COMMENT '状态',
    loginTime   TIMESTAMP COMMENT '登录时间',
    createTime  TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updateTime  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
);

ALTER TABLE sys_user
    ADD CONSTRAINT check_account_length CHECK (LENGTH(account) >= 6 AND LENGTH(account) <= 10),
    ADD CONSTRAINT check_nickname_length CHECK (LENGTH(nickname) >= 3 AND LENGTH(nickname) <= 8);

INSERT INTO sys_user (userId, account, nickname, userType, email, phonenumber, sex, avatar, password, status, loginTime)
VALUES (1, '123456', 'user1', 'type1', 'user1@example.com', '123456789', 'M', 'avatar1', 'password1', 'active',
        '2023-05-20 10:00:00');

INSERT INTO sys_user (userId, account, nickname, userType, email, phonenumber, sex, avatar, password, status, loginTime)
VALUES (2, '987654', 'user2', 'type2', 'user2@example.com', '987654321', 'F', 'avatar2', 'password2', 'inactive',
        '2023-05-20 11:00:00');

INSERT INTO sys_user (userId, account, nickname, userType, email, phonenumber, sex, avatar, password, status, loginTime)
VALUES (3, '555555', 'user3', 'type3', 'user3@example.com', '555555555', 'M', 'avatar3', 'password3', 'active',
        '2023-05-20 12:00:00');
