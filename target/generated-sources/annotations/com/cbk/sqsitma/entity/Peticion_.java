package com.cbk.sqsitma.entity;

import com.cbk.sqsitma.entity.PersonalAtencion;
import com.cbk.sqsitma.entity.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-20T18:13:51")
@StaticMetamodel(Peticion.class)
public class Peticion_ { 

    public static volatile SingularAttribute<Peticion, Date> createdAt;
    public static volatile ListAttribute<Peticion, PersonalAtencion> personalAtencionList;
    public static volatile SingularAttribute<Peticion, String> asunto;
    public static volatile SingularAttribute<Peticion, Usuario> usuarioEmail;
    public static volatile SingularAttribute<Peticion, Integer> id;
    public static volatile SingularAttribute<Peticion, String> peticion;
    public static volatile SingularAttribute<Peticion, Date> updatedAt;

}