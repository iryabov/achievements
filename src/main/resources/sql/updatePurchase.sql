update achievements.purchase
set member_id = :member_id,
    award_id = :award_id,
    dt = :dt,
    cost = :cost,
    note = :note,
    status = :status
where id = :id