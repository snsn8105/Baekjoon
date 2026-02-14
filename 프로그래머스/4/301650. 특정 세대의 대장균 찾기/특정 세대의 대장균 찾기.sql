-- 코드를 작성해주세요
select ID
from ecoli_data
where parent_id in (select id
                    from ecoli_data
                    where parent_id in (select id
                                       from ecoli_data
                                       where parent_id is null));