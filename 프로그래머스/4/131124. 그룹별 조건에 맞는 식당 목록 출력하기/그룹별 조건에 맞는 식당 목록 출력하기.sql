-- 코드를 입력하세요
SELECT p.MEMBER_NAME, r.REVIEW_TEXT, date_format(r.REVIEW_DATE, "%Y-%m-%d") as REVIEW_DATE
from MEMBER_PROFILE p
join REST_REVIEW r on p.member_id = r.member_id
where r.member_id in (select member_id
                  from REST_REVIEW
                  group by member_id
                  having count(*) = (select max(cnt) 
                                    from (select member_id, count(*) as cnt
                                         from REST_REVIEW
                                         group by member_id) T
                                    )
                  )
order by r.review_date asc, r.review_text asc;
