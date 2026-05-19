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
    'Projeto Financeiro',
    '2026-01-01',
    '2026-03-01',
    NULL,
    80000,
    'Projeto em análise',
    1,
    'EM_ANALISE'
),

-- ANALISE_REALIZADA
(
    'Portal RH',
    '2026-02-01',
    '2026-06-01',
    NULL,
    150000,
    'Projeto com análise realizada',
    2,
    'ANALISE_REALIZADA'
),

-- ANALISE_APROVADA
(
    'Sistema Logistica',
    '2026-01-15',
    '2026-07-15',
    NULL,
    250000,
    'Projeto aprovado',
    3,
    'ANALISE_APROVADA'
),

-- INICIADO
(
    'ERP Corporativo',
    '2026-03-01',
    '2026-12-01',
    NULL,
    900000,
    'Projeto iniciado',
    4,
    'INICIADO'
),

-- PLANEJADO
(
    'Aplicativo Mobile',
    '2026-04-01',
    '2026-08-01',
    NULL,
    120000,
    'Projeto planejado',
    5,
    'PLANEJADO'
),

-- EM_ANDAMENTO
(
    'Sistema Compras',
    '2026-02-10',
    '2026-09-10',
    NULL,
    300000,
    'Projeto em andamento',
    6,
    'EM_ANDAMENTO'
),

-- ENCERRADO
(
    'Sistema Antigo',
    '2025-01-01',
    '2025-06-01',
    '2025-05-20',
    500000,
    'Projeto encerrado',
    7,
    'ENCERRADO'
),

-- CANCELADO
(
    'Projeto Cancelado',
    '2026-01-01',
    '2026-10-01',
    NULL,
    700000,
    'Projeto cancelado',
    8,
    'CANCELADO'
);