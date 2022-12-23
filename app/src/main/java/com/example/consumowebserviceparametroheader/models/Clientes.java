package com.example.consumowebserviceparametroheader.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Clientes {
    private String cedula;
    private String nombres;
    private String correo;
    private String telefono;
    private String fecnaci;
    private String urlavatar;


    public String getUrlavatar() { return urlavatar; }

    public void setUrlavatar(String urlavatar) { this.urlavatar = urlavatar; }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecnaci() {
        return fecnaci;
    }

    public void setFecnaci(String fecnaci) {
        this.fecnaci = fecnaci;
    }



    public Clientes(JSONObject a) throws JSONException {
        cedula =  a.getString("identificacion").toString() ;
        nombres =  a.getString("nombre").toString() ;
        correo =  a.getString("correo").toString() ;
        telefono =  a.getString("telefonomovil").toString() ;
        fecnaci = a.getString("fnacimiento").toString() ;
        urlavatar = a.getString("foto").toString() ;
    }
    public static ArrayList<Clientes> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Clientes> cliente = new ArrayList<>();
        for (int i = 0; i < datos.length() && i < 20; i++) {
            cliente.add(new Clientes(datos.getJSONObject(i)));
        }
        return cliente;
    }
}
