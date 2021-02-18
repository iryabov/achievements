select count(*) as cnt, m.name as name
from achievements.winner w
join achievements.achievement a on a.id = w.achievement_id
join achievements.member m on m.id = w.member_id
where :filters
group by m.name
order by 1 desc