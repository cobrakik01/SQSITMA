/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbk.sqsitma.controller.alumnos;

import com.cbk.sqsitma.controller.PersonalAtencionFacade;
import com.cbk.sqsitma.controller.PeticionFacade;
import com.cbk.sqsitma.controller.UsuarioFacade;
import com.cbk.sqsitma.controller.util.JsfUtil;
import com.cbk.sqsitma.controller.util.JsfUtil.PersistAction;
import com.cbk.sqsitma.controller.util.SesionManagedBean;
import com.cbk.sqsitma.entity.PersonalAtencion;
import com.cbk.sqsitma.entity.Peticion;
import com.cbk.sqsitma.entity.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author cobrakik
 */
@Named(value = "alumnosManagedBean")
@SessionScoped
public class AlumnosManagedBean implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private PeticionFacade peticionFacade;

    @EJB
    private PersonalAtencionFacade personalAtencionFacade;

    private List<Peticion> peticiones = null;

    private Peticion peticionSelected;

    private List<PersonalAtencion> respuestasPeticionSeleccionada;

    @ManagedProperty(value = "#{sesionManagedBean}")
    private SesionManagedBean sesion;

    /**
     * Creates a new instance of AlumnosManagedBean
     */
    public AlumnosManagedBean() {
    }
    
    @PreDestroy
    public void destroy() {
        peticiones = null;
    }

    public List<PersonalAtencion> getRespuestasPeticionSeleccionada() {
        respuestasPeticionSeleccionada = personalAtencionFacade.findAll(peticionSelected);
        return respuestasPeticionSeleccionada;
    }

    public void setRespuestasPeticionSeleccionada(List<PersonalAtencion> respuestasPeticionSeleccionada) {
        this.respuestasPeticionSeleccionada = respuestasPeticionSeleccionada;
    }

    public SesionManagedBean getSesion() {
        return sesion;
    }

    public void setSesion(SesionManagedBean sesion) {
        this.sesion = sesion;
    }

    public PeticionFacade getPeticionFacade() {
        return peticionFacade;
    }

    public List<Peticion> getPeticiones() {
        //if (peticiones == null) {
            peticiones = null;
            Usuario us = JsfUtil.getUserSession(usuarioFacade);
            peticiones = peticionFacade.findAll(us);
        //}
        return peticiones;
    }

    public void setPeticiones(List<Peticion> peticiones) {
        this.peticiones = peticiones;
    }

    public Peticion getPeticionSelected() {
        return peticionSelected;
    }

    public void setPeticionSelected(Peticion peticion) {
        this.peticionSelected = peticion;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public Peticion preCreationPeticion() {
        peticionSelected = new Peticion();
        initializeEmbeddableKey();
        return peticionSelected;
    }

    public void createPeticion() {
        peticionSelected.setCreatedAt(new Date());
        peticionSelected.setUpdatedAt(peticionSelected.getCreatedAt());
        peticionSelected.setUsuarioEmail(usuarioFacade.findByEmail(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName()));
        persistPeticion(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PeticionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            peticiones = null;    // Invalidate list of items to trigger re-query.
        }
    }

    private void persistPeticion(PersistAction persistAction, String successMessage) {
        if (peticionSelected != null) {
            try {
                if (persistAction != PersistAction.DELETE) {
                    peticionFacade.edit(peticionSelected);
                } else {
                    peticionFacade.remove(peticionSelected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    @FacesConverter(forClass = Peticion.class)
    public static class PeticionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AlumnosManagedBean controller = (AlumnosManagedBean) context.getApplication().getELResolver().getValue(context.getELContext(), null, "alumnosManagedBean");
            return controller.getPeticionFacade().find(value);
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Peticion) {
                Peticion o = (Peticion) object;
                return String.valueOf(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Peticion.class.getName()});
                return null;
            }
        }

    }

}
