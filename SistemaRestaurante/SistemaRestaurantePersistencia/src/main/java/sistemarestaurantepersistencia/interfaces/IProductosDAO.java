/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sistemarestaurantepersistencia.interfaces;

import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.dtos.NuevoProductoDTO;

/**
 *
 * @author gael_
 */
public interface IProductosDAO {
    public abstract Producto guardar(NuevoProductoDTO NuevoVideojuego);
}
