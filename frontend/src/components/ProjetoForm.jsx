import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
  TextField,
  MenuItem,
} from "@mui/material";

import { useState, useEffect } from "react";

function ProjetoForm({
  open,
  onClose,
  onSalvar,
  projetoSelecionado,
}) {

  const [form, setForm] = useState({
    nome: "",
    status: "",
    risco: "",
  });

  useEffect(() => {

    if (projetoSelecionado) {
      setForm(projetoSelecionado);
    }

  }, [projetoSelecionado]);

  function handleChange(e) {

    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  }

  function handleSubmit() {
    onSalvar(form);
  }

  return (
    <Dialog
      open={open}
      onClose={onClose}
      fullWidth
    >

      <DialogTitle>
        Projeto
      </DialogTitle>

      <DialogContent>

        <TextField
          label="Nome"
          name="nome"
          value={form.nome}
          onChange={handleChange}
          fullWidth
          margin="normal"
        />

        <TextField
          select
          label="Status"
          name="status"
          value={form.status}
          onChange={handleChange}
          fullWidth
          margin="normal"
        >

          <MenuItem value="EM_ANALISE">
            EM_ANALISE
          </MenuItem>

          <MenuItem value="ANALISE_REALIZADA">
            ANALISE_REALIZADA
          </MenuItem>

          <MenuItem value="ANALISE_APROVADA">
            ANALISE_APROVADA
          </MenuItem>

        </TextField>

        <TextField
          select
          label="Risco"
          name="risco"
          value={form.risco}
          onChange={handleChange}
          fullWidth
          margin="normal"
        >

          <MenuItem value="BAIXO">
            BAIXO
          </MenuItem>

          <MenuItem value="MEDIO">
            MEDIO
          </MenuItem>

          <MenuItem value="ALTO">
            ALTO
          </MenuItem>

        </TextField>

      </DialogContent>

      <DialogActions>

        <Button onClick={onClose}>
          Cancelar
        </Button>

        <Button
          variant="contained"
          onClick={handleSubmit}
        >
          Salvar
        </Button>

      </DialogActions>

    </Dialog>
  );
}

export default ProjetoForm;