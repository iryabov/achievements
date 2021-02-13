select :select
from achievements.winner w
join achievements.achievement a on a.id = w.achievement_id
join achievements.member m on m.id = w.member_id
where :filters
order by w.year desc, w.month desc