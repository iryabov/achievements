update achievements.winner
set member_id = :member_id,
    achievement_id = :achievement_id,
    year = :year,
    month = :month,
    type = :type,
    points = :points,
    note = :note
where id = :id