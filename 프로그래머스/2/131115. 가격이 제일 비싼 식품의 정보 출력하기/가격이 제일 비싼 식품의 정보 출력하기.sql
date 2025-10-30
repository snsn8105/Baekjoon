-- 코드를 입력하세요
SELECT f.product_id, f.product_name, f.product_cd, f.category, f.price
from FOOD_PRODUCT f
order by f.price desc
limit 1;