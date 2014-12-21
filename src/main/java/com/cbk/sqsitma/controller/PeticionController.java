package com.cbk.sqsitma.controller;

import com.cbk.sqsitma.entity.Peticion;
import com.cbk.sqsitma.controller.util.JsfUtil;
import com.cbk.sqsitma.controller.util.JsfUtil.PersistAction;
import com.cbk.sqsitma.entity.EstatusPeticion;
import com.cbk.sqsitma.entity.PersonalAtencion;
import com.cbk.sqsitma.entity.Usuario;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("peticionController")
@SessionScoped
public class PeticionController implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private EstatusPeticionFacade estatusPeticionFacade;

    @EJB
    private PersonalAtencionFacade personalAtencionFacade;

    @EJB
    private com.cbk.sqsitma.controller.PeticionFacade ejbFacade;
    private List<Peticion> filteredPeticiones;
    private List<Peticion> items = null;
    private Peticion selected;
    private PersonalAtencion personalAtencion;
    private EstatusPeticion estatusPeticion;
    private List<EstatusPeticion> listaEstatusPeticion;

    public PeticionController() {
    }

    public List<Peticion> getFilteredPeticiones() {
        return filteredPeticiones;
    }

    public void setFilteredPeticiones(List<Peticion> filteredPeticiones) {
        this.filteredPeticiones = filteredPeticiones;
    }

    public List<EstatusPeticion> getListaEstatusPeticion() {
        listaEstatusPeticion = estatusPeticionFacade.findAll();
        return listaEstatusPeticion;
    }

    public void setListaEstatusPeticion(List<EstatusPeticion> listaEstatusPeticion) {
        this.listaEstatusPeticion = listaEstatusPeticion;
    }

    public EstatusPeticion getEstatusPeticion() {
        return estatusPeticion;
    }

    public void setEstatusPeticion(EstatusPeticion estatusPeticion) {
        this.estatusPeticion = estatusPeticion;
    }

    public PersonalAtencion getPersonalAtencion() {
        return personalAtencion;
    }

    public void setPersonalAtencion(PersonalAtencion personalAtencion) {
        this.personalAtencion = personalAtencion;
    }

    public Peticion getSelected() {
        return selected;
    }

    public void setSelected(Peticion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PeticionFacade getFacade() {
        return ejbFacade;
    }

    public Peticion prepareCreate() {
        selected = new Peticion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PeticionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PeticionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PeticionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Peticion> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
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

    public Peticion getPeticion(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Peticion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Peticion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Peticion.class)
    public static class PeticionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PeticionController controller = (PeticionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "peticionController");
            return controller.getPeticion(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Peticion) {
                Peticion o = (Peticion) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Peticion.class.getName()});
                return null;
            }
        }

    }

    public String estatusPeticion(Peticion peticion) {
        List<PersonalAtencion> l = personalAtencionFacade.findAll(peticion);
        if (l.size() > 0) {
            return l.get(0).getEstatusPeticionId().getNombreEstatus();
        }
        return "Sin respuesta";
    }

    private List<PersonalAtencion> respuestasPeticionSeleccionada;

    public List<PersonalAtencion> respuestasPorPeticion(Peticion peticion) {
        return personalAtencionFacade.findAll(peticion);
    }

    public List<PersonalAtencion> getRespuestasPeticionSeleccionada() {
        respuestasPeticionSeleccionada = personalAtencionFacade.findAll(selected);
        return respuestasPeticionSeleccionada;
    }

    public void setRespuestasPeticionSeleccionada(List<PersonalAtencion> respuestasPeticionSeleccionada) {
        this.respuestasPeticionSeleccionada = respuestasPeticionSeleccionada;
    }

    public PersonalAtencion prepareCreatePersonalAtencion() {
        personalAtencion = new PersonalAtencion();
        estatusPeticion = new EstatusPeticion();
        return personalAtencion;
    }

    public void responderPeticion() {
        Peticion peticionSeleccionada = selected;
        EstatusPeticion estatusSeleccionado = estatusPeticion;
        Usuario usuarioQueAtiende = JsfUtil.getUserSession(usuarioFacade);

        PersonalAtencion respuesta = new PersonalAtencion();
        respuesta.setCreatedAt(new Date());
        respuesta.setEstatusPeticionId(estatusSeleccionado);
        respuesta.setPersonalEmail(usuarioQueAtiende);
        respuesta.setPeticionId(peticionSeleccionada);
        personalAtencionFacade.create(respuesta);

        JsfUtil.addSuccessMessage("Se registro la respuesta correctamente.");
    }

}
