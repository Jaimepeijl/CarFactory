CREATE TABLE IF NOT EXISTS phone(

                                        id uuid PRIMARY KEY,
                                         name varchar(20) ,
                                         colour varchar(20),
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

                                  id uuid PRIMARY KEY,
                                  brand varchar(20),
                                  model varchar(20),
                                  colour varchar(20),
                                  type varchar(20),
                                  max_speed_in_kmph bigint,
                                  trunk_content_in_liters bigint,
                                  costs_per_month bigint,
                                  stock bigint,
                                  min_stock bigint,
                                  max_stock bigint

);
CREATE TABLE IF NOT EXISTS laptop(
                                     id uuid PRIMARY KEY,
                                     brand varchar(20),
                                     model varchar(20),
                                     colour varchar(20),
                                     type varchar(20),
                                     stock bigint,
                                     processor varchar (2),
                                     screen_width_in_inches bigint,
                                     depreciation_in_years int,
                                     min_stock bigint,
                                     max_stock bigint

);