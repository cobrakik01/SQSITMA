package com.cbk.sqsitma.entity;

import com.cbk.sqsitma.entity.EstatusPeticion;
import com.cbk.sqsitma.entity.Peticion;
import com.cbk.sqsitma.entity.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-21T16:36:29")
@StaticMetamodel(PersonalAtencion.class)
public class PersonalAtencion_ { 

    public static volatile SingularAttribute<PersonalAtencion, EstatusPeticion> estatusPeticionId;
    public static volatile SingularAttribute<PersonalAtencion, Date> createdAt;
    public static volatile SingularAttribute<PersonalAtencion, Peticion> peticionId;
    public static volatile SingularAttribute<PersonalAtencion, Integer> id;
    public static volatile SingularAttribute<PersonalAtencion, Usuario> personalEmail;

}