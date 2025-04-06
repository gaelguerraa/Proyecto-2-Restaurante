package sistemarestaurantepersistencia.interfaces;

import java.util.List;
import sistemarestaurantedominio.Comanda;
import sistemarestaurantedominio.dtos.NuevaComandaDTO;

public interface IComandasDAO {
    public abstract Comanda guardarComanda(NuevaComandaDTO nuevaComanda);
    public abstract List<Comanda> consultarComandasPorFecha();
}
