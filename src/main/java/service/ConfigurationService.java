package service;

import dao.ConfigurationDAO;
import entities.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by linhtran on 07/05/2017.
 */

@Service
@Transactional
public class ConfigurationService extends AbstractService<Configuration>{

    @Autowired
    ConfigurationDAO configurationDAO;

    public ConfigurationService() {
    }

    public void save(Configuration configuration)
    {
        this.configurationDAO.update(configuration);
    }
    public void delete(int idConf)
    {
        this.configurationDAO.delete(idConf);
    }

    public Class<Configuration> getClassTable() {
        return Configuration.class;
    }

    public Configuration find(int id)
    {
        return this.find(id);
    }
    public List<Configuration> getAllConfiguration()
    {
        return this.findAll();
    }

    public  boolean isHaveFormatTime(Configuration config,String str)
    {
        if(config.getDateFormat().trim().equalsIgnoreCase(str.trim()))
        {
            return true;
        }
        return false;
    }

}
