-- 코드를 작성해주세요
select subquery.ID, case 
                when per = 1 then 'CRITICAL'
                when per = 2 then 'HIGH'
                when per = 3 then 'MEDIUM'
                when per = 4 then 'LOW'
                end as COLONY_NAME
from (select ID, NTILE(4) over (order by e.size_of_colony desc) as per
        from ecoli_data e
     ) as subquery
order by subquery.ID asc;