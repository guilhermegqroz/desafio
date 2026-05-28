import {
  FolderKanban,
  LayoutDashboard,
  FileBarChart2,
  Users,
  Settings,
  LogOut,
  Search,
  Plus,
  Pencil,
  Trash2,
  AlertTriangle,
  CheckCircle2,
  BarChart3,
} from "lucide-react";

const projects = [
  {
    id: 1,
    name: "Sistema Bancário",
    status: "ANALISE_REALIZADA",
    risk: "MEDIO",
  },
  {
    id: 2,
    name: "Portal Educacional",
    status: "EM_ANALISE",
    risk: "MEDIO",
  },
  {
    id: 3,
    name: "Sistema Hospitalar",
    status: "ANALISE_REALIZADA",
    risk: "MEDIO",
  },
  {
    id: 4,
    name: "Marketplace Digital",
    status: "ANALISE_APROVADA",
    risk: "ALTO",
  },
  {
    id: 5,
    name: "Sistema Jurídico",
    status: "ANALISE_APROVADA",
    risk: "MEDIO",
  },
];

function Card({ title, value, subtitle, icon: Icon, color }) {
  return (
    <div
      className={`relative overflow-hidden rounded-2xl border border-white/10 p-6 bg-gradient-to-br ${color} shadow-2xl`}
    >
      <div className="flex items-start justify-between">
        <div>
          <p className="text-zinc-300 text-sm">{title}</p>

          <h2 className="text-4xl font-bold mt-2">{value}</h2>

          <p className="text-zinc-400 text-sm mt-2">{subtitle}</p>
        </div>

        <div className="bg-white/10 p-4 rounded-2xl">
          <Icon size={28} />
        </div>
      </div>

      <div className="absolute bottom-0 right-0 w-40 h-40 bg-white/5 blur-3xl rounded-full" />
    </div>
  );
}

function Badge({ children, type }) {
  const styles = {
    blue: "bg-blue-500/20 text-blue-400",
    green: "bg-emerald-500/20 text-emerald-400",
    orange: "bg-orange-500/20 text-orange-400",
    red: "bg-red-500/20 text-red-400",
    purple: "bg-purple-500/20 text-purple-400",
  };

  return (
    <span
      className={`px-3 py-1 rounded-full text-xs font-semibold ${styles[type]}`}
    >
      {children}
    </span>
  );
}

