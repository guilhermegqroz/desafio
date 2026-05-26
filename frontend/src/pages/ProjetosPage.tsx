import { useEffect, useState } from "react";
import api from "../services/api";

interface Projeto {
  id: number;
  nome: string;
  status: string;
  risco: string;
}

function ProjetosPage() {

  const [projetos, setProjetos] = useState<Projeto[]>([]);

  useEffect(() => {
    carregarProjetos();
  }, []);

  async function carregarProjetos() {
    try {
      const response = await api.get("/projetos");
      console.log(response.data);
      setProjetos(response.data.content);
    } catch (error) {
      console.error(error);
    }
  }

  return (
    <div style={{ padding: 20 }}>
      <h1>Projetos</h1>

      {projetos.map((projeto) => (
        <div key={projeto.id}>
          <h3>{projeto.nome}</h3>
          <p>Status: {projeto.status}</p>
          <p>Risco: {projeto.risco}</p>
          <hr />
        </div>
      ))}
    </div>
  );
}

export default ProjetosPage;