import {
  Paper,
  Chip,
  IconButton,
} from "@mui/material";

import {
  DataGrid,
} from "@mui/x-data-grid";

import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";

function ProjetoTable({
  projetos,
  onEditar,
  onExcluir,
}) {

  const columns = [
    {
      field: "nome",
      headerName: "Projeto",
      flex: 1,
    },
    {
      field: "status",
      headerName: "Status",
      flex: 1,
      renderCell: (params) => (
        <Chip
          label={params.value}
          color="primary"
        />
      ),
    },
    {
      field: "risco",
      headerName: "Risco",
      flex: 1,
      renderCell: (params) => {

        let color = "success";

        if (params.value === "MEDIO") {
          color = "warning";
        }

        if (params.value === "ALTO") {
          color = "error";
        }

        return (
          <Chip
            label={params.value}
            color={color}
          />
        );
      },
    },
    {
      field: "acoes",
      headerName: "Ações",
      width: 150,
      renderCell: (params) => (
        <>
          <IconButton
            color="primary"
            onClick={() => onEditar(params.row)}
          >
            <EditIcon />
          </IconButton>

          <IconButton
            color="error"
            onClick={() => onExcluir(params.row.id)}
          >
            <DeleteIcon />
          </IconButton>
        </>
      ),
    },
  ];

  return (
    <Paper
      sx={{
        height: 600,
        borderRadius: 4,
      }}
    >
      <DataGrid
        rows={projetos}
        columns={columns}
        pageSizeOptions={[5, 10]}
      />
    </Paper>
  );
}

export default ProjetoTable;