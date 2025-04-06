package sistemarestaurantenegocio;

import sistemarestaurantedominio.Comanda;
import sistemarestaurantedominio.dtos.NuevaComandaDTO;

public interface IComandasBO {
    public abstract Comanda registrarComanda(NuevaComandaDTO nuevaComanda);
}
