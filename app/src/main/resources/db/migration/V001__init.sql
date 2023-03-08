CREATE TABLE IF NOT EXISTS phone(

                                         name varchar(20) PRIMARY KEY,
                                         color varchar(20),
                                         segment varchar(20),
                                         memory varchar(20),
                                         cameras int,
                                         cost bigint,
                                         stock bigint,
                                         min_stock bigint,
                                         max_stock bigint,
                                         chips_per bigint

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
CREATE TABLE IF NOT EXISTS laptop(

                                     brand varchar(20),
                                     model varchar(20) PRIMARY KEY,
                                     colour varchar(20),
                                     type varchar(20),
                                     stock bigint,
                                     min_stock bigint,
                                     max_stock bigint

);