/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.siga.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Stalin
 */
@Entity
@Table(name = "foto")
public class Foto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "FOTO_ID")
    private Integer fotoId;
    @Lob
    @Column(name = "FOTO")
    private byte[] foto;

    
	public Integer getFotoId() {
		return fotoId;
	}


	public void setFotoId(Integer fotoId) {
		this.fotoId = fotoId;
	}


	public byte[] getFoto() {
		return foto;
	}


	public void setFoto(byte[] foto) {
		this.foto = foto;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Foto(Integer fotoId, byte[] foto) {
		super();
		this.fotoId = fotoId;
		this.foto = foto;
	}


	public Foto() {
    }

    
    
}
