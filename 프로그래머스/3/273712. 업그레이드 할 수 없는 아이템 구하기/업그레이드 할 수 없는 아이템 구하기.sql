-- 코드를 작성해주세요
select ii.ITEM_ID, ii.ITEM_NAME, ii.RARITY
from item_info ii
where ii.ITEM_ID not in (select parent_item_id
                        from item_tree
                        where parent_item_id is not null)
order by ii.ITEM_ID desc;