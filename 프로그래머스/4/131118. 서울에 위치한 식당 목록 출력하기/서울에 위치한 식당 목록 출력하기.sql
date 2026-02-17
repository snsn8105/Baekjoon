-- 코드를 입력하세요
SELECT ri.REST_ID, ri.REST_NAME, ri.FOOD_TYPE, ri.FAVORITES, ri.ADDRESS, round(avg(rr.review_score), 2) as SCORE
from REST_INFO ri
join REST_REVIEW rr on rr.rest_id = ri.rest_id
where ri.ADDRESS like '서울%'
group by rr.rest_id
order by score desc, ri.favorites desc;