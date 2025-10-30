-- 코드를 입력하세요
SELECT count(distinct i.name) as count
from ANIMAL_INS i
where i.name is not null