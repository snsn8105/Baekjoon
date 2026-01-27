-- 코드를 입력하세요
SELECT ii.INGREDIENT_TYPE, sum(fh.TOTAL_ORDER) as TOTAL_ORDER
from FIRST_HALF fh 
inner join ICECREAM_INFO ii on fh.FLAVOR = ii.FLAVOR
group by ingredient_type
order by TOTAL_ORDER asc;