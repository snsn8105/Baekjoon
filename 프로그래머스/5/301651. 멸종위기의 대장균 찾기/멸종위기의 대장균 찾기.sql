with recursive GenerationCTE as (
    select e.ID, 1 AS GENERATION
    from ecoli_data e
    where parent_id is null
    union all
    -- 세대 확장 (재귀)
    select e.ID, g.generation + 1
    from ecoli_data e
    join GenerationCTE g on e.parent_ID = g.id
)

-- 코드를 작성해주세요
select count(g.generation) as COUNT, g.GENERATION
from GenerationCTE g
left join ecoli_data e on e.parent_id = g.id
where e.id is null
group by g.generation
order by g.generation;