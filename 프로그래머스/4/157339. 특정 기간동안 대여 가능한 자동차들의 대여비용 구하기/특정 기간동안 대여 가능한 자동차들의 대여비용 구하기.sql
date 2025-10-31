SELECT DISTINCT
    car_id, 
    car_type, 
    ROUND(daily_fee * 30 * (1 - discount_rate / 100), 0) AS fee
FROM car_rental_company_car
LEFT JOIN car_rental_company_rental_history
    USING (car_id)
LEFT JOIN car_rental_company_discount_plan
    USING (car_type)
WHERE car_type IN ('세단', 'SUV') 
    AND duration_type = '30일 이상'
    AND car_id NOT IN (
        SELECT car_id
        FROM car_rental_company_rental_history
        WHERE end_date >= '2022-11-01'
        AND start_date <= '2022-11-30')
    AND daily_fee * 30 * (1 - discount_rate / 100) >= 500000
    AND daily_fee * 30 * (1 - discount_rate / 100) < 2000000
ORDER BY fee DESC, car_type ASC, car_id DESC