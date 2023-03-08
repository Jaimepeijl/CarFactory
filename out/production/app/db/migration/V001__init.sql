CREATE TABLE IF NOT EXISTS phone(

                                         name varchar(20) PRIMARY KEY,
                                         color varchar(20),
                                         cameras int,
                                         stock bigint,
                                         min_stock bigint,
                                         max_stock bigint

);
CREATE TABLE IF NOT EXISTS car(

                                    brand varchar(20) PRIMARY KEY,
                                    model varchar(20),
                                    colour varchar(20),
                                    type varchar(20),
                                    stock bigint,
                                    min_stock bigint,
                                    max_stock bigint

);