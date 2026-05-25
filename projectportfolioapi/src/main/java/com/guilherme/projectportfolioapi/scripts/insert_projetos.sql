INSERT INTO projetos (
    nome,
    data_inicio,
    previsao_termino,
    data_real_termino,
    orcamento_total,
    descricao,
    gerente_id,
    status
) VALUES

-- EM_ANALISE
(
    'Sistema Bancário',
    '2026-05-01',
    '2026-09-01',
    NULL,
    95000,
    'Projeto em análise inicial',
    9,
    'EM_ANALISE'
),

(
    'Portal Educacional',
    '2026-06-10',
    '2026-11-15',
    NULL,
    110000,
    'Projeto aguardando análise',
    10,
    'EM_ANALISE'
),

-- ANALISE_REALIZADA
(
    'Sistema Hospitalar',
    '2026-01-20',
    '2026-07-30',
    NULL,
    220000,
    'Análise concluída',
    11,
    'ANALISE_REALIZADA'
),

(
    'Plataforma Streaming',
    '2026-03-15',
    '2026-10-20',
    NULL,
    450000,
    'Projeto com análise realizada',
    12,
    'ANALISE_REALIZADA'
),

-- ANALISE_APROVADA
(
    'Marketplace Digital',
    '2026-02-01',
    '2026-12-01',
    NULL,
    650000,
    'Projeto aprovado para execução',
    13,
    'ANALISE_APROVADA'
),

(
    'Sistema Jurídico',
    '2026-04-12',
    '2026-09-12',
    NULL,
    180000,
    'Projeto aprovado',
    14,
    'ANALISE_APROVADA'
),

-- INICIADO
(
    'Plataforma de Cursos',
    '2026-01-05',
    '2026-08-05',
    NULL,
    350000,
    'Projeto iniciado recentemente',
    15,
    'INICIADO'
),

(
    'Sistema de Delivery',
    '2026-02-18',
    '2026-11-18',
    NULL,
    500000,
    'Execução iniciada',
    16,
    'INICIADO'
),

-- PLANEJADO
(
    'Aplicação de Investimentos',
    '2026-07-01',
    '2027-01-01',
    NULL,
    275000,
    'Projeto em fase de planejamento',
    17,
    'PLANEJADO'
),

(
    'Sistema de Eventos',
    '2026-08-10',
    '2027-02-15',
    NULL,
    145000,
    'Planejamento inicial',
    18,
    'PLANEJADO'
),

-- EM_ANDAMENTO
(
    'CRM Empresarial',
    '2026-01-15',
    '2026-10-15',
    NULL,
    720000,
    'Projeto em desenvolvimento',
    19,
    'EM_ANDAMENTO'
),

(
    'Sistema de Segurança',
    '2026-03-01',
    '2026-12-20',
    NULL,
    980000,
    'Projeto em andamento',
    20,
    'EM_ANDAMENTO'
),

-- ENCERRADO
(
    'Portal Corporativo',
    '2025-02-01',
    '2025-09-01',
    '2025-08-20',
    310000,
    'Projeto finalizado com sucesso',
    21,
    'ENCERRADO'
),

(
    'Sistema Acadêmico',
    '2025-01-10',
    '2025-07-10',
    '2025-07-01',
    210000,
    'Projeto encerrado',
    22,
    'ENCERRADO'
),

-- CANCELADO
(
    'App de Turismo',
    '2026-03-01',
    '2026-12-01',
    NULL,
    430000,
    'Projeto cancelado por estratégia',
    23,
    'CANCELADO'
),

(
    'Sistema de Franquias',
    '2026-04-15',
    '2027-01-15',
    NULL,
    800000,
    'Projeto cancelado',
    24,
    'CANCELADO'
);