CREATE TABLE COFFEE (
    COFFEE_ID INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(50) NOT NULL,
    KIND VARCHAR(50) NOT NULL,
    PRICE INT NOT NULL,
    REG_DAY DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    MOD_DAY DATETIME NULL DEFAULT 0 ON UPDATE CURRENT_TIMESTAMP(),
    PRIMARY KEY (COFFEE_ID)
    )
    COMMENT='Web Programming | from 프론트엔드 실습 테이블'
    ;
    
    Insert into coffee (name, kind, price, reg_day) VALUES('아메리카노' , '1.커피' , 4000, str_to_date('20220105','%Y%m%d'));
    Insert into coffee (name, kind, price, reg_day) values('카페라떼' , '1.커피' , 4500, str_to_date('20220205','%Y%m%d'));
    Insert into coffee (name, kind, price, reg_day) values('카푸치노' , '1.커피' , 5000, str_to_date('20220305','%Y%m%d'));
    Insert into coffee (name, kind, price, reg_day) values('콜드브루' , '1.커피' , 4500, str_to_date('20220405','%Y%m%d'));
    Insert into coffee (name, kind, price, reg_day) values('에스프레소' , '1.커피' , 4500, str_to_date('20220505','%Y%m%d'));
    Insert into coffee (name, kind, price, reg_day) VALUES('레몬에이드' , '2.에이드' , 5000, str_to_date('20220605','%Y%m%d'));
    Insert into coffee (name, kind, price, reg_day) VALUES('자몽에이드' , '2.에이드' , 6000, str_to_date('20220705','%Y%m%d'));
    Insert into coffee (name, kind, price, reg_day) VALUES('망고에이드' , '2.에이드' , 5500, str_to_date('20220805','%Y%m%d'));
    Insert into coffee (name, kind, price, reg_day) VALUES('딸기스무디' , '3.스무디' , 6000, str_to_date('20220905','%Y%m%d'));
    Insert into coffee (name, kind, price, reg_day) VALUES('블루베리스무디' , '3.스무디' , 6000, str_to_date('20221005','%Y%m%d'));
    Insert into coffee (name, kind, price, reg_day) VALUES('망고스무디' , '3.스무디' , 6000, str_to_date('20221105','%Y%m%d'));
    
    
    CREATE TABLE `coffee_log` (
    `COFFEE_ID` INT(11) NOT NULL,
    `PRICE` INT(11) NOT NULL,
    `REG_DAY` DATETIME NOT NULL DEFAULT current_timestamp()
    )

    -- oracle , mariadb
    Insert into coffee_log(coffee_id,price)         
    select 1,300 from dual
    union all
    select 2,300 from dual
    union all
    select 3,300 from DUAL;

    -- mairadb
    Insert into coffee_log(coffee_id,price)
    Values
    ('1',300),
    ('1',300),
    ('1',300)
    ;