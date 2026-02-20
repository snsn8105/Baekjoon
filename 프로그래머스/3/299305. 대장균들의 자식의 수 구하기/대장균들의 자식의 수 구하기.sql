-- 코드를 작성해주세요
select e.ID, count(ne.ID) as child_count
from ecoli_data e
left join ecoli_data ne on e.id = ne.parent_id
group by e.id
order by e.ID asc;