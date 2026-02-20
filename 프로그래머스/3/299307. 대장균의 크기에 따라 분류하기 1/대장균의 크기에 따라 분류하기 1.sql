-- 코드를 작성해주세요
select e.ID, case 
                when e.size_of_colony <= 100 then 'LOW'
                when e.size_of_colony <= 1000 then 'MEDIUM'
                when e.size_of_colony > 1000 then 'HIGH'
                end as SIZE
from ecoli_data e
order by e.ID;