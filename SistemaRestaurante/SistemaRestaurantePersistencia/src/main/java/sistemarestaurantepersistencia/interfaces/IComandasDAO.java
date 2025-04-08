package sistemarestaurantepersistencia.interfaces;

import java.time.LocalDate;
import java.util.List;
import sistemarestaurantedominio.Comanda;
import sistemarestaurantedominio.Mesa;
import sistemarestaurantedominio.dtos.NuevaComandaDTO;

public interface IComandasDAO {
    public abstract Comanda guardarComanda(NuevaComandaDTO nuevaComanda);
    public abstract List<NuevaComandaDTO> consultarComandas();
    public abstract List<NuevaComandaDTO> consultarComandasPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin);
    public abstract List<Mesa> obtenerMesas();
    public abstract Mesa buscarMesaPorNumero(int numeroMesa);
    public abstract int obtenerConsecutivoDelDia();
}
