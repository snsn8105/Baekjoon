-- 코드를 입력하세요
SELECT CATEGORY, PRICE as MAX_PRICE, PRODUCT_NAME
from food_product fp
where (category, price) in (
        select category, max(price)
        from food_product
        where CATEGORY IN ('과자', '국', '김치', '식용유')
        group by category 
    )
order by max_price desc;