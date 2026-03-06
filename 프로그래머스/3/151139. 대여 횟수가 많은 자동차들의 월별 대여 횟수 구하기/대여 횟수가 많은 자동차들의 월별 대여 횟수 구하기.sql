-- 코드를 입력하세요
SELECT date_format(c.start_date, "%m") as MONTH, c.CAR_ID, count(c.HISTORY_ID) as RECORDS
from car_rental_company_rental_history c
where c.car_ID in (select c.car_id
           from car_rental_company_rental_history c
            where c.start_date between "2022-08-01" and "2022-10-31"
            group by c.CAR_ID
           having count(c.HISTORY_ID) >= 5) 
    and  c.start_date between "2022-08-01" and "2022-10-31"
group by MONTH, c.CAR_ID
having RECORDS > 0
order by MONTH asc, CAR_ID desc;