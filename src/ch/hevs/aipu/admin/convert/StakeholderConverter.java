package ch.hevs.aipu.admin.convert;

import ch.hevs.aipu.admin.entity.Stakeholder;
import ch.hevs.aipu.admin.service.Aipu;
import ch.hevs.aipu.admin.service.AipuBean;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import java.util.List;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;

/**
 * Created by pok on 04.01.2016.
 */
@FacesConverter("stakeholderConverter")
public class StakeholderConverter implements Converter{

    @Override
    public Stakeholder getAsObject(FacesContext context, UIComponent component, String value) {

        if(value != null && value.trim().length() > 0) {
            try {
                Aipu service = new AipuBean();
                //StakeholderBean service = (StakeholderBean) context.getExternalContext().getApplicationMap().get("StakeholderBean");
                List<Stakeholder> list= service.getAllStakeholder();
                int position = 0;
                for (int i = 0; i < list.size(); i++){
                    if (list.get(i).getName().equals(value)){
                        position = i;
                    }
                }

                if(list.size()==0)
                    return null;

                return list.get(position);
            } catch(NumberFormatException e) {

                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid stakeholder."));
            }
        }
        else {
            FacesMessage message = new FacesMessage("Choose a Stakeholder");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return null;
    }

}
