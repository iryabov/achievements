select
    count(*) over (partition by m.name order by w.year, w.month) as stars,
    w.year as year,
    w.month as month,
    m.name as name
from achievements.winner w
join achievements.achievement a on a.id = w.achievement_id
join achievements.member m on m.id = w.member_id
where :filters
group by w.year, w.month, m.name
order by w.year, w.month asc