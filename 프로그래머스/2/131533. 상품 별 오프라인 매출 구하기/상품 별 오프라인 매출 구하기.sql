-- 코드를 입력하세요
SELECT product.product_code, SUM(product.PRICE * offline_sale.SALES_AMOUNT) AS SALES
    from product
    inner join offline_sale on product.product_id = offline_sale.product_id
    group by product.product_code
    order by SALES DESC, product.product_code ASC; 