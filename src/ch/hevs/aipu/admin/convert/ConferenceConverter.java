package ch.hevs.aipu.admin.convert;

import ch.hevs.aipu.admin.entity.Conference;
import ch.hevs.aipu.admin.managedbean.StakeholderBean;
import ch.hevs.aipu.admin.service.Aipu;
import ch.hevs.aipu.admin.service.AipuBean;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.util.List;

/**
 * Created by Muhamed on 11.12.2015.
 */
@FacesConverter("conferenceConverter")
public class ConferenceConverter implements Converter{

    @Override
    public Conference getAsObject(FacesContext context, UIComponent component, String value) {

        if(value != null && value.trim().length() > 0) {
            try {
                Aipu service = new AipuBean();
                //StakeholderBean service = (StakeholderBean) context.getExternalContext().getApplicationMap().get("StakeholderBean");
                List<Conference> list= service.getAllConferences();
                int position = 0;
                for (int i = 0; i < list.size(); i++){
                    if (list.get(i).getTitle().equals(value)){
                        position = i;
                    }
                }

                if(list.size()==0)
                    return null;

                return list.get(position);
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid conference."));
            }
        }
        else {
            FacesMessage message = new FacesMessage("Choose a Conference");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return null;
    }
}
