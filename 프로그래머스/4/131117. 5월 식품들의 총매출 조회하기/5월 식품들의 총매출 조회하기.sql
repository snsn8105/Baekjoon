-- 코드를 입력하세요
SELECT p.PRODUCT_ID, p.PRODUCT_NAME, p.price * sum(o.amount) as TOTAL_SALES
from FOOD_PRODUCT p
join FOOD_ORDER o on p.product_id = o.product_id
where YEAR(o.produce_date) = '2022' and MONTH(o.produce_date) = '05'
group by p.product_id
order by TOTAL_SALES desc, p.product_id asc;