insert into achievements.purchase (member_id, award_id, dt, cost, note)
values (
    (select id from achievement.member where email = :email),
    :award_id,
    current_date(),
    (select cost from award where id = :award_id),
    :note)