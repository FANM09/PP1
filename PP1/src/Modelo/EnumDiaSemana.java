/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dylan Mendez
 */
public enum EnumDiaSemana {
  LUNES(2,"Lunes"),
  MARTES(3,"Martes"),
  MIERCOLES(4,"Miercoles"),
  JUEVES(5,"Jueves"),
  VIERNES(6,"Viernes"),
  SABADO(7,"Sabado"),
  DOMINGO(1,"Domingo");
  
  private final int diaInt;
  private final String diaString;
  private static final Map lookup = new HashMap();
  
  static {
    EnumSet.allOf(EnumDiaSemana.class).forEach((each) -> {
      lookup.put(each.diaInt, each.diaString);
    });
  }
  
  private EnumDiaSemana(int diaInt, String diaStr){
    this.diaInt=diaInt;
    this.diaString=diaStr;
  }
  
  public static String get(int v){
    return (String) lookup.get(v);
  }


}
