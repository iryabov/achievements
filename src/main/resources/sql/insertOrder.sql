insert into achievements.purchase (member_id, award_id, dt, cost, note, status)
values (
    (select id from achievements.member where email = :email),
    :award_id,
    current_date,
    (select cost from achievements.award where id = :award_id),
    :note,
    1)