package modelo;

import java.util.ArrayList;

public class ImpressoraContrato {
    
    private int empresaId;
    private int modeloId;
    private String serie;
    private String chapa;

    // Construtor para inicializar os valores
    public ImpressoraContrato(int empresaId, int modeloId, String serie, String chapa) {
        this.empresaId  = empresaId;
        this.modeloId   = modeloId;
        this.serie      = serie;
        this.chapa      = chapa;
    }

    /**
     * @return the empresaId
     */
    public int getEmpresaId() {
        return empresaId;
    }

    /**
     * @param empresaId the empresaId to set
     */
    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }

    /**
     * @return the modeloId
     */
    public int getModeloId() {
        return modeloId;
    }

    /**
     * @param modeloId the modeloId to set
     */
    public void setModeloId(int modeloId) {
        this.modeloId = modeloId;
    }

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * @return the chapa
     */
    public String getChapa() {
        return chapa;
    }

    /**
     * @param chapa the chapa to set
     */
    public void setChapa(String chapa) {
        this.chapa = chapa;
    }

  
}
