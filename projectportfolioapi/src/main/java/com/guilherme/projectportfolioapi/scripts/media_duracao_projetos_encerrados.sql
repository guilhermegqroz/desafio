SELECT AVG(data_real_termino - data_inicio)
FROM projetos
WHERE status = 'ENCERRADO';