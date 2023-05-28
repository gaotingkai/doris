// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

suite("nereids_insert_unique_type_cast") {
    sql 'use nereids_insert_into_table_test'
    sql 'clean label from nereids_insert_into_table_test'

    sql 'set enable_nereids_planner=true'
    sql 'set enable_fallback_to_original_planner=false'

    sql '''insert into uni_t_type_cast
            select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src'''
    qt_11 'select * from uni_t_type_cast order by id, kint'

    sql '''insert into uni_t_type_cast with label label_uni_type_cast_cte
            with cte as (select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src)
            select * from cte'''
    qt_12 'select * from uni_t_type_cast order by id, kint'

    sql '''insert into uni_t_type_cast partition (p1, p2) with label label_uni_type_cast
            select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src where id < 4'''
    qt_13 'select * from uni_t_type_cast order by id, kint'

    sql '''insert into uni_light_sc_t_type_cast
            select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src'''
    qt_21 'select * from uni_light_sc_t_type_cast order by id, kint'

    sql '''insert into uni_light_sc_t_type_cast with label label_uni_light_sc_type_cast_cte
            with cte as (select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src)
            select * from cte'''
    qt_22 'select * from uni_light_sc_t_type_cast order by id, kint'

    sql '''insert into uni_light_sc_t_type_cast partition (p1, p2) with label label_uni_light_sc_type_cast
            select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src where id < 4'''
    qt_23 'select * from uni_light_sc_t_type_cast order by id, kint'

    sql '''insert into uni_mow_t_type_cast
            select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src'''
    qt_31 'select * from uni_mow_t_type_cast order by id, kint'

    sql '''insert into uni_mow_t_type_cast with label label_uni_mow_type_cast_cte
            with cte as (select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src)
            select * from cte'''
    qt_32 'select * from uni_mow_t_type_cast order by id, kint'

    sql '''insert into uni_mow_t_type_cast partition (p1, p2) with label label_uni_mow_type_cast
            select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src where id < 4'''
    qt_33 'select * from uni_mow_t_type_cast order by id, kint'

    sql '''insert into uni_light_sc_mow_t_type_cast
            select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src'''
    qt_41 'select * from uni_light_sc_mow_t_type_cast order by id, kint'

    sql '''insert into uni_light_sc_mow_t_type_cast with label label_uni_light_sc_mow_type_cast_cte
            with cte as (select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src)
            select * from cte'''
    qt_42 'select * from uni_light_sc_mow_t_type_cast order by id, kint'

    sql '''insert into uni_light_sc_mow_t_type_cast partition (p1, p2) with label label_uni_light_sc_mow_type_cast
            select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src where id < 4'''
    qt_43 'select * from uni_light_sc_mow_t_type_cast order by id, kint'

    sql '''insert into uni_mow_not_null_t_type_cast
            select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src where id is not null'''
    qt_51 'select * from uni_mow_not_null_t_type_cast order by id, kint'

    sql '''insert into uni_mow_not_null_t_type_cast with label label_uni_not_null_type_cast_cte
            with cte as (select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src)
            select * from cte where id is not null'''
    qt_52 'select * from uni_mow_not_null_t_type_cast order by id, kint'

    sql '''insert into uni_mow_not_null_t_type_cast partition (p1, p2) with label label_uni_not_null_type_cast
            select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src where id < 4 and id is not null'''
    qt_53 'select * from uni_mow_not_null_t_type_cast order by id, kint'

    sql '''insert into uni_light_sc_mow_not_null_t_type_cast
            select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src where id is not null'''
    qt_61 'select * from uni_light_sc_mow_not_null_t_type_cast order by id, kint'

    sql '''insert into uni_light_sc_mow_not_null_t_type_cast with label label_uni_light_sc_not_null_type_cast_cte
            with cte as (select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src)
            select * from cte where id is not null'''
    qt_62 'select * from uni_light_sc_mow_not_null_t_type_cast order by id, kint'

    sql '''insert into uni_light_sc_mow_not_null_t_type_cast partition (p1, p2) with label label_uni_light_sc_not_null_type_cast
            select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src where id < 4 and id is not null'''
    qt_63 'select * from uni_light_sc_mow_not_null_t_type_cast order by id, kint'

    sql '''insert into uni_mow_not_null_t_type_cast
            select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src where id is not null'''
    qt_71 'select * from uni_mow_not_null_t_type_cast order by id, kint'

    sql '''insert into uni_mow_not_null_t_type_cast with label label_uni_mow_not_null_type_cast_cte
            with cte as (select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src)
            select * from cte where id is not null'''
    qt_72 'select * from uni_mow_not_null_t_type_cast order by id, kint'

    sql '''insert into uni_mow_not_null_t_type_cast partition (p1, p2) with label label_uni_mow_not_null_type_cast
            select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src where id < 4 and id is not null'''
    qt_73 'select * from uni_mow_not_null_t_type_cast order by id, kint'

    sql '''insert into uni_light_sc_mow_not_null_t_type_cast
            select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src where id is not null'''
    qt_81 'select * from uni_light_sc_mow_not_null_t_type_cast order by id, kint'

    sql '''insert into uni_light_sc_mow_not_null_t_type_cast with label label_uni_light_sc_mow_not_null_type_cast_cte
            with cte as (select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src)
            select * from cte where id is not null'''
    qt_82 'select * from uni_light_sc_mow_not_null_t_type_cast order by id, kint'

    sql '''insert into uni_light_sc_mow_not_null_t_type_cast partition (p1, p2) with label label_uni_light_sc_mow_not_null_type_cast
            select id, ktint, ksint, kint, kbint, kdtv2, kdtm, kdbl from src where id < 4 and id is not null'''
    qt_83 'select * from uni_light_sc_mow_not_null_t_type_cast order by id, kint'
}