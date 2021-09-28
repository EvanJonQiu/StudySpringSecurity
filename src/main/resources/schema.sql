DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
    id SERIAL PRIMARY KEY,
    real_name VARCHAR(32) NOT NULL,
    user_name VARCHAR(32) NOT NULL,
    password VARCHAR(64) NULL,
    last_login_time TIMESTAMP NULL,
    enabled BOOLEAN DEFAULT TRUE NULL,
    not_expired BOOLEAN DEFAULT TRUE NULL,
    account_not_locked BOOLEAN DEFAULT TRUE NULL,
    credentials_not_expired BOOLEAN DEFAULT TRUE NULL,
    create_time TIMESTAMP NULL,
    update_time TIMESTAMP NULL,
    create_user INTEGER NULL,
    update_user INTEGER NULL,
    not_deleted BOOLEAN DEFAULT FALSE NULL
);

COMMENT ON TABLE sys_user IS '用户表';
COMMENT ON COLUMN sys_user.id IS '主键';
COMMENT ON COLUMN sys_user.real_name IS '真实姓名';
COMMENT ON COLUMN sys_user.user_name IS '用户名称';
COMMENT ON COLUMN sys_user.password IS '用户密码';
COMMENT ON COLUMN sys_user.last_login_time IS '最后登录时间';
COMMENT ON COLUMN sys_user.enabled IS '用户状态：TRUE 启用, FALSE 禁用';
COMMENT ON COLUMN sys_user.not_expired IS '账号是否过期';
COMMENT ON COLUMN sys_user.account_not_locked IS '账号是否被锁定';
COMMENT ON COLUMN sys_user.credentials_not_expired IS '账号授权是否过期';
COMMENT ON COLUMN sys_user.create_time IS '账号创建时间';
COMMENT ON COLUMN sys_user.update_time IS '账号更新时间';
COMMENT ON COLUMN sys_user.create_user IS '账号创建者ID';
COMMENT ON COLUMN sys_user.update_user IS '账号更新者ID';
COMMENT ON COLUMN sys_user.not_deleted IS '受否可以被删除: TRUE 可以被删除, FALSE 不可以被删除';

ALTER TABLE sys_user OWNER TO test;
GRANT ALL PRIVILEGES ON TABLE sys_user TO test;

-----
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
    id SERIAL PRIMARY KEY,
    role_code VARCHAR(32) NULL,
    role_name VARCHAR(32) NULL,
    role_description VARCHAR(64) NULL
);

COMMENT ON TABLE sys_role IS '用户角色表';
COMMENT ON COLUMN sys_role.id IS '主键';
COMMENT ON COLUMN sys_role.role_code IS '角色编码';
COMMENT ON COLUMN sys_role.role_name IS '角色名称';
COMMENT ON COLUMN sys_role.role_description IS '角色描述';

ALTER TABLE sys_role OWNER TO test;
GRANT ALL PRIVILEGES ON TABLE sys_role TO test;

-----
DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role (
    id SERIAL PRIMARY KEY,
    user_id INT NULL,
    role_id INT NULL
);

COMMENT ON TABLE sys_user_role IS '用户角色关系表';
COMMENT ON COLUMN sys_user_role.id IS '主键';
COMMENT ON COLUMN sys_user_role.user_id IS '用户ID';
COMMENT ON COLUMN sys_user_role.role_id IS '角色ID';

ALTER TABLE sys_user_role OWNER TO test;
GRANT ALL PRIVILEGES ON TABLE sys_user_role TO test;


---- test data
INSERT INTO sys_user (id, real_name, user_name, password, last_login_time, enabled, not_expired, account_not_locked, credentials_not_expired, create_time, update_time, create_user, update_user, not_deleted)
VALUES (DEFAULT, '用户1', 'admin', '$2a$10$tNnVuG3rHf.LQxdFeo0R.OcQEUFKpqaEVpyo9kAsIyfnWGDT5BEK6', '2019-09-04 20:25:36', TRUE, TRUE, TRUE, TRUE, '2019-08-29 06:28:36', '2019-09-04 20:25:36', 1, 1, TRUE);
INSERT INTO sys_user (id, real_name, user_name, password, last_login_time, enabled, not_expired, account_not_locked, credentials_not_expired, create_time, update_time, create_user, update_user, not_deleted)
VALUES (DEFAULT, '用户2', 'customor', '$2a$10$tNnVuG3rHf.LQxdFeo0R.OcQEUFKpqaEVpyo9kAsIyfnWGDT5BEK6', '2019-09-05 00:07:12', TRUE, TRUE, TRUE, TRUE, '2019-08-29 06:29:24', '2019-09-05 00:07:12', 1, 2, FALSE);

insert into sys_role (id, role_code, role_name, role_description)
values (DEFAULT, 'ROLE_ADMIN', '管理员', '管理员，拥有所有权限');
insert into sys_role (id, role_code, role_name, role_description)
values (DEFAULT, 'ROLE_USER', '普通用户', '普通用户，拥有部分权限');

INSERT INTO sys_user_role (id, user_id, role_id) VALUES (DEFAULT, 1, 1);
INSERT INTO sys_user_role (id, user_id, role_id) VALUES (DEFAULT, 2, 2);
