DELETE FROM achievements WHERE id IN (SELECT id FROM achievements WHERE achievementName = 'Azure Developer Associate' LIMIT 1)
