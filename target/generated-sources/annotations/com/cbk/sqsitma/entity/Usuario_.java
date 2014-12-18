package com.cbk.sqsitma.entity;

import com.cbk.sqsitma.entity.Carrera;
import com.cbk.sqsitma.entity.Departamento;
import com.cbk.sqsitma.entity.GrupoUsuario;
import com.cbk.sqsitma.entity.PersonalAtencion;
import com.cbk.sqsitma.entity.Peticion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-17T22:18:30")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> app;
    public static volatile ListAttribute<Usuario, PersonalAtencion> personalAtencionList;
    public static volatile SingularAttribute<Usuario, String> clave;
    public static volatile ListAttribute<Usuario, Peticion> peticionList;
    public static volatile SingularAttribute<Usuario, Boolean> alumno;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile SingularAttribute<Usuario, Carrera> carreraId;
    public static volatile SingularAttribute<Usuario, Date> createdAt;
    public static volatile SingularAttribute<Usuario, String> password;
    public static volatile ListAttribute<Usuario, GrupoUsuario> grupoUsuarioList;
    public static volatile SingularAttribute<Usuario, Departamento> departamentoId;
    public static volatile SingularAttribute<Usuario, String> email;
    public static volatile SingularAttribute<Usuario, String> apm;
    public static volatile SingularAttribute<Usuario, Date> updatedAt;

}