
package com.umes.jeb.hww.eis.dto;


public class TipoCampo {

    protected String atributoLog;
    protected boolean ignoreLog;
    protected Integer codigo;
    protected Integer id;
    protected String nombre;
    protected Short version;

    public String getAtributoLog() {
        return atributoLog;
    }

    public void setAtributoLog(String atributoLog) {
        this.atributoLog = atributoLog;
    }

    public boolean isIgnoreLog() {
        return ignoreLog;
    }

    public void setIgnoreLog(boolean ignoreLog) {
        this.ignoreLog = ignoreLog;
    }

    /**
     * Gets the value of the codigo property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Sets the value of the codigo property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setCodigo(Integer value) {
        this.codigo = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the nombre property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the version property.
     *
     * @return possible object is
     * {@link Short }
     */
    public Short getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     *
     * @param value allowed object is
     *              {@link Short }
     */
    public void setVersion(Short value) {
        this.version = value;
    }

}