export default function App() {
  return (
    <div className="min-h-screen bg-[#020817] text-white flex">
      {/* SIDEBAR */}
      <aside className="w-72 border-r border-white/10 bg-[#030b1a] p-6 flex flex-col justify-between">
        <div>
          <div className="flex items-center gap-4 mb-12">
            <div className="bg-blue-600 p-3 rounded-2xl">
              <FolderKanban />
            </div>

            <div>
              <h1 className="font-bold text-xl">Sistema</h1>
              <p className="text-zinc-400 text-sm">Bancário</p>
            </div>
          </div>

          <nav className="space-y-3">
            <button className="w-full flex items-center gap-3 bg-blue-600/20 text-blue-400 p-4 rounded-2xl">
              <FolderKanban size={20} />
              Projetos
            </button>

            <button className="w-full flex items-center gap-3 p-4 rounded-2xl hover:bg-white/5">
              <LayoutDashboard size={20} />
              Dashboard
            </button>

            <button className="w-full flex items-center gap-3 p-4 rounded-2xl hover:bg-white/5">
              <FileBarChart2 size={20} />
              Relatórios
            </button>

            <button className="w-full flex items-center gap-3 p-4 rounded-2xl hover:bg-white/5">
              <Users size={20} />
              Usuários
            </button>

            <button className="w-full flex items-center gap-3 p-4 rounded-2xl hover:bg-white/5">
              <Settings size={20} />
              Configurações
            </button>
          </nav>
        </div>

        <div className="border border-white/10 rounded-2xl p-4 bg-white/5">
          <div className="flex items-center gap-3">
            <div className="w-12 h-12 rounded-full bg-blue-600 flex items-center justify-center font-bold">
              GM
            </div>

            <div>
              <h2 className="font-semibold">Guilherme</h2>
              <p className="text-zinc-400 text-sm">Administrador</p>
            </div>
          </div>

          <button className="mt-6 flex items-center gap-2 text-zinc-400 hover:text-white">
            <LogOut size={18} />
            Sair
          </button>
        </div>
      </aside>

      {/* CONTENT */}
      <main className="flex-1 p-8">
        {/* HEADER */}
        <div className="flex items-center justify-between">
          <div>
            <h1 className="text-5xl font-bold">Gestão de Projetos</h1>

            <p className="text-zinc-400 mt-2">
              Gerencie e acompanhe todos os projetos do sistema
            </p>
          </div>

          <div className="flex items-center gap-4">
            <div className="bg-white/5 border border-white/10 rounded-2xl px-4 py-3 flex items-center gap-3 w-80">
              <Search size={18} className="text-zinc-400" />

              <input
                type="text"
                placeholder="Buscar projetos..."
                className="bg-transparent outline-none w-full"
              />
            </div>

            <button className="bg-blue-600 hover:bg-blue-500 transition px-6 py-4 rounded-2xl flex items-center gap-2 font-semibold shadow-lg shadow-blue-600/30">
              <Plus size={20} />
              Novo Projeto
            </button>
          </div>
        </div>

        {/* CARDS */}
        <div className="grid grid-cols-4 gap-6 mt-10">
          <Card
            title="Total de Projetos"
            value="10"
            subtitle="Todos os projetos cadastrados"
            icon={FolderKanban}
            color="from-blue-900/50 to-blue-600/10"
          />

          <Card
            title="Em Análise"
            value="3"
            subtitle="Aguardando avaliação"
            icon={CheckCircle2}
            color="from-emerald-900/50 to-emerald-600/10"
          />

          <Card
            title="Alto Risco"
            value="3"
            subtitle="Projetos críticos"
            icon={AlertTriangle}
            color="from-orange-900/50 to-orange-600/10"
          />

          <Card
            title="Aprovados"
            value="3"
            subtitle="Análises aprovadas"
            icon={BarChart3}
            color="from-purple-900/50 to-purple-600/10"
          />
        </div>

        {/* TABLE */}
        <div className="mt-8 bg-[#081121] border border-white/10 rounded-3xl overflow-hidden">
          <div className="p-6 border-b border-white/10 flex items-center justify-between">
            <h2 className="text-2xl font-semibold">
              Lista de Projetos
            </h2>

            <button className="border border-white/10 px-4 py-2 rounded-xl hover:bg-white/5">
              Filtrar
            </button>
          </div>

          <table className="w-full">
            <thead className="text-zinc-400">
              <tr className="border-b border-white/10">
                <th className="text-left p-5">#</th>
                <th className="text-left p-5">Projeto</th>
                <th className="text-left p-5">Status</th>
                <th className="text-left p-5">Risco</th>
                <th className="text-left p-5">Data</th>
                <th className="text-left p-5">Ações</th>
              </tr>
            </thead>

            <tbody>
              {projects.map((project) => (
                <tr
                  key={project.id}
                  className="border-b border-white/5 hover:bg-white/5"
                >
                  <td className="p-5">{project.id}</td>

                  <td className="p-5">{project.name}</td>

                  <td className="p-5">
                    {project.status === "ANALISE_REALIZADA" && (
                      <Badge type="blue">
                        ANALISE_REALIZADA
                      </Badge>
                    )}

                    {project.status === "EM_ANALISE" && (
                      <Badge type="purple">
                        EM_ANALISE
                      </Badge>
                    )}

                    {project.status === "ANALISE_APROVADA" && (
                      <Badge type="green">
                        ANALISE_APROVADA
                      </Badge>
                    )}
                  </td>

                  <td className="p-5">
                    {project.risk === "MEDIO" ? (
                      <Badge type="orange">MEDIO</Badge>
                    ) : (
                      <Badge type="red">ALTO</Badge>
                    )}
                  </td>

                  <td className="p-5 text-zinc-400">
                    24/05/2025 10:21
                  </td>

                  <td className="p-5">
                    <div className="flex gap-2">
                      <button className="bg-blue-600 p-2 rounded-lg">
                        <Pencil size={16} />
                      </button>

                      <button className="bg-red-600 p-2 rounded-lg">
                        <Trash2 size={16} />
                      </button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </main>
    </div>
  );
}