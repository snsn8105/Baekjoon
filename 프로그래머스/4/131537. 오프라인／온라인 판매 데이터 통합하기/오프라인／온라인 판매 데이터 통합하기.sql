-- 코드를 입력하세요
SELECT DATE_FORMAT(ons.SALES_DATE, '%Y-%m-%d') as SALES_DATE, ons.PRODUCT_ID, ons.USER_ID, ons.SALES_AMOUNT
from ONLINE_SALE ons
where ons.SALES_DATE like '2022-03%'

union all

select DATE_FORMAT(offs.SALES_DATE, '%Y-%m-%d') as SALES_DATE, offs.PRODUCT_ID, null as USER_ID, offs.SALES_AMOUNT
from OFFLINE_SALE offs
where offs.SALES_DATE like '2022-03%'

order by SALES_DATE asc, PRODUCT_ID asc, USER_ID asc;