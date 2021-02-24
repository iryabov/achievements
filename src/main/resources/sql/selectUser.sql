select
    m.id as id,
    m.name as name,
    m.email as email,
    m.avatar as avatar,
    (select count(*)
       from achievements.winner w
      where w.member_id = m.id) as stars,
    (select count(*)
       from achievements.purchase p
      where p.member_id = m.id
       and p.status = 2) as orders, --processing
    coalesce((select sum(p.cost)
       from achievements.purchase p
      where p.member_id = m.id), 0) as purchases
from achievements.member m
where m.email = :email