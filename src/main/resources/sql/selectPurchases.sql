select :select
from achievements.purchase p
join achievements.award a on a.id = p.award_id
join achievements.member m on m.id = p.member_id
where :filters
order by p.dt desc